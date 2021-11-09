package com.example.casestudymodule6nhomculiee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class TopCompanys {
    String name;
    int quantity;

    public TopCompanys(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }
}
