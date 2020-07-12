import java.sql.*;

public class Gast {

    private int gastid;
    private Connection conn;
    private String vorname;
    private String nachname;
    private String titel;
    private String adresse;
    private int hnr;
    private Date geburtsdatum;
    private String tel;
    private String email;
    private int beherbergungsid;

    /**
     *  Konstruktor Kategorie
     */

    Gast(int gastid, Connection conn){
        this.gastid = gastid;
        this.conn = conn;
    }
    /**
     *  Load Methode
     *  Ladet Tupel bei erstmaliger Verwendung in Klasse
     */
    void load(){
        try{
            String query = "SELECT * FROM gast";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                if(rs.getInt("gastid") == (getGastid())){
                    this.vorname = rs.getString("vorname");
                    this.nachname = rs.getString("nachname");
                    this.titel = rs.getString("titel");
                    this.adresse = rs.getString("adresse");
                    this.hnr = rs.getInt("hnr");
                    this.geburtsdatum = rs.getDate("geburtsdatum");
                    this.tel = rs.getString("tel");
                    this.email = rs.getString("email");
                    this.beherbergungsid = rs.getInt("beherbergungsid");
                    break;
                }
            }
            rs.close();
            stmt.close();
        }catch (SQLException e){
            System.out.println(e);
        }
    }
    /**
     *  Unload Methode
     *  Resetet Objekt
     *  private damit nicht ausversehen unload() aufgerufen wird und man mit persist() die Informationen verliert.
     */
    private void unload(){
        this.vorname = null;
        this.nachname = null;
        this.titel = null;
        this.adresse = null;
        this.hnr = 0;
        this.geburtsdatum = null;
        this.tel = null;
        this.email = null;
        this.beherbergungsid = 0;
    }
    /**
     *  Persist Methode
     *  Ladet Änderungen in Datenbank hoch
     */
    void persist(){
        try{
            PreparedStatement ps = conn.prepareStatement("UPDATE gast SET vorname = ? , nachname = ? , titel = ? , adresse = ? , hnr = ?, geburtsdatum = ?, tel = ?,email = ?, beherbergungsid = ? WHERE gastid = ?");
            ps.setString(1, this.vorname);
            ps.setString(2, this.nachname);
            ps.setString(3, this.titel);
            ps.setString(4, this.adresse);
            ps.setInt(5, this.hnr);
            ps.setDate(6, this.geburtsdatum);
            ps.setString(7, this.tel);
            ps.setString(8, this.email);
            ps.setInt(9, this.beherbergungsid);
            ps.setInt(10,this.gastid);
            ps.executeUpdate();
            ps.close();
        }catch (SQLException e){
            System.out.println(2);
            System.out.println(e);
        }
        unload();
    }
    /**
     *  Getter Methoden
     */
    int getGastid(){
        return gastid;
    }
    String getVorname(){
        if(vorname != null){
            return vorname;
        }
        load();
        return vorname;
    }
    String getNachname(){
        if(nachname != null){
            return nachname;
        }
        load();
        return nachname;
    }
    String getTitel(){
        if(titel != null){
            return titel;
        }
        load();
        return titel;
    }
    String getAdresse(){
        if(adresse != null){
            return adresse;
        }
        load();
        return adresse;
    }
    int getHnr() {
        if (hnr != 0) {
            return hnr;
        }
        load();
        return hnr;
    }
    Date getGeburtsdatum(){
        if(geburtsdatum != null){
            return geburtsdatum;
        }
        load();
        return geburtsdatum;
    }
    String getTel(){
        if(tel != null){
            return tel;
        }
        load();
        return tel;
    }
    String getEmail(){
        if(email != null){
            return email;
        }
        load();
        return email;
    }
    int getBeherbergungsid(){
        if(beherbergungsid != 0){
            return beherbergungsid;
        }
        load();
        return beherbergungsid;
    }

    /**
     *  Setter Methoden
     */

    void setVorname(String  vorname){
        this.vorname = vorname;
    }
    void setNachname(String nachname){
        this.nachname = nachname;
    }
    void setTitel(String titel){
        this.titel = titel;
    }
    void setAdresse(String adresse){
        this.adresse = adresse;
    }
    void setHnr(int hnr){
        this.hnr = hnr;
    }
    void setGeburtsdatum(Date geburtsdatum){
        this.geburtsdatum = geburtsdatum;
    }
    void setTel(String tel){
        this.tel = tel;
    }
    void setEmail(String email){
        this.email = email;
    }
    void setBeherbergungsid(int beherbergungsid){
        this.beherbergungsid = beherbergungsid;
    }
    /**
     *  Add Methode
     *  Fügt neuen Gast ein
     */
    void addGast(int gastid,String vorname, String nachname, String titel, String adresse, int hnr, Date geburtsdatum, String tel, String email, int beherbergungsid){
        try{
            PreparedStatement ps = conn.prepareStatement("INSERT INTO gast(gastid, vorname, nachname, titel, adresse, hnr, geburtsdatum, tel, email, beherbergungsid) VALUES(?,?,?,?,?,?,?,?,?,?)");
            ps.setInt(1, gastid);
            ps.setString(2,vorname);
            ps.setString(3,nachname);
            ps.setString(4,titel);
            ps.setString(5,adresse);
            ps.setInt(6,hnr);
            ps.setDate(7,geburtsdatum);
            ps.setString(8,tel);
            ps.setString(9,email);
            ps.setInt(10,beherbergungsid);
            ps.executeUpdate();
            ps.close();
        }catch(SQLException e){
            System.out.println(e);
        }
    }
    /**
     *  Delete Methode
     *  Löscht Tupel mit gegebener gastid
     */
    void deleteGast(int gastid){
        try{
            PreparedStatement ps = conn.prepareStatement("DELETE FROM gast WHERE gastid = ?");
            ps.setInt(1, gastid);
            ps.executeUpdate();
            ps.close();
        }catch(SQLException e){
            System.out.println(e);
        }
    }

    /**
     *  Create Buchung
     *  Fügt neue Buchung in die Buchung Tabelle ein
     */
    void createBuchung(int buchungsid, Date anreise, Date abreise, int anzahlerwachsene, int anzahlkinder, int einheitid){
        try{
            PreparedStatement ps = conn.prepareStatement("INSERT INTO buchung(buchungsid, anreise, abreise, anzahlerwachsene, anzahlkinder, einheitid) VALUES(?,?,?,?,?,?,?)");
            ps.setInt(1, buchungsid);
            ps.setDate(2,anreise);
            ps.setDate(3,abreise);
            ps.setInt(4,anzahlerwachsene);
            ps.setInt(5,anzahlkinder);
            ps.setInt(6,getGastid());
            ps.setInt(7,einheitid);
            ps.executeUpdate();
            ps.close();
        }catch(SQLException e){
            System.out.println(e);
        }
    }
}
