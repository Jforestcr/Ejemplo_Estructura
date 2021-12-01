/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author asurafon
 */
public class PersonaRepositorio {
    private static List<Persona> listaPersonas = new ArrayList<>();

    public static List<Persona> getListaPersonas() {
        return listaPersonas;
    }

    public static void setListaPersonas(List<Persona> listaPersonas) {
        PersonaRepositorio.listaPersonas = listaPersonas;
    }
    
    public static void agregarPersona(Persona persona){
        listaPersonas.add(persona);
    }
    
    public static boolean existeIdentificacion(String identificacion){
        /*
        for(Persona persona : listaPersonas){
            if(persona.getIdentificacion().equals(identificacion))
                return true;
        }
        return false;
        */
        
        return listaPersonas.stream().anyMatch((persona) -> (persona.getIdentificacion().equals(identificacion)));
    }
    
}
