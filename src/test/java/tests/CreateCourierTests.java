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

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class CreateCourierTests extends BaseTest {
    private final CourierSteps courierSteps = new CourierSteps();
    private Courier courier;
    private Integer courierId;

    @After
    public void tearDown() {
        if (courierId != null) {
            courierSteps.deleteCourier(courierId);
        }
    }

    @Test
    @DisplayName("Создание курьера")
    @Description("Курьер может быть успешно создан")
    public void courierCanBeCreatedTest() {
        courier = Courier.random();
        Response response = courierSteps.createCourier(courier);
        response.then().statusCode(HttpStatus.SC_CREATED).body("ok", equalTo(true));

        courierId = courierSteps.loginCourier(new CourierCredentials(courier.getLogin(), courier.getPassword()))
                .then().extract().path("id");
    }

    @Test
    @DisplayName("Создание двух курьеров с одинаковым логином")
    @Description("Нельзя создать двух курьеров с одним логином")
    public void cannotCreateTwoCouriersWithSameLoginTest() {
        courier = Courier.random();
        courierSteps.createCourier(courier);

        Response response = courierSteps.createCourier(courier);
        response.then().statusCode(HttpStatus.SC_CONFLICT)
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));

        courierId = courierSteps.loginCourier(new CourierCredentials(courier.getLogin(), courier.getPassword()))
                .then().extract().path("id");
    }

    @Test
    @DisplayName("Создание курьера без обязательных полей")
    @Description("Если отсутствует login или password, возвращается ошибка")
    public void cannotCreateCourierWithoutRequiredFieldsTest() {
        courier = new Courier(null, "1234", "NoLogin");
        Response response = courierSteps.createCourier(courier);
        response.then().statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }
}