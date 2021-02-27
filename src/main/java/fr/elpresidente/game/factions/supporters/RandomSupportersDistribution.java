package fr.elpresidente.game.factions.supporters;

import fr.elpresidente.game.factions.FactionController;

public class RandomSupportersDistribution implements SupportersDistribution{

    @Override
    public void addSupporters(int number_supporters) {
        FactionController.getInstance().addSupportersRandomly(number_supporters);
    }
}
