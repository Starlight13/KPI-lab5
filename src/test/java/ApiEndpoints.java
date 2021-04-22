import entities.Order;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApiEndpoints {

    public Response addOrder(Order order) {
        return given().
                body(order).
                post(ConfigPath.ORDER).
                then().extract().response();
    }

    public Response updateOrder(Order order) {
        return given().
                body(order).
                put(ConfigPath.ORDER).
                then().extract().response();
    }

    public Response deleteOrder(long id) {
        return given().
                pathParam("orderId", id).
                delete(ConfigPath.ORDER_WITH_PARAM).
                then().extract().response();
    }

    public Response getOrderById(long id) {
        return given().pathParam("orderId", id).
                get(ConfigPath.ORDER_WITH_PARAM).then().extract().response();
    }

    public Response getAllOrders() {
        return given().
                get(ConfigPath.ALL_ORDERS).then().extract().response();
    }

    private RequestSpecification given() {
        return RestAssured.given()
                .log().uri()
                .log().body()
                .baseUri(ConfigPath.BASE_URL)
                .contentType(ContentType.JSON);
    }
}
