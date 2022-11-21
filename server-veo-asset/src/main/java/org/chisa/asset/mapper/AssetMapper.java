package org.chisa.asset.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.chisa.asset.domain.Asset;
import org.chisa.commons.global_dto.PageDTO;

import java.util.List;

@Mapper
public interface AssetMapper {

    List<Asset> selectAssets(@Param("asset") Asset asset);

    Integer modifyAssets(@Param("asset")Asset asset);

    Asset selectAsset(@Param("assetId")Integer assetId,@Param("stockId") Integer stockId);

}
