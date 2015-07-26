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
	 * ���ݸ���id����ȡ������Ӹ���
	 * ���directΪtrue���򷵻ظ����ֱϵ�Ӹ���
	 * ���directΪfalse,�򷵻ظ���������Ӹ���
	 */
	public List<OClass> getSubClasses(String cid, boolean direct) {
		// TODO Auto-generated method stub
		List<OClass> oclist = new ArrayList<OClass>();
		try{
			OntModel model = omodelFactory.getModel();
			//��ѯ�����ļ�������������
			OntClass ontc = model.getOntClass(OModelFactory.NSC+cid);
			//��ȡ�������������Ӹ���
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
			logger.error("��ȡ�����Ӹ������"+e.getMessage());
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
			logger.error("���ݸ���id��ȡ�������ֳ���"+e.getMessage());
		}
		return res;
	}

}
