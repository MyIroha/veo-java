package org.chisa.getway.api.filters;

import cn.hutool.core.bean.BeanUtil;
import com.google.gson.JsonObject;
import org.chisa.commons.global_dto.EmpDTO;
import org.chisa.commons.global_utils.RedissConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.RequestPath;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class AuthGlobalFilter implements GlobalFilter, Ordered {
    private AntPathMatcher antPathMatcher = new AntPathMatcher();
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getURI().getPath();
        if(!antPathMatcher.match("/api/emp/**",path)){

            return out(exchange,chain);
        }
        return chain.filter(exchange);
    }

    public Mono<Void> out(ServerWebExchange exchange,GatewayFilterChain chain){

        ServerHttpResponse response = exchange.getResponse();
        String code = String.valueOf(HttpStatus.PROXY_AUTHENTICATION_REQUIRED.value());
        // 1.??????????????????
//        MultiValueMap<String, String> params = exchange.getRequest().getQueryParams();
        // 2.??????authorization??????
//        String token = params.getFirst("token");
        HttpHeaders headers = exchange.getRequest().getHeaders();
        String token = headers.getFirst("token");
        if(token==null||token.isEmpty()||token.equals("null")){
            //3.??????
            //3.1.??????????????????????????????
            //3.2.????????????
            return out(response,code);
        }
        //??????????????????
        Map<Object, Object> entries = stringRedisTemplate.opsForHash()
                .entries(token);
        //?????????empDto
        EmpDTO empDTO = BeanUtil.fillBeanWithMap(entries, new EmpDTO(), false);
        System.err.println(empDTO.toString());
        System.err.println(empDTO==null||empDTO.getStockId()==0);
        if(empDTO==null||empDTO.getStockId()==0){
            //4.empdto?????? ??????token??????
            //4.2.????????????
            return out(response,code);
        }
        //5.??????token????????????
        stringRedisTemplate.expire(token, RedissConstants.EMP_TOKEN_EXPIRE, TimeUnit.MINUTES);
        return chain.filter(exchange);
    }

    private Mono<Void> out(ServerHttpResponse response,String code) {
        JsonObject message = new JsonObject();
        message.addProperty("success", false);
        message.addProperty("code", code);
        message.addProperty("data", "????????????");
        byte[] bits = message.toString().getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(bits);
        //???????????????????????????????????????????????????
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        return response.writeWith(Mono.just(buffer));
    }
}
