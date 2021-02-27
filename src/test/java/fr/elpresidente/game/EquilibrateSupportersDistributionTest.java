package fr.elpresidente.game;

import fr.elpresidente.game.factions.FactionController;
import fr.elpresidente.game.factions.supporters.EquilibrateSupportersDistribution;
import fr.elpresidente.game.factions.supporters.SupportersDistribution;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;


public class EquilibrateSupportersDistributionTest extends TestCase {

    private SupportersDistribution supportersDistribution;
    private FactionController factionController;
    private int number_supporters_by_faction;

    @Before
    public void setUp(){

        this.number_supporters_by_faction = 8;
        int satisfaction_by_faction = 50;
        this.supportersDistribution = new EquilibrateSupportersDistribution();
        this.factionController = FactionController.getInstance();
        this.factionController.initFactions(number_supporters_by_faction, satisfaction_by_faction);
        this.factionController.getFactionFromNameFaction("capitalist").setSatisfaction(80);
        this.factionController.getFactionFromNameFaction("communist").setSatisfaction(40);
        this.factionController.getFactionFromNameFaction("ecologist").setSatisfaction(70);
        this.factionController.getFactionFromNameFaction("liberal").setSatisfaction(0);
        this.factionController.getFactionFromNameFaction("militarist").setSatisfaction(0);
    }

    @Test
    public void testEquilibrateSupportersDistributionWithAValidSatisfactionFor6Factions() {
        int total_new_supporters_add = 8;
        this.supportersDistribution.addSupporters(total_new_supporters_add);

        assertEquals(10, this.factionController.getFactionFromNameFaction("capitalist").getSupporters());
        assertEquals(10, this.factionController.getFactionFromNameFaction("ecologist").getSupporters());
        assertEquals(10, this.factionController.getFactionFromNameFaction("loyalist").getSupporters());
        assertEquals(10, this.factionController.getFactionFromNameFaction("nationalist").getSupporters());
        assertEquals(8, this.factionController.getFactionFromNameFaction("communist").getSupporters());
        assertEquals(8, this.factionController.getFactionFromNameFaction("religious").getSupporters());
        assertEquals(this.factionController.getFactions().size() * this.number_supporters_by_faction + total_new_supporters_add, this.factionController.determineTotalSupporters());
    }
}
