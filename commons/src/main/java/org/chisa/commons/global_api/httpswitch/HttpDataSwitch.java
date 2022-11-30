package org.chisa.commons.global_api.httpswitch;

import org.chisa.commons.global_api.ResultVo;

import java.util.List;

public interface HttpDataSwitch<V>{
    boolean suitable(ResultVo vo);

    <T> V  switchData(Object o, Class<T> clazz);



}
