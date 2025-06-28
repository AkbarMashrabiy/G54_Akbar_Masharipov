package uz.app.entity;

import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Food {
    private final String id = UUID.randomUUID().toString();
    private String name;
    private Double price;
    private Boolean active;
}
