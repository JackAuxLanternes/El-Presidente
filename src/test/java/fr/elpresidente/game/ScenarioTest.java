package fr.elpresidente.game;

import fr.elpresidente.game.scenario.ScenarioParser;
import junit.framework.TestCase;
import org.junit.Test;

import java.io.File;

public class ScenarioTest extends TestCase {

    @Test
    public void testScenarioCanBeParsed() {
        ScenarioParser scenarioParser = new ScenarioParser("."
                + File.separator + "src"
                + File.separator + "test"
                + File.separator + "resources"
                + File.separator + "testScenario.json");
        scenarioParser.openScenario();

        assertEquals(scenarioParser.getScenario().getDate().toString(), "{\"year\":1953,\"season\":\"WINTER\"}");
    }

}
