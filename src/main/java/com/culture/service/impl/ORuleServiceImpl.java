package com.culture.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.culture.model.OModelFactory;
import com.culture.model.ORule;
import com.culture.service.ORuleService;
import com.culture.util.FileUtils;

@Service
public class ORuleServiceImpl extends BaseService implements ORuleService{
	
	@Autowired
	private OModelFactory modelFactory;

	/**
	 * 添加规则
	 */
	public boolean addRule(ORule orule,String opBodyType1,String opBodyType2,String opHeadType) {
		// TODO Auto-generated method stub
		boolean flag = true;
		String rule = "";
		if(orule.getType() == 1){	//一元规则
			String bodyValue = "";
			String headValue = "";
			if(opBodyType1.equals("1")){	//对象属性
				bodyValue = "NSP:"+orule.getOpBodyValue1();
			}else if(opBodyType1.equals("2")){	//数据属性
				bodyValue = "'"+orule.getOpBodyValue1()+"'";
			}
			if(opHeadType.equals("1")){	//对象属性
				headValue = "NSP:"+orule.getOpHeadValue();
			}else if(opHeadType.equals("2")){	//数据属性
				headValue = "'"+orule.getOpHeadValue()+"'";
			}
			rule = "[(?a NSP:"+orule.getOpBodyName1()+" "+bodyValue+") -> (?a NSP:"+orule.getOpHeadName()+" "+headValue+")]";
		}else if(orule.getType() == 2){	//二元规则
			String bodyValue1 = "";
			String bodyValue2 = "";
			String headValue = "";
			if(opBodyType1.equals("1")){	//对象属性
				bodyValue1 = "NSP:"+orule.getOpBodyValue1();
			}else if(opBodyType1.equals("2")){	//数据属性
				bodyValue1 = "'"+orule.getOpBodyValue1()+"'";
			}
			if(opBodyType2.equals("1")){	//对象属性
				bodyValue2 = "NSP:"+orule.getOpBodyValue2();
			}else if(opBodyType2.equals("2")){	//数据属性
				bodyValue2 = "'"+orule.getOpBodyValue2()+"'";
			}
			if(opHeadType.equals("1")){	//对象属性
				headValue = "NSP:"+orule.getOpHeadValue();
			}else if(opHeadType.equals("2")){	//数据属性
				headValue = "'"+orule.getOpHeadValue()+"'";
			}
			rule = "[(?a NSP:"+orule.getOpBodyName1()+" "+bodyValue1+"),(?a NSP:"+orule.getOpBodyName2()+" "+bodyValue2+") -> (?a NSP:"+orule.getOpHeadName()+" "+headValue+")]";
		}
		//保存到数据库
		orule.setRule(rule);
		flag =  getORuleDao().addRule(orule);
		//保存到规则文件
		FileUtils.writeFile(modelFactory.getRuleFile(), rule+"\n");
		return flag;
	}

	/**
	 * 根据规则id查找规则
	 */
	public ORule getRuleById(int rid) {
		// TODO Auto-generated method stub
		return getORuleDao().getRuleById(rid);
	}

	/**
	 * 更新规则
	 */
	public boolean updateRule(ORule rule) {
		// TODO Auto-generated method stub
		return getORuleDao().updateRule(rule);
	}

	/**
	 * 返回规则列表
	 */
	public List<ORule> getRuleList(ORule orule) {
		// TODO Auto-generated method stub
		return getORuleDao().getRuleList(orule);
	}

	/**
	 * 删除规则
	 */
	public boolean delRule(int rid) {
		// TODO Auto-generated method stub
		boolean flag = true;
		ORule rule = getRuleById(Integer.valueOf(rid));
		String fileName = modelFactory.getRuleFile();
		String content = rule.getRule();
		//从规则文件中删除规则
		try {
			FileUtils.deleteRow(fileName, content);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		//从数据库中删除
		flag =  getORuleDao().delRule(rid);
		return flag;
	}

}
