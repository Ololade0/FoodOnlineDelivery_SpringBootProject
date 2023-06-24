package onlinefooddeliveryapp.onlinefooddelivery.service;

;
import lombok.RequiredArgsConstructor;
import onlinefooddeliveryapp.onlinefooddelivery.dao.model.Role;
import onlinefooddeliveryapp.onlinefooddelivery.dao.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService{
    private final RoleRepository roleRepository;
    @Override
    public Role save(Role userRole) {
        return roleRepository.save(userRole);
    }
}
