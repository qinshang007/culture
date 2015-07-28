package com.culture.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.culture.dao.impl.OClassDaoImpl;
import com.culture.model.OClass;
import com.culture.model.OModelFactory;
import com.culture.model.OProperty;
import com.culture.service.OClassService;
import com.culture.service.OPropertyService;
import com.culture.util.CodeGenerator;
import com.culture.util.StringUtils;
import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import com.hp.hpl.jena.vocabulary.XSD;

@Controller
@RequestMapping(value="/property")
public class PropertyController extends BaseController{
	
	@Autowired
	private OClassService ocService;

	@Autowired
	private OPropertyService opService;
	
	@Autowired
	private OModelFactory omodelFactory;

	Resource[] resource = new Resource[]{XSD.xstring,XSD.xint,XSD.xfloat,XSD.date};
	
	private static final Logger logger = Logger.getLogger(PropertyController.class);  
	
	/**
	 * ��������
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/save.do")
	public void saveProperty(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			OntModel model = omodelFactory.getModel();
			//�������,1Ϊ�������ԣ�2Ϊ��������
			int ptype = Integer.valueOf(request.getParameter("ptype"));
			//��ȡ��������
			String pname = request.getParameter("pname");
			//��ȡ����������
			String pfname = request.getParameter("pfname");
			//��ȡ������id
			int pfid = Integer.parseInt(request.getParameter("pfid"));
			//��ȡ������
			String domain = request.getParameter("domain");
			//��ȡֵ��
			String range = request.getParameter("range");
			//�Ƿ�ͨ������
			int isgeneral = Integer.valueOf(request.getParameter("isgeneral"));
			
			OntProperty children = null;
			//Ϊ�������ֵ��
			if(ptype==1){
				 children = model.createObjectProperty(OModelFactory.NSP+pname);
				 
				//��ֵ��ֵת��Ϊ����
				String[] rangeArray = range.split(",");
				
				//Ϊ�������ֵ��
				for(int i=0;i<rangeArray.length;i++){
					OntClass oclass = model.getOntClass(OModelFactory.NSC+rangeArray[i]);
					children.addRange(oclass);
				}
				 
			}else if(ptype==2){
				 children = model.createDatatypeProperty(OModelFactory.NSP+pname);
				 
				 //���ֵ��
				 int index = Integer.valueOf(range);
				 //��ȡ��������
				 Resource s = resource[index];
				 children.addRange(s);
			}
			
			//�����Բ�Ϊ���Ҳ�������
			if(!StringUtils.isEmpty(pfname)&&!pfname.equals("��")){
				OntProperty parent = model.getOntProperty(OModelFactory.NSP+pfname);//ȡ��������
				parent.addSubProperty(children);
			}
			
			//��������ֵת��Ϊ����
			String[] domainArray = domain.split(",");
			
			//Ϊ������Ӷ�����
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
			}
			//���浽���ݿ�
			OProperty op = new OProperty();
			op.setPname(pname);
			op.setPtype(ptype);
			op.setPfid(pfid);
			op.setIsgeneral(isgeneral);
			opService.addProperty(op);
			
			outputJsonResponse(response, true, "uploadSuccess");
			                                                                                                                                                   
		}catch (RuntimeException e) {
			logger.error("�������Գ���" +  ",errMsg=" + e.getMessage());
			outputJsonResponse(response, false, e.getMessage());
		}
	}
	
	/**
	 * �޸�����
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/update.do")
	public void updateProperty(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			OntModel model = omodelFactory.getModel();
			//�������,1Ϊ�������ԣ�2Ϊ��������
			int ptype = Integer.valueOf(request.getParameter("ptype"));
			//��ȡ��������
			String pname = request.getParameter("pname");
			//��������id
			int pid = Integer.parseInt(request.getParameter("pid"));
			//��ȡ����������
			String pfname = request.getParameter("pfname");
			//��ȡ������id
			int pfid = Integer.parseInt(request.getParameter("pfid"));
			//��ȡ������
			String domain = request.getParameter("domain");
			//��ȡֵ��
			String range = request.getParameter("range");
			
			OntProperty children = null;
			
			if(ptype==1){
				
				 children = model.getObjectProperty(OModelFactory.NSP+pid);
				 
				//��ȡ����֮ǰ��ֵ�򣬲�����Щֵ�������
				ExtendedIterator<OntResource> rangeiter = (ExtendedIterator<OntResource>)children.listRange();
				List<OntResource> rangelist = new ArrayList<OntResource>();
				while(rangeiter.hasNext()){
					OntResource rs = rangeiter.next();
					rangelist.add(rs);
				}
				for(int i=0;i<rangelist.size();i++){
					children.removeRange(rangelist.get(i));
				}
				
				//��ֵ��ֵת��Ϊ����
				String[] rangeArray = range.split(",");
				
				//Ϊ�������ֵ��
				for(int i=0;i<rangeArray.length;i++){
					OntClass oclass = model.getOntClass(OModelFactory.NSC+rangeArray[i]);
					children.addRange(oclass);
				}

				
			}else if(ptype==2){
				
				 children = model.getDatatypeProperty(OModelFactory.NSP+pid);
				 
				//�������֮ǰ��ֵ�򣬲����������
				OntResource or = children.getRange();
				children.removeRange(or);
				
				 //���ֵ��
				 int index = Integer.valueOf(range);
				 //��ȡ��������
				 Resource s = resource[index];
				 children.addRange(s);
			}
			
			//��ȡ����֮ǰ�Ķ����򣬲�����Щ�����������
			ExtendedIterator<OntResource> domainiter = (ExtendedIterator<OntResource>)children.listDomain();
			List<OntResource> domainlist = new ArrayList<OntResource>();
			while(domainiter.hasNext()){
				OntResource rs = domainiter.next();
				domainlist.add(rs);
			}
			for(int i=0;i<domainlist.size();i++)
				children.removeDomain(domainlist.get(i));
			
			//��������ֵת��Ϊ����
			String[] domainArray = domain.split(",");
			
			//Ϊ������Ӷ�����
			for(int i=0;i<domainArray.length;i++){
				OntClass oclass = model.getOntClass(OModelFactory.NSC+domainArray[i]);
				children.addDomain(oclass);
			}
			
			//�����Ϊ���Ҳ�������
			if(!StringUtils.isEmpty(pfname)&&!pfname.equals("��")){
				//ȡ����ǰ�ĸ����Ȼ������Ӹ�����ɾ��
				OntProperty old_parent = children.getSuperProperty();
				if(old_parent!=null)
					old_parent.removeSubProperty(children);
				//ȡ���µĸ�����
				OntProperty parent = model.getOntProperty(OModelFactory.NSP+pfname);
				parent.addSubProperty(children);//
			}

			//write XML FILE
			File file = new File(omodelFactory.getOwlFile());
			try{
				OutputStream out = new FileOutputStream(file);
				model.write(out);
			}catch(Exception e){
				e.printStackTrace();
			}
			//���浽���ݿ�
			OProperty op = new OProperty();
			op.setPid(pid);
			op.setPname(pname);
			op.setPtype(ptype);
			op.setPfid(pfid);
			opService.updateProperty(op);
			
			outputJsonResponse(response, true, "updateSuccess");

		}catch (Exception e) {
			logger.error("�޸����Գ���" +  ",errMsg=" + e.getMessage());
			outputJsonResponse(response, false, e.getMessage());
		}
	}

	/**
	 * ��Ӷ������Խ���
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/addObjectProperty.do")
	public ModelAndView addObjectProperty(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			//��ȡ������id
			String pfid = request.getParameter("pfid");
			//��ȡ����������
			String pfname = request.getParameter("pfname");
			//��ȡ�����б�
			OClass oclass = new OClass();
			oclass.setDel(0);
			List<OClass> oclist = ocService.getClassList(oclass);
			//��ȡ�����б�
			List<OProperty> oplist = opService.getPropertyList();
			Map map = new HashMap();
			map.put("pfid",pfid);
			map.put("pfname", pfname);
			map.put("oclist", oclist);
			map.put("oplist", oplist);
			ModelAndView view = new ModelAndView("property/addObjectProperty").addAllObjects(map);
			return view;
		}catch (RuntimeException e) {
			logger.error("��Ӷ������Խ������" +  ",errMsg=" + e.getMessage());
			outputJsonResponse(response, false, e.getMessage());
			return null;
		}
	}

	/**
	 * ����������Խ���
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/addDataProperty.do")
	public ModelAndView addDataProperty(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			//��ȡ������id
			String pfid = request.getParameter("pfid");
			//��ȡ����������
			String pfname = request.getParameter("pfname");
			//��ȡ�����б�
			OClass oclass = new OClass();
			oclass.setDel(0);
			List<OClass> oclist = ocService.getClassList(oclass);
			//��ȡ�����б�
			List<OProperty> oplist = opService.getPropertyList();
			Map map = new HashMap();
			map.put("pfid",pfid);
			map.put("pfname", pfname);
			map.put("oclist", oclist);
			map.put("oplist", oplist);
			ModelAndView view = new ModelAndView("property/addDataProperty").addAllObjects(map);
			return view;
		}catch (RuntimeException e) {
			logger.error("����������Խ������" +  ",errMsg=" + e.getMessage());
			outputJsonResponse(response, false, e.getMessage());
			return null;
		}
	}

	/**
	 * �����б����
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/propertyList.do")
	public ModelAndView getPropertyList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			//��ȡ�����б�
			List<OProperty> oplist = opService.getPropertyList();
			Map map = new HashMap();
			map.put("oplist", oplist);
			ModelAndView view = new ModelAndView("property/propertyList").addAllObjects(map);
			return view;
		}catch (RuntimeException e) {
			logger.error("���ظ����б����" +  ",errMsg=" + e.getMessage());
			outputJsonResponse(response, false, e.getMessage());
			return null;
		}
	}

	/**
	 * �����������
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/viewProperty.do")
	public ModelAndView viewProperty(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			OntModel model = omodelFactory.getModel();
			String pid = request.getParameter("pid");
			//��ȡ����
			OProperty op = opService.getPropertyById(pid);
			//��ȡ�����б�
			OClass oclass = new OClass();
			oclass.setDel(0);
			List<OClass> domainList = ocService.getClassList(oclass);
			List<OClass> rangeList = ocService.getClassList(oclass);
			//��ȡ�����б�
			List<OProperty> oplist = opService.getPropertyList();
			//��ȡ������id
			int pfid = op.getPfid();
			//��ȡ����������
			String pfname = "��";
			for(int i=0;i<oplist.size();i++){
				if(oplist.get(i).getPid() == pfid){
					pfname = oplist.get(i).getPname();
					oplist.remove(i);
					break;
				}
			}
			//ȡ�����Ե�ӳ��
			Map<Integer,Integer> dmap = new HashMap<Integer,Integer>();
			Map<Integer,Integer> rmap = new HashMap<Integer,Integer>();
			for(int i=0; i<domainList.size();i++){
				dmap.put(domainList.get(i).getCid(), i);
				rmap.put(rangeList.get(i).getCid(), i);
			}

			if(op.getPtype()==1){	//����Ƕ�������
				op.setPtype(1);
				ObjectProperty ontop = model.getObjectProperty(OModelFactory.NSP+op.getPid());
				//��ȡ���ԵĶ�����
				ExtendedIterator<OntResource> domainiter = (ExtendedIterator<OntResource>)ontop.listDomain();
				while(domainiter.hasNext()){
					OntResource rs = domainiter.next();
					String uri = rs.toString();
					//������������Ŀ������Ϊ���cid�������ֿ�ͷ�Ļ����ᱻĬ�Ͻض�һλ
					String cid = uri.substring(uri.indexOf('#')+1);
					int index = dmap.get(cid);
					domainList.get(index).setSelected(1);
				}
				op.setDomainList(domainList);
				//��ȡ���Ե�ֵ��
				ExtendedIterator<OntResource> rangeiter = (ExtendedIterator<OntResource>)ontop.listRange();
				while(rangeiter.hasNext()){
					OntResource rs = rangeiter.next();
					String uri = rs.toString();
					String cid = uri.substring(uri.indexOf('#')+1);
					int index = rmap.get(cid);
					rangeList.get(index).setSelected(1);
				}
				op.setRangeList(rangeList);
			}else if(op.getPtype()==2){ //�������������
				op.setPtype(2);
				DatatypeProperty dp = model.getDatatypeProperty(OModelFactory.NSP+op.getPid());
				//��ȡ���ԵĶ�����
				ExtendedIterator<OntResource> domainiter = (ExtendedIterator<OntResource>)dp.listDomain();
				while(domainiter.hasNext()){
					OntResource rs = domainiter.next();
					String namespace = rs.toString();
					String cid = namespace.substring(namespace.indexOf('#')+1);
					int index = dmap.get(cid);
					domainList.get(index).setSelected(1);
				}
				op.setDomainList(domainList);
				//������Ե�ֵ��
				OntResource or = dp.getRange();
				for(int i=0;i<resource.length;i++){
					if(or.equals(resource[i]))
						op.setRange(""+i);
				}
			}
			Map map = new HashMap();
			map.put("op", op);
			map.put("oplist", oplist);
			map.put("pfid", pfid);
			map.put("pfname", pfname);
			return new ModelAndView("property/viewProperty").addAllObjects(map);
		}catch (RuntimeException e) {
			logger.error("�鿴�����������" +  ",errMsg=" + e.getMessage());
			outputJsonResponse(response, false, e.getMessage());
			return null;
		}
	}
	
	
}
