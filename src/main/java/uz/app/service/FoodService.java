package uz.app.service;

import uz.app.entity.Food;

import java.util.ArrayList;
import java.util.List;

import static uz.app.db.Storage.foods;

public class FoodService {

    public boolean addFood(Food food){
        return foods.add(food);
    }

    public List<Food> getAllActiveFoods(){
        List<Food> tempList = new ArrayList<>();
        for (Food food : foods) {
            if (food.getActive().equals(true)){
                tempList.add(food);
            }
        }
        return tempList;
    }

    public boolean isExistFood(String foodName){
        return foods.stream().anyMatch(food -> food.getName().equals(foodName));
    }
    public Food getFoodWithId(String id){
        for (Food food : foods) {
            if (food.getId().equals(id)){
                return food;
            }
        }
        return null;
    }

}
