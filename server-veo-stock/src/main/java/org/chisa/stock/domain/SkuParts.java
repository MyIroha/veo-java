package org.chisa.stock.domain;

import lombok.Data;
import lombok.ToString;
import org.chisa.commons.global_dto.VechicleTypeDTO;

@Data
@ToString
public class SkuParts {
    private String skuNumber;
    private String skuName;
    private String sku_icon;
    private VechicleTypeDTO vechicleType;
}
