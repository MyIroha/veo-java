package org.chisa.asset.controller;

import org.chisa.asset.service.AseetFilterItemsService;
import org.chisa.commons.global_api.ResultVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class AseetFilterItems {
    @Resource
    AseetFilterItemsService aseetFilterItemsService;
    @GetMapping("/filter/config/")
    public ResultVo getAllFilterItems(){

        return aseetFilterItemsService.getAseetAllFilter();
    }
}
