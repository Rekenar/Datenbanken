import java.sql.*;
import java.util.ArrayList;

public class Kategorie {

    private int kategorieid;
    private Connection conn;
    private String bezeichnung;
    private String beschreibungstext;
    private int anzahlbetten;
    private int anzahlgesamtbetten;
    private int beherbergungsid;
    private ArrayList freieeinheiten;
    /**
     *  Konstruktor Kategorie
     */

    Kategorie(int kategorieid, Connection conn){
        this.kategorieid = kategorieid;
        this.conn = conn;
    }
    /**
     *  Load Methode
     *  Ladet Tupel bei erstmaliger Verwendung in Klasse
     */
    void load(){
        try{
            String query = "SELECT * FROM kategorie";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                if(rs.getInt("kategorieid") == (getkategorieid())){
                    this.bezeichnung = rs.getString("bezeichnung");
                    this.beschreibungstext = rs.getString("beschreibungstext");
                    this.anzahlbetten = rs.getInt("anzahlbetten");
                    this.anzahlgesamtbetten = rs.getInt("anzahlgesamtbetten");
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
        this.bezeichnung = null;
        this.beschreibungstext = null;
        this.anzahlbetten = 0;
        this.anzahlgesamtbetten = 0;
        this.beherbergungsid = 0;
    }
    /**
     *  Persist Methode
     *  Ladet Änderungen in Datenbank hoch
     */
    void persist(){
        try{
            PreparedStatement ps = conn.prepareStatement("UPDATE kategorie SET bezeichnung = ? , beschreibungstext = ? , anzahlbetten = ? , anzahlgesamtbetten = ? , beherbergungsid = ? WHERE kategorieid = ?");
            ps.setString(1, this.bezeichnung);
            ps.setString(2, this.beschreibungstext);
            ps.setInt(3, this.anzahlbetten);
            ps.setInt(4, this.anzahlgesamtbetten);
            ps.setInt(5, this.beherbergungsid);
            ps.setInt(6,this.kategorieid);
            ps.executeUpdate();
            ps.close();
        }catch (SQLException e){
            System.out.println(e);
        }
        unload();
    }
    /**
     *  Getter Methoden
     */
    int getkategorieid(){
        return kategorieid;
    }
    String getBezeichnung(){
        if(bezeichnung != null){
            return bezeichnung;
        }
        load();
        return bezeichnung;
    }
    String getBeschreibungstext(){
        if(beschreibungstext != null){
            return beschreibungstext;
        }
        load();
        return beschreibungstext;
    }
    int getAnzahlbetten(){
        if(anzahlbetten != 0){
            return anzahlbetten;
        }
        load();
        return anzahlbetten;
    }
    int getAnzahlgesamtbetten(){
        if(anzahlgesamtbetten != 0){
            return anzahlgesamtbetten;
        }
        load();
        return anzahlgesamtbetten;
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
    void setBezeichnung(String  bezeichnung){
        this.bezeichnung = bezeichnung;
    }
    void setBeschreibungstext(String beschreibungstext){
        this.beschreibungstext = beschreibungstext;
    }
    void setAnzahlbetten(int anzahlbetten){
        this.anzahlbetten = anzahlbetten;
    }
    void setAnzahlgesamtbetten(int anzahlgesamtbetten){
        this.anzahlgesamtbetten = anzahlgesamtbetten;
    }
    void setBeherbergungsid(int beherbergungsid){
        this.beherbergungsid = beherbergungsid;
    }
    /**
     *  Add Methode
     *  Fügt neue Kategorie ein
     */
    void addKategorie(int kategorieid,String bezeichnung, String beschreibungstext, int anzahlbetten, int anzahlgesamtbetten, int beherbergungsid){
        try{
            PreparedStatement ps = conn.prepareStatement("INSERT INTO kategorie(kategorieid, bezeichnung, beschreibungstext, anzahlbetten, anzahlgesamtbetten, beherbergungsid) VALUES(?,?,?,?,?,?)");
            ps.setInt(1, kategorieid);
            ps.setString(2,bezeichnung);
            ps.setString(3,beschreibungstext);
            ps.setInt(4,anzahlbetten);
            ps.setInt(5,anzahlgesamtbetten);
            ps.setInt(6,beherbergungsid);
            ps.executeUpdate();
            ps.close();
        }catch(SQLException e){
            System.out.println(e);
        }
    }
    /**
     *  Delete Methode
     *  Löscht Tupel mit gegebener kategorieid
     */
    void deleteKategorie(int kategorieid){
        try{
            PreparedStatement ps = conn.prepareStatement("DELETE FROM kategorie WHERE kategorieid = ?");
            ps.setInt(1, kategorieid);
            ps.executeUpdate();
            ps.close();
        }catch(SQLException e){
            System.out.println(e);
        }
    }

    /**
     *  Abfrage der freien Einheiten
     */

    ArrayList getfreieEinheiten(Date anreise, Date abreise){
        try{
            String query = "SELECT * FROM buchung ";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                if(anreise.before(rs.getDate("anreise")) && abreise.before(rs.getDate("anreise"))){
                    freieeinheiten.add(rs.getInt("einheitid"));
                }
                else if(anreise.after(rs.getDate("abreise"))  && abreise.after(rs.getDate("abreise"))){
                    freieeinheiten.add(rs.getInt("einheitid"));
                }
            }
            rs.close();
            stmt.close();
        }catch (SQLException e){
            System.out.println(e);
        }
        return freieeinheiten;
    }
}
