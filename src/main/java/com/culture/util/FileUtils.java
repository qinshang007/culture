package com.culture.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author malik
 * @version 2011-3-10 下午10:49:41
 */
public class FileUtils {
	
	public static void method1(String file, String conent) {   
        BufferedWriter out = null;   
        try {   
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true)));   
            out.write(conent);   
        } catch (Exception e) {   
            e.printStackTrace();   
        } finally {   
            try {   
            	if(out != null){
            		out.close();   
                }
            } catch (IOException e) {   
                e.printStackTrace();   
            }   
        }   
    }   
  
    /**  
     * 追加文件：使用FileWriter  
     *   
     * @param fileName  
     * @param content  
     */  
    public static void writeFile(String fileName, String content) { 
    	FileWriter writer = null;
        try {   
            // 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件   
            writer = new FileWriter(fileName, true);   
            writer.write(content);     
        } catch (IOException e) {   
            e.printStackTrace();   
        } finally {   
            try {   
            	if(writer != null){
            		writer.close();   
            	}
            } catch (IOException e) {   
                e.printStackTrace();   
            }   
        } 
    }   
  
    /**  
     * 追加文件：使用RandomAccessFile  
     *   
     * @param fileName 文件名  
     * @param content 追加的内容  
     */  
    public static void method3(String fileName, String content) { 
    	RandomAccessFile randomFile = null;
        try {   
            // 打开一个随机访问文件流，按读写方式   
            randomFile = new RandomAccessFile(fileName, "rw");   
            // 文件长度，字节数   
            long fileLength = randomFile.length();   
            // 将写文件指针移到文件尾。   
            randomFile.seek(fileLength);   
            randomFile.writeBytes(content);    
        } catch (IOException e) {   
            e.printStackTrace();   
        } finally{
        	if(randomFile != null){
        		try {
					randomFile.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}
        }
    }  
    
    /**
     * 删除文件中的某一行
     * @param fileName
     * @param content
     */
    public static void deleteRow(String fileName, String content) throws IOException{ 
        File file = new File(fileName);
        BufferedReader reader = null;
        List<String> list = new ArrayList<String>();
        reader = new BufferedReader(new FileReader(file));
        String text = reader.readLine();
        while (text != null) {
            if (!text.trim().equals(content)) {
                list.add(text + "\r\n");
            }
            text = reader.readLine();
        }
        reader.close();
        writeFile(fileName,list);
    }   

    /**
     * 写文件操作
     * @param filePath
     * @param list
     * @throws IOException
     */
    private static void writeFile(String filePath, List<String> list)throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream outputStream = new FileOutputStream(file);
        for (String s : list) {
            outputStream.write(s.getBytes());
        }
        outputStream.close();
    }

	public static void main(String[] args) {
		try{
			String fileName = "text.txt";
			String content = "[(?a NSP:纹饰 '莲花纹'),(?a NSP:使用情境 '摆件') -> (?a NSP:象征意义 '高雅')]";
			deleteRow(fileName,content);
		}catch(Exception e){
			System.out.println(e);
		}
	}
}
