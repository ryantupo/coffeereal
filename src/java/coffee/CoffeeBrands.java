/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coffee;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.sql.DataSource;
import user.CurrentUser;

/**
 *
 * @author Ryan
 */
@Named("thisbean")
@RequestScoped
public class CoffeeBrands implements Serializable {

    private List<coffeeBrand> brands;
    private List<CurrentUser> users;
    @Resource(name = "jdbc/loginpool")
    DataSource dataSource;

    String searchResult;
    String searchResultEdit;
    String editRedirect;
    String editRedirectBio;

    int brand_id;

    @PostConstruct
    public void init() {

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement compareUser = connection.prepareStatement("SELECT * FROM coffeebrands INNER JOIN brandinfo ON coffeebrands.brand_id = brandinfo.brand_id ");
            compareUser.executeQuery();
            ResultSet results = compareUser.getResultSet();
            brands = new ArrayList<>();

            ///////// initiate a list here /////////
            while (results.next()) {

                brands.add(new coffeeBrand(results.getInt("brand_id"), results.getString("brand_name"), results.getString("country_name"), results.getInt("est"), results.getString("BRAND_LOGO_FILE_NAME")));

            }

            connection.close();

        } catch (SQLException e) {
            System.out.println("bad boy sql");
            System.out.println(e);
        }

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement compareUser = connection.prepareStatement("select * from logindetails ");
            compareUser.executeQuery();
            ResultSet results = compareUser.getResultSet();
            users = new ArrayList<>();

            ///////// initiate a list here /////////
            while (results.next()) {

                users.add(new CurrentUser(results.getString("user_id"), results.getString("username"), results.getString("password"), results.getString("firstname"), results.getString("lastname"), results.getString("emailaddress"), results.getBoolean("admin"), results.getString("profile_pic")));

            }

            connection.close();

        } catch (SQLException e) {
            System.out.println("SQL error occured");
            System.out.println(e);
        }

    }

    public List<coffeeBrand> getBrands() {
        return brands;
    }

    public List<String> completeText(String query) {
        String queryLowerCase = query.toLowerCase();
        List<String> brandname = new ArrayList<>();
        List<coffeeBrand> brandNames = getBrands();

        for (coffeeBrand brand : brandNames) {
            brandname.add(brand.getBrand_Name());
        }
        setTxt1(query);
        return brandname.stream().filter(t -> t.toLowerCase().startsWith(queryLowerCase)).collect(Collectors.toList());
    }

    public void deleteBrand(String Brand_Id_To_Delete) throws IOException {

        try {
            Connection connection = dataSource.getConnection();

            PreparedStatement DeleteUser = connection.prepareStatement("DELETE FROM coffeebrands where Brand_Id = ? ");
            PreparedStatement DeleteUserInfo = connection.prepareStatement("DELETE FROM brandinfo where Brand_Id = ? ");
            
            PreparedStatement DeleteBrand1 = connection.prepareStatement("DELETE FROM COFFEE_BRAND_AROMAS where Brand_Id = ? ");
            PreparedStatement DeleteBrand2 = connection.prepareStatement("DELETE FROM COFFEE_BRAND_TASTES where Brand_Id = ? ");
            PreparedStatement DeleteBrand3 = connection.prepareStatement("DELETE FROM COFFEE_OLDMAN where Brand_Id = ? ");
            PreparedStatement DeleteBrand4 = connection.prepareStatement("DELETE FROM COFFEE_OLDMAN where Brand_Id = ? ");
            PreparedStatement DeleteBrand5 = connection.prepareStatement("DELETE FROM COFFEE_OLDWOMAN_DRINKER where Brand_Id = ? ");
            PreparedStatement DeleteBrand6 = connection.prepareStatement("DELETE FROM COFFEE_BASIC_DRINKER where Brand_Id = ? ");
            PreparedStatement DeleteBrand7 = connection.prepareStatement("DELETE FROM COFFEE_ADVENTURIST_DRINKER where Brand_Id = ? ");
            
            DeleteUser.setString(1, Brand_Id_To_Delete);
            DeleteUserInfo.setString(1, Brand_Id_To_Delete);
            
            DeleteBrand1.setString(1, Brand_Id_To_Delete);
            DeleteBrand2.setString(1, Brand_Id_To_Delete);
            DeleteBrand3.setString(1, Brand_Id_To_Delete);
            DeleteBrand4.setString(1, Brand_Id_To_Delete);
            DeleteBrand5.setString(1, Brand_Id_To_Delete);
            DeleteBrand6.setString(1, Brand_Id_To_Delete);
            DeleteBrand7.setString(1, Brand_Id_To_Delete);
            
            
            DeleteBrand1.executeUpdate();
            DeleteBrand2.executeUpdate();
            DeleteBrand3.executeUpdate();
            DeleteBrand4.executeUpdate();
            DeleteBrand5.executeUpdate();
            DeleteBrand6.executeUpdate();
            DeleteBrand7.executeUpdate();

            DeleteUserInfo.executeUpdate();
            DeleteUser.executeUpdate();

            connection.close();

        } catch (SQLException e) {
            System.out.println("SQL error occured");
            System.out.println(e);
        }
        FacesContext.getCurrentInstance().getExternalContext().redirect("/login/faces/adminpage.xhtml");
    }

    public void deleteUser(String user_Id_To_Delete) throws IOException {

        try {
            Connection connection = dataSource.getConnection();

            PreparedStatement DeleteUser = connection.prepareStatement("DELETE FROM logindetails where user_Id = ? ");

            DeleteUser.setString(1, user_Id_To_Delete);

            DeleteUser.executeUpdate();

        } catch (SQLException e) {
            System.out.println("SQL error occured");
            System.out.println(e);
        }
        FacesContext.getCurrentInstance().getExternalContext().redirect("/login/faces/adminpage.xhtml");
    }
//   
    String txt1;

    public void setBrand_Id(int newid) {
        this.brand_id = newid;
    }

    public int getBrand_Id() {
        return brand_id;
    }

    public String getTxt1() {
        return txt1;
    }

    public void setTxt1(String txt1) {
        this.txt1 = txt1;
    }

    public String printTxt1() {
        return txt1;
    }

    public String getSearchResult() {
        return searchResult;
    }

    public String setSearchResult(String newResult) {
        if (newResult == "") {
            return "";
        } else {
            this.searchResult = newResult;
            return redirectToShow();
        }
    }

    public String getSearchResultEdit() {
        return searchResultEdit;
    }

    public void setSearchResultEdit(String searchResultEdit) {
        this.searchResultEdit = searchResultEdit;
    }

    public String redirectToShow() {
        return "show.xhtml?brandname=#{\'thisbean.getSearchResult()\'}";

    }

    public String redirectToEdit(String newResult) {
        setSearchResultEdit(newResult);
        return "editBrand.xhtml?brandname=#{\'thisbean.getSearchResultEdit()\'}";

    }

    public String redirectToEditUser(String newResult) {
        setEditRedirect(newResult);
        return "editUser.xhtml?username=#{\'thisbean.getEditRedirect()\'}";

    }

    public String redirectToEditUserBio(String newResult) {
        setEditRedirectBio(newResult);
        return "editUserBio.xhtml?username=#{\'thisbean.getEditRedirect()\'}";

    }

    public String getEditRedirect() {
        return editRedirect;
    }

    public void setEditRedirect(String editRedirect) {
        this.editRedirect = editRedirect;
    }

    public List<CurrentUser> getUsers() {
        return users;
    }

    public void setUsers(List<CurrentUser> users) {
        this.users = users;
    }

    public String getEditRedirectBio() {
        return editRedirectBio;
    }

    public void setEditRedirectBio(String editRedirectBio) {
        this.editRedirectBio = editRedirectBio;
    }

}
