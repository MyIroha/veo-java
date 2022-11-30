package org.chisa.commons.global_dto;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class AssetDTO {
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

    public static boolean filterJudgment(AssetDTO assetDTO){
        if(assetDTO == null){
            return false;
        }
        if(assetDTO.getFrameNumber()!=null||assetDTO.getIotNumber()!=null){
            return true;
        }
        if(assetDTO.getVechicleType()!=null){
            return true;
        }
        return false;
    }
}
