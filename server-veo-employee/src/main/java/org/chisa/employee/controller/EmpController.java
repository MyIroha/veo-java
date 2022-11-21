package org.chisa.employee.controller;

import lombok.extern.slf4j.Slf4j;
import org.chisa.commons.global_api.ResultVo;
import org.chisa.employee.dto.LoginFormDTO;
import org.chisa.employee.service.EmpService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/emp")
public class EmpController {

    @Resource
    private EmpService empService;

    @PostMapping("/login")
    public ResultVo loginByPhone(@RequestBody LoginFormDTO loginFormDTO){
        return empService.trueLoginByPhoneAndPwd(loginFormDTO);
    }

    @GetMapping("/sendCode/{phone}")
    public ResultVo sendCode(@PathVariable("phone")String phone){
        return  empService.sendCode(phone);
    }
}
