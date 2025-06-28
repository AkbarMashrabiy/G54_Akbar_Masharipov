package uz.app.service;

import uz.app.entity.Food;
import uz.app.entity.Waiter;

import java.util.ArrayList;
import java.util.List;

import static uz.app.db.Storage.waiters;


public class WaiterService {
    public boolean addWaiter(Waiter waiter){
        return waiters.add(waiter);
    }
    public List<Waiter> getAllActiveWaiters(){
        List<Waiter> tempList = new ArrayList<>();
        for (Waiter waiter : waiters) {
            if (waiter.getActive().equals(true)){
                tempList.add(waiter);
            }
        }
        return tempList;
    }
    public boolean isExistWaiter(String fullName){
       return waiters.stream().anyMatch(waiter -> waiter.getFullName().equals(fullName));
    }
    public Waiter getWaiterWithId(String id){
        for (Waiter waiter : waiters) {
            if (waiter.getId().equals(id)){
                return waiter;
            }
        }
        return null;
    }


}
