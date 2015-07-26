package com.culture.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.culture.model.OClass;
import com.culture.model.OModelFactory;
import com.culture.service.OClassService;
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
		return getOClassDao().addClass(oclass);
	}

	public boolean delClass(String id) {
		// TODO Auto-generated method stub
		return getOClassDao().delClass(id);
	}

	public boolean updateClass(OClass oclass) {
		// TODO Auto-generated method stub
		return getOClassDao().updateClass(oclass);
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
	public List<OClass> getSubClasses(String cid, boolean direct) {
		// TODO Auto-generated method stub
		List<OClass> oclist = new ArrayList<OClass>();
		try{
			OntModel model = omodelFactory.getModel();
			//查询本体文件，获得文物概念
			OntClass ontc = model.getOntClass(OModelFactory.NSC+cid);
			//获取文物概念的所有子概念
			ExtendedIterator<OntClass> iter = ontc.listSubClasses(direct);
			while(iter.hasNext()){
				OntClass children = iter.next();
				String uri = children.toString();
				String id = uri.substring(uri.indexOf('#')+1);
				OClass temp = new OClass();
				temp.setCname(children.getLabel("zh"));
				temp.setCid(id);
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

}
