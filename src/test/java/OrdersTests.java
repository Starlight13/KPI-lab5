import entities.Message;
import entities.Order;
import io.restassured.response.Response;

import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
@RunWith(SerenityRunner.class)
public class OrdersTests {

    @Test
    public void testCreateOrder() {
        Order order = new Order();
        order.setName("Test 6");
        order.setPrice(300);
        Response response = new ApiEndpoints().addOrder(order);
        response.then().statusCode(200);
    }

    @Test
    public void testUpdateExistedOrder() {
        Order order = new Order();
        order.setId(2);
        order.setName("Test 2");
        order.setPrice(222);
        Response response = new ApiEndpoints().updateOrder(order);
        response.then().statusCode(200);
    }

    @Test
    public void testUpdateNonExistingOrder() {
        Order order = new Order();
        order.setId(0);
        order.setName("Test 0");
        order.setPrice(222);
        Response response = new ApiEndpoints().updateOrder(order);
        Message message = response.body().as(Message.class);
        Assert.assertEquals(message.getMessage(), "No such order by this ID.");
    }

    @Test
    public void testGetOrderById() {
        Response response = new ApiEndpoints().getOrderById(1);
        Order order = response.body().as(Order.class);
        Assert.assertEquals(order.getName(), "Test 1");
    }

    @Test
    public void testGetAllOrders() {
        Response response = new ApiEndpoints().getAllOrders();
        response.then().statusCode(200);
    }

    @Test
    public void testDeleteExistingOrderById() {
        List<Order> orders = Arrays.asList(new ApiEndpoints().getAllOrders().body().as(Order[].class));

        orders.sort(Comparator.comparing(Order::getId));

        Response response = new ApiEndpoints().deleteOrder(orders.get(orders.size() - 1).getId());
        Message message = response.body().as(Message.class);
        Assert.assertEquals(message.getMessage(), "Order was deleted.");
    }

    @Test
    public void testDeleteNonExistingOrderById() {
        Response response = new ApiEndpoints().deleteOrder(0);
        Message message = response.body().as(Message.class);
        Assert.assertEquals(message.getMessage(), "No such order by this ID.");
    }

    @Test
    public void testDeleteNonExistingOrderByIdFailWithError() {
        Response response = new ApiEndpoints().deleteOrder(0);
        response.then().statusCode(500);
    }
}
