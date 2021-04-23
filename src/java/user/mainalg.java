/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.sql.DataSource;

/**
 *
 * @author Ryan
 */
@Named("mainalg")
@ViewScoped
public class mainalg implements Serializable {

    Integer[] tastesList = {1, 1, 1, 1, 1, 1, 1, 1};
    Boolean[] aromasList = {false, false, false, false, false, false, false, false, false};

    int old_m_points;
    int old_w_points;
    int basic_points;
    int adventure_points;

    @Resource(name = "jdbc/loginpool")
    DataSource dataSource;

    private int brand_id;
    private int user_id;
    private String username;

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
    public mainalg() {

    }

    public mainalg(int brand_id, int likes_sour, int likes_winey, int likes_adicic, int likes_mellow, int likes_bland, int likes_sharp, int likes_harsh, int likes_pungent, boolean likes_flowery, boolean likes_fruity, boolean likes_herby, boolean likes_nutty, boolean likes_caramelly, boolean likes_chocolatey, boolean likes_resinous, boolean likes_spicy, boolean likes_carbony) {
        this.brand_id = brand_id;
        this.likes_sour = likes_sour;
        this.likes_winey = likes_winey;
        this.likes_adicic = likes_adicic;
        this.likes_mellow = likes_mellow;
        this.likes_bland = likes_bland;
        this.likes_sharp = likes_sharp;
        this.likes_harsh = likes_harsh;
        this.likes_pungent = likes_pungent;
        this.likes_flowery = likes_flowery;
        this.likes_fruity = likes_fruity;
        this.likes_herby = likes_herby;
        this.likes_nutty = likes_nutty;
        this.likes_caramelly = likes_caramelly;
        this.likes_chocolatey = likes_chocolatey;
        this.likes_resinous = likes_resinous;
        this.likes_spicy = likes_spicy;
        this.likes_carbony = likes_carbony;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public int getOld_m_points() {
        return old_m_points;
    }

    public void setOld_m_points(int old_m_points) {
        this.old_m_points = old_m_points;
    }

    public int getOld_w_points() {
        return old_w_points;
    }

    public void setOld_w_points(int old_w_points) {
        this.old_w_points = old_w_points;
    }

    public int getBasic_points() {
        return basic_points;
    }

    public void setBasic_points(int basic_points) {
        this.basic_points = basic_points;
    }

    public int getAdventure_points() {
        return adventure_points;
    }

    public void setAdventure_points(int adventure_points) {
        this.adventure_points = adventure_points;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }


    public int getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(int brand_id) {
        this.brand_id = brand_id;
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

        System.out.println("wag11111111111111111111");

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

    }

    public void place_into_catogory(String userName) throws SQLException {

//(old man : bitter, harsh , sour | resinous , carbony , herby)
//
//(basic drinker : mellow , adicic , bland | nutty , caramelly , chocolatey)
//
//(adventurist : winey , tart , sharp | spicey , flowery , fruity)
//
//(old woman : mellow , bland , winey | herby , flowery , resinous)



        setAdventure_points(0);
        setBasic_points(0);
        setOld_m_points(0);
        setOld_w_points(0);

        ArrayList<Integer> brand_taste_list = new ArrayList<>();
        ArrayList<Boolean> brand_Aroma_list = new ArrayList<>();

        List<Integer> tasteTemp = Arrays.asList(getLikes_adicic(), getLikes_bland(), getLikes_harsh(), getLikes_mellow(), getLikes_pungent(), getLikes_sharp(), getLikes_sour(), getLikes_winey());

        List<Boolean> aromaTemp = Arrays.asList(isLikes_caramelly(), isLikes_carbony(), isLikes_chocolatey(), isLikes_flowery(), isLikes_fruity(), isLikes_herby(), isLikes_nutty());

        brand_taste_list.addAll(tasteTemp);
        brand_Aroma_list.addAll(aromaTemp);

        for (int i = 0; i <= 7; i++) {
            switch (i) {

                case 0:
                    setBasic_points(getBasic_points() + flavourProfile.points_calc(brand_taste_list.get(0)));
                case 1:
                    setBasic_points(getBasic_points() + flavourProfile.points_calc(brand_taste_list.get(1)));
                    setOld_w_points(getOld_w_points() + flavourProfile.points_calc(brand_taste_list.get(1)));

                case 2:
                    setOld_m_points(getOld_m_points() + flavourProfile.points_calc(brand_taste_list.get(2)));

                case 3:
                    setBasic_points(getBasic_points() + flavourProfile.points_calc(brand_taste_list.get(3)));
                    setOld_w_points(getOld_w_points() + flavourProfile.points_calc(brand_taste_list.get(3)));

                case 4://old man doesnt like pungant but likes harsh which both are subcatagories of bitter so we use half points
                    setOld_m_points((int) (getOld_m_points() + (Math.round(flavourProfile.points_calc(brand_taste_list.get(4))) * 0.5)));

                case 5:
                    setAdventure_points(getAdventure_points() + flavourProfile.points_calc(brand_taste_list.get(5)));

                case 6:
                    setOld_m_points(getOld_m_points() + flavourProfile.points_calc(brand_taste_list.get(6)));

                case 7:
                    setAdventure_points(getAdventure_points() + flavourProfile.points_calc(brand_taste_list.get(7)));
                    setOld_w_points(getOld_w_points() + flavourProfile.points_calc(brand_taste_list.get(7)));

                default:
                    System.out.println("");

            }
        }

        //aromas
        for (int i = 0; i <= 6; i++) {
            switch (i) {

                case 0:
                    setBasic_points(getBasic_points() + flavourProfile.points_bool(brand_Aroma_list.get(0)));

                case 1:
                    setOld_m_points(getOld_m_points() + flavourProfile.points_bool(brand_Aroma_list.get(1)));

                case 2:
                    setBasic_points(getBasic_points() + flavourProfile.points_bool(brand_Aroma_list.get(2)));

                case 3:
                    setOld_w_points(getOld_w_points() + flavourProfile.points_bool(brand_Aroma_list.get(3)));
                    setAdventure_points(getAdventure_points() + flavourProfile.points_bool(brand_Aroma_list.get(3)));
                case 4:
                    setAdventure_points(getAdventure_points() + flavourProfile.points_bool(brand_Aroma_list.get(4)));

                case 5:
                    setOld_w_points(getOld_w_points() + flavourProfile.points_bool(brand_Aroma_list.get(5)));
                    setOld_m_points(getOld_m_points() + flavourProfile.points_bool(brand_Aroma_list.get(5)));
                case 6:
                    setBasic_points(getBasic_points() + flavourProfile.points_bool(brand_Aroma_list.get(6)));

                default:
                    System.out.println("");

            }
        }

        // alot of ifs 
        // make a list with the with info loop through that and check against a case thingy 
        System.out.println("old man " + getOld_m_points());
        System.out.println("old woman " + getOld_w_points());
        System.out.println("basic " + getBasic_points());
        System.out.println("adventure " + getAdventure_points());
        
        setUser_id(Integer.parseInt(userPage.getUserId()));
        pickGroupUser(getUser_id(), getOld_m_points(), getOld_w_points(), getBasic_points(), getAdventure_points());

    }

    public void pickGroupUser(int user_Id, int old, int oldw, int basic, int advent) throws SQLException {
        ArrayList<Integer> points = new ArrayList<>();
        ArrayList<String> pointsNames = new ArrayList<>();

        

        points.add(old);
        points.add(oldw);
        points.add(basic);
        points.add(advent);

        pointsNames.add("oldman");
        pointsNames.add("oldwoman");
        pointsNames.add("basic");
        pointsNames.add("advent");

        try {
            if ((pointsNames.get(points.indexOf(Collections.max(points)))) == "oldman") {
                addToGroup("oldman");
            } else if ((pointsNames.get(points.indexOf(Collections.max(points)))) == "oldwoman") {
                addToGroup("oldwoman");
            } else if ((pointsNames.get(points.indexOf(Collections.max(points)))) == "basic") {
                addToGroup("basic");
            } else if ((pointsNames.get(points.indexOf(Collections.max(points)))) == "advent") {
                addToGroup("advent");
            } else {
                System.out.println("something went wrong");
            }
        } catch (SQLException e) {
            System.out.println("bad boy sql");
            System.out.println(e);
        }
    }

    public void addToGroup(String groupname) throws SQLException {

        try {

            Connection connection = dataSource.getConnection();

            if (compareUser(user_id) == true) {

                PreparedStatement addEntry
                        = connection.prepareStatement("INSERT INTO APP.COFFEE_TEST_USER_ANSWERS_CATEGORY"
                                + "(USER_ID,TYPE_OF_COFFEE_DRINKER)"
                                + "VALUES ( ?, ? )");

                // specify the PreparedStatement's arguments
                addEntry.setInt(1, getUser_id());
                addEntry.setString(2, groupname);

                addEntry.executeUpdate(); // insert the entry

            } else {

                PreparedStatement EditBrand = connection.prepareStatement("UPDATE COFFEE_TEST_USER_ANSWERS_CATEGORY SET TYPE_OF_COFFEE_DRINKER = ? ");

                EditBrand.setString(1, groupname);

                EditBrand.executeUpdate();

            }
            connection.close();

        } catch (SQLException e) {
            System.out.println("bad boy sql");
            System.out.println(e);
        }

    }

    public boolean compareUser(int UserId) throws SQLException {

        Connection connection = dataSource.getConnection();
        PreparedStatement compareUser = connection.prepareStatement("select count(*) as total from COFFEE_TEST_USER_ANSWERS_CATEGORY where User_ID = ? ");
        compareUser.setInt(1, getUser_id());
        int rows;
        try (ResultSet results = compareUser.executeQuery()) {
            results.next();
            rows = results.getInt("total");
        }
        if (rows > 0) {
            return false;
        }
        return true;
    }

}
