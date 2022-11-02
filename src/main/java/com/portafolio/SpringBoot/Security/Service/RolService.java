package com.portafolio.SpringBoot.Security.Service;

import com.portafolio.SpringBoot.Security.Entity.Rol;
import com.portafolio.SpringBoot.Security.Enums.RolNombre;
import com.portafolio.SpringBoot.Security.Repository.IRolRepository;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class RolService {
    @Autowired
    IRolRepository iRolRepository;
    
    public Optional<Rol> getByRolNombre(RolNombre rolNombre) {
        return iRolRepository.findByRolNombre(rolNombre);
    }
    
    public void save(Rol rol) {
        iRolRepository.save(rol);
    }
}
