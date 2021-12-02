/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com;

import dao.Persona;
import dao.PersonaRepositorio;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author asurafon
 */
@Named(value = "index")
@RequestScoped
public class index {
     private String accion;

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }
     
    /**
     * Creates a new instance of index
     */
    public index() {
    }
    
    public void dispararAccion(){
        PersonaRepositorio personaRepositorio = new PersonaRepositorio();
        Persona persona = new Persona();
        
        //crear
        if(this.accion.equals("c")){
            persona.setNombre("probando");
            persona.setApellido1("probando");
            persona.setApellido2("probando");
            persona.setIdentificacion("123");
            personaRepositorio.crearPersona(persona);
        }
        else if(this.accion.equals("a")){
            persona.setIdPersona(3);
            persona.setNombre("actualizando");
            persona.setApellido1("actualizando");
            persona.setApellido2("actualizando");
            persona.setIdentificacion("123");
            personaRepositorio.actualizarPersona(persona);
        }
        else if(this.accion.equals("e")){
            personaRepositorio.eliminarPersona(3);
        }
        else if(this.accion.equals("lt")){
            personaRepositorio.leerPersonas();
        }
        else if(this.accion.equals("l")){
            personaRepositorio.leerPersona("123");
        }
    }
    
}
