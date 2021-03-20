/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coffee;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Ryan
 */
@ManagedBean(name = "brandbean")
@RequestScoped
public class coffeeBrand {

    String brand_name;
    String country;
    int established;
    
    
    
    public coffeeBrand(){
        
    }
    
    
    public coffeeBrand(String bName,String cName, int estab){
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

    public int getEstablished() {
        return this.established;
    }

    public void setBrand_Name(String newName) {
        brand_name = newName;
    }

    public void setCountry(String newCountry) {
        country = newCountry;
    }

    public void setEstablished(int newEst) {
        established = newEst;
    }

}
