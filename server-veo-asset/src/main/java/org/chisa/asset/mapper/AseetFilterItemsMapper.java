package org.chisa.asset.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.chisa.commons.global_dto.StatusAllDTO;
import org.chisa.commons.global_dto.VechicleTypeDTO;

import java.util.List;

@Mapper
public interface AseetFilterItemsMapper {

    List<StatusAllDTO> getStatus();

    List<VechicleTypeDTO> getVechileTypes();
}
