package onlinefooddeliveryapp.onlinefooddelivery.dao.repository;


import onlinefooddeliveryapp.onlinefooddelivery.dao.model.Restaurants;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestaurantRepository extends MongoRepository<Restaurants, String> {
    Optional<Restaurants> findRestaurantsByRestaurantName(String restaurantName);
    Optional<Restaurants> findRestaurantsByRestaurantId(String restaurantId);
}
