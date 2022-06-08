package ru.netology.web.page;

import com.codeborne.selenide.ElementsCollection;
import lombok.val;

import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private ElementsCollection cards = $$(".list__item div");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

//    public DashboardPage() {
//    }

    public int getCardBalance(int cardNumber) {
        if (cardNumber == 1) {
            val text = cards.first().text();
            return extractBalance(text);
        }
        if (cardNumber == 2) {
            val text = cards.last().text();
            return extractBalance(text);
        }
        return 0;
    }


    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);

    }
}
