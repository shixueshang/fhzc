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
INSERT INTO `bank`.`dictionary` (`id`, `cat`, `name`, `key`, `value`, `is_default`, `status`, `is_modify`) VALUES ('42', 'passport', '证件类型', '身份证', 'A', '0', '0', '0');
INSERT INTO `bank`.`dictionary` (`id`, `cat`, `name`, `key`, `value`, `is_default`, `status`, `is_modify`) VALUES ('43', 'passport', '证件类型', '台胞证', 'B', '0', '0', '0');
INSERT INTO `bank`.`dictionary` (`id`, `cat`, `name`, `key`, `value`, `is_default`, `status`, `is_modify`) VALUES ('44', 'passport', '证件类型', '回乡证', 'C', '0', '0', '0');


TRUNCATE TABLE `areas`;
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

TRUNCATE TABLE `user_role`;
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

INSERT INTO `rights` VALUES (1,7,'基础体检服务',100,NULL,1,'2016-07-19 20:49:01','某知名供应商','该要暂缺','封面暂无',NULL),(2,7,'高端体检服务\r',100,NULL,1,'2016-07-19 20:49:01','某知名供应商','该要暂缺','封面暂无',NULL),(3,7,'基础洁牙服务',100,'static',1,'2016-07-19 20:49:01','某知名供应商','该要暂缺','封面暂无',NULL),(4,7,'口腔诊疗服务\r',100,'static',1,'2016-07-19 20:49:01','某知名供应商','该要暂缺','封面暂无',NULL),(5,7,'全国三甲医院全程导医陪诊服务\r',100,'static',1,'2016-07-19 20:49:01','某知名供应商','该要暂缺','封面暂无',NULL),(6,7,'全国三甲医院预约专家号服务',100,'static',1,'2016-07-19 20:49:01','某知名供应商','该要暂缺','封面暂无',NULL),(7,7,'全国三甲医院专家预约挂号及导诊服务',100,'static',1,'2016-07-19 20:49:01','某知名供应商','该要暂缺','封面暂无',NULL),(8,7,'全国三甲医院手术、住院协调预约服务\r',100,'static',1,'2016-07-19 20:49:01','某知名供应商','该要暂缺','封面暂无',NULL),(9,7,'全国三甲医院重要检查预约服务',100,'static',1,'2016-07-19 20:49:01','某知名供应商','该要暂缺','封面暂无',NULL),(10,7,'全球专业海外医疗就诊推荐方案',100,'static',1,'2016-07-19 20:49:01','某知名供应商','该要暂缺','封面暂无',NULL),(11,7,'海外早期防癌筛查体检服务',100,'static',1,'2016-07-19 20:49:01','某知名供应商','该要暂缺','封面暂无',NULL),(19,8,'国内一二线城市接送机服务',100,'static',1,'2016-07-19 20:49:01','某知名供应商','该要暂缺','封面暂无',NULL),(20,8,'境外20个城市接送机预约',100,'static',1,'2016-07-19 20:49:01','某知名供应商','该要暂缺','封面暂无',NULL),(21,8,'全国24家高铁站贵宾休息室预约服务',100,'static',1,'2016-07-19 20:49:01','某知名供应商','该要暂缺','封面暂无',NULL),(22,8,'国内机场CIP专用嘉宾通道预约服务',100,'static',1,'2016-07-19 20:49:01','某知名供应商','该要暂缺','封面暂无',NULL),(23,8,'全球427家机场VIP贵宾要客通道预约服务',100,'static',1,'2016-07-19 20:49:01','某知名供应商','该要暂缺','封面暂无',NULL),(24,8,'各国签证咨询、代办服务',100,'static',1,'2016-07-19 20:49:01','某知名供应商','该要暂缺','封面暂无',NULL),(25,8,'三年国际驾照代办服务',100,'static',1,'2016-07-19 20:49:01','某知名供应商','该要暂缺','封面暂无',NULL),(26,9,'演唱会、剧院演出门票预订服务\r',100,'static',1,'2016-07-19 20:49:01','某知名供应商','该要暂缺','封面暂无',NULL),(27,9,'拍卖会门票预订及贵宾入场资格获取服务',100,'static',1,'2016-07-19 20:49:01','某知名供应商','该要暂缺','封面暂无',NULL),(28,10,'国内城市道路救援服务',100,'static',1,'2016-07-19 20:49:01','某知名供应商','该要暂缺','封面暂无',NULL),(29,10,'私人飞行驾照培训\r',100,'static',1,'2016-07-19 20:49:01','某知名供应商','该要暂缺','封面暂无',NULL),(30,10,'全国专人代驾服务',100,'static',1,'2016-07-19 20:49:01','某知名供应商','该要暂缺','封面暂无',NULL),(31,10,'全国洗车服务',100,'static',1,'2016-07-19 20:49:01','某知名供应商','该要暂缺','封面暂无',NULL),(32,11,'知名雪茄、红酒优惠购买预订服务',100,'static',1,'2016-07-19 20:49:01','某知名供应商','该要暂缺','封面暂无',NULL),(33,11,'复华定制限量版红酒兑换服务',100,'static',1,'2016-07-19 20:49:01','某知名供应商','该要暂缺','封面暂无',NULL),(34,11,'国内高档餐厅预约订位服务',100,'static',1,'2016-07-19 20:49:01','某知名供应商','该要暂缺','封面暂无',NULL),(35,11,'国际一线奢侈品牌产品预订及优惠折扣服务05、高端西服、礼服量体定制预约服务',100,'static',1,'2016-07-19 20:49:01','某知名供应商','该要暂缺','封面暂无',NULL),(36,11,'奢侈品清洁护理服务',100,'static',1,'2016-07-19 20:49:01','某知名供应商','该要暂缺','封面暂无',NULL),(37,11,'专属美容、SPA护理预约服务',100,'static',1,'2016-07-19 20:49:01','某知名供应商','该要暂缺','封面暂无',NULL),(38,11,'豪车优惠购买、试乘试驾服务',100,'static',1,'2016-07-19 20:49:01','某知名供应商','该要暂缺','封面暂无',NULL),(39,11,'全国优选影院观影服务',100,'static',1,'2016-07-19 20:49:01','某知名供应商','该要暂缺','封面暂无',NULL),(40,12,'全国精选高尔夫练习场畅打服务',100,'static',1,'2016-07-19 20:49:01','某知名供应商','该要暂缺','封面暂无',NULL),(41,12,'全国高尔夫球场优惠订场服务',100,'static',1,'2016-07-19 20:49:01','某知名供应商','该要暂缺','封面暂无',NULL),(42,12,'高尔夫初、中、高级专业培训，预约服务',100,'static',1,'2016-07-19 20:49:01','某知名供应商','该要暂缺','封面暂无',NULL),(43,12,'高尔夫专业球具及配件优惠预订服务',100,'static',1,'2016-07-19 20:49:01','某知名供应商','该要暂缺','封面暂无',NULL),(44,12,'高尔夫职业赛事观赛预约服务',100,'static',1,'2016-07-19 20:49:01','某知名供应商','该要暂缺','封面暂无',NULL),(45,12,'全国健身场馆预订服务',100,'static',1,'2016-07-19 20:49:01','某知名供应商','该要暂缺','封面暂无',NULL),(46,12,'全国网球场馆预订服务',100,'static',1,'2016-07-19 20:49:01','某知名供应商','该要暂缺','封面暂无',NULL),(47,13,'律师法律咨询服务',100,'static',1,'2016-07-19 20:49:01','某知名供应商','该要暂缺','封面暂无',NULL),(48,13,'税务问题咨询服务',100,'static',1,'2016-07-19 20:49:01','某知名供应商','该要暂缺','封面暂无',NULL),(49,13,'投资移民咨询服务',100,'static',1,'2016-07-19 20:49:01','某知名供应商','该要暂缺','封面暂无',NULL),(50,13,'子女留学及教育咨询服务',100,'static',1,'2016-07-19 20:49:01','某知名供应商','该要暂缺','封面暂无',NULL),(51,13,'专属语言培训优惠预订服务',100,'static',1,'2016-07-19 20:49:01','某知名供应商','该要暂缺','封面暂无',NULL),(52,14,'健康养生专题讲座',100,'static',1,'2016-07-19 20:49:01','某知名供应商','该要暂缺','封面暂无',NULL),(53,14,'红酒品鉴会',100,'static',1,'2016-07-19 20:49:01','某知名供应商','该要暂缺','封面暂无',NULL),(54,14,'全球精品旅行线路特惠',100,'static',1,'2016-07-19 20:49:01','某知名供应商','该要暂缺','封面暂无',NULL),(55,14,'亲子主题活动',100,'static',1,'2016-07-19 20:49:01','某知名供应商','该要暂缺','封面暂无',NULL),(56,14,'、家庭资产配置专题研讨会',100,'static',1,'2016-07-19 20:49:01','某知名供应商','该要暂缺','封面暂无',NULL);

