package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataGenerator;
import ru.netology.data.DbGenerator;
import ru.netology.page.DashboardPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentTest {

    @BeforeEach
    void setUp() {
        open("http://localhost:8080/");
    }

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDown() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    public void shouldBuyWithApprovedCard() {
        DbGenerator.cleanData();
        var card = DataGenerator.getApprovedCard();
        var dashboardPage = new DashboardPage();
        var fillBuyPage = dashboardPage.openBuyPage();
        fillBuyPage.fillForm(card);
        fillBuyPage.getSuccessStatus();
        assertEquals("APPROVED", DbGenerator.getPaymentStatus());
    }

    @Test
    public void shouldBuyWithDeclinedCard() {
        DbGenerator.cleanData();
        var card = DataGenerator.getDeclinedCard();
        var dashboardPage = new DashboardPage();
        var fillBuyPage = dashboardPage.openBuyPage();
        fillBuyPage.fillForm(card);
        fillBuyPage.getErrorStatus();
        assertEquals("DECLINED", DbGenerator.getPaymentStatus());
    }

    @Test
    public void shouldBuyUsingNotExistCard() {
        DbGenerator.cleanData();
        var card = DataGenerator.getNotExistCard();
        var dashboardPage = new DashboardPage();
        var fillBuyPage = dashboardPage.openBuyPage();
        fillBuyPage.fillForm(card);
        fillBuyPage.getErrorStatus();
        assertEquals("null", DbGenerator.getPaymentStatus());
    }

    @Test
    public void shouldBuyWithWrongMonthCard() {
        DbGenerator.cleanData();
        var card = DataGenerator.getWrongMonthCard();
        var dashboardPage = new DashboardPage();
        var fillPayPage = dashboardPage.openBuyPage();
        fillPayPage.fillForm(card);
        assertEquals("Неверный формат", fillPayPage.getInvalidData());
    }

    @Test
    public void shouldBuyWithWrongYearCard() {
        DbGenerator.cleanData();
        var card = DataGenerator.getWrongYearCard();
        var dashboardPage = new DashboardPage();
        var fillPayPage = dashboardPage.openBuyPage();
        fillPayPage.fillForm(card);
        assertEquals("Неверный формат", fillPayPage.getInvalidData());
    }

    @Test
    public void shouldBuyWithWrongCardNumber() {
        DbGenerator.cleanData();
        var card = DataGenerator.getWrongNumberCard();
        var dashboardPage = new DashboardPage();
        var fillPayPage = dashboardPage.openBuyPage();
        fillPayPage.fillForm(card);
        assertEquals("Неверный формат", fillPayPage.getInvalidData());
    }

    @Test
    public void shouldBuyWithWrongClientNameCard() {
        DbGenerator.cleanData();
        var card = DataGenerator.getWrongClientNameCard();
        var dashboardPage = new DashboardPage();
        var fillPayPage = dashboardPage.openBuyPage();
        fillPayPage.fillForm(card);
        assertEquals("Неверный формат", fillPayPage.getInvalidData());
    }

    @Test
    public void shouldBuyWithWrongClientCvcCard() {
        DbGenerator.cleanData();
        var card = DataGenerator.getWrongClientCvcCard();
        var dashboardPage = new DashboardPage();
        var fillPayPage = dashboardPage.openBuyPage();
        fillPayPage.fillForm(card);
        assertEquals("Неверный формат", fillPayPage.getInvalidData());
    }

    @Test
    public void shouldBuyWithEmptyForm() {
        DbGenerator.cleanData();
        var card = DataGenerator.getEmptyForm();
        var dashboardPage = new DashboardPage();
        var fillPayPage = dashboardPage.openBuyPage();
        fillPayPage.fillForm(card);
        assertEquals("Неверный формат", fillPayPage.getInvalidData());
    }

    @Test
    public void shouldBuyWithWithPastYearCard() {
        DbGenerator.cleanData();
        var card = DataGenerator.getPastYearCard();
        var dashboardPage = new DashboardPage();
        var fillPayPage = dashboardPage.openBuyPage();
        fillPayPage.fillForm(card);
        assertEquals("Истёк срок действия карты", fillPayPage.getInvalidData());
    }

    @Test
    public void shouldBuyWithSymbolsInClientNameCard() {
        DbGenerator.cleanData();
        var card = DataGenerator.getSymbolsInClientNameCard();
        var dashboardPage = new DashboardPage();
        var fillPayPage = dashboardPage.openBuyPage();
        fillPayPage.fillForm(card);
        assertEquals("Неверный формат", fillPayPage.getInvalidData());
    }

    @Test
    public void shouldBuyWithEmptyCardNumber() {
        DbGenerator.cleanData();
        var card = DataGenerator.getEmptyCardNumber();
        var dashboardPage = new DashboardPage();
        var fillPayPage = dashboardPage.openBuyPage();
        fillPayPage.fillForm(card);
        assertEquals("Неверный формат", fillPayPage.getInvalidData());
    }

    @Test
    public void shouldBuyWithEmptyCardMonth() {
        DbGenerator.cleanData();
        var card = DataGenerator.getEmptyMonth();
        var dashboardPage = new DashboardPage();
        var fillPayPage = dashboardPage.openBuyPage();
        fillPayPage.fillForm(card);
        assertEquals("Неверный формат", fillPayPage.getInvalidData());
    }

    @Test
    public void shouldBuyWithEmptyCardYear() {
        DbGenerator.cleanData();
        var card = DataGenerator.getEmptyYear();
        var dashboardPage = new DashboardPage();
        var fillPayPage = dashboardPage.openBuyPage();
        fillPayPage.fillForm(card);
        assertEquals("Неверный формат", fillPayPage.getInvalidData());
    }

    @Test
    public void shouldBuyWithEmptyClientName() {
        DbGenerator.cleanData();
        var card = DataGenerator.getEmptyName();
        var dashboardPage = new DashboardPage();
        var fillPayPage = dashboardPage.openBuyPage();
        fillPayPage.fillForm(card);
        assertEquals("Поле обязательно для заполнения", fillPayPage.getInvalidData());
    }

    @Test
    public void shouldBuyWithEmptyCvc() {
        DbGenerator.cleanData();
        var card = DataGenerator.getEmptyCvc();
        var dashboardPage = new DashboardPage();
        var fillPayPage = dashboardPage.openBuyPage();
        fillPayPage.fillForm(card);
        assertEquals("Поле обязательно для заполнения", fillPayPage.getInvalidData());
    }
}
