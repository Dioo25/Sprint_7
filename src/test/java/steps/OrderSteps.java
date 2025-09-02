package steps;

import client.OrderClient;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import model.Order;

public class OrderSteps {
    OrderClient orderClient = new OrderClient();

    @Step("Создать заказ")
    public Response createOrder(Order order) {
        return orderClient.createOrder(order);
    }

    @Step("Получить список заказов")
    public Response getOrdersList() {
        return orderClient.getOrdersList();
    }

    @Step("Принять заказ")
    public Response acceptOrder(int courierId, int orderId) {
        return orderClient.acceptOrder(courierId, orderId);
    }

    @Step("Получить заказ по треку")
    public Response getOrderByTrack(int track) {
        return orderClient.getOrderByTrack(track);
    }
}