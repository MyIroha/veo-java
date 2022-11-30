package org.chisa.asset.domain;


import lombok.Data;
import lombok.ToString;
import org.chisa.commons.global_dto.PageDTO;
import org.chisa.commons.global_dto.StatusAllDTO;
import org.chisa.commons.global_dto.VechicleTypeDTO;

@Data
@ToString
public class Asset {
    /**
     * 资产id
     */
    private String assetId;
    /**
     * IOT Number
     */
    private String iotNumber;
    /**
     * Frame Number
     */
    private String frameNumber;
    /**
     * 车架类型
     */
    private VechicleTypeDTO vechicleType;
    /**
     * 所在市场
     */
    private int stockId;
    /**
     * 工单详情
     */
    private String woId;
    /**
     * iot状态
     */
    private StatusAllDTO iotStatus;
    /**
     * frame状态
     */
    private StatusAllDTO frameStatus;

    private PageDTO pageDTO;
}
