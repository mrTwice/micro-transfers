package ru.otus.java.pro.mt.notifications.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StatusInfoDto {
    private String transferId;
    private String status;
}
