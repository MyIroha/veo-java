package org.chisa.wo.service;

import org.chisa.commons.global_api.ResultVo;
import org.chisa.wo.domain.Wo;

public interface WoService {
    ResultVo getWoList(Wo wo,String token) throws InstantiationException, IllegalAccessException;
}
