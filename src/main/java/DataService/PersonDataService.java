package DataService;


import DataModels.Person;
import DataSource.MySQL;
import java.util.ArrayList;

    public class PersonDataService {

        private MySQL dbh = new MySQL();

        public ArrayList<Person> getAllPersons() {
            return dbh.getAllPersons();
        }

        public Person addPerson(Person person) {
            return dbh.addPerson(person);
        }

        //getPersonById
        public Person getPersonById(int perId){ return dbh.getPersonById(perId);}

        //Update person
        public Person updatePerson(Person person, int perId) {return dbh.updatePerson(person, perId);}

        //Delete person
        public String deletePerson(int persId){return dbh.deletePerson(persId);}
    }
