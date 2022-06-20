import java.sql.*;

public class Main {
    public static void main(String[] args) {
        try{
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost/ProjectDatabase","postgres","Admin");
            Kategorie k = new Kategorie(1,con);
            Gast g = new Gast(1,con);
            Beherbergungsbetrieb b = new Beherbergungsbetrieb(1,con);
            //Gast Methoden Test
            System.out.println("-------------------------------------------------------------------------");

            System.out.println(g.getAdresse());
            System.out.println(g.getHnr());
            System.out.println(g.getNachname());
            System.out.println(g.getEmail());
            System.out.println(g.getGeburtsdatum());
            System.out.println(g.getTel());
            System.out.println(g.getEmail());
            g.setNachname("Kowalski");
            g.persist();
            System.out.println(g.getNachname());
            //Beherbergungsbetrieb Methoden Test
            System.out.println("-------------------------------------------------------------------------");

            System.out.println(b.getAdresse());
            System.out.println(b.getHnr());
            System.out.println(b.getFax());
            System.out.println(b.getEmail());
            System.out.println(b.getTel());
            b.setEmail("hotel1@gmx.com");
            b.persist();
            System.out.println(b.getEmail());
            System.out.println(b.getTyp());
            System.out.println("-------------------------------------------------------------------------");
            //Kategorie Methoden Test
            System.out.println(k.getBeherbergungsid());
            System.out.println(k.getBezeichnung());
            System.out.println(k.getBeschreibungstext());
            System.out.println(k.getAnzahlbetten());
            System.out.println(k.getAnzahlgesamtbetten());
            k.setBezeichnung("Business");
            k.persist();
            System.out.println(k.getBezeichnung());
            k.deleteKategorie(6);
            con.close();
        }catch (SQLException ex){
            System.out.println(ex);
        }


    }
}
