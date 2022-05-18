package Resources;

import DataModels.Person;
import DataService.PersonDataService;
import DataSource.MySQL;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.ArrayList;

@Path("/person")
public class PersonResource {
    private PersonDataService dataService = new PersonDataService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Person> getAllPersons() {
        return dataService.getAllPersons();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{persId}")
    public Person getPersonById(@PathParam("persId") int persId) {
        return dataService.getPersonById(persId);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Person addPerson(Person person) {
        return dataService.addPerson(person);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{persId}")
    public Person updatePerson(@PathParam("persId") int persId, Person person) {
        return dataService.updatePerson(person, persId);
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{persId}")
    public String deletePerson(@PathParam("persId") int persId) {
        return dataService.deletePerson(persId);
    }
}