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
import javax.annotation.Resource;
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

    String bname;
    String bcountry;
    int bEst;
    int brand_id;
    String brand_Info;
    String brand_Origin;
    String brand_Logo_file_Name;
    String originalName;

    public BrandPage() {

    }

    public void dothething(String url) {

        setBname(url);
        setOriginalName(url);

        try {
            Connection connection = dataSource.getConnection();

            PreparedStatement compareUser2 = connection.prepareStatement("select * from COFFEEBRANDS where BRAND_NAME = ? ");
            compareUser2.setString(1, getBname());
            compareUser2.executeQuery();
            ResultSet results2 = compareUser2.getResultSet();
            while (results2.next()) {
                setBrand_id(results2.getInt("BRAND_ID"));
                setBcountry(results2.getString("COUNTRY_NAME"));
                setbEst(results2.getInt("EST"));

            }

            PreparedStatement compare_Id = connection.prepareStatement("select * from BRANDINFO where BRAND_ID = ? ");
            compare_Id.setInt(1, getBrand_id());
            compare_Id.executeQuery();
            ResultSet results3 = compare_Id.getResultSet();
            while (results3.next()) {
                setBrand_Info(results3.getString("BRAND_INFO"));
                setBrand_Origin(results3.getString("ORIGIN_INFO"));
                setBrand_Logo_file_Name(results3.getString("BRAND_LOGO_FILE_NAME"));                

            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("bad boy sql");
            System.out.println(e);
        }

    }

    public void updateBrand() throws IOException {

        try {
            Connection connection = dataSource.getConnection();

            PreparedStatement compareUser2 = connection.prepareStatement("select * from COFFEEBRANDS where BRAND_NAME = ? ");
            compareUser2.setString(1, getOriginalName());
            compareUser2.execute();
            ResultSet results2 = compareUser2.getResultSet();
            while (results2.next()) {
                setBrand_id(results2.getInt("BRAND_ID"));

            }

            PreparedStatement EditBrand = connection.prepareStatement("UPDATE coffeebrands SET brand_name = ? , country_name = ? , Est = ? WHERE brand_name = ? ");
            PreparedStatement EditBrandInfo = connection.prepareStatement("UPDATE brandinfo SET brand_id = ? , brand_info = ? , origin_info = ? , brand_logo_file_name = ? WHERE brand_id = ? ");

            EditBrand.setString(1, getBname());
            EditBrand.setString(2, getBcountry());
            EditBrand.setInt(3, getbEst());
            EditBrand.setString(4, getOriginalName());

            EditBrandInfo.setInt(1, getBrand_id());
            EditBrandInfo.setString(2, getBrand_Info());
            EditBrandInfo.setString(3, getBrand_Origin());
            EditBrandInfo.setString(4, getBrand_Logo_file_Name());
            EditBrandInfo.setInt(5, getBrand_id());

            
            EditBrand.executeUpdate();
            EditBrandInfo.executeUpdate();

            connection.close();
        } catch (SQLException e) {
            System.out.println("bad boy sql");
            System.out.println(e);
        }
        FacesContext.getCurrentInstance().getExternalContext().redirect("/login/faces/adminpage.xhtml");
    }
    
//    
//                    navBar = "<a class=\"active\" href=\"/login/faces/index.xhtml\">Home</a>"
//                        + "<a href=\"/login/faces/register.xhtml\">Register</a>"
//                        + "<a href=\"/login/faces/loginpage.xhtml\">Log in</a>";
    
    public String srcLogoGetter(){
        
        if (getBrand_Logo_file_Name().contains("https") ){
            return "<img class=\"roundimg\" src=\"" + brand_Logo_file_Name + "\" alt= \"Logo\" />";
        }else{
            return  "<img class=\"roundimg\" src=\"" + "/login/faces/resources/images/logos/" + brand_Logo_file_Name + "\" alt=\"Logo\" />" ;
        }
       
        
    }  

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public String getBcountry() {
        return bcountry;
    }

    public void setBcountry(String bcountry) {
        this.bcountry = bcountry;
    }

    public int getbEst() {
        return bEst;
    }

    public void setbEst(int bEst) {
        this.bEst = bEst;
    }


    public int getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(int brand_id) {
        this.brand_id = brand_id;
    }

    public String getBrand_Info() {
        return brand_Info;
    }

    public void setBrand_Info(String brand_Info) {
        this.brand_Info = brand_Info;
    }

    public String getBrand_Origin() {
        return brand_Origin;
    }

    public void setBrand_Origin(String brand_Origin) {
        this.brand_Origin = brand_Origin;
    }

    public String getBrand_Logo_file_Name() {
        return brand_Logo_file_Name;
    }

    public void setBrand_Logo_file_Name(String brand_Logo_file_Name) {
        this.brand_Logo_file_Name = brand_Logo_file_Name;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

}
