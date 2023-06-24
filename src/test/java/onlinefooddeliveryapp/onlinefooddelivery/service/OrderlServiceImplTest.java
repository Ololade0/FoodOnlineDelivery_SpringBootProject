package onlinefooddeliveryapp.onlinefooddelivery.service;


import onlinefooddeliveryapp.onlinefooddelivery.dao.model.Address;
import onlinefooddeliveryapp.onlinefooddelivery.dao.model.Order;
import onlinefooddeliveryapp.onlinefooddelivery.dao.model.OrderStatus;
import onlinefooddeliveryapp.onlinefooddelivery.dto.request.FindAllOrderRequest;
import onlinefooddeliveryapp.onlinefooddelivery.dto.request.PlaceOrderRequest;
import onlinefooddeliveryapp.onlinefooddelivery.dto.request.UpdateOrderRequest;
import onlinefooddeliveryapp.onlinefooddelivery.exception.OrderAlreadyExistException;
import onlinefooddeliveryapp.onlinefooddelivery.exception.OrderCannotBeFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
class OrderlServiceImplTest {

    Order savedOrder;

    @Autowired
    private OrderService orderService;
    @BeforeEach
    void setUp() throws OrderCannotBeFoundException, OrderAlreadyExistException {
        PlaceOrderRequest placeOrderRequest = PlaceOrderRequest.builder()
                .quantity(10)
                .ordered_at(LocalDateTime.now())
                .orderStatus(OrderStatus.PLACED_ORDER)
                .totalPrice(BigDecimal.valueOf(2000))
                .deliveryTime(LocalDateTime.now())
                .address(Address.builder()
                        .area("Yaba Area")
                        .city("Lagos")
                        .country("Nigeria")
                        .houseNumber("No 31")
                        .state("Lagos")
                        .build())
                .build();
        savedOrder =  orderService.placedOrders(placeOrderRequest);

    }

    @AfterEach
    void tearDown() {
        orderService.deleteAllOrders();

    }

        @Test
        void orderCanBeSaved() throws OrderCannotBeFoundException, OrderAlreadyExistException {
            PlaceOrderRequest placeOrderRequest = PlaceOrderRequest.builder()
                    .quantity(10)
                    .ordered_at(LocalDateTime.now())
                    .orderStatus(OrderStatus.PLACED_ORDER)
                    .totalPrice(BigDecimal.valueOf(2000))
                    .deliveryTime(LocalDateTime.now())
                    .address(Address.builder()
                            .area("Yaba Area")
                            .city("Lagos")
                            .country("Nigeria")
                            .houseNumber("No 31")
                            .state("Lagos")
                            .build())
                    .build();
            orderService.placedOrders(placeOrderRequest);

            assertEquals(OrderStatus.PLACED_ORDER,savedOrder.getOrderStatus());
            assertEquals(BigDecimal.valueOf(2000),savedOrder.getTotalPrice());
            assertEquals("Nigeria", savedOrder.getAddress().getCountry());
            assertEquals("No 31", savedOrder.getAddress().getHouseNumber());
            assertThat(savedOrder.getId()).isNotNull();
            System.out.println(savedOrder);


    }

    @Test
    void retriveOrder() throws OrderCannotBeFoundException {
        Order foundOrder = orderService.retriveOrder(savedOrder.getId());
        assertThat(foundOrder.getId()).isEqualTo(savedOrder.getId());
        assertThat(foundOrder).isNotNull();

    }

    @Test
    void deleteOrder() throws OrderCannotBeFoundException {
     String deletedOrder =   orderService.deleteOrder(savedOrder.getId());
        assertEquals("Orders successfully removed", deletedOrder);

    }

    @Test
    void deleteAllOrders() {
        String deletedOrder =  orderService.deleteAllOrders();
        assertEquals("All Orders sucessfully deleted", deletedOrder);

    }

    @Test
    void retrieveAllOrders() {
        FindAllOrderRequest findAllOrderRequest = FindAllOrderRequest.builder()
                .numberOfPages(1)
                .pages(1)
                .build();
        Page<Order> foundOrder =
        orderService.retrieveAllOrders(findAllOrderRequest);
        assertThat(foundOrder.getTotalElements()).isNotNull();
        assertEquals(1L, foundOrder.getTotalElements());
    }


    @Test
    void updateOrder() throws OrderCannotBeFoundException {
        UpdateOrderRequest updateOrder = UpdateOrderRequest.builder()
                .orderId(savedOrder.getId())
                .updatedAt(LocalDateTime.now())
                .orderStatus(OrderStatus.PACKAGED)
                .totalPrice(BigDecimal.valueOf(3000))
                .address(Address.builder()
                        .area("Lekki Area")
                        .city("Ibadan")
                        .country("Ghana")
                        .houseNumber("No 32")
                        .state("Oyo")
                        .build())
                .build();
        Order updatedOrder =  orderService.updateOrder(updateOrder, updateOrder.getOrderId());
        assertEquals("Lekki Area", updatedOrder.getAddress().getArea());
        assertEquals(OrderStatus.PACKAGED, updatedOrder.getOrderStatus());
        assertEquals(BigDecimal.valueOf(3000), updatedOrder.getTotalPrice());

        System.out.println("updated order is" + updatedOrder);



    }
}
