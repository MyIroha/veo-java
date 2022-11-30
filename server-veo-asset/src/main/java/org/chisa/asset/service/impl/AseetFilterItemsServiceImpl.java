package org.chisa.asset.service.impl;

import org.chisa.commons.global_dto.StatusAllDTO;
import org.chisa.commons.global_dto.VechicleTypeDTO;
import org.chisa.asset.mapper.AseetFilterItemsMapper;
import org.chisa.asset.service.AseetFilterItemsService;
import org.chisa.commons.global_api.ResultVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AseetFilterItemsServiceImpl implements AseetFilterItemsService {

    @Resource
    AseetFilterItemsMapper aseetFilterItemsMapper;

    @Override
    public ResultVo getAseetAllFilter() {
        Map<String, List<StatusAllDTO>> collect = aseetFilterItemsMapper.getStatus()
                .stream().collect(Collectors.groupingBy(StatusAllDTO::getStatusType));
        List<VechicleTypeDTO> vechileTypes = aseetFilterItemsMapper.getVechileTypes();
        Map<String,Object> map = new HashMap<>();
        map.put("iot",collect.get("iot"));
        map.put("frame1",collect.get("frame"));
        map.put("vechileType",vechileTypes);
        return new ResultVo(map);
    }
}
