package data;

import com.github.javafaker.Faker;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataHelper {

    public static class  AuthInfo{

        public static String createBodyPaymentRequest(Card card) {
            return "{\n" +
                    " \"number\": \"" + card.getNumber() + "\",\n" +
                    " \"month\": \"" + card.getMonth() + "\",\n" +
                    " \"year\": \"" + card.getYear() + "\",\n" +
                    " \"holder\": \"" + card.getHolder() + "\",\n" +
                    " \"cvc\": \"" + card.getCvc() + "\"\n" +
                    "}";
        }
    }
    private static final Faker faker = new Faker(Locale.ENGLISH);

    public static String getURL() {
        return "http://localhost:8080";
    }

    public static String getApprovedCardNumber() {
        return "4444 4444 4444 4441";
    }

    public static String getDeclinedCardNumber() {
        return "4444 4444 4444 4442";
    }

    public static String getMonth(int month) {
        return LocalDate.now().plusMonths(month).format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String getYear(int year) {
        return LocalDate.now().plusYears(year).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getValidName() {
        return faker.name().firstName() + " " + faker.name().lastName();
    }

    public static String getNumber(int length) {
        return faker.number().digits(length);
    }

    public static String getValidCVC() {
        return getNumber(3);
    }

    public static String getFrom13To99() {
        int element = (int) (Math.random() * 87 + 13);
        return Integer.toString(element);
    }

    public static String getInvalidName() {
        return faker.name().firstName();
    }

    public static String getInvalid3Names() {
        return faker.name().firstName() + " " + faker.name().lastName() + " " + faker.name().firstName();
    }

    public static String getInvalidNameWithNumber () {
        return faker.name().firstName() + getFrom13To99() + " " + faker.name().lastName() + getFrom13To99();
    }

    public static String getInvalidNameCyrilic() {
        Faker fakerRU = new Faker(new Locale("ru"));
        return faker.name().firstName() + " " + fakerRU.name().lastName();
    }
}
