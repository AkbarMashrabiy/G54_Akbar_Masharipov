package uz.app.service;

import uz.app.entity.Order;

import static uz.app.db.Storage.orders;

public class OrderService {
    public boolean addOrder(Order order){
        return orders.add(order);
    }

}
