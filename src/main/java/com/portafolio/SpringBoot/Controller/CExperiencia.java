package com.portafolio.SpringBoot.Controller;

import com.portafolio.SpringBoot.Dto.dtoExperiencia;
import com.portafolio.SpringBoot.Entity.Experiencia;
import com.portafolio.SpringBoot.Security.Controller.Message;
import com.portafolio.SpringBoot.Service.SExperiencia;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("explab")
@CrossOrigin(origins = "http://localhost:4200")
public class CExperiencia {
    @Autowired
    SExperiencia sExperiencia;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Experiencia>> list() {
        List<Experiencia> list = sExperiencia.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoExperiencia dtoexp) {
        if(StringUtils.isBlank(dtoexp.getNombreE()))
            return new ResponseEntity(new Message("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(sExperiencia.existsByNombreE(dtoexp.getNombreE()))
            return new ResponseEntity(new Message("Esa experiencia existe"), HttpStatus.BAD_REQUEST);
        Experiencia experiencia = new Experiencia(dtoexp.getNombreE(), dtoexp.getDescripcionE());
        sExperiencia.save(experiencia);
        
        return new ResponseEntity(new Message("Experiencia agregada"), HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoExperiencia dtoexp) {
        //Validamos si existe el Id
        if(!sExperiencia.existsById(id))
            return new ResponseEntity(new Message("El Id no existe"), HttpStatus.BAD_REQUEST);
        //Compara nombre de experiencia
        if(sExperiencia.existsByNombreE(dtoexp.getNombreE()) && sExperiencia.getByNombreE(dtoexp.getNombreE()).get().getId() != id) 
            return new ResponseEntity(new Message("Esa experiencia ya existe"), HttpStatus.BAD_REQUEST);
        //No puede estar vacio
        if(StringUtils.isBlank(dtoexp.getNombreE()))
            return new ResponseEntity(new Message("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        
        Experiencia experiencia = sExperiencia.getOne(id).get();
        experiencia.setNombreE(dtoexp.getNombreE());
        experiencia.setDescripcionE((dtoexp.getDescripcionE()));
        
        sExperiencia.save(experiencia);
        return new ResponseEntity(new Message("Experiencia actualizada"), HttpStatus.OK);
    }
    
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        //Validamos si existe el Id
        if(!sExperiencia.existsById(id))
            return new ResponseEntity(new Message("El Id no existe"), HttpStatus.BAD_REQUEST);
        
        sExperiencia.delete(id);
        
        return new ResponseEntity(new Message("Experiencia eliminada"), HttpStatus.OK);
    }
}