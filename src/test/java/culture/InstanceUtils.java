package culture;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.vocabulary.XSD;

public class InstanceUtils {
	
	static String NSC = "http://jena.zju.edu.cn/class#";
	static String NSP = "http://jena.zju.edu.cn/property#";
	static String NSI = "http://jena.zju.edu.cn/instance#";

	/**
	 * 创建本体文件
	 */
	public static void createSchema(){
		//create model
		OntModel model = ModelFactory.createOntologyModel();
		//create culture class
		OntClass qiwu = model.createClass(NSC+"qiwu");	//器物
		//create pattern class
		OntClass pattern = model.createClass(NSC+"pattern");	//纹饰
		OntClass lotus = model.createClass(NSC+"lotus");		//莲花纹
		OntClass peony = model.createClass(NSC+"peony");		//牡丹纹
		pattern.addSubClass(lotus);
		pattern.addSubClass(peony);
		//create meaning class
		OntClass meaning = model.createClass(NSC+"meaning");	//象征意义
		OntClass elegance = model.createClass(NSC+"elegance");	//高雅
		OntClass rich = model.createClass(NSC+"rich");			//富贵
		meaning.addSubClass(elegance);
		meaning.addSubClass(rich);
		//create objectproperty
		ObjectProperty hasPattern = model.createObjectProperty(NSP+"hasPattern");	//拥有纹饰
		hasPattern.setDomain(qiwu);
		hasPattern.setRange(pattern);
		ObjectProperty hasMeaning = model.createObjectProperty(NSP+"hasMeaning");	//意义
		hasMeaning.setDomain(qiwu);
		hasMeaning.setRange(meaning);
		//create dataproperty
		DatatypeProperty title = model.createDatatypeProperty(NSP+"title"); 	//名字
		title.setDomain(qiwu);
		title.setRange(XSD.xstring);
		DatatypeProperty scene = model.createDatatypeProperty(NSP+"scene");	//使用情境
		scene.setDomain(qiwu);
		scene.setRange(XSD.xstring);
		DatatypeProperty shape = model.createDatatypeProperty(NSP+"shape");	//器形
		shape.setDomain(qiwu);
		shape.setRange(XSD.xstring);
		//set namespace prefix
		model.setNsPrefix("NSC", NSC);
		model.setNsPrefix("NSP", NSP);
		//write owl file
		File file = new File("ontology.owl");
		try{
			OutputStream out = new FileOutputStream(file);
			model.write(out);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 创建实例文件
	 */
	public static void createData(){
		//create instance model
		OntModel instanceModel = ModelFactory.createOntologyModel();
		instanceModel.setNsPrefix("NSC", NSC);
		instanceModel.setNsPrefix("NSP", NSP);
		instanceModel.setNsPrefix("NSI", NSI);
		//get owl model
		OntModel model = ModelFactory.createOntologyModel();
		model.setNsPrefix("NSC", NSC);
		model.setNsPrefix("NSP", NSP);
		File owl = new File("ontology.owl");
		if(owl.exists())
			 try {
				model.read(new FileInputStream("ontology.owl"),"");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//get qiwu class
		OntClass qiwu = model.getOntClass(NSC+"qiwu");	
		OntClass pattern = model.getOntClass(NSC+"pattern");
		OntClass lotus = model.getOntClass(NSC+"lotus");
		OntClass peony = model.getOntClass(NSC+"peony");
		OntClass meaning = model.getOntClass(NSC+"meaning");
		OntClass elegance = model.getOntClass(NSC+"elegance");
		OntClass rich = model.getOntClass(NSC+"rich");
		//get propertys
		OntProperty title = model.getDatatypeProperty(NSP+"title");
		OntProperty hasPattern = model.getObjectProperty(NSP+"hasPattern");
		OntProperty scene = model.getDatatypeProperty(NSP+"scene");
		OntProperty shape = model.getDatatypeProperty(NSP+"shape");
		OntProperty hasMeaning = model.getObjectProperty(NSP+"hasMeaning");
		//create instances 1
		Individual qw1 = instanceModel.createIndividual(NSI+"qw1", qiwu);
		qw1.addProperty(title, "五彩落花流水图碗");
		qw1.addProperty(hasPattern, lotus);
		qw1.addProperty(hasPattern, peony);
		qw1.addProperty(scene, "摆件");
		//crete instance 2
		Individual qw2 = instanceModel.createIndividual(NSI+"qw2", qiwu);
		qw2.addProperty(title, "五彩竹雀纹壶");
		qw2.addProperty(hasPattern, peony);
		qw2.addProperty(shape, "直口圆唇，丰肩圆腹，管口为流，椭圆形曲柄，圈足。盖顶为宝珠形钮。");
		//write data file
		File file = new File("data.owl");
		try{
			OutputStream out = new FileOutputStream(file);
			instanceModel.write(out);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[]args){
		createData();
	}
	
}
