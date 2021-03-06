package fr.elpresidente.game.endofyear.events;

import fr.elpresidente.game.resources.consumable.ConsumableController;
import fr.elpresidente.game.resources.resource.ResourcesController;

public class EarnMoney implements BalanceSheetEvent {

    @Override
    public void callEvent() {
        this.generateMoneyFormIndustry();
    }

    private void generateMoneyFormIndustry() {
        int money_amount_generate_by_industry = ResourcesController.getInstance().getIndustry().getAnnualYields();
        ConsumableController.getInstance().getTreasury().addAmount(money_amount_generate_by_industry);
    }
}
