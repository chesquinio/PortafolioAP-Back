package com.portafolio.SpringBoot.Security.Repository;

import com.portafolio.SpringBoot.Security.Entity.Rol;
import com.portafolio.SpringBoot.Security.Enums.RolNombre;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRolRepository extends JpaRepository<Rol, Integer>{
       Optional<Rol> findByRolNombre(RolNombre rolNombre);
} 

