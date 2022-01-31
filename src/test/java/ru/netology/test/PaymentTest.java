package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataGenerator;
import ru.netology.data.DbGenerator;
import ru.netology.page.BuyPage;
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

    @Test // падает
    public void shouldPayApprovedCard() {
        DbGenerator.cleanData();
        var card = DataGenerator.approvedCard();
        var dashboardPage = new DashboardPage();
        var fillBuyPage = dashboardPage.buyPage();
        fillBuyPage.fillForm(card);
        fillBuyPage.successStatus();
        assertEquals("APPROVED", DbGenerator.paymentStatus());
    }

    @Test // падает
    public void shouldCreditApprovedCard() {
        DbGenerator.cleanData();
        var card = DataGenerator.approvedCard();
        var dashboardPage = new DashboardPage();
        var fillCreditPage = dashboardPage.creditPage();
        fillCreditPage.fillForm(card);
        fillCreditPage.successStatus();
        assertEquals("APPROVED", DbGenerator.creditStatus());
    }

    @Test //падает, успешно
    public void shouldPayDeclinedCard() {
        DbGenerator.cleanData();
        var card = DataGenerator.declinedCard();
        var dashboardPage = new DashboardPage();
        var fillBuyPage = dashboardPage.buyPage();
        fillBuyPage.fillForm(card);
        fillBuyPage.errorStatus();
        assertEquals("DECLINED", DbGenerator.paymentStatus());
    }

    @Test //падает, успешно
    public void shouldCreditDeclinedCard() {
        DbGenerator.cleanData();
        var card = DataGenerator.declinedCard();
        var dashboardPage = new DashboardPage();
        var fillCreditPage = dashboardPage.creditPage();
        fillCreditPage.fillForm(card);
        fillCreditPage.errorStatus();
        assertEquals("DECLINED", DbGenerator.creditStatus());
    }

    @Test //падает
    public void shouldBuyUsingNotExistCard() {
        DbGenerator.cleanData();
        var card = DataGenerator.notExistCard();
        var dashboardPage = new DashboardPage();
        var fillBuyPage = dashboardPage.buyPage();
        fillBuyPage.fillForm(card);
        fillBuyPage.errorStatus();
        assertEquals("Ошибка", DbGenerator.paymentStatus());
    }

    @Test // падает
    public void shouldCreditUsingNotExistCard() {
        DbGenerator.cleanData();
        var card = DataGenerator.notExistCard();
        var dashboardPage = new DashboardPage();
        var fillCreditPage = dashboardPage.creditPage();
        fillCreditPage.fillForm(card);
        fillCreditPage.errorStatus();
        assertEquals("Ошибка", DbGenerator.creditStatus());
    }

    @Test // ok
    public void wrongMonthCard() {
        DbGenerator.cleanData();
        var card = DataGenerator.wrongMonthCard();
        var dashboardPage = new DashboardPage();
        var fillPayPage = dashboardPage.buyPage();
        fillPayPage.fillForm(card);
        assertEquals("Неверный формат", fillPayPage.invalidData());

    }

    @Test // ok
    public void wrongMonthCardCredit() {
        DbGenerator.cleanData();
        var card = DataGenerator.wrongMonthCard();
        var dashboardPage = new DashboardPage();
        var fillCreditPage = dashboardPage.creditPage();
        fillCreditPage.fillForm(card);
        assertEquals("Неверный формат", fillCreditPage.wrongFormat());

    }

    @Test // ok
    public void wrongYearCard() {
        DbGenerator.cleanData();
        var card = DataGenerator.wrongYearCard();
        var dashboardPage = new DashboardPage();
        var fillPayPage = dashboardPage.buyPage();
        fillPayPage.fillForm(card);
        assertEquals("Неверный формат", fillPayPage.invalidData());
    }

    @Test // ok
    public void wrongYearCardCredit() {
        DbGenerator.cleanData();
        var card = DataGenerator.wrongYearCard();
        var dashboardPage = new DashboardPage();
        var fillCreditPage = dashboardPage.creditPage();
        fillCreditPage.fillForm(card);
        assertEquals("Неверный формат", fillCreditPage.wrongFormat());
    }

    @Test // ok
    public void wrongCardNumber() {
        DbGenerator.cleanData();
        var card = DataGenerator.wrongNumberCard();
        var dashboardPage = new DashboardPage();
        var fillPayPage = dashboardPage.buyPage();
        fillPayPage.fillForm(card);
        assertEquals("Неверный формат", fillPayPage.invalidData());
    }

    @Test //ok
    public void wrongCardNumberCredit() {
        DbGenerator.cleanData();
        var card = DataGenerator.wrongNumberCard();
        var dashboardPage = new DashboardPage();
        var fillCreditPage = dashboardPage.creditPage();
        fillCreditPage.fillForm(card);
        assertEquals("Неверный формат", fillCreditPage.wrongFormat());
    }

    @Test //падает, успешно
    public void wrongClientNameCard() {
        DbGenerator.cleanData();
        var card = DataGenerator.wrongClientNameCard();
        var dashboardPage = new DashboardPage();
        var fillPayPage = dashboardPage.buyPage();
        fillPayPage.fillForm(card);
        assertEquals("Неверный формат", fillPayPage.invalidData());
    }

    @Test //падает, успешно
    public void wrongClientNameCardCredit() {
        DbGenerator.cleanData();
        var card = DataGenerator.wrongClientNameCard();
        var dashboardPage = new DashboardPage();
        var fillCreditPage = dashboardPage.creditPage();
        fillCreditPage.fillForm(card);
        assertEquals("Неверный формат", fillCreditPage.wrongFormat());
    }

    @Test //ок
    public void wrongClientCvcCard() {
        DbGenerator.cleanData();
        var card = DataGenerator.wrongClientCvcCard();
        var dashboardPage = new DashboardPage();
        var fillPayPage = dashboardPage.buyPage();
        fillPayPage.fillForm(card);
        assertEquals("Неверный формат", fillPayPage.invalidData());
    }

    @Test //ок
    public void wrongClientCvcCardCredit() {
        DbGenerator.cleanData();
        var card = DataGenerator.wrongClientCvcCard();
        var dashboardPage = new DashboardPage();
        var fillCreditPage = dashboardPage.creditPage();
        fillCreditPage.fillForm(card);
        assertEquals("Неверный формат", fillCreditPage.wrongFormat());
    }

    @Test //ок
    public void emptyForm() {
        DbGenerator.cleanData();
        var card = DataGenerator.emptyForm();
        var dashboardPage = new DashboardPage();
        var fillPayPage = dashboardPage.buyPage();
        fillPayPage.fillForm(card);
        assertEquals("Неверный формат", fillPayPage.invalidData());
    }

    @Test //ок
    public void emptyFormCredit() {
        DbGenerator.cleanData();
        var card = DataGenerator.emptyForm();
        var dashboardPage = new DashboardPage();
        var fillCreditPage = dashboardPage.creditPage();
        fillCreditPage.fillForm(card);
        assertEquals("Неверный формат", fillCreditPage.wrongFormat());
    }

    @Test //ok
    public void pastYearCard() {
        DbGenerator.cleanData();
        var card = DataGenerator.pastYearCard();
        var dashboardPage = new DashboardPage();
        var fillPayPage = dashboardPage.buyPage();
        fillPayPage.fillForm(card);
        assertEquals("Истёк срок действия карты", fillPayPage.invalidData());
    }

    @Test //ok
    public void pastYearCardCredit() {
        DbGenerator.cleanData();
        var card = DataGenerator.pastYearCard();
        var dashboardPage = new DashboardPage();
        var fillCreditPage = dashboardPage.creditPage();
        fillCreditPage.fillForm(card);
        assertEquals("Истёк срок действия карты", fillCreditPage.wrongFormat());
    }

    @Test //падает
    public void symbolsInClientNameCard() {
        DbGenerator.cleanData();
        var card = DataGenerator.symbolsInClientNameCard();
        var dashboardPage = new DashboardPage();
        var fillPayPage = dashboardPage.buyPage();
        fillPayPage.fillForm(card);
        assertEquals("Неверный формат", fillPayPage.invalidData());
    }

    @Test //падает
    public void symbolsInClientNameCardCredit() {
        DbGenerator.cleanData();
        var card = DataGenerator.symbolsInClientNameCard();
        var dashboardPage = new DashboardPage();
        var fillCreditPage = dashboardPage.creditPage();
        fillCreditPage.fillForm(card);
        assertEquals("Неверный формат", fillCreditPage.wrongFormat());
    }

    @Test //ok
    public void emptyCardNumber() {
        DbGenerator.cleanData();
        var card = DataGenerator.emptyCardNumber();
        var dashboardPage = new DashboardPage();
        var fillPayPage = dashboardPage.buyPage();
        fillPayPage.fillForm(card);
        assertEquals("Неверный формат", fillPayPage.invalidData());
    }

    @Test //ok
    public void emptyCardNumberCredit() {
        DbGenerator.cleanData();
        var card = DataGenerator.emptyCardNumber();
        var dashboardPage = new DashboardPage();
        var fillCreditPage = dashboardPage.creditPage();
        fillCreditPage.fillForm(card);
        assertEquals("Неверный формат", fillCreditPage.wrongFormat());
    }

    @Test //ok
    public void emptyCardMonth() {
        DbGenerator.cleanData();
        var card = DataGenerator.emptyMonth();
        var dashboardPage = new DashboardPage();
        var fillPayPage = dashboardPage.buyPage();
        fillPayPage.fillForm(card);
        assertEquals("Неверный формат", fillPayPage.invalidData());
    }

    @Test //ok
    public void emptyCardMonthCredit() {
        DbGenerator.cleanData();
        var card = DataGenerator.emptyMonth();
        var dashboardPage = new DashboardPage();
        var fillCreditPage = dashboardPage.creditPage();
        fillCreditPage.fillForm(card);
        assertEquals("Неверный формат", fillCreditPage.wrongFormat());
    }

    @Test //ok
    public void emptyCardYear() {
        DbGenerator.cleanData();
        var card = DataGenerator.emptyYear();
        var dashboardPage = new DashboardPage();
        var fillPayPage = dashboardPage.buyPage();
        fillPayPage.fillForm(card);
        assertEquals("Неверный формат", fillPayPage.invalidData());
    }

    @Test //ok
    public void emptyCardYearCredit() {
        DbGenerator.cleanData();
        var card = DataGenerator.emptyYear();
        var dashboardPage = new DashboardPage();
        var fillCreditPage = dashboardPage.creditPage();
        fillCreditPage.fillForm(card);
        assertEquals("Неверный формат", fillCreditPage.wrongFormat());
    }

    @Test //ok
    public void emptyClientName() {
        DbGenerator.cleanData();
        var card = DataGenerator.emptyName();
        var dashboardPage = new DashboardPage();
        var fillPayPage = dashboardPage.buyPage();
        fillPayPage.fillForm(card);
        assertEquals("Поле обязательно для заполнения", fillPayPage.invalidData());
    }

    @Test //ok
    public void emptyClientNameCredit() {
        DbGenerator.cleanData();
        var card = DataGenerator.emptyName();
        var dashboardPage = new DashboardPage();
        var fillCreditPage = dashboardPage.creditPage();
        fillCreditPage.fillForm(card);
        assertEquals("Поле обязательно для заполнения", fillCreditPage.wrongFormat());
    }

    @Test //ok
    public void emptyCvc() {
        DbGenerator.cleanData();
        var card = DataGenerator.emptyCvc();
        var dashboardPage = new DashboardPage();
        var fillPayPage = dashboardPage.buyPage();
        fillPayPage.fillForm(card);
        assertEquals("Поле обязательно для заполнения", fillPayPage.invalidData());
    }

    @Test //ok
    public void emptyCvcCredit() {
        DbGenerator.cleanData();
        var card = DataGenerator.emptyCvc();
        var dashboardPage = new DashboardPage();
        var fillCreditPage = dashboardPage.creditPage();
        fillCreditPage.fillForm(card);
        assertEquals("Поле обязательно для заполнения", fillCreditPage.wrongFormat());
    }

}
