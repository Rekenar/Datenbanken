import java.sql.*;

public class Beherbergungsbetrieb {

    private int beherbergungsid;
    private Connection conn;
    private String beherbergungsname;
    private String adresse;
    private int hnr;
    private String tel;
    private String email;
    private String fax;
    private String website;
    private int sterne = 0;
    private String typ;

    /**
     *  Konstruktor Kategorie
     */

    Beherbergungsbetrieb(int beherbergungsid, Connection conn){
        this.beherbergungsid = beherbergungsid;
        this.conn = conn;
    }
    /**
     *  Load Methode
     *  Ladet Tupel bei erstmaliger Verwendung in Klasse
     */
    void load(){
        try{
            String query = "SELECT * FROM beherbergungsbetrieb";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                if(rs.getInt("beherbergungsid") == (getBeherbergungsid())){
                    this.beherbergungsname = rs.getString("beherbergungsname");
                    this.adresse = rs.getString("adresse");
                    this.hnr = rs.getInt("hnr");
                    this.tel = rs.getString("tel");
                    this.email = rs.getString("email");
                    this.fax = rs.getString("fax");
                    this.website = rs.getString("website");
                    this.typ = rs.getString("typ");
                    if(rs.getString("typ").equals("Hotel")){
                        this.sterne = rs.getInt("sterne");
                    }

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
        this.beherbergungsname = null;
        this.adresse = null;
        this.hnr = 0;
        this.tel = null;
        this.email = null;
        this.fax = null;
        this.website = null;
        this.sterne = 0;
        this.typ = null;
    }
    /**
     *  Persist Methode
     *  Ladet Änderungen in Datenbank hoch
     */
    void persist(){
        try{
            PreparedStatement ps = conn.prepareStatement("UPDATE beherbergungsbetrieb SET beherbergungsname = ? , adresse = ? , hnr = ? , tel = ? , email = ?, fax = ?, website = ?,sterne = ?, typ = ? WHERE beherbergungsid = ?");
            ps.setString(1, this.beherbergungsname);
            ps.setString(2, this.adresse);
            ps.setInt(3, this.hnr);
            ps.setString(4, this.tel);
            ps.setString(5, this.email);
            ps.setString(6, this.fax);
            ps.setString(7, this.website);
            ps.setInt(8, this.sterne);
            ps.setString(9, this.typ);
            ps.setInt(10,this.beherbergungsid);
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
    int getBeherbergungsid(){
        return beherbergungsid;
    }
    String getBeherbergungsname(){
        if(beherbergungsname != null){
            return beherbergungsname;
        }
        load();
        return beherbergungsname;
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
    String getFax(){
        if(fax != null){
            return fax;
        }
        load();
        return fax;
    }
    String getWebsite(){
        if(website != null){
            return website;
        }
        load();
        return website;
    }
    int getSterne(){
        if (sterne != 0) {
            return hnr;
        }
        load();
        return hnr;
    }
    String getTyp(){
        if(typ != null){
            return typ;
        }
        load();
        return typ;
    }
    /**
     *  Setter Methoden
     */

    void setBeherbergungsname(String beherbergungsname){
        this.beherbergungsname = beherbergungsname;
    }
    void getAdresse(String adresse){
        this.adresse = adresse;

    }
    void setHnr(int hnr){
        this.hnr = hnr;

    }
    void setTel(String tel){
        this.tel = tel;

    }
    void setEmail(String email){
        this.email = email;

    }
    void setFax(String fax){
        this.fax = fax;

    }
    void setWebsite(String website){
        this.website = website;

    }
    public void setSterne(int sterne) {
        this.sterne = sterne;

    }
    void setTyp(String typ){
        this.typ = typ;

    }
    /**
     *  Add Methode
     *  Fügt neuen Gast ein
     */
    void addBeherbergungsbetrieb(int beherbergungsid,String beherbergunsname, String adresse, int hnr, String tel, String email, String fax, String website, int sterne, String typ){
        try{
            PreparedStatement ps = conn.prepareStatement("INSERT INTO beherbergungsbetrieb(beherbergungsid, beherbergunsname, adresse, hnr, tel, email, fax, website, sterne, typ) VALUES(?,?,?,?,?,?,?,?,?,?)");
            ps.setInt(1, beherbergungsid);
            ps.setString(2,beherbergunsname);
            ps.setString(3,adresse);
            ps.setInt(4,hnr);
            ps.setString(5,tel);
            ps.setString(6,email);
            ps.setString(7,fax);
            ps.setString(8,website);
            ps.setInt(9,sterne);
            ps.setString(10,typ);
            ps.executeUpdate();
            ps.close();
        }catch(SQLException e){
            System.out.println(e);
        }
    }
    /**
     *  Delete Methode
     *  Löscht Tupel mit gegebener beherbergungsid
     */
    void deleteBeherbergunsbetrieb(int beherbergungsid){
        try{
            PreparedStatement ps = conn.prepareStatement("DELETE FROM beherbungsbetrieb WHERE beherbergungsid = ?");
            ps.setInt(1, beherbergungsid);
            ps.executeUpdate();
            ps.close();
        }catch(SQLException e){
            System.out.println(e);
        }
    }
}
