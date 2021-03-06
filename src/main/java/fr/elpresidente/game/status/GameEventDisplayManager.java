package fr.elpresidente.game.status;

import fr.elpresidente.game.events.Event;
import fr.elpresidente.game.events.EventController;
import fr.elpresidente.game.tools.UserIOTools;

public class GameEventDisplayManager {

    private Event event;

    private int choicesSize;

    public void showEvent() {
        this.setEventInfo();
        this.printHeadline();
        this.printEventChoices();
        this.applySelectedEvent();
    }

    private void printHeadline() {
        System.out.println("===== El-Présigaro =====");
        System.out.println("📰 " + event.getDescription());
    }

    private void printEventChoices() {
        for (int i = 0; i < choicesSize; i++) {
            System.out.println("===");
            System.out.println((i + 1) + " → " + event.getChoicesName().get(i));
        }
        System.out.println("===");
    }

    private void applySelectedEvent() {
        event.processEffectForChoice(UserIOTools.readUserChoice(1, this.event.getChoicesName().size()) - 1);
    }

    private void setEventInfo() {
        this.event = EventController.getInstance().getCurrentEvent();
        this.choicesSize = this.event.getChoicesName().size();
    }
}
