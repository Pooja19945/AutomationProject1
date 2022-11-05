package StepDefinations;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class ConfigFileReader {
         Properties properties;
         final String propertyFilePath= "C:/Users/pooshind2/Desktop/Project/AutomationPracticeProject/Project1/AutomationFramework/Environment/Configrations/config.properties";
        public  void configReader(Map<String,String> testData) {
            BufferedReader reader;
            try {
                reader = new BufferedReader(new FileReader(propertyFilePath));
                properties = new Properties();
                properties.load(reader);
                reader.close();

                Set<Map.Entry<Object, Object>> df = properties.entrySet();
                for(Map.Entry<Object, Object> eachEntry:df){
                    System.out.println(eachEntry.getKey()+"-----> "+eachEntry.getValue());
                    testData.put(eachEntry.getKey().toString(),eachEntry.getValue().toString());
                }
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
            }
        }
        public void testDataFromExcel(Map<String,String> testData){

            File file=new File("C:/Users/pooshind2/Desktop/Project/AutomationPracticeProject/Project1/AutomationFramework/Environment/TestData/TestData.xlsx");
            try {
                XSSFWorkbook wb=new  XSSFWorkbook(file);
                XSSFSheet sheet=wb.getSheet("Module1");
                XSSFRow eachRow;
                Cell eachCell;
                XSSFRow firstRow=sheet.getRow(0);
                for (int i=1;sheet.getRow(i)!=null;i++)
                {
                    eachRow = sheet.getRow(i);
                    for (int j = 0; j<eachRow.getLastCellNum()-1; j++)
                    {
                        if(!eachRow.getCell(j).getStringCellValue().equalsIgnoreCase(null)) {
                            eachCell = eachRow.getCell(j);
                            testData.put(firstRow.getCell(j).getStringCellValue(), eachCell.getStringCellValue());
                        }
                     }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InvalidFormatException e) {
                throw new RuntimeException(e);
            }

        }
    }

