package page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class HomePage {
    private static SelenideElement paymentButton = $(".button");
    private static SelenideElement creditButton = $(".button_view_extra");

    public static FormPage paymentPage() {
        paymentButton.click();
        return new FormPage();
    }

    public static FormPage creditPage() {
        creditButton.click();
        return new FormPage();
    }
}
