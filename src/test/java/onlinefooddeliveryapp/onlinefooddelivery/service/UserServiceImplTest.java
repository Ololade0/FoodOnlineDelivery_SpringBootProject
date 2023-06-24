package onlinefooddeliveryapp.onlinefooddelivery.service;


import onlinefooddeliveryapp.onlinefooddelivery.dao.model.Users;
import onlinefooddeliveryapp.onlinefooddelivery.dto.request.SignUpUserRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
class UserServiceImplTest {
    Users registeredUser;



    @Autowired
    private UserService userServices;

    @BeforeEach
    void setUp() {

        SignUpUserRequest signUpUserRequest = SignUpUserRequest.builder()

                .email("adesuyiololad@gmail.com")
                .firstName("Ololade")
                .lastName("Demilade")
                .phoneNo("08109093828")
                .password("12345")
                .build();
        registeredUser =   userServices.signUpUser(signUpUserRequest);
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void userCanBeRegister(){

        SignUpUserRequest signUpUserRequest = SignUpUserRequest.builder()
                .email("adesuyiololad@gmail.com")
                .firstName("Ololade")
                .lastName("Demilade")
                .phoneNo("08109093828")
                .password("12345")
                .build();
       Users registeredUser =   userServices.signUpUser(signUpUserRequest);
        assertThat(registeredUser.getId()).isNotNull();
        System.out.println(registeredUser);

    }


}
