package DataSource;

import DataModels.Person;
import java.sql.*;
import java.util.ArrayList;

    public class MySQL {

        private String connStr = "jdbc:mysql://localhost:3306/firstApiDB"; //Connection String for MySQL.
        private String usr = "DBuser";
        private String pwd = "DBuser";
        private Connection conn = null;

        private void connect() {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(connStr, usr, pwd);
                System.out.println("Connected..");
            }
            catch (ClassNotFoundException | SQLException e) {
                System.out.println("Fejl med driver-loading eller connection. " + e.getMessage());
            }
        }

        private void closeConn() {
            try {
                conn.close();
            }
            catch (SQLException e) {
                //throw new RuntimeException(e);
            }
        }

        public ArrayList<Person> getAllPersons() {
            connect();
            ArrayList<Person> pList = new ArrayList<>();
            String sql = "select * from persons";
            try {
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while(rs.next()) {
                    Person p = new Person(
                            rs.getInt("persId"),
                            rs.getString("firstName"),
                            rs.getString("lastName"),
                            rs.getBoolean("student"),
                            rs.getString("lastUpdated")
                    );
                    pList.add(p);
                }
            }
            catch (SQLException e) {
                System.out.println("Error " + e.getMessage());
            }

            closeConn();
            return pList;
        }

        public Person addPerson(Person person) {
            Person p = null;
            connect();
            String sql = " insert into persons (firstName, lastName, student) values(?,?,?)";
            try {
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, person.getFirstName());
                pstmt.setString(2, person.getLastName());
                pstmt.setBoolean(3, person.isStudent());
                pstmt.executeUpdate();
                Statement stmt = conn.createStatement();
                ResultSet pid = stmt.executeQuery("select last_insert_id()");
                pid.next();
                int id = pid.getInt(1);
                p = getPersonById(id);
            }
            catch (SQLException e) {
                System.out.println("Error " + e.getMessage());
            }
            closeConn();
            return p;
        }

        public Person getPersonById(int persId){

            connect();
            Person p = null;
            String sql = "select * from persons where persId = ?";
            try {
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, persId);
                ResultSet rs = pstmt.executeQuery();
                if(rs.next()) {
                    p = new Person(
                            rs.getInt("persId"),
                            rs.getString("firstName"),
                            rs.getString("lastName"),
                            rs.getBoolean("student"),
                            rs.getString("lastUpdated")
                    );
                }
                System.out.println(p.toString());
            }
            catch (SQLException e) {
                System.out.println("Error " + e.getMessage());
            }
            closeConn();
            return p;
        }

        public Person updatePerson(Person person, int persId){
            connect();
            Person p = null;
            String sql = "UPDATE persons SET firstName=?, lastName=?, student=? WHERE persId = ?";
            PreparedStatement pstmt = null;
            try {
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, person.getFirstName());
                pstmt.setString(2, person.getLastName());
                pstmt.setBoolean(3, person.isStudent());
                pstmt.setInt(4, persId);
                pstmt.executeUpdate();
                p = getPersonById(persId);

            }catch (SQLException e) {
                System.out.println("Error " + e.getMessage());
            }
            closeConn();
            return p;
        }

        public String deletePerson(int persId){
            connect();
            Person p = null;
            String sql = "DELETE FROM persons WHERE persId = ?";
            PreparedStatement pstmt = null;
            try {
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, persId);
                pstmt.executeUpdate();

            }catch (SQLException e) {
                System.out.println("Error " + e.getMessage());
            }
            closeConn();
            return "Person with " + persId + " was deleted!";
        }
    }
