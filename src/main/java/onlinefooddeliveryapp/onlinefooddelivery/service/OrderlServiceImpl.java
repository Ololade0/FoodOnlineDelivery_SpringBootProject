package onlinefooddeliveryapp.onlinefooddelivery.service;

import lombok.RequiredArgsConstructor;
import onlinefooddeliveryapp.onlinefooddelivery.dao.model.Address;
import onlinefooddeliveryapp.onlinefooddelivery.dao.model.Order;
import onlinefooddeliveryapp.onlinefooddelivery.dao.repository.OrderRepository;
import onlinefooddeliveryapp.onlinefooddelivery.dto.request.FindAllOrderRequest;
import onlinefooddeliveryapp.onlinefooddelivery.dto.request.PlaceOrderRequest;
import onlinefooddeliveryapp.onlinefooddelivery.dto.request.UpdateOrderRequest;
import onlinefooddeliveryapp.onlinefooddelivery.exception.OrderAlreadyExistException;
import onlinefooddeliveryapp.onlinefooddelivery.exception.OrderCannotBeFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderlServiceImpl implements OrderService{
	private final OrderRepository orderRepository;
	private final AddressService addressService;
	
	

	
	@Override
	public Order placedOrders(PlaceOrderRequest placeOrderRequest) throws OrderAlreadyExistException {
//		Optional<Order> existingOrder = orderRepository.findById(placeOrderRequest.getOrderId());
//		if(existingOrder.isPresent()){
//			throw new OrderAlreadyExistException("Order already exist");
//		}
		Order placedOrder = Order.builder()
				.quantity(placeOrderRequest.getQuantity())
				.deliveryTime(placeOrderRequest.getDeliveryTime())
				.totalPrice(calculateTotalPrice(placeOrderRequest))
				.itemPrice(placeOrderRequest.getItemPrice())
				.ordered_at(placeOrderRequest.getOrdered_at())
				.orderStatus(placeOrderRequest.getOrderStatus())
				.address(placeOrderRequest.getAddress())
				.build();
	Address addressDetails = addressService.saveAddress(placedOrder.getAddress());
		placedOrder.setOrderId(placedOrder.getOrderId());
		placedOrder.setAddress(addressDetails);
		return orderRepository.save(placedOrder);

	}

	public BigDecimal calculateTotalPrice(PlaceOrderRequest placeOrderRequest) {
		BigDecimal total = BigDecimal.ZERO;
		if (placeOrderRequest.getTotalPrice() != null && placeOrderRequest.getQuantity() != null) {
			total = placeOrderRequest.getTotalPrice().multiply(BigDecimal.valueOf(placeOrderRequest.getQuantity()));
		}
		return total;
	}

	@Override
	public Order retriveOrder(String orderId) throws OrderCannotBeFoundException {
		Optional<Order> foundOrder  = orderRepository.findById(orderId);
		if(foundOrder.isPresent()) {
			return foundOrder.get();
		}
		else {
			throw new OrderCannotBeFoundException("Order with " + orderId + " cannot be found");
		}
	}

	@Override
	public String deleteOrder(String orderId) throws OrderCannotBeFoundException {
		Optional<Order> orderToBeDeleted = orderRepository.findById(orderId);
		if (orderToBeDeleted.isPresent()) {
			orderRepository.delete(orderToBeDeleted.get());
			return "Orders successfully removed";
		} else {

			throw new OrderCannotBeFoundException("Order with " + orderId + " cannot be found");
		}

	}

	@Override
	public String deleteAllOrders() {
		orderRepository.deleteAll();
		return "All Orders sucessfully deleted";
	}


	@Override
	public Page<Order> retrieveAllOrders(FindAllOrderRequest findAllOrder) {
		Pageable pageable = PageRequest.of(findAllOrder.getPages() - 1, findAllOrder.getNumberOfPages());
		return orderRepository.findAll(pageable);
	}


	@Override
	public Order updateOrder(UpdateOrderRequest updateOrderRequest, String orderId) throws OrderCannotBeFoundException {
		Optional<Order> updatedOrder = orderRepository.findById(orderId);
		if (updatedOrder.isPresent()) {
				updatedOrder.get().setAddress(updateOrderRequest.getAddress());
				updatedOrder.get().setOrderStatus(updateOrderRequest.getOrderStatus());
				updatedOrder.get().setDeliveryTime(updateOrderRequest.getUpdatedAt());
				updatedOrder.get().setItemPrice(updateOrderRequest.getTotalPrice());
				orderRepository.save(updatedOrder.get());
				return updatedOrder.get();
			}


		else {
			throw new OrderCannotBeFoundException("Order with " + orderId + " cannot be found");
		}
	}








}
