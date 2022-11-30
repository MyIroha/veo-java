package org.chisa.wo.controller;


import org.chisa.commons.global_api.ResultVo;
import org.chisa.commons.global_api.httpswitch.DataSwitchList;
import org.chisa.commons.global_api.httpswitch.HttpDataSwitch;
import org.chisa.commons.global_dto.AssetDTO;
import org.chisa.wo.domain.Wo;
import org.chisa.wo.service.WoService;
import org.chisa.wo.service.client.AssetClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/wo")
public class WoController {

    @Resource
    WoService woService;


    @PostMapping("/getWoList")
    public ResultVo getWoList(@RequestBody Wo wo,HttpServletRequest request) throws InstantiationException, IllegalAccessException {
        String token = request.getHeader("token");
        return woService.getWoList(wo,token);
    }


}
