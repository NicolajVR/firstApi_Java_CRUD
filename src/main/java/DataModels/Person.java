package DataModels;

public class Person {

    private int persId;
    private String firstName;
    private String lastName;
    private boolean student;
    private String lastUpdated;

    public Person() {} //empty contractor.

    public Person(int persId, String firstName, String lastName, boolean student, String lastUpdated) {
        this.persId = persId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.student = student;
        this.lastUpdated = lastUpdated;
    }

    public int getPersId() {
        return persId;
    }

    public void setPersId(int persId) {
        this.persId = persId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isStudent() {
        return student;
    }

    public void setStudent(boolean student) {
        this.student = student;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    //a whole person.
    @Override
    public String toString() {
        return "Person{" +
                "persId=" + persId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", student=" + student +
                ", lastUpdated='" + lastUpdated + '\'' +
                '}';
    }
}
