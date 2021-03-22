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
    
    //brand ID
    int Brand_id;
    
    
    
    //brand info
    
    String Brand_Info;
    String Brand_Origin_Info;
    String Brand_Logo_file_Name;
    
    
    

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
                setBrandid(results2.getInt("BRAND_ID"));
                setBCountry(results2.getString("COUNTRY_NAME"));
                setBEst(results2.getInt("EST"));

            }
            
            
            PreparedStatement compare_Id = connection.prepareStatement("select * from BRANDINFO where BRAND_ID = ? ");
            compare_Id.setInt(1, Brand_id);
            compare_Id.execute();
            ResultSet results3 = compare_Id.getResultSet();
            while (results3.next()) {
                setBInfo(results3.getString("BRAND_INFO"));
                setBOrigin(results3.getString("ORIGIN_INFO"));
                setBLogo(results3.getString("BRAND_LOGO_FILE_NAME"));

            }
            
            connection.close();
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
    
    
    //brand information
    
    public String getBrand_info() {
        return Brand_Info;
    }

    public String getBrand_Origin() {
        return Brand_Origin_Info;
    }

    public String getBrand_Logo() {
        return Brand_Logo_file_Name;
    }

    public void setBInfo(String newBInfo) {
        this.Brand_Info = newBInfo;
    }

    public void setBOrigin(String newBOrigin) {
        this.Brand_Origin_Info = newBOrigin;
    }

    public void setBLogo(String newBLogo) {
        this.Brand_Logo_file_Name = newBLogo;
    }
    
    //brand id
    
      public int getBrandId() {
        return Brand_id;
    }
      
    public void setBrandid(int newId){
        this.Brand_id = newId;
    }
    
    
    
    
    
    
    
    
    
    

}
