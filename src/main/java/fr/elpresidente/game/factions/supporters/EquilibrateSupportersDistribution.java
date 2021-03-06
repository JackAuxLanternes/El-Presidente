package fr.elpresidente.game.factions.supporters;

import fr.elpresidente.game.factions.Faction;
import fr.elpresidente.game.factions.FactionController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EquilibrateSupportersDistribution implements SupportersDistribution {

    private List<Faction> valid_factions;
    private Map<Faction, Integer> factions_add_supporters_number;

    @Override
    public void addSupporters(int total_new_supporters) {
        this.setAlgorithmsParameters(total_new_supporters);

        this.valid_factions.forEach(faction -> {
            if (this.factions_add_supporters_number.get(faction) > 0)
                faction.addSupporter(this.factions_add_supporters_number.get(faction));
        });
    }

    private void setAlgorithmsParameters(int total_new_supporters) {
        this.setValid_factions();
        this.sortValidFactionsInDescendingOrderOfSatisfaction();
        this.setFactions_add_supporters_number(total_new_supporters);
        this.equilibrateNewSupportersNumberByFaction(total_new_supporters);
    }

    private void setFactions_add_supporters_number(int total_new_supporters) {
        this.factions_add_supporters_number = new HashMap<>();
        this.valid_factions.forEach(faction ->
                this.factions_add_supporters_number.put(faction, this.getNumberOfNewSupportersForThisFaction(faction, total_new_supporters))
        );
    }

    private void equilibrateNewSupportersNumberByFaction(int total_new_supporters) {
        for (Faction faction : this.valid_factions) {
            if (!this.newSupportersForThisFactionInferiorMaximumAuthorized(faction)) {

                this.factions_add_supporters_number.replace(faction, this.getMaxNewSupportersForThisFaction(faction));
            }

            total_new_supporters = this.modifyTotalNeWSupportersForAFactionAccordingSupportersUsed(total_new_supporters, faction);
        }
    }

    private int modifyTotalNeWSupportersForAFactionAccordingSupportersUsed(int total_new_supporters, Faction faction) {

        if (total_new_supporters < this.factions_add_supporters_number.get(faction)) {

            this.factions_add_supporters_number.replace(faction, total_new_supporters);
        }
        return total_new_supporters - this.factions_add_supporters_number.get(faction);
    }

    private boolean newSupportersForThisFactionInferiorMaximumAuthorized(Faction faction) {
        return this.getMaxNewSupportersForThisFaction(faction) > this.factions_add_supporters_number.get(faction);
    }


    private List<Faction> getAllFactionsWithMoreThanMinSupportersMinSatisfaction() {
        return FactionController.getInstance().getFactions()
                .stream()
                .filter(faction -> faction.getSupporters() > faction.getMINIMUM_SATISFACTION() && faction.getSatisfaction() > faction.getMINIMUM_SATISFACTION())
                .collect(Collectors.toList());
    }

    private void setValid_factions() {
        this.valid_factions = this.getAllFactionsWithMoreThanMinSupportersMinSatisfaction();
    }

    private void sortValidFactionsInDescendingOrderOfSatisfaction() {

        boolean flag = true;
        while (flag) {
            flag = false;
            for (int i = 0; i < this.valid_factions.size() - 1; i++) {
                if (this.valid_factions.get(i).getSatisfaction() < this.valid_factions.get(i + 1).getSatisfaction()) {
                    Faction faction_tmp = this.valid_factions.get(i + 1);
                    this.valid_factions.set(i + 1, this.valid_factions.get(i));
                    this.valid_factions.set(i, faction_tmp);
                    flag = true;
                }
            }
        }
    }


    private int getNumberOfNewSupportersForThisFaction(Faction faction, int total_new_supporters) {
        int number_percent_of_new_supporter = (int) Math.ceil(faction.getSatisfaction() / this.getOnePercentOfTheValidFactionsSatisfaction());
        return (int) Math.ceil(this.getOnePercentOfTheNewSupporters(total_new_supporters) * number_percent_of_new_supporter);
    }

    private int getMaxNewSupportersForThisFaction(Faction faction) {
        return (int) Math.ceil((float) faction.getSupporters() / 2);
    }

    private double getOnePercentOfTheValidFactionsSatisfaction() {
        return this.valid_factions.stream()
                .mapToDouble(Faction::getSatisfaction)
                .sum() / 100;

    }

    private double getOnePercentOfTheNewSupporters(int total_number_supporter) {
        return (double) total_number_supporter / 100;

    }
}
