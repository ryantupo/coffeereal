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
public class BrandPage implements Serializable{

    @Resource(name = "jdbc/loginpool")
    DataSource dataSource;
    
    
    
    public BrandPage(){
        
    }
    

    public void compareUser() {
        
//        String BrandName = CoffeeBrands.txt1;
           String BrandName = "Costa Coffee";
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement compareUser2 = connection.prepareStatement("select * as from COFFEEBRANDS where BRAND_NAME = '" + BrandName + "' ");

            ResultSet results2 = compareUser2.executeQuery();
            coffeeBrand currentCoffeeBrand = new coffeeBrand(results2.getString("Brand_name"), results2.getString("Country_name"), results2.getInt("EST"));
                    
                
            
        } catch (SQLException e) {
            System.out.println("bad boy sql");
            System.out.println(e);
        }
       
    }
    
    
    public String redirectToShow(){
        return "show.xhtml?brandname=#{thisbean.txt1}";
        
    }
    
    
    
    
    
    
    
    
}
