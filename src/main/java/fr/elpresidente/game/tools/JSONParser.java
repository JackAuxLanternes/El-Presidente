package fr.elpresidente.game.tools;

import fr.elpresidente.game.save.Save;
import fr.elpresidente.game.scenario.Scenario;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class JSONParser {
    private JSONContent content;

    private File jsonFile;

    private String jsonFileContent;

    public JSONParser(String file_name) {
        setJsonFile(new File(file_name));
        setJsonFileContent("");
    }

    public void openAsScenario() {
        readJsonFile();
        content = new Scenario(jsonFileContent);
    }

    public void openAsSave() {
        readJsonFile();
        content = new Save(jsonFileContent);
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

    public JSONContent getContent() {
        return content;
    }
}
