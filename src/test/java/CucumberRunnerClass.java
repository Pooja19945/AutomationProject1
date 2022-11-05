import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "C:/Users/pooshind2/Desktop/Project/AutomationPracticeProject/Project1/AutomationFramework/src/test/resources/features/"
        ,glue={"StepDefinations"}
        ,monochrome = true
        ,dryRun = false
        ,plugin = {"pretty", "html:target/cucumber-reports"}
        ,tags= "@TestCase1")

public class CucumberRunnerClass {
}
