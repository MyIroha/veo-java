package org.chisa.employee.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.util.RandomUtil;
import org.chisa.commons.global_api.ResultVo;
import org.chisa.employee.domain.Employee;
import org.chisa.commons.global_dto.EmpDTO;
import org.chisa.employee.dto.LoginFormDTO;
import org.chisa.employee.mapper.EmpMapper;
import org.chisa.employee.service.EmpService;
import org.chisa.commons.global_utils.RedissConstants;
import org.chisa.employee.utils.RegexUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class EmpServiceImpl implements EmpService {

    @Resource
    protected EmpMapper empMapper;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public ResultVo trueLoginByPhoneAndPwd(LoginFormDTO loginFormDTO) {
        String phone = loginFormDTO.getPhone();
        System.out.println("phone");
        if(RegexUtils.isPhoneInvalid(phone)){
            return new ResultVo(701,"手机号码错误错误");
        }
        String codeCache = stringRedisTemplate.opsForValue().get(RedissConstants.LOGIN_CODE_KEY+phone);
        if(codeCache==null){
            return new ResultVo(700,"验证码错误");
        }
        String code = loginFormDTO.getCode();
        System.out.println(code +"-"+codeCache);
        if(!code.equals(codeCache)){
            return new ResultVo(700,"验证码错误");
        }
        Employee employee = empMapper.trueLoginByPhone(phone);
        if(employee==null){
            return new ResultVo(600,"此用户不存在");
        }
        System.out.println(employee.toString());
        EmpDTO empDTO = BeanUtil.copyProperties(employee,EmpDTO.class);
        System.out.println(empDTO.toString());
        Map<String, Object> empMap = BeanUtil.beanToMap(empDTO,new HashMap<>(),
                CopyOptions.create()
                        .setIgnoreNullValue(true)
                        .setFieldValueEditor((fieldName,fieldValue)->{
                            return fieldValue.toString();
                        }));
        String  token= RedissConstants.EMP_TOKEN_KEY+UUID.randomUUID().toString();
        stringRedisTemplate.opsForHash().putAll(token,empMap);
        stringRedisTemplate.expire(token,RedissConstants.EMP_TOKEN_EXPIRE,TimeUnit.MINUTES);

        return new ResultVo(token);
    }

    @Override
    public ResultVo sendCode(String phone) {
        System.out.println(phone);
        if(RegexUtils.isPhoneInvalid(phone)){
            System.out.println(RegexUtils.isPhoneInvalid(phone));
            return new ResultVo(701,"手机号码错误错误");
        }
        String code = RandomUtil.randomNumbers(6);
        System.out.println(code);
        stringRedisTemplate.opsForValue().set(RedissConstants.LOGIN_CODE_KEY+phone,code, RedissConstants.LOGIN_CODE_EXPIRE,TimeUnit.SECONDS);
        System.out.println(new ResultVo());
        return new ResultVo();
    }
}
