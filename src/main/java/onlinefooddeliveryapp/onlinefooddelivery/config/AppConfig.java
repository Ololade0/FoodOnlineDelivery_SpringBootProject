package onlinefooddeliveryapp.onlinefooddelivery.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;



@Configuration
public class AppConfig {
    @Bean
    public LocalValidatorFactoryBean validatorFactoryBean(){

        return new LocalValidatorFactoryBean();
    }

}