package uz.app.db;

import uz.app.entity.Food;
import uz.app.entity.Order;
import uz.app.entity.Table;
import uz.app.entity.Waiter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public interface Storage {
    Scanner scnInt = new Scanner(System.in);
    Scanner scnStr = new Scanner(System.in);


    List<Food> foods = new ArrayList<>();
    List<Waiter> waiters = new ArrayList<>();
    List<Table> tables = new ArrayList<>();
    List<Order> orders = new ArrayList<>();




}
