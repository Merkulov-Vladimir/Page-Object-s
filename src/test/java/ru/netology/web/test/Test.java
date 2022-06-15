package ru.netology.web.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.junit.jupiter.api.BeforeEach;
import ru.netology.web.data.Datahelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPage;
import ru.netology.web.page.TransferMoney;
import ru.netology.web.page.VerificationPage;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test {


    @BeforeEach
        // открытие страницы и вход в систему
    void setup() {
        open("http://localhost:9999");
        var login = new LoginPage();
        var authInfo = Datahelper.getAuthInfo();
        login.validLogin(authInfo);
        var verificationCode = Datahelper.getVerificationCodeFor(authInfo);
        var dashboardPage = new VerificationPage();
        dashboardPage.validVerify(verificationCode);
    }

    @org.junit.jupiter.api.Test
    void shouldTransferMoneyFromSecondToFirst() {
        // Configuration.holdBrowserOpen = true;
        DashboardPage item = new DashboardPage();
        var secondNumber = Datahelper.getSecondNumber();
        int amount = 8000;//сумма перевода
        int balanceFirstCardBefore = item.getCardBalance(1);
        int balanceSecondCardBefore = item.getCardBalance(2);
        item.transferMoney(0);
        //var transferPage = dashboardPage.selectCardToTransfer(secondNumber);
        var dashboardPage = new TransferMoney();
        dashboardPage.transfer(secondNumber, amount);
        //$("[data-test-id=dashboard]").shouldHave(Condition.text("Личный кабинет"));
        int balanceFirstCard = item.getCardBalance(1);
        int balanceSecondCard = item.getCardBalance(2);
        int expectedBalanceFirstCard = balanceFirstCardBefore + amount;
        int expectedBalanceSecondCard = balanceSecondCardBefore - amount;
        assertEquals(expectedBalanceFirstCard, balanceFirstCard);
        assertEquals(expectedBalanceSecondCard, balanceSecondCard);
    }

    @org.junit.jupiter.api.Test
    void shouldTransferMoneyFromFirstToSecond() {
        DashboardPage item = new DashboardPage();
        var firstNumber = Datahelper.getFirstNumber();
        int amount = 1000;//сумма перевода
        int balanceFirstCardBefore = item.getCardBalance(1);
        int balanceSecondCardBefore = item.getCardBalance(2);
        item.transferMoney(1);
        //var transferPage = dashboardPage.selectCardToTransfer(secondNumber);
        var dashboardPage = new TransferMoney();
        dashboardPage.transfer(firstNumber, amount);
        //$("[data-test-id=dashboard]").shouldHave(Condition.text("Личный кабинет"));
        int balanceFirstCard = item.getCardBalance(1);
        int balanceSecondCard = item.getCardBalance(2);
        int expectedBalanceFirstCard = balanceFirstCardBefore - amount;
        int expectedBalanceSecondCard = balanceSecondCardBefore + amount;
        assertEquals(expectedBalanceFirstCard, balanceFirstCard);
        assertEquals(expectedBalanceSecondCard, balanceSecondCard);
    }
}
