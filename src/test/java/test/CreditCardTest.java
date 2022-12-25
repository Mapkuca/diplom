package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import page.FormPage;
import page.HomePage;
import utils.DBUtil;

import static com.codeborne.selenide.Selenide.open;

public class CreditCardTest {

    @BeforeEach
    public void setting() {
        open("http://localhost:8080");
        DBUtil.clearingTable("order_entity");
        DBUtil.clearingTable("credit_request_entity");
    }

    @Test
    public void shouldSuccessCreditWithApprovedCard() {
        HomePage.creditPage();
        FormPage.setValidApprovedCard();
        FormPage.proceedButton.click();
        FormPage.paySuccess();
        Assertions.assertEquals("APPROVED", DBUtil.getPaymentStatus("credit_request_entity"));
        Assertions.assertEquals(1L, DBUtil.countOrderIfCredit());
    }

    @Test
    public void shouldDeclineCredit() {
        HomePage.creditPage();
        FormPage.setValidDeclinedCard();
        FormPage.proceedButton.click();
        FormPage.payError();
        Assertions.assertEquals("DECLINED", DBUtil.getPaymentStatus("payment_entity"));
        Assertions.assertEquals(1L, DBUtil.countOrderIfCredit());
    }
}
