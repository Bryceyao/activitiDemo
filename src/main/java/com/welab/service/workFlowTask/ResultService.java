package com.welab.service.workFlowTask;

import java.io.Serializable;
import org.activiti.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Component;

@Component
public class ResultService  implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = -9214198532319385414L;

    public void judge(DelegateExecution execution) throws Exception {
        System.out.println("++++++完成查询++++++");
        String findCity= (String) execution.getVariable("findCity");
//        String updateResult= (String) execution.getVariable("updateResult");
        System.out.println("完成查询============="+findCity);
//        System.out.println("完成修改============="+updateResult);
    }
}
