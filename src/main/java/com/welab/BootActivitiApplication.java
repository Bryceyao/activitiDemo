package com.welab;

import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class BootActivitiApplication {

    /**
     * 启动项目并 初始化表数据
     * select * from ACT_ID_GROUP ;
     * select * from ACT_ID_USER;
     *
     * @param args
     *
     * Created by Bryce Yao<sysyaoyulong@gmail.com> on Jun 6, 2017.
     */
	public static void main(String[] args) {
		SpringApplication.run(BootActivitiApplication.class, args);
	}
	@Bean
	InitializingBean usersAndGroupsInitializer() {

		return new InitializingBean() {
			@Autowired
			private RuntimeService runtimeService;
			@Autowired
			IdentityService identityService;
			public void afterPropertiesSet() throws Exception {

				Group group = identityService.newGroup("user");
				group.setName("users");
				group.setType("security-role");
				identityService.saveGroup(group);
				User admin = identityService.newUser("admin");
				admin.setPassword("admin");
				identityService.saveUser(admin);
				Map<String, Object> variables = new HashMap<String, Object>();
				variables.put("admin","cAdmin");
				ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("hireProcessWithJpa", variables);
			}
		};
	}
}
