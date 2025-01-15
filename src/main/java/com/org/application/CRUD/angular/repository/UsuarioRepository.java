package com.org.application.CRUD.angular.repository;

import com.org.application.CRUD.angular.user.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);

    @Modifying()
    @Query("update Usuario u set u.firstname =:firstname, u.lastname =:lastname, u.email =:email where u.id =:id")
    void updateUser(@Param(value = "id") Long id, @Param(value = "firstname") String firstname, @Param(value = "lastname") String lastname, @Param(value = "email") String email);


}
