package fr.elpresidente.game.resources;

import org.json.simple.JSONObject;

public interface Resource {

    int size = 0;

    int getSize();

    void setSize(int size);

    void addSize(int size);

    int getAnnualYields();

    String getName();

    JSONObject toJSONObject();
}
