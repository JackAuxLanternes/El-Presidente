package fr.elpresidente.game.endofyear.events;

import fr.elpresidente.game.factions.FactionController;
import fr.elpresidente.game.resources.ConsumableController;
import fr.elpresidente.game.resources.ResourcesController;

import java.util.NoSuchElementException;

public interface BalanceSheetEvent {

    void callEvent();
}
