/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviciosWeb;

import dao.Persona;
import dao.PersonaRepositorio;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.json.JsonWriter;
import javax.ws.rs.POST;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author Jeffry
 */
@Path("persona")
@RequestScoped
public class PersonaResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of PersonaResource
     */
    public PersonaResource() {
    }

    @GET
    @Path("/personas")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerPersonas() {
        try {
            PersonaRepositorio personaRepositorio = new PersonaRepositorio();
            List<Persona> listaPersonas = personaRepositorio.leerPersonas();
            JsonArrayBuilder arregloPersonas = Json.createArrayBuilder();
            for (Persona persona : listaPersonas) {
                JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
                JsonObject jsonObject = jsonObjectBuilder
                        .add("nombre", persona.getNombre())
                        .add("apellido1", persona.getApellido1())
                        .add("apellido2", persona.getApellido2())
                        .add("identificacion", persona.getIdentificacion())
                        .build();
                arregloPersonas.add(jsonObject);
            }
            JsonObjectBuilder jsonObjectBuilder2 = Json.createObjectBuilder();
            JsonObject jsonFinal = jsonObjectBuilder2.add("personas", arregloPersonas).build();
            StringWriter jsonString = new StringWriter();
            JsonWriter jsonWriter = Json.createWriter(jsonString);
            jsonWriter.writeObject(jsonFinal);
            return Response.ok(jsonString.toString()).build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            String resultado = "{\"error\": \"Ocurrió un error inesperado.\"}";
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(resultado).build();
        }
    }

    @POST
    @Path("/agregarPersona")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response obtenerPersonas(String contenido) {
        try {
            JsonReader jsonReader = Json.createReader(new StringReader(contenido));
            JsonObject jsonObject = jsonReader.readObject();
            PersonaRepositorio personaRepositorio = new PersonaRepositorio();
            Persona persona = new Persona();
            persona.setNombre(jsonObject.getString("nombre"));
            persona.setApellido1(jsonObject.getString("apellido1"));
            persona.setApellido2(jsonObject.getString("apellido2"));
            persona.setIdentificacion(jsonObject.getString("identificacion"));
            personaRepositorio.crearPersona(persona);
            return Response.ok("{\"operacionExitosa\": 1}").build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            String resultado = "{\"error\": \"Ocurrió un error inesperado.\"}";
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(resultado).build();
        }
    }
}
