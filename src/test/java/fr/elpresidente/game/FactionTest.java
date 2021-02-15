package fr.elpresidente.game;

import fr.elpresidente.game.factions.Faction;
import fr.elpresidente.game.factions.FactionController;
import junit.framework.TestCase;

public class FactionTest extends TestCase {

    public void testAddSupportersOfTheOnlyFactionSuperiorThan0() {
        FactionController factionController = FactionController.getInstance();
        factionController.initFactions(0, 10);
        Faction faction = factionController.getFactionFromNameFaction("capitalist");
        faction.setSupporters(10);

        factionController.addSupportersRandomly(2);


        assertEquals(12, faction.getSupporters());
    }

    public void testAddSupportersOfAFactionWith0Supporter() {
        FactionController factionController = FactionController.getInstance();
        factionController.initFactions(0, 10);
        Faction faction = factionController.getFactionFromNameFaction("capitalist");

        faction.addSupporter(2);


        assertEquals(0, faction.getSupporters());
    }

    public void testSubstractSupportersOfAFactionWith0Supporters() {

        FactionController.resetInstance();
        FactionController factionController = FactionController.getInstance();
        factionController.initFactions(0, 10);
        Faction faction = factionController.getFactionFromNameFaction("capitalist");

        faction.subtractSupporter(2);


        assertEquals(0, faction.getSupporters());
    }

    public void testAddSatisfactionToAFactionWith0Satisfaction() {
        FactionController factionController = FactionController.getInstance();
        factionController.initFactions(10, 0);
        Faction faction = factionController.getFactionFromNameFaction("capitalist");

        faction.addSatisfaction(2);


        assertEquals(0, faction.getSatisfaction());
    }

    public void testAddSatisfactionToAFactionWith100Satisfaction() {
        FactionController factionController = FactionController.getInstance();
        factionController.initFactions(10, 0);
        Faction faction = factionController.getFactionFromNameFaction("capitalist");
        faction.setSatisfaction(100);

        faction.addSatisfaction(2);


        assertEquals(100, faction.getSatisfaction());
    }
}
