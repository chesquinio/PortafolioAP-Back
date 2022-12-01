package com.portafolio.SpringBoot.Repository;

import com.portafolio.SpringBoot.Entity.Proyecto;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RProyecto extends JpaRepository<Proyecto, Integer> {
    public Optional<Proyecto> findByTitulo(String titulo);
    public boolean existsByTitulo(String titulo);
}