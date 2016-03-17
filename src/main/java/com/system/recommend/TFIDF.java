package com.system.recommend;


import java.io.*;
import java.sql.*;

import java.util.*;
import java.util.Map.Entry;


import org.apache.commons.lang.StringUtils;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.culture.model.CulturalBean;


public class TFIDF {
	
	static String sql = null;

	static DBConnect db = null;

	static ResultSet ret = null;
	
	static Statement st = null;

	// the list of file
    private static ArrayList<String> FileList = new ArrayList<String>(); 
    
    // all culture map
    private static HashMap<String,String> culMap = new HashMap<String,String>();

    //get list of file for the directory, including sub-directory of it
    public static List<String> readDirs(String filepath) throws FileNotFoundException, IOException
    {
        try
        {
            File file = new File(filepath);
            if(!file.isDirectory())
            {
                System.out.println("输入的[]");
                System.out.println("filepath:" + file.getAbsolutePath());
            }
            else
            {
                String[] flist = file.list();
                for(int i = 0; i < flist.length; i++)
                {
                    File newfile = new File(filepath + "\\" + flist[i]);
                    if(!newfile.isDirectory())
                    {
                        FileList.add(newfile.getAbsolutePath());
                    }
                    else if(newfile.isDirectory()) //if file is a directory, call ReadDirs
                    {
                        readDirs(filepath + "\\" + flist[i]);
                    }                    
                }
            }
        }catch(FileNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
        return FileList;
    }
    
    //read file
    public static String readFile(String file) throws FileNotFoundException, IOException
    {
        StringBuffer strSb = new StringBuffer(); //String is constant， StringBuffer can be changed.
        InputStreamReader inStrR = new InputStreamReader(new FileInputStream(file), "utf-8"); //byte streams to character streams
        BufferedReader br = new BufferedReader(inStrR); 
        String line = br.readLine();
        while(line != null){
            strSb.append(line).append("\r\n");
            line = br.readLine();    
        }
        
        return strSb.toString();
    }
    
    //word segmentation
    public static ArrayList<String> cutWords(String file) throws IOException{
        
        ArrayList<String> words = new ArrayList<String>();
        String text = culMap.get(file);
        StringReader reader = new StringReader(text);

        IKAnalyzer analyzer = new IKAnalyzer();
        IKSegmenter ikseg = new IKSegmenter(reader, false);
        try{
            do{
                Lexeme me = ikseg.next();
                if(me == null)
                    break;
                String term = me.getLexemeText();
                if(StringUtils.isNumeric(StringUtils.remove(term,'.')))
                    continue;
                if(words.contains(term.toLowerCase()))
                    continue;
                words.add(term);
            }while(true);
        }catch(IOException e){
            System.out.println( e.toString());
        }

      return words;
    }

    
    //term frequency in a file, times for each word
    public static HashMap<String, Integer> normalTF(ArrayList<String> cutwords){
        HashMap<String, Integer> resTF = new HashMap<String, Integer>();
        
        for(String word : cutwords){
            if(resTF.get(word) == null){
                resTF.put(word, 1);
            }
            else{
                resTF.put(word, resTF.get(word) + 1);
            }
        }
        return resTF;
    }
    
    //term frequency in a file, frequency of each word
    public static HashMap<String, Float> tf(ArrayList<String> cutwords){
        HashMap<String, Float> resTF = new HashMap<String, Float>();
        
        int wordLen = cutwords.size();
        HashMap<String, Integer> intTF = TFIDF.normalTF(cutwords); 
        
        Iterator iter = intTF.entrySet().iterator(); //iterator for that get from TF
        while(iter.hasNext()){
            Map.Entry entry = (Map.Entry)iter.next();
            resTF.put(entry.getKey().toString(), Float.parseFloat(entry.getValue().toString()) / wordLen);
        }
        return resTF;
    } 
    
    //tf times for file
    public static HashMap<String, HashMap<String, Integer>> normalTFAllFiles(String dirc) throws IOException{
        HashMap<String, HashMap<String, Integer>> allNormalTF = new HashMap<String, HashMap<String,Integer>>();
        
        List<String> filelist = TFIDF.readDirs(dirc);
        for(String file : filelist){
            HashMap<String, Integer> dict = new HashMap<String, Integer>();
            ArrayList<String> cutwords = TFIDF.cutWords(file); //get cut word for one file
            
            dict = TFIDF.normalTF(cutwords);
            allNormalTF.put(file, dict);
        }    
        return allNormalTF;
    }
    
    //tf for all file
    public static HashMap<String,HashMap<String, Float>> tfAllFiles() throws IOException{
        HashMap<String, HashMap<String, Float>> allTF = new HashMap<String, HashMap<String, Float>>();
        Iterator iter = culMap.entrySet().iterator();
        while(iter.hasNext()){
        	 HashMap<String, Float> dict = new HashMap<String, Float>();
        	 Map.Entry entry = (Map.Entry)iter.next();
        	 String identifier = entry.getKey().toString();
        	 ArrayList<String> cutwords = TFIDF.cutWords(identifier); //get cut words for one file
             dict = TFIDF.tf(cutwords);
             allTF.put(identifier, dict);
        }
        
        return allTF;
    }


    
    public static HashMap<String, Float> idf(HashMap<String,HashMap<String, Float>> all_tf){
        HashMap<String, Float> resIdf = new HashMap<String, Float>();
        HashMap<String, Integer> dict = new HashMap<String, Integer>();
        int docNum = culMap.size();
        
        Iterator culIter = culMap.entrySet().iterator();
        while(culIter.hasNext()){
        	Map.Entry culEntry = (Map.Entry)culIter.next();
        	String identifier = culEntry.getKey().toString();
        	HashMap<String, Float> temp = all_tf.get(identifier);
            Iterator iter = temp.entrySet().iterator();
            while(iter.hasNext()){
                Map.Entry entry = (Map.Entry)iter.next();
                String word = entry.getKey().toString();
                if(dict.get(word) == null){
                    dict.put(word, 1);
                }else {
                    dict.put(word, dict.get(word) + 1);
                }
            }
        }
        
        Iterator iter_dict = dict.entrySet().iterator();
        while(iter_dict.hasNext()){
            Map.Entry entry = (Map.Entry)iter_dict.next();
            float value = (float)Math.log(docNum / Float.parseFloat(entry.getValue().toString()));
            resIdf.put(entry.getKey().toString(), value);
        }
        return resIdf;
    }
    
    //tf-idf
    public static void tf_idf(HashMap<String,HashMap<String, Float>> all_tf,HashMap<String, Float> idfs){
        HashMap<String, LinkedHashMap<String, Float>> resTfIdf = new HashMap<String, LinkedHashMap<String, Float>>();
      
        int docNum = culMap.size();
        
        Iterator culIter = culMap.entrySet().iterator();
        while(culIter.hasNext()){
        	Map.Entry culEntry = (Map.Entry)culIter.next();
        	String identifier = culEntry.getKey().toString();
            LinkedHashMap<String, Float> tfidf = new LinkedHashMap<String, Float>();
            HashMap<String, Float> temp = all_tf.get(identifier);
            Iterator iter = temp.entrySet().iterator();
            while(iter.hasNext()){
                Map.Entry entry = (Map.Entry)iter.next();
                String word = entry.getKey().toString();
                Float value = (float)Float.parseFloat((entry.getValue()).toString()) * idfs.get(word);                 
                tfidf.put(word, value);
            }
            tfidf = sort(tfidf);
            resTfIdf.put(identifier, tfidf);

            String keyword=getTop(tfidf).toString().substring(1, getTop(tfidf).toString().length()-1).toString();
/*
 * //加类别属性至keywords
//             sql="select classification from test where identifier=?  ";
            sql="select classification from culture where identifier=?  ";
            try {        		
        		db = new DBConnect(sql);
            	db.pst.setString(1, identifier);      
            	ret = db.pst.executeQuery();  
  	      	  
	            while (ret.next()) {  
	            	String classification=ret.getString(1);
	            	keyword=classification+','+keyword;
	            }
	        } catch (SQLException e) {
	        	e.printStackTrace();
	        }
         
*/           
            String sql = "update culture set keywords= ? where identifier=?";
            
            try {        		
        		db = new DBConnect(sql);
            	db.pst.setString(1, keyword);
            	db.pst.setString(2, identifier);         
            	db.pst.execute();        	
	        } catch (SQLException e) {
	        	e.printStackTrace();
	        }finally{
	        	db.close();
	        }
          
        }
   
//        System.out.println("TF-IDF for Every file is :");
//        DisTfIdf(resTfIdf);
        
    }
    
    public static void DisTfIdf(HashMap<String, LinkedHashMap<String, Float>> tfidf){
        Iterator iter1 = tfidf.entrySet().iterator();
        while(iter1.hasNext()){
            Map.Entry entrys = (Map.Entry)iter1.next();
            System.out.println("FileName: " + entrys.getKey().toString());
            System.out.print("{");
            HashMap<String, Float> temp = (HashMap<String, Float>) entrys.getValue();
            Iterator iter2 = temp.entrySet().iterator();            
            while(iter2.hasNext()){
                Map.Entry entry = (Map.Entry)iter2.next(); 
                System.out.print(entry.getKey().toString() + " = " + entry.getValue().toString() + ", ");
            }
            System.out.println("}");
        }      
    }
    
	// 排序
    public static LinkedHashMap<String, Float> sort(HashMap<String, Float> map){

    	LinkedHashMap<String,Float> resMap = new LinkedHashMap<String,Float>();
		List<Map.Entry<String, Float>> infoIds = new ArrayList<Map.Entry<String, Float>>(map.entrySet());
			Collections.sort(infoIds, new Comparator<Map.Entry<String, Float>>() {
				public int compare(Map.Entry<String, Float> o1,
						Map.Entry<String, Float> o2) {
					
					if(o1.getValue()==o2.getValue()&&o1.getKey().equals(o2.getKey()))
						return 0;
					return o1.getValue()>o2.getValue()?-1:1;
				}
			});
			
		for (int i = 0; i < infoIds.size(); i++) {
			Entry<String,Float> ent=infoIds.get(i);
			resMap.put(ent.getKey(), ent.getValue());
		}
		return resMap;
    }
    
    public static ArrayList<String> getTop(LinkedHashMap<String, Float> map){
    	ArrayList<String> top=new ArrayList<String>();
    	List<Map.Entry<String, Float>> infoIds = new ArrayList<Map.Entry<String, Float>>(map.entrySet());
    	try{
	    	for (int i = 0; i < 15; i++) {
				Entry<String,Float> ent=infoIds.get(i);
				top.add(ent.getKey());
			}
	    }catch(IndexOutOfBoundsException e ){
	        System.out.println( e.toString());
	    }
    	return top;
    }
    
    //compute similarity between the bean and the Others
    public static HashMap<String, Float> allSimilarty(CulturalBean cb) throws IOException, SQLException{
 
    	HashMap<String,Float> resMap = new HashMap<String,Float>();
    	
    	
    	for(CulturalBean cbn : GetDBData.selectAll() ){
    		double similarity=SimilarDegreeByCos.getSimilarDegree(cb.getKeywords(), cbn.getKeywords());
        	resMap.put(cbn.getIdentifier(),(float) similarity);  
    	}
	   	 
	   	return resMap;
	   	
    }
       
    //Top similar id
    public static LinkedHashMap<String, Float> topSimilar(LinkedHashMap<String, Float> map){
    	LinkedHashMap<String, Float> topSimilar=new LinkedHashMap<String, Float>();
   	
    	List<Map.Entry<String, Float>> infoIds = new ArrayList<Map.Entry<String, Float>>(map.entrySet());
    	try{
	    	for (int i = 0; i < 10; i++) {
				Entry<String,Float> ent=infoIds.get(i);
				topSimilar.put(ent.getKey(), ent.getValue());
			   //System.out.println(ent.getKey()+ "  "+ent.getValue());
			}
	    }catch(IndexOutOfBoundsException e ){
	        System.out.println( e.toString());
	    }

    	return topSimilar;
    }
    
    //将topSimilar写回数据库
    public static void save(CulturalBean cb,LinkedHashMap<String, Float> map){
    	String identifier = cb.getIdentifier();
    	int id=cb.getId();
    	String topSimilar = "";
    	
    	List<Map.Entry<String, Float>> infoIds = new ArrayList<Map.Entry<String, Float>>(map.entrySet());
    	try{
	    	for (int i = 0; i < 10; i++) {
				Entry<String,Float> ent=infoIds.get(i);
				topSimilar=topSimilar+ent.getKey()+',';
			}
	    }catch(IndexOutOfBoundsException e ){
	        System.out.println( e.toString());
	    }

    	String sql = "update topSimilar set topSimilar= ? where identifier=? and id=?";
    	try {        		
    		db = new DBConnect(sql);
        	db.pst.setString(1, topSimilar);
        	db.pst.setString(2, identifier); 
        	db.pst.setInt(3, id);
        	db.pst.execute();        	
        } catch (SQLException e) {
        	e.printStackTrace();
        }finally{
        	db.close();
        }
    	    	
    }
    
    public static void main(String[] args) throws IOException, SQLException {
         // TODO Auto-generated method stub
     	 
      	 culMap = GetDBData.getAllCul();
      	 System.out.println(culMap.get("EF098BB40AFD4FE89C342EAF6F81C3F3"));
         HashMap<String,HashMap<String, Float>> all_tf = tfAllFiles();
         System.out.println(all_tf.get("EF098BB40AFD4FE89C342EAF6F81C3F3"));
//         HashMap<String, Float> idfs = idf(all_tf);
//         System.out.println(); 
//         tf_idf(all_tf, idfs); 
         
//     	 for(CulturalBean cb : GetDBData.selectAll() ){     		 
//     		HashMap<String, Float> allSimilarty=allSimilarty(cb);
//        	LinkedHashMap<String, Float> sort=sort(allSimilarty);
//        	LinkedHashMap<String, Float> topSimilar=topSimilar(sort);
//        	save(cb,topSimilar);     	 
//     	 }	 
     }
}






