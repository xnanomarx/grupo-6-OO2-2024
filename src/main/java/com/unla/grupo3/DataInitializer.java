package com.unla.grupo3;

import com.unla.grupo3.entities.User;
import com.unla.grupo3.entities.UserRole;
import com.unla.grupo3.repositories.IUserRepository;
import com.unla.grupo3.repositories.IUserRoleRepository;
import com.unla.grupo3.services.implementation.ProductoService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class DataInitializer {

    private final IUserRepository userRepository;
    private final IUserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DataInitializer(IUserRepository userRepository, IUserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void initialize() {
        if (userRepository.findByUsername("admin") == null) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("1234"));
            admin.setEnabled(true);

            UserRole adminRole = new UserRole();
            adminRole.setRole("ROLE_ADMIN");
            adminRole.setUser(admin);

            Set<UserRole> roles = new HashSet<>();
            roles.add(adminRole);
            admin.setUserRoles(roles);

            userRepository.save(admin);
            userRoleRepository.save(adminRole);
        }

    }
}