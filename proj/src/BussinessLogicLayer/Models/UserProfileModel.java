package BussinessLogicLayer.Models;

import DataAccessLayer.DAO.AppUsersDAO;

import java.util.Objects;

public class UserProfileModel {
    private String userId;
    private String name;
    private String surname;
    private String midName;
    private String phone;
    private String nationality;
    private String country;
    private String county;
    private String address;
    private String zip;
    private AppUsersModel appUsersByUserId;

    public UserProfileModel(String userId, String name, String surname, String midName, String phone, String nationality, String country, String county, String address, String zip, AppUsersModel appUsersByUserId) {
        this.userId = userId;
        this.name = name;
        this.surname = surname;
        this.midName = midName;
        this.phone = phone;
        this.nationality = nationality;
        this.country = country;
        this.county = county;
        this.address = address;
        this.zip = zip;
        this.appUsersByUserId = appUsersByUserId;
    }

    public UserProfileModel(String userId, String name, String surname, String midName, String phone, String nationality, String country, String county, String address, String zip) {
        this.userId = userId;
        this.name = name;
        this.surname = surname;
        this.midName = midName;
        this.phone = phone;
        this.nationality = nationality;
        this.country = country;
        this.county = county;
        this.address = address;
        this.zip = zip;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMidName() {
        return midName;
    }

    public void setMidName(String midName) {
        this.midName = midName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserProfileModel that = (UserProfileModel) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(name, that.name) &&
                Objects.equals(surname, that.surname) &&
                Objects.equals(midName, that.midName) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(nationality, that.nationality) &&
                Objects.equals(country, that.country) &&
                Objects.equals(county, that.county) &&
                Objects.equals(address, that.address) &&
                Objects.equals(zip, that.zip);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userId, name, surname, midName, phone, nationality, country, county, address, zip);
    }

    public AppUsersModel getAppUsersByUserId() {
        return AppUsersDAO.findById(userId);
    }

    public void setAppUsersByUserId(AppUsersModel appUsersByUserId) {
        this.appUsersByUserId = appUsersByUserId;
    }
}
