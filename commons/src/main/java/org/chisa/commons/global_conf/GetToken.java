package org.chisa.commons.global_conf;

import cn.hutool.core.bean.BeanUtil;
import org.chisa.commons.global_dto.EmpDTO;
import org.chisa.commons.global_utils.TheadLocalUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import java.util.Map;


public class GetToken implements HandlerInterceptor {

    StringRedisTemplate stringRedisTemplate;

    public GetToken(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public boolean preHandle(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        Map<Object, Object> userMap = stringRedisTemplate.opsForHash()
                .entries(token);
        EmpDTO empDTO = BeanUtil.fillBeanWithMap(userMap, new EmpDTO(), false);
            TheadLocalUtils.saveToken(empDTO);

        return true;
    }


    @Override
    public void afterCompletion(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, Object handler, Exception ex) throws Exception {
        if(TheadLocalUtils.getToken()!=null){
            TheadLocalUtils.removeToken();
        }
    }
}
