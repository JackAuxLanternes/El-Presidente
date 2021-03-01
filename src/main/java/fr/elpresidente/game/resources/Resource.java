package fr.elpresidente.game.resources;

import fr.elpresidente.game.builders.LoadFromSaveBuilder;
import org.json.simple.JSONObject;

public interface Resource extends LoadFromSaveBuilder {

    int getSize();

    void setSize(int size);

    void addSize(int size);

    void subtractSize(int size);

    void updateSize(int size);

    int getAnnualYields();

    String getName();

    JSONObject toJSONObject();
}
