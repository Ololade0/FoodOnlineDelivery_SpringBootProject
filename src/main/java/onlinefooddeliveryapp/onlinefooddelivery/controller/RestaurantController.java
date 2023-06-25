package onlinefooddeliveryapp.onlinefooddelivery.controller;

import lombok.RequiredArgsConstructor;
import onlinefooddeliveryapp.onlinefooddelivery.service.RestaurantService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class RestaurantController {
    private final RestaurantService restaurantService;
}
