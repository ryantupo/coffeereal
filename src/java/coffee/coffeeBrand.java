/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coffee;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.sql.DataSource;
import user.flavourProfile;
import user.mainalg;

/**
 *
 * @author Ryan
 */
@ManagedBean(name = "brandbean")
@RequestScoped
public class coffeeBrand {

    int brand_Id;
    String brand_name;
    String country;
    Integer established;

    String brand_Info;
    String origin_Info;
    String logoLink;

    private int likes_sour = 1;
    private int likes_winey = 1;
    private int likes_adicic = 1;
    private int likes_mellow = 1;
    private int likes_bland = 1;
    private int likes_sharp = 1;
    private int likes_harsh = 1;
    private int likes_pungent = 1;

    private boolean likes_flowery;
    private boolean likes_fruity;
    private boolean likes_herby;
    private boolean likes_nutty;
    private boolean likes_caramelly;
    private boolean likes_chocolatey;
    private boolean likes_resinous;
    private boolean likes_spicy;
    private boolean likes_carbony;

    private String error1;

    @Resource(name = "jdbc/loginpool")
    DataSource dataSource;

    public coffeeBrand() {

    }

    public coffeeBrand(int b_Id, String bName, String cName, Integer estab, String logo) {
        this.brand_Id = b_Id;
        this.brand_name = bName;
        this.country = cName;
        this.established = estab;
        this.logoLink = logo;

    }

    public String srcLogoGetter() {

        if (getLogoLink().contains("https")) {
            return "<img class=\"roundimg\" src=\"" + logoLink + "\" alt= \"Logo\" />";
        } else {
            return "<img class=\"roundimg\" src=\"" + "/login/faces/resources/images/logos/" + logoLink + "\" alt=\"Logo\" />";
        }

    }

    public String getBrand_Name() {
        return this.brand_name;
    }

    public String getcountry() {
        return this.country;
    }

    public Integer getEstablished() {
        return this.established;
    }

    public int getBrand_Id() {
        return this.brand_Id;
    }

    public String getBrand_Info() {
        return this.brand_Info;
    }

    public String getOrigin_Info() {
        return this.origin_Info;
    }

    public void setBrand_Name(String newName) {
        brand_name = newName;
    }

    public void setCountry(String newCountry) {
        country = newCountry;
    }

    public void setEstablished(Integer newEst) {
        established = newEst;
    }

    public void setBrand_Id(int newId) {
        brand_Id = newId;
    }

    public void setBrand_Info(String new_Info) {
        brand_Info = new_Info;
    }

    public void setOrigin_Info(String newOrigin_Info) {
        origin_Info = newOrigin_Info;
    }

    public String getError1() {
        return error1;
    }

    public void setError1(String newError) {
        this.error1 = newError;
    }

    public String getLogoLink() {
        return logoLink;
    }

    public void setLogoLink(String logoLink) {
        this.logoLink = logoLink;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public int getLikes_sour() {
        return likes_sour;
    }

    public void setLikes_sour(int likes_sour) {
        this.likes_sour = likes_sour;
    }

    public int getLikes_winey() {
        return likes_winey;
    }

    public void setLikes_winey(int likes_winey) {
        this.likes_winey = likes_winey;
    }

    public int getLikes_adicic() {
        return likes_adicic;
    }

    public void setLikes_adicic(int likes_adicic) {
        this.likes_adicic = likes_adicic;
    }

    public int getLikes_mellow() {
        return likes_mellow;
    }

    public void setLikes_mellow(int likes_mellow) {
        this.likes_mellow = likes_mellow;
    }

    public int getLikes_bland() {
        return likes_bland;
    }

    public void setLikes_bland(int likes_bland) {
        this.likes_bland = likes_bland;
    }

    public int getLikes_sharp() {
        return likes_sharp;
    }

    public void setLikes_sharp(int likes_sharp) {
        this.likes_sharp = likes_sharp;
    }

    public int getLikes_harsh() {
        return likes_harsh;
    }

    public void setLikes_harsh(int likes_harsh) {
        this.likes_harsh = likes_harsh;
    }

    public int getLikes_pungent() {
        return likes_pungent;
    }

    public void setLikes_pungent(int likes_pungent) {
        this.likes_pungent = likes_pungent;
    }

    public boolean isLikes_flowery() {
        return likes_flowery;
    }

    public void setLikes_flowery(boolean likes_flowery) {
        this.likes_flowery = likes_flowery;
    }

    public boolean isLikes_fruity() {
        return likes_fruity;
    }

    public void setLikes_fruity(boolean likes_fruity) {
        this.likes_fruity = likes_fruity;
    }

    public boolean isLikes_herby() {
        return likes_herby;
    }

    public void setLikes_herby(boolean likes_herby) {
        this.likes_herby = likes_herby;
    }

    public boolean isLikes_nutty() {
        return likes_nutty;
    }

    public void setLikes_nutty(boolean likes_nutty) {
        this.likes_nutty = likes_nutty;
    }

    public boolean isLikes_caramelly() {
        return likes_caramelly;
    }

    public void setLikes_caramelly(boolean likes_caramelly) {
        this.likes_caramelly = likes_caramelly;
    }

    public boolean isLikes_chocolatey() {
        return likes_chocolatey;
    }

    public void setLikes_chocolatey(boolean likes_chocolatey) {
        this.likes_chocolatey = likes_chocolatey;
    }

    public boolean isLikes_resinous() {
        return likes_resinous;
    }

    public void setLikes_resinous(boolean likes_resinous) {
        this.likes_resinous = likes_resinous;
    }

    public boolean isLikes_spicy() {
        return likes_spicy;
    }

    public void setLikes_spicy(boolean likes_spicy) {
        this.likes_spicy = likes_spicy;
    }

    public boolean isLikes_carbony() {
        return likes_carbony;
    }

    public void setLikes_carbony(boolean likes_carbony) {
        this.likes_carbony = likes_carbony;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public boolean checkInputs(String Bname, String Cname, Integer Est, String B_Info, String BO_Info) {

        String errorOutput = "";

        final Pattern noSpecialpattern = Pattern.compile("^[a-zA-Z0-9_ .-]*$");
        final Pattern noSpecialPattern2 = Pattern.compile("/^[0-9]*$/");
        if (!noSpecialpattern.matcher(Bname).matches()) {
            errorOutput += ("Error code 001 :Brand Name cannot contain special Characters\n");
        }
        if (!noSpecialpattern.matcher(Cname).matches()) {
            errorOutput += ("Error code 002 :Country Name cannot contain special Characters\n");
        }
        if (noSpecialPattern2.matcher(String.valueOf(Est)).matches()) {
            errorOutput += ("Error code 004 :Year of establishment can only be an Integer\n");
        }

        final Pattern Passwordpattern = Pattern.compile("(.*?)");
        if (!Passwordpattern.matcher(B_Info).matches()) {
            errorOutput += ("Error code 005 : not a Valid brand info\n");
        }

        if (!Passwordpattern.matcher(BO_Info).matches()) {
            errorOutput += ("Error code 006 : not a Valid brand info\n");
        }

        setError1(errorOutput);

        if (getError1() == "") {
            return false;
        } else {
            return true;
        }
    }

    public boolean compareUser(String Bname) throws SQLException {

        Connection connection = dataSource.getConnection();
        System.out.println("connection :" + connection);
        PreparedStatement compareBrand = connection.prepareStatement("select count(*) as total from coffeebrands where Brand_Name = '" + Bname + "' ");
        System.out.println("compare Brand tables : " + compareBrand);

        int rows;
        try (ResultSet results = compareBrand.executeQuery()) {
            results.next();
            rows = results.getInt("total");
        }
        if (rows > 0) {
            return false;
        }
        return true;
    }

    public void clear() {
        setBrand_Name(null);
        setCountry(null);
        setEstablished(null);
        setBrand_Info(null);
        setOrigin_Info(null);

    }

    //database stuff
    // save a new address book entry
    public void save() throws SQLException, IOException {

        // check whether dataSource was injected by the server
        if (dataSource == null) {
            throw new SQLException("Unable to obtain DataSource");
        }

        // obtain a connection from the connection pool
        Connection connection = dataSource.getConnection();

        // check whether connection was successful
        if (connection == null) {
            throw new SQLException("Unable to connect to DataSource");
        }

        if (checkInputs(getBrand_Name(), getcountry(), getEstablished(), getBrand_Info(), getOrigin_Info())) {
            return;

        } else {

            if (!compareUser(getBrand_Name())) {

                setError1("Brand Name already used");
                return;

            } else {
                setError1("succesfully added");

                // create a PreparedStatement to insert a new login entry
                PreparedStatement addEntry
                        = connection.prepareStatement("INSERT INTO APP.coffeebrands"
                                + "(brand_name,country_name,est)"
                                + "VALUES ( ?, ?, ? )");

                // specify the PreparedStatement's arguments
                addEntry.setString(1, getBrand_Name());
                addEntry.setString(2, getcountry());
                addEntry.setInt(3, getEstablished());

                addEntry.executeUpdate(); // insert the entry

                PreparedStatement getNewId = connection.prepareStatement("SELECT brand_id FROM coffeebrands ORDER BY brand_id DESC OFFSET 0 ROWS FETCH FIRST 1 ROW ONLY");

//                getNewId.setString(1, getBrand_Name());
                try (ResultSet results = getNewId.executeQuery()) {

                    results.next();

                    setBrand_Id(results.getInt("brand_id"));

                    PreparedStatement addEntry2
                            = connection.prepareStatement("INSERT INTO APP.brandinfo"
                                    + "(brand_id,brand_info,origin_info,brand_logo_file_name)"
                                    + "VALUES ( ?, ?, ? , ? )");

                    // specify the PreparedStatement's arguments
                    addEntry2.setInt(1, getBrand_Id());
                    addEntry2.setString(2, getBrand_Info());
                    addEntry2.setString(3, getOrigin_Info());
                    addEntry2.setString(4, getLogoLink());

                    addEntry2.executeUpdate(); // insert the entry
                }

                try {

                    PreparedStatement addEntry2
                            = connection.prepareStatement("INSERT INTO APP.COFFEE_BRAND_AROMAS"
                                    + "(BRAND_ID,FLOWERY,FRUITY,HERBY,NUTTY,CARAMELLY,"
                                    + "CHOCOLATEY,RESINOUS,SPICY,CARBONY)"
                                    + "VALUES ( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? )");

                    // specify the PreparedStatement's arguments
                    addEntry2.setInt(1, getBrand_Id());
                    addEntry2.setBoolean(2, isLikes_flowery());
                    addEntry2.setBoolean(3, isLikes_fruity());
                    addEntry2.setBoolean(4, isLikes_herby());
                    addEntry2.setBoolean(5, isLikes_nutty());
                    addEntry2.setBoolean(6, isLikes_caramelly());
                    addEntry2.setBoolean(7, isLikes_chocolatey());
                    addEntry2.setBoolean(8, isLikes_resinous());
                    addEntry2.setBoolean(9, isLikes_spicy());
                    addEntry2.setBoolean(10, isLikes_carbony());

                    addEntry2.executeUpdate(); // insert the entry

                } catch (SQLException e) {
                    System.out.println("SQL error occured");
                    System.out.println(e);
                }

                try {

                    PreparedStatement addEntry3
                            = connection.prepareStatement("INSERT INTO APP.COFFEE_BRAND_TASTES"
                                    + "(BRAND_ID,SOUR,WINEY,ADICIC,MELLOW,BLAND,SHARP,HARSH,PUNGENT)"
                                    + "VALUES ( ? , ? , ? , ? , ? , ? , ? , ? , ? )");

                    // specify the PreparedStatement's arguments
                    addEntry3.setInt(1, getBrand_Id());
                    addEntry3.setInt(2, getLikes_sour());
                    addEntry3.setInt(3, getLikes_winey());
                    addEntry3.setInt(4, getLikes_adicic());
                    addEntry3.setInt(5, getLikes_mellow());
                    addEntry3.setInt(6, getLikes_bland());
                    addEntry3.setInt(7, getLikes_sharp());
                    addEntry3.setInt(8, getLikes_harsh());
                    addEntry3.setInt(9, getLikes_pungent());

                    addEntry3.executeUpdate(); // insert the entry

                } catch (SQLException e) {
                    System.out.println("SQL error occured");
                    System.out.println(e);
                }

                // end try
                connection.close(); // return this connection to pool
                clear();
                // end finally     
            } // end method save

        }
          //redirect
        FacesContext.getCurrentInstance().getExternalContext().redirect("/login/faces/index.xhtml");
    
    }
}
