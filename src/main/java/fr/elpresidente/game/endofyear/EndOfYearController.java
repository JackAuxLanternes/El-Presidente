package fr.elpresidente.game.endofyear;

public class EndOfYearController {

    public void callEvents(){
        this.callOptionnalEvents();
        this.callReviewEvents();
    }

    public void callOptionnalEvents() {
        OptionalEventMenu optionalEventMenu = new OptionalEventMenu();
        optionalEventMenu.choseOptionalEvent();
    }

    public void callReviewEvents() {
        ReviewEvents reviewEventPrinter = new ReviewEvents();
        reviewEventPrinter.applyAllReviewEvents();
    }
}
