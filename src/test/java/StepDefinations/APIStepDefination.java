package StepDefinations;

import com.vimalselvam.cucumber.listener.Reporter;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.codec.binary.Base64;
import org.json.simple.JSONObject;
import org.testng.Assert;

import java.io.File;
import java.util.HashMap;
import java.util.Properties;

public class APIStepDefination{
    ConfigFileReader cReader;
    Properties p;
    RequestSpecification httpRequest;
    static HashMap<String,String> testData=new HashMap<String,String>();

    @Before
    public void loadConfigurations(){
        cReader =new ConfigFileReader();
        cReader.configReader(testData);
        cReader.testDataFromExcel(testData);
        System.out.println(testData);
    }
    @After
    public static void writeExtentReport() {
        Reporter.loadXMLConfig(new File(testData.get("reportConfigPath")));
    }
    @Given("Test case start")
    public void test_case_start() {

        System.out.println("Test case starts");
        RestAssured.baseURI="https://demoqa.com"; //RestAssured Class
        httpRequest=RestAssured.given();

    }
    @Given("User create new user")
    public void create_new_user() {
        httpRequest.header("Content-Type","application/json");
        JSONObject payload = new JSONObject();
        payload.put("userName",testData.get("userName"));
        payload.put("password",testData.get("password"));

        //posting request and getting response
        Response response=httpRequest.body(payload.toString()).post("/Account/v1/User"); //Response Interface

        //validating response

        System.out.println("Status Line : "+response.statusLine());
        Assert.assertEquals(response.statusLine(),"HTTP/1.1 201 Created","Unable to create User");
        System.out.println(response.body().asString());
        JsonPath js=response.jsonPath();
        testData.put("userID",js.getString("userID"));
    }
    @Given("user generate token")
    public void generate_token() {
        httpRequest.header("Content-Type","application/json");
        JSONObject payload = new JSONObject();
        payload.put("userName",testData.get("userName"));
        payload.put("password",testData.get("password"));

        //posting request and getting response
        Response response=httpRequest.body(payload.toString()).post("/Account/v1/GenerateToken");

        //validating response

        System.out.println("Status Line : "+response.statusLine());
        System.out.println("Status code : "+response.statusCode());
        JsonPath js =response.jsonPath();
        String token=js.getString("token");
        System.out.println("token : "+token);
        testData.put("token",token) ;
    }
    @Given("user is authorized")
    public void authorize_Users() {
        String userDetails=testData.get("userName")+":"+testData.get("password");
        byte [] encoUserDetails= Base64.encodeBase64(userDetails.getBytes());
        String encoDetailsAsString=new String(encoUserDetails);
        System.out.println("encoded value : "+encoDetailsAsString);
        httpRequest.header("Authorization","Basic "+encoDetailsAsString);
        httpRequest.header("Content-Type","application/json");
        JSONObject payload = new JSONObject();
        payload.put("userName",testData.get("userName"));
        payload.put("password",testData.get("password"));

        //posting request and getting response
        Response response=httpRequest.body(payload.toString()).post("/Account/v1/Authorized");

        //validating response
        System.out.println("Status Line : "+response.statusLine());
        System.out.println("Status code : "+response.statusCode());
        Assert.assertEquals(response.statusCode(),200);
        System.out.println("Response Body : "+response.body().asString());
        Assert.assertEquals(response.body().asString(),"true","User is not authorized");
    }
    @Then("Test case ends")
    public void test_case_end() {
        System.out.println("end of the testcases");
    }
}
