package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com.dummyjson.steps",
        tags = "@regression",
        plugin = {
                "pretty",
                "html:target/cucumber-report/cucumber-reports.html",
                "json:target/cucumber-report/cucumber.json"
        }
)
public class RunTest extends AbstractTestNGCucumberTests {
}
