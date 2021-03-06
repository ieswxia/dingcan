package com.yizhiyun.dingcan.model;
// Generated Jun 11, 2014 11:44:43 PM by Hibernate Tools 3.6.0



/**
 * OrderItems generated by hbm2java
 */
public class OrderItem  implements java.io.Serializable {


     private long orderItemId;
     private Long orderItemVendorOrderId;
     private Integer orderItemFoodId;
     private Integer orderItemFoodCount;
     private Character orderItemStatus;

    public OrderItem() {
    }

	
    public OrderItem(long orderItemId) {
        this.orderItemId = orderItemId;
    }
    public OrderItem(long orderItemId, Long orderItemVendorOrderId, Integer orderItemFoodId, Integer orderItemFoodCount, Character orderItemStatus) {
       this.orderItemId = orderItemId;
       this.orderItemVendorOrderId = orderItemVendorOrderId;
       this.orderItemFoodId = orderItemFoodId;
       this.orderItemFoodCount = orderItemFoodCount;
       this.orderItemStatus = orderItemStatus;
    }
   
    public long getOrderItemId() {
        return this.orderItemId;
    }
    
    public void setOrderItemId(long orderItemId) {
        this.orderItemId = orderItemId;
    }
    public Long getOrderItemVendorOrderId() {
        return this.orderItemVendorOrderId;
    }
    
    public void setOrderItemVendorOrderId(Long orderItemVendorOrderId) {
        this.orderItemVendorOrderId = orderItemVendorOrderId;
    }
    public Integer getOrderItemFoodId() {
        return this.orderItemFoodId;
    }
    
    public void setOrderItemFoodId(Integer orderItemFoodId) {
        this.orderItemFoodId = orderItemFoodId;
    }
    public Integer getOrderItemFoodCount() {
        return this.orderItemFoodCount;
    }
    
    public void setOrderItemFoodCount(Integer orderItemFoodCount) {
        this.orderItemFoodCount = orderItemFoodCount;
    }
    public Character getOrderItemStatus() {
        return this.orderItemStatus;
    }
    
    public void setOrderItemStatus(Character orderItemStatus) {
        this.orderItemStatus = orderItemStatus;
    }




}


