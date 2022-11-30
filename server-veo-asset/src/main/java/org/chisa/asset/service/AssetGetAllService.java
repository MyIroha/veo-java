package org.chisa.asset.service;

import org.chisa.commons.global_api.ResultVo;
import org.springframework.web.HttpRequestHandler;

public interface AssetGetAllService {
    ResultVo getAssetWo();

    ResultVo getWoStatus();
}
