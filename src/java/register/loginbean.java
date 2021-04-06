/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package register;

//import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.connect;
//import static com.sun.jmx.remote.internal.IIOPHelper.connect;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import static javax.management.remote.JMXConnectorFactory.connect;
import static javax.management.remote.JMXConnectorFactory.connect;
//import static javax.rmi.PortableRemoteObject.connect;
import javax.servlet.http.*;
import javax.sql.DataSource;
import sessions.GetSession;
import user.CurrentUser;

/**
 *
 * @author Ryan
 */
@ManagedBean(name = "loginbean")
@SessionScoped

public class loginbean implements Serializable {

    @Resource(name = "jdbc/loginpool")
    DataSource dataSource;

    public static String userName;
    public String userPassword;
    public static ArrayList<String> currentData = new ArrayList<String>();

    public String getUserName() {
        return userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserName(String inputUserName) {
        this.userName = inputUserName;
    }

    public void setUserPassword(String inputPassword) {
        this.userPassword = inputPassword;
    }

    public void clear() {
        setUserName("");
        setUserPassword("");
    }

    //function that will get all data from the database then make an instance of currentUser and populate that object with the relavent data from the database
    public boolean AttemptLogin(String Uname, String Pword) throws SQLException {

        Connection connection = dataSource.getConnection();
        System.out.println("connection :" + connection);
        PreparedStatement compareUser = connection.prepareStatement("select count(*) as total from LOGINDETAILS where UserName = '" + Uname + "' and Password = '" + Pword + "' ");
        System.out.println("compare User tables : " + compareUser);

        int rows;
        try (ResultSet results = compareUser.executeQuery()) {
            System.out.println("result1 : " + results);
            results.next();
            System.out.println("resultnext : " + results);
            rows = results.getInt("total");
            System.out.println("rows : " + rows);

        }

        if (rows > 0) {

            return true;
        }

        return false;
    }

    public void SuccesfulLogin() throws SQLException, IOException {
     
        
        Connection connection = dataSource.getConnection();
        
        if (connection.equals(null)) {
            throw new SQLException("Failure to connect to the database");
        }

        //session code
        HttpSession session = GetSession.getSession();

        if (AttemptLogin(userName, userPassword)) {

            System.out.println("connection :" + connection);
            PreparedStatement compareUser = connection.prepareStatement("select * from LOGINDETAILS where UserName = '" + userName + "' and Password = '" + userPassword + "' ");
            System.out.println("compare User tables : " + compareUser);

            ResultSet set = compareUser.executeQuery();
            if (set.next()) {
                System.out.println("inside next" + userName);
                session.setAttribute("USERNAME", userName);

                CurrentUser currentUser = new CurrentUser(set.getString("USER_ID"), set.getString("USERNAME"), set.getString("PASSWORD"), set.getString("FIRSTNAME"), set.getString("LASTNAME"), set.getString("EMAILADDRESS"),set.getBoolean("admin"));
                setUserName(((String) session.getAttribute("USERNAME")));
                FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
                System.out.println("bsdfmbs");
            }

//            }

        } else {
            session.invalidate();
            System.out.println("oooga booga smnalll caveman brain");
            //enternet invalid data
        }

    }

    //log out function / end session
    public void logout() {

        //session code
        HttpSession session = GetSession.getSession();
        session.invalidate();
        setUserName(null);
    }
}
