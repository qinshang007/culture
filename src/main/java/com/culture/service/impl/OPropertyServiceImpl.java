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
	 * ���ݸ������֣���ȡ������ø������������
	 */
	public List<OProperty> getPropertys(String cname) {
		// TODO Auto-generated method stub
		List<OProperty> oplist = new ArrayList<OProperty>();
		try{
			OntModel model = omodelFactory.getModel();
			//��ȡ����
			OntClass ontc = model.getOntClass(OModelFactory.NSC+cname);
			//��ȡ���������
			ExtendedIterator<OntProperty> iter = ontc.listDeclaredProperties();
			while(iter.hasNext()){
				OntProperty ontp = iter.next();
				String ontp_name = ontp.getLocalName();
				OProperty op = new OProperty();
				List<OClass> rangeList = new ArrayList<OClass>();
				op.setPname(ontp_name);
				//����������Ƕ�������
				if(ontp.isObjectProperty()){
					op.setPtype(1);
					//��ȡ�����Ե�ֵ��
					ExtendedIterator<OntResource> rsiter = (ExtendedIterator<OntResource>) ontp.listRange();
					while(rsiter.hasNext()){
						OntResource rs = rsiter.next();
						if(rs.isClass()){
							OntClass rsc = rs.asClass();
							//����ø������Ӹ���Ļ������������������Ӹ���
							ExtendedIterator<OntClass> ontciter = rsc.listSubClasses(false);
							while(ontciter.hasNext()){
								OntClass suboc = ontciter.next();	//ȡ������Ӹ���
								String ocname = suboc.getLocalName();
								OClass oc = new OClass();
								oc.setCname(ocname);
								rangeList.add(oc);
							}
						}
					}
					op.setRangeList(rangeList);
				}else if(ontp.isDatatypeProperty()){//�������������������
					op.setPtype(2);
					OntResource rs = ontp.getRange();
					op.setRange(rs.getLocalName());
				}
				oplist.add(op);
			}

		}catch(Exception e){
			logger.error("���ظ�������Գ���"+e.getMessage());
		}
		return oplist;
	}

}
