package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.CardInfo;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CreditPage {

    private SelenideElement heading = $(withText("Кредит по данным карты"));
    private SelenideElement cardNumber = $("[placeholder='0000 0000 0000 0000']");
    private SelenideElement month = $("[placeholder='08']");
    private SelenideElement year = $("[placeholder='22']");
    private SelenideElement client = $(byText("Владелец")).parent().$(".input__control");
    private SelenideElement cvc = $("[placeholder='999']");
    private SelenideElement continueButton = $$(".button_theme_alfa-on-white .button__text").findBy(exactText("Продолжить"));
    private SelenideElement success = $(".notification_status_ok");
    private SelenideElement error = $(".notification_status_error");
    private SelenideElement formatError = $(".input__sub");

    public CreditPage() {
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

    public void getSuccessStatus() {
        success.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void getErrorStatus() {
        error.shouldBe(visible, Duration.ofSeconds(15));
    }

    public String getWrongFormat() {
        return formatError.getText();
    }

}
