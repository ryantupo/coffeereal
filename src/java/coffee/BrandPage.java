/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coffee;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.sql.DataSource;

/**
 *
 * @author Ryan
 */
@Named("brandpage")
@ViewScoped
public class BrandPage implements Serializable {

    @Resource(name = "jdbc/loginpool")
    DataSource dataSource;

    String Bname;
    String Bcountry;
    int BEst;

    public BrandPage() {

    }

    
    public void dothething(String url) {

        String BrandName = url;

        try {
            Connection connection = dataSource.getConnection();

            PreparedStatement compareUser2 = connection.prepareStatement("select * from COFFEEBRANDS where BRAND_NAME = ? ");
            compareUser2.setString(1, BrandName);
            compareUser2.execute();
            ResultSet results2 = compareUser2.getResultSet();
            while (results2.next()) {
                setBCountry(results2.getString("COUNTRY_NAME"));
                setBEst(results2.getInt("EST"));

            }

        } catch (SQLException e) {
            System.out.println("bad boy sql");
            System.out.println(e);
        }

    }

    public String getBname() {
        return Bname;
    }

    public String getBcountry() {
        return Bcountry;
    }

    public int getBEst() {
        return BEst;
    }

    public void setBname(String newBname) {
        this.Bname = newBname;
    }

    public void setBCountry(String newBCountry) {
        this.Bcountry = newBCountry;
    }

    public void setBEst(int newBEst) {
        this.BEst = newBEst;
    }

}
