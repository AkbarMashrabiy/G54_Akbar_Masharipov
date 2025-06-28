package uz.app.service;

import uz.app.entity.Table;

import java.util.ArrayList;
import java.util.List;

import static uz.app.db.Storage.foods;
import static uz.app.db.Storage.tables;

public class TableService {

    public boolean addTable(Table table){
        return tables.add(table);
    }

    public List<Table> getAllActiveTables(){
        List<Table> tempList = new ArrayList<>();
        for (Table table : tables) {
            if (table.getActive().equals(true)){
                tempList.add(table);
            }
        }
        return tempList;
    }

    public boolean isExistTable(Integer number){
        return tables.stream().anyMatch(table -> table.getNumber().equals(number));
    }


    public Table getTableWithId(String id){
        for (Table table : tables) {
            if (table.getId().equals(id)){
                return table;
            }
        }
        return null;
    }
    public Table getTableWithNumber(Integer number){
        for (Table table : tables) {
            if (table.getNumber().equals(number)){
                return table;
            }
        }
        return null;
    }
}
