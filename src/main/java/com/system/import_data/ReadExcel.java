/**
 * 
 */
package com.system.import_data;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.culture.model.CulturalBean;
import com.culture.util.CodeGenerator;



/**
 * @author Hongten
 * @created 2014-5-20
 */
public class ReadExcel {
    
    /**
     * read the Excel file
     * @param path the path of the Excel file
     * @return
     * @throws IOException
     */
    public List<CulturalBean> readExcel(String path) throws IOException {
        if (path == null || Common.EMPTY.equals(path)) {
            return null;
        } else {
            String postfix = Util.getPostfix(path);
            if (!Common.EMPTY.equals(postfix)) {
                if (Common.OFFICE_EXCEL_2003_POSTFIX.equals(postfix)) {
                    return readXls(path);
                } else if (Common.OFFICE_EXCEL_2010_POSTFIX.equals(postfix)) {
                    return readXlsx(path);
                }
            } else {
                System.out.println(path + Common.NOT_EXCEL_FILE);
            }
        }
        return null;
    }

    /**
     * Read the Excel 2010
     * @param path the path of the excel file
     * @return
     * @throws IOException
     */
    public List<CulturalBean> readXlsx(String path) throws IOException {
        System.out.println(Common.PROCESSING + path);
        InputStream is = new FileInputStream(path);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
        CulturalBean cultural = null;
        List<CulturalBean> list = new ArrayList<CulturalBean>();
        // Read the Sheet
        for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
            if (xssfSheet == null) {
                continue;
            }
            // Read the Row
            for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
                XSSFRow xssfRow = xssfSheet.getRow(rowNum);
                if (xssfRow != null) {
                	cultural = new CulturalBean();
                    XSSFCell id = xssfRow.getCell(0);
                    XSSFCell mainpic = xssfRow.getCell(1);
                    XSSFCell classification = xssfRow.getCell(2);
                    XSSFCell title = xssfRow.getCell(3);
                    XSSFCell used_title = xssfRow.getCell(4);
                    XSSFCell c_level = xssfRow.getCell(5);
                    XSSFCell creation_date = xssfRow.getCell(6);
                    XSSFCell creation_time = xssfRow.getCell(7);
                    XSSFCell place_of_origin = xssfRow.getCell(8);
                    XSSFCell creator = xssfRow.getCell(9);
                    XSSFCell measurement = xssfRow.getCell(10);
                    XSSFCell excavation_date = xssfRow.getCell(11);
                    XSSFCell excavation_place = xssfRow.getCell(12);
                    XSSFCell current_location = xssfRow.getCell(13);
                    XSSFCell exhibition_history = xssfRow.getCell(14);
                    XSSFCell shape = xssfRow.getCell(15);
                    XSSFCell pattern = xssfRow.getCell(16);
                    XSSFCell color = xssfRow.getCell(17);
                    XSSFCell scene = xssfRow.getCell(18);
                    XSSFCell c_usage = xssfRow.getCell(19);
                    XSSFCell symbolic_meaning = xssfRow.getCell(20);
                    XSSFCell aesthetic_desc = xssfRow.getCell(21);
                    XSSFCell material = xssfRow.getCell(22);
                    XSSFCell technique = xssfRow.getCell(23);
                    XSSFCell history_info = xssfRow.getCell(24);
                    XSSFCell folklore = xssfRow.getCell(25);
                    XSSFCell relation = xssfRow.getCell(26);
                    XSSFCell identifier = xssfRow.getCell(27);
                    XSSFCell source = xssfRow.getCell(28);
                    XSSFCell rights = xssfRow.getCell(29);
                    cultural.setId(Integer.valueOf(getValue(id)));
                    cultural.setMainpic(Common.PICPRE+getValue(mainpic));
                    cultural.setType("器物");
                    cultural.setClassification(getValue(classification));
                    cultural.setTitle(getValue(title));
                    cultural.setUsed_title(getValue(used_title));
                    cultural.setC_level(getValue(c_level));
                    cultural.setCreation_date(DynastyUtil.dynastyMap.get(getValue(creation_date)));
                    cultural.setCreation_time(getValue(creation_time));
                    cultural.setPlace_of_origin(getValue(place_of_origin));
                    cultural.setCreator(getValue(creator));
                    cultural.setMeasurement(getValue(measurement));
                    cultural.setExcavation_date(getValue(excavation_date));
                    cultural.setExcavation_place(getValue(excavation_place));
                    cultural.setCurrent_location(getValue(current_location));
                    cultural.setExhibition_history(getValue(exhibition_history));
                    cultural.setShape(getValue(shape));
                    cultural.setPattern(getValue(pattern));
                    cultural.setColor(getValue(color));
                    cultural.setScene(getValue(scene));
                    cultural.setC_usage(getValue(c_usage));
                    cultural.setSymbolic_meaning(getValue(symbolic_meaning));
                    cultural.setAesthetic_desc(getValue(aesthetic_desc));
                    cultural.setMaterial(getValue(material));
                    cultural.setTechnique(getValue(technique));
                    cultural.setHistory_info(getValue(history_info));
                    cultural.setFolklore(getValue(folklore));
                    cultural.setRelation(getValue(relation));
                    cultural.setIdentifier(CodeGenerator.createUUID());
                    cultural.setSource(getValue(source));
                    cultural.setRights(getValue(rights));
                    cultural.setManager("yibiaosuo");
                    list.add(cultural);
                }
            }
        }
        return list;
    }

    /**
     * Read the Excel 2003-2007
     * @param path the path of the Excel
     * @return
     * @throws IOException
     */
    public List<CulturalBean> readXls(String path) throws IOException {
        System.out.println(Common.PROCESSING + path);
        InputStream is = new FileInputStream(path);
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        CulturalBean cultural = null;
        List<CulturalBean> list = new ArrayList<CulturalBean>();
        // Read the Sheet
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            // Read the Row
            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow != null) {
                	cultural = new CulturalBean();
                    HSSFCell id = hssfRow.getCell(0);
                    HSSFCell mainpic = hssfRow.getCell(1);
                    HSSFCell classification = hssfRow.getCell(2);
                    HSSFCell title = hssfRow.getCell(3);
                    HSSFCell used_title = hssfRow.getCell(4);
                    HSSFCell c_level = hssfRow.getCell(5);
                    HSSFCell creation_date = hssfRow.getCell(6);
                    HSSFCell creation_time = hssfRow.getCell(7);
                    HSSFCell place_of_origin = hssfRow.getCell(8);
                    HSSFCell creator = hssfRow.getCell(9);
                    HSSFCell measurement = hssfRow.getCell(10);
                    HSSFCell excavation_date = hssfRow.getCell(11);
                    HSSFCell excavation_place = hssfRow.getCell(12);
                    HSSFCell current_location = hssfRow.getCell(13);
                    HSSFCell exhibition_history = hssfRow.getCell(14);
                    HSSFCell shape = hssfRow.getCell(15);
                    HSSFCell pattern = hssfRow.getCell(16);
                    HSSFCell color = hssfRow.getCell(17);
                    HSSFCell scene = hssfRow.getCell(18);
                    HSSFCell c_usage = hssfRow.getCell(19);
                    HSSFCell symbolic_meaning = hssfRow.getCell(20);
                    HSSFCell aesthetic_desc = hssfRow.getCell(21);
                    HSSFCell material = hssfRow.getCell(22);
                    HSSFCell technique = hssfRow.getCell(23);
                    HSSFCell history_info = hssfRow.getCell(24);
                    HSSFCell folklore = hssfRow.getCell(25);
                    HSSFCell relation = hssfRow.getCell(26);
                    HSSFCell identifier = hssfRow.getCell(27);
                    HSSFCell source = hssfRow.getCell(28);
                    HSSFCell rights = hssfRow.getCell(29);
                    cultural.setId(Integer.valueOf(getValue(id)));
                    cultural.setMainpic(Common.PICPRE+getValue(mainpic));
                    cultural.setType("器物");
                    cultural.setClassification(getValue(classification));
                    cultural.setTitle(getValue(title));
                    cultural.setUsed_title(getValue(used_title));
                    cultural.setC_level(getValue(c_level));
                    cultural.setCreation_date(DynastyUtil.dynastyMap.get(getValue(creation_date)));
                    cultural.setCreation_time(getValue(creation_time));
                    cultural.setPlace_of_origin(getValue(place_of_origin));
                    cultural.setCreator(getValue(creator));
                    cultural.setMeasurement(getValue(measurement));
                    cultural.setExcavation_date(getValue(excavation_date));
                    cultural.setExcavation_place(getValue(excavation_place));
                    cultural.setCurrent_location(getValue(current_location));
                    cultural.setExhibition_history(getValue(exhibition_history));
                    cultural.setShape(getValue(shape));
                    cultural.setPattern(getValue(pattern));
                    cultural.setColor(getValue(color));
                    cultural.setScene(getValue(scene));
                    cultural.setC_usage(getValue(c_usage));
                    cultural.setSymbolic_meaning(getValue(symbolic_meaning));
                    cultural.setAesthetic_desc(getValue(aesthetic_desc));
                    cultural.setMaterial(getValue(material));
                    cultural.setTechnique(getValue(technique));
                    cultural.setHistory_info(getValue(history_info));
                    cultural.setFolklore(getValue(folklore));
                    cultural.setRelation(getValue(relation));
                    cultural.setIdentifier(CodeGenerator.createUUID());
                    cultural.setSource(getValue(source));
                    cultural.setRights(getValue(rights));
                    cultural.setManager("yibiaosuo");
                    list.add(cultural);
                }
            }
        }
        return list;
    }

    @SuppressWarnings("static-access")
    private String getValue(XSSFCell xssfRow) {
    	if(xssfRow == null)
    		return "";
    	else if (xssfRow.getCellType() == xssfRow.CELL_TYPE_BOOLEAN) {
            return String.valueOf(xssfRow.getBooleanCellValue());
        } else if (xssfRow.getCellType() == xssfRow.CELL_TYPE_NUMERIC) {
            return String.valueOf(xssfRow.getNumericCellValue());
        } else {
            return String.valueOf(xssfRow.getStringCellValue());
        }
    }

    @SuppressWarnings("static-access")
    private String getValue(HSSFCell hssfCell) {
    	if(hssfCell == null)
    		return "";
    	else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(hssfCell.getBooleanCellValue());
        } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
            return String.valueOf(hssfCell.getNumericCellValue());
        } else {
            return String.valueOf(hssfCell.getStringCellValue());
        }
    }
}