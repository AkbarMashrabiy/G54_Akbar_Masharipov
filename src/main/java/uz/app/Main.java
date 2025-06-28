package uz.app;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import uz.app.entity.Food;
import uz.app.entity.Order;
import uz.app.entity.Table;
import uz.app.entity.Waiter;
import uz.app.service.FoodService;
import uz.app.service.OrderService;
import uz.app.service.TableService;
import uz.app.service.WaiterService;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static uz.app.db.Storage.*;

public class Main {
    static WaiterService waiterService = new WaiterService();
    static FoodService foodService = new FoodService();
    static TableService tableService = new TableService();
    static OrderService orderService = new OrderService();
    static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    public static void main(String[] args) {


        while (true){
            System.out.print("""
                    \
                    1 Add food
                    2 Add waiter
                    3 Add table
                    4 OrderService
                    5 Show All waiters
                    
                    
                    0 Exit
                    """);

            switch (scnStr.nextLine()){
                case "0" -> { return; }
                case "1" -> addFood();
                case "2" -> addWaiter();
                case "3" -> addTable();
                case "4" -> orderService();
                case "5" -> showAllWaiters();
                default -> System.out.println("Invalid command!");
            }
        }
    }

    private static void showAllWaiters() {
        if (waiters.isEmpty()){
            System.out.println("Sorry, We have not waiter yet!");
            return;
        }
        for (Waiter waiter : waiters) {
            System.out.println(waiter);
        }
    }

    private static void orderService() {
        List<Table> allActiveTables = tableService.getAllActiveTables();
        allActiveTables.forEach(System.out::println);

        System.out.println("\n Welcome! Chose table for you: ");
        String tableId = scnStr.nextLine();
        Table table = tableService.getTableWithId(tableId);
        if (table !=null){
            List<Food> activeFoods = foodService.getAllActiveFoods();
            List<Food> selectedFoods = new ArrayList<>();

            while (true){
                activeFoods.forEach(System.out::println);
                System.out.println("\nChose food for you: ");
                System.out.print("0 Exit ");
                String food = scnStr.nextLine();
                if (selectedFoods.isEmpty() && food.equals("0")) return;
                if (selectedFoods.size() > 0  && food.equals("0")){
                    Double overallSum = 0.0;
                    for (Food selectedFood : selectedFoods) {
                        overallSum += selectedFood.getPrice();
                    }
                    orderService.addOrder(new Order(tableId, table.getWaiterId(), selectedFoods, overallSum));
                    System.out.println("Order completed Successfully!");
                    writeOrderToJson();

                    String waiterId = table.getWaiterId();
                    Waiter waiter = waiterService.getWaiterWithId(waiterId);
                    Double profit = waiter.getAllOrdersProfit();
                    Double lastProfit = profit+ overallSum;
                    waiter.setAllOrdersProfit(lastProfit);


                    return;



                }
                Food tempFood = foodService.getFoodWithId(food);
                if (tempFood != null){
                    selectedFoods.add(tempFood);
                    System.out.println("Added!");
                }else {
                    System.out.println("Sorry table not found!");
                }

            }
        }else {
            System.out.println("Sorry table not found!");
            return;
        }
    }

    private static void addTable() {
        System.out.println("Enter table Number: ");
        Integer number = scnInt.nextInt();

        if (!tableService.isExistTable(number)){
            List<Waiter> allActiveWaiters = waiterService.getAllActiveWaiters();
            if (allActiveWaiters != null) {
                allActiveWaiters.forEach(System.out::println);
                System.out.println("\n Enter waiter for this table: ");
                String waiterId = scnStr.nextLine();
                Waiter waiter = waiterService.getWaiterWithId(waiterId);
                if (waiter != null) {
                    tableService.addTable(new Table(waiterId, number, true ));
                    if (waiter.getTables() != null) {
                        List<Table> lastTables = waiter.getTables();
                        lastTables.add(tableService.getTableWithNumber(number));
                        waiter.setTables(lastTables);
                    } else {
                        List<Table> tables = new ArrayList<>();
                        tables.add(tableService.getTableWithNumber(number));
                        waiter.setTables(tables);
                    }

                    System.out.println("Table added Successfully!");
                    writeTablesToJson();
                    return;
                }else {
                    System.out.println("Sorry waiter not found!");
                    return;
                }

            }
            else {
                System.out.println("Sorry, We have not waiter yet!");
                return;
            }
        }
        System.out.println("This Table number already added!");
    }


    private static void addFood() {
        System.out.println("Enter food name: ");
        String name = scnStr.nextLine();

        System.out.println("Enter food price: ");
        Double price = scnInt.nextDouble();

        if (!foodService.isExistFood(name)){
            foodService.addFood(new Food(name, price, true));
            System.out.println("Food added Successfully!");
            writeFoodsToJson();
            return;
        }
        System.out.println("This food name already added!");
    }



    private static void addWaiter() {
        System.out.println("Enter waiter fullName: ");
        String fullName = scnStr.nextLine();

        if (!waiterService.isExistWaiter(fullName)){
            waiterService.addWaiter(new Waiter(fullName, null, null, true));
            System.out.println("Waiter added Successfully!");
            writeWaitersToJson();
            return;
        }
        System.out.println("This waiter name already added!");
    }








    private static void writeOrderToJson(){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/java/orders.json"));
            writer.write(gson.toJson(orders));
            writer.close();

        } catch (IOException e) {
            System.out.println("Error with writer");
        }
    }
    private static void writeWaitersToJson(){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/java/waiters.json"));
            writer.write(gson.toJson(waiters));
            writer.close();

        } catch (IOException e) {
            System.out.println("Error with writer");
        }
    }
    private static void writeFoodsToJson(){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/java/foods.json"));
            writer.write(gson.toJson(foods));
            writer.close();

        } catch (IOException e) {
            System.out.println("Error with writer");
        }
    }
    private static void writeTablesToJson(){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/java/tables.json"));
            writer.write(gson.toJson(tables));
            writer.close();

        } catch (IOException e) {
            System.out.println("Error with writer");
        }
    }

}