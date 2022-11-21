package org.chisa.asset.service.impl;


import cn.hutool.core.bean.BeanUtil;
import org.chisa.asset.domain.Asset;
import org.chisa.asset.mapper.AssetMapper;
import org.chisa.asset.service.AssetService;
import org.chisa.commons.global_api.ResultVo;
import org.chisa.commons.global_dto.EmpDTO;
import org.chisa.commons.global_dto.PageDTO;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Service
public class AssetServiceImpl implements AssetService {

    @Resource
    private AssetMapper assetMapper;

    @Resource
    StringRedisTemplate stringRedisTemplate;
    @Override
    public ResultVo selectAssets(Asset asset, HttpServletRequest request) {
        String token = request.getHeader("token");
        Map<Object, Object> entries = stringRedisTemplate.opsForHash().entries(token);
        EmpDTO empDTO = BeanUtil.fillBeanWithMap(entries, new EmpDTO(), false);
        asset.setStockId(empDTO.getStockId());
        System.err.println(asset.getPageDTO().toString());
        if(asset.getPageDTO()==null){
            asset.setPageDTO(new PageDTO());
        }
        System.err.println();

        return new ResultVo(assetMapper.selectAssets(asset));
    }

    @Override
    public ResultVo modifyAssets(Asset asset, HttpServletRequest request) {
        String token = request.getHeader("token");
        Map<Object, Object> entries = stringRedisTemplate.opsForHash().entries(token);
        EmpDTO empDTO = BeanUtil.fillBeanWithMap(entries, new EmpDTO(), false);
        asset.setStockId(empDTO.getStockId());
        System.err.println(asset.toString());
        return new ResultVo(assetMapper.modifyAssets(asset));
    }

    @Override
    public ResultVo selectAsset(Integer assetId, HttpServletRequest request) {
        String token = request.getHeader("token");
        Map<Object, Object> entries = stringRedisTemplate.opsForHash().entries(token);
        EmpDTO empDTO = BeanUtil.fillBeanWithMap(entries, new EmpDTO(), false);
        return new ResultVo(assetMapper.selectAsset(assetId,empDTO.getStockId()));
    }
}
