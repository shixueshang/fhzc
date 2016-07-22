##初始化数据

TRUNCATE TABLE `admin`;
TRUNCATE TABLE `admin_role`;
TRUNCATE TABLE `system_module`;
TRUNCATE TABLE `system_admin_module`;
/*
-- Query: SELECT * FROM bank.admin_role
-- Date: 2016-07-08 18:08
*/
INSERT INTO `admin_role` (`role_id`,`role_name`) VALUES (2,'分总');
INSERT INTO `admin_role` (`role_id`,`role_name`) VALUES (1,'区总');

/*
-- Query: SELECT * FROM bank.admin
-- Date: 2016-07-08 16:31
*/
INSERT INTO `admin` (`id`,`login`,`password`,`realname`,`role`,`login_ip`,`last_login_time`) VALUES (1,'admin','098f6bcd4621d373cade4e832627b4f6','管理员',1,NULL,NULL);
INSERT INTO `admin` (`id`,`login`,`password`,`realname`,`role`,`login_ip`,`last_login_time`) VALUES (2,'test','098f6bcd4621d373cade4e832627b4f6','测试',1,NULL,NULL);


/*
-- Query: SELECT * FROM bank.system_module
-- Date: 2016-07-08 16:28
*/
INSERT INTO `system_module` (`id`,`name`,`parent_module_id`,`is_valid`,`url`) VALUES (1,'顶级节点',0,1,'/');
INSERT INTO `system_module` (`id`,`name`,`parent_module_id`,`is_valid`,`url`) VALUES (2,'业务管理',1,1,'/business/manage');
INSERT INTO `system_module` (`id`,`name`,`parent_module_id`,`is_valid`,`url`) VALUES (3,'业绩管理',1,1,'/performance/manage');
INSERT INTO `system_module` (`id`,`name`,`parent_module_id`,`is_valid`,`url`) VALUES (4,'订单管理',1,1,'/order/manage');
INSERT INTO `system_module` (`id`,`name`,`parent_module_id`,`is_valid`,`url`) VALUES (5,'理财师管理',1,1,'/financial/manage');
INSERT INTO `system_module` (`id`,`name`,`parent_module_id`,`is_valid`,`url`) VALUES (6,'组织架构管理',1,1,'/organization/manage');
INSERT INTO `system_module` (`id`,`name`,`parent_module_id`,`is_valid`,`url`) VALUES (7,'客户管理',1,1,'/user/manage');
INSERT INTO `system_module` (`id`,`name`,`parent_module_id`,`is_valid`,`url`) VALUES (8,'产品管理',2,1,'/business/product');
INSERT INTO `system_module` (`id`,`name`,`parent_module_id`,`is_valid`,`url`) VALUES (9,'产品列表',8,1,'/business/product/list');

/*
-- Query: select * FROM bank.system_admin_module
-- Date: 2016-07-08 16:32
*/
INSERT INTO `system_admin_module` (`id`,`admin_id`,`module_id`,`mode`) VALUES (1,1,1,'r');
INSERT INTO `system_admin_module` (`id`,`admin_id`,`module_id`,`mode`) VALUES (2,1,2,'r');
INSERT INTO `system_admin_module` (`id`,`admin_id`,`module_id`,`mode`) VALUES (3,1,3,'r');
INSERT INTO `system_admin_module` (`id`,`admin_id`,`module_id`,`mode`) VALUES (4,1,4,'r');
INSERT INTO `system_admin_module` (`id`,`admin_id`,`module_id`,`mode`) VALUES (5,1,5,'r');
INSERT INTO `system_admin_module` (`id`,`admin_id`,`module_id`,`mode`) VALUES (6,1,6,'r');
INSERT INTO `system_admin_module` (`id`,`admin_id`,`module_id`,`mode`) VALUES (7,1,7,'r');
INSERT INTO `system_admin_module` (`id`,`admin_id`,`module_id`,`mode`) VALUES (8,2,2,'r');
INSERT INTO `system_admin_module` (`id`,`admin_id`,`module_id`,`mode`) VALUES (9,2,3,'r');


##初始化字典数据
/*
-- Query: SELECT * FROM bank.dictionary
-- Date: 2016-07-22 15:12
*/
TRUNCATE TABLE `dictionary`;
INSERT INTO `dictionary` (`id`,`cat`,`name`,`key`,`value`,`is_default`,`status`,`is_modify`,`date_created`,`last_updated`) VALUES (1,'product_type','产品类型','鑫丰母基金(开放式契约型)','1',0,0,0,NULL,NULL);
INSERT INTO `dictionary` (`id`,`cat`,`name`,`key`,`value`,`is_default`,`status`,`is_modify`,`date_created`,`last_updated`) VALUES (2,'product_type','产品类型','封闭式有限合伙私募基金','2',0,0,0,NULL,NULL);
INSERT INTO `dictionary` (`id`,`cat`,`name`,`key`,`value`,`is_default`,`status`,`is_modify`,`date_created`,`last_updated`) VALUES (3,'product_type','产品类型','封闭式契约型私募基金','3',0,0,0,NULL,NULL);
INSERT INTO `dictionary` (`id`,`cat`,`name`,`key`,`value`,`is_default`,`status`,`is_modify`,`date_created`,`last_updated`) VALUES (4,'report_category','报告类型','每周点评','1',0,0,0,NULL,NULL);
INSERT INTO `dictionary` (`id`,`cat`,`name`,`key`,`value`,`is_default`,`status`,`is_modify`,`date_created`,`last_updated`) VALUES (5,'report_category','报告类型','复华财经新视点','2',0,0,0,NULL,NULL);
INSERT INTO `dictionary` (`id`,`cat`,`name`,`key`,`value`,`is_default`,`status`,`is_modify`,`date_created`,`last_updated`) VALUES (6,'report_category','报告类型','复华资产研究报告','3',0,0,0,NULL,NULL);
INSERT INTO `dictionary` (`id`,`cat`,`name`,`key`,`value`,`is_default`,`status`,`is_modify`,`date_created`,`last_updated`) VALUES (7,'rights_category','权益类型','健康管理','1',0,0,0,NULL,NULL);
INSERT INTO `dictionary` (`id`,`cat`,`name`,`key`,`value`,`is_default`,`status`,`is_modify`,`date_created`,`last_updated`) VALUES (8,'rights_category','权益类型','旅行家','2',0,0,0,NULL,NULL);
INSERT INTO `dictionary` (`id`,`cat`,`name`,`key`,`value`,`is_default`,`status`,`is_modify`,`date_created`,`last_updated`) VALUES (9,'rights_category','权益类型','艺术家','3',0,0,0,NULL,NULL);
INSERT INTO `dictionary` (`id`,`cat`,`name`,`key`,`value`,`is_default`,`status`,`is_modify`,`date_created`,`last_updated`) VALUES (10,'rights_category','权益类型','商旅通','4',0,0,0,NULL,NULL);
INSERT INTO `dictionary` (`id`,`cat`,`name`,`key`,`value`,`is_default`,`status`,`is_modify`,`date_created`,`last_updated`) VALUES (11,'rights_category','权益类型','奢生活','5',0,0,0,NULL,NULL);
INSERT INTO `dictionary` (`id`,`cat`,`name`,`key`,`value`,`is_default`,`status`,`is_modify`,`date_created`,`last_updated`) VALUES (12,'rights_category','权益类型','爱体育','6',0,0,0,NULL,NULL);
INSERT INTO `dictionary` (`id`,`cat`,`name`,`key`,`value`,`is_default`,`status`,`is_modify`,`date_created`,`last_updated`) VALUES (13,'rights_category','权益类型','商学院','7',0,0,0,NULL,NULL);
INSERT INTO `dictionary` (`id`,`cat`,`name`,`key`,`value`,`is_default`,`status`,`is_modify`,`date_created`,`last_updated`) VALUES (14,'rights_category','权益类型','俱乐部','8',0,0,0,NULL,NULL);
INSERT INTO `dictionary` (`id`,`cat`,`name`,`key`,`value`,`is_default`,`status`,`is_modify`,`date_created`,`last_updated`) VALUES (15,'activity_category','活动类型','生命管理俱乐部','1',0,0,0,NULL,NULL);
INSERT INTO `dictionary` (`id`,`cat`,`name`,`key`,`value`,`is_default`,`status`,`is_modify`,`date_created`,`last_updated`) VALUES (16,'activity_category','活动类型','高尔夫俱乐部','2',0,0,0,NULL,NULL);
INSERT INTO `dictionary` (`id`,`cat`,`name`,`key`,`value`,`is_default`,`status`,`is_modify`,`date_created`,`last_updated`) VALUES (17,'activity_category','活动类型','教育传承俱乐部','3',0,0,0,NULL,NULL);
INSERT INTO `dictionary` (`id`,`cat`,`name`,`key`,`value`,`is_default`,`status`,`is_modify`,`date_created`,`last_updated`) VALUES (18,'activity_category','活动类型','商旅俱乐部','4',0,0,0,NULL,NULL);
INSERT INTO `dictionary` (`id`,`cat`,`name`,`key`,`value`,`is_default`,`status`,`is_modify`,`date_created`,`last_updated`) VALUES (19,'activity_category','活动类型','投资者俱乐部','5',0,0,0,NULL,NULL);
INSERT INTO `dictionary` (`id`,`cat`,`name`,`key`,`value`,`is_default`,`status`,`is_modify`,`date_created`,`last_updated`) VALUES (20,'product_status','产品状态','未知','0',0,0,0,NULL,NULL);
INSERT INTO `dictionary` (`id`,`cat`,`name`,`key`,`value`,`is_default`,`status`,`is_modify`,`date_created`,`last_updated`) VALUES (21,'product_status','产品状态','产品预热','1',0,0,0,NULL,NULL);
INSERT INTO `dictionary` (`id`,`cat`,`name`,`key`,`value`,`is_default`,`status`,`is_modify`,`date_created`,`last_updated`) VALUES (22,'product_status','产品状态','募集中','2',0,0,0,NULL,NULL);
INSERT INTO `dictionary` (`id`,`cat`,`name`,`key`,`value`,`is_default`,`status`,`is_modify`,`date_created`,`last_updated`) VALUES (23,'product_status','产品状态','募集结束','3',0,0,0,NULL,NULL);
INSERT INTO `dictionary` (`id`,`cat`,`name`,`key`,`value`,`is_default`,`status`,`is_modify`,`date_created`,`last_updated`) VALUES (24,'product_status','产品状态','募集失败','4',0,0,0,NULL,NULL);
INSERT INTO `dictionary` (`id`,`cat`,`name`,`key`,`value`,`is_default`,`status`,`is_modify`,`date_created`,`last_updated`) VALUES (25,'product_status','产品状态','产品成立','5',0,0,0,NULL,NULL);
INSERT INTO `dictionary` (`id`,`cat`,`name`,`key`,`value`,`is_default`,`status`,`is_modify`,`date_created`,`last_updated`) VALUES (26,'product_status','产品状态','产品到期','6',0,0,0,NULL,NULL);
INSERT INTO `dictionary` (`id`,`cat`,`name`,`key`,`value`,`is_default`,`status`,`is_modify`,`date_created`,`last_updated`) VALUES (27,'product_status','产品状态','提前结束','7',0,0,0,NULL,NULL);
INSERT INTO `dictionary` (`id`,`cat`,`name`,`key`,`value`,`is_default`,`status`,`is_modify`,`date_created`,`last_updated`) VALUES (28, 'yes_no', '是否', '是', '1', '0', '0', '0',NULL,NULL);
INSERT INTO `dictionary` (`id`,`cat`,`name`,`key`,`value`,`is_default`,`status`,`is_modify`,`date_created`,`last_updated`) VALUES (29, 'yes_no', '是否', '否', '0', '0', '0', '0',NULL,NULL);
INSERT INTO `bank`.`dictionary` (`id`, `cat`, `name`, `key`, `value`, `is_default`, `status`, `is_modify`) VALUES ('30', 'product_issue_type', '产品发行模式', '其他', '0', '0', '0', '0');
INSERT INTO `bank`.`dictionary` (`id`, `cat`, `name`, `key`, `value`, `is_default`, `status`, `is_modify`) VALUES ('31', 'product_issue_type', '产品发行模式', '契约型基金', '1', '0', '0', '0');
INSERT INTO `bank`.`dictionary` (`id`, `cat`, `name`, `key`, `value`, `is_default`, `status`, `is_modify`) VALUES ('32', 'product_issue_type', '产品发行模式', '有限合伙', '2', '0', '0', '0');
INSERT INTO `bank`.`dictionary` (`id`, `cat`, `name`, `key`, `value`, `is_default`, `status`, `is_modify`) VALUES ('33', 'product_issue_type', '产品发行模式', '信托', '3', '0', '0', '0');
INSERT INTO `bank`.`dictionary` (`id`, `cat`, `name`, `key`, `value`, `is_default`, `status`, `is_modify`) VALUES ('34', 'product_issue_type', '产品发行模式', '债权', '4', '0', '0', '0');
INSERT INTO `bank`.`dictionary` (`id`, `cat`, `name`, `key`, `value`, `is_default`, `status`, `is_modify`) VALUES ('35', 'product_issue_type', '产品发行模式', '保险', '5', '0', '0', '0');
INSERT INTO `bank`.`dictionary` (`id`, `cat`, `name`, `key`, `value`, `is_default`, `status`, `is_modify`) VALUES ('36', 'customer_level', '客户等级', '准客户', '1', '0', '0', '0');
INSERT INTO `bank`.`dictionary` (`id`, `cat`, `name`, `key`, `value`, `is_default`, `status`, `is_modify`) VALUES ('37', 'customer_level', '客户等级', '客户', '2', '0', '0', '0');
INSERT INTO `bank`.`dictionary` (`id`, `cat`, `name`, `key`, `value`, `is_default`, `status`, `is_modify`) VALUES ('38', 'customer_level', '客户等级', '金卡客户', '3', '0', '0', '0');
INSERT INTO `bank`.`dictionary` (`id`, `cat`, `name`, `key`, `value`, `is_default`, `status`, `is_modify`) VALUES ('39', 'risk_level', '风险评级', 'A', '1', '0', '0', '0');
INSERT INTO `bank`.`dictionary` (`id`, `cat`, `name`, `key`, `value`, `is_default`, `status`, `is_modify`) VALUES ('40', 'risk_level', '风险评级', 'B', '2', '0', '0', '0');
INSERT INTO `bank`.`dictionary` (`id`, `cat`, `name`, `key`, `value`, `is_default`, `status`, `is_modify`) VALUES ('41', 'risk_level', '风险评级', 'C', '3', '0', '0', '0');

INSERT INTO `bank`.`areas` (`area_name`) VALUES ('北京');
INSERT INTO `bank`.`areas` (`area_name`) VALUES ('上海');
INSERT INTO `bank`.`areas` (`area_name`) VALUES ('广州');
INSERT INTO `bank`.`areas` (`area_name`) VALUES ('深圳');
INSERT INTO `bank`.`areas` (`area_name`) VALUES ('天津');
INSERT INTO `bank`.`areas` (`area_name`) VALUES ('青岛');
INSERT INTO `bank`.`areas` (`area_name`) VALUES ('杭州');
INSERT INTO `bank`.`areas` (`area_name`) VALUES ('苏州');
INSERT INTO `bank`.`areas` (`area_name`) VALUES ('成都');
INSERT INTO `bank`.`areas` (`area_name`) VALUES ('重庆');

INSERT INTO `bank`.`user_role` (`role_name`) VALUES ('总经理');
INSERT INTO `bank`.`user_role` (`role_name`) VALUES ('分公司总经理');
INSERT INTO `bank`.`user_role` (`role_name`) VALUES ('分公司副总经理');
INSERT INTO `bank`.`user_role` (`role_name`) VALUES ('副总经理');
INSERT INTO `bank`.`user_role` (`role_name`) VALUES ('高级理财顾问');
INSERT INTO `bank`.`user_role` (`role_name`) VALUES ('高级理财规划师');
INSERT INTO `bank`.`user_role` (`role_name`) VALUES ('华东区域总经理');
INSERT INTO `bank`.`user_role` (`role_name`) VALUES ('华南区总经理');
INSERT INTO `bank`.`user_role` (`role_name`) VALUES ('精英理财规划师');
INSERT INTO `bank`.`user_role` (`role_name`) VALUES ('精英理财规划师(1星)');
INSERT INTO `bank`.`user_role` (`role_name`) VALUES ('精英理财规划师(2星)');
INSERT INTO `bank`.`user_role` (`role_name`) VALUES ('精英理财规划师(3星)');
INSERT INTO `bank`.`user_role` (`role_name`) VALUES ('精英理财规划师(4星)');
INSERT INTO `bank`.`user_role` (`role_name`) VALUES ('精英理财规划师(5星)');
INSERT INTO `bank`.`user_role` (`role_name`) VALUES ('理财顾问');
INSERT INTO `bank`.`user_role` (`role_name`) VALUES ('理财规划师');
INSERT INTO `bank`.`user_role` (`role_name`) VALUES ('理财规划师(3星)');
INSERT INTO `bank`.`user_role` (`role_name`) VALUES ('理财规划师(5星)');
INSERT INTO `bank`.`user_role` (`role_name`) VALUES ('市场总监(1级)');
INSERT INTO `bank`.`user_role` (`role_name`) VALUES ('市场总监(1星)');
INSERT INTO `bank`.`user_role` (`role_name`) VALUES ('市场总监(2级)');
INSERT INTO `bank`.`user_role` (`role_name`) VALUES ('市场总监(2星)');
INSERT INTO `bank`.`user_role` (`role_name`) VALUES ('市场总监(3级)');
INSERT INTO `bank`.`user_role` (`role_name`) VALUES ('市场总监(4级)');
INSERT INTO `bank`.`user_role` (`role_name`) VALUES ('市场总监(5级)');
INSERT INTO `bank`.`user_role` (`role_name`) VALUES ('市场总监(5星)');
INSERT INTO `bank`.`user_role` (`role_name`) VALUES ('市场总监(7级)');
INSERT INTO `bank`.`user_role` (`role_name`) VALUES ('市场总监1级');
INSERT INTO `bank`.`user_role` (`role_name`) VALUES ('市场总监2级');
INSERT INTO `bank`.`user_role` (`role_name`) VALUES ('市场总监3级');
INSERT INTO `bank`.`user_role` (`role_name`) VALUES ('西南区区域总经理');
INSERT INTO `bank`.`user_role` (`role_name`) VALUES ('资深理财顾问');
INSERT INTO `bank`.`user_role` (`role_name`) VALUES ('资深理财规划师');
INSERT INTO `bank`.`user_role` (`role_name`) VALUES ('青渝总经理');


