/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import coffee.BrandPage;
import coffee.coffeeBrand;
import java.io.IOException;
import java.io.Serializable;
import static java.lang.Integer.parseInt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.annotation.Resource;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import register.loginbean;
import sessions.GetSession;

/**
 *
 * @author Ryan
 */
@Named("userPage")
@ViewScoped
public class userPage implements Serializable {

    @Resource(name = "jdbc/loginpool")
    DataSource dataSource;

    int userID;
    String userId;
    String userName;
    String password;
    String firstName;
    String lastName;
    String emailAddress;
    boolean AdminAuthentication;
    String profilePic;

    String link;
    String bio;

    String origionalName;

    //---------------------
    //category of user
    String category = "";
    String choice;

    //holds id's for top5
    ArrayList<Integer> brand_Ids = new ArrayList<>();

    //holds the top 5 objects
    ArrayList<coffeeBrand> top5Brands = new ArrayList<>();

    public userPage() {

    }

    public void dothething(String url) {

        setUserName(url);
        setOrigionalName(url);
        try {
            Connection connection = dataSource.getConnection();

            PreparedStatement compareUser2 = connection.prepareStatement("select * from logindetails where USERNAME = ? ");
            compareUser2.setString(1, getUserName());
            compareUser2.executeQuery();
            ResultSet results2 = compareUser2.getResultSet();
            while (results2.next()) {
                setUserId(results2.getString("user_ID"));
                setPassword(results2.getString("password"));
                setFirstName(results2.getString("firstname"));
                setLastName(results2.getString("lastname"));
                setEmailAddress(results2.getString("emailaddress"));
                setAdminAuthentication(results2.getBoolean("admin"));
                setProfilePic(results2.getString("profile_pic"));
            }

            PreparedStatement compareUser3 = connection.prepareStatement("select * from user_info where USER_id = ? ");
            compareUser3.setString(1, getUserId());
            compareUser3.executeQuery();
            ResultSet results3 = compareUser3.getResultSet();
            while (results3.next()) {
                setLink(results3.getString("link"));
                setBio(results3.getString("bio"));
            }
            connection.close();

        } catch (SQLException e) {
            System.out.println("SQL Error Occured");
            System.out.println(e);
        }

    }

    public void dothething2(String url) {

        setUserName(url);
        setOrigionalName(url);
        try {
            Connection connection = dataSource.getConnection();

            PreparedStatement compareUser2 = connection.prepareStatement("select * from logindetails where USERNAME = ? ");
            compareUser2.setString(1, getUserName());
            compareUser2.executeQuery();
            ResultSet results2 = compareUser2.getResultSet();
            while (results2.next()) {
                setUserId(results2.getString("user_ID"));
                setPassword(results2.getString("password"));
                setFirstName(results2.getString("firstname"));
                setLastName(results2.getString("lastname"));
                setEmailAddress(results2.getString("emailaddress"));
                setAdminAuthentication(results2.getBoolean("admin"));
                setProfilePic(results2.getString("profile_pic"));
            }

            PreparedStatement compareUser3 = connection.prepareStatement("select * from user_info where USER_id = ? ");
            compareUser3.setString(1, getUserId());
            compareUser3.executeQuery();
            ResultSet results3 = compareUser3.getResultSet();
            while (results3.next()) {
                setLink(results3.getString("link"));
                setBio(results3.getString("bio"));
            }
            connection.close();

        } catch (SQLException e) {
            System.out.println("SQL Error Occured");
            System.out.println(e);
        }

        getTop5();

    }

    public void getTop5() {

        getUserCategory();

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement compareUser = connection.prepareStatement("SELECT * FROM COFFEE_OLDMAN ORDER BY points desc FETCH NEXT 5 ROWS ONLY");

            switch (category) {
                case "oldman":
                    compareUser = connection.prepareStatement("SELECT * FROM COFFEE_OLDMAN ORDER BY points desc FETCH NEXT 5 ROWS ONLY");
                    break;
                case "oldwoman":
                    compareUser = connection.prepareStatement("SELECT * FROM COFFEE_OLDWOMAN_DRINKER ORDER BY points desc FETCH NEXT 5 ROWS ONLY");
                    break;
                case "advent":
                    compareUser = connection.prepareStatement("SELECT * FROM COFFEE_ADVENTURIST_DRINKER ORDER BY points desc FETCH NEXT 5 ROWS ONLY");
                    break;
                case "basic":
                    compareUser = connection.prepareStatement("SELECT * FROM COFFEE_BASIC_DRINKER ORDER BY points desc FETCH NEXT 5 ROWS ONLY");
                    break;
                default:
                    System.out.println("bad boy no logged in");
            }

            compareUser.executeQuery();
            ResultSet results = compareUser.getResultSet();
            ///////// initiate a list here /////////
            while (results.next()) {

                brand_Ids.add(results.getInt("brand_id"));
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("SQL Error Occured");
            System.out.println(e);
        }

        top5Brands.clear();

        for (int brand : brand_Ids) {

            try {
                Connection connection = dataSource.getConnection();

                PreparedStatement compareUser = connection.prepareStatement("SELECT * FROM coffeebrands INNER JOIN brandinfo ON coffeebrands.brand_id = brandinfo.brand_id where coffeebrands.brand_id = ? ");

                compareUser.setInt(1, brand);
                compareUser.executeQuery();
                ResultSet results = compareUser.getResultSet();
                while (results.next()) {
                    coffeeBrand brand1 = new coffeeBrand(brand, results.getString("brand_name"), results.getString("country_name"), results.getInt("est"), results.getString("brand_logo_file_name"));

                    top5Brands.add(brand1);
                }
                connection.close();
            } catch (SQLException e) {
                System.out.println("SQL Error Occured");
                System.out.println(e);
            }

        }

    }

    //sets the category for drinker of the current user locally
    public void getUserCategory() {

        try {
            Connection connection = dataSource.getConnection();

            PreparedStatement compareUser2 = connection.prepareStatement("select * from logindetails where USERNAME = ? ");
            compareUser2.setString(1, getOrigionalName());
            compareUser2.executeQuery();

            ResultSet results = compareUser2.getResultSet();
            while (results.next()) {
                setUserID(results.getInt("user_ID"));
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("SQL Error Occured");
            System.out.println(e);
        }

        //get catorgory for user
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement compareUser = connection.prepareStatement("select * from COFFEE_TEST_USER_ANSWERS_CATEGORY WHERE USER_ID = ? ");
            compareUser.setInt(1, userID);
            compareUser.executeQuery();
            ResultSet results = compareUser.getResultSet();
            while (results.next()) {

                setCategory(results.getString("TYPE_OF_COFFEE_DRINKER"));

            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("SQL Error Occured");
            System.out.println(e);
        }

    }

    public void updateUser() throws IOException {

        //session code
        HttpSession session = GetSession.getSession();

        try {
            Connection connection = dataSource.getConnection();

            PreparedStatement EditBrand = connection.prepareStatement("UPDATE logindetails SET username = ? , password = ? , firstname = ? , lastname = ? , emailaddress = ? , admin = ? , profile_pic = ? WHERE username = ? ");

            EditBrand.setString(1, getUserName());
            EditBrand.setString(2, getPassword());
            EditBrand.setString(3, getFirstName());
            EditBrand.setString(4, getLastName());
            EditBrand.setString(5, getEmailAddress());
            EditBrand.setBoolean(6, isAdminAuthentication());
            EditBrand.setString(7, getProfilePic());
            EditBrand.setString(8, getOrigionalName());

            EditBrand.executeUpdate();

            connection.close();
        } catch (SQLException e) {
            System.out.println("SQL Error Occured");
            System.out.println(e);
        }

        if (session.getAttribute("USERNAME") == getOrigionalName()) {

            session.setAttribute("USERNAME", getUserName());
        }

        FacesContext.getCurrentInstance().getExternalContext().redirect("/login/faces/adminpage.xhtml");
    }

    public void updateUserbio() throws IOException {

        try {
            Connection connection = dataSource.getConnection();

            PreparedStatement compareUser2 = connection.prepareStatement("select * from logindetails where USERNAME = ? ");
            compareUser2.setString(1, getOrigionalName());
            compareUser2.executeQuery();
            ResultSet results2 = compareUser2.getResultSet();
            while (results2.next()) {
                setUserId(results2.getString("user_ID"));
            }

            PreparedStatement EditBrand = connection.prepareStatement("UPDATE logindetails SET username = ? , password = ? , firstname = ? , lastname = ? , emailaddress = ? , profile_pic = ? WHERE username = ? ");
            PreparedStatement EditBio = connection.prepareStatement("UPDATE user_info SET link = ? , bio = ? WHERE user_id = ? ");

            EditBrand.setString(1, getUserName());
            EditBrand.setString(2, getPassword());
            EditBrand.setString(3, getFirstName());
            EditBrand.setString(4, getLastName());
            EditBrand.setString(5, getEmailAddress());
            EditBrand.setString(6, getProfilePic());
            EditBrand.setString(7, getOrigionalName());

            EditBio.setString(1, getLink());
            EditBio.setString(2, getBio());
            EditBio.setString(3, getUserId());

            EditBio.executeUpdate();
            EditBrand.executeUpdate();

            connection.close();
        } catch (SQLException e) {
            System.out.println("SQL Error Occured");
            System.out.println(e);
        }
        FacesContext.getCurrentInstance().getExternalContext().redirect("/login/faces/userProfile.xhtml");
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public ArrayList<Integer> getBrand_Ids() {
        return brand_Ids;
    }

    public void setBrand_Ids(ArrayList<Integer> brand_Ids) {
        this.brand_Ids = brand_Ids;
    }

    public ArrayList<coffeeBrand> getTop5Brands() {
        return top5Brands;
    }

    public void setTop5Brands(ArrayList<coffeeBrand> top5Brands) {
        this.top5Brands = top5Brands;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public boolean isAdminAuthentication() {
        return AdminAuthentication;
    }

    public void setAdminAuthentication(boolean AdminAuthentication) {
        this.AdminAuthentication = AdminAuthentication;
    }

    public String getOrigionalName() {
        return origionalName;
    }

    public void setOrigionalName(String origionalName) {
        this.origionalName = origionalName;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String srcProfilePicGetter() {

        if (profilePic == null) {
            return "<img class=\"roundimg\" src=\"" + "https://associatestimes.com/wp-content/uploads/2021/01/202002020449273339_Frantic-search-on-for-3-Chinese-missing-in-Vellore_SECVPF.jpg" + "\" alt= \"Logo\" />";
        } else if (profilePic.contains("https")) {
            return "<img class=\"roundimg\" src=\"" + profilePic + "\" alt= \"Logo\" />";
        } else {
            return "<img class=\"roundimg\" src=\"" + "/login/faces/resources/images/logos/" + profilePic + "\" alt=\"Logo\" />";
        }

    }

}
