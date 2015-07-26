package culture;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Iterator;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;

public class ReadXml {
	
	public static void main(String[] args){
		//����model
		OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
		//owl�ļ�·��
		String fileSrc = "test.owl";
		//��ȡ�ļ�
		 try {
			model.read(new FileInputStream(fileSrc),"");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//����model
		Iterator<OntClass> i = model.listClasses();
		while(i.hasNext()){
			OntClass c = i.next();
			System.out.println(c.getURI());
		}
		
	}
	
}
