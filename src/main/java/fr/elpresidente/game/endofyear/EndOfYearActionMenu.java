package fr.elpresidente.game.endofyear;

public interface EndOfYearActionMenu {

    Object readActionValue();

    void printChoiceMenu();

    void printInvalideChoice(Object invalid_value);

    void printGameStatusAfterAction();
}
