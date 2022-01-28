package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataGenerator {
    private DataGenerator() {
    }

    private static Faker faker = new Faker();

    public static String cardCvc() {
        return faker.numerify("###");
    }

    public static String cardClientName() {
        return faker.name().fullName();
    }

    public static String emptyField() {
        return "";
    }

    public static CardInfo approvedCard() {
        LocalDate today = LocalDate.now();
        String month = today.plusMonths(faker.number().numberBetween(1, 13)).format(DateTimeFormatter.ofPattern("MM"));
        String year = today.plusYears(faker.number().numberBetween(1, 4)).format(DateTimeFormatter.ofPattern("yy"));
        return new CardInfo("4444 4444 4444 4441", month, year, cardClientName(), cardCvc());
    }

    public static CardInfo declinedCard() {
        LocalDate today = LocalDate.now();
        String month = today.plusMonths(faker.number().numberBetween(1, 13)).format(DateTimeFormatter.ofPattern("MM"));
        String year = today.plusYears(faker.number().numberBetween(1, 4)).format(DateTimeFormatter.ofPattern("yy"));
        return new CardInfo("4444 4444 4444 4442", month, year, cardClientName(), cardCvc());
    }

    public static CardInfo notExistCard() {
        LocalDate today = LocalDate.now();
        String month = today.plusMonths(faker.number().numberBetween(1, 13)).format(DateTimeFormatter.ofPattern("MM"));
        String year = today.plusYears(faker.number().numberBetween(1, 4)).format(DateTimeFormatter.ofPattern("yy"));
        return new CardInfo(faker.numerify("#### #### #### ####"), month, year, cardClientName(), cardCvc());
    }

    public static CardInfo wrongNumberCard() {
        LocalDate today = LocalDate.now();
        String month = today.plusMonths(faker.number().numberBetween(1, 13)).format(DateTimeFormatter.ofPattern("MM"));
        String year = today.plusYears(faker.number().numberBetween(1, 4)).format(DateTimeFormatter.ofPattern("yy"));
        return new CardInfo("4444 4444 4444 444", month, year, cardClientName(), cardCvc());
    }

    public static CardInfo wrongMonthCard() {
        LocalDate today = LocalDate.now();
        String year = today.plusYears(faker.number().numberBetween(1, 4)).format(DateTimeFormatter.ofPattern("yy"));
        return new CardInfo("4444 4444 4444 4441", "0", year, cardClientName(), cardCvc());
    }

    public static CardInfo wrongYearCard() {
        LocalDate today = LocalDate.now();
        String month = today.plusMonths(faker.number().numberBetween(1, 13)).format(DateTimeFormatter.ofPattern("MM"));
        return new CardInfo("4444 4444 4444 4441", month, "0", cardClientName(), cardCvc());
    }

    public static CardInfo wrongClientNameCard() {
        LocalDate today = LocalDate.now();
        String month = today.plusMonths(faker.number().numberBetween(1, 13)).format(DateTimeFormatter.ofPattern("MM"));
        String year = today.plusYears(faker.number().numberBetween(1, 4)).format(DateTimeFormatter.ofPattern("yy"));
        return new CardInfo("4444 4444 4444 4441", month, year, "qew", cardCvc());
    }

    public static CardInfo wrongClientCvcCard() {
        LocalDate today = LocalDate.now();
        String month = today.plusMonths(faker.number().numberBetween(1, 13)).format(DateTimeFormatter.ofPattern("MM"));
        String year = today.plusYears(faker.number().numberBetween(1, 4)).format(DateTimeFormatter.ofPattern("yy"));
        return new CardInfo("4444 4444 4444 4441", month, year, cardClientName(), "0");
    }

    public static CardInfo pastYearCard() {
        LocalDate today = LocalDate.now();
        String month = today.plusMonths(faker.number().numberBetween(1, 13)).format(DateTimeFormatter.ofPattern("MM"));
        LocalDate pastYear = today.minusYears(2);
        String year = pastYear.format(DateTimeFormatter.ofPattern("yy"));
        return new CardInfo("4444 4444 4444 4441", month, year, cardClientName(), cardCvc());
    }

    public static CardInfo emptyForm() {
        return new CardInfo(emptyField(), emptyField(), emptyField(), emptyField(), emptyField());
    }

    public static CardInfo emptyCardNumber() {
        LocalDate today = LocalDate.now();
        String month = today.plusMonths(faker.number().numberBetween(1, 13)).format(DateTimeFormatter.ofPattern("MM"));
        String year = today.plusYears(faker.number().numberBetween(1, 4)).format(DateTimeFormatter.ofPattern("yy"));
        return new CardInfo(emptyField(), month, year, cardClientName(), cardCvc());
    }

    public static CardInfo emptyMonth() {
        LocalDate today = LocalDate.now();
        String year = today.plusYears(faker.number().numberBetween(1, 4)).format(DateTimeFormatter.ofPattern("yy"));
        return new CardInfo("4444 4444 4444 4441", emptyField(), year, cardClientName(), cardCvc());
    }

    public static CardInfo emptyYear() {
        LocalDate today = LocalDate.now();
        String month = today.plusMonths(faker.number().numberBetween(1, 13)).format(DateTimeFormatter.ofPattern("MM"));
        return new CardInfo("4444 4444 4444 4441", month, emptyField(), cardClientName(), cardCvc());
    }

    public static CardInfo emptyName() {
        LocalDate today = LocalDate.now();
        String month = today.plusMonths(faker.number().numberBetween(1, 13)).format(DateTimeFormatter.ofPattern("MM"));
        String year = today.plusYears(faker.number().numberBetween(1, 4)).format(DateTimeFormatter.ofPattern("yy"));
        return new CardInfo("4444 4444 4444 4441", month, year, emptyField(), cardCvc());
    }

    public static CardInfo emptyCvc() {
        LocalDate today = LocalDate.now();
        String month = today.plusMonths(faker.number().numberBetween(1, 13)).format(DateTimeFormatter.ofPattern("MM"));
        String year = today.plusYears(faker.number().numberBetween(1, 4)).format(DateTimeFormatter.ofPattern("yy"));
        return new CardInfo("4444 4444 4444 4441", month, year, cardClientName(), emptyField());
    }

    public static CardInfo symbolsInClientNameCard() {
        LocalDate today = LocalDate.now();
        String month = today.plusMonths(faker.number().numberBetween(1, 13)).format(DateTimeFormatter.ofPattern("MM"));
        String year = today.plusYears(faker.number().numberBetween(1, 4)).format(DateTimeFormatter.ofPattern("yy"));
        return new CardInfo("4444 4444 4444 4441", month, year, "%)!<", cardCvc());
    }

}
