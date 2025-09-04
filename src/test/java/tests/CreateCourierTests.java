package tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import model.Courier;
import model.CourierCredentials;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Test;
import steps.CourierSteps;
import utils.BaseTest;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class CreateCourierTests extends BaseTest {
    private final CourierSteps courierSteps = new CourierSteps();
    private Courier courier;
    private Integer courierId;

    @After
    public void tearDown() {
        if (courier != null) {
            try {
                Response loginResp = courierSteps.loginCourier(new CourierCredentials(courier.getLogin(), courier.getPassword()));
                if (loginResp.statusCode() == HttpStatus.SC_OK) {
                    int id = loginResp.then().extract().path("id");
                    courierSteps.deleteCourier(id);
                }
            } catch (Exception ignored) {
                // if login/delete fails — ничего не делаем, тест всё равно упал/завершился
            }
        }
    }

    @Test
    @DisplayName("Создание курьера")
    @Description("Курьер может быть успешно создан")
    public void courierCanBeCreatedTest() {
        courier = Courier.random();
        Response response = courierSteps.createCourier(courier);
        response.then().statusCode(HttpStatus.SC_CREATED).body("ok", equalTo(true));
    }

    @Test
    @DisplayName("Нельзя создать двух курьеров с одинаковым логином")
    @Description("При создании пользователя с уже существующим login возвращается ошибка")
    public void cannotCreateTwoCouriersWithSameLoginTest() {
        courier = Courier.random();
        courierSteps.createCourier(courier); // первый раз
        Response response = courierSteps.createCourier(courier); // второй раз
        response.then().statusCode(HttpStatus.SC_CONFLICT)
                .body("message", containsString("Этот логин уже используется"));
    }

    @Test
    @DisplayName("Создание курьера без login")
    @Description("Если отсутствует login, возвращается ошибка")
    public void cannotCreateCourierWithoutLoginTest() {
        // не задаём логин
        courier = new Courier(null, "password123", "NoLogin");
        Response response = courierSteps.createCourier(courier);
        response.then().statusCode(HttpStatus.SC_BAD_REQUEST).body("message", notNullValue());
        // не будем пытаться удалить несуществующего курьера
        courier = null;
    }

    @Test
    @DisplayName("Создание курьера без password")
    @Description("Если отсутствует password, возвращается ошибка")
    public void cannotCreateCourierWithoutPasswordTest() {
        courier = new Courier("user_no_password_" + System.currentTimeMillis(), null, "NoPass");
        Response response = courierSteps.createCourier(courier);
        response.then().statusCode(HttpStatus.SC_BAD_REQUEST).body("message", notNullValue());
        courier = null;
    }
}