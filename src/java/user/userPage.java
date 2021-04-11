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
import javax.annotation.Resource;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.sql.DataSource;
import register.loginbean;

/**
 *
 * @author Ryan
 */
@Named("userPage")
@ViewScoped
public class userPage implements Serializable {

    @Resource(name = "jdbc/loginpool")
    DataSource dataSource;

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

    public userPage() {

    }

    public void dothething(String url) {

        setUserName(url);
        setOrigionalName(url);
        System.out.println("MUMUMMUMUMMUMUMUMMUMUMU");
        System.out.println(url);
        System.out.println("MUMUMMUMUMMUMUMUMMUMUMU");
        System.out.println(getOrigionalName());
        System.out.println("MUMUMMUMUMMUMUMUMMUMUMU");

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
            System.out.println("bad boy sql");
            System.out.println(e);
        }

    }

    public void updateUser() throws IOException {

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
            System.out.println("bad boy sql");
            System.out.println(e);
        }
        FacesContext.getCurrentInstance().getExternalContext().redirect("/login/faces/adminpage.xhtml");
    }

    public void updateUserbio() throws IOException {

        System.out.println("DADADDADADADADADADDA");
        System.out.println(getOrigionalName());
        System.out.println(getUserName());
        System.out.println("DADADADADADDADADADAD");

        try {
            Connection connection = dataSource.getConnection();

            PreparedStatement compareUser2 = connection.prepareStatement("select * from logindetails where USERNAME = ? ");
            compareUser2.setString(1, getOrigionalName());
            compareUser2.executeQuery();
            ResultSet results2 = compareUser2.getResultSet();
            while (results2.next()) {
                setUserId(results2.getString("user_ID"));
            }
            
            System.out.println("IDIDIIDIIDIDIDIDIIDID");
            System.out.println(getUserId());
            System.out.println("IDIDIIDIIDIDIDIDIIDID");

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
            System.out.println("bad boy sql");
            System.out.println(e);
        }
        FacesContext.getCurrentInstance().getExternalContext().redirect("/login/faces/userProfile.xhtml");
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
        
        System.out.println("HEREEEE");
        System.out.println(getProfilePic());
        
        
        if (profilePic==null){
         return "<img class=\"LogoImage\" src=\"" + "https://associatestimes.com/wp-content/uploads/2021/01/202002020449273339_Frantic-search-on-for-3-Chinese-missing-in-Vellore_SECVPF.jpg" + "\" alt= \"Logo\" />";
        }else if (profilePic.contains("https")) {
            return "<img class=\"LogoImage\" src=\"" + profilePic + "\" alt= \"Logo\" />";
        } else {
            return "<img class=\"LogoImage\" src=\"" + "/login/faces/resources/images/logos/" + profilePic + "\" alt=\"Logo\" />";
        }

    }

}
