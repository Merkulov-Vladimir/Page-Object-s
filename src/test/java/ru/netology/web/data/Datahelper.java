package ru.netology.web.data;

import lombok.Value;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$$;

public class Datahelper {
    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }



    @Value
    public static class CardInfo {
        public String cardNumber;
    }

    public static CardInfo getFirstNumber() {
        return new CardInfo("5559000000000001");
    }

    public static CardInfo getSecondNumber() {
        return new CardInfo("5559000000000002");
    }
}

