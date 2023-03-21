package test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utils.ApiUtils;

public class APITest {

    @BeforeAll
    static  void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }
    @Test
    public void shouldPayWithApprovedCardAPI() {
        ApiUtils.shouldSendRequestApprovedCard();
    }

    @Test
    public void shouldPayWithDeclinedCardAPI() {
        ApiUtils.shouldSendRequestDeclinedCard();
    }

    @Test
    public void shouldCreditWithApprovedCardAPI() {
        ApiUtils.shouldSendCreditRequestApprovedCard();
    }

    @Test
    public void shouldCreditWithDeclinedCardAPI() {
        ApiUtils.shouldSendCreditRequestDeclinedCard();
    }
}
