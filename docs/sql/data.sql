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
INSERT INTO `bank`.`dictionary` (`id`, `cat`, `name`, `key`, `value`, `is_default`, `status`, `is_modify`) VALUES ('1', 'product_type', '产品类型', '鑫丰母基金(开放式契约型)', '1', '0', '0', '0');
INSERT INTO `bank`.`dictionary` (`id`, `cat`, `name`, `key`, `value`, `is_default`, `status`, `is_modify`) VALUES ('2', 'product_type', '产品类型', '封闭式有限合伙私募基金', '2', '0', '0', '0');
INSERT INTO `bank`.`dictionary` (`id`, `cat`, `name`, `key`, `value`, `is_default`, `status`, `is_modify`) VALUES ('3', 'product_type', '产品类型', '封闭式契约型私募基金', '3', '0', '0', '0');
INSERT INTO `bank`.`dictionary` (`id`, `cat`, `name`, `key`, `value`, `is_default`, `status`, `is_modify`) VALUES ('4', 'report_category', '报告类型', '每周点评', '1', '0', '0', '0');
INSERT INTO `bank`.`dictionary` (`id`, `cat`, `name`, `key`, `value`, `is_default`, `status`, `is_modify`) VALUES ('5', 'report_category', '报告类型', '复华财经新视点', '2', '0', '0', '0');
INSERT INTO `bank`.`dictionary` (`id`, `cat`, `name`, `key`, `value`, `is_default`, `status`, `is_modify`) VALUES ('6', 'report_category', '报告类型', '复华资产研究报告', '3', '0', '0', '0');
INSERT INTO `bank`.`dictionary` (`id`, `cat`, `name`, `key`, `value`, `is_default`, `status`, `is_modify`) VALUES ('7', 'rights_category', '权益类型', '健康管理', '1', '0', '0', '0');
INSERT INTO `bank`.`dictionary` (`id`, `cat`, `name`, `key`, `value`, `is_default`, `status`, `is_modify`) VALUES ('8', 'rights_category', '权益类型', '旅行家', '2', '0', '0', '0');
INSERT INTO `bank`.`dictionary` (`id`, `cat`, `name`, `key`, `value`, `is_default`, `status`, `is_modify`) VALUES ('9', 'rights_category', '权益类型', '艺术家', '3', '0', '0', '0');
INSERT INTO `bank`.`dictionary` (`id`, `cat`, `name`, `key`, `value`, `is_default`, `status`, `is_modify`) VALUES ('10', 'rights_category', '权益类型', '商旅通', '4', '0', '0', '0');
INSERT INTO `bank`.`dictionary` (`id`, `cat`, `name`, `key`, `value`, `is_default`, `status`, `is_modify`) VALUES ('11', 'rights_category', '权益类型', '奢生活', '5', '0', '0', '0');
INSERT INTO `bank`.`dictionary` (`id`, `cat`, `name`, `key`, `value`, `is_default`, `status`, `is_modify`) VALUES ('12', 'rights_category', '权益类型', '爱体育', '6', '0', '0', '0');
INSERT INTO `bank`.`dictionary` (`id`, `cat`, `name`, `key`, `value`, `is_default`, `status`, `is_modify`) VALUES ('13', 'rights_category', '权益类型', '商学院', '7', '0', '0', '0');
INSERT INTO `bank`.`dictionary` (`id`, `cat`, `name`, `key`, `value`, `is_default`, `status`, `is_modify`) VALUES ('14', 'rights_category', '权益类型', '俱乐部', '8', '0', '0', '0');
INSERT INTO `bank`.`dictionary` (`id`, `cat`, `name`, `key`, `value`, `is_default`, `status`, `is_modify`) VALUES ('15', 'activity_category', '活动类型', '生命管理俱乐部', '1', '0', '0', '0');
INSERT INTO `bank`.`dictionary` (`id`, `cat`, `name`, `key`, `value`, `is_default`, `status`, `is_modify`) VALUES ('16', 'activity_category', '活动类型', '高尔夫俱乐部', '2', '0', '0', '0');
INSERT INTO `bank`.`dictionary` (`id`, `cat`, `name`, `key`, `value`, `is_default`, `status`, `is_modify`) VALUES ('17', 'activity_category', '活动类型', '教育传承俱乐部', '3', '0', '0', '0');
INSERT INTO `bank`.`dictionary` (`id`, `cat`, `name`, `key`, `value`, `is_default`, `status`, `is_modify`) VALUES ('18', 'activity_category', '活动类型', '商旅俱乐部', '4', '0', '0', '0');
INSERT INTO `bank`.`dictionary` (`id`, `cat`, `name`, `key`, `value`, `is_default`, `status`, `is_modify`) VALUES ('19', 'activity_category', '活动类型', '投资者俱乐部', '5', '0', '0', '0');

