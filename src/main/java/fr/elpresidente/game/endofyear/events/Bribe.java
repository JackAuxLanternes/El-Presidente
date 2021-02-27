package fr.elpresidente.game.endofyear.events;

import fr.elpresidente.game.factions.Faction;
import fr.elpresidente.game.factions.FactionController;
import fr.elpresidente.game.resources.ConsumableController;

public class Bribe {

    private final String[] FACTION_THAT_DOES_NOT_LIKE_BRIBE = {"loyalist"};


    public void bribeFaction(String name_faction) {

        Faction faction = FactionController.getInstance().getFactionFromNameFaction(name_faction);
        int amount_payed_for_numbers_supporters = this.getThePriceForNumberSupporters(faction.getSupporters());
        if(weCanBribeThisFaction(faction, amount_payed_for_numbers_supporters)) {
            this.payThePriceForFactionSupporters(amount_payed_for_numbers_supporters);
            this.addSatisfactionPercentageToTheFaction(faction);
            this.reduceSatisfactionForFactionThatDoesNotLikeBribe(amount_payed_for_numbers_supporters);
        }else {
            System.out.println(this.factionToBribeDoesNotHaveMaximumSatisfaction(faction));
            System.out.println(this.weHaveEnoughMoneyToBribeThisFaction(amount_payed_for_numbers_supporters));
            System.out.println(this.factionDoesLikeBribe(faction));
            System.out.println(ConsumableController.getInstance().getTreasury().getAmount());
            this.printRulesToBribeFaction();
            this.printFactionsThatDoesNotLikeBribe();
            this.printMoneyAndSatisfactionStatus(faction);
        }
    }

    private void payThePriceForFactionSupporters(int amount_payed_for_numbers_supporters) {
        System.out.println(amount_payed_for_numbers_supporters);
        ConsumableController.getInstance().getTreasury().subtractAmount(amount_payed_for_numbers_supporters);
    }

    private boolean weCanBribeThisFaction(Faction faction, int amount_payed_for_numbers_supporters) {
        return this.factionToBribeDoesNotHaveMaximumSatisfaction(faction)
                && this.weHaveEnoughMoneyToBribeThisFaction(amount_payed_for_numbers_supporters)
                && this.factionDoesLikeBribe(faction);
    }

    private boolean factionToBribeDoesNotHaveMaximumSatisfaction(Faction faction) {

        return faction.getSatisfaction() < faction.getMAXIMUM_SATISFACTION();
    }

    private boolean weHaveEnoughMoneyToBribeThisFaction(int amount_payed_for_numbers_supporters) {
        return ConsumableController.getInstance().getTreasury().getAmount() > amount_payed_for_numbers_supporters;
    }

    private int getThePriceForNumberSupporters(int number_supporters) {
        int price_payed_for_one_supporter = 15;
        return price_payed_for_one_supporter * number_supporters;
    }

    private void addSatisfactionPercentageToTheFaction(Faction faction) {
        int satisfaction_percentage_add_for_one_payement = 10;
        faction.addSatisfaction(satisfaction_percentage_add_for_one_payement);
    }

    private void reduceSatisfactionForFactionThatDoesNotLikeBribe(int amount_payed_for_numbers_supporters) {
        int amount_divided_for_price_to_calculate_satisfaction = 10;
        for(String name_faction: this.FACTION_THAT_DOES_NOT_LIKE_BRIBE) {
            FactionController.getInstance().getFactionFromNameFaction(name_faction).removeSatisfaction(amount_payed_for_numbers_supporters / amount_divided_for_price_to_calculate_satisfaction);
        }
    }

    private boolean factionDoesLikeBribe(Faction faction) {
        for(String faction_name_doesnt_like_bribe: this.FACTION_THAT_DOES_NOT_LIKE_BRIBE) {
            if (faction_name_doesnt_like_bribe == faction.getName())
                return false;
        }
        return true;
    }

    private void printFactionsThatDoesNotLikeBribe() {
        for(String faction_name: this.FACTION_THAT_DOES_NOT_LIKE_BRIBE) {
            System.out.println(faction_name + " ");
        }
    }

    private void printRulesToBribeFaction() {
        System.out.println("La satisfaction de la faction à laquelle vous voulez donner un pot de vin ne doit pas dépasser 100% \n" +
                "Vous devez avoir assez d'argent pour payer chaque partisan de la faction \n" +
                "Pour rappel vous ne pouvez pas donner un pot de vin aux factions");
    }

    private void printMoneyAndSatisfactionStatus(Faction faction) {
        System.out.println("Vous avez exactement " + ConsumableController.getInstance().getTreasury().getAmount() + "€ et "
                + faction.getSatisfaction() + "% de satisfaction pour cette faction");
    }

    public String[] getFACTION_THAT_DOES_NOT_LIKE_BRIBE() {
        return FACTION_THAT_DOES_NOT_LIKE_BRIBE;
    }
}
