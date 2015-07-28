package com.culture.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.culture.model.OClass;
import com.culture.model.OModelFactory;
import com.culture.model.OProperty;
import com.culture.service.OPropertyService;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;

@Service
public class OPropertyServiceImpl extends BaseService implements OPropertyService{

	@Autowired
	private OModelFactory omodelFactory;

	private static final Logger logger = Logger.getLogger(OPropertyServiceImpl.class);
	
	public List<OProperty> getPropertyList() {
		// TODO Auto-generated method stub
		return getOPropertyDao().getPropertyList();
	}

	public OProperty getPropertyById(String id) {
		// TODO Auto-generated method stub
		return getOPropertyDao().getPropertyById(id);
	}

	public boolean addProperty(OProperty OProperty) {
		// TODO Auto-generated method stub
		return getOPropertyDao().addProperty(OProperty);
	}

	public boolean delProperty(String id) {
		// TODO Auto-generated method stub
		return getOPropertyDao().delProperty(id);
	}

	public boolean updateProperty(OProperty oproperty) {
		// TODO Auto-generated method stub
		return getOPropertyDao().updateProperty(oproperty);
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
					op.setRange(rs.getLocalName());
				}
				oplist.add(op);
			}

		}catch(Exception e){
			logger.error("返回概念的属性出错："+e.getMessage());
		}
		return oplist;
	}

}
