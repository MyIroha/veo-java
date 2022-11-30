package org.chisa.wo.domain;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class WoTake {
    private String woId;
    private Integer empId;
    private Integer empGetScores;
}
