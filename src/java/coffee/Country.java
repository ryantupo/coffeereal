/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package coffee;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;
import javax.faces.application.*;
import javax.faces.bean.*;
import javax.faces.context.*;
import javax.inject.*;
import org.primefaces.event.*;

public class Country implements Serializable, Comparable<Country> {

	private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private String code;
    private Locale locale;
    private boolean rtl;

    public Country() {}

    public Country(int id, Locale locale) {
    	this(id, locale.getDisplayCountry(), locale.getCountry().toLowerCase(), locale);
    }

    public Country(int id, Locale locale, boolean rtl) {
    	this(id, locale.getDisplayCountry(), locale.getCountry().toLowerCase(), locale);
    	this.rtl = rtl;
    }


    public Country(int id, String name, String code) {
    	this(id, name, code, null);
    }

    public Country(int id, String name, String code, Locale locale) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.locale = locale;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public String getLanguage( ) {
		return locale == null ? "en" : locale.getLanguage();
	}

	public String getDisplayLanguage( ) {
		return locale == null ? "English" : locale.getDisplayLanguage();
	}

	public boolean isRtl() {
		return rtl;
	}

	public void setRtl(boolean rtl) {
		this.rtl = rtl;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return id == country.id &&
                Objects.equals(name, country.name) &&
                Objects.equals(code, country.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, code);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(Country o) {
        return name.compareTo(o.name);
    }

}