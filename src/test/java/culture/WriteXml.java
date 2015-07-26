package culture;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.ontology.ProfileRegistry;
import com.hp.hpl.jena.rdf.model.ModelFactory;

public class WriteXml {
	
	static String NS = "http://jena.zju.edu.cn/human#";
	
	public static void main(String[] args){
		String owlSrc = "E:/apache-tomcat-7.0.55/webapps/owl/culture.owl";
		//create model
		OntModel model = ModelFactory.createOntologyModel(ProfileRegistry.OWL_DL_LANG);
		//create class
		OntClass professor = model.createClass(NS+"Professor");
		OntClass facultyMember = model .createClass(NS+"FacultyMember");
		OntClass person = model.createClass(NS+"Person");
		facultyMember.addSubClass(professor);
		person.addSubClass(facultyMember);
		//write XML
		model.write(System.out);
		//write XML FILE
		File file = new File("test.owl");
		try{
			OutputStream out = new FileOutputStream(file);
			model.write(out);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
