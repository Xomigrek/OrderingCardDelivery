package ru.netology.оrderingсarddelivery;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.conditions.Visible;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardDeliveryTest {
//    public String setCalendar(int days) {
//        Calendar calendar = new GregorianCalendar();
//        calendar.add(Calendar.DAY_OF_YEAR, days);
//        SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy");
//        String str = format1.format(calendar.getTime());
//        return str;
//    }
    public String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @Test
    void shouldRegisterOrder () {
        open("http://localhost:9999/");
        String planningDate = generateDate(4);
        $$("[type=\"text\"]").first().setValue("Санкт-Петербург");
        $x("//input[@placeholder=\"Дата встречи\"]").sendKeys(Keys.CONTROL + "A");
        $x("//input[@placeholder=\"Дата встречи\"]").sendKeys(Keys.BACK_SPACE);
        $x("//input[@placeholder=\"Дата встречи\"]").setValue(planningDate);
        $$("[type=\"text\"]").last().setValue("Хоменко Игорь");
        $x("//input[@name=\"phone\"]").setValue("+79119994143");
        $(".checkbox").click();
        $("button.button").click();
        $x("//div[@class=\"notification__content\"]").should(Condition.visible, Duration.ofSeconds(15));
        String text = $x("//div[@class=\"notification__content\"]").getText();
        assertEquals("Встреча успешно забронирована на " + planningDate,text.trim());

    }
}
