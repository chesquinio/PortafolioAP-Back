package com.portafolio.SpringBoot.Security.Service;

import com.portafolio.SpringBoot.Security.Entity.Usuario;
import com.portafolio.SpringBoot.Security.Repository.IUsuarioRepository;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UsuarioService {
    @Autowired
    IUsuarioRepository iUsuarioRepository;
    
    public Optional<Usuario> getByNombreUsuario(String nombreUsuario) {
        return iUsuarioRepository.findByNombreUsuario(nombreUsuario);
    }
    
    public boolean existsByNombreUsuario(String nombreUsuario) {
        return iUsuarioRepository.existsByNombreUsuario(nombreUsuario);
    }
    
    public boolean existsByEmail(String email) {
        return iUsuarioRepository.existsByEmail(email);
    }
    
    public void save(Usuario usuario) {
        iUsuarioRepository.save(usuario);
    }
}
