package test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.culture.dao.CulturalDao;
import com.culture.dao.InstanceDao;
import com.culture.model.CulturalBean;
import com.culture.model.Instance;

public class InstanceDaoTest {

	public void getClassList(){
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/applicationContext.xml");
		CulturalDao clDao = (CulturalDao)context.getBean("clDao");
		Map map = new HashMap();
		map.put("level", 1);
		List<CulturalBean> clList = clDao.getCulturalList(map);
		System.out.println(clList.size());
		
	}
	
	public static void main(String[]args){
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/applicationContext.xml");
		CulturalDao clDao = (CulturalDao)context.getBean("clDao");
		InstanceDao instDao = (InstanceDao)context.getBean("instanceDao");
		Map map = new HashMap();
		map.put("level", 1);
		List<CulturalBean> clList = clDao.getCulturalList(map);
		System.out.println(clList.size());
		for(int i=0;i<clList.size();i++){
			CulturalBean cb = clList.get(i);
			System.out.println(i+" "+cb.getTitle());
			Instance temp = new Instance();
			temp.setIdentifier(cb.getIdentifier());
			temp.setTitle(cb.getTitle());
			temp.setUsed_title(cb.getUsed_title());
			temp.setType(cb.getType());
			temp.setClassification(cb.getClassification());
			temp.setCrtime(cb.getCrtime());
			instDao.addInstance(temp);
		};
	}

	
}
