package test;

import data.DataHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import page.FormPage;
import page.HomePage;
import utils.DBUtil;

public class CreditCardTest extends BaseTest {
    HomePage homePage = new HomePage();


    @Test
    public void shouldSuccessCreditWithApprovedCard() {
        FormPage formPage = homePage.creditPage();
        formPage.setValidCard(DataHelper.getApprovedCardNumber());
        formPage.proceedButton.click();
        formPage.paySuccess();
        Assertions.assertEquals("APPROVED", DBUtil.getPaymentStatus("credit_request_entity"));
        Assertions.assertEquals(1L, DBUtil.countOrderIfCredit());
    }

    @Test
    public void shouldDeclineCredit() {
        FormPage formPage = homePage.creditPage();
        formPage.setValidCard(DataHelper.getDeclinedCardNumber());
        formPage.proceedButton.click();
        formPage.payError();
        Assertions.assertEquals("DECLINED", DBUtil.getPaymentStatus("payment_request_entity"));
        Assertions.assertEquals(1L, DBUtil.countOrderIfCredit());
    }

    @Test
    public void shouldSendEmptyForm() {
        FormPage formPage = homePage.creditPage();
        formPage.proceedButton.click();
        formPage.errorInRequiredField();
    }

    @Test
    public void shouldEnterNumberFrom13To99InMonth() {
        FormPage formPage = homePage.creditPage();
        formPage.setValidCard(DataHelper.getApprovedCardNumber());
        formPage.setMonth(DataHelper.getFrom13To99());
        formPage.proceedButton.click();
        formPage.errorExpiredCard();
    }

    @Test
    public void shouldBeLastMonth() {
        FormPage formPage = homePage.creditPage();
        formPage.setValidCard(DataHelper.getApprovedCardNumber());
        formPage.setMonth(DataHelper.getMonth(-1));
        formPage.setYear(DataHelper.getYear(0));
        formPage.proceedButton.click();
        formPage.errorInvalidExpirationDate();
    }

    @Test
    public void shouldBeInvalidYear() {
        FormPage formPage = homePage.creditPage();
        formPage.setValidCard(DataHelper.getApprovedCardNumber());
        formPage.setYear(DataHelper.getNumber(1));
        formPage.proceedButton.click();
        formPage.errorInvalidFormat();
    }

    @Test
    public void shouldBeLastYear() {
        FormPage formPage = homePage.creditPage();
        formPage.setValidCard(DataHelper.getApprovedCardNumber());
        formPage.setYear(DataHelper.getYear(-1));
        formPage.proceedButton.click();
        formPage.errorExpiredCard();
    }

    @Test
    public void shouldBeCyrilicName() {
        FormPage formPage = homePage.creditPage();
        formPage.setValidCard(DataHelper.getApprovedCardNumber());
        formPage.setOwner(DataHelper.getInvalidNameCyrilic());
        formPage.proceedButton.click();
        formPage.errorInvalidFormat();
    }

    @Test
    public void shouldBeOneWordInName() {
        FormPage formPage = homePage.creditPage();
        formPage.setValidCard(DataHelper.getApprovedCardNumber());
        formPage.setOwner(DataHelper.getInvalidName());
        formPage.proceedButton.click();
        formPage.errorInvalidFormat();
    }

    @Test
    public void shouldBe3WordsInName() {
        FormPage formPage = homePage.creditPage();
        formPage.setValidCard(DataHelper.getApprovedCardNumber());
        formPage.setOwner(DataHelper.getInvalid3Names());
        formPage.proceedButton.click();
        formPage.errorInvalidFormat();
    }

    @Test
    public void shouldBeNumbersInName() {
        FormPage formPage = homePage.creditPage();
        formPage.setValidCard(DataHelper.getApprovedCardNumber());
        formPage.setOwner(DataHelper.getInvalidNameWithNumber());
        formPage.proceedButton.click();
        formPage.errorInvalidFormat();
    }

    @Test
    public void shouldBeSymbolsInName() {
        FormPage formPage = homePage.creditPage();
        formPage.setValidCard(DataHelper.getApprovedCardNumber());
        formPage.setOwner(DataHelper.getInvalidName() + " " + " \"[|]`~<!--@/*$%^&#*/()?>,.*/\\");
        formPage.proceedButton.click();
        formPage.errorInvalidFormat();
    }

    @Test
    public void shouldBe2NumbersInCVC() {
        FormPage formPage = homePage.creditPage();
        formPage.setValidCard(DataHelper.getApprovedCardNumber());
        formPage.setCVC(DataHelper.getNumber(2));
        formPage.proceedButton.click();
        formPage.errorInvalidFormat();
    }

}
