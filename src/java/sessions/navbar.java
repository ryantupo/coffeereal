/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpSession;
import register.loginbean;

/**
 *
 * @author Ryan
 */
@SessionScoped
@ViewScoped
@ManagedBean(name = "navbar")
public class navbar implements Serializable {

    String navBar;
    String loggedInUsername;

    //GetSession curSession = new GetSession();
    HttpSession session = GetSession.getSession();

    String username = (String) session.getAttribute("USERNAME");

    public String navbar() {
        return loginbean.userName;
    }

    public String getNavBar() {
        if (!checkSession()) {
            navBar = "<a class=\"active\" href=\"/login/faces/index.xhtml\">Home</a>"
                    + "<a href=\"/login/faces/register.xhtml\">Register</a>"
                    + "<a href=\"/login/faces/loginpage.xhtml\">Log in</a>"
                    + "<a href=\"/login/faces/adminpage.xhtml\">admin page</a>"
                    + "<a href=\"/login/faces/registerBrand.xhtml\">register brand</a>";

            System.out.println(username);
            return navBar;

        } else {
            navBar = "<a class=\"active\" href=\"/login/faces/index.xhtml\">Home</a>"
                    + "<div class=\"welcomemessage\"><p>Welcome, " + navbar() + "</p></div>";

            System.out.println(username);

            return navBar;
        }

    }

    public boolean checkSession() throws NullPointerException {

        if (loginbean.userName == null) {
            return false;
        } else {
            return true;
        }
    }

}
