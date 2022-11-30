package org.chisa.stock.domain;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class StockLoation {
    private Integer stockId;
    private String stockLocation;

}
