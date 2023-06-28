package onlinefooddeliveryapp.onlinefooddelivery.controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import onlinefooddeliveryapp.onlinefooddelivery.dao.model.Order;
import onlinefooddeliveryapp.onlinefooddelivery.dto.request.FindAllOrderRequest;
import onlinefooddeliveryapp.onlinefooddelivery.dto.request.PlaceOrderRequest;
import onlinefooddeliveryapp.onlinefooddelivery.dto.request.UpdateOrderRequest;
import onlinefooddeliveryapp.onlinefooddelivery.exception.OrderAlreadyExistException;
import onlinefooddeliveryapp.onlinefooddelivery.exception.OrderCannotBeFoundException;
import onlinefooddeliveryapp.onlinefooddelivery.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class OrderController {
    private final OrderService orderService;


    @PostMapping("/order")
    public ResponseEntity<?> placeOrder(@NonNull @RequestBody PlaceOrderRequest placeOrderRequest) throws OrderCannotBeFoundException, OrderAlreadyExistException {
        Order placedOrders = orderService.placedOrders(placeOrderRequest);
        return new ResponseEntity<>(placedOrders, HttpStatus.CREATED);
    }

    @GetMapping("order/{orderId}")
    public ResponseEntity<?> findOrderById(@PathVariable  String orderId) throws OrderCannotBeFoundException{
        Order placedOrders = orderService.retriveOrder(orderId);
        return new ResponseEntity<>(placedOrders, HttpStatus.CREATED);
    }

    @GetMapping("findAllOrders")
    public ResponseEntity<?> findOrderAllOrders(@RequestBody FindAllOrderRequest findAllOrderRequest){
        Page<Order> placedOrders = orderService.retrieveAllOrders(findAllOrderRequest);
        return new ResponseEntity<>(placedOrders, HttpStatus.CREATED);
    }


    @DeleteMapping("deletedorder/{orderId}")
    public ResponseEntity<?> deleteOrderById(@PathVariable  String orderId) throws OrderCannotBeFoundException{
        String deletedOrders = orderService.deleteOrder(orderId);
        return new ResponseEntity<>(deletedOrders, HttpStatus.CREATED);
    }

    @DeleteMapping("deletedAllOrder")
    public ResponseEntity<?> deleteAllOrders() {
        String deletedOrders = orderService.deleteAllOrders();
        return new ResponseEntity<>(deletedOrders, HttpStatus.CREATED);
    }

    @PutMapping("upadteOrder/{orderId}")
    public ResponseEntity<?> upadatedOrders(@RequestBody UpdateOrderRequest updateOrderRequest, @PathVariable String orderId) throws OrderCannotBeFoundException {
        Order updatedOrders = orderService.updateOrder(updateOrderRequest, orderId);
        return new ResponseEntity<>(updatedOrders, HttpStatus.CREATED);
    }

}
