package org.chisa.employee.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.chisa.employee.domain.Employee;

@Mapper
public interface EmpMapper {

    Employee trueLoginByPhoneAndPwd(String phone,String pwd);

    Employee trueLoginByPhone(String phone);
}
