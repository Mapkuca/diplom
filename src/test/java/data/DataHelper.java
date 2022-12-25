package data;

import com.github.javafaker.Faker;
import org.apache.commons.lang3.RandomStringUtils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataHelper {

    public static class  AuthInfo{

        public static String createJSON(Card card) {
            return "{\n" +
                    " \"number\": \"" + card.getNumber() + "\",\n" +
                    " \"month\": \"" + card.getMonth() + "\",\n" +
                    " \"year\": \"" + card.getYear() + "\",\n" +
                    " \"cvc\": \"" + card.getCvc() + "\"\n" +
                    "}";
        }
    }
    private static final Faker faker = new Faker(Locale.ENGLISH);

    public static String getApprovedCardNumber() {
        return "4444 4444 4444 4441";
    }

    public static String getDeclinedCardNumber() {
        return "4444 4444 4444 4442";
    }

    public static String getValidMonth() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM");
        String month = sdf.format(faker.date().birthday());
        return month;
    }

    public static String getNextYear() {
        return LocalDate.now().plusYears(1).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getValidName() {
        return faker.name().firstName() + " " + faker.name().lastName();
    }

    public static String getValidCVC() {
        int n = (int) (Math.random() * 999 + 1);
        return String.format("%03d", n);
    }

    public static String getOneNumber() {
        int element = (int) (Math.random() * 10);
        return Integer.toString(element);
    }

    public static String getFrom13To99() {
        int element = (int) (Math.random() * 87 + 13);
        return Integer.toString(element);
    }

    public static String getLastMonth() {
        return LocalDate.now().minusMonths(1).format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String getLastYear() {
        return LocalDate.now().minusYears(1).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getYear() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getYearPlus6() {
        return LocalDate.now().plusYears(6).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getInvalidName() {
        return faker.name().firstName();
    }

    public static String getInvalid3Names() {
        return faker.name().firstName() + " " + faker.name().lastName() + " " + faker.name().firstName();
    }

    public static String getInvalidLenghtOfName65() {
        String firstName = RandomStringUtils.randomAlphabetic(30);
        String secondName = RandomStringUtils.randomAlphabetic(34);
        return firstName + " " + secondName;
    }

    public static String getInvalidNameWithNumber () {
        return faker.name().firstName() + getFrom13To99() + " " + faker.name().lastName() + getFrom13To99();
    }

    public static String getInvalidNameCyrilic() {
        Faker fakerRU = new Faker(new Locale("ru"));
        return faker.name().firstName() + " " + fakerRU.name().lastName();
    }
}
