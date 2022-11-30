package org.chisa.asset.service.impl;

import org.aspectj.weaver.ast.Var;
import org.chisa.asset.mapper.AssetGetAllMapper;
import org.chisa.asset.service.AssetGetAllService;
import org.chisa.commons.global_api.ResultVo;
import org.chisa.commons.global_dto.AssetDTO;
import org.chisa.commons.global_dto.EmpDTO;
import org.chisa.commons.global_utils.TheadLocalUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.HttpRequestHandler;

import javax.annotation.Resource;
import java.awt.*;
import java.util.List;

@Service
public class AssetGetAllServiceImpl implements AssetGetAllService {

    @Resource
    AssetGetAllMapper assetGetAllMapper;

    @Override
    public ResultVo getAssetWo() {
        Integer stockId = TheadLocalUtils.getToken().getStockId();
        List<AssetDTO> list = assetGetAllMapper.getAssetonWO(stockId);
        return new ResultVo(list);
    }

    @Override
    public ResultVo getWoStatus() {
        return new ResultVo(assetGetAllMapper.getWoStatus());
    }
}
