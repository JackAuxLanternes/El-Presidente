package fr.elpresidente.game.endofyear.events;

import fr.elpresidente.game.factions.Faction;
import fr.elpresidente.game.factions.FactionController;
import fr.elpresidente.game.resources.ConsumableController;

import java.util.Arrays;
import java.util.List;

public class Bribe {


    private final FactionController factionController;

    private final ConsumableController consumableController;


    private final String[] FACTION_THAT_DOESNT_LIKE_BRIBE = {"loyalist"};

    public Bribe() {
        this.factionController = FactionController.getInstance();
        this.consumableController = ConsumableController.getInstance();
    }

    public void bribeFaction(String name_faction) {

        Faction faction = factionController.getFactionFromNameFaction(name_faction);
        int amount_payed_for_numbers_supporters = this.getThePriceForNumberSupporters(faction.getSupporters());
        if(weCanBribeThisFaction(faction, amount_payed_for_numbers_supporters)) {
            this.payThePriceForFactionsupporters(amount_payed_for_numbers_supporters);
            this.addSatisfactionPercentageToTheFaction(faction);
            this.reduceSatisfactionForFactionThatDoesntLikeBribe(amount_payed_for_numbers_supporters);
        }else {
            this.printRulesToBribeFaction();
            this.printFactionsThatDoesntLikeBribe();
            this.printMoneyAndSatisfactionStatus(faction);
        }
    }

    private void payThePriceForFactionsupporters(int amount_payed_for_numbers_supporters) {
            this.consumableController.getTreasury().subtractAmount(amount_payed_for_numbers_supporters);
    }

    private boolean weCanBribeThisFaction(Faction faction, int amount_payed_for_numbers_supporters) {
        return this.factionToBribeDoesntHaveMaximumSatisfaction(faction)
                && this.weHaveEnoughMoneyToBribeThisFaction(amount_payed_for_numbers_supporters)
                && this.factionDoesLikeBribe(faction);
    }

    private boolean factionToBribeDoesntHaveMaximumSatisfaction(Faction faction) {

        return faction.getSatisfaction() < faction.getMAXIMUM_SATISFACTION();
    }

    private boolean weHaveEnoughMoneyToBribeThisFaction(int amount_payed_for_numbers_supporters) {
        return this.consumableController.getTreasury().getAmount() > amount_payed_for_numbers_supporters;
    }

    private int getThePriceForNumberSupporters(int number_supporters) {
        int price_payed_for_one_supporter = 15;
        return price_payed_for_one_supporter * number_supporters;
    }

    private void addSatisfactionPercentageToTheFaction(Faction faction) {
        int satisfaction_percentage_add_for_one_payement = 10;
        faction.addSatisaction(satisfaction_percentage_add_for_one_payement);
    }

    private void reduceSatisfactionForFactionThatDoesntLikeBribe(int amount_payed_for_numbers_supporters) {
        int amount_divided_for_price_to_calculate_satisfaction = 10;
        for(String name_faction: this.FACTION_THAT_DOESNT_LIKE_BRIBE) {
            this.factionController.getFactionFromNameFaction(name_faction).removeSatisfaction(amount_payed_for_numbers_supporters / amount_divided_for_price_to_calculate_satisfaction);
        }
    }

    private boolean factionDoesLikeBribe(Faction faction) {
        for(String faction_name_doesnt_like_bribe: this.FACTION_THAT_DOESNT_LIKE_BRIBE) {
            if (faction_name_doesnt_like_bribe == faction.getName())
                return false;
        }
        return true;
    }

    private void printFactionsThatDoesntLikeBribe() {
        for(String faction_name: this.FACTION_THAT_DOESNT_LIKE_BRIBE) {
            System.out.println(faction_name + " ");
        }
    }

    private void printRulesToBribeFaction() {
        System.out.println("La satisfaction de la faction à laquelle vous voulez donner un pot de vin ne doit pas dépasser 100% \n" +
                "Vous devez avoir assez d'argent pour payer chaque partisan de la faction \n" +
                "Pour rappel vous ne pouvez pas donner un pot de vin aux factions");
    }

    private void printMoneyAndSatisfactionStatus(Faction faction) {
        System.out.println("Vous avez exactement " + this.consumableController.getTreasury().getAmount() + "€ et "
                + faction.getSatisfaction() + "% de satisfaction pour cette faction");
    }

    public FactionController getFactionController() {
        return factionController;
    }

    public ConsumableController getConsumableController() {
        return consumableController;
    }

    public String[] getFACTION_THAT_DOESNT_LIKE_BRIBE() {
        return FACTION_THAT_DOESNT_LIKE_BRIBE;
    }
}
