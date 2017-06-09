package com.welab.service.impl;

import java.io.Serializable;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

public class WResultService  implements JavaDelegate,Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = -9214198532319385414L;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        System.out.println("++++++完成查询++++++");
        String findCity= (String) execution.getVariable("findCity");
        String updateResult= (String) execution.getVariable("updateResult");
        System.out.println("完成查询============="+findCity);
        System.out.println("完成修改============="+updateResult);
    }
}
