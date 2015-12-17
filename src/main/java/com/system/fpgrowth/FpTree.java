package com.system.fpgrowth;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
 
public class FpTree {
     
    private static int support = 1;		//支持度
    
    private static int size = 2;		//频繁项级大小
    
    public static void genRule(List<List<String>> transactions) throws IOException{
    	
    	//初始化结果集
        Map<String,Integer> result = new HashMap<String,Integer>();
        getResult(transactions,result);
        DecimalFormat    df   = new DecimalFormat("######0.00");   
        Iterator iter = result.entrySet().iterator();
        while(iter.hasNext()){
        	Entry entry = (Entry)iter.next();
        	String key = (String)entry.getKey();
        	int val = (Integer)entry.getValue();
        	String[] keys = key.split(",");
        	if(keys.length == 2){
        		String start = keys[0];	
        		int startValue = result.get(start);
        		double confidence = (double)val/startValue;
        		confidence *= 100;
        		System.out.println("{"+keys[0]+"}-->{"+keys[1]+"},"+df.format(confidence)+"%");
        	}
        }

    }
     
    public static void getResult(List<List<String>> transactions,Map<String,Integer> result) throws IOException{
        
    	//初始一个频繁模式集
        List<String> frequences = new LinkedList<String>();
        //开始递归
        digTree(transactions,frequences,result);
        
    }
     
    public static void digTree(List<List<String>> transactions,
            List<String> frequences,Map<String,Integer> result){
        //扫描事物数据集，排序
        final Map<String,Integer> sortedMap = scanAndSort(transactions);
        //没有数据是支持最小支持度了，可以停止了
        if(sortedMap.size() == 0){
            return;
        }
        Map<String,List<TreeNode>> index = new HashMap<String,List<TreeNode>>();
        TreeNode root = buildTree(transactions,index,sortedMap);
 
        //否则开始从排序最低的项开始 抽出条件模式基，递归挖掘
        List<String> headTable = new ArrayList<String>(sortedMap.keySet());
        Collections.sort(headTable,new Comparator<String>(){
            @Override
            public int compare(String o1, String o2) {
                int i = sortedMap.get(o2)-sortedMap.get(o1); 
                return i != 0 ? i : o1.compareTo(o2);
            }});
        //从项头表最后一项开始挖掘
        for(int i=headTable.size()-1;i>=0;i--){
            String subject = headTable.get(i);
            List<List<String>> frequentModeBases = extract(index.get(subject),root);
             
            LinkedList<String> nextFrequences = new LinkedList<String>(frequences);
            nextFrequences.add(subject);
            if(nextFrequences.size() == 1 || nextFrequences.size() == 2){
                System.out.println(StringUtils.join(nextFrequences,",")+"\t"+sortedMap.get(subject));
                result.put(StringUtils.join(nextFrequences,","), sortedMap.get(subject));
            }
             
            digTree(frequentModeBases,nextFrequences,result);
             
        }
    }
     
    /**
     * 挖掘一个项上面的频繁模式基
     * @param list
     * @param root
     * @return
     */
    public static List<List<String>> extract(List<TreeNode> list,TreeNode root){
        List<List<String>> returnList = new ArrayList<List<String>>();
        for(TreeNode node:list){
            TreeNode parent = node.getParent();
            if(parent.getCount() != -1){
                ArrayList<String> tranc = new ArrayList<String>();
                while(parent.getCount() != -1){
                    tranc.add(parent.getName());
                    parent = parent.getParent();
                }
                for(int i=0;i<node.getCount();i++){
                    returnList.add(tranc);
                }
            }
        }
        return returnList;
    }
     
    /**
     * 构建pf树
     * @param file
     * @param index
     * @param sortedMap
     * @return
     * @throws IOException
     */
    public static TreeNode buildTree(List<List<String>> transactions,
            Map<String,List<TreeNode>> index,
            final Map<String,Integer> sortedMap){
        TreeNode root = new TreeNode(null,"root",-1);
        for(List<String> subjects:transactions){
            Iterator<String> ite = subjects.iterator();
            while(ite.hasNext()){
                String subject = ite.next();
                if(!sortedMap.containsKey(subject)){
                    ite.remove();
                }
            }
            Collections.sort(subjects,new Comparator<String>(){
                @Override
                public int compare(String o1, String o2) {
                    int i = sortedMap.get(o2)-sortedMap.get(o1); 
                    return i != 0 ? i : o1.compareTo(o2);
                }});
             
            TreeNode current = root;
            for(int i=0;i<subjects.size();i++){
                String subject = subjects.get(i);
                TreeNode next = current.findChild(subject);
                if(next == null){
                    TreeNode newNode = new TreeNode(current,subject,1);
                    current.addChild(newNode);
                    current = newNode;
                    List<TreeNode> thisIndex = index.get(subject);
                    if(thisIndex == null){
                        thisIndex = new ArrayList<TreeNode>();
                        index.put(subject, thisIndex);
                    }
                    thisIndex.add(newNode);
                }else{
                    next.incrementCount(1);
                    current = next;
                }
            }
        }
        return root;
    }
     
    /**
     * 扫描排序
     * @param file
     * @return
     * @throws IOException
     */
    public static Map<String,Integer> scanAndSort(List<List<String>> transactions){
        Map<String,Integer> map = new HashMap<String,Integer>();
        //空的就不扫了
        if(transactions.size()==0){
            return map;
        }
        for(List<String> basket:transactions){
            for(String subject:basket){
                Integer count = map.get(subject);
                if (count == null) {
                    map.put(subject, 1);
                } else {
                    map.put(subject, count + 1);
                }
            }
        }
        Iterator<Entry<String,Integer>> ite = map.entrySet().iterator();
        while(ite.hasNext()){
            Entry<String,Integer> entry = ite.next();
            if(entry.getValue() < support){
                ite.remove();
            }
        }
        return map;
    }
    
    /**
     * 统计每个项的数量
     * @param transactions
     * @return
     */
    public static Map<String,Integer> count(List<List<String>> transactions){
    	Map<String,Integer> map = new HashMap<String,Integer>();
    	for(int i=0;i<transactions.size();i++){
    		for(int j=0;j<transactions.get(i).size();j++){
    			String object = transactions.get(i).get(j);
    			if(map.get(object)==null){
    				map.put(object, 1);
    			}else{
    				int count = map.get(object);
    				map.put(object,count++);
    			}
    		}
    	}
    	return map;
    }
}