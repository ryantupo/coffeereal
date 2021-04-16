/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import coffee.BrandPage;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.sql.DataSource;
import register.loginbean;

/**
 *
 * @author Ryan
 */
@Named("mainalg")
@ViewScoped
public class mainalg implements Serializable {

    Integer[] tastesList = {1, 1, 1, 1, 1, 1, 1, 1};
    Boolean[] aromasList = {false, false, false, false, false, false, false, false, false};

    @Resource(name = "jdbc/loginpool")
    DataSource dataSource;

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

//(old man : bitter, harsh , sour | resinous , carbony , herby)
//
//(basic drinker : mellow , adicic , bland | nutty , caramelly , chocolatey)
//
//(adventurist : winey , tart , sharp | spicey , flowery , fruity)
//
//(old woman : mellow , bland , winey | herby , flowery , resinous)
//    
//    plan of action!!!!
//    
//    
//    1. get all answers
//            
//    2. make list of tastes for each type of drinker
//           - if integer is over 7 then will be changed to true
//           - if 3 or below regard as false
//           - if 4-6 regard as null
//           
//        make an alg that checks how many words/flavours the user has in common with certain coffee catogories
//    
//    3.assign the user to the table with catogories
//            
//            
//    ---------------------
//    
//    
//    function for showing profile feed:
//    
//    just show rows of the table catogories with its top 5 coffees
//       
//    
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

    public void addMessage(boolean typeofaroma, String nameofaroma) {
        String summary = typeofaroma ? "You like" + nameofaroma : "Unchecked";
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
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

    public void submitTest() {

        tastesList[0] = (getLikes_sour());
        tastesList[1] = (getLikes_winey());
        tastesList[2] = (getLikes_adicic());
        tastesList[3] = (getLikes_mellow());
        tastesList[4] = (getLikes_bland());
        tastesList[5] = (getLikes_sharp());
        tastesList[6] = (getLikes_harsh());
        tastesList[7] = (getLikes_pungent());

        aromasList[0] = (isLikes_flowery());
        aromasList[1] = (isLikes_fruity());
        aromasList[2] = (isLikes_herby());
        aromasList[3] = (isLikes_nutty());
        aromasList[4] = (isLikes_caramelly());
        aromasList[5] = (isLikes_chocolatey());
        aromasList[6] = (isLikes_resinous());
        aromasList[7] = (isLikes_spicy());
        aromasList[8] = (isLikes_carbony());

        for (int taste : tastesList) {
            System.out.println(": " + taste);
        }

        for (boolean likes : aromasList) {
            System.out.println(": " + likes);
        }

        System.out.println(tastesList.toString());
        System.out.println(aromasList.toString());

    }

}
