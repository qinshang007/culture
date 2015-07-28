package test;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.culture.dao.OClassDao;
import com.culture.model.OClass;
import com.culture.model.OClass;
import com.culture.util.DateUtils;

public class ClassDaoTest {
	
	public void getClassList(){
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/applicationContext.xml");
		OClassDao ocDao = (OClassDao)context.getBean("ocDao");
		OClass oc = new OClass();
		oc.setCid(1);
		oc.setDel(0);
		List<OClass> ocList = ocDao.getClassList(oc);
		for(int i=0;i<ocList.size();i++){
			System.out.println(ocList.get(i).getCname());
		}
		
	}
	
	public static void main(String args[]){
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/applicationContext.xml");
		OClassDao ocDao = (OClassDao)context.getBean("ocDao");
		OClass oc = new OClass();
		oc.setCfid(0);
		oc.setCname("织物");
		oc.setTime(Timestamp.valueOf(DateUtils.getCurrDateTimeStr()));
		oc.setDel(0);
		if(ocDao.addClass(oc))
			System.out.println("添加成功！");
		else
			System.out.println("添加失败！");
	}
	
}
