package page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class HomePage {
    private final SelenideElement paymentButton = $(".button");
    private final SelenideElement creditButton = $(".button_view_extra");
    public final SelenideElement nameTypeOfTransaction = $("button~h3");

    public FormPage paymentPage() {
        this.paymentButton.click();
        nameTypeOfTransaction.shouldHave(text("Оплата по карте")).shouldBe(visible);
        return new FormPage();
    }

    public FormPage creditPage() {
        this.creditButton.click();
        nameTypeOfTransaction.shouldHave(text("Кредит по данным карты")).shouldBe(visible);
        return new FormPage();
    }
}
