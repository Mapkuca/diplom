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
    HomePage homePage = new HomePage();

    @BeforeEach
    public void setting() {
        open(DataHelper.getURL());
        DBUtil.clearingTable("order_entity");
        DBUtil.clearingTable("payment_entity");
    }

    @Test
    public void shouldPayByApprovedCard() {
        FormPage formPage = homePage.paymentPage();
        formPage.setValidCard(DataHelper.getApprovedCardNumber());
        formPage.proceedButton.click();
        formPage.paySuccess();
        Assertions.assertEquals("APPROVED", DBUtil.getPaymentStatus("payment_entity"));
        Assertions.assertEquals(1L, DBUtil.countOrderIfPayment());
    }

    @Test
    public void shouldPayByDeclinedCard() {
        FormPage formPage = homePage.paymentPage();
        formPage.setValidCard(DataHelper.getDeclinedCardNumber());
        formPage.proceedButton.click();
        formPage.payError();
        Assertions.assertEquals("DECLINED", DBUtil.getPaymentStatus("payment_entity"));
        Assertions.assertEquals(1L, DBUtil.countOrderIfPayment());
    }

    @Test
    public void shouldSendEmptyForm() {
        FormPage formPage = homePage.paymentPage();
        formPage.proceedButton.click();
        formPage.errorInRequiredField();
    }

    @Test
    public void shouldEnterNumberInMonthFrom13Till99() {
        FormPage formPage = homePage.paymentPage();
        formPage.setValidCard(DataHelper.getApprovedCardNumber());
        formPage.setMonth(DataHelper.getFrom13To99());
        formPage.proceedButton.click();
        formPage.errorExpiredCard();
    }

    @Test
    public void shouldEnterLastMonth() {
        FormPage formPage = homePage.paymentPage();
        formPage.setValidCard(DataHelper.getApprovedCardNumber());
        formPage.setMonth(DataHelper.getMonth(-1));
        formPage.setYear(DataHelper.getYear(0));
        formPage.proceedButton.click();
        formPage.errorInvalidExpirationDate();
    }

    @Test
    public void shouldEnterInvalidYear() {
        FormPage formPage = homePage.paymentPage();
        formPage.setValidCard(DataHelper.getApprovedCardNumber());
        formPage.setYear(DataHelper.getNumber(1));
        formPage.proceedButton.click();
        formPage.errorInvalidFormat();
    }

    @Test
    public void shouldEnterLastYear() {
        FormPage formPage = homePage.paymentPage();
        formPage.setValidCard(DataHelper.getApprovedCardNumber());
        formPage.setYear(DataHelper.getYear(-1));
        formPage.proceedButton.click();
        formPage.errorExpiredCard();
    }

    @Test
    public void shouldEnterCyrilicName() {
        FormPage formPage = homePage.paymentPage();
        formPage.setValidCard(DataHelper.getApprovedCardNumber());
        formPage.setOwner(DataHelper.getInvalidNameCyrilic());
        formPage.proceedButton.click();
        formPage.errorInvalidFormat();
    }

    @Test
    public void shouldEnterOneWordInName() {
        FormPage formPage = homePage.paymentPage();
        formPage.setValidCard(DataHelper.getApprovedCardNumber());
        formPage.setOwner(DataHelper.getInvalidName());
        formPage.proceedButton.click();
        formPage.errorInvalidFormat();
    }

    @Test
    public void shouldEnter3WordsInName() {
        FormPage formPage = homePage.paymentPage();
        formPage.setValidCard(DataHelper.getApprovedCardNumber());
        formPage.setOwner(DataHelper.getInvalid3Names());
        formPage.proceedButton.click();
        formPage.errorInvalidFormat();
    }

    @Test
    public void shouldBeNumbersInName() {
        FormPage formPage = homePage.paymentPage();
        formPage.setValidCard(DataHelper.getApprovedCardNumber());
        formPage.setOwner(DataHelper.getInvalidNameWithNumber());
        formPage.proceedButton.click();
        formPage.errorInvalidFormat();
    }

    @Test
    public void shouldBeSymbolsInName() {
        FormPage formPage = homePage.paymentPage();
        formPage.setValidCard(DataHelper.getApprovedCardNumber());
        formPage.setOwner(DataHelper.getInvalidName() + " " + " \"[|]`~<!--@/*$%^&#*/()?>,.*/\\");
        formPage.proceedButton.click();
        formPage.errorInvalidFormat();
    }

    @Test
    public void shouldBe2NumbersInCvc() {
        FormPage formPage = homePage.paymentPage();
        formPage.setValidCard(DataHelper.getApprovedCardNumber());
        formPage.setCVC(DataHelper.getNumber(2));
        formPage.proceedButton.click();
        formPage.errorInvalidFormat();
    }

}

