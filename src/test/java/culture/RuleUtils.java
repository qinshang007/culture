package culture;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import com.hp.hpl.jena.rdf.model.InfModel;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.reasoner.Reasoner;
import com.hp.hpl.jena.reasoner.rulesys.GenericRuleReasoner;
import com.hp.hpl.jena.reasoner.rulesys.GenericRuleReasonerFactory;
import com.hp.hpl.jena.reasoner.rulesys.Rule;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.util.PrintUtil;
import com.hp.hpl.jena.vocabulary.ReasonerVocabulary;

public class RuleUtils {
	
	static String NSC = "http://jena.zju.edu.cn/class#";
	static String NSP = "http://jena.zju.edu.cn/property#";
	static String NSI = "http://jena.zju.edu.cn/instance#";
	
	static String rule1 = "[rule1: (?a NSP:pattern '莲花纹'),(?a NSP:scene '摆件') -> (?a NSP:meaning '高雅')]";
	static String rule2 = "[rule1: (?a NSP:pattern '莲花纹') -> (?a NSP:meaning '高雅')]";
	static String rule3 = "[rule1: (?a NSP:hasPattern NSC:lotus) -> (?a NSP:hasMeaning NSC:elegance)]";
	
	
	/**
	 * 根据字符串获取推理机
	 * @return
	 */
	public static Reasoner getReasoner(){
		//register namespace
		PrintUtil.registerPrefix("NSC", NSC);
		PrintUtil.registerPrefix("NSP", NSP);
		PrintUtil.registerPrefix("NSI", NSI);
		//create rules
		String rules = rule3;
		//create reasoner
		Reasoner reasoner = new GenericRuleReasoner(Rule.parseRules(rules));
		return reasoner;
	}

	/**
	 * 读取规则文件生成推理机
	 * @param filename
	 * @return
	 */
	public static Reasoner getReasoner(String filename){
		//register namespace
		PrintUtil.registerPrefix("NSC", NSC);
		PrintUtil.registerPrefix("NSP", NSP);
		PrintUtil.registerPrefix("NSI", NSI);
		//config reasoner
		Model m = ModelFactory.createDefaultModel();
		Resource configuration =  m.createResource();
		configuration.addProperty(ReasonerVocabulary.PROPruleMode, "hybrid");
		configuration.addProperty(ReasonerVocabulary.PROPruleSet,  filename);
		//create an instance of such a reasoner
		Reasoner reasoner = GenericRuleReasonerFactory.theInstance().create(configuration);
		return reasoner;
	}
	
	/**
	 * 推理
	 */
	public static void infer(){
		Reasoner reasoner = getReasoner("culture.rules");
		//load schema model
		Model schema = FileManager.get().loadModel("file:ontology.owl");
		reasoner.bindSchema(schema);
		//load data model
		Model data = FileManager.get().loadModel("file:data.owl");
		//create infmodel
		InfModel infmodel = ModelFactory.createInfModel(reasoner, data);
		
		//write inference file
//		File file = new File("inference.owl");
//		try{
//			OutputStream out = new FileOutputStream(file);
//			infmodel.write(out);
//		}catch(Exception e){
//			e.printStackTrace();
//		}
		//get instance
		Resource resource = infmodel.getResource(NSI+"qw1");
		printStatements(infmodel, resource, null, null);
	}
	
	public static void printStatements(Model m, Resource s, Property p, Resource o) {
	    for (StmtIterator i = m.listStatements(s,p,o); i.hasNext(); ) {
	        Statement stmt = i.nextStatement();
	        System.out.println(" - " + PrintUtil.print(stmt));
	    }
	}
	
	public static void main(String[]args){
		infer();
	}
}
