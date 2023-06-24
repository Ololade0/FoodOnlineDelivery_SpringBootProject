package onlinefooddeliveryapp.onlinefooddelivery.config;


import lombok.extern.slf4j.Slf4j;
import onlinefooddeliveryapp.onlinefooddelivery.dao.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SetUpDataLoader implements ApplicationListener<ContextRefreshedEvent>{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder(){

        return new BCryptPasswordEncoder();
    }



    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
//        if (userRepository.findUserByEmail("adesuyi@gmail.com").isEmpty()){
//            User user = new User("Ololade", "Ola","ololade@gmail.com", bCryptPasswordEncoder().encode("12345"), "12345", "Sabo", RoleType.ROLE_USER);
//            userRepository.save(user);
//        }
    }
}