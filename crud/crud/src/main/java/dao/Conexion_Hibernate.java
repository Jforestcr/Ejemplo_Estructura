/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author asurafon
 */
public class Conexion_Hibernate {
    private static final String PERSISTANCE_UNIT_NAME = "PERSISTENCE";
    private static EntityManagerFactory factory;
    
    public static EntityManagerFactory getEntityManagerFactory(){
        try{
            if(factory == null)
                factory = Persistence.createEntityManagerFactory(PERSISTANCE_UNIT_NAME);
            
            return factory;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
    }
    
    public static void shutdown(){
        if(factory == null)
            factory.close();
    }
}
