 package culture;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Selector;
import com.hp.hpl.jena.rdf.model.SimpleSelector;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;

public class DeleteClass {
	
	public static void main(String[] args){
		String NSC = "http://culture.zju.edu.cn/class#";
		//创建model
		OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
		//owl文件路径
		String fileSrc = "ontology.owl";
		//读取文件
		 try {
			model.read(new FileInputStream(fileSrc),"");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//遍历所有的statements
//		StmtIterator iter = model.listStatements();
//		while(iter.hasNext()){
//			Statement stmt = iter.nextStatement();
//			Resource  subject   = stmt.getSubject();     // get the subject
//		    Property  predicate = stmt.getPredicate();   // get the predicate
//		    RDFNode   object    = stmt.getObject();      // get the object
//
//		    System.out.print(subject.toString());
//		    System.out.print(" " + predicate.toString() + " ");
//		    if (object instanceof Resource) {
//		       System.out.print(object.toString());
//		    } else {
//		        // object is a literal
//		        System.out.print(" \"" + object.toString() + "\"");
//		    }
//
//		    System.out.println(" .");
//		}
		//删除faultyMemebr
		OntClass yugou = model.getOntClass(NSC+"鱼钩");
//		if(yugou.hasSuperClass()){
//			OntClass parentClass = yugou.getSuperClass();
//			parentClass.removeSubClass(yugou);
//		}else if(yugou.hasSubClass()){
//			while(yugou.hasSubClass()){
//				OntClass childClass = yugou.getSubClass();
//				yugou.removeSubClass(childClass);
//			}
//		}
		yugou.remove();
		model.write(System.out);
	}

}
