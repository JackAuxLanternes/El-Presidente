package fr.elpresidente.game;

import fr.elpresidente.game.endofyear.events.AgricultureSurplus;
import fr.elpresidente.game.endofyear.events.BalanceSheetEvent;
import fr.elpresidente.game.endofyear.events.Foodbalancing;
import fr.elpresidente.game.factions.FactionController;
import fr.elpresidente.game.resources.ConsumableController;
import fr.elpresidente.game.resources.ResourcesController;
import junit.framework.TestCase;

public class AgricultureSurplusTest extends TestCase {

    public void testIfAgricultureIsInSurplus()
    {
        BalanceSheetEvent agricultureSurplus = new AgricultureSurplus();
        FactionController factionController = FactionController.getInstance();
        factionController.initFactions(10, 50);
        int faction_initial_number_supporters = factionController.determineTotalSupporters();
        int value_to_add_to_supporter_to_get_the_max =  factionController.determineTotalSupporters() * 10 / 100;
        ResourcesController resourcesController = ResourcesController.getInstance();
        resourcesController.getAgriculture().setSize(51);
        resourcesController.getIndustry().setSize(48);

        agricultureSurplus.callEvent();
        assertTrue( faction_initial_number_supporters + value_to_add_to_supporter_to_get_the_max >= factionController.determineTotalSupporters());
        assertTrue( faction_initial_number_supporters <= factionController.determineTotalSupporters());
    }

    public void testIfAgricultureIsNotInSurplus()
    {
        BalanceSheetEvent agricultureSurplus = new AgricultureSurplus();
        FactionController factionController = FactionController.getInstance();
        factionController.initFactions(10, 50);
        int faction_initial_number_supporters = factionController.determineTotalSupporters();
        ResourcesController resourcesController = ResourcesController.getInstance();
        resourcesController.getAgriculture().setSize(48);
        resourcesController.getIndustry().setSize(51);

        agricultureSurplus.callEvent();
        assertEquals(  true,faction_initial_number_supporters == factionController.determineTotalSupporters());

    }
}
