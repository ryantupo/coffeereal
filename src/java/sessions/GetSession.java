/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import java.io.IOException;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ryan
 */
//@ManagedBean(name ="getsession")
@Named("getsession")
@ViewScoped
public class GetSession implements Serializable {
    
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
        
        if(currentuser != null) {
            return true;
        }
        
        return false;
    }
    
    
    
    //redirect if logged in
    public void doLoginRedirect() throws IOException {
        
        if(isLoggedIn() == true) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
        }
    }
    
}