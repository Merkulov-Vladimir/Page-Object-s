package ru.netology.web.page;

import ru.netology.web.data.Datahelper;

import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    public void validVerify(Datahelper.VerificationCode verificationCode) {
        $("[name=code]").setValue(verificationCode.getCode());
        $("button").click();
    }
}
