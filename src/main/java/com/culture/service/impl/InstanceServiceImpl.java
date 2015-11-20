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

import com.culture.model.Instance;
import com.culture.model.OClass;
import com.culture.model.OModelFactory;
import com.culture.model.OProperty;
import com.culture.service.InstanceService;
import com.culture.service.OClassService;
import com.culture.service.OPropertyService;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntProperty;

@Service
public class InstanceServiceImpl extends BaseService implements InstanceService{

	@Autowired
	private OModelFactory omodelFactory;
	@Autowired
	private OPropertyService opService;
	@Autowired
	private OClassService ocService;

	
	private static final Logger logger = Logger.getLogger(InstanceServiceImpl.class);
	
	/*中英文属性映射*/
	private Map<String,String> propertyMap = new HashMap<String,String>(){{
		put("名称", "title");
		put("其他名称", "used_title");
//		put("级别", "c_level");
		put("创作朝代", "creation_date");
//		put("创作时间", "creation_time");
//		put("产地", "place_of_origin");
		put("创作者", "creator");
//		put("度量", "measurement");
//		put("出土时间", "excavation_date");
//		put("出土地点", "excavation_place");
//		put("现藏地点", "current_location");
//		put("展览历史", "exhibition_history");
//		put("地域环境", "location");
		put("器型", "shape");
		put("纹饰", "pattern");
		put("颜色", "color");
		put("结构", "structure");
//		put("装饰", "decoration");
		put("使用情境", "scene");
//		put("使用方式", "c_usage");
		put("象征意义", "symbolic_meaning");
		put("审美", "aesthetic_desc");
//		put("社会历史信息", "social_history_info");
//		put("材质", "material");
//		put("工艺", "technique");
//		put("历史信息", "history_info");
//		put("民间典故", "folklore");
//		put("关联", "relation");
		put("标识符", "identifier");
//		put("资料来源", "source");
//		put("作品授权", "rights");
	}};
	
	public String[] propertys = {"名称","其他名称","创作朝代","创作者","器型","纹饰","颜色","结构","使用情境","象征意义","审美"};

	/**
	 * 将文物实例写入到本体文件中
	 */
	public boolean addInstance(Map<String,String> valueMap,Instance cb) {
		// TODO Auto-generated method stub
		try{
			boolean flag = true;
			//保存到数据库
			flag = getInstanceDao().addInstance(cb);
			//保存到本体文件
//			if(flag){
//				writeInstance(valueMap,cb);
//			}
			return flag;
		}catch(Exception e){
			logger.error("将文物实例写入到本体文件出错："+e.getMessage());
			return false;
		}
	} 

	/**
	 * 更新本体实例
	 */
	public boolean editInstance(Map<String,String> valueMap,Instance cb) {
		// TODO Auto-generated method stub
		try{
			boolean flag = true;
			//更新数据库
			flag  = updateInstance(cb);
			//取得本体实例模型
//			OntModel instanceModel = omodelFactory.getInstanceModel();
//			//取得旧的本体实例
//			Individual oldCul = instanceModel.getIndividual(OModelFactory.NSI+valueMap.get("oldTitle"));
//			//删除旧的实例
//			if(oldCul != null)
//				oldCul.remove();
//			//生成新的实例
//			if(flag){
//				//取得文物的类别
//				String classification = cb.getClassification();
//				//取得本体概念模型
//				OntModel model = omodelFactory.getModel();
//				//查询本体文件，获得文物概念
//				OntClass ontc = model.getOntClass(OModelFactory.NSC+classification); 
//				//新建本体实例
//				Individual cul = instanceModel.createIndividual(OModelFactory.NSI+cb.getTitle(),ontc);
//				//为文物概念的每个属性赋值
//				for(int i=0;i<propertys.length;i++){
//					String property = propertys[i];
//					OProperty op = opService.getPropertyByName(property,false);
//					//如果是对象属性
//					if(op.getPtype()==1){
//						String opname = op.getPname();
//						String opvalue = valueMap.get(opname);
//						if(opvalue != null){//如果属性不为空
//							OntProperty ontp = model.getObjectProperty(OModelFactory.NSP+opname);
//							String values[] = opvalue.split(",");
//							for(String value:values){
//								//获取属性的值，为一个概念
//								OntClass ovalue = model.getOntClass(OModelFactory.NSC+value);
//								cul.addProperty(ontp, ovalue);
//							}
//						}
//					}else if(op.getPtype()==2){ //数据属性
//						String opname = op.getPname();
//						String opvalue = "";
//						opvalue = valueMap.get(opname);
//						if(opvalue != null){//如果属性不为空
//							OntProperty ontp = model.getDatatypeProperty(OModelFactory.NSP+opname);
//							cul.addProperty(ontp,opvalue);
//						}
//					}
//				}
//				//写到owl文件
//				File file = new File(omodelFactory.getInstanceFile());
//				try{
//					OutputStream out = new FileOutputStream(file);
//					instanceModel.write(out);
//				}catch(Exception e){
//					e.printStackTrace();
//				}
//			}
			return flag;
		}catch(Exception e){
			logger.error("修改本体实例文件出错："+e.getMessage());
			return false;
		}
	}


	/**
	 * 删除本体实例
	 */
	public boolean delInstance(String culId,String title) {
		// TODO Auto-generated method stub
		boolean flag = true;
		try{
			//删除数据库
			flag = getInstanceDao().delInstance(culId);
//			//取得本体实例模型
//			OntModel instanceModel = omodelFactory.getInstanceModel();
//			//取得旧的本体实例
//			Individual oldCul = instanceModel.getIndividual(OModelFactory.NSI+title);
//			//删除旧的实例
//			if(oldCul != null)
//				oldCul.remove();
//			//更新owl文件
//			File file = new File(omodelFactory.getInstanceFile());
//			try{
//				OutputStream out = new FileOutputStream(file);
//				instanceModel.write(out);
//			}catch(Exception e){
//				e.printStackTrace();
//			}
		}catch(Exception e){
			logger.error("删除本体实例文件出错："+e.getMessage());
		}
		return flag;
	}
	
	/**
	 * 写本体实例到文件中去
	 */
	public void writeInstance(Map<String,String> valueMap,Instance cb){
		//取得文物的类别
		String classification = cb.getClassification();
		//取得本体概念模型
		OntModel model = omodelFactory.getModel();
		//查询本体文件，获得文物概念
		OntClass ontc = model.getOntClass(OModelFactory.NSC+classification);
		//取得本体实例模型
		OntModel instanceModel = omodelFactory.getInstanceModel();
		Individual cul = instanceModel.createIndividual(OModelFactory.NSI+cb.getTitle(),ontc);
		//为文物概念的每个属性赋值
		for(int i=0;i<propertys.length;i++){
			String property = propertys[i];
			OProperty op = opService.getPropertyByName(property,false);
			//如果是对象属性
			if(op.getPtype()==1){
				String opname = op.getPname();
				String opvalue = valueMap.get(opname);
				if(opvalue != null){//如果属性不为空
					OntProperty ontp = model.getObjectProperty(OModelFactory.NSP+opname);
					String values[] = opvalue.split(",");
					for(String value:values){
						//获取属性的值，为一个概念
						OntClass ovalue = model.getOntClass(OModelFactory.NSC+value);
						cul.addProperty(ontp, ovalue);
					}
				}
			}else if(op.getPtype()==2){ //数据属性
				String opname = op.getPname();
				String opvalue = "";
				opvalue = valueMap.get(opname);
				if(opvalue != null){//如果属性不为空
					OntProperty ontp = model.getDatatypeProperty(OModelFactory.NSP+opname);
					cul.addProperty(ontp,opvalue);
				}
			}
		}
		//写到owl文件
		File file = new File(omodelFactory.getInstanceFile());
		try{
			OutputStream out = new FileOutputStream(file);
			instanceModel.write(out);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * 根据实例id获取实例
	 */
	public Instance getInstanceById(String identifier) {
		// TODO Auto-generated method stub
		return getInstanceDao().getInstanceById(identifier);
	}

	/**
	 * 更新本体实例到数据库
	 */
	public boolean updateInstance(Instance instance) {
		// TODO Auto-generated method stub
		boolean flag = true;
		flag =  getInstanceDao().updateInstance(instance);
		if(!flag){	//数据库没有该信息
			flag = getInstanceDao().addInstance(instance);
		}
		return flag;
	}

	/**
	 * 获取创作朝代列表
	 */
	public List<OClass> getCreationDateList() {
		// TODO Auto-generated method stub
		OClass oclass = getOClassDao().getClassByName("朝代");
		List<OClass> dynasityList = getOClassDao().getClassList(oclass);
		List<OClass> creationDateList = new ArrayList<OClass>();
		OClass oc1 = new OClass();
		oc1.setCname("旧石器时代（距今约300万年-距今约1万年）");
		OClass oc2 = new OClass();
		oc2.setCname("新石器时代（距今1万年-距今5000至2000年不等）");
		creationDateList.add(oc1);
		creationDateList.add(oc2);
		creationDateList.addAll(dynasityList);
		return creationDateList;
	}

	/**
	 * 获取文物实例页面属性map
	 */
	public Map getInstanceMap() {
		// TODO Auto-generated method stub
		//获取创作朝代列表
		List<OClass> creationDateList = getCreationDateList();
		//获取纹饰列表
		List<OClass> patternList = ocService.getChildClass("纹饰", 0);
		//获取器形列表
		List<OClass> shapeList = ocService.getChildClass("器型", 0);
		//获取色彩列表
		List<OClass> colorList = ocService.getChildClass("颜色", 0);
		//获取使用情境列表
		List<OClass> sceneList = ocService.getChildClass("使用情境", 0);
		//获取象征意义列表
		List<OClass> meaningList = ocService.getChildClass("象征意义", 0);
		//获取结构列表
		List<OClass> structureList = ocService.getChildClass("结构", 0);
		//获取审美列表
		List<OClass> aestheticList = ocService.getChildClass("审美", 0);
		//将参数存到map里头
		Map map = new HashMap();
		map.put("creationDateList", creationDateList);
		map.put("patternList", patternList);
		map.put("shapeList", shapeList);
		map.put("colorList", colorList);
		map.put("sceneList", sceneList);
		map.put("meaningList", meaningList);
		map.put("structureList", structureList);
		map.put("aestheticList", aestheticList);
		return map;
	}
}
