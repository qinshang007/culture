package test;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.culture.dao.OClassDao;
import com.culture.model.OClass;
import com.culture.model.OClass;
import com.culture.util.DateUtils;

public class CulturalDaoTest {
	
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
	
	public static void main(String args[]) throws InterruptedException{
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/applicationContext.xml");
		OClassDao ocDao = (OClassDao)context.getBean("ocDao");
		int cid = 685;
		for(int i=705;i<=2481;i++){
			cid = i;
			OClass oc = ocDao.getClassById(String.valueOf(cid));
			if(oc != null){
				int cfid = oc.getCfid();
				String path = "";
				if(cfid == 0)
					path = "0";
				else{
					OClass pa = ocDao.getClassById(String.valueOf(cfid));
					path = pa.getPath();
				}
				path = path+","+cid;
				oc.setPath(path);
				if(ocDao.updatePath(oc))
					System.out.println("更新成功:-------->"+cid);
				else
					System.out.println("更新失败:-------->"+cid);
				Thread.sleep(1000);
			}
		}
	}
	
}
