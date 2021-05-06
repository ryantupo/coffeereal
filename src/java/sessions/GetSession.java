/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 *
 * @author Ryan
 */
@Named("getsession")
@ViewScoped
public class GetSession implements Serializable {

    @Resource(name = "jdbc/loginpool")
    DataSource dataSource;
    Boolean adminClearence = false;

    public static HttpSession getSession() {

        return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

    }

    public String getUsername() {

        return "Welcome, " + getSession().getAttribute("USERNAME").toString();

    }

    //return true or false wether online or not
    public boolean isLoggedIn() throws IOException {

        HttpSession session = GetSession.getSession();
        String currentuser = (String) session.getAttribute("USERNAME");

        if (currentuser != null) {
            return true;
        }

        return false;
    }

    //redirect if logged in
    public void doLoginRedirect() throws IOException {

        if (isLoggedIn() == true) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
        }
    }

    public void checkadmin() throws IOException {

        if (isLoggedIn() == true) {

            try {

                Connection connection = dataSource.getConnection();

                PreparedStatement compareUser2 = connection.prepareStatement("select * from LOGINDETAILS where USERNAME = ? ");
                compareUser2.setString(1, getSession().getAttribute("USERNAME").toString());
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

            if (adminClearence) {
                System.out.println("admin is logged in");
            } else {

                ExternalContext ec = FacesContext.getCurrentInstance()
                        .getExternalContext();
                try {
                    ec.redirect(ec.getRequestContextPath()
                            + "/error/404.xhtml");
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }

        } else {
            ExternalContext ec = FacesContext.getCurrentInstance()
                    .getExternalContext();
            try {
                ec.redirect(ec.getRequestContextPath()
                        + "/error/404.xhtml");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

    }

    public Boolean getAdminClearence() {
        return adminClearence;
    }

    public void setAdminClearence(Boolean adminClearence) {
        this.adminClearence = adminClearence;
    }

}
