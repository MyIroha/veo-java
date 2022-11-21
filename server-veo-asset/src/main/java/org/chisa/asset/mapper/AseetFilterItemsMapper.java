package org.chisa.asset.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.chisa.asset.domain.StatusAll;
import org.chisa.asset.domain.VechicleType;

import java.util.List;

@Mapper
public interface AseetFilterItemsMapper {

    List<StatusAll> getStatus();

    List<VechicleType> getVechileTypes();
}
