package org.chisa.asset.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.chisa.commons.global_dto.AssetDTO;
import org.chisa.commons.global_dto.StatusAllDTO;

import java.util.List;

@Mapper
public interface AssetGetAllMapper {
    /**
     * 获取asset中 wo_id 不为0的资产
     */
    List<AssetDTO> getAssetonWO(@Param("stockId") Integer stockId);

    List<StatusAllDTO> getWoStatus();

}
