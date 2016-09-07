##初始化数据

TRUNCATE TABLE `admin`;
TRUNCATE TABLE `admin_role`;
TRUNCATE TABLE `system_module`;

/*
-- Query: SELECT * FROM bank.admin
-- Date: 2016-07-08 16:31
*/
INSERT INTO `admin` (`id`,`login`,`password`,`realname`,`role`,`login_ip`,`organ`) VALUES (1,'admin','098f6bcd4621d373cade4e832627b4f6','管理员',1,NULL,'1');

/*
-- Query: SELECT * FROM bank.system_module
-- Date: 2016-08-05 14:27
*/
INSERT INTO `system_module` (`id`,`name`,`url`,`desc`,`parent_module_id`,`level`,`is_valid`) VALUES (1,'顶级节点','/',NULL,NULL,1,1);
INSERT INTO `system_module` (`id`,`name`,`url`,`desc`,`parent_module_id`,`level`,`is_valid`) VALUES (2,'产品管理','/business/product',NULL,1,2,1);
INSERT INTO `system_module` (`id`,`name`,`url`,`desc`,`parent_module_id`,`level`,`is_valid`) VALUES (3,'产品列表','/business/product/list',NULL,2,3,1);
INSERT INTO `system_module` (`id`,`name`,`url`,`desc`,`parent_module_id`,`level`,`is_valid`) VALUES (4,'产品新增','/business/product/pub',NULL,2,3,1);
INSERT INTO `system_module` (`id`,`name`,`url`,`desc`,`parent_module_id`,`level`,`is_valid`) VALUES (5,'投研报告管理','/business/report',NULL,1,2,1);
INSERT INTO `system_module` (`id`,`name`,`url`,`desc`,`parent_module_id`,`level`,`is_valid`) VALUES (6,'报告列表','/business/report/list',NULL,5,3,1);
INSERT INTO `system_module` (`id`,`name`,`url`,`desc`,`parent_module_id`,`level`,`is_valid`) VALUES (7,'新增报告','/business/report/pub',NULL,5,3,1);
INSERT INTO `system_module` (`id`,`name`,`url`,`desc`,`parent_module_id`,`level`,`is_valid`) VALUES (8,'活动管理','/business/activity',NULL,1,2,1);
INSERT INTO `system_module` (`id`,`name`,`url`,`desc`,`parent_module_id`,`level`,`is_valid`) VALUES (9,'活动列表','/business/activity/list',NULL,8,3,1);
INSERT INTO `system_module` (`id`,`name`,`url`,`desc`,`parent_module_id`,`level`,`is_valid`) VALUES (10,'活动新增','/business/activity/pub',NULL,8,3,1);
INSERT INTO `system_module` (`id`,`name`,`url`,`desc`,`parent_module_id`,`level`,`is_valid`) VALUES (11,'权益管理','/business/rights',NULL,1,2,1);
INSERT INTO `system_module` (`id`,`name`,`url`,`desc`,`parent_module_id`,`level`,`is_valid`) VALUES (12,'权益列表','/business/rights/list',NULL,11,3,1);
INSERT INTO `system_module` (`id`,`name`,`url`,`desc`,`parent_module_id`,`level`,`is_valid`) VALUES (13,'权益新增','/business/rights/pub',NULL,11,3,1);
INSERT INTO `system_module` (`id`,`name`,`url`,`desc`,`parent_module_id`,`level`,`is_valid`) VALUES (14,'机构管理','/organization/department',NULL,1,2,1);
INSERT INTO `system_module` (`id`,`name`,`url`,`desc`,`parent_module_id`,`level`,`is_valid`) VALUES (15,'机构配置','/organization/department/department',NULL,14,3,1);
INSERT INTO `system_module` (`id`,`name`,`url`,`desc`,`parent_module_id`,`level`,`is_valid`) VALUES (16,'系统管理','/system',NULL,1,2,1);
INSERT INTO `system_module` (`id`,`name`,`url`,`desc`,`parent_module_id`,`level`,`is_valid`) VALUES (19,'角色列表','/system/role/list',NULL,16,3,1);
INSERT INTO `system_module` (`id`,`name`,`url`,`desc`,`parent_module_id`,`level`,`is_valid`) VALUES (20,'角色新增','/system/role/pub',NULL,16,3,1);
INSERT INTO `system_module` (`id`,`name`,`url`,`desc`,`parent_module_id`,`level`,`is_valid`) VALUES (21,'管理员列表','/system/admin/list',NULL,16,3,1);
INSERT INTO `system_module` (`id`,`name`,`url`,`desc`,`parent_module_id`,`level`,`is_valid`) VALUES (22,'管理员新增','/system/admin/pub',NULL,16,3,1);
INSERT INTO `system_module` (`id`,`name`,`url`,`desc`,`parent_module_id`,`level`,`is_valid`) VALUES (23,'产品预约列表','/business/product/order/list',NULL,2,3,1);
INSERT INTO `system_module` (`id`,`name`,`url`,`desc`,`parent_module_id`,`level`,`is_valid`) VALUES (24,'活动报名','/business/activity/registers','',8,3,1);
INSERT INTO `system_module` (`id`,`name`,`url`,`desc`,`parent_module_id`,`level`,`is_valid`) VALUES (25,'理财师管理','/personal/planner',NULL,1,2,1);
INSERT INTO `system_module` (`id`,`name`,`url`,`desc`,`parent_module_id`,`level`,`is_valid`) VALUES (26,'理财师列表','/personal/planner/list',NULL,25,3,1);
INSERT INTO `system_module` (`id`,`name`,`url`,`desc`,`parent_module_id`,`level`,`is_valid`) VALUES (27,'客户管理','/personal/customer',NULL,1,2,1);
INSERT INTO `system_module` (`id`,`name`,`url`,`desc`,`parent_module_id`,`level`,`is_valid`) VALUES (28,'个人客户列表','/personal/customer/single/list',NULL,27,3,1);
INSERT INTO `system_module` (`id`,`name`,`url`,`desc`,`parent_module_id`,`level`,`is_valid`) VALUES (29,'添加权益预约','/business/rights/reservation/pub',NULL,11,3,1);
INSERT INTO `system_module` (`id`,`name`,`url`,`desc`,`parent_module_id`,`level`,`is_valid`) VALUES (30,'权益预约列表','/business/rights/reservations',NULL,11,3,1);
INSERT INTO `system_module` (`id`,`name`,`url`,`desc`,`parent_module_id`,`level`,`is_valid`) VALUES (31,'机构客户列表','/personal/customer/organ/list',NULL,27,3,1);
INSERT INTO `system_module` (`id`,`name`,`url`,`desc`,`parent_module_id`,`level`,`is_valid`) VALUES (32,'产品分类维护','/business/product/type',NULL,2,3,1);
INSERT INTO `system_module` (`id`,`name`,`url`,`desc`,`parent_module_id`,`level`,`is_valid`) VALUES (33,'导入管理','/import',NULL,1,2,1);
INSERT INTO `system_module` (`id`,`name`,`url`,`desc`,`parent_module_id`,`level`,`is_valid`) VALUES (34,'产品导入','/business/product/importor',NULL,33,3,1);
INSERT INTO `system_module` (`id`,`name`,`url`,`desc`,`parent_module_id`,`level`,`is_valid`) VALUES (35,'在职理财师导入','/personal/planner/importor',NULL,33,3,1);
INSERT INTO `system_module` (`id`,`name`,`url`,`desc`,`parent_module_id`,`level`,`is_valid`) VALUES (36,'理财师日业绩导入','/business/plannerachivementsdaily/importor',NULL,33,3,1);
INSERT INTO `system_module` (`id`,`name`,`url`,`desc`,`parent_module_id`,`level`,`is_valid`) VALUES (37,'理财师月业绩导入','/business/plannerachivementsmonthly/importor',NULL,33,3,1);
INSERT INTO `system_module` (`id`,`name`,`url`,`desc`,`parent_module_id`,`level`,`is_valid`) VALUES (38,'财务日表导入','/business/contract/importor',NULL,33,3,1);
INSERT INTO `system_module` (`id`,`name`,`url`,`desc`,`parent_module_id`,`level`,`is_valid`) VALUES (39,'兑付记录导入','/business/payment/importor',NULL,33,3,1);
INSERT INTO `system_module` (`id`,`name`,`url`,`desc`,`parent_module_id`,`level`,`is_valid`) VALUES (40,'鑫丰母兑付导入','/business/payment/importorspecial',NULL,33,3,1);
INSERT INTO `system_module` (`id`,`name`,`url`,`desc`,`parent_module_id`,`level`,`is_valid`) VALUES (41,'个人投资人档案表导入','/business/customerdocument/importorpersonal',NULL,33,3,1);
INSERT INTO `system_module` (`id`,`name`,`url`,`desc`,`parent_module_id`,`level`,`is_valid`) VALUES (42,'机构投资人档案表导入','/business/customerdocument/importoragent',NULL,33,3,1);
INSERT INTO `system_module` (`id`,`name`,`url`,`desc`,`parent_module_id`,`level`,`is_valid`) VALUES (43,'积分历史导入','/business/score/importoradd',NULL,33,3,1);
INSERT INTO `system_module` (`id`,`name`,`url`,`desc`,`parent_module_id`,`level`,`is_valid`) VALUES (44,'权益消费导入','/business/score/importorconsume',NULL,33,3,1);
INSERT INTO `system_module` (`id`,`name`,`url`,`desc`,`parent_module_id`,`level`,`is_valid`) VALUES (45,'资产管理','/business/assets',NULL,1,2,1);
INSERT INTO `system_module` (`id`,`name`,`url`,`desc`,`parent_module_id`,`level`,`is_valid`) VALUES (46,'订单列表','/business/assets/list',NULL,45,3,1);
INSERT INTO `system_module` (`id`,`name`,`url`,`desc`,`parent_module_id`,`level`,`is_valid`) VALUES (47,'离职理财师导入','/personal/planner/importoroff',NULL,33,3,1);
INSERT INTO `system_module` (`id`,`name`,`url`,`desc`,`parent_module_id`,`level`,`is_valid`) VALUES (48,'积分管理','/business/score',NULL,1,2,1);
INSERT INTO `system_module` (`id`,`name`,`url`,`desc`,`parent_module_id`,`level`,`is_valid`) VALUES (49,'积分审批','/business/score/listpending',NULL,48,3,1);
INSERT INTO `system_module` (`id`,`name`,`url`,`desc`,`parent_module_id`,`level`,`is_valid`) VALUES (50,'理财师业绩','/personal/planner/achivement',NULL,25,3,1);
INSERT INTO `system_module` (`id`,`name`,`url`,`desc`,`parent_module_id`,`level`,`is_valid`) VALUES (51,'banner列表','/system/banner/list',NULL,16,3,1);
INSERT INTO `system_module` (`id`,`name`,`url`,`desc`,`parent_module_id`,`level`,`is_valid`) VALUES (52,'banner新增','/system/banner/pub',NULL,16,3,1);
INSERT INTO `system_module` (`id`,`name`,`url`,`desc`,`parent_module_id`,`level`,`is_valid`) VALUES (53, '关注列表', '/personal/focus/list',NULL, 27, 3, 1);
INSERT INTO `system_module` (`id`,`name`,`url`,`desc`,`parent_module_id`,`level`,`is_valid`) VALUES (54, '关于App', '/system/about/app/pub',NULL,16, 3, 1);
INSERT INTO `system_module` (`id`,`name`,`url`,`desc`,`parent_module_id`,`level`,`is_valid`) VALUES (55, '联系我们', '/system/about/contact/pub',NULL, 16, 3, 1);
INSERT INTO `system_module` (`id`,`name`,`url`,`desc`,`parent_module_id`,`level`,`is_valid`) VALUES (56, '消息新增', '/system/notice/pub',NULL, 16, 3, 1);
INSERT INTO `system_module` (`id`,`name`,`url`,`desc`,`parent_module_id`,`level`,`is_valid`) VALUES (57, '消息列表', '/system/notice/list',NULL, 16, 3, 1);
INSERT INTO `system_module` (`id`,`name`,`url`,`desc`,`parent_module_id`,`level`,`is_valid`) VALUES (58, '财务日报列表','/business/contract/list', NULL, 45, 3, 1);
INSERT INTO `system_module` (`id`,`name`,`url`,`desc`,`parent_module_id`,`level`,`is_valid`) VALUES (59, '操作日志','/system/log/list',NULL,16,3,1);
INSERT INTO `system_module` (`id`,`name`,`url`,`desc`,`parent_module_id`,`level`,`is_valid`) VALUES (60, '缺位管理', '/personal/customer/missPlanner',NULL, 27, 3, 1);
INSERT INTO `system_module` (`id`,`name`,`url`,`desc`,`parent_module_id`,`level`,`is_valid`) VALUES ('61', '产品关注列表', '/business/product/focus/list', null, '2', '3', '1');
INSERT INTO `system_module` (`id`,`name`,`url`,`desc`,`parent_module_id`,`level`,`is_valid`) VALUES ('62', '活动关注列表', '/business/activity/focusList', null, '8', '3', '1');
INSERT INTO `system_module` (`id`,`name`,`url`,`desc`,`parent_module_id`,`level`,`is_valid`) VALUES ('63', '报告关注列表', '/business/report/focusList', null, '5', '3', '1');
INSERT INTO `system_module` (`id`,`name`,`url`,`desc`,`parent_module_id`,`level`,`is_valid`)VALUES ('64', '权益关注列表', '/business/rights/focusList', null, '11', '3', '1');
INSERT INTO `system_module` (`id`,`name`,`url`,`desc`,`parent_module_id`,`level`,`is_valid`) VALUES (65, '积分查询', '/business/score/query',NULL,48, 3, 1);
INSERT INTO `system_module` (`id`,`name`,`url`,`desc`,`parent_module_id`,`level`,`is_valid`) VALUES (66, '资产持仓', '/business/assets/holdings/find',NULL,45, 3, 1);
INSERT INTO `system_module` (`id`,`name`,`url`,`desc`,`parent_module_id`,`level`,`is_valid`) VALUES ('67', '权益分类维护', '/business/rights/type', null, '11', '3', '1');
INSERT INTO `system_module` (`id`,`name`,`url`,`desc`,`parent_module_id`,`level`,`is_valid`) VALUES ('68', '活动分类维护', '/business/activity/type', null, '8', '3', '1');
INSERT INTO `system_module` (`id`,`name`,`url`,`desc`,`parent_module_id`,`level`,`is_valid`) VALUES ('69', '报告分类维护', '/business/report/type', null, '5', '3', '1');
INSERT INTO `system_module` (`id`,`name`,`url`,`desc`,`parent_module_id`,`level`,`is_valid`) VALUES ('70', '推荐资产配置', '/business/assets/recommend', null, '45', '3', '1');
INSERT INTO `system_module` (`id`,`name`,`url`,`desc`,`parent_module_id`,`level`,`is_valid`) VALUES ('71', '意见列表', '/system/suggest/list', null, 16, 3, 1);
INSERT INTO `system_module` (`id`,`name`,`url`,`desc`,`parent_module_id`,`level`,`is_valid`) VALUES ('72', '积分列表', '/business/score/list', null, 48, 3, 1);


/*
-- Query: SELECT * FROM bank.dictionary
-- Date: 2016-08-01 10:57
*/
TRUNCATE TABLE `dictionary`;
INSERT INTO `dictionary` (`id`,`cat`,`name`,`key`,`value`,`is_default`,`status`,`is_modify`,`date_created`,`last_updated`) VALUES (1,'product_type','产品类型','另类投资','1',0,0,0,NULL,NULL);
INSERT INTO `dictionary` (`id`,`cat`,`name`,`key`,`value`,`is_default`,`status`,`is_modify`,`date_created`,`last_updated`) VALUES (2,'product_type','产品类型','旅游地产','2',0,0,0,NULL,NULL);
INSERT INTO `dictionary` (`id`,`cat`,`name`,`key`,`value`,`is_default`,`status`,`is_modify`,`date_created`,`last_updated`) VALUES (3,'product_type','产品类型','海外投资','3',0,0,0,NULL,NULL);
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
INSERT INTO `dictionary` (`id`,`cat`,`name`,`key`,`value`,`is_default`,`status`,`is_modify`,`date_created`,`last_updated`) VALUES (28,'yes_no','是否','是','1',0,0,0,NULL,NULL);
INSERT INTO `dictionary` (`id`,`cat`,`name`,`key`,`value`,`is_default`,`status`,`is_modify`,`date_created`,`last_updated`) VALUES (29,'yes_no','是否','否','0',0,0,0,NULL,NULL);
INSERT INTO `dictionary` (`id`,`cat`,`name`,`key`,`value`,`is_default`,`status`,`is_modify`,`date_created`,`last_updated`) VALUES (30,'product_issue_type','产品发行模式','其他','0',0,0,0,NULL,NULL);
INSERT INTO `dictionary` (`id`,`cat`,`name`,`key`,`value`,`is_default`,`status`,`is_modify`,`date_created`,`last_updated`) VALUES (31,'product_issue_type','产品发行模式','契约型基金','1',0,0,0,NULL,NULL);
INSERT INTO `dictionary` (`id`,`cat`,`name`,`key`,`value`,`is_default`,`status`,`is_modify`,`date_created`,`last_updated`) VALUES (32,'product_issue_type','产品发行模式','有限合伙','2',0,0,0,NULL,NULL);
INSERT INTO `dictionary` (`id`,`cat`,`name`,`key`,`value`,`is_default`,`status`,`is_modify`,`date_created`,`last_updated`) VALUES (33,'product_issue_type','产品发行模式','信托','3',0,0,0,NULL,NULL);
INSERT INTO `dictionary` (`id`,`cat`,`name`,`key`,`value`,`is_default`,`status`,`is_modify`,`date_created`,`last_updated`) VALUES (34,'product_issue_type','产品发行模式','债权','4',0,0,0,NULL,NULL);
INSERT INTO `dictionary` (`id`,`cat`,`name`,`key`,`value`,`is_default`,`status`,`is_modify`,`date_created`,`last_updated`) VALUES (35,'product_issue_type','产品发行模式','保险','5',0,0,0,NULL,NULL);
INSERT INTO `dictionary` (`id`,`cat`,`name`,`key`,`value`,`is_default`,`status`,`is_modify`,`date_created`,`last_updated`) VALUES (36,'customer_level','客户等级','投资人','1',0,0,0,NULL,NULL);
INSERT INTO `dictionary` (`id`,`cat`,`name`,`key`,`value`,`is_default`,`status`,`is_modify`,`date_created`,`last_updated`) VALUES (37,'customer_level','客户等级','准会员','2',0,0,0,NULL,NULL);
INSERT INTO `dictionary` (`id`,`cat`,`name`,`key`,`value`,`is_default`,`status`,`is_modify`,`date_created`,`last_updated`) VALUES (38,'customer_level','客户等级','银卡','3',0,0,0,NULL,NULL);
INSERT INTO `dictionary` (`id`,`cat`,`name`,`key`,`value`,`is_default`,`status`,`is_modify`,`date_created`,`last_updated`) VALUES (39,'customer_level','客户等级','金卡','4',0,0,0,NULL,NULL);
INSERT INTO `dictionary` (`id`,`cat`,`name`,`key`,`value`,`is_default`,`status`,`is_modify`,`date_created`,`last_updated`) VALUES (40,'customer_level','客户等级','黑金卡','5',0,0,0,NULL,NULL);
INSERT INTO `dictionary` (`id`,`cat`,`name`,`key`,`value`,`is_default`,`status`,`is_modify`,`date_created`,`last_updated`) VALUES (50,'risk_level','风险评级','A','1',0,0,0,NULL,NULL);
INSERT INTO `dictionary` (`id`,`cat`,`name`,`key`,`value`,`is_default`,`status`,`is_modify`,`date_created`,`last_updated`) VALUES (51,'risk_level','风险评级','B','2',0,0,0,NULL,NULL);
INSERT INTO `dictionary` (`id`,`cat`,`name`,`key`,`value`,`is_default`,`status`,`is_modify`,`date_created`,`last_updated`) VALUES (52,'risk_level','风险评级','C','3',0,0,0,NULL,NULL);
INSERT INTO `dictionary` (`id`,`cat`,`name`,`key`,`value`,`is_default`,`status`,`is_modify`,`date_created`,`last_updated`) VALUES (53,'passport','证件类型','身份证','1',0,0,0,NULL,NULL);
INSERT INTO `dictionary` (`id`,`cat`,`name`,`key`,`value`,`is_default`,`status`,`is_modify`,`date_created`,`last_updated`) VALUES (54,'passport','证件类型','护照','2',0,0,0,NULL,NULL);
INSERT INTO `dictionary` (`id`,`cat`,`name`,`key`,`value`,`is_default`,`status`,`is_modify`,`date_created`,`last_updated`) VALUES (55,'passport','证件类型','港澳通行证','3',0,0,0,NULL,NULL);
INSERT INTO `dictionary` (`id`,`cat`,`name`,`key`,`value`,`is_default`,`status`,`is_modify`,`date_created`,`last_updated`) VALUES (56,'passport','证件类型','台胞证','4',0,0,0,NULL,NULL);
INSERT INTO `dictionary` (`id`,`cat`,`name`,`key`,`value`,`is_default`,`status`,`is_modify`,`date_created`,`last_updated`) VALUES (57,'passport','证件类型','香港身份证','5',0,0,0,NULL,NULL);
INSERT INTO `dictionary` (`id`,`cat`,`name`,`key`,`value`,`is_default`,`status`,`is_modify`,`date_created`,`last_updated`) VALUES (58,'passport','证件类型','营业执照','6',0,0,0,NULL,NULL);
INSERT INTO `dictionary` (`id`,`cat`,`name`,`key`,`value`,`is_default`,`status`,`is_modify`,`date_created`,`last_updated`) VALUES (59,'passport','证件类型','基金会法人登记证书','7',0,0,0,NULL,NULL);
INSERT INTO `dictionary` (`id`,`cat`,`name`,`key`,`value`,`is_default`,`status`,`is_modify`,`date_created`,`last_updated`) VALUES (60,'assets_status','资产状态','派息','dividend',0,0,0,NULL,NULL);
INSERT INTO `dictionary` (`id`,`cat`,`name`,`key`,`value`,`is_default`,`status`,`is_modify`,`date_created`,`last_updated`) VALUES (61,'assets_status','资产状态','购买','purchase',0,0,0,NULL,NULL);
INSERT INTO `dictionary` (`id`,`cat`,`name`,`key`,`value`,`is_default`,`status`,`is_modify`,`date_created`,`last_updated`) VALUES (62,'assets_status','资产状态','去存','renew',0,0,0,NULL,NULL);
INSERT INTO `dictionary` (`id`,`cat`,`name`,`key`,`value`,`is_default`,`status`,`is_modify`,`date_created`,`last_updated`) VALUES (63,'assets_status','资产状态','赎回','redemption',0,0,0,NULL,NULL);
INSERT INTO `dictionary` (`id`,`cat`,`name`,`key`,`value`,`is_default`,`status`,`is_modify`,`date_created`,`last_updated`) VALUES (64,'product_type','产品类型','权益投资','4',0,0,0,'2016-08-05 17:43:58','2016-08-05 17:43:58');
INSERT INTO `dictionary` (`id`,`cat`,`name`,`key`,`value`,`is_default`,`status`,`is_modify`,`date_created`,`last_updated`) VALUES (65,'score_status','积分状态','增加','add',0,0,0,NULL,NULL);
INSERT INTO `dictionary` (`id`,`cat`,`name`,`key`,`value`,`is_default`,`status`,`is_modify`,`date_created`,`last_updated`) VALUES (66,'score_status','积分状态','消费','consume',0,0,0,NULL,NULL);
INSERT INTO `dictionary` (`id`,`cat`,`name`,`key`,`value`,`is_default`,`status`,`is_modify`,`date_created`,`last_updated`) VALUES (67,'score_status','积分状态','冻结','frozen',0,0,0,NULL,NULL);
INSERT INTO `dictionary` (`id`,`cat`,`name`,`key`,`value`,`is_default`,`status`,`is_modify`,`date_created`,`last_updated`) VALUES (68,'score_status','积分状态','过期','expire',0,0,0,NULL,NULL);
INSERT INTO `dictionary` (`id`,`cat`,`name`,`key`,`value`,`is_default`,`status`,`is_modify`,`date_created`,`last_updated`) VALUES (69,'score_from_type','积分来源','产品','product',0,0,0,NULL,NULL);
INSERT INTO `dictionary` (`id`,`cat`,`name`,`key`,`value`,`is_default`,`status`,`is_modify`,`date_created`,`last_updated`) VALUES (70,'score_from_type','积分来源','活动','activity',0,0,0,NULL,NULL);
INSERT INTO `dictionary` (`id`,`cat`,`name`,`key`,`value`,`is_default`,`status`,`is_modify`,`date_created`,`last_updated`) VALUES (71,'score_from_type','积分来源','权益','rights',0,0,0,NULL,NULL);
INSERT INTO `dictionary` (`id`,`cat`,`name`,`key`,`value`,`is_default`,`status`,`is_modify`,`date_created`,`last_updated`) VALUES (72,'score_from_type','积分来源','其他','other',0,0,0,NULL,NULL);
INSERT INTO `dictionary` (`id`,`cat`,`name`,`key`,`value`,`is_default`,`status`,`is_modify`,`date_created`,`last_updated`) VALUES (74,'risk_level','风险评级','成长型','4',0,0,0,NULL,NULL);
INSERT INTO `dictionary` (`id`,`cat`,`name`,`key`,`value`,`is_default`,`status`,`is_modify`,`date_created`,`last_updated`) VALUES (75,'risk_level','风险评级','进取型','5',0,0,0,NULL,NULL);
INSERT INTO `dictionary` (`id`,`cat`,`name`,`key`,`value`,`is_default`,`status`,`is_modify`,`date_created`,`last_updated`) VALUES (76,'push_channel','推送渠道','系统','1',0,0,0,NULL,NULL);
INSERT INTO `dictionary` (`id`,`cat`,`name`,`key`,`value`,`is_default`,`status`,`is_modify`,`date_created`,`last_updated`) VALUES (77,'push_channel','推送渠道','短信','2',0,0,0,NULL,NULL);
INSERT INTO `dictionary` (`id`,`cat`,`name`,`key`,`value`,`is_default`,`status`,`is_modify`,`date_created`,`last_updated`) VALUES (78,'push_channel','推送渠道','推送','3',0,0,0,NULL,NULL);
INSERT INTO `dictionary` (`id`,`cat`,`name`,`key`,`value`,`is_default`,`status`,`is_modify`,`date_created`,`last_updated`) VALUES (79,'push_channel','推送渠道','邮件','4',0,0,0,NULL,NULL);
INSERT INTO `dictionary` (`id`,`cat`,`name`,`key`,`value`,`is_default`,`status`,`is_modify`,`date_created`,`last_updated`) VALUES (80, 'reservation_status', '预约状态', '预约中', '0', '0', '0', '0', NULL ,NULL);
INSERT INTO `bank`.`dictionary` (`id`, `cat`, `name`, `key`, `value`, `is_default`, `status`, `is_modify`,`date_created`,`last_updated`) VALUES ('81', 'reservation_status', '预约状态', '预约成功', '1', '0', '0', '0', NULL,NULL );
INSERT INTO `bank`.`dictionary` (`id`, `cat`, `name`, `key`, `value`, `is_default`, `status`, `is_modify`,`date_created`,`last_updated`) VALUES ('82', 'reservation_status', '预约状态', '预约失败', '2', '0', '0', '0',NULL ,NULL );
INSERT INTO `bank`.`dictionary` (`id`, `cat`, `name`, `key`, `value`, `is_default`, `status`, `is_modify`,`date_created`,`last_updated`) VALUES ('83', 'reservation_status', '预约状态', '客户取消预约', '3', '0', '0', '0',NULL ,NULL );
INSERT INTO `bank`.`dictionary` (`id`, `cat`, `name`, `key`, `value`, `is_default`, `status`, `is_modify`,`date_created`,`last_updated`) VALUES ('84', 'reservation_status', '预约状态', '客户消费', '4', '0', '0', '0',NULL ,NULL );
INSERT INTO `bank`.`dictionary` (`id`, `cat`, `name`, `key`, `value`, `is_default`, `status`, `is_modify`,`date_created`,`last_updated`) VALUES ('85', 'reservation_status', '预约状态', '客户缺席', '5', '0', '0', '0',NULL ,NULL );



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


/*
-- Query: SELECT * FROM bank.department
-- Date: 2016-08-05 14:30
*/
TRUNCATE TABLE `department`;
INSERT INTO `department` (`department_id`,`title`,`parent_dept_id`,`ctime`,`leaf`,`status`,`leader_uid`,`level`) VALUES (1,'复华资产',NULL,'2016-07-22 20:25:08',0,0,NULL,1);
/*
INSERT INTO `department` (`department_id`,`title`,`parent_dept_id`,`ctime`,`leaf`,`status`,`leader_uid`) VALUES (2,'华北区',1,'2016-07-22 20:25:08',0,0,NULL);
INSERT INTO `department` (`department_id`,`title`,`parent_dept_id`,`ctime`,`leaf`,`status`,`leader_uid`) VALUES (3,'华南区',1,'2016-07-22 20:25:08',0,0,NULL);
INSERT INTO `department` (`department_id`,`title`,`parent_dept_id`,`ctime`,`leaf`,`status`,`leader_uid`) VALUES (9,'西南区',1,'2016-07-22 20:25:08',0,0,NULL);
INSERT INTO `department` (`department_id`,`title`,`parent_dept_id`,`ctime`,`leaf`,`status`,`leader_uid`) VALUES (10,'华东区',1,'2016-07-22 20:25:08',0,0,NULL);
INSERT INTO `department` (`department_id`,`title`,`parent_dept_id`,`ctime`,`leaf`,`status`,`leader_uid`) VALUES (11,'青渝区',1,'2016-07-22 20:25:08',0,0,NULL);
INSERT INTO `department` (`department_id`,`title`,`parent_dept_id`,`ctime`,`leaf`,`status`,`leader_uid`) VALUES (12,'北京分公司',2,'2016-07-22 20:25:08',0,0,255);
INSERT INTO `department` (`department_id`,`title`,`parent_dept_id`,`ctime`,`leaf`,`status`,`leader_uid`) VALUES (13,'天津分公司',2,'2016-07-22 20:25:08',1,0,NULL);
INSERT INTO `department` (`department_id`,`title`,`parent_dept_id`,`ctime`,`leaf`,`status`,`leader_uid`) VALUES (14,'广州分公司',3,'2016-07-22 20:25:08',1,0,NULL);
INSERT INTO `department` (`department_id`,`title`,`parent_dept_id`,`ctime`,`leaf`,`status`,`leader_uid`) VALUES (15,'深圳分公司',3,'2016-07-22 20:25:08',1,0,NULL);
INSERT INTO `department` (`department_id`,`title`,`parent_dept_id`,`ctime`,`leaf`,`status`,`leader_uid`) VALUES (16,'成都分公司',9,'2016-07-22 20:25:08',1,0,NULL);
INSERT INTO `department` (`department_id`,`title`,`parent_dept_id`,`ctime`,`leaf`,`status`,`leader_uid`) VALUES (17,'重庆分公司',9,'2016-07-22 20:25:08',1,0,NULL);
INSERT INTO `department` (`department_id`,`title`,`parent_dept_id`,`ctime`,`leaf`,`status`,`leader_uid`) VALUES (18,'杭州分公司',10,'2016-07-22 20:25:08',1,0,NULL);
INSERT INTO `department` (`department_id`,`title`,`parent_dept_id`,`ctime`,`leaf`,`status`,`leader_uid`) VALUES (19,'上海分公司',10,'2016-07-22 20:25:08',1,0,NULL);
INSERT INTO `department` (`department_id`,`title`,`parent_dept_id`,`ctime`,`leaf`,`status`,`leader_uid`) VALUES (20,'苏州分公司',10,'2016-07-22 20:25:08',1,0,NULL);
INSERT INTO `department` (`department_id`,`title`,`parent_dept_id`,`ctime`,`leaf`,`status`,`leader_uid`) VALUES (21,'青岛分公司',11,'2016-07-22 20:25:08',0,0,NULL);
INSERT INTO `department` (`department_id`,`title`,`parent_dept_id`,`ctime`,`leaf`,`status`,`leader_uid`) VALUES (22,'李鹏直属团队',12,'2016-07-22 20:25:08',1,0,256);
INSERT INTO `department` (`department_id`,`title`,`parent_dept_id`,`ctime`,`leaf`,`status`,`leader_uid`) VALUES (23,'杨辉直属团队',12,'2016-07-22 20:25:08',1,1,NULL);
INSERT INTO `department` (`department_id`,`title`,`parent_dept_id`,`ctime`,`leaf`,`status`,`leader_uid`) VALUES (24,'高峰团队',12,'2016-07-22 20:25:08',1,1,NULL);
INSERT INTO `department` (`department_id`,`title`,`parent_dept_id`,`ctime`,`leaf`,`status`,`leader_uid`) VALUES (26,'青岛团队ee',21,'2016-07-25 20:24:08',1,0,NULL);
*/

TRUNCATE TABLE `user`;

INSERT INTO `bank`.`user` (`uid`, `login`, `password`, `realname`, `gender`, `avatar`, `login_role`,  `ctime`) VALUES ('1', 'fhzs', '14adcfdae177218ed3b7f9806c85c1b4', '复华助手', 'male', '/opt/fhzc/api/image/default-head.jpg', 'planner', now());

TRUNCATE TABLE `assets_recommend`;

INSERT INTO `assets_recommend` (`id`,`recommend_type`,`proportion`,`status`) VALUES ('1', '1', '20.00', '0');
INSERT INTO `assets_recommend` (`id`,`recommend_type`,`proportion`,`status`) VALUES ('2', '2', '20.00', '0');
INSERT INTO `assets_recommend` (`id`,`recommend_type`,`proportion`,`status`)VALUES ('3', '3', '10.00', '0');
INSERT INTO `assets_recommend` (`id`,`recommend_type`,`proportion`,`status`) VALUES ('4', '4', '50.00', '0');
