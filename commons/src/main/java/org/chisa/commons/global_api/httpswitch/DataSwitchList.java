package org.chisa.commons.global_api.httpswitch;

import cn.hutool.core.bean.BeanUtil;
import org.chisa.commons.global_api.ResultVo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class DataSwitchList{


    public boolean suitable(ResultVo v) {
        if(v.getCode() == 1000 && v.getData() instanceof List){
            return true;
        }
        return false;
    }

    public <T> List<T> switchData(Object obj, Class<T> clazz) throws InstantiationException, IllegalAccessException {
        List<T> list = new ArrayList();
        for(Object o : (List<?>)obj){
//            Map<String,T> map = (Map<String, T>)o;
//            list.add(map);
            T t = BeanUtil.fillBeanWithMap((Map<Object, Object>) o, clazz.newInstance(), false);
            list.add(t);
        }
        return list;
    }


}
