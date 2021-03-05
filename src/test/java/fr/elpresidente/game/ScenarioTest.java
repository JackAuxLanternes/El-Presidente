package fr.elpresidente.game;

import fr.elpresidente.game.scenario.Scenario;
import fr.elpresidente.game.tools.JSONParser;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class ScenarioTest {

    @Test
    public void testScenarioCanBeParsed() {
        JSONParser scenarioParser = new JSONParser("."
                + File.separator + "src"
                + File.separator + "test"
                + File.separator + "resources"
                + File.separator + "testScenario.json");
        scenarioParser.openAsScenario();
        Scenario scenario = (Scenario) scenarioParser.getContent();

        Assert.assertEquals(scenario.getDate().toString(), "{\"year\":1953,\"season\":\"WINTER\"}");
    }

}
