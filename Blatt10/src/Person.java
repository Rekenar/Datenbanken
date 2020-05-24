import java.sql.*;
import java.util.ArrayList;

public class Person {

    private String email;
    private Connection conn;
    private String vorname;
    private String nachname;
    private Date geburtsdatum;
    private String geschlecht;
    private ArrayList freunde = new ArrayList();


    /**
     *  Konstruktor Person
     */
    Person(String email, Connection conn){
        this.email = email;
        this.conn = conn;
    }

    /**
     *  GETTER Methoden
     */

    String getEmail(){
        return email;
    }
    String getVorname(){
        try{
            String query = "SELECT * FROM person";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                if(rs.getString("email").equals(getEmail())){
                    this.vorname = rs.getString("vorname");
                    break;
                }
            }
            rs.close();
            stmt.close();

        }catch (SQLException e){
            System.out.println("not Working getVorname");
        }
        return this.vorname;
    }
    String getNachname(){
        try{
            String query = "SELECT * FROM person";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                if(rs.getString("email").equals(getEmail())){
                    this.nachname = rs.getString("nachname");
                    break;
                }
            }
            rs.close();
            stmt.close();

        }catch (SQLException e){
            System.out.println("not Working getNachname");
        }
        return this.nachname;
    }
    Date getGeburtsdatum(){
        try{
            String query = "SELECT * FROM person";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                if(rs.getString("email").equals(getEmail())){
                    this.geburtsdatum = rs.getDate("geburtsdatum");
                    break;
                }
            }
            rs.close();
            stmt.close();

        }catch (SQLException e){
            System.out.println("not Working getGeburtsdatum");
        }
        return this.geburtsdatum;
    }
    String getGeschlecht(){
        try{
            String query = "SELECT * FROM person";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                if(rs.getString("email").equals(getEmail())){
                    this.geschlecht = rs.getString("geschlecht");
                    break;
                }
            }
            rs.close();
            stmt.close();

        }catch (SQLException e){
            System.out.println("not Working getGeschlecht");
        }
        return this.geschlecht;
    }
    ArrayList getFreunde(){// Verwenden sie zur Speicherung der Freunde eine geeignete Datenstruktur mit variabler Länge.
        try{
            String query = "SELECT hatfreund.email AS email, emailfreund FROM hatfreund INNER JOIN person ON hatfreund.email = person.email";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                    if(rs.getString("email").equals(getEmail())){
                        Person freundneu = new Person(rs.getString("emailfreund"), conn);
                        this.freunde.add(freundneu);
                }
            }
            rs.close();
            stmt.close();
        }catch (SQLException e){
            System.out.println("not Working getFreunde");
        }
        return this.freunde;
    }

    /**
     *  SETTER Methoden
     */

    void setVorname(String vorname){
        try{
            PreparedStatement ps = conn.prepareStatement("UPDATE person SET vorname = ? WHERE vorname = ?");
            ps.setString(1, vorname);
            ps.setString(2,getVorname());
            ps.executeUpdate();
            ps.close();
        }catch(SQLException e){
            System.out.println("not Working setVorname");
        }
    }
    void setNachname(String nachname){
        try{
            PreparedStatement ps = conn.prepareStatement("UPDATE person SET nachname = ? WHERE nachname = ?");
            ps.setString(1, nachname);
            ps.setString(2,getNachname());
            ps.executeUpdate();
            ps.close();
        }catch(SQLException e){
            System.out.println("not Working setNachname");
        }
    }
    void setGeburtstag(Date geburtstag){
        try{
            PreparedStatement ps = conn.prepareStatement("UPDATE person SET geburtsdatum = ? WHERE geburtsdatum = ?");
            ps.setDate(1, geburtstag);
            ps.setDate(2,getGeburtsdatum());
            ps.executeUpdate();
            ps.close();
        }catch(SQLException e){
            System.out.println("not Working setGeburtsdatum");
        }
    }
    void setGeschlecht(String geschlecht){
        try{
            PreparedStatement ps = conn.prepareStatement("UPDATE person SET geschlecht = ? WHERE geschlecht = ?");
            ps.setString(1, geschlecht);
            ps.setString(2,getGeschlecht());
            ps.executeUpdate();
            ps.close();
        }catch(SQLException e){
            System.out.println("not Working setGeschlecht");
        }
    }
    void setEmail(String newEmail){
        try{
            PreparedStatement ps = conn.prepareStatement("UPDATE person SET email = ? WHERE email = ?");
            ps.setString(1, newEmail);
            ps.setString(2,getEmail());
            ps.executeUpdate();
            ps.close();
        }catch(SQLException e){
            System.out.println("not Working setEmail");
        }
    }
    void addFriend(Person friend){

    }
    void removeFriend(Person exfriend){

    }
    void delete(){

    }
    void persist(){

    }
    void unload(){

    }
}
