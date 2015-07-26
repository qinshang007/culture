package com.culture.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
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
import com.culture.util.JsonUtils;
import com.culture.util.StringUtils;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntProperty;


@Controller
@RequestMapping(value="/class")
public class ClassController extends BaseController{

	@Autowired
	private OClassService ocService;
	@Autowired
	private OModelFactory omodelFactory;

	private static final Logger logger = Logger.getLogger(ClassController.class);  
	
	/**
	 * �������
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/save.do")
	public void saveClass(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			OntModel model = omodelFactory.getModel();
			//��ȡ��������
			String cname = request.getParameter("cname");
			//���ɸ���id
			String cid = CodeGenerator.createUUID();
			//��ȡ����������
			String cfname = request.getParameter("cfname");
			//��ȡ������id
			String cfid = request.getParameter("cfid");
			
			OntClass children = model.createClass(OModelFactory.NSC+cname);
			children.setLabel(cname, "zh");
			
			//�����Ϊ���Ҳ�������
			if(!StringUtils.isEmpty(cfname)&&!cfname.equals("��")){
				OntClass parent = model.getOntClass(OModelFactory.NSC+cfid);//ȡ��������
				parent.addSubClass(children);//
			}
			
			//д��owl�ļ�
			File file = new File(omodelFactory.getOwlFile());
			try{
				OutputStream out = new FileOutputStream(file);
				model.write(out);
			}catch(Exception e){
				e.printStackTrace();
			}
			//���浽���ݿ�
			OClass c = new OClass();
			c.setCname(cname);
			c.setCid(cid);
			c.setCfid(cfid);
			ocService.addClass(c);
			
			outputJsonResponse(response, true, "uploadSuccess");
			                                                                                                                                                   
		}catch (RuntimeException e) {
			logger.error("����������" +  ",errMsg=" + e.getMessage());
			outputJsonResponse(response, false, e.getMessage());
		}
	}

	/**
	 * �������
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/update.do")
	public void updateClass(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			OntModel model = omodelFactory.getModel();
			//��ȡ����id
			String cid = request.getParameter("cid");
			//��ȡ��������
			String cname = request.getParameter("cname");
			//��ȡ����������
			String cfname = request.getParameter("cfname");
			//��ȡ������id
			String cfid = request.getParameter("cfid");
			
			//���ĸ���label
			OntClass children = model.getOntClass(OModelFactory.NSC+cid);
			children.setLabel(cname, "zh");
			
			//�����Ϊ���Ҳ�������
			if(!StringUtils.isEmpty(cfname)&&!cfname.equals("��")){
				//ȡ����ǰ�ĸ����Ȼ������Ӹ�����ɾ��
				OntClass old_parent = children.getSuperClass();
				if(old_parent!=null)
					old_parent.removeSubClass(children);
				//ȡ���µĸ�����
				OntClass parent = model.getOntClass(OModelFactory.NSC+cfid);
				parent.addSubClass(children);//
			}
			
			//д��owl�ļ�
			File file = new File(omodelFactory.getOwlFile());
			try{
				OutputStream out = new FileOutputStream(file);
				model.write(out);
			}catch(Exception e){
				e.printStackTrace();
			}
			//���µ����ݿ�
			OClass c = new OClass();
			c.setCname(cname);
			c.setCid(cid);
			c.setCfid(cfid);
			ocService.updateClass(c);
			
			outputJsonResponse(response, true, "updateSuccess");
			                                                                                                                                                   
		}catch (RuntimeException e) {
			logger.error("���¸������" +  ",errMsg=" + e.getMessage());
			outputJsonResponse(response, false, e.getMessage());
		}
	}

	
	/**
	 * ��Ӹ������
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/addClass.do")
	public ModelAndView addClass(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			//��ȡ������id
			String cfid = request.getParameter("cfid");
			//��ȡ����������
			String cfname = request.getParameter("cfname");
			//��ȡ�����б�
			OClass oclass = new OClass();
			oclass.setDel(0);
			List<OClass> oclist = ocService.getClassList(oclass);
			for(int i=0;i<oclist.size();i++){
				if(oclist.get(i).getCid().equals(cfid)){
					cfname = oclist.get(i).getCname();
					oclist.remove(i);
					break;
				}
			}
			Map map = new HashMap();
			map.put("cfid",cfid);
			map.put("cfname", cfname);
			map.put("oclist", oclist);
			ModelAndView view = new ModelAndView("class/addClass").addAllObjects(map);
			return view;
		}catch (RuntimeException e) {
			logger.error("��Ӹ���ҳ�����" +  ",errMsg=" + e.getMessage());
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
	@RequestMapping("/classList.do")
	public ModelAndView getClassList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			//��ȡ�����б�
			OClass oclass = new OClass();
			oclass.setDel(0);
			List<OClass> oclist = ocService.getClassList(oclass);
			Map map = new HashMap();
			map.put("oclist", oclist);
			ModelAndView view = new ModelAndView("class/classList").addAllObjects(map);
			return view;
		}catch (RuntimeException e) {
			logger.error("���ظ����б����" +  ",errMsg=" + e.getMessage());
			outputJsonResponse(response, false, e.getMessage());
			return null;
		}
	}

	/**
	 * ���ĸ������
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/viewClass.do")
	public ModelAndView viewClass(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			String cid = request.getParameter("cid");
			//��ȡ����
			OClass oc = ocService.getClassById(cid);
			//��ȡ�����б�
			OClass oclass = new OClass();
			oclass.setDel(0);
			List<OClass> oclist = ocService.getClassList(oclass);
			//��ȡ������
			String cfid = oc.getCfid();
			String cfname = "��";
			for(int i=0;i<oclist.size();i++){
				if(oclist.get(i).getCid().equals(cfid)){
					cfname = oclist.get(i).getCname();
					oclist.remove(i);
					break;
				}
			}
			Map map = new HashMap();
			map.put("oc", oc);
			map.put("oclist", oclist);
			map.put("cfid", cfid);
			map.put("cfname", cfname);
			ModelAndView view = new ModelAndView("class/viewClass").addAllObjects(map);
			return view;
		}catch (RuntimeException e) {
			logger.error("���ĸ���������" +  ",errMsg=" + e.getMessage());
			outputJsonResponse(response, false, e.getMessage());
			return null;
		}
	}
	
	
	@RequestMapping("/testlog.do")
	public void testlog(HttpServletRequest request, HttpServletResponse response) throws Exception{
		log.info("info�������");
		log.error("����log4j��־���ã�");
	}
	
}
