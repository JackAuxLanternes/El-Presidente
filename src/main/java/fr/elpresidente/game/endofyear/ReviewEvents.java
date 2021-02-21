package fr.elpresidente.game.endofyear;

import fr.elpresidente.game.endofyear.events.*;

 class ReviewEvents {


    public void applyAllReviewEvents() {

        this.printReviewEvents();
        BalanceSheetEvent agricultureSurplus = new AgricultureSurplus();
        BalanceSheetEvent foodBalancing = new FoodBalancing();
        BalanceSheetEvent harvestFood = new HarvestFood();
        BalanceSheetEvent earnMoney = new EarnMoney();

        harvestFood.callEvent();
        earnMoney.callEvent();
        agricultureSurplus.callEvent();
        foodBalancing.callEvent();
    }

    private void printReviewEvents() {
        System.out.println("==============================");
        System.out.println("=== Bienvenue aux Bilan de l'année");
        System.out.println("=== C'est le moment de l'année où l'on va");
        System.out.println("=== Comptabiliser l'argent que va nous rapporter l'Industry, la nourriture que va nous rapporter l'Agriculture");
        System.out.println("=== Distribué de la nourriture à la population");
        System.out.println("=== Supprimer des Partisans si on a pas assez de nourriture");
        System.out.println("=== Rajouter des Partisans si en revanche on est en excès de nourriture");
    }


}
