package com.portafolio.SpringBoot.Interface;

import com.portafolio.SpringBoot.Entity.Persona;
import java.util.List;

public interface IPersonaService {
    //Traer una lista de personas
    public List<Persona> getPersona();
    
    //Guardar un objeto del tipo Persona
    public void savePersona(Persona persona);
            
    //Eliminar un objeto por Id    
    public void deletePersona(Long id);
    
    //Buscar persona por Id
    public Persona findPersona(Long id);
}
