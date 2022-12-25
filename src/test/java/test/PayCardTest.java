package test;

import data.DataHelper;
import org.junit.jupiter.api.*;

import page.FormPage;
import page.HomePage;
import org.junit.jupiter.api.BeforeEach;
import utils.ApiUtils;
import utils.DBUtil;

import static com.codeborne.selenide.Selenide.open;

public class PayCardTest {

    @BeforeEach
    public void setOpen() {
        open("http://localhost:8080");
        DBUtil.clearingTable("order_entity");
        DBUtil.clearingTable("payment_entity");
    }

    @Test
    public void shouldPayByApprovedCard() {
        HomePage.paymentPage();
        FormPage.setValidApprovedCard();
        FormPage.proceedButton.click();
        FormPage.paySuccess();
        Assertions.assertEquals("APPROVED", DBUtil.getPaymentStatus("payment_entity"));
        Assertions.assertEquals(1L, DBUtil.countOrderIfPayment());
    }

    @Test
    public void shouldPayByDeclinedCard() {
        HomePage.paymentPage();
        FormPage.setValidDeclinedCard();
        FormPage.proceedButton.click();
        FormPage.payError();
        Assertions.assertEquals("DECLINED", DBUtil.getPaymentStatus("payment_entity"));
        Assertions.assertEquals(1L, DBUtil.countOrderIfPayment());
    }

    @Test
    public void shouldSendEmptyForm() {
        HomePage.paymentPage();
        FormPage.proceedButton.click();
        Assertions.assertTrue(FormPage.requiredFieldError());
    }

    @Test
    public void shouldEnterNumberInMonthFrom13Till99() {
        HomePage.paymentPage();
        FormPage.setValidApprovedCard();
        FormPage.setMonth2(DataHelper.getFrom13To99());
        FormPage.proceedButton.click();
        FormPage.errorExpiredCard();
    }

    @Test
    public void shouldEnterLastMonth() {
        HomePage.paymentPage();
        FormPage.setValidApprovedCard();
        FormPage.setMonth2(DataHelper.getLastMonth());
        FormPage.setYear2(DataHelper.getYear());
        FormPage.proceedButton.click();
        FormPage.errorExpiredCard();
    }

    @Test
    public void shouldEnterInvalidYear() {
        HomePage.paymentPage();
        FormPage.setValidApprovedCard();
        FormPage.setYear2(DataHelper.getOneNumber());
        FormPage.proceedButton.click();
        FormPage.errorInvalidFormat();
    }

    @Test
    public void shouldEnterLastYear() {
        HomePage.paymentPage();
        FormPage.setValidApprovedCard();
        FormPage.setYear2(DataHelper.getLastYear());
        FormPage.proceedButton.click();
        FormPage.errorExpiredCard();
    }

    @Test
    public void shouldEnterCyrilicName() {
        HomePage.paymentPage();
        FormPage.setValidApprovedCard();
        FormPage.setOwner2(DataHelper.getInvalidNameCyrilic());
        FormPage.proceedButton.click();
        FormPage.errorInvalidFormat();
    }

    @Test
    public void shouldEnterOneWordInName() {
        HomePage.paymentPage();
        FormPage.setValidApprovedCard();
        FormPage.setOwner2(DataHelper.getInvalidName());
        FormPage.proceedButton.click();
        FormPage.errorInvalidFormat();
    }

    @Test
    public void shouldEnter3WordsInName() {
        HomePage.paymentPage();
        FormPage.setValidApprovedCard();
        FormPage.setOwner2(DataHelper.getInvalid3Names());
        FormPage.proceedButton.click();
        FormPage.errorInvalidFormat();
    }

    @Test
    public void shouldBeNumbersInName() {
        HomePage.paymentPage();
        FormPage.setValidApprovedCard();
        FormPage.setOwner2(DataHelper.getInvalidNameWithNumber());
        FormPage.proceedButton.click();
        FormPage.errorInvalidFormat();
    }

    @Test
    public void shouldBeSymbolsInName() {
        HomePage.paymentPage();
        FormPage.setValidApprovedCard();
        FormPage.setOwner2(DataHelper.getInvalidName() + " " + " \"[|]`~<!--@/*$%^&#*/()?>,.*/\\");
        FormPage.proceedButton.click();
        FormPage.errorInvalidFormat();
    }

    @Test
    public void shouldBe2NumbersInCvc() {
        HomePage.paymentPage();
        FormPage.setValidApprovedCard();
        FormPage.setCVC2(DataHelper.getOneNumber() + DataHelper.getOneNumber());
        FormPage.proceedButton.click();
        FormPage.errorInvalidFormat();
    }

}

