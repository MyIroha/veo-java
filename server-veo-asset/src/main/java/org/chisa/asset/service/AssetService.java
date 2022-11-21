package org.chisa.asset.service;

import org.chisa.asset.domain.Asset;
import org.chisa.commons.global_api.ResultVo;
import org.springframework.stereotype.Service;
import org.springframework.web.HttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public interface AssetService {
    ResultVo selectAssets(Asset asset, HttpServletRequest request);


    ResultVo modifyAssets(Asset asset, HttpServletRequest request);

    ResultVo selectAsset(Integer assetId, HttpServletRequest request);
}
