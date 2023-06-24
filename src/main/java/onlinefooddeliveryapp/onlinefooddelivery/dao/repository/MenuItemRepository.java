package onlinefooddeliveryapp.onlinefooddelivery.dao.repository;


import onlinefooddeliveryapp.onlinefooddelivery.dao.model.MenuItems;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MenuItemRepository extends MongoRepository<MenuItems, String> {
    Optional<MenuItems> findMenuItemsByMenuName(String menuName);
}
