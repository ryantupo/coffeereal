/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import register.loginbean;

/**
 *
 * @author Ryan
 */
@SessionScoped
@ViewScoped
@ManagedBean(name = "navbar")
public class navbar implements Serializable {

    @Resource(name = "jdbc/loginpool")
    DataSource dataSource;

    String navBar;
    Boolean adminClearence = false;

    //GetSession curSession = new GetSession();
    HttpSession session = GetSession.getSession();

    String username = (String) session.getAttribute("USERNAME");

    public String navbar() {
        return ((String) session.getAttribute("USERNAME"));
    }

    public String getNavBar() {
        try {
            Connection connection = dataSource.getConnection();

            PreparedStatement compareUser2 = connection.prepareStatement("select * from LOGINDETAILS where USERNAME = ? ");
            compareUser2.setString(1, navbar());
            compareUser2.executeQuery();
            ResultSet results2 = compareUser2.getResultSet();
            while (results2.next()) {
                setAdminClearence(results2.getBoolean("ADMIN"));

            }

            connection.close();
        } catch (SQLException e) {
            System.out.println("SQL error occured");
            System.out.println(e);
        }

        if (getAdminClearence()) {

            if (!checkSession()) {

                navBar = "<a class=\"active\" href=\"/login/faces/index.xhtml\">Home</a>"
                        + "<a href=\"/login/faces/register.xhtml\">Register</a>"
                        + "<a href=\"/login/faces/loginpage.xhtml\">Log in</a>";
                return navBar;
            } else {
                navBar = "<a class=\"active\" href=\"/login/faces/index.xhtml\">Home</a>"
                        + "<a href=\"/login/faces/adminpage.xhtml\">admin page</a>"
                        + "<a href=\"/login/faces/registerBrand.xhtml\">register brand</a>"
                        + "<a href=\"/login/faces/userProfile.xhtml\">Profile</a>"
                        + "<a href=\"/login/faces/quiz.xhtml\">Quiz</a></div>";
                return navBar;
            }
        } else {
            if (!checkSession()) {

                navBar = "<a class=\"active\" href=\"/login/faces/index.xhtml\">Home</a>"
                        + "<a href=\"/login/faces/register.xhtml\">Register</a>"
                        + "<a href=\"/login/faces/loginpage.xhtml\">Log in</a>";
                return navBar;
            } else {
                navBar = "<a class=\"active\" href=\"/login/faces/index.xhtml\">Home</a>"
                        + "<a href=\"/login/faces/userProfile.xhtml\">Profile</a>"
                        + "<a href=\"/login/faces/quiz.xhtml\">Quiz</a></div>";
                return navBar;
            }

        }

    }

    public boolean checkSession() throws NullPointerException {

        if (((String) session.getAttribute("USERNAME")) == null) {
            return false;
        } else {
            return true;
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getAdminClearence() {
        return adminClearence;
    }

    public void setAdminClearence(Boolean adminClearence) {
        this.adminClearence = adminClearence;
    }

}
