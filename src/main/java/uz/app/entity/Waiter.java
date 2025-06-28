package uz.app.entity;

import lombok.*;

import java.util.List;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Waiter {
    private final String id = UUID.randomUUID().toString();
    private String fullName;
    private List<Table> tables;
    private Double allOrdersProfit;
    private Boolean active;
}
