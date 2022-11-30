package org.chisa.commons.global_utils;

import org.chisa.commons.global_dto.EmpDTO;

public class TheadLocalUtils {
    private TheadLocalUtils(){}

    public static final ThreadLocal<EmpDTO> THREAD_LOCAL = new ThreadLocal<>();

    public static void saveToken(EmpDTO empDTO){
        THREAD_LOCAL.set(empDTO);
    }
    public static EmpDTO getToken(){
       return THREAD_LOCAL.get();
    }
    public static void removeToken(){
        THREAD_LOCAL.remove();
    }
}
