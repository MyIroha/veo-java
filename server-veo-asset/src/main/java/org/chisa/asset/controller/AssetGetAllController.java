package org.chisa.asset.controller;

import org.chisa.asset.service.AssetGetAllService;
import org.chisa.commons.global_api.ResultVo;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/getAseetByme")
public class AssetGetAllController {

    @Resource
    AssetGetAllService assetGetAllService;

    @GetMapping("/getAseetWo")
    public ResultVo getAssetWo(){
        return assetGetAllService.getAssetWo();
    }

    @GetMapping("/getWoStatus")
    public ResultVo getWoStatus(){
        return assetGetAllService.getWoStatus();
    }
}
