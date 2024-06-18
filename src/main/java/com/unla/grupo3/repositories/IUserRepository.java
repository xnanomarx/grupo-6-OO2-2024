package com.unla.grupo3.repositories;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.unla.grupo3.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    @Query(value="select * from baseprueba.user u where u.username = :username", nativeQuery=true)
    User findByUsernameAndFetchUserRolesEagerly(@Param("username") String username);
}