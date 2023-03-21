package page;

import com.codeborne.selenide.SelenideElement;
import data.DataHelper;
import lombok.Data;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@Data

public class FormPage {
    private final SelenideElement cardField = $$("input").get(0);
    public final SelenideElement monthField = $$("input").get(1);
    public final SelenideElement yearField = $$("input").get(2);
    public final SelenideElement ownerField = $$("input").get(3);
    public final SelenideElement cvcField = $$("input").get(4);
    public final SelenideElement proceedButton = $(".form-field .button");
    private final SelenideElement textSuccess = $(".notification_status_ok");
    private final SelenideElement textError = $(".notification_status_error");
    private final SelenideElement textErrorInvalidFormat = $(".input__sub");
    private final List<SelenideElement> textErrorInvalidFormatList = $$(".input__sub");

    public void paySuccess() {
        textSuccess.shouldHave(text("Операция одобрена Банком."), Duration.ofSeconds(15)).shouldBe(visible);
    }

    public void payError() {
        textError.shouldHave(text("Ошибка! Банк отказал в проведении операции."), Duration.ofSeconds(15)).shouldBe(visible);
    }

    public void errorInvalidFormat() {
        textErrorInvalidFormat.shouldHave(text("Неверный формат"), Duration.ofSeconds(2)).shouldBe(visible);
    }

    public void errorExpiredCard() {
        textErrorInvalidFormat.shouldHave(text("Истёк срок действия карты"), Duration.ofSeconds(2)).shouldBe(visible);
    }

    public void errorInvalidExpirationDate() {
        textErrorInvalidFormat.shouldHave(text("Неверно указан срок действия карты"), Duration.ofSeconds(2)).shouldBe(visible);
    }

    public void errorInRequiredField() {
        for (SelenideElement element : textErrorInvalidFormatList) {
            element.shouldHave(text("Поле обязательно для заполнения"), Duration.ofSeconds(2)).shouldBe(visible);
        }
    }

    private void cleanField(SelenideElement fieldName) {
        fieldName.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME, Keys.BACK_SPACE));
    }
    public void setCard(String card) {
        cardField.setValue(card);
    }

    public void setMonth(String month) {
        cleanField(monthField);
        monthField.setValue(month);
    }

    public void setYear(String year) {
        cleanField(yearField);
        yearField.setValue(year);
    }

    public void setOwner(String owner) {
        cleanField(ownerField);
        ownerField.setValue(owner);
    }

    public void  setCVC(String cvc) {
        cleanField(cardField);
        cvcField.setValue(cvc);
    }

    public void setValidCard(String card) {
        this.setCard(card);
        this.setMonth(DataHelper.getMonth(0));
        this.setYear(DataHelper.getYear(1));
        this.setOwner(DataHelper.getValidName());
        this.setCVC(DataHelper.getValidCVC());
    }

}
