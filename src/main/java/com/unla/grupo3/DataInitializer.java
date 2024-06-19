package com.unla.grupo3;

import com.unla.grupo3.entities.User;
import com.unla.grupo3.entities.UserRole;
import com.unla.grupo3.repositories.IUserRepository;
import com.unla.grupo3.repositories.IUserRoleRepository;
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
        // Verificar si ya existe un usuario con el nombre "admin"
            // Crear un nuevo usuario administrador
        if (userRepository.findByUsername("admin") == null) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("1234")); // Codificar la contraseña
            admin.setEnabled(true); // Habilitar el usuario

            // Crear el rol de administrador
            UserRole adminRole = new UserRole();
            adminRole.setRole("ROLE_ADMIN"); // Rol de administrador
            adminRole.setUser(admin); // Asociar el usuario al rol

            // Asignar el rol al usuario
            Set<UserRole> roles = new HashSet<>();
            roles.add(adminRole);
            admin.setUserRoles(roles);


            // Guardar el usuario en la base de datos
            userRepository.save(admin);
            userRoleRepository.save(adminRole);
        }

    }
}