/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

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

            PreparedStatement compareUser2 = connection.prepareStatement("select * from logindetails where username = ? ");
            compareUser2.setString(1, getOrigionalName());
            compareUser2.execute();
            ResultSet results2 = compareUser2.getResultSet();
            while (results2.next()) {
                setUserId(results2.getString("user_ID"));

            }

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
    
    
    
    

}
