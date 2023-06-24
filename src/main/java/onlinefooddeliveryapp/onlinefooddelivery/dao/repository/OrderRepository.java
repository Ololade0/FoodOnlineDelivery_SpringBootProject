package onlinefooddeliveryapp.onlinefooddelivery.dao.repository;


import onlinefooddeliveryapp.onlinefooddelivery.dao.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
}
