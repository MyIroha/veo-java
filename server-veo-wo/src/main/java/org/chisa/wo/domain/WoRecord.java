package org.chisa.wo.domain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class WoRecord {
    private String woId;
    private String woInfo;
    private String woInfoType;
    private Integer complete;
    private Integer empId;
}
