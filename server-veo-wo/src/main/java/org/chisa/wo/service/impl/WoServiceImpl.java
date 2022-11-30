package org.chisa.wo.service.impl;

import org.chisa.commons.global_api.ResultVo;
import org.chisa.commons.global_api.httpswitch.DataSwitchList;
import org.chisa.commons.global_dto.AssetDTO;
import org.chisa.commons.global_dto.EmpDTO;
import org.chisa.commons.global_dto.StatusAllDTO;
import org.chisa.commons.global_utils.TheadLocalUtils;
import org.chisa.wo.domain.Wo;
import org.chisa.wo.mapper.WoMapper;
import org.chisa.wo.service.WoService;
import org.chisa.wo.service.client.AssetClient;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class WoServiceImpl implements WoService {

    static List<StatusAllDTO> STATUSALLDTOS = new ArrayList<>();

    @Resource
    private AssetClient assetClient;

    @Resource(name = "woMapper")
    private WoMapper woMapper;


    @Override
    public ResultVo getWoList(Wo wo,String tokenKey) throws InstantiationException, IllegalAccessException {
        EmpDTO token = TheadLocalUtils.getToken();
        DataSwitchList httpDataSwitch =new DataSwitchList();
        this.getWoStatus(httpDataSwitch,tokenKey);
        if(wo == null){
            wo = new Wo();
        }
        List<AssetDTO> assetDTOS = new ArrayList<>();
        boolean b = AssetDTO.filterJudgment(wo.getAsset());
        if(b){
            ResultVo resultVo = assetClient.selectAssets(tokenKey, wo.getAsset());
            if(resultVo.getCode() != 1000){
                return new ResultVo("Number错误");
            }
            assetDTOS = httpDataSwitch.switchData(resultVo.getData(), AssetDTO.class);
        }
        if(assetDTOS == null && b && assetDTOS.isEmpty()){
            return new ResultVo("Number不存在");
        }
        //获取到输入iot和Frame 搜索获取的 资产id
        List<String> collect = new ArrayList<>();
        if(assetDTOS.size()>0){
            collect = assetDTOS.stream().map(assetDTO -> assetDTO.getAssetId()).collect(Collectors.toList());
        }
        //获取本库存的所有工单或者请求
        wo.setStockId(token.getStockId());
        List<Wo> woList = woMapper.getWoList(wo,collect);
        Boolean noData = assetDTOS.size()>0;
        if(noData) {
            List<AssetDTO> finalAssetDTOS = assetDTOS;
            System.err.println("finalAssetDTOS=====================" + finalAssetDTOS);
            woList = woList.stream().map(wo1 -> {
                return finalAssetDTOS.stream().filter(assetDTO -> {
                    return assetDTO.getAssetId().equals(wo1.getAsset().getAssetId());
                }).map(assetDTO -> {
                    wo1.setAsset(assetDTO);
                    return wo1;
                }).collect(Collectors.toList());
            }).flatMap(List::stream).collect(Collectors.toList());
        }else{
            List<String> assetId = woList.stream().map(wo1 -> String.valueOf(wo1.getAsset().getAssetId())).collect(Collectors.toList());
            List<Object> o = new ArrayList<>();
            for(String asid:assetId){
                ResultVo resultVo = assetClient.selectAsset(tokenKey, asid);
                if(resultVo.getCode() != 1000){
                    continue;
                }
                o.add(resultVo.getData());
            }
            if(o.size()>0){
                assetDTOS = httpDataSwitch.switchData(o, AssetDTO.class);
                List<AssetDTO> finalAssetDTOS1 = assetDTOS;
                woList = woList.stream().map(wo1 -> {
                    return finalAssetDTOS1.stream().filter(assetDTO -> {
                        return assetDTO.getAssetId().equals(wo1.getAsset().getAssetId());
                    }).map(assetDTO -> {
                        wo1.setAsset(assetDTO);
                        return wo1;
                    }).collect(Collectors.toList());
                }).flatMap(List::stream).collect(Collectors.toList());
            }

        }
        woList = woList.stream().map(woL -> {
            return STATUSALLDTOS.stream().filter(statusAllDTO -> {
                return statusAllDTO.getStatusId() == woL.getWoStatusId().getStatusId();
            }).map(statusAllDTO -> {
                woL.setWoStatusId(statusAllDTO);
                return woL;
            }).collect(Collectors.toList());
        }).flatMap(List::stream).collect(Collectors.toList());
        Map<String, List<Wo>> groupWo = woList.stream().collect(Collectors.groupingBy(Wo::getWoType));
        return new ResultVo(groupWo);
    }

    private void getWoStatus(DataSwitchList httpDataSwitch,String token) throws InstantiationException, IllegalAccessException {
        if(STATUSALLDTOS!=null && STATUSALLDTOS.isEmpty()){
            ResultVo woStatus = assetClient.getWoStatus(token);
            STATUSALLDTOS = httpDataSwitch.switchData(woStatus.getData(), StatusAllDTO.class);
        }
    }


    //    public ResultVo getWoList(String token) throws InstantiationException, IllegalAccessException {
//        ResultVo assetWo = assetClient.getAssetWo(token);
//        DataSwitchList httpDataSwitch =new DataSwitchList();
//        //获取asset数据
//        List<AssetDTO> getAsset = httpDataSwitch.switchData(assetWo.getData(), AssetDTO.class);
//        List<String> assetId = getAsset.stream().map(m -> String.valueOf(m.getAssetId())).collect(Collectors.toList());
//        if(assetId == null){
//            return new ResultVo();
//        }
//        //使用assetid获取wo数据
//        List<Wo> woList = woMapper.getWoList(assetId);
//        ResultVo woStatus = assetClient.getWoStatus(token);
//        STATUSALLDTOS = httpDataSwitch.switchData(woStatus.getData(), StatusAllDTO.class);
//        List<Wo> collect = woList.stream().map(wo -> {
//            return getAsset.stream().filter(assetDTO -> {
//                return wo.getAsset().getAssetId().equals(assetDTO.getAssetId());
//            }).map(assetDTO -> {
//                wo.setAsset(assetDTO);
//                return wo;
//            }).collect(Collectors.toList());
//        }).flatMap(List::stream).collect(Collectors.toList());
//        collect = collect.stream().map(wo -> {
//            return STATUSALLDTOS.stream().filter(statusAllDTO -> {
//                return statusAllDTO.getStatusId() == wo.getWoStatusId().getStatusId();
//            }).map(statusAllDTO -> {
//                wo.setWoStatusId(statusAllDTO);
//                return wo;
//            }).collect(Collectors.toList());
//        }).flatMap(List::stream).collect(Collectors.toList());
//        collect.forEach(wo->System.out.println(wo));
//        return new ResultVo(collect);
//    }
}
