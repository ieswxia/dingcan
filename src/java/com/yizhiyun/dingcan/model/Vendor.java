package com.yizhiyun.dingcan.model;
// Generated Jun 11, 2014 11:44:43 PM by Hibernate Tools 3.6.0



/**
 * Vendors generated by hbm2java
 */
public class Vendor  implements java.io.Serializable {


     private int vendorId;
     private String restaurantAddress;
     private String restaurantTel;
     private String restaurantName;
     private String restaurantOptionalTel;
     private String restaurantOptionalTel2;
     private String restaurantOptionalTel3;
     private String restaurantOptionalTel4;
     private String restaurantOptionalTel5;
     private Double latitude;
     private Double longitude;

    public Vendor() {
    }

	
    public Vendor(int vendorId) {
        this.vendorId = vendorId;
    }
    public Vendor(int vendorId, String restaurantAddress, String restaurantTel, String restaurantName, String restaurantOptionalTel, String restaurantOptionalTel2, String restaurantOptionalTel3, String restaurantOptionalTel4, String restaurantOptionalTel5, Double latitude, Double longitude) {
       this.vendorId = vendorId;
       this.restaurantAddress = restaurantAddress;
       this.restaurantTel = restaurantTel;
       this.restaurantName = restaurantName;
       this.restaurantOptionalTel = restaurantOptionalTel;
       this.restaurantOptionalTel2 = restaurantOptionalTel2;
       this.restaurantOptionalTel3 = restaurantOptionalTel3;
       this.restaurantOptionalTel4 = restaurantOptionalTel4;
       this.restaurantOptionalTel5 = restaurantOptionalTel5;
       this.latitude = latitude;
       this.longitude = longitude;
    }
   
    public int getVendorId() {
        return this.vendorId;
    }
    
    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }
    public String getRestaurantAddress() {
        return this.restaurantAddress;
    }
    
    public void setRestaurantAddress(String restaurantAddress) {
        this.restaurantAddress = restaurantAddress;
    }
    public String getRestaurantTel() {
        return this.restaurantTel;
    }
    
    public void setRestaurantTel(String restaurantTel) {
        this.restaurantTel = restaurantTel;
    }
    public String getRestaurantName() {
        return this.restaurantName;
    }
    
    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }
    public String getRestaurantOptionalTel() {
        return this.restaurantOptionalTel;
    }
    
    public void setRestaurantOptionalTel(String restaurantOptionalTel) {
        this.restaurantOptionalTel = restaurantOptionalTel;
    }
    public String getRestaurantOptionalTel2() {
        return this.restaurantOptionalTel2;
    }
    
    public void setRestaurantOptionalTel2(String restaurantOptionalTel2) {
        this.restaurantOptionalTel2 = restaurantOptionalTel2;
    }
    public String getRestaurantOptionalTel3() {
        return this.restaurantOptionalTel3;
    }
    
    public void setRestaurantOptionalTel3(String restaurantOptionalTel3) {
        this.restaurantOptionalTel3 = restaurantOptionalTel3;
    }
    public String getRestaurantOptionalTel4() {
        return this.restaurantOptionalTel4;
    }
    
    public void setRestaurantOptionalTel4(String restaurantOptionalTel4) {
        this.restaurantOptionalTel4 = restaurantOptionalTel4;
    }
    public String getRestaurantOptionalTel5() {
        return this.restaurantOptionalTel5;
    }
    
    public void setRestaurantOptionalTel5(String restaurantOptionalTel5) {
        this.restaurantOptionalTel5 = restaurantOptionalTel5;
    }
    public Double getLatitude() {
        return this.latitude;
    }
    
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
    public Double getLongitude() {
        return this.longitude;
    }
    
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }




}


