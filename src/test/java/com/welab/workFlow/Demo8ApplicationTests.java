package com.welab.workFlow;

import java.util.List;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSONObject;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Demo8ApplicationTests {
    @Autowired
    RuntimeService runtimeService;
    @Autowired
    RepositoryService repositoryService;
    @Autowired
    TaskService taskService;

    @Test
    public void contextLoads() {
        check();
    }

    /**
     * 部署流程
     * 
     * Created by Bryce Yao<sysyaoyulong@gmail.com> on Jun 8, 2017.
     */
    public void deployFlow() {
        Deployment deployment = repositoryService.createDeployment().addClasspathResource("processes/workFlow/MyProcess1.bpmn").deploy();
        System.out.println("deploymentId========" + JSONObject.toJSONString(deployment));
    }

    /**
     * 启动流程
     *
     *
     * Created by Bryce Yao<sysyaoyulong@gmail.com> on Jun 8, 2017.
     */
    public void start() {
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("myProcess");
        System.out.println("processInstance=========" + JSONObject.toJSONString(processInstance));
    }

    
    public void check(){
        List<Task> tasks=taskService.createTaskQuery().processDefinitionId("myProcess:3:5004").list();
        for (Task task : tasks) {
            System.out.println("task=========" + JSONObject.toJSONString(task));
        }
    }

    /**
     * 查询当前运行实例.
     * 
     * Created by Bryce Yao<sysyaoyulong@gmail.com> on Jun 8, 2017.
     */
    public void queryCount() {
        long count = runtimeService.createProcessInstanceQuery().count();
        System.out.println("count==========" + count);
    }


}
