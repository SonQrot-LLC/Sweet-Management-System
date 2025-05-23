package sweet.management;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "MyFeatures", monochrome = true, snippets = SnippetType.CAMELCASE,
        glue = {"sweet.management"})
public class    AcceptanceTest {

}