package uz.app.entity;

import java.util.List;
import lombok.*;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private final String id = UUID.randomUUID().toString();
    private String tableId;
    private String waiterId;
    private List<Food> foods;
    private Double overallPrice;
}
