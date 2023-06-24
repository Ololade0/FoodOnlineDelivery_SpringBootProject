package onlinefooddeliveryapp.onlinefooddelivery.dao.repository;

import onlinefooddeliveryapp.onlinefooddelivery.dao.model.Users;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<Users, String> {
    Optional<Users> findUsersByEmail(String email);
}
