/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author asurafon
 */
public class PersonaRepositorio {
    public List<Persona> leerPersonas(){
        EntityManager conexion = Conexion_Hibernate.getEntityManagerFactory().createEntityManager();
        try{
            Query query = conexion.createNamedQuery("Persona.findAll");
            List<Persona> personas = query.getResultList();
            return personas;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
        finally{
            conexion.close();
        }
    }
    
    public Persona leerPersona(String identificacion){
        EntityManager conexion = Conexion_Hibernate.getEntityManagerFactory().createEntityManager();
        try{
            Query query = conexion.createNamedQuery("Persona.findByIdentificacion");
            query.setParameter("identificacion", identificacion);
            List<Persona> personas = query.getResultList();
            return personas.isEmpty() ? null : personas.get(0);
            /*if(personas.isEmpty())
                return null;
            else
                return personas.get(0);]*/
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
        finally{
            conexion.close();
        }
    }
    
    public void crearPersona(Persona persona){
        EntityManager conexion = Conexion_Hibernate.getEntityManagerFactory().createEntityManager();
        try{
            persona.setIdPersona(null);
            conexion.getTransaction().begin();
            conexion.persist(persona);
            conexion.getTransaction().commit();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
        finally{
            conexion.close();
        }
    }
    
    public void actualizarPersona(Persona persona){
        EntityManager conexion = Conexion_Hibernate.getEntityManagerFactory().createEntityManager();
        try{
            conexion.getTransaction().begin();
            conexion.merge(persona);
            conexion.getTransaction().commit();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
        finally{
            conexion.close();
        }
    }
    
    public void eliminarPersona(int idPersona){
        EntityManager conexion = Conexion_Hibernate.getEntityManagerFactory().createEntityManager();
        try{
            conexion.getTransaction().begin();
            Persona persona = conexion.find(Persona.class, idPersona);
            conexion.remove(persona);
            conexion.getTransaction().commit();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
        finally{
            conexion.close();
        }
    }
}
