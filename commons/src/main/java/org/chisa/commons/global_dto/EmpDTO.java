package org.chisa.commons.global_dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class EmpDTO {
    private String employeeId;
    private String employeeName;
    private int stockId;
}
