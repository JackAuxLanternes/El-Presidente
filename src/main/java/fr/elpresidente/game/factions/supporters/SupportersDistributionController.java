package fr.elpresidente.game.factions.supporters;

public class SupportersDistributionController {

    private static SupportersDistributionController instance;

    private SupportersDistribution supportersDistribution;


    public static SupportersDistributionController getInstance() {
        if (isInstanceNotInitialized()) {
            instance = new SupportersDistributionController();
        }

        return instance;
    }

    private static Boolean isInstanceNotInitialized() {
        return instance == null;
    }

    public static void resetInstance() {
        if (instance != null) {
            instance = null;
        }
    }

    public void setSupportersDistributionFromJSONName(String name_distribution) {
        if (name_distribution.equals("equilibrate")) {
            this.setSupportersDistribution(new EquilibrateSupportersDistribution());
        } else {
            this.setSupportersDistribution(new RandomSupportersDistribution());
        }
    }

    public SupportersDistribution getSupportersDistribution() {
        return supportersDistribution;
    }

    public void setSupportersDistribution(SupportersDistribution supportersDistribution) {
        this.supportersDistribution = supportersDistribution;
    }
}
