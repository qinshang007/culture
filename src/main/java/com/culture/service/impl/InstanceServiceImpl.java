package com.culture.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.culture.model.CulturalBean;
import com.culture.model.OModelFactory;
import com.culture.model.OProperty;
import com.culture.service.InstanceService;
import com.culture.service.OPropertyService;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntProperty;

@Service
public class InstanceServiceImpl implements InstanceService{

	@Autowired
	private OModelFactory omodelFactory;
	@Autowired
	private OPropertyService opService;
	
	private static final Logger logger = Logger.getLogger(InstanceServiceImpl.class);
	
	/*中英文属性映射*/
	private Map<String,String> propertyMap = new HashMap<String,String>(){{
		put("名称", "title");
		put("其他名称", "used_title");
		put("级别", "c_level");
		put("创作朝代", "creation_date");
		put("创作时间", "creation_time");
		put("产地", "place_of_origin");
		put("创作者", "creator");
		put("度量", "measurement");
		put("出土时间", "excavation_date");
		put("出土地点", "excavation_place");
		put("现藏地点", "current_location");
		put("展览历史", "exhibition_history");
		put("地域环境", "location");
		put("器形", "shape");
		put("纹饰", "pattern");
		put("色彩", "color");
		put("结构", "structure");
		put("装饰", "decoration");
		put("使用情境", "scene");
		put("使用方式", "c_usage");
		put("象征意义", "symbolic_meaning");
		put("审美", "aesthetic_desc");
		put("社会历史信息", "social_history_info");
		put("材质", "material");
		put("工艺", "technique");
		put("历史信息", "history_info");
		put("民间典故", "folklore");
		put("关联", "relation");
		put("标识符", "identifier");
		put("资料来源", "source");
		put("作品授权", "rights");
	}};
	
	

	/**
	 * 将文物实例写入到本体文件中
	 */
	public void addInstance(HttpServletRequest request,CulturalBean cb) {
		// TODO Auto-generated method stub
		try{
			//取得文物的类别
			String classification = cb.getClassification();
			//取得本体概念模型
			OntModel model = omodelFactory.getModel();
			//查询本体文件，获得文物概念
			OntClass ontc = model.getOntClass(OModelFactory.NSC+classification);
			//取得本体实例模型
			OntModel instanceModel = omodelFactory.getInstanceModel();
			Individual cul = instanceModel.createIndividual(OModelFactory.NSI+cb.getTitle(),ontc);
			//获取文物概念的属性列表
			List<OProperty> opList = opService.getPropertys(classification);
			//为文物概念的每个属性赋值
			for(int i=0;i<opList.size();i++){
				OProperty op = opList.get(i);
				//如果是对象属性
				if(op.getPtype()==1){
					String opname = op.getPname();
					String opvalue = request.getParameter(propertyMap.get(opname));
					OntProperty ontp = model.getObjectProperty(OModelFactory.NSP+opname);
					//获取属性的值，为一个概念
					OntClass value = model.getOntClass(OModelFactory.NSC+opvalue);
					cul.addProperty(ontp, value);
				}else if(op.getPtype()==2){ //数据属性
					String opname = op.getPname();
					String opvalue = "";
					if(opname.equals("标识符")){
						opvalue = cb.getIdentifier();
					}else{
						opvalue = request.getParameter(propertyMap.get(opname));
					}
					OntProperty ontp = model.getDatatypeProperty(OModelFactory.NSP+opname);
					cul.addProperty(ontp,opvalue);
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

		}catch(Exception e){
			logger.error("将文物实例写入到本体文件出错："+e.getMessage());
		}
	}


	/**
	 * 更新本体实例
	 */
	public void editInstance(HttpServletRequest request, CulturalBean cb,String oldTitle) {
		// TODO Auto-generated method stub
		try{
			//取得本体实例模型
			OntModel instanceModel = omodelFactory.getInstanceModel();
			//取得旧的本体实例
			Individual oldCul = instanceModel.getIndividual(OModelFactory.NSI+oldTitle);
			//删除旧的实例
			if(oldCul != null)
				oldCul.remove();
			//生成新的实例
			addInstance(request,cb);
		}catch(Exception e){
			logger.error("修改本体实例文件出错："+e.getMessage());
		}
	}


	/**
	 * 删除本体实例
	 */
	public void delInstance(String title) {
		// TODO Auto-generated method stub
		try{
			//取得本体实例模型
			OntModel instanceModel = omodelFactory.getInstanceModel();
			//取得旧的本体实例
			Individual oldCul = instanceModel.getIndividual(OModelFactory.NSI+title);
			//删除旧的实例
			if(oldCul != null)
				oldCul.remove();
			//更新owl文件
			File file = new File(omodelFactory.getInstanceFile());
			try{
				OutputStream out = new FileOutputStream(file);
				instanceModel.write(out);
			}catch(Exception e){
				e.printStackTrace();
			}

		}catch(Exception e){
			logger.error("删除本体实例文件出错："+e.getMessage());
		}
	}
}
