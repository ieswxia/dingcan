/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.yizhiyun.dingcan.model;

import java.util.List;

/**
 *
 * @author sjqi
 */
public class EditFoodInfo {
    private int vendorId;
    
    private List<Food> add;
    
    private List<Food> delete;
    
    private List<Food> update;

    /**
     * @return the vendorId
     */
    public int getVendorId() {
        return vendorId;
    }

    /**
     * @param vendorId the vendorId to set
     */
    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }

    /**
     * @return the add
     */
    public List<Food> getAdd() {
        return add;
    }

    /**
     * @param add the add to set
     */
    public void setAdd(List<Food> add) {
        this.add = add;
    }

    /**
     * @return the delete
     */
    public List<Food> getDelete() {
        return delete;
    }

    /**
     * @param delete the delete to set
     */
    public void setDelete(List<Food> delete) {
        this.delete = delete;
    }

    /**
     * @return the update
     */
    public List<Food> getUpdate() {
        return update;
    }

    /**
     * @param update the update to set
     */
    public void setUpdate(List<Food> update) {
        this.update = update;
    }
    
    
}
