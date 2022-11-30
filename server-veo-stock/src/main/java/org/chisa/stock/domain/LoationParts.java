package org.chisa.stock.domain;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class LoationParts {
    private String skuNumber;
    private Integer stockId;
    private Integer inStock;
    private Integer inTransit;
    private Integer inSurplus;
}
