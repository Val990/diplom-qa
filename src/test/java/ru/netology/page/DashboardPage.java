package ru.netology.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private SelenideElement heading = $(withText("Путешествие дня"));
    private SelenideElement buyButton = $$(".button_theme_alfa-on-white .button__text").findBy(exactText("Купить"));
    private SelenideElement creditButton = $$(".button_theme_alfa-on-white .button__text").findBy(exactText("Купить в кредит"));

    public DashboardPage() {
        heading.shouldBe(visible);
        buyButton.shouldBe(visible);
        creditButton.shouldBe(visible);
    }

    public BuyPage openBuyPage() {
        buyButton.click();
        return new BuyPage();
    }

    public CreditPage openCreditPage() {
        creditButton.click();
        return new CreditPage();
    }
}