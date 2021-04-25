package user;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Ryan
 */
import coffee.BrandPage;
import java.io.IOException;
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
import register.loginbean;
import sun.security.x509.X400Address;

@Named("flavourbean")
@ViewScoped
public class flavourProfile implements Serializable {

    int old_m_points;
    int old_w_points;
    int basic_points;
    int adventure_points;

    ArrayList<mainalg> coffeebrands = new ArrayList<>();

    ArrayList<Integer> brand_Ids = new ArrayList<>();

    ArrayList<Integer> oldman_coffee_brands = new ArrayList<>();
    ArrayList<Integer> oldwoman_coffee_brands = new ArrayList<>();
    ArrayList<Integer> basic_coffee_brands = new ArrayList<>();
    ArrayList<Integer> adventurer_coffee_brands = new ArrayList<>();

    ArrayList<Integer> oldman_coffee_brands_points = new ArrayList<>();
    ArrayList<Integer> oldwoman_coffee_brands_points = new ArrayList<>();
    ArrayList<Integer> basic_coffee_brands_points = new ArrayList<>();
    ArrayList<Integer> adventurer_coffee_brands_points = new ArrayList<>();

    @Resource(name = "jdbc/loginpool")
    DataSource dataSource;

    public flavourProfile() {

    }

    public flavourProfile(int brand_id, int likes_sour, int likes_winey, int likes_adicic, int likes_mellow, int likes_bland, int likes_sharp, int likes_harsh, int likes_pungent, boolean likes_flowery, boolean likes_fruity, boolean likes_herby, boolean likes_nutty, boolean likes_caramelly, boolean likes_chocolatey, boolean likes_resinous, boolean likes_spicy, boolean likes_carbony) {

    }

    //function to grab all coffeebrands as mainalg classes 
    //a list for each coffee drinker type - done
    //use point system of 4 below == false 6+ is true if 0 dont show if 10 deffinitly show
    //
    public void getAllCoffeeBrands() {

        System.out.println("did i get here?");

        try {

            //get list of coffeebrand by there ID's
            Connection connection = dataSource.getConnection();

            PreparedStatement compareUser2 = connection.prepareStatement("select Brand_id from COFFEEBRANDS");
            compareUser2.executeQuery();
            ResultSet results2 = compareUser2.getResultSet();
            while (results2.next()) {

                brand_Ids.add(results2.getInt("Brand_Id"));

            }

        } catch (SQLException e) {
            System.out.println("bad boy sql");
            System.out.println(e);
        }

        //do an inner join of aromos and tastes to make mainalgs
        try {
//int likes_sour,int likes_winey,int likes_adicic,int likes_mellow ,int likes_bland,int likes_sharp,int likes_harsh,int likes_pungent, boolean likes_flowery, boolean likes_fruity, boolean likes_herby, boolean likes_nutty, boolean likes_caramelly, boolean likes_chocolatey, boolean likes_resinous, boolean likes_spicy, boolean likes_carbony
            Connection connection = dataSource.getConnection();

//                SELECT Orders.OrderID, Customers.CustomerName, Orders.OrderDate FROM Orders INNER JOIN Customers ON Orders.CustomerID = Customers.CustomerID;
//                
//                SELECT * FROM COFFEE_BRAND_AROMAS INNER JOIN COFFEE_BRAND_TASTES ON COFFEE_BRAND_AROMAS.BRAND_ID = COFFEE_BRAND_TASTES.BRAND_ID
//                
            PreparedStatement compareUser2 = connection.prepareStatement("SELECT * FROM COFFEE_BRAND_AROMAS INNER JOIN COFFEE_BRAND_TASTES ON COFFEE_BRAND_AROMAS.BRAND_ID = COFFEE_BRAND_TASTES.BRAND_ID");
            compareUser2.executeQuery();
            ResultSet results2 = compareUser2.getResultSet();
            while (results2.next()) {
                mainalg coffee1 = new mainalg(results2.getInt("BRAND_ID"), results2.getInt("SOUR"), results2.getInt("WINEY"), results2.getInt("ADICIC"), results2.getInt("MELLOW"), results2.getInt("BLAND"), results2.getInt("SHARP"), results2.getInt("HARSH"), results2.getInt("PUNGENT"), results2.getBoolean("FLOWERY"), results2.getBoolean("FRUITY"), results2.getBoolean("HERBY"), results2.getBoolean("NUTTY"), results2.getBoolean("CARAMELLY"), results2.getBoolean("CHOCOLATEY"), results2.getBoolean("RESINOUS"), results2.getBoolean("SPICY"), results2.getBoolean("CARBONY"));

                //int brand_id,int likes_sour, int likes_winey, int likes_adicic, int likes_mellow, int likes_bland, int likes_sharp, int likes_harsh, int likes_pungent, boolean likes_flowery, boolean likes_fruity, boolean likes_herby, boolean likes_nutty, boolean likes_caramelly, boolean likes_chocolatey, boolean likes_resinous, boolean likes_spicy, boolean likes_carbony
                coffeebrands.add(coffee1);

            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("bad boy sql");
            System.out.println(e);
        }

    }

    public void make_top_5s() {

        getAllCoffeeBrands();

//(old man : bitter, harsh , sour | resinous , carbony , herby)
//
//(basic drinker : mellow , adicic , bland | nutty , caramelly , chocolatey)
//
//(adventurist : winey , tart , sharp | spicey , flowery , fruity)
//
//(old woman : mellow , bland , winey | herby , flowery , resinous)
        for (mainalg brand : coffeebrands) {
            
            System.out.println("thewse are the flavourf flavs with brand id");
            System.out.println(brand.getBrand_id());

            setAdventure_points(0);
            setBasic_points(0);
            setOld_m_points(0);
            setOld_w_points(0);

            ArrayList<Integer> brand_taste_list = new ArrayList<>();
            ArrayList<Boolean> brand_Aroma_list = new ArrayList<>();

            List<Integer> tasteTemp = Arrays.asList(brand.getLikes_adicic(), brand.getLikes_bland(), brand.getLikes_harsh(), brand.getLikes_mellow(), brand.getLikes_pungent(), brand.getLikes_sharp(), brand.getLikes_sour(), brand.getLikes_winey());

            List<Boolean> aromaTemp = Arrays.asList(brand.isLikes_caramelly(), brand.isLikes_carbony(), brand.isLikes_chocolatey(), brand.isLikes_flowery(), brand.isLikes_fruity(), brand.isLikes_herby(), brand.isLikes_nutty());

            brand_taste_list.addAll(tasteTemp);
            brand_Aroma_list.addAll(aromaTemp);

            for (int i = 0; i <= 7; i++) {
                switch (i) {

                    case 0:
                        setBasic_points(getBasic_points() + points_calc(brand_taste_list.get(0)));
                    case 1:
                        setBasic_points(getBasic_points() + points_calc(brand_taste_list.get(1)));
                        setOld_w_points(getOld_w_points() + points_calc(brand_taste_list.get(1)));

                    case 2:
                        setOld_m_points(getOld_m_points() + points_calc(brand_taste_list.get(2)));

                    case 3:
                        setBasic_points(getBasic_points() + points_calc(brand_taste_list.get(3)));
                        setOld_w_points(getOld_w_points() + points_calc(brand_taste_list.get(3)));

                    case 4://old man doesnt like pungant but likes harsh which both are subcatagories of bitter so we use half points
                        setOld_m_points((int) (getOld_m_points() + (Math.round(points_calc(brand_taste_list.get(4))) * 0.5)));

                    case 5:
                        setAdventure_points(getAdventure_points() + points_calc(brand_taste_list.get(5)));

                    case 6:
                        setOld_m_points(getOld_m_points() + points_calc(brand_taste_list.get(6)));

                    case 7:
                        setAdventure_points(getAdventure_points() + points_calc(brand_taste_list.get(7)));
                        setOld_w_points(getOld_w_points() + points_calc(brand_taste_list.get(7)));

                    default:
                        System.out.println("");

                }
            }

            //aromas
            for (int i = 0; i <= 6; i++) {
                switch (i) {

                    case 0:
                        setBasic_points(getBasic_points() + points_bool(brand_Aroma_list.get(0)));

                    case 1:
                        setOld_m_points(getOld_m_points() + points_bool(brand_Aroma_list.get(1)));

                    case 2:
                        setBasic_points(getBasic_points() + points_bool(brand_Aroma_list.get(2)));

                    case 3:
                        setOld_w_points(getOld_w_points() + points_bool(brand_Aroma_list.get(3)));
                        setAdventure_points(getAdventure_points() + points_bool(brand_Aroma_list.get(3)));
                    case 4:
                        setAdventure_points(getAdventure_points() + points_bool(brand_Aroma_list.get(4)));

                    case 5:
                        setOld_w_points(getOld_w_points() + points_bool(brand_Aroma_list.get(5)));
                        setOld_m_points(getOld_m_points() + points_bool(brand_Aroma_list.get(5)));
                    case 6:
                        setBasic_points(getBasic_points() + points_bool(brand_Aroma_list.get(6)));

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
            pickGroup(brand.getBrand_id(), getOld_m_points(), getOld_w_points(), getBasic_points(), getAdventure_points());

        }

        for (int brand1 : oldman_coffee_brands) {
            System.out.println("old - " + brand1);
        }
        for (int brand2 : oldwoman_coffee_brands) {
            System.out.println("oldwoman - " + brand2);
        }
        for (int brand3 : basic_coffee_brands) {
            System.out.println("basic - " + brand3);
        }
        for (int brand4 : adventurer_coffee_brands) {
            System.out.println("adventurer - " + brand4);
        }

        //populate all tables
        cleartables();
        populate_tables_old();
        populate_tables_old_woman();
        populate_tables_basic();
        populate_tables_adventure();

    }


    public void cleartables() {

        try {
            Connection connection = dataSource.getConnection();

            PreparedStatement del_old = connection.prepareStatement("DELETE FROM COFFEE_OLDMAN ");
            PreparedStatement del_old_w = connection.prepareStatement("DELETE FROM COFFEE_OLDWOMAN_DRINKER ");
            PreparedStatement del_basic = connection.prepareStatement("DELETE FROM COFFEE_BASIC_DRINKER ");
            PreparedStatement del_advent = connection.prepareStatement("DELETE FROM COFFEE_ADVENTURIST_DRINKER ");

            del_old.executeUpdate();
            del_old_w.executeUpdate();
            del_basic.executeUpdate();
            del_advent.executeUpdate();
        } catch (SQLException e) {
            System.out.println("bad boy sql");
            System.out.println(e);
        }
    }

    //POPULATES THE OLDMAN TABLES 
    public void populate_tables_old() {

        for (int oldbrand : oldman_coffee_brands) {

            try {
                Connection connection = dataSource.getConnection();

                PreparedStatement addEntry
                        = connection.prepareStatement("INSERT INTO APP.COFFEE_OLDMAN"
                                + "(BRAND_ID,POINTS)"
                                + "VALUES ( ?, ? )");

                // specify the PreparedStatement's arguments
                addEntry.setInt(1, oldbrand);
                addEntry.setInt(2, oldman_coffee_brands_points.get(oldman_coffee_brands.indexOf(oldbrand)));

                addEntry.executeUpdate(); // insert the entry
            } catch (SQLException e) {
                System.out.println("bad boy sql");
                System.out.println(e);
            }

        }

    }

    //populates the old woman tables
    public void populate_tables_old_woman() {

        for (int oldbrand : oldwoman_coffee_brands) {

            try {
                Connection connection = dataSource.getConnection();

                PreparedStatement addEntry
                        = connection.prepareStatement("INSERT INTO APP.COFFEE_OLDWOMAN_DRINKER"
                                + "(BRAND_ID,POINTS)"
                                + "VALUES ( ?, ? )");

                // specify the PreparedStatement's arguments
                addEntry.setInt(1, oldbrand);
                addEntry.setInt(2, oldwoman_coffee_brands_points.get(oldwoman_coffee_brands.indexOf(oldbrand)));

                addEntry.executeUpdate(); // insert the entry
            } catch (SQLException e) {
                System.out.println("bad boy sql");
                System.out.println(e);
            }

        }

    }

    //populates the BASIC tables
    public void populate_tables_basic() {

        for (int oldbrand : basic_coffee_brands) {

            try {
                Connection connection = dataSource.getConnection();

                PreparedStatement addEntry
                        = connection.prepareStatement("INSERT INTO APP.COFFEE_BASIC_DRINKER"
                                + "(BRAND_ID,POINTS)"
                                + "VALUES ( ?, ? )");

                // specify the PreparedStatement's arguments
                addEntry.setInt(1, oldbrand);
                addEntry.setInt(2, basic_coffee_brands_points.get(basic_coffee_brands.indexOf(oldbrand)));

                addEntry.executeUpdate(); // insert the entry
            } catch (SQLException e) {
                System.out.println("bad boy sql");
                System.out.println(e);
            }

        }

    }

    //populates the BASIC tables
    public void populate_tables_adventure() {

        for (int oldbrand : adventurer_coffee_brands) {

            try {
                Connection connection = dataSource.getConnection();

                PreparedStatement addEntry
                        = connection.prepareStatement("INSERT INTO APP.COFFEE_ADVENTURIST_DRINKER"
                                + "(BRAND_ID,POINTS)"
                                + "VALUES ( ?, ? )");

                // specify the PreparedStatement's arguments
                addEntry.setInt(1, oldbrand);
                addEntry.setInt(2, adventurer_coffee_brands_points.get(adventurer_coffee_brands.indexOf(oldbrand)));

                addEntry.executeUpdate(); // insert the entry
            } catch (SQLException e) {
                System.out.println("bad boy sql");
                System.out.println(e);
            }

        }

    }

    public void pickGroup(int brandid, int old, int oldw, int basic, int advent) {
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

        if ((pointsNames.get(points.indexOf(Collections.max(points)))) == "oldman") {
            oldman_coffee_brands.add(brandid);
            oldman_coffee_brands_points.add(Collections.max(points));
        } else if ((pointsNames.get(points.indexOf(Collections.max(points)))) == "oldwoman") {
            oldwoman_coffee_brands.add(brandid);
            oldwoman_coffee_brands_points.add(Collections.max(points));
        } else if ((pointsNames.get(points.indexOf(Collections.max(points)))) == "basic") {
            basic_coffee_brands.add(brandid);
            basic_coffee_brands_points.add(Collections.max(points));
        } else if ((pointsNames.get(points.indexOf(Collections.max(points)))) == "advent") {
            adventurer_coffee_brands.add(brandid);
            adventurer_coffee_brands_points.add(Collections.max(points));
        } else {
            System.out.println("something went wrong");
        }
    }



    public static int points_bool(boolean like) {
        if (like) {
            return 3;
        } else {
            return 0;
        }
    }

    public static int points_calc(int num) {

        if (num < 5) {
            return ((5 - num) * -1);
        } else if (num > 5) {
            return (5 - num);
        } else {
            return 0;
        }

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

}
