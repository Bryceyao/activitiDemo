####一般数据
##===1===通用的流程定义和流程资源
select * from ACT_GE_BYTEARRAY;
##===2===系统相关属性
select * from ACT_GE_PROPERTY;

####流程历史记录
##===3===历史的流程实例
select * from ACT_HI_ACTINST;
##===4===历史的流程附件
select * from ACT_HI_ATTACHMENT;
##===5===历史的说明性信息
select * from ACT_HI_COMMENT;
##===6===历史的流程运行中的细节信息
select * from ACT_HI_DETAIL;
##===7===历史的流程运行过程中用户关系
select * from ACT_HI_IDENTITYLINK;
##===8===历史的流程实例
select * from ACT_HI_PROCINST;
##===9===历史的任务实例
select * from ACT_HI_TASKINST;
##===10===历史的流程运行中的变量信息
select * from ACT_HI_VARINST;

####用户用户组表
##===11===身份信息-组信息
select * from ACT_ID_GROUP;
##===12===身份信息-组信息
select * from ACT_ID_INFO;
##===13===身份信息-用户和组关系的中间表
select * from ACT_ID_MEMBERSHIP;
##===14===身份信息-用户信息
select * from ACT_ID_USER;

####流程定义表
##===15===部署单元信息
select * from ACT_RE_DEPLOYMENT;
##===16===模型信息
select * from ACT_RE_MODEL;
##===17===已部署的流程定义
select * from ACT_RE_PROCDEF;

####运行实例表
##===18===运行时事件
select * from ACT_RU_EVENT_SUBSCR;
##===19===运行时流程执行实例
select * from ACT_RU_EXECUTION;
##===20===运行时用户关系信息
select * from ACT_RU_IDENTITYLINK;
##===21===运行时作业
select * from ACT_RU_JOB;
##===22===运行时任务
select * from ACT_RU_TASK;
##===23===运行时变量表
select * from ACT_RU_VARIABLE