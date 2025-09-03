package model;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class Order {
    private String firstName;
    private String lastName;
    private String address;
    private String metroStation;
    private String phone;
    private int rentTime;
    private String deliveryDate;
    private String comment;
    private List<String> color;

    public Order(String firstName, String lastName, String address, String metroStation,
                 String phone, int rentTime, String deliveryDate, String comment, List<String> color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }

    public static Order random() {
        String unique = UUID.randomUUID().toString().substring(0, 5);
        return new Order("Иван" + unique, "Иванов", "Москва", "4", "+79999999999",
                5, "2025-09-05", "Позвонить", null);
    }

    public static Order randomWithColors(String[] colors) {
        String unique = UUID.randomUUID().toString().substring(0, 5);
        return new Order("Иван" + unique, "Иванов", "Москва", "4", "+79999999999",
                5, "2025-09-05", "Позвонить", Arrays.asList(colors));
    }
}
