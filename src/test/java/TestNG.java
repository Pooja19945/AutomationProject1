
import StepDefinations.ConfigFileReader;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.annotations.*;

import java.util.HashMap;
import java.util.Map;
@Listeners(CustomListner.class)
public class TestNG {
    static HashMap<String,String> data=new HashMap<String,String>();
    private static final Logger logger = LogManager.getLogger(TestNG.class);
    @BeforeClass(alwaysRun = true)
    public void beforeClassMethod(){
        System.out.println("BeforeClass annotation");

        ConfigFileReader cReader = new ConfigFileReader();
        cReader.configReader(data);
        cReader.testDataFromExcel(data);
    }
    @AfterClass(alwaysRun = true)
    public void afterClass(){
        logger.info("AfterClass annotation");
    }
    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(){
        logger.info("BeforeMethod annotation");
    }
    @AfterMethod(alwaysRun = true)
    public void afterMethod(){
        logger.info("AfterMethod annotation");
    }
    @Test(groups = {"UITest"})
    public void test1(){
        logger.info("Test1 annotation");

    }
    @Test(groups = {"UITest"})
    public void test2(){
        logger.info("Test2 annotation");

    }
    @Test(groups = {"UITest"})
    public void test3(){
        logger.info("Test3 annotation");

    }
    @Test(groups = {"UITest"})
    public void test4(){
        logger.info("Test4 annotation");

    }

    @Test(groups = {"DBTest"})
    public void test5(){
        logger.info("Test5 annotation");

    }
    @Test(groups = {"UITest"})
    public void test6(){
        logger.info("Test6 annotation");

    }
    @Test(groups = {"DBTest"})
    public void test7(){
        logger.info("Test7 annotation");

    }
//    @DataProvider(name = "testData")
//    public  Object[][] getData(){
//        int i=1;
//    String[][] dataArray=new String[][]{};
//        for(Map.Entry<String,String> entry : data.entrySet()){
//            dataArray[i][i]= entry.getKey();
//            dataArray[i][2]= entry.getValue();
//        }
//    System.out.println(dataArray.toString());

//return dataArray;
//    }
}

//The TestNG has a default value of thread = 5 for parallel testing,
//default priority=0