# activitiDemo
工作流引擎之activiti

* spring boot整合activiti只需两步

     1.添加boot的activiti模块

		<dependency>
			<groupId>org.activiti</groupId>
			<artifactId>activiti-spring-boot-starter-basic</artifactId>
			<version>${activiti.version}</version>
		</dependency>
		
    2.添加你的流程图文件

		在你boot项目的资源文件resources下新建文件夹processes，将bpmn文件置于processes文件夹下面
		
		注：spring boot容器启动的时候会去这个文件夹下找xxx.bpmn20.xml和xx.bpmn的文件并装载自动发布，如下图类可说明
		![image](https://raw.githubusercontent.com/Bryceyao/bryceFile/master/work/image/activitiDemo/20160819143438_74009.png)
		
		
		如果你想自定义，也很简单，也上图所示，只需在application.properties中加上spring.activiti.xxx=xxx等配置就好,如下图
		![image](https://raw.githubusercontent.com/Bryceyao/bryceFile/master/work/image/activitiDemo/20160819144400_28352.png)
		
		
		至此，你的spring上下文就装载了如processEngineConfiguration，processEngine，repositoryService，runtimeService，taskService，identityService等常用的操作对象实例了


* 增强你的activiti，发布restful管理activiti resource

    1.添加依赖

		<dependency>
			<groupId>org.activiti</groupId>
			<artifactId>activiti-spring-boot-starter-rest-api</artifactId>
			<version>${activiti.version}</version>
		</dependency>

    2.添加用户组以及用户，只有验证通过才能管理activiti

		你可以直接数据库手动添加表名如（act_id_group，act_id_user），也可以通过api完成，如下

				Group group = identityService.newGroup("user");
				group.setName("users");
				group.setType("security-role");
				identityService.saveGroup(group);
				User admin = identityService.newUser("kl");
				admin.setPassword("kl");
				identityService.saveUser(admin);
				
	完成以上步骤后，启动容器，访问相关查询resource，会先让你认证，认证采用的spring 的security，通过后就可以拥有所有的activiti的resource服务了，这样有个好处就是可轻松的做到工作流服务和业务应用服务的分离，这个对于多应用使用工作流来说简直就是福音
	
	这里列举几个常用的restful：如流程发布列表     http://127.0.0.1:8080/repository/deployments

	运行时流程：http://127.0.0.1:8080/runtime/process-instances/

	当然接口不仅是这些，更多的接口信息可从spring 的RequestMappingHandlerMapping : Mapped日志得知，或者官方文档http://activiti.org/userguide/index.html#_rest_api ，或者还有一种方式得知，请看下文

* 增强你的activiti，发布restful管理boot应用

    1.添加依赖

		<dependency>
			<groupId>org.activiti</groupId>
			<artifactId>activiti-spring-boot-starter-actuator</artifactId>
			<version>${activiti.version}</version>
		</dependency>

    2.相关接口信息可从spring 的RequestMappingHandlerMapping : Mapped日志得知，如
		![image](https://raw.githubusercontent.com/Bryceyao/bryceFile/master/work/image/activitiDemo/20160819143013_47940.png)


使用 http://localhost:8080/mappings就可以查看所有的restful接口信息了





















参考资料：http://www.kailing.pub/article/index/arcid/137.html
