package fr.elpresidente.game;

import fr.elpresidente.game.factions.Faction;
import fr.elpresidente.game.factions.FactionController;
import fr.elpresidente.game.factions.supporters.SupportersDistribution;
import fr.elpresidente.game.factions.supporters.SupportersDistributionController;
import junit.framework.TestCase;

public class FactionTest extends TestCase {

    /*TODO Bouger ce test d'ici*/
    public void testAddSupportersOfTheOnlyFactionSuperiorThan0() {
        FactionController factionController = FactionController.getInstance();
        factionController.initFactions(0, 10);
        Faction faction = factionController.getFactionFromNameFaction("capitalist");
        faction.setSupporters(10);
        SupportersDistributionController.getInstance().setSupportersDistributionFromJSONName("random");
        SupportersDistributionController.getInstance().getSupportersDistribution().addSupporters(2);


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

        faction.removeSupporter(2);


        assertEquals(0, faction.getSupporters());
    }

    public void testAddSatisfactionToAFactionWith0Satisfaction() {
        FactionController factionController = FactionController.getInstance();
        factionController.initFactions(10, 0);
        Faction faction = factionController.getFactionFromNameFaction("capitalist");

        faction.updateSatisfaction(2);


        assertEquals(0., faction.getSatisfaction());
    }

    public void testAddSatisfactionToAFactionWith100Satisfaction() {
        FactionController factionController = FactionController.getInstance();
        factionController.initFactions(10, 0);
        Faction faction = factionController.getFactionFromNameFaction("capitalist");
        faction.setSatisfaction(100);

        faction.updateSatisfaction(2);


        assertEquals(100., faction.getSatisfaction());
    }
}
