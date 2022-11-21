package org.chisa.employee.domain;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class Employee {
    private String employeeId;
    private String employeeName;
    private String sex;
    private String phone;
    private String password;
    private String email;
    private Date createTime;
    private Date endTime;
    private int stockId;
}
