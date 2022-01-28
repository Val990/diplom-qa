package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.CardInfo;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class BuyPage {
    private SelenideElement heading = $$("h3").find(Condition.text("Оплата по карте"));
    private SelenideElement cardNumber = $("[placeholder='0000 0000 0000 0000']");
    private SelenideElement month = $("[placeholder='08']");
    private SelenideElement year = $("[placeholder='22']");
    private SelenideElement client = $(byText("Владелец")).parent().$(".input__control");
    private SelenideElement cvc = $("[placeholder='999']");
    private SelenideElement continueButton = $$(".button_theme_alfa-on-white .button__text").findBy(exactText("Продолжить"));
    private SelenideElement success = $(".notification_status_ok");
    private SelenideElement error = $(".notification_status_error");
    private SelenideElement invalidData = $(".input__sub");

    public BuyPage() {
        heading.shouldBe(visible);
    }

    public void fillForm(CardInfo cardInfo) {
        cardNumber.setValue(cardInfo.getNumber());
        month.setValue(cardInfo.getMonth());
        year.setValue(cardInfo.getYear());
        client.setValue(cardInfo.getClient());
        cvc.setValue(cardInfo.getCvc());
        continueButton.click();
    }

    public void successStatus() {
        success.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void errorStatus() {
        error.shouldBe(visible, Duration.ofSeconds(15));
    }

    public String invalidData() {
        return invalidData.getText();
    }
}