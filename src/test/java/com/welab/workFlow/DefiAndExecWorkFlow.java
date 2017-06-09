package com.welab.workFlow;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.welab.common.util.BeanUtil;


/**
 * 流程部署，启动，执行，待办
 * 
 * Created by Bryce Yao<sysyaoyulong@gmail.com> on Jun 9, 2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DefiAndExecWorkFlow {
    @Autowired
    RuntimeService runtimeService;
    @Autowired
    RepositoryService repositoryService;
    @Autowired
    TaskService taskService;
    @Autowired
    ProcessEngine processEngine;
    @Autowired
    FormService formService;

    @Test
    public void contextLoads() throws Exception {
//        createDeployment();//1发布流程
        startProcessInstanceByKey();//2发起一个流程实例
//        createTaskQueryManagement();//3 Management查询待办任务
//        completeTaskManagement();//4 Management处理某个任务
//        createTaskQueryKermit();// 5 Kermit 查询待办任务
//        completeTaskKermit(); //6 Kermit处理任务
    }
    
    /**
     * 6 Kermit 处理任务.
     *
     *
     * Created by Bryce Yao<sysyaoyulong@gmail.com> on Jun 8, 2017.
     */
    public void completeTaskKermit(){
        List<Task> tasks = taskService.createTaskQuery().taskCandidateOrAssigned("Kermit").list();
        
        for (Task task : tasks) {
            System.out.println("Task available:===== " + task.getId()+","+task.getName()+","+task.getDescription());
            Map<String, Object> taskVariables = new HashMap<String, Object>();
            taskVariables.put("vacationApproved", "false");
            taskVariables.put("managerMotivation", "We have a tight deadline!");
            taskService.complete(task.getId(), taskVariables);
        }
    }
   
    
    /**
     * 2 Kermit发起一个流程实例
     *
     *
     * Created by Bryce Yao<sysyaoyulong@gmail.com> on Jun 8, 2017.
     */
    public void startProcessInstanceByKey(){
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("employeeName", "Kermit");
        variables.put("numberOfDays", new Integer(4));
        variables.put("vacationMotivation", "I'm really tired!");

        RuntimeService runtimeService = processEngine.getRuntimeService();
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("vacationRequest", variables);

//        System.out.println("processInstance======="+JSONObject.toJSONString(processInstance));
        // Verify that we started a new process instance
        System.out.println("Number of process instances: " + runtimeService.createProcessInstanceQuery().count());
    }
    
    /**
     * 5 Kermit 查询待办任务
     *
     *
     * Created by Bryce Yao<sysyaoyulong@gmail.com> on Jun 8, 2017.
     * @throws IntrospectionException 
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     */
   
    public void  createTaskQueryKermit() throws Exception{
        List<Task> tasks = taskService.createTaskQuery().taskCandidateOrAssigned("Kermit").list();
        for (Task task : tasks) {
            System.out.println("Task available:===== " + task.getId()+","+task.getName()+","+task.getDescription()+","+task.getOwner());
            
            TaskFormData  taskFormdata = formService.getTaskFormData(task.getId());
            List<FormProperty> formPropertys = taskFormdata.getFormProperties();
            for (FormProperty formProperty : formPropertys) {
                System.out.println("formProperty=========="+JSON.toJSONString(formProperty));
            }
        }
    }
    
    /**
     * 3 management 查询待办任务
     *
     *
     * Created by Bryce Yao<sysyaoyulong@gmail.com> on Jun 8, 2017.
     * @throws IntrospectionException 
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     */
   
    public void  createTaskQueryManagement() throws Exception{
        //management为handleRequest节点配置的“activiti:candidateGroups”
        List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("management").list();
        for (Task task : tasks) {
            System.out.println("Task available:===== " + task.getId()+","+task.getName()+","+task.getDescription()+","+task.getOwner());
        }
    }
    
    /**
     * 4 management处理任务.
     *
     *
     * Created by Bryce Yao<sysyaoyulong@gmail.com> on Jun 8, 2017.
     */
    public void completeTaskManagement(){
        List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("management").list();
        for (Task task : tasks) {
            System.out.println("Task available:===== " + task.getId()+","+task.getName()+","+task.getDescription());
            Map<String, Object> taskVariables = new HashMap<String, Object>();
            taskVariables.put("vacationApproved", "false");
            taskVariables.put("managerMotivation", "We have a tight deadline!");
            taskService.complete(task.getId(), taskVariables);
        }
    }
    
    /**
     * 1发布流程.
     *
     *
     * Created by Bryce Yao<sysyaoyulong@gmail.com> on Jun 8, 2017.
     */
    public void createDeployment(){
        RepositoryService repositoryService = processEngine.getRepositoryService();
        repositoryService.createDeployment()
          .addClasspathResource("processes/workFlow/SubProcessTwoMethod.bpmn")
          .deploy();

        System.out.println("Number of process definitions==========" + repositoryService.createProcessDefinitionQuery().count());
    }
    
    
    
   
    
    
    
    
}
