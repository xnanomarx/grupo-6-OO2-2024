package com.unla.grupo3.repositories;

import com.unla.grupo3.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    User findByUsernameAndFetchUserRolesEagerly(String username);
}