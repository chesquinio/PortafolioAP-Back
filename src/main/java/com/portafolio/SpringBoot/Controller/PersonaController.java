package com.portafolio.SpringBoot.Controller;

import com.portafolio.SpringBoot.Dto.dtoPersona;
import com.portafolio.SpringBoot.Entity.Persona;
import com.portafolio.SpringBoot.Security.Controller.Message;
import com.portafolio.SpringBoot.Service.ImpPersonaService;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/personas")
@CrossOrigin(origins = "http://localhost:4200")
public class PersonaController {
    @Autowired
    ImpPersonaService sPersona;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Persona>> list() {
        List<Persona> list = sPersona.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
   /* @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        //Validamos si existe el Id
        if(!sPersona.existsById(id))
            return new ResponseEntity(new Message("El Id no existe"), HttpStatus.BAD_REQUEST);
        
        sPersona.delete(id);
        
        return new ResponseEntity(new Message("Experiencia eliminada"), HttpStatus.OK);
    }*/
    
    /*@PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoPersona dtoper) {
        if(StringUtils.isBlank(dtoper.getNombre()))
            return new ResponseEntity(new Message("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(sPersona.existsByNombreE(dtoper.getNombre()))
            return new ResponseEntity(new Message("Esa experiencia existe"), HttpStatus.BAD_REQUEST);
        Persona persona = new Persona(dtoper.getNombre(), dtoper.getDescripcion());
        sPersona.save(persona);
        
        return new ResponseEntity(new Message("Experiencia agregada"), HttpStatus.OK);
    }*/
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoPersona dtoper) {
        //Validamos si existe el Id
        if(!sPersona.existsById(id))
            return new ResponseEntity(new Message("El Id no existe"), HttpStatus.BAD_REQUEST);
        //Compara nombre de experiencia
        if(sPersona.existsByNombre(dtoper.getNombre()) && sPersona.getByNombre(dtoper.getNombre()).get().getId() != id) 
            return new ResponseEntity(new Message("Esa experiencia ya existe"), HttpStatus.BAD_REQUEST);
        //No puede estar vacio
        if(StringUtils.isBlank(dtoper.getNombre()))
            return new ResponseEntity(new Message("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        
        Persona persona = sPersona.getOne(id).get();
        persona.setNombre(dtoper.getNombre());
        persona.setApellido(dtoper.getApellido());
        persona.setDescripcion((dtoper.getDescripcion()));
        persona.setImg(dtoper.getImg());
        
        sPersona.save(persona);
        return new ResponseEntity(new Message("Persona actualizada"), HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Persona> getById(@PathVariable("id") int id){
        if(!sPersona.existsById(id))
            return new ResponseEntity(new Message("No existe"), HttpStatus.NOT_FOUND);
        Persona persona = sPersona.getOne(id).get();
        return new ResponseEntity(persona, HttpStatus.OK);
    }
    
}
