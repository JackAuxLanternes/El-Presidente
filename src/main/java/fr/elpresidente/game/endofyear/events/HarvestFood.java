package fr.elpresidente.game.endofyear.events;

import fr.elpresidente.game.resources.consumable.ConsumableController;
import fr.elpresidente.game.resources.resource.ResourcesController;

public class HarvestFood implements BalanceSheetEvent {

    @Override
    public void callEvent() {
        this.harvestFoodFromAgriculture();
    }

    private void harvestFoodFromAgriculture() {
        int food_amount_generate_by_agriculture = ResourcesController.getInstance().getAgriculture().getAnnualYields();
        ConsumableController.getInstance().getFood().addAmount(food_amount_generate_by_agriculture);
    }
}
