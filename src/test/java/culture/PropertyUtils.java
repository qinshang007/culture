package culture;

import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import com.hp.hpl.jena.vocabulary.XSD;

public class PropertyUtils {
	
	static String NSC = "http://jena.zju.edu.cn/class#";
	static String NSP = "http://jena.zju.edu.cn/property#";
	static String NSI = "http://jena.zju.edu.cn/instance#";
	
	/**
	 * 创建属性
	 */
	public  void createProperty(){
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
		//create ObjectProperty
		ObjectProperty hasAffiliation = model.createObjectProperty(NSP+"hasAffiliation");
		hasAffiliation.addDomain(person);
		hasAffiliation.addRange(organisation);
		//create DataProperty
		DatatypeProperty collegeName = model.createDatatypeProperty(NSP+"collegeName");
		collegeName.addDomain(person);
		collegeName.addRange(XSD.xstring);
		//set namespace prefix
		model.setNsPrefix("NSC", NSC);
		model.setNsPrefix("NSP", NSP);
		//write XML
		model.write(System.out);
	}
	
	/**
	 * 遍历某一个类的所有属性
	 */
	public void traverseProperty(){
		//create model
		OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
		//create class
		OntClass oc = model.createClass(NSC+"oc");
		OntClass od = model.createClass(NSC+"od");
		//create subclass
		OntClass suboc = model.createClass(NSC+"suboc");
		oc.addSubClass(suboc);
		//create property
		DatatypeProperty dp1 = model.createDatatypeProperty(NSP+"dp1");
		dp1.addDomain(oc);
		dp1.addRange(XSD.xstring);
		DatatypeProperty dp2 = model.createDatatypeProperty(NSP+"dp2");
		dp2.addDomain(oc);
		dp2.addRange(XSD.xint);
		DatatypeProperty dp3 = model.createDatatypeProperty(NSP+"dp3");
		dp3.addDomain(suboc);
		dp3.addRange(XSD.xint);
		ObjectProperty op1 = model.createObjectProperty(NSP+"op1");
		op1.addDomain(oc);
		op1.addRange(od);
		//traverse oc property
		ExtendedIterator<OntProperty> iter = oc.listDeclaredProperties(true);
		while(iter.hasNext()){
			OntProperty op = iter.next();
			System.out.println("oc属性: "+op.toString());
		}
		//traverse suboc property
		ExtendedIterator<OntProperty> subiter = suboc.listDeclaredProperties();
		while(subiter.hasNext()){
			OntProperty op = subiter.next();
			System.out.println("suboc属性: "+op.toString());
		}

	}
	
	/**
	 * 获取一个属性的值域
	 */
	public void getRange(){
		//create model
		OntModel model = ModelFactory.createOntologyModel();
		//create class
		OntClass oc = model.createClass(NSC+"oc");
		oc.setLabel("概念oc", "zh");
		OntClass od = model.createClass(NSC+"od");
		od.setLabel("概念od", "zh");
		OntClass oe = model.createClass(NSC+"oe");
		oe.setLabel("概念oe", "zh");
		//create subclass
		OntClass suboc = model.createClass(NSC+"suboc");
		suboc.setLabel("概念suboc", "zh");
		oc.addSubClass(suboc);
		//create property
		DatatypeProperty dp1 = model.createDatatypeProperty(NSP+"dp1");
		dp1.addDomain(oc);
		dp1.addRange(XSD.xstring);
		DatatypeProperty dp2 = model.createDatatypeProperty(NSP+"dp2");
		dp2.addDomain(oc);
		dp2.addRange(XSD.xint);
		DatatypeProperty dp3 = model.createDatatypeProperty(NSP+"dp3");
		dp3.addDomain(suboc);
		dp3.addRange(XSD.xint);
		ObjectProperty op1 = model.createObjectProperty(NSP+"op1");
		op1.addDomain(oc);
		op1.addRange(od);
		//get range
		ExtendedIterator<OntResource> iter = (ExtendedIterator<OntResource>) op1.listRange();
		while(iter.hasNext()){
			OntResource s = iter.next();
			System.out.println(s.toString()+"  "+s.getLabel("zh"));
		}
	}
	
	/**
	 * 删除属性
	 */
	public  void deleteProperty(){
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
		//create ObjectProperty
		ObjectProperty hasAffiliation = model.createObjectProperty(NSP+"hasAffiliation");
		hasAffiliation.addDomain(person);
		hasAffiliation.addRange(organisation);
		//create DataProperty
		DatatypeProperty collegeName = model.createDatatypeProperty(NSP+"collegeName");
		collegeName.addDomain(person);
		collegeName.addRange(XSD.xstring);
		//set namespace prefix
		model.setNsPrefix("NSC", NSC);
		model.setNsPrefix("NSP", NSP);
		//delete collegeName property
		collegeName.remove();
		//write XML
		model.write(System.out);
	}

	
	public static void main(String[] args){
		PropertyUtils cp = new PropertyUtils();
		cp.deleteProperty();
	}
}
