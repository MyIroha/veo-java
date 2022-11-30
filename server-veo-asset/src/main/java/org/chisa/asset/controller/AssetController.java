package org.chisa.asset.controller;

import org.apache.http.HttpResponse;
import org.chisa.asset.domain.Asset;
import org.chisa.asset.service.AssetService;
import org.chisa.commons.global_api.ResultVo;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/asset")
public class AssetController {
    @Resource
    private AssetService assetService;

    @PostMapping("/selectAssets")
    public ResultVo selectAssets(@RequestBody Asset asset, HttpServletRequest request) {


        return assetService.selectAssets(asset,request);
    }

    @PostMapping("/Info")
    public ResultVo modifyAssets(@RequestBody Asset asset, HttpServletRequest request) {
        return assetService.modifyAssets(asset,request);
    }

    @GetMapping("/getAssets")
    public ResultVo getAssets() {
        return new ResultVo("0");
    }


    @GetMapping("/Info/{id}")
    public ResultVo assetInfo(@PathVariable("id")Integer id,HttpServletRequest request){
        return assetService.selectAsset(id,request);
    }
}
