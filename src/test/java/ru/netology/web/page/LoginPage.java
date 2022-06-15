package ru.netology.web.page;

import ru.netology.web.data.Datahelper;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    public LoginPage validLogin(Datahelper.AuthInfo info) {
        $("[name=login]").setValue(info.getLogin());
        $("[name=password]").setValue(info.getPassword());
        $("button").click();
        return this;
    }

//    public void validVerify(Datahelper.VerificationCode verificationCode) {
//        $("[name=code]").setValue(verificationCode.getCode());
//        $("button").click();
//    }
}
