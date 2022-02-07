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

public class CreditTest {

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
    public void shouldCreditWithApprovedCard() {
        DbGenerator.cleanData();
        var card = DataGenerator.getApprovedCard();
        var dashboardPage = new DashboardPage();
        var fillCreditPage = dashboardPage.openCreditPage();
        fillCreditPage.fillForm(card);
        fillCreditPage.getSuccessStatus();
        assertEquals("APPROVED", DbGenerator.getCreditStatus());
    }

    @Test
    public void shouldCreditWithDeclinedCard() {
        DbGenerator.cleanData();
        var card = DataGenerator.getDeclinedCard();
        var dashboardPage = new DashboardPage();
        var fillCreditPage = dashboardPage.openCreditPage();
        fillCreditPage.fillForm(card);
        fillCreditPage.getErrorStatus();
        assertEquals("DECLINED", DbGenerator.getCreditStatus());
    }

    @Test
    public void shouldCreditUsingNotExistCard() {
        DbGenerator.cleanData();
        var card = DataGenerator.getNotExistCard();
        var dashboardPage = new DashboardPage();
        var fillCreditPage = dashboardPage.openCreditPage();
        fillCreditPage.fillForm(card);
        fillCreditPage.getErrorStatus();
        assertEquals("null", DbGenerator.getCreditStatus());
    }

    @Test
    public void shouldCreditWithWrongMonthCard() {
        DbGenerator.cleanData();
        var card = DataGenerator.getWrongMonthCard();
        var dashboardPage = new DashboardPage();
        var fillCreditPage = dashboardPage.openCreditPage();
        fillCreditPage.fillForm(card);
        assertEquals("Неверный формат", fillCreditPage.getWrongFormat());
    }

    @Test
    public void shouldCreditWithWrongYearCard() {
        DbGenerator.cleanData();
        var card = DataGenerator.getWrongYearCard();
        var dashboardPage = new DashboardPage();
        var fillCreditPage = dashboardPage.openCreditPage();
        fillCreditPage.fillForm(card);
        assertEquals("Неверный формат", fillCreditPage.getWrongFormat());
    }

    @Test
    public void shouldCreditWithWrongCardNumber() {
        DbGenerator.cleanData();
        var card = DataGenerator.getWrongNumberCard();
        var dashboardPage = new DashboardPage();
        var fillCreditPage = dashboardPage.openCreditPage();
        fillCreditPage.fillForm(card);
        assertEquals("Неверный формат", fillCreditPage.getWrongFormat());
    }

    @Test
    public void shouldCreditWithWrongClientNameCard() {
        DbGenerator.cleanData();
        var card = DataGenerator.getWrongClientNameCard();
        var dashboardPage = new DashboardPage();
        var fillCreditPage = dashboardPage.openCreditPage();
        fillCreditPage.fillForm(card);
        assertEquals("Неверный формат", fillCreditPage.getWrongFormat());
    }

    @Test
    public void shouldCreditWithWrongClientCvcCard() {
        DbGenerator.cleanData();
        var card = DataGenerator.getWrongClientCvcCard();
        var dashboardPage = new DashboardPage();
        var fillCreditPage = dashboardPage.openCreditPage();
        fillCreditPage.fillForm(card);
        assertEquals("Неверный формат", fillCreditPage.getWrongFormat());
    }

    @Test
    public void shouldCreditWithEmptyForm() {
        DbGenerator.cleanData();
        var card = DataGenerator.getEmptyForm();
        var dashboardPage = new DashboardPage();
        var fillCreditPage = dashboardPage.openCreditPage();
        fillCreditPage.fillForm(card);
        assertEquals("Неверный формат", fillCreditPage.getWrongFormat());
    }

    @Test
    public void shouldCreditWithPastYearCard() {
        DbGenerator.cleanData();
        var card = DataGenerator.getPastYearCard();
        var dashboardPage = new DashboardPage();
        var fillCreditPage = dashboardPage.openCreditPage();
        fillCreditPage.fillForm(card);
        assertEquals("Истёк срок действия карты", fillCreditPage.getWrongFormat());
    }

    @Test
    public void shouldCreditWithSymbolsInClientNameCard() {
        DbGenerator.cleanData();
        var card = DataGenerator.getSymbolsInClientNameCard();
        var dashboardPage = new DashboardPage();
        var fillCreditPage = dashboardPage.openCreditPage();
        fillCreditPage.fillForm(card);
        assertEquals("Неверный формат", fillCreditPage.getWrongFormat());
    }

    @Test
    public void shouldCreditWithEmptyCardNumber() {
        DbGenerator.cleanData();
        var card = DataGenerator.getEmptyCardNumber();
        var dashboardPage = new DashboardPage();
        var fillCreditPage = dashboardPage.openCreditPage();
        fillCreditPage.fillForm(card);
        assertEquals("Неверный формат", fillCreditPage.getWrongFormat());
    }

    @Test
    public void shouldCreditWithEmptyCardMonth() {
        DbGenerator.cleanData();
        var card = DataGenerator.getEmptyMonth();
        var dashboardPage = new DashboardPage();
        var fillCreditPage = dashboardPage.openCreditPage();
        fillCreditPage.fillForm(card);
        assertEquals("Неверный формат", fillCreditPage.getWrongFormat());
    }

    @Test
    public void shouldCreditWithEmptyCardYear() {
        DbGenerator.cleanData();
        var card = DataGenerator.getEmptyYear();
        var dashboardPage = new DashboardPage();
        var fillCreditPage = dashboardPage.openCreditPage();
        fillCreditPage.fillForm(card);
        assertEquals("Неверный формат", fillCreditPage.getWrongFormat());
    }

    @Test
    public void shouldCreditWithEmptyClientName() {
        DbGenerator.cleanData();
        var card = DataGenerator.getEmptyName();
        var dashboardPage = new DashboardPage();
        var fillCreditPage = dashboardPage.openCreditPage();
        fillCreditPage.fillForm(card);
        assertEquals("Поле обязательно для заполнения", fillCreditPage.getWrongFormat());
    }

    @Test
    public void shouldCreditWithEmptyCvc() {
        DbGenerator.cleanData();
        var card = DataGenerator.getEmptyCvc();
        var dashboardPage = new DashboardPage();
        var fillCreditPage = dashboardPage.openCreditPage();
        fillCreditPage.fillForm(card);
        assertEquals("Поле обязательно для заполнения", fillCreditPage.getWrongFormat());
    }
}
