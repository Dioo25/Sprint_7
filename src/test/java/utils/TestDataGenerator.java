package utils;

import model.Courier;
import model.Order;

import java.util.Collections;
import java.util.Random;

public class TestDataGenerator {

    private static final Random random = new Random();

    public static Courier getRandomCourier() {
        String login = "courier" + random.nextInt(100000);
        String password = "pass" + random.nextInt(100000);
        String firstName = "name" + random.nextInt(100000);
        return new Courier(login, password, firstName);
    }

    public static Order getDefaultOrder() {
        return new Order(
                "Иван",
                "Иванов",
                "Москва, Красная площадь, 1",
                "Сокольники",
                "+79999999999",
                5,
                "2025-09-01",
                "Позвонить за час",
                Collections.singletonList("BLACK")
        );
    }
}
