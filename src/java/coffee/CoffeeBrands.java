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

/**
 *
 * @author Ryan
 */
@Named("thisbean")
@RequestScoped
public class CoffeeBrands implements Serializable {

    private List<coffeeBrand> brands;
    @Resource(name = "jdbc/loginpool")
    DataSource dataSource;

    String searchResult;
    String searchResultEdit;

    @PostConstruct
    public void init() {

        System.out.println("outsaidesdjbfsdlfjsdbfldsnfdsnfds");

        try {
            Connection connection = dataSource.getConnection();

            PreparedStatement compareUser = connection.prepareStatement("select * from coffeebrands ");

            compareUser.executeQuery();

            ResultSet results = compareUser.getResultSet();

            brands = new ArrayList<>();

            ///////// initiate a list here /////////
            while (results.next()) {

                /////// add to the list in here /////////
//            coffeeBrand b1 = new coffeeBrand(results.getString("brand_name"),results.getString("country"),results.getInt("est"));
//            
                brands.add(new coffeeBrand(results.getInt("brand_id"), results.getString("brand_name"), results.getString("country_name"), results.getInt("est")));
                //names.add(results.getString("brand_name"));
            }

            System.out.println(brands);

        } catch (SQLException e) {
            System.out.println("bad boy sql");
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
        //return brandname;
        System.out.println("here!!!vvvvvvvvvvvvvvvvvvvvvvvv");
        System.out.println(txt1);

        setTxt1(query);
        System.out.println("second try");
        System.out.println(txt1);
        System.out.println(printTxt1());
        return brandname.stream().filter(t -> t.toLowerCase().startsWith(queryLowerCase)).collect(Collectors.toList());
    }

    public void deleteBrand(String Brand_Id_To_Delete) throws IOException {

        try {
            Connection connection = dataSource.getConnection();

//            DELETE FROM Customer_Coupon WHERE COUPON_ID IN (
//                SELECT COUPON_ID FROM Customer_Coupon INNER JOIN Company_Coupon ON
//                Customer_Coupon.COUPON_Id = Company_Coupon.COUPON_Id WHERE COMP_Id = 123
//            );
            //"DELETE FROM customer_coupon WHERE coupon_id IN (SELECT company_coupon.coupon_Id FROM company_coupon WHERE company_coupon.company_id = ";
            //"DELETE FROM coffeebrand WHERE brand_id IN (SELECT brandinfo.brand_id FROM brandinfo WHERE brandinfo.brand_id = ";
//            
//            PreparedStatement DeleteUser = connection.prepareStatement("DELETE FROM coffeebrands WHERE brand_id IN (SELECT brandinfo.brand_id FROM brandinfo WHERE brandinfo.brand_id = ? )");
//             
            PreparedStatement DeleteUser = connection.prepareStatement("DELETE FROM coffeebrands where Brand_Id = ? ");
            PreparedStatement DeleteUserInfo = connection.prepareStatement("DELETE FROM brandinfo where Brand_Id = ? ");

            DeleteUser.setString(1, Brand_Id_To_Delete);
            DeleteUserInfo.setString(1, Brand_Id_To_Delete);

            DeleteUserInfo.executeUpdate();
            DeleteUser.executeUpdate();

        } catch (SQLException e) {
            System.out.println("bad boy sql");
            System.out.println(e);
        }
        FacesContext.getCurrentInstance().getExternalContext().redirect("/login/faces/adminpage.xhtml");
    }

//   
    String txt1;

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
        return String.valueOf(searchResult);
    }

    public String setSearchResult(String newResult) {
        this.searchResult = newResult;
        return redirectToShow();
    }

    public String getSearchResultEdit() {
        return String.valueOf(searchResultEdit);
    }

    public String setSearchResultEdit(String newResult) {
        this.searchResultEdit = newResult;
        return redirectToEdit();
    }

    public String redirectToShow() {
        return "show.xhtml?brandname=#{\'thisbean.getSearchResult()\'}";

    }

    public String redirectToEdit() {
        return "editBrand.xhtml?brandname=#{\'thisbean.getSearchResultEdit()\'}";

    }

}
