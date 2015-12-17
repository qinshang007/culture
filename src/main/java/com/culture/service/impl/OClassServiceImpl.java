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
import com.culture.service.OClassService;
import com.culture.util.StringUtils;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;

@Service
public class OClassServiceImpl extends BaseService implements OClassService{

	private static final Logger logger = Logger.getLogger(OClassServiceImpl.class);
	
	@Autowired
	private OModelFactory omodelFactory;

	
	public List<OClass> getClassList(OClass oclass) {
		// TODO Auto-generated method stub
		return getOClassDao().getClassList(oclass);
	}

	public OClass getClassById(String id) {
		// TODO Auto-generated method stub
		return getOClassDao().getClassById(id);
	}

	public boolean addClass(OClass oclass) {
		// TODO Auto-generated method stub
		boolean flag = true;
		flag =  getOClassDao().addClass(oclass);
		if(flag){	//更新path
			int cfid = oclass.getCfid();
			String path = "";
			if(cfid == 0)
				path = "0";
			else{
				OClass pa = getClassById(String.valueOf(cfid));
				path = pa.getPath();
			}
			path = path+","+oclass.getCid();
			oclass.setPath(path);
			flag = updatePath(oclass);
		}
		return flag;
	}

	/**
	 * 删除概念
	 */
	public boolean delClass(String cid,String cname) {
		// TODO Auto-generated method stub
		boolean flag = true;
		List<OClass> ocList = getChildClass(Integer.valueOf(cid),0); 
		if(ocList.size() == 0){	//该概念是叶子概念
			flag =  getOClassDao().delClass(Integer.valueOf(cid));
		}else{	//该概念是非叶子概念
			//将其子概念的cfid置为0
			flag = getOClassDao().upgradeClass(Integer.valueOf(cid));
			//更改其子概念路径
			for(int i=0;i<ocList.size();i++){
				OClass oclass = ocList.get(i);
				String path = oclass.getPath();
				int index = path.indexOf(cid);
				String newPath = "0"+path.substring(index+cid.length());
				oclass.setPath(newPath);
				getOClassDao().updatePath(oclass);
			}
			//删除该概念
			flag =  getOClassDao().delClass(Integer.valueOf(cid));
		}
		//删除本体文件
//		if(flag){
//			 OntModel model = omodelFactory.getModel();
//			 OntClass oldClass = model.getOntClass(OModelFactory.NSC+cname);
//			 //如果概念有父概念的话
////			 if(oldClass.hasSuperClass()){
////				 OntClass parent = oldClass.getSuperClass();
////				 parent.removeSubClass(oldClass);
////			 }else if(oldClass.hasSubClass()){
////				 OntClass child = oldClass.getSubClass();
////				 oldClass.removeSubClass(child);
////			 }
//			 oldClass.remove();
//			 //write XML FILE
//			 File file = new File(omodelFactory.getOwlFile());
//			 try{
//				 OutputStream out = new FileOutputStream(file);
//				 model.write(out);
//			 }catch(Exception e){
//				 e.printStackTrace();
//				 flag = false;
//			 }
//		}
		return flag;
	}

	public boolean updateClass(OClass oclass) {
		// TODO Auto-generated method stub
		boolean flag = true;
		flag =  getOClassDao().updateClass(oclass);
		if(flag){	//更新path
			int cfid = oclass.getCfid();
			String path = "";
			if(cfid == 0)
				path = "0";
			else{
				OClass pa = getClassById(String.valueOf(cfid));
				path = pa.getPath();
			}
			path = path+","+oclass.getCid();
			oclass.setPath(path);
			flag = updatePath(oclass);
		}
		return flag;
	}

	public OClass getClassByName(String cname) {
		// TODO Auto-generated method stub
		return getOClassDao().getClassByName(cname);
	}

	/**
	 * 根据概念id，获取概念的子概念
	 * 如果direct为true，则返回概念的直系子概念
	 * 如果direct为false,则返回概念的所有子概念
	 */
	public List<OClass> getSubClasses(String cname, boolean direct) {
		// TODO Auto-generated method stub
		List<OClass> oclist = new ArrayList<OClass>();
		try{
			OntModel model = omodelFactory.getModel();
			//查询本体文件，获得文物概念
			OntClass ontc = model.getOntClass(OModelFactory.NSC+cname);
			//获取文物概念的所有子概念
			ExtendedIterator<OntClass> iter = ontc.listSubClasses(direct);
			while(iter.hasNext()){
				OntClass children = iter.next();
				String ocname = children.getLocalName();
				OClass temp = new OClass();
				temp.setCname(ocname);
				oclist.add(temp);
			}
		}catch(Exception e){
			logger.error("获取文物子概念出错："+e.getMessage());
		}
		return oclist;
	}

	public String getNameById(String id) {
		// TODO Auto-generated method stub
		String res = "";
		try{
			OClass oc = getOClassDao().getClassById(id);
			res = oc.getCname();
		}catch(Exception e){
			logger.error("根据概念id获取概念名字出错："+e.getMessage());
		}
		return res;
	}

	/**
	 * 验证概念名字是否存在
	 */
	public boolean isClassExist(String cname) {
		// TODO Auto-generated method stub
		boolean flag = false;
		try{
			OClass oc = getOClassDao().getClassByName(cname);
			if(oc != null)
				flag = true;
		}catch(Exception e){
			logger.error("验证概念名字是否存在出错："+e.getMessage());
		}
		return flag;
	}

	/**
	 * 更新路径
	 */
	public boolean updatePath(OClass oclass) {
		// TODO Auto-generated method stub
		return getOClassDao().updatePath(oclass);
	}

	/**
	 * 根据概念id查询数据库，返回某一概念的所有子概念
	 * 如果direct为1，则返回概念的直系子概念
	 * 如果direct为0,则返回概念的所有子概念
	 * @param cid
	 * @return
	 */
	public List<OClass> getChildClass(int cid,int direct) {
		// TODO Auto-generated method stub
		List<OClass> ocList = null;
		String query = ","+cid+",";
		Map map = new HashMap();
		map.put("cid", query);
		map.put("direct", direct);
		ocList =  getOClassDao().getChildClass(map);
		return ocList;
	}

	/**
	 * 根据概念名称查询数据库，返回某一概念的所有子概念
	 * 如果direct为1，则返回概念的直系子概念
	 * 如果direct为0,则返回概念的所有子概念
	 */
	public List<OClass> getChildClass(String cname,int direct) {
		// TODO Auto-generated method stub
		OClass oc = getOClassDao().getClassByName(cname);
		List<OClass> ocList = null;
		if(oc != null){
			int cid = oc.getCid();
			String query = ","+cid+",";
			Map map = new HashMap();
			map.put("cid", query);
			map.put("direct", direct);
			ocList = getOClassDao().getChildClass(map);
			return ocList;
		}
		return null;
	}

	/**
	 * 生成概念的本体文件
	 */
	@Override
	public boolean genClassOwl() {
		// TODO Auto-generated method stub
		OntModel model = omodelFactory.getModel();
		OClass oclass = new OClass();
		oclass.setDel(0);
		List<OClass> ocList = getOClassDao().getClassList(oclass);
		for(int i=0;i<ocList.size();i++){
			OClass oc = ocList.get(i);
			//获取概念名字
			String cname = oc.getCname();
			System.out.println(cname);
			//获取父概念名字
			String cfname = "";
			int cfid = oc.getCfid();
			//获取父概念id
			if(cfid != 0 ){
				OClass ofc = getClassById(String.valueOf(cfid));
				cfname = ofc.getCname();
			}
			OntClass children = model.getOntClass(OModelFactory.NSC+cname);
			if(children == null)
				children = model.createClass(OModelFactory.NSC+cname);
			if(!StringUtils.isEmpty(cfname)&&!cfname.equals("无")){
				OntClass parent = model.getOntClass(OModelFactory.NSC+cfname);//取得父概念
				if(parent == null){
					parent = model.createClass(OModelFactory.NSC+cfname);
				}
				parent.addSubClass(children);
			}
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
