package ru.netology.web.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.junit.jupiter.api.BeforeEach;
import ru.netology.web.data.Datahelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPage;
import ru.netology.web.page.TransferMoney;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test {
    DashboardPage item = new DashboardPage();

    @BeforeEach
        // открытие страницы и вход в систему
    void setup() {
        open("http://localhost:9999");
        var login = new LoginPage();
        var authInfo = Datahelper.getAuthInfo();
        var verificationCode = Datahelper.getVerificationCodeFor(authInfo);
        login.validLogin(authInfo).validVerify(verificationCode);
    }

    @org.junit.jupiter.api.Test
    void shouldTransferMoneyFromSecondToFirst() {
       // Configuration.holdBrowserOpen = true;
        var secondNumber = Datahelper.getSecondNumber();
        int amount = 8000;//сумма перевода
        int balanceFirstCardBefore = item.getCardBalance(1);
        int balanceSecondCardBefore = item.getCardBalance(2);
        $$(withText("Пополнить")).get(0).click();
        //var transferPage = dashboardPage.selectCardToTransfer(secondNumber);
        var dashboardPage = new TransferMoney();
        dashboardPage.transfer(secondNumber, amount);
        $("[data-test-id=dashboard]").shouldHave(Condition.text("Личный кабинет"));
        int balanceFirstCard = item.getCardBalance(1);
        int balanceSecondCard = item.getCardBalance(2);
        int expectedBalanceFirstCard = balanceFirstCardBefore + amount;
        int expectedBalanceSecondCard = balanceSecondCardBefore - amount;
        assertEquals(expectedBalanceFirstCard, balanceFirstCard);
        assertEquals(expectedBalanceSecondCard, balanceSecondCard);
    }

    @org.junit.jupiter.api.Test
    void shouldTransferMoneyFromFirstToSecond() {
        var firstNumber = Datahelper.getFirstNumber();
        int amount = 1000;//сумма перевода
        int balanceFirstCardBefore = item.getCardBalance(1);
        int balanceSecondCardBefore = item.getCardBalance(2);
        $$(withText("Пополнить")).get(1).click();
        //var transferPage = dashboardPage.selectCardToTransfer(secondNumber);
        var dashboardPage = new TransferMoney();
        dashboardPage.transfer(firstNumber, amount);
        $("[data-test-id=dashboard]").shouldHave(Condition.text("Личный кабинет"));
        int balanceFirstCard = item.getCardBalance(1);
        int balanceSecondCard = item.getCardBalance(2);
        int expectedBalanceFirstCard = balanceFirstCardBefore - amount;
        int expectedBalanceSecondCard = balanceSecondCardBefore + amount;
        assertEquals(expectedBalanceFirstCard, balanceFirstCard);
        assertEquals(expectedBalanceSecondCard, balanceSecondCard);
    }
}
