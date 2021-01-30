package fr.elpresidente.game.scenario;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ScenarioLoader {

    private Scenario scenario;

    private File jsonFile;

    private String jsonFileContent;

    public ScenarioLoader() {
        setJsonFile(new File("C:\\Users\\quent\\Documents\\Cours\\JAVA\\El-Presidente\\src\\main\\resources\\scenario.json"));
        setJsonFileContent("");
    }

    public void loadScenario() {
        readJsonFile();
        scenario = new Scenario(jsonFileContent);
    }

    private void readJsonFile() {
        try {
            Scanner scanner = new Scanner(jsonFile);
            processJsonFile(scanner);
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void processJsonFile(Scanner scanner) {
        while (scanner.hasNextLine()) {
            addJsonFileContent(scanner.nextLine());
        }
    }

    public void addJsonFileContent(String content) {
        this.jsonFileContent += content;
    }

    public void setJsonFileContent(String jsonFileContent) {
        this.jsonFileContent = jsonFileContent;
    }

    public void setJsonFile(File jsonFile) {
        this.jsonFile = jsonFile;
    }

    public Scenario getScenario() {
        return scenario;
    }
}
