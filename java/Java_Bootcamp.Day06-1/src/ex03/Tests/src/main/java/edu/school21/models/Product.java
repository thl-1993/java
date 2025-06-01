package edu.school21.models;

import jdk.jshell.Snippet;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@ToString
@Builder

public class Product {
    private Integer id;
    private String name;
    private int price;

}
