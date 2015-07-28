package culture;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.vocabulary.XSD;

public class CreateInstance {
	
	static String NSC = "http://jena.zju.edu.cn/class#";
	static String NSP = "http://jena.zju.edu.cn/property#";
	static String NSI = "http://jena.zju.edu.cn/instance#";
	
	public static void main(String[] args){
		//create model
		OntModel model = ModelFactory.createOntologyModel();
		//create human class
		OntClass professor = model.createClass(NSC+"Professor");
		OntClass facultyMember = model .createClass(NSC+"FacultyMember");
		OntClass person = model.createClass(NSC+"Person");
		facultyMember.addSubClass(professor);
		person.addSubClass(facultyMember);
		//create organization class
		OntClass organisation = model.createClass(NSC+"Organisation");
		OntClass university = model.createClass(NSC+"University");
		OntClass college = model.createClass(NSC+"College");
		organisation.addSubClass(university);
		university.addSubClass(college);
		//create object property
		ObjectProperty hasAffiliation = model.createObjectProperty(NSP+"hasAffiliation");
		hasAffiliation.addDomain(person);
		hasAffiliation.addRange(organisation);  
		//create data property
		DatatypeProperty name = model.createDatatypeProperty(NSP+"name");
		name.hasDomain(person);
		name.hasRange(XSD.xstring);
		//create instances1
		Individual lyp = professor.createIndividual(NSI+"LYP");
		Individual computer_and_science_college = college.createIndividual(NSI+"ComputerAndScienceCollege"); 
		lyp.addProperty(hasAffiliation, computer_and_science_college);
		lyp.addProperty(name, "林炀平");
		//create instances2
		Individual lpf = professor.createIndividual(NSI+"LPF");
		lpf.addProperty(hasAffiliation, computer_and_science_college);
		lpf.addProperty(name, "李鹏飞");		
		//set namespace prefix
		model.setNsPrefix("NSC", NSC);
		model.setNsPrefix("NSP", NSP);
		//write XML
		model.write(System.out);
		//delete lfp instance
		lpf.remove();
		model.write(System.out);
		//write XML file
		File file = new File("test.owl");
		try{
			OutputStream out = new FileOutputStream(file);
			model.write(out);
		}catch(Exception e){
			e.printStackTrace();
		}

	}
}
