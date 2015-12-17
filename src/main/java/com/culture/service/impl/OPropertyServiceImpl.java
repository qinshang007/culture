package com.culture.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.culture.model.OClass;
import com.culture.model.OModelFactory;
import com.culture.model.OProperty;
import com.culture.model.UserBean;
import com.culture.service.OClassService;
import com.culture.service.OPropertyService;
import com.culture.service.UserService;
import com.culture.util.StringUtils;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;

@Service
public class OPropertyServiceImpl extends BaseService implements OPropertyService{

	@Autowired
	private OModelFactory omodelFactory;
	
	@Autowired
	private UserService userService;
	@Autowired
	private OClassService ocService;

	private static final Logger logger = Logger.getLogger(OPropertyServiceImpl.class);
	
	public List<OProperty> getPropertyList(String userName) {
		// TODO Auto-generated method stub
		//获取用户权限
		UserBean user = userService.getUserByName(userName);
		Map map = new HashMap();
		if(user != null){
			map.put("manager", user.getUserName());
			map.put("level", user.getLevel());
		}
		map.put("del", 0);
		return getOPropertyDao().getPropertyList(map);
	}

	public OProperty getPropertyById(int id) {
		// TODO Auto-generated method stub
		return getOPropertyDao().getPropertyById(id);
	}

	/**
	 * 根据属性名字返回属性
	 */
	public OProperty getPropertyByName(String pname,boolean needRange) {
		// TODO Auto-generated method stub
		OProperty op = new OProperty();
		try{
			op = getOPropertyDao().getPropertyByName(pname);
			if(!needRange)	//是否需要属性的值域
				return op;
			if(op != null){
				List<OClass> rangeList = new ArrayList<OClass>();
				String range = op.getPrange();
				if(range != null){
					String ranges[] = range.split(",");
					for(int i=0;i<ranges.length;i++){
						List<OClass> temp = ocService.getChildClass(ranges[i],0);
						if(temp != null)
							rangeList.addAll(temp);
					}
					op.setRangeList(rangeList);
				}
			}
		}catch(Exception e){
			logger.error("根据属性名字返回属性出错："+e.getMessage());
		}
		return op;
	}
	
	/**
	 * 添加属性
	 */
	public boolean addProperty(OProperty op) {
		// TODO Auto-generated method stub
		boolean flag = true;
		//保存到数据库
		flag =  getOPropertyDao().addProperty(op);
		//写入到owl文件
//		if(flag){
//			//更新到本体文件
//			flag = writePropertyToFile(op,null);
//		}
		return flag;
	}

	/**
	 * 更新属性
	 */
	public boolean updateProperty(OProperty op,String oldPname) {
		// TODO Auto-generated method stub
		boolean flag = true;
		//更新到数据库
		flag =  getOPropertyDao().updateProperty(op);
//		//更新到本体文件
//		if(flag){
//			flag = writePropertyToFile(op,oldPname);
//		}
		return flag;
	}
	

	/**
	 * 将属性写入到本体文件
	 * @param op
	 */
	public boolean writePropertyToFile(OProperty op,String oldPname){
		boolean flag = true;
		OntModel model = omodelFactory.getModel();
		//如果oldpname不为空，表示更新属性，为空表示添加属性
		if(StringUtils.isNotEmpty(oldPname)){
			//删除旧的属性
			 OntProperty oldProperty = model.getOntProperty(OModelFactory.NSP+oldPname);
			 oldProperty.remove();
		}
		 //创建新的属性
		 OntProperty children = null;
			
		//对象属性
		if(op.getPtype()==1){
			 children = model.createObjectProperty(OModelFactory.NSP+op.getPname());
			 
			//获取值域数组
			String[] rangeArray = op.getPrange().split(",");
			
			//给该属性添加值域
			for(int i=0;i<rangeArray.length;i++){
				OntClass oclass = model.getOntClass(OModelFactory.NSC+rangeArray[i]);
				children.addRange(oclass);
			}
			 
		}else if(op.getPtype()==2){ //数据属性
			 children = model.createDatatypeProperty(OModelFactory.NSP+op.getPname());
			 
			 //获取数据属性的类型
			 int index = Integer.valueOf(op.getPrange());
			 Resource s = OModelFactory.resource[index];
			 children.addRange(s);
		}
		
		//如果父属性不为空
		if(StringUtils.isNotEmpty(op.getPfname())&&!op.getPfname().equals("无")){
			//从本体文件中查询该父属性
			OntProperty parent = model.getOntProperty(OModelFactory.NSP+op.getPfname());//获取父属性
			parent.addSubProperty(children);
		}
		
		//获取定义域列表
		String[] domainArray = op.getPdomain().split(",");
		
		//遍历定义域数组
		for(int i=0;i<domainArray.length;i++){
			OntClass oclass = model.getOntClass(OModelFactory.NSC+domainArray[i]);
			children.addDomain(oclass);
		}

		//write XML FILE
		File file = new File(omodelFactory.getOwlFile());
		try{
			OutputStream out = new FileOutputStream(file);
			model.write(out);
		}catch(Exception e){
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}
	
	/**
	 * 删除属性
	 */
	public boolean delProperty(String pid,String pname) {
		// TODO Auto-generated method stub
		boolean flag = true;
		//删除数据库
		flag = getOPropertyDao().delProperty(Integer.valueOf(pid));
		//更新数据库
		flag = getOPropertyDao().upgradeProperty(Integer.valueOf(pid));
		//删除本体文件
//		 OntModel model = omodelFactory.getModel();
//		 OntProperty oldProperty = model.getOntProperty(OModelFactory.NSP+pname);
//		 oldProperty.remove();
//		 //write XML FILE
//		 File file = new File(omodelFactory.getOwlFile());
//		 try{
//			 OutputStream out = new FileOutputStream(file);
//			 model.write(out);
//		 }catch(Exception e){
//			 e.printStackTrace();
//			 flag = false;
//		 }
		return flag;
	}

	
	/**
	 * 根据概念名字，获取所有与该概念关联的属性
	 */
	public List<OProperty> getPropertys(String cname) {
		// TODO Auto-generated method stub
		List<OProperty> oplist = new ArrayList<OProperty>();
		try{
			OntModel model = omodelFactory.getModel();
			//获取概念
			OntClass ontc = model.getOntClass(OModelFactory.NSC+cname);
			//获取概念的属性
			ExtendedIterator<OntProperty> iter = ontc.listDeclaredProperties();
			while(iter.hasNext()){
				OntProperty ontp = iter.next();
				String ontp_name = ontp.getLocalName();
				OProperty op = new OProperty();
				List<OClass> rangeList = new ArrayList<OClass>();
				op.setPname(ontp_name);
				//如果该属性是对象属性
				if(ontp.isObjectProperty()){
					op.setPtype(1);
					//获取该属性的值域
					ExtendedIterator<OntResource> rsiter = (ExtendedIterator<OntResource>) ontp.listRange();
					while(rsiter.hasNext()){
						OntResource rs = rsiter.next();
						if(rs.isClass()){
							OntClass rsc = rs.asClass();
							//如果该概念有子概念的话，遍历出它的所有子概念
							ExtendedIterator<OntClass> ontciter = rsc.listSubClasses(false);
							while(ontciter.hasNext()){
								OntClass suboc = ontciter.next();	//取出这个子概念
								String ocname = suboc.getLocalName();
								OClass oc = new OClass();
								oc.setCname(ocname);
								rangeList.add(oc);
							}
						}
					}
					op.setRangeList(rangeList);
				}else if(ontp.isDatatypeProperty()){//如果该属性是数据属性
					op.setPtype(2);
					OntResource rs = ontp.getRange();
					op.setPrange(rs.getLocalName());
				}
				oplist.add(op);
			}

		}catch(Exception e){
			logger.error("返回概念的属性出错："+e.getMessage());
		}
		return oplist;
	}

	/**
	 * 返回规则页面需要的属性
	 */
	public List<OProperty> getRulePropertys() {
		// TODO Auto-generated method stub
		List<OProperty> propertyList = new ArrayList<OProperty>();
		try{
//			OntModel model = omodelFactory.getModel();
//			OntProperty opattern = model.getOntProperty(OModelFactory.NSP+"纹饰");
//			OntProperty oscene = model.getOntProperty(OModelFactory.NSP+"使用情境");
//			OntProperty oshape = model.getOntProperty(OModelFactory.NSP+"器型");
//			OntProperty osymbolic_meaning = model.getOntProperty(OModelFactory.NSP+"象征意义");
//			OntProperty ocolor = model.getOntProperty(OModelFactory.NSP+"颜色");
//			OProperty pattern = transfer(opattern);
//			OProperty scene = transfer(oscene);
//			OProperty shape = transfer(oshape);
//			OProperty symbolic_meaning = transfer(osymbolic_meaning);
//			OProperty color = transfer(ocolor);
			OProperty pattern = getPropertyByName("纹饰",true);
			OProperty scene = getPropertyByName("使用情境",true);
			OProperty shape = getPropertyByName("器型",true);
			OProperty symbolic_meaning = getPropertyByName("象征意义",true);
			OProperty color = getPropertyByName("颜色",true);
			OProperty nianhao = getPropertyByName("年号", true);
			OProperty creation_date = getPropertyByName("创作朝代", true);
			propertyList.add(pattern);
			propertyList.add(scene);
			propertyList.add(shape);
			propertyList.add(symbolic_meaning);
			propertyList.add(color);
			propertyList.add(nianhao);
			propertyList.add(creation_date);
		}catch(Exception e){
			logger.error("返回规则页面需要的属性出错："+e.getMessage());
		}		
		return propertyList;
	}

	/**
	 * 遍历属性的值域
	 */
	public OProperty transfer(OntProperty ontp) {
		OProperty op = new OProperty();
		// TODO Auto-generated method stub
//		op.setPname(ontp_name);
		//如果该属性是对象属性
//		if(ontp.isObjectProperty()){
//			op.setPtype(1);
//			//获取该属性的值域
//			ExtendedIterator<OntResource> rsiter = (ExtendedIterator<OntResource>) ontp.listRange();
//			while(rsiter.hasNext()){
//				OntResource rs = rsiter.next();
//				if(rs.isClass()){
//					OntClass rsc = rs.asClass();
//					//如果该概念有子概念的话，遍历出它的所有子概念
//					ExtendedIterator<OntClass> ontciter = rsc.listSubClasses(false);
//					while(ontciter.hasNext()){
//						OntClass suboc = ontciter.next();	//取出这个子概念
//						String ocname = suboc.getLocalName();
//						OClass oc = new OClass();
//						oc.setCname(ocname);
//						rangeList.add(oc);
//					}
//				}
//			}
//			op.setRangeList(rangeList);
//		}else if(ontp.isDatatypeProperty()){//如果该属性是数据属性
//			op.setPtype(2);
//			OntResource rs = ontp.getRange();
//			op.setPrange(rs.getLocalName());
//		}
		return op;
	}

	/**
	 * 验证属性名字是否存在
	 */
	public boolean isPropertyExist(String pname) {
		// TODO Auto-generated method stub
		boolean flag = false;
		try{
			OProperty op = getOPropertyDao().getPropertyByName(pname);
			if(op != null)
				flag = true;
		}catch(Exception e){
			logger.error("验证属性名字是否存在出错："+e.getMessage());
		}
		return flag;
	}

	/**
	 * 生成属性的本体文件
	 */
	@Override
	public boolean genPropertyOwl() {
		// TODO Auto-generated method stub
		OntModel model = omodelFactory.getModel();
		//获取属性列表
		List<OProperty> oplist = getPropertyList("admin");
		try{
			for(int i=0;i<oplist.size();i++){
				OProperty op = oplist.get(i);
				System.out.println(i+" "+op.getPname());
				 //创建新的属性
				 OntProperty children = null;
				//对象属性
				if(op.getPtype()==1){
					
					 children = model.getObjectProperty(OModelFactory.NSP+op.getPname());
					 
					 if(children == null)
						 children = model.createObjectProperty(OModelFactory.NSP+op.getPname());
					 
					//获取值域数组
					String[] rangeArray = op.getPrange().split(",");
					
					//给该属性添加值域
					for(int j=0;j<rangeArray.length;j++){
						OntClass oclass = model.getOntClass(OModelFactory.NSC+rangeArray[j]);
						children.addRange(oclass);
					}
					 
				}else if(op.getPtype()==2){ //数据属性
					
					children = model.getDatatypeProperty(OModelFactory.NSP+op.getPname());
					
					if(children == null)
						children = model.createDatatypeProperty(OModelFactory.NSP+op.getPname());
					 
					 //获取数据属性的类型
					 int index = 0;//Integer.valueOf(op.getPrange());
					 Resource s = OModelFactory.resource[index];
					 children.addRange(s);
				}
				String pfname = null;
				int pfid = op.getPfid();
				int pftype = 0;
				if(pfid != 0){
					OProperty ofp = getOPropertyDao().getPropertyById(pfid);
					pfname = ofp.getPname();
					pftype = ofp.getPtype();
				}
				//如果父属性不为空
				if(StringUtils.isNotEmpty(pfname)&&!pfname.equals("无")){
					//从本体文件中查询该父属性
					OntProperty parent = model.getOntProperty(OModelFactory.NSP+op.getPfname());//获取父属性
					if(parent == null){
						if(pftype == 1){
							parent = model.createObjectProperty(OModelFactory.NSP+pfname);
						}else if(pftype == 2){
							parent = model.createDatatypeProperty(OModelFactory.NSP+pfname);
						}
					}
					parent.addSubProperty(children);
				}
				
				//获取定义域列表
				String[] domainArray = op.getPdomain().split(",");
				
				//遍历定义域数组
				for(int j=0;j<domainArray.length;j++){
					OntClass oclass = model.getOntClass(OModelFactory.NSC+domainArray[j]);
					children.addDomain(oclass);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		//写入owl文件
		File file = new File(omodelFactory.getOwlFile());
		try{
			OutputStream out = new FileOutputStream(file);
			model.write(out);
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
