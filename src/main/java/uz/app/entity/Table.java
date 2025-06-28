package uz.app.entity;

import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Table {
    private final String id = UUID.randomUUID().toString();
    private String waiterId;
    private Integer number;
    private Boolean active;
}
