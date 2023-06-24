package onlinefooddeliveryapp.onlinefooddelivery.dao.repository;

import onlinefooddeliveryapp.onlinefooddelivery.dao.model.Address;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends MongoRepository<Address, String> {
}
