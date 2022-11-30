package org.chisa.wo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.chisa.wo.domain.Wo;

import java.util.List;

@Mapper
public interface WoMapper {
//
    List<Wo> getWoList(@Param("wo")Wo wo,@Param("asseIds")List<String> assetIds);
}
