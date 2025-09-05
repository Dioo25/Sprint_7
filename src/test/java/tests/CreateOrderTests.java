package tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import model.Order;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import steps.OrderSteps;
import utils.BaseTest;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.Matchers.notNullValue;

@RunWith(Parameterized.class)
@DisplayName("Создание заказов (параметризация по цветам)")
public class CreateOrderTests extends BaseTest {
    private final OrderSteps orderSteps = new OrderSteps();
    private Integer track;

    // параметр — массив цветов (null / one / two)
    @Parameterized.Parameters(name = "{index}: colors={0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                { (String[]) null },             // без цвета
                { new String[]{"BLACK"} },       // BLACK
                { new String[]{"GREY"} },        // GREY
                { new String[]{"BLACK", "GREY"} } // оба цвета
        });
    }

    private final String[] colors;

    public CreateOrderTests(String[] colors) {
        this.colors = colors;
    }

    @After
    public void tearDown() {
        // у нас нет удобного API для удаления заказа — пропускаем
        track = null;
    }

    @Test
    @DisplayName("Создание заказа с параметризацией цветов")
    @Description("Проверяем, что заказ можно создать с разными комбинациями цветов")
    public void orderCanBeCreatedWithColorsTest() {
        Order order = (colors == null) ? Order.random() : Order.randomWithColors(colors);
        Response response = orderSteps.createOrder(order);
        response.then().statusCode(HttpStatus.SC_CREATED).body("track", notNullValue());

        track = response.then().extract().path("track");
    }
}