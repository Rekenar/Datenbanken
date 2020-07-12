import java.sql.*;

public class Test {
    public static void main(String[] args) {
        try{
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost/Freunde","postgres","15935724862684pH");
            Person alias = new Person("Jonas.Voigt@yahoo.com",con);
            Person person = new Person("H.Mueller@yahoo.com", con);
            System.out.println(person.getVorname());
            System.out.println(person.getNachname());
            System.out.println(person.getGeburtsdatum());
            System.out.println(person.getGeschlecht());
            System.out.println(person.getFreunde().size());
            person.setVorname("Hannah");
            System.out.println(person.getVorname());
            person.addFriend(alias);
            person.removeFriend(alias);
            person.unload();
            con.close();
        }catch (SQLException ex){
            System.out.println(ex);
        }


    }
}
