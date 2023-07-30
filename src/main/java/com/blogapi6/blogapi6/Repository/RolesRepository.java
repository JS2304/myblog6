package com.blogapi6.blogapi6.Repository;

import com.blogapi6.blogapi6.Entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolesRepository extends JpaRepository<Roles,Long> {

    Optional<Roles> findByName(String name);
}
