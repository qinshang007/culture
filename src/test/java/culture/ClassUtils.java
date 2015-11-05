package culture;

import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import com.hp.hpl.jena.vocabulary.XSD;

public class ClassUtils {
	
	static String NS = "http://jena.zju.edu.cn/class#";
	static String NSP = "http://jena.zju.edu.cn/property#";
	static String NSI = "http://jena.zju.edu.cn/instance#";
	
	/**
	 * 创建概念
	 */
	public void cerateClass(){		

		//create model
		OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
		//create class
		OntClass  paper = model.createClass(NS+"文章");
		System.out.println(paper.getLocalName());
		OntClass bestPaper = model.createClass(NS+"BestPaper");
		paper.addSubClass(bestPaper);

	}
	
	/**
	 * 列出所有的子概念
	 */
	public void listClass(){
		//create model
		OntModel model = ModelFactory.createOntologyModel();
		//create grandpa class
		OntClass  gp = model.createClass(NS+"gp");
		//create father class
		OntClass  f1 = model.createClass(NS+"f1");
		OntClass  f2 = model.createClass(NS+"f2");
		gp.addSubClass(f1);
		gp.addSubClass(f2);
		//create grandson class
		OntClass  gs1 = model.createClass(NS+"gs1");
		OntClass  gs2 = model.createClass(NS+"gs2");
		OntClass  gs3 = model.createClass(NS+"gs3");
		OntClass  gs4 = model.createClass(NS+"gs4");
		f1.addSubClass(gs1);
		f1.addSubClass(gs2);
		f2.addSubClass(gs3);
		f2.addSubClass(gs4);
		//list subclass
		ExtendedIterator<OntClass> iter = gp.listSubClasses(true);
		while(iter.hasNext()){
			OntClass oc = iter.next();
			System.out.println(oc.toString());
		}
		
	}
	
	/**
	 * 删除概念
	 */
	public void deleteClass(){
		//create model
		OntModel model = ModelFactory.createOntologyModel();
		//create grandpa class
		OntClass  gp = model.createClass(NS+"gp");
		//create father class
		OntClass  f1 = model.createClass(NS+"f1");
		OntClass  f2 = model.createClass(NS+"f2");
		gp.addSubClass(f1);
		gp.addSubClass(f2);
		//create grandson class
		OntClass  gs1 = model.createClass(NS+"gs1");
		OntClass  gs2 = model.createClass(NS+"gs2");
		OntClass  gs3 = model.createClass(NS+"gs3");
		OntClass  gs4 = model.createClass(NS+"gs4");
		f1.addSubClass(gs1);
		f1.addSubClass(gs2);
		f2.addSubClass(gs3);
		f2.addSubClass(gs4);
		//create property
		ObjectProperty op1 = model.createObjectProperty(NSP+"op1");
		op1.addDomain(f1);
		op1.addRange(f2);
		DatatypeProperty op2 = model.createDatatypeProperty(NSP+"op2");
		op2.addDomain(f1);
		op2.setRange(XSD.xstring);
		//create instance
		Individual in1 = model.createIndividual(NSI+"in1",gp);
		in1.addProperty(op2, "哈哈");
		in1.addProperty(op1, gs1);
		//delete class
		gs1.remove();
		model.write(System.out);
	}
	
	public static void main(String[] args){
		ClassUtils cu  = new ClassUtils();
		cu.deleteClass();
	}
	
}
