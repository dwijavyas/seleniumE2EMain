package cucumber;

import org.testng.annotations.Test;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/java/cucumber/Main2.feature",
glue= {"stepDefinitions"}, monochrome=true, tags="@InvalidTest",plugin= {"html:target/cucumber-reports.html"})
@Test
public class TestRunner extends AbstractTestNGCucumberTests {
	
}
