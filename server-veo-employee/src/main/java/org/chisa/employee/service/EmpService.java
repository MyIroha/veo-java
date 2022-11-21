package org.chisa.employee.service;
import org.chisa.commons.global_api.ResultVo;
import org.chisa.employee.dto.LoginFormDTO;

public interface EmpService {
    ResultVo trueLoginByPhoneAndPwd(LoginFormDTO loginFormDTO);

    ResultVo sendCode(String phone);
}
