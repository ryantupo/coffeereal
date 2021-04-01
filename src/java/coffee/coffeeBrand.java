/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coffee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.sql.DataSource;

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

    private String error1;

    @Resource(name = "jdbc/loginpool")
    DataSource dataSource;

    public coffeeBrand() {

    }

    public coffeeBrand(int b_Id, String bName, String cName, Integer estab) {
        this.brand_Id = b_Id;
        this.brand_name = bName;
        this.country = cName;
        this.established = estab;
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

    public boolean checkInputs(String Bname, String Cname, Integer Est, String B_Info, String BO_Info) {

        String errorOutput = "";

        final Pattern noSpecialpattern = Pattern.compile("^[a-zA-Z0-9_.-]*$");
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
            System.out.println("result1 : " + results);
            results.next();
            System.out.println("resultnext : " + results);
            rows = results.getInt("total");
            System.out.println("rows : " + rows);

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
    public void save() throws SQLException {
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

                }

                PreparedStatement addEntry2
                        = connection.prepareStatement("INSERT INTO APP.brandinfo"
                                + "(brand_id,brand_info,origin_info)"
                                + "VALUES ( ?, ?, ? )");

                // specify the PreparedStatement's arguments
                addEntry2.setInt(1, getBrand_Id());
                addEntry2.setString(2, getBrand_Info());
                addEntry2.setString(3, getOrigin_Info());

                addEntry2.executeUpdate(); // insert the entry

            }// end try
        }
        connection.close(); // return this connection to pool
        clear();
        // end finally
    } // end method save

}
