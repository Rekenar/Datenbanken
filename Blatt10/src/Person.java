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
            if(vorname != null){
                return vorname;
            }
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
            if(nachname != null){
                return nachname;
            }
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
            if(geburtsdatum != null){
                return geburtsdatum;
            }
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
            if(geschlecht != null){
                return geschlecht;
            }
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
                        this.freunde.add( new Person(rs.getString("emailfreund"), conn));
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
            PreparedStatement ps = conn.prepareStatement("UPDATE person SET vorname = ? WHERE email = ?");
            ps.setString(1, vorname);
            ps.setString(2,getEmail());
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
        try{
            PreparedStatement ps = conn.prepareStatement("INSERT INTO hatfreund(email, emailfreund, bisdatum) VALUES(?,?,?)");
            ps.setString(1, getEmail());
            ps.setString(2,friend.getEmail());
            ps.setDate(3,getGeburtsdatum());
            ps.executeUpdate();
            ps.close();
        }catch(SQLException e){
            System.out.println("not Working addFriend");
        }
    }
    void removeFriend(Person exfriend){

        try{
            PreparedStatement ps = conn.prepareStatement("DELETE FROM hatfreund WHERE email = ? AND emailfreund = ?");
            ps.setString(1, getEmail());
            ps.setString(2,exfriend.getEmail());
            ps.executeUpdate();
            ps.close();
        }catch(SQLException e){
            System.out.println("not Working RemoveFriend");
        }
    }
    void delete(){
        
    }
    void persist(){

    }
    void unload(){
        this.vorname = null;
        this.nachname = null;
        this.geburtsdatum = null;
        this.geschlecht = null;
        this.freunde.clear();

    }
}
