package steps;

import client.OrderClient;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import model.Order;

public class OrderSteps {
    private final OrderClient orderClient = new OrderClient();

    @Step("Создать заказ (steps)")
    public Response createOrder(Order order) {
        return orderClient.createOrder(order);
    }

    @Step("Отменить заказ по треку (steps)")
    public Response cancelOrder(int track) {
        return orderClient.cancelOrder(track);
    }

    @Step("Принять заказ (steps)")
    public Response acceptOrder(int courierId, int orderId) {
        return orderClient.acceptOrder(courierId, orderId);
    }

    @Step("Получить заказ по треку (steps)")
    public Response getOrderByTrack(int track) {
        return orderClient.getOrderByTrack(track);
    }

    @Step("Получить список заказов (steps)")
    public Response getOrdersList() {
        return orderClient.getOrdersList();
    }
}