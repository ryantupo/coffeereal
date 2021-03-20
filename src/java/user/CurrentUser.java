/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.sql.DataSource;
//import static com.sun.jmx.remote.internal.IIOPHelper.connect;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.view.ViewScoped;
import static javax.management.remote.JMXConnectorFactory.connect;
import javax.sql.DataSource;
import javax.sql.rowset.CachedRowSet;

/**
 *
 * @author Ryan
 */
@ManagedBean(name = "Userbean")
@SessionScoped
public class CurrentUser implements Serializable{

    public String userId;
    public String userName;
    public String password;
    public String firstName;
    public String lastName;
    public String emailAddress;

    public CurrentUser(String UserId ,String Uname, String Pword, String Fname, String Lname, String Email) {
        this.userId = UserId;
        this.userName = Uname;
        this.password = Pword;
        this.firstName = Fname;
        this.lastName = Lname;
        this.emailAddress = Email;
    }

    @Resource(name = "jdbc/loginpool")
    DataSource dataSource;

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setUserId(String inputUserId) {
        this.userId = inputUserId;
    }

    public void setUserName(String inputUserName) {
        this.userName = inputUserName;
    }

    public void setPassword(String inputPassword) {
        this.password = inputPassword;
    }

    public void setFirstName(String inputName) {
        this.firstName = inputName;
    }

    public void setLastName(String inputLastName) {
        this.lastName = inputLastName;
    }

    public void setEmailAddress(String inputEmailAddress) {
        this.emailAddress = inputEmailAddress;
    }

}
