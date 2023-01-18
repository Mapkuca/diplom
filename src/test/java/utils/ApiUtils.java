package utils;

import data.Card;
import data.DataHelper;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ApiUtils {
    private static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setBasePath("/api/v1")
            .setPort(8080)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public static void shouldSendRequestApprovedCard() {
        Card card = new Card(DataHelper.getApprovedCardNumber(),
        DataHelper.getMonth(0),
        DataHelper.getYear(1),
        DataHelper.getValidName(),
        DataHelper.getValidCVC());

        given()
                .spec(requestSpec)
                .body(DataHelper.AuthInfo.createBodyPaymentRequest(card))
                .when()
                .post("/pay")
                .then()
                .statusCode(200)
                .body("status", equalTo("APPROVED"));
    }

    public static void shouldSendRequestDeclinedCard() {
        Card card = new Card(DataHelper.getDeclinedCardNumber(),
                DataHelper.getMonth(0),
                DataHelper.getYear(1),
                DataHelper.getValidName(),
                DataHelper.getValidCVC());
        given()
                .spec(requestSpec)
                .body(DataHelper.AuthInfo.createBodyPaymentRequest(card))
                .when()
                .post("/pay")
                .then()
                .statusCode(200)
                .body("status", equalTo("DECLINED"));
    }

    public static void shouldSendCreditRequestApprovedCard() {
        Card card = new Card(DataHelper.getApprovedCardNumber(),
                DataHelper.getMonth(0),
                DataHelper.getYear(1),
                DataHelper.getValidName(),
                DataHelper.getValidCVC());
        given()
                .spec(requestSpec)
                .body(DataHelper.AuthInfo.createBodyPaymentRequest(card))
                .when()
                .post("/credit")
                .then()
                .statusCode(200)
                .body("status", equalTo("APPROVED"));
    }

    public static void shouldSendCreditRequestDeclinedCard() {
        Card card = new Card(DataHelper.getDeclinedCardNumber(),
                DataHelper.getMonth(0),
                DataHelper.getYear(1),
                DataHelper.getValidName(),
                DataHelper.getValidCVC());
        given()
                .spec(requestSpec)
                .body(DataHelper.AuthInfo.createBodyPaymentRequest(card))
                .when()
                .post("/credit")
                .then()
                .statusCode(200)
                .body("status", equalTo("DECLINED"));
    }
}
