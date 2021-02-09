package fr.elpresidente.game.endofyear.events;

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

        int number_supporters = factionController.getFactionFromNameFaction(name_faction).getSupporters();

        this.payThePriceForFactionsupporters(number_supporters);
        this.addSatisfactionPercentageToTheFaction(name_faction, number_supporters);
    }

    private void payThePriceForFactionsupporters(int number_supporters) {

        int price_payed_for_one_supporter = 15;
        this.consumableController.getTreasury().subtractAmount(price_payed_for_one_supporter * number_supporters);
    }

    private void addSatisfactionPercentageToTheFaction(String name_faction, int number_supporters) {
        int satisfaction_percentage_add_for_one_payement = 10;
        factionController.addSatisfactionPercentageForOneFaction(name_faction, satisfaction_percentage_add_for_one_payement);
    }

    public FactionController getFactionController() {
        return factionController;
    }

    public ConsumableController getConsumableController() {
        return consumableController;
    }
}
