package culture;

import java.util.Iterator;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;

public class ClassUtils {
	
	static String NS = "http://jena.zju.edu.cn/class#";
	
	/**
	 * 创建概念
	 */
	public void cerateClass(){		

		//create model
		OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
		//create class
		OntClass  paper = model.createClass(NS+"Paper");
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
	
	public static void main(String[] args){
		ClassUtils cu  = new ClassUtils();
		cu.listClass();
	}
	
}
