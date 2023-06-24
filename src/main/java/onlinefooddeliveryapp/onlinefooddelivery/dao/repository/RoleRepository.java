package onlinefooddeliveryapp.onlinefooddelivery.dao.repository;


import onlinefooddeliveryapp.onlinefooddelivery.dao.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends MongoRepository<Role, String> {
}
