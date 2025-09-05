package steps;

import client.OrderClient;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import model.Order;

public class OrderSteps {
    private final OrderClient orderClient = new OrderClient();

    @Step("Шаг: создать заказ")
    public Response createOrder(Order order) {
        return orderClient.createOrder(order);
    }

    @Step("Шаг: получить список заказов")
    public Response getOrdersList() {
        return orderClient.getOrdersList();
    }

    @Step("Шаг: получить заказ по треку")
    public Response getOrderByTrack(int track) {
        return orderClient.getOrderByTrack(track);
    }

    @Step("Шаг: принять заказ")
    public Response acceptOrder(int courierId, int orderId) {
        return orderClient.acceptOrder(courierId, orderId);
    }
}