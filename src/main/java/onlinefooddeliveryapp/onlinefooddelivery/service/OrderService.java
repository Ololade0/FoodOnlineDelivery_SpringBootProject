package onlinefooddeliveryapp.onlinefooddelivery.service;


import onlinefooddeliveryapp.onlinefooddelivery.dao.model.Order;
import onlinefooddeliveryapp.onlinefooddelivery.dto.request.FindAllOrderRequest;
import onlinefooddeliveryapp.onlinefooddelivery.dto.request.PlaceOrderRequest;
import onlinefooddeliveryapp.onlinefooddelivery.dto.request.UpdateOrderRequest;
import onlinefooddeliveryapp.onlinefooddelivery.exception.OrderCannotBeFoundException;
import org.springframework.data.domain.Page;

public interface OrderService {
    Order placedOrders(PlaceOrderRequest placeOrderRequest) throws OrderCannotBeFoundException, OrderAlreadyExistException;

    Order retriveOrder(String orderId) throws OrderCannotBeFoundException;

    String deleteOrder(String orderId) throws OrderCannotBeFoundException;

    String deleteAllOrders();

    Page<Order> retrieveAllOrders(FindAllOrderRequest findAllOrder);

    Order updateOrder(UpdateOrderRequest updateOrderRequest, String orderId) throws OrderCannotBeFoundException;

}


