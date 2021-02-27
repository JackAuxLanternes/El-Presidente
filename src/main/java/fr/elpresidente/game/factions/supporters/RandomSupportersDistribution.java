package fr.elpresidente.game.factions.supporters;

import fr.elpresidente.game.factions.Faction;
import fr.elpresidente.game.factions.FactionController;

public class RandomSupportersDistribution implements SupportersDistribution{

    @Override
    public void addSupporters(int number_supporters) {
        for (int i = 0; i < number_supporters; i++) {
            this.addSupporterRandomFaction();
        }
    }

    private void addSupporterRandomFaction() {

        int random_index_faction;
        Faction random_faction;

        do {
            random_index_faction = FactionController.getInstance().getRandomFactionIndex();
            random_faction = FactionController.getInstance().getFactions().get(random_index_faction);
        } while (random_faction.getSupporters() <= 0);

        random_faction.addSupporter(1);
    }
}
