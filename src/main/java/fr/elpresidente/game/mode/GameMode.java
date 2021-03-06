package fr.elpresidente.game.mode;

import fr.elpresidente.game.turn.Seasons;
import org.json.simple.JSONObject;

public interface GameMode
{
    void setEvent(int year, Seasons season);

    JSONObject toJSONObject();

    String toString();
}
