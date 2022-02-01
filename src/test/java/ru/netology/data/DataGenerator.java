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

    public static CardInfo getApprovedCard() {
        LocalDate today = LocalDate.now();
        String month = today.plusMonths(faker.number().numberBetween(1, 13)).format(DateTimeFormatter.ofPattern("MM"));
        String year = today.plusYears(faker.number().numberBetween(1, 4)).format(DateTimeFormatter.ofPattern("yy"));
        return new CardInfo("4444 4444 4444 4441", month, year, cardClientName(), cardCvc());
    }

    public static CardInfo getDeclinedCard() {
        LocalDate today = LocalDate.now();
        String month = today.plusMonths(faker.number().numberBetween(1, 13)).format(DateTimeFormatter.ofPattern("MM"));
        String year = today.plusYears(faker.number().numberBetween(1, 4)).format(DateTimeFormatter.ofPattern("yy"));
        return new CardInfo("4444 4444 4444 4442", month, year, cardClientName(), cardCvc());
    }

    public static CardInfo getNotExistCard() {
        LocalDate today = LocalDate.now();
        String month = today.plusMonths(faker.number().numberBetween(1, 13)).format(DateTimeFormatter.ofPattern("MM"));
        String year = today.plusYears(faker.number().numberBetween(1, 4)).format(DateTimeFormatter.ofPattern("yy"));
        return new CardInfo(faker.numerify("#### #### #### ####"), month, year, cardClientName(), cardCvc());
    }

    public static CardInfo getWrongNumberCard() {
        LocalDate today = LocalDate.now();
        String month = today.plusMonths(faker.number().numberBetween(1, 13)).format(DateTimeFormatter.ofPattern("MM"));
        String year = today.plusYears(faker.number().numberBetween(1, 4)).format(DateTimeFormatter.ofPattern("yy"));
        return new CardInfo("4444 4444 4444 444", month, year, cardClientName(), cardCvc());
    }

    public static CardInfo getWrongMonthCard() {
        LocalDate today = LocalDate.now();
        String year = today.plusYears(faker.number().numberBetween(1, 4)).format(DateTimeFormatter.ofPattern("yy"));
        return new CardInfo("4444 4444 4444 4441", "0", year, cardClientName(), cardCvc());
    }

    public static CardInfo getWrongYearCard() {
        LocalDate today = LocalDate.now();
        String month = today.plusMonths(faker.number().numberBetween(1, 13)).format(DateTimeFormatter.ofPattern("MM"));
        return new CardInfo("4444 4444 4444 4441", month, "0", cardClientName(), cardCvc());
    }

    public static CardInfo getWrongClientNameCard() {
        LocalDate today = LocalDate.now();
        String month = today.plusMonths(faker.number().numberBetween(1, 13)).format(DateTimeFormatter.ofPattern("MM"));
        String year = today.plusYears(faker.number().numberBetween(1, 4)).format(DateTimeFormatter.ofPattern("yy"));
        return new CardInfo("4444 4444 4444 4441", month, year, "qew", cardCvc());
    }

    public static CardInfo getWrongClientCvcCard() {
        LocalDate today = LocalDate.now();
        String month = today.plusMonths(faker.number().numberBetween(1, 13)).format(DateTimeFormatter.ofPattern("MM"));
        String year = today.plusYears(faker.number().numberBetween(1, 4)).format(DateTimeFormatter.ofPattern("yy"));
        return new CardInfo("4444 4444 4444 4441", month, year, cardClientName(), "0");
    }

    public static CardInfo getPastYearCard() {
        LocalDate today = LocalDate.now();
        String month = today.plusMonths(faker.number().numberBetween(1, 13)).format(DateTimeFormatter.ofPattern("MM"));
        LocalDate pastYear = today.minusYears(2);
        String year = pastYear.format(DateTimeFormatter.ofPattern("yy"));
        return new CardInfo("4444 4444 4444 4441", month, year, cardClientName(), cardCvc());
    }

    public static CardInfo getEmptyForm() {
        return new CardInfo(emptyField(), emptyField(), emptyField(), emptyField(), emptyField());
    }

    public static CardInfo getEmptyCardNumber() {
        LocalDate today = LocalDate.now();
        String month = today.plusMonths(faker.number().numberBetween(1, 13)).format(DateTimeFormatter.ofPattern("MM"));
        String year = today.plusYears(faker.number().numberBetween(1, 4)).format(DateTimeFormatter.ofPattern("yy"));
        return new CardInfo(emptyField(), month, year, cardClientName(), cardCvc());
    }

    public static CardInfo getEmptyMonth() {
        LocalDate today = LocalDate.now();
        String year = today.plusYears(faker.number().numberBetween(1, 4)).format(DateTimeFormatter.ofPattern("yy"));
        return new CardInfo("4444 4444 4444 4441", emptyField(), year, cardClientName(), cardCvc());
    }

    public static CardInfo getEmptyYear() {
        LocalDate today = LocalDate.now();
        String month = today.plusMonths(faker.number().numberBetween(1, 13)).format(DateTimeFormatter.ofPattern("MM"));
        return new CardInfo("4444 4444 4444 4441", month, emptyField(), cardClientName(), cardCvc());
    }

    public static CardInfo getEmptyName() {
        LocalDate today = LocalDate.now();
        String month = today.plusMonths(faker.number().numberBetween(1, 13)).format(DateTimeFormatter.ofPattern("MM"));
        String year = today.plusYears(faker.number().numberBetween(1, 4)).format(DateTimeFormatter.ofPattern("yy"));
        return new CardInfo("4444 4444 4444 4441", month, year, emptyField(), cardCvc());
    }

    public static CardInfo getEmptyCvc() {
        LocalDate today = LocalDate.now();
        String month = today.plusMonths(faker.number().numberBetween(1, 13)).format(DateTimeFormatter.ofPattern("MM"));
        String year = today.plusYears(faker.number().numberBetween(1, 4)).format(DateTimeFormatter.ofPattern("yy"));
        return new CardInfo("4444 4444 4444 4441", month, year, cardClientName(), emptyField());
    }

    public static CardInfo getSymbolsInClientNameCard() {
        LocalDate today = LocalDate.now();
        String month = today.plusMonths(faker.number().numberBetween(1, 13)).format(DateTimeFormatter.ofPattern("MM"));
        String year = today.plusYears(faker.number().numberBetween(1, 4)).format(DateTimeFormatter.ofPattern("yy"));
        return new CardInfo("4444 4444 4444 4441", month, year, "%)!<", cardCvc());
    }

}
