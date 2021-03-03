package fr.elpresidente.game.save;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SaveParser {
    private Save save;

    private File jsonFile;

    private String jsonFileContent;

    public SaveParser(String file_name) {
        setJsonFile(new File(file_name));
        setJsonFileContent("");
    }

    public void openSave() {
        readJsonFile();
        save = new Save(jsonFileContent);
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

    public Save getSave() {
        return save;
    }
}
