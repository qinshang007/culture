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
	 * ���ݸ���id����ȡ������ø������������
	 */
	public List<OProperty> getPropertys(String cid) {
		// TODO Auto-generated method stub
		List<OProperty> oplist = new ArrayList<OProperty>();
		try{
			OntModel model = omodelFactory.getModel();
			//��ȡ����
			OntClass ontc = model.getOntClass(OModelFactory.NSC+cid);
			//��ȡ���������
			ExtendedIterator<OntProperty> iter = ontc.listDeclaredProperties();
			while(iter.hasNext()){
				OntProperty ontp = iter.next();
				String ontp_uri = ontp.toString();
				String ontp_id = ontp_uri.substring(ontp_uri.indexOf('#')+1);
				OProperty op = new OProperty();
				List<OClass> rangeList = new ArrayList<OClass>();
				op.setPname(ontp.getLabel("zh"));
				op.setPid(ontp_id);
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
								String uri = suboc.toString();
								String id = uri.substring(uri.indexOf('#')+1);
								OClass oc = new OClass();
								oc.setCid(id);
								oc.setCname(suboc.getLabel("zh"));
								rangeList.add(oc);
							}
						}
					}
					op.setRangeList(rangeList);
				}else if(ontp.isDatatypeProperty()){//�������������������
					op.setPtype(2);
					OntResource rs = ontp.getRange();
					OClass oc = new OClass();
					String uri = rs.toString();
					String id = uri.substring(uri.indexOf('#')+1);
					oc.setCid(id);
				}
				oplist.add(op);
			}

		}catch(Exception e){
			logger.error("���ظ�������Գ���"+e.getMessage());
		}
		return oplist;
	}

}
