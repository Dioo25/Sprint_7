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

    public Order() { }

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

    // фабричный метод
    public static Order random() {
        String uniq = UUID.randomUUID().toString().substring(0, 6);
        return new Order("Имя" + uniq, "Фам" + uniq, "Москва, ул. " + uniq, "1",
                "+7999" + uniq, 1, "2025-09-05", "Комментарий", null);
    }

    public static Order randomWithColors(String[] colors) {
        Order order = random();
        if (colors != null) {
            order.setColor(Arrays.asList(colors));
        }
        return order;
    }

    // геттеры / сеттеры (rest-assured / gson/ jackson)
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getAddress() { return address; }
    public String getMetroStation() { return metroStation; }
    public String getPhone() { return phone; }
    public int getRentTime() { return rentTime; }
    public String getDeliveryDate() { return deliveryDate; }
    public String getComment() { return comment; }
    public List<String> getColor() { return color; }

    public void setColor(List<String> color) { this.color = color; }
}
