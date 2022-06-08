package ru.netology.web.page;

import org.openqa.selenium.Keys;
import ru.netology.web.data.Datahelper;

import static com.codeborne.selenide.Selenide.$;

public class TransferMoney {
    public void transfer(Datahelper.CardInfo cardInfo, int amount) {
        $("[data-test-id=amount] input").sendKeys(Keys.CONTROL + "A", Keys.BACK_SPACE);
        $("[data-test-id=amount] input").setValue(String.valueOf(amount));
        $("[data-test-id=from] input").sendKeys(Keys.CONTROL + "A", Keys.BACK_SPACE);
        $("[data-test-id=from] input").setValue(cardInfo.getCardNumber());
        $("[data-test-id =action-transfer]").click();
    }
}
