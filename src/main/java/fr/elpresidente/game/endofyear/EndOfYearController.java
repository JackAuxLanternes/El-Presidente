package fr.elpresidente.game.endofyear;

public class EndOfYearController {

    public void callEvents() {
        this.callOptionalEvents();
        this.callReviewEvents();
    }

    public void callOptionalEvents() {
        OptionalEventMenu optionalEventMenu = new OptionalEventMenu();
        optionalEventMenu.choseOptionalEvent();
    }

    public void callReviewEvents() {
        ReviewEvents reviewEventPrinter = new ReviewEvents();
        reviewEventPrinter.applyAllReviewEvents();
    }
}
