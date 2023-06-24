package onlinefooddeliveryapp.onlinefooddelivery.service;

import onlinefooddeliveryapp.onlinefooddelivery.dao.model.Address;
import onlinefooddeliveryapp.onlinefooddelivery.dao.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressRepository addressRepository;
    @Override
    public Address saveAddress(Address address) {
        Address address1 = Address.builder()
                .addressId(address.getAddressId())
                .state(address.getState())
                .houseNumber(address.getHouseNumber())
                .country(address.getCountry())
                .city(address.getCity())
                .area(address.getArea())
                .build();
        return addressRepository.save(address1);

    }
}
