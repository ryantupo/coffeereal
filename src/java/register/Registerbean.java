
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Ryan
 */
package register;

//import static com.sun.jmx.remote.internal.IIOPHelper.connect;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import static javax.management.remote.JMXConnectorFactory.connect;
import javax.sql.DataSource;
import javax.sql.rowset.CachedRowSet;

@ManagedBean(name = "Registerbean")
public class Registerbean {

    public String user_Id;
    public String userName;
    public String password;
    public String firstName;
    public String lastName;
    public String emailAddress;

    public String link;
    public String bio;

    private String error1;

    @Resource(name = "jdbc/loginpool")
    DataSource dataSource;

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

    public String getError1() {
        return error1;
    }

    public void setError1(String newError) {
        this.error1 = newError;
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

    public String getUser_Id() {
        return user_Id;
    }

    public void setUser_Id(String user_Id) {
        this.user_Id = user_Id;
    }

    public boolean checkInputs(String uname, String fname, String lname, String pword, String Email) {

        String errorOutput = "";

        final Pattern EmailAddresspattern = Pattern.compile("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$");
        if (!EmailAddresspattern.matcher(Email).matches()) {
            errorOutput += ("Error code 001 : not a Valid Email Address\n");
        }

        final Pattern FirstNamepattern = Pattern.compile("^[a-zA-Z0-9_.-]*$");
        if (!FirstNamepattern.matcher(fname).matches()) {
            errorOutput += ("Error code 002 :First name cannot contain special Characters\n");
        }

        final Pattern LastNamepattern = Pattern.compile("^[a-zA-Z0-9_.-]*$");
        if (!LastNamepattern.matcher(lname).matches()) {
            errorOutput += ("Error code 003 :Last name cannot contain special Characters\n");
        }

        final Pattern UserNamepattern = Pattern.compile("^[a-zA-Z0-9_.-]*$");
        if (!UserNamepattern.matcher(uname).matches()) {
            errorOutput += ("Error code 004 :Username cannot contain special Characters\n");
        }

        final Pattern Passwordpattern = Pattern.compile("(.*?)");
        if (!Passwordpattern.matcher(pword).matches()) {
            errorOutput += ("Error code 005 : not a Valid Password\n");
        }

        setError1(errorOutput);

        if (getError1() == "") {
            return false;
        } else {
            return true;
        }
    }

    public boolean compareUser(String Uname, String Email) throws SQLException {

        Connection connection = dataSource.getConnection();
        System.out.println("connection :" + connection);
        PreparedStatement compareUser = connection.prepareStatement("select count(*) as total from LOGINDETAILS where UserName = '" + Uname + "' ");
        System.out.println("compare User tables : " + compareUser);

        int rows;
        try (ResultSet results = compareUser.executeQuery()) {
            results.next();
            rows = results.getInt("total");
        }
        if (rows > 0) {
            return false;
        }
        return true;
    }

    public boolean compareEmail(String Uname, String Email) throws SQLException {

        Connection connection = dataSource.getConnection();
        PreparedStatement compareUser = connection.prepareStatement("select count(*) as total from LOGINDETAILS where EMAILADDRESS = '" + Email + "' ");
        int rows;
        try (ResultSet results = compareUser.executeQuery()) {
            results.next();
            rows = results.getInt("total");
        }
        if (rows > 0) {
            return false;
        }
        return true;
    }

    public void clear() {
        setUserName(null);
        setPassword(null);
        setFirstName(null);
        setLastName(null);
        setEmailAddress(null);

    }

    //database stuff
    // save a new address book entry
    public void save() throws SQLException, IOException {
        // check whether dataSource was injected by the server
        if (dataSource == null) {
            throw new SQLException("Unable to obtain DataSource");
        }

        // obtain a connection from the connection pool
        Connection connection = dataSource.getConnection();

        // check whether connection was successful
        if (connection == null) {
            throw new SQLException("Unable to connect to DataSource");
        }

        if (checkInputs(getUserName(), getFirstName(), getLastName(), getPassword(), getEmailAddress())) {
            return;

        } else {

            if (!compareUser(getUserName(), getEmailAddress())) {

                setError1("username already used");
                return;

            } else if (!compareEmail(getUserName(), getEmailAddress())) {

                setError1("Email Address already used");
                return;

            } else {
                setError1("succesfully added");

                // create a PreparedStatement to insert a new login entry
                PreparedStatement addEntry
                        = connection.prepareStatement("INSERT INTO APP.LOGINDETAILS"
                                + "(USERNAME,PASSWORD,FIRSTNAME,LASTNAME,EMAILADDRESS)"
                                + "VALUES ( ?, ?, ? , ? , ? )");

                // specify the PreparedStatement's arguments
                addEntry.setString(1, getUserName());
                addEntry.setString(2, getPassword());
                addEntry.setString(3, getFirstName());
                addEntry.setString(4, getLastName());
                addEntry.setString(5, getEmailAddress());

                addEntry.executeUpdate(); // insert the entry

                PreparedStatement compareUser2 = connection.prepareStatement("select * from logindetails where USERNAME = ? ");
                compareUser2.setString(1, getUserName());
                compareUser2.executeQuery();

                ResultSet results2 = compareUser2.getResultSet();
                while (results2.next()) {
                    setUser_Id(results2.getString("User_ID"));

                }

                PreparedStatement addEntry2
                        = connection.prepareStatement("INSERT INTO APP.USER_INFO"
                                + "(USER_ID,LINK,BIO)"
                                + "VALUES ( ?, ?, ? )");

                addEntry2.setString(1, getUser_Id());
                addEntry2.setString(2, "");
                addEntry2.setString(3, "");

                addEntry2.executeUpdate(); // insert the entry

            }// end try
        }
        connection.close(); // return this connection to pool
        clear();
        // end finally

        //redirect
        FacesContext.getCurrentInstance().getExternalContext().redirect("/login/faces/index.xhtml");
    } // end method save

} // end class Registerbean

