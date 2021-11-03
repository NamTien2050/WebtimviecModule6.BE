package com.example.casestudymodule6nhomculiee.dto;

import lombok.Data;

@Data
public class ChangeStatus {
    boolean status;

    public ChangeStatus() {
    }

    public ChangeStatus(boolean status) {
        this.status = status;
    }
}
