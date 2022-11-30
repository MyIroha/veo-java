package org.chisa.wo.service.client;


import org.chisa.commons.global_api.ResultVo;
import org.chisa.commons.global_dto.AssetDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "server-veo-asset")
public interface AssetClient {
    @GetMapping("/veo-asset/getAseetByme/getAseetWo")
    ResultVo getAssetWo(@RequestHeader(value = "token") String token);

    @GetMapping("/veo-asset/getAseetByme/getWoStatus")
    ResultVo getWoStatus(@RequestHeader(value = "token") String token);

    @PostMapping("/veo-asset/asset/selectAssets")
    ResultVo selectAssets(@RequestHeader(value = "token") String token, @RequestBody AssetDTO assetDTO);

    @GetMapping("/veo-asset/asset/Info/{id}")
    ResultVo selectAsset(@RequestHeader(value = "token") String token,@PathVariable(value = "id") String id);
}
