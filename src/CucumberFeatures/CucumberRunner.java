package CucumberFeatures;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)

@CucumberOptions(
		plugin = {"pretty", "json:target"},
		features = {"src/CucumberRunner/"}
		)

public class CucumberRunner {

}
