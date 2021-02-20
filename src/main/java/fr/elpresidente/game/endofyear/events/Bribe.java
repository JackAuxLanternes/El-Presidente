package fr.elpresidente.game.endofyear.events;

import fr.elpresidente.game.factions.Faction;
import fr.elpresidente.game.factions.FactionController;
import fr.elpresidente.game.resources.ConsumableController;

public class Bribe {


    private FactionController factionController;

    private ConsumableController consumableController;

    public Bribe() {
        this.factionController = FactionController.getInstance();
        this.consumableController = ConsumableController.getInstance();
    }

    public void bribeFaction(String name_faction) {

        Faction faction = factionController.getFactionFromNameFaction(name_faction);

        System.out.println("nombre de supporters " + faction.getSupporters());
        this.payThePriceForFactionsupporters(faction.getSupporters());
        this.addSatisfactionPercentageToTheFaction(faction);
    }

    private void payThePriceForFactionsupporters(int number_supporters) {

        int price_payed_for_one_supporter = 15;
        this.consumableController.getTreasury().subtractAmount(price_payed_for_one_supporter * number_supporters);
    }

    private void addSatisfactionPercentageToTheFaction(Faction faction) {
        int satisfaction_percentage_add_for_one_payement = 10;
        faction.addSatisaction(satisfaction_percentage_add_for_one_payement);
    }

    public FactionController getFactionController() {
        return factionController;
    }

    public ConsumableController getConsumableController() {
        return consumableController;
    }
}
