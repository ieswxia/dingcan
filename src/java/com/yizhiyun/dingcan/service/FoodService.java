package com.yizhiyun.dingcan.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yizhiyun.dingcan.dao.BaseDao;
import com.yizhiyun.dingcan.model.EditFoodInfo;
import com.yizhiyun.dingcan.model.Food;

@Service
@Transactional
public class FoodService {
	@Autowired
	BaseDao baseDao;

	public void saveFood(Food food) {
		baseDao.save("FoodMapper.saveFood", food);
	}

	public void editFood(Food food) {
		baseDao.update("FoodMapper.editFood", food);
	}

	public void deleteFood(Food food) {
		baseDao.delete("FoodMapper.deleteFood", food);
	}
	
	public List<Food> getFoodsByVendor(int vendorId){
		return baseDao.getList("FoodMapper.getFoodsByVendor", vendorId);
	}

	public void editFoodInfo(EditFoodInfo editFoodInfo) {
		List<Food> adds = editFoodInfo.getAdd();
		if (adds != null) {
			for (Food addFood : adds) {
				addFood.setVendorId(editFoodInfo.getVendorId());
				saveFood(addFood);
			}
		}

		List<Food> updates = editFoodInfo.getUpdate();
		if (updates != null) {
			for (Food updateFood : updates) {
				updateFood.setVendorId(editFoodInfo.getVendorId());
				editFood(updateFood);
			}
		}
		
		List<Food> deletes = editFoodInfo.getUpdate();
		if (deletes != null) {
			for (Food deleteFood : deletes) {
				deleteFood(deleteFood);
			}
		}
	}

}
