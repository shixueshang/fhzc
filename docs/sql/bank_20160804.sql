/*
Navicat MySQL Data Transfer

Source Server         : 120.24.49.204
Source Server Version : 50537
Source Host           : 120.24.49.204:3306
Source Database       : bank

Target Server Type    : MYSQL
Target Server Version : 50537
File Encoding         : 65001

Date: 2016-08-04 16:56:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for activity
-- ----------------------------
DROP TABLE IF EXISTS `activity`;
CREATE TABLE `activity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL COMMENT '活动名称(标题）',
  `content` text COMMENT '活动内容',
  `address` varchar(45) DEFAULT NULL COMMENT '活动地点',
  `apply_begin_time` datetime DEFAULT NULL COMMENT '报名开始时间',
  `apply_end_time` datetime DEFAULT NULL COMMENT '报名结束时间',
  `begin_time` date DEFAULT NULL COMMENT '活动开始时间',
  `end_time` date DEFAULT NULL COMMENT '活动结束时间',
  `memo` varchar(255) DEFAULT NULL COMMENT '注意事项',
  `sponsor` varchar(45) DEFAULT NULL COMMENT '活动主办方',
  `area_id` int(11) DEFAULT NULL,
  `ctime` datetime NOT NULL,
  `url` varchar(255) DEFAULT NULL COMMENT '活动链接',
  `status` int(1) NOT NULL DEFAULT '0' COMMENT '活动状态 0预约中|1已完成',
  `is_display` int(1) NOT NULL DEFAULT '0' COMMENT '是否显示 0否|1是',
  `summary` varchar(255) DEFAULT NULL COMMENT '活动摘要',
  `cid` int(1) DEFAULT NULL COMMENT '活动类型',
  `cover` varchar(255) DEFAULT NULL COMMENT '封面',
  `department_id` int(11) DEFAULT NULL COMMENT '活动发布角色部门',
  `is_ recommend` int(1) unsigned DEFAULT '0' COMMENT '是否推荐 1是|0否',
  `user_req` varchar(255) DEFAULT NULL COMMENT '用户要求',
  PRIMARY KEY (`id`),
  KEY `area` (`area_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='活动';

-- ----------------------------
-- Table structure for activity_apply
-- ----------------------------
DROP TABLE IF EXISTS `activity_apply`;
CREATE TABLE `activity_apply` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `activity_id` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `person_num` int(11) NOT NULL DEFAULT '1' COMMENT '报名人数',
  `planner_id` int(11) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL COMMENT '为他人报名留下电话',
  `type` enum('self','invite') NOT NULL DEFAULT 'self' COMMENT 'self:客户主动参与；invite:理财师邀请',
  `result` int(11) NOT NULL DEFAULT '0' COMMENT '是否参加 0否|1是',
  `ctime` datetime NOT NULL,
  `is_contact` int(1) DEFAULT '0' COMMENT '是否电话联系1 已联系，0未联系',
  `is_sure` int(1) DEFAULT '0' COMMENT '是否确认参加 1 参加，0不参加',
  `person_name` varchar(45) DEFAULT NULL COMMENT '预约客户姓名',
  PRIMARY KEY (`id`),
  KEY `activity_index` (`activity_id`),
  KEY `planner_index` (`planner_id`),
  KEY `type_index` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8 COMMENT='活动邀请';

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `realname` varchar(45) DEFAULT NULL,
  `login_ip` varchar(45) DEFAULT NULL,
  `last_login_time` datetime DEFAULT NULL,
  `role` int(11) DEFAULT NULL,
  `status` int(1) DEFAULT NULL COMMENT '用户状态',
  `mobile` varchar(45) DEFAULT NULL COMMENT '手机号',
  `organ` int(11) DEFAULT NULL COMMENT '所属公司',
  `area` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_uniq` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='后台管理用户表';

-- ----------------------------
-- Table structure for admin_role
-- ----------------------------
DROP TABLE IF EXISTS `admin_role`;
CREATE TABLE `admin_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `role_name` varchar(255) NOT NULL COMMENT '管理组角色名称',
  `description` varchar(500) DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `name_uniq` (`role_name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for areas
-- ----------------------------
DROP TABLE IF EXISTS `areas`;
CREATE TABLE `areas` (
  `area_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `parent_id` int(10) unsigned NOT NULL COMMENT '上一级的id值',
  `area_name` varchar(50) NOT NULL COMMENT '地区名称',
  `display_order` int(10) unsigned NOT NULL DEFAULT '99' COMMENT '排序',
  PRIMARY KEY (`area_id`),
  KEY `parent_id` (`parent_id`),
  KEY `sort` (`display_order`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='地区信息';

-- ----------------------------
-- Table structure for assets_history
-- ----------------------------
DROP TABLE IF EXISTS `assets_history`;
CREATE TABLE `assets_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `type` enum('dividend','purchase','renew','redemption') NOT NULL DEFAULT 'purchase',
  `amount` int(11) DEFAULT NULL COMMENT '金额',
  `ctime` datetime NOT NULL,
  `dead_date` date NOT NULL COMMENT '截至日期',
  `payment_date` date DEFAULT NULL COMMENT '到账日期',
  `serial` varchar(45) NOT NULL COMMENT '合同编号',
  `customer_name` varchar(45) DEFAULT NULL,
  `planner_id` int(11) DEFAULT NULL COMMENT '理财师id',
  `buy_time` date NOT NULL COMMENT '到账日期',
  `amount_usd` int(11) NOT NULL COMMENT '投资额（美元）',
  `amount_rmb` int(11) NOT NULL COMMENT '投资额（人民币）',
  `annualised` int(11) DEFAULT NULL COMMENT '年化金额',
  `period` varchar(45) NOT NULL COMMENT '投资期限',
  `invaild` tinyint(4) NOT NULL DEFAULT '1' COMMENT '合同有效性:1有效|0无效',
  `product_found_day` date DEFAULT NULL COMMENT '产品成立日期',
  `value_date` date DEFAULT NULL COMMENT '起息日',
  `product_expire_day` date DEFAULT NULL COMMENT '产品到期日期',
  `expire_day` date DEFAULT NULL COMMENT '合同到期日期',
  `bank` varchar(45) NOT NULL COMMENT '开户银行信息',
  `bank_account` varchar(45) NOT NULL COMMENT '银行账号',
  `lot` varchar(45) DEFAULT NULL COMMENT '基金份额',
  `duration_month` int(11) DEFAULT NULL COMMENT '投资期限（月）',
  `duration_day` int(11) DEFAULT NULL COMMENT '投资期限（日）',
  `pub_agent` varchar(45) DEFAULT NULL COMMENT '发行机构',
  `branch_agent` varchar(45) DEFAULT NULL COMMENT '分支机构',
  `is_member` tinyint(4) NOT NULL DEFAULT '1' COMMENT '是否是会员 0否|1是',
  `memo` varchar(45) DEFAULT NULL COMMENT '备注信息',
  `earning_rate` varchar(45) DEFAULT NULL COMMENT '收益率',
  `distribute_earning` varchar(45) DEFAULT NULL COMMENT '分配收益',
  `payment` varchar(45) DEFAULT NULL COMMENT '兑付合计',
  PRIMARY KEY (`id`),
  KEY `customer_index` (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8 COMMENT='客户资产配置变动';

-- ----------------------------
-- Table structure for banner
-- ----------------------------
DROP TABLE IF EXISTS `banner`;
CREATE TABLE `banner` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` enum('index_text','index_pic') DEFAULT NULL,
  `text` varchar(45) DEFAULT NULL,
  `cover` varchar(45) DEFAULT NULL,
  `from_id` varchar(45) DEFAULT NULL,
  `from_type` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for contract
-- ----------------------------
DROP TABLE IF EXISTS `contract`;
CREATE TABLE `contract` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `serial` varchar(45) NOT NULL COMMENT '合同编号',
  `product_id` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL COMMENT '客户id',
  `customer_name` varchar(45) DEFAULT NULL,
  `planner_id` int(11) DEFAULT NULL COMMENT '理财师id',
  `buy_time` date NOT NULL COMMENT '到账日期',
  `amount_usd` int(11) NOT NULL COMMENT '投资额（美元）',
  `amount_rmb` int(11) NOT NULL COMMENT '投资额（人民币）',
  `annualised` int(11) DEFAULT NULL COMMENT '年化金额',
  `period` varchar(45) NOT NULL COMMENT '投资期限',
  `invaild` tinyint(4) NOT NULL DEFAULT '1' COMMENT '合同有效性:1有效|0无效',
  `product_found_day` date DEFAULT NULL COMMENT '产品成立日期',
  `value_date` date DEFAULT NULL COMMENT '起息日',
  `product_expire_day` date DEFAULT NULL COMMENT '产品到期日期',
  `expire_day` date DEFAULT NULL COMMENT '合同到期日期',
  `bank` varchar(45) NOT NULL COMMENT '开户银行信息',
  `bank_account` varchar(45) NOT NULL COMMENT '银行账号',
  `lot` varchar(45) DEFAULT NULL COMMENT '基金份额',
  `duration_month` int(11) DEFAULT NULL COMMENT '投资期限（月）',
  `duration_day` int(11) DEFAULT NULL COMMENT '投资期限（日）',
  `pub_agent` varchar(45) DEFAULT NULL COMMENT '发行机构',
  `branch_agent` varchar(45) DEFAULT NULL COMMENT '分支机构',
  `is_member` tinyint(4) NOT NULL DEFAULT '1' COMMENT '是否是会员 0否|1是',
  `memo` varchar(45) DEFAULT NULL COMMENT '备注信息',
  `ctime` datetime NOT NULL,
  `earning_rate` varchar(45) DEFAULT NULL COMMENT '收益率',
  `distribute_earning` varchar(45) DEFAULT NULL COMMENT '分配收益',
  `payment` varchar(45) DEFAULT NULL COMMENT '兑付合计',
  `has_passport` tinyint(4) NOT NULL DEFAULT '0' COMMENT '合同是否有身份证信息',
  PRIMARY KEY (`id`,`period`),
  KEY `product_index` (`product_id`),
  KEY `planner_index` (`planner_id`)
) ENGINE=InnoDB AUTO_INCREMENT=122 DEFAULT CHARSET=utf8 COMMENT='客户合同表';

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `customer_id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL,
  `cb_id` varchar(45) DEFAULT NULL COMMENT '客户号（来自excel）',
  `level_id` int(11) DEFAULT NULL,
  `risk` varchar(45) DEFAULT NULL,
  `department_id` int(11) NOT NULL COMMENT '客户所属分公司',
  `bank_info_id` int(11) DEFAULT NULL COMMENT '银行信息id',
  `organ_name` varchar(100) DEFAULT NULL COMMENT '机构全称',
  `customer_type` enum('single','organ') DEFAULT NULL,
  `business_license` varchar(255) DEFAULT NULL COMMENT '营业执照',
  `account_license` varchar(255) DEFAULT NULL COMMENT '开户许可',
  `contact_relation` varchar(45) DEFAULT NULL COMMENT '联系人关系',
  `entrusted_letter` varchar(45) DEFAULT NULL COMMENT '法人委托函',
  PRIMARY KEY (`customer_id`),
  UNIQUE KEY `customer_uniq` (`uid`),
  KEY `dept_index` (`department_id`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8 COMMENT='客户信息表';

-- ----------------------------
-- Table structure for customer_bank
-- ----------------------------
DROP TABLE IF EXISTS `customer_bank`;
CREATE TABLE `customer_bank` (
  `bank_info_id` int(11) NOT NULL AUTO_INCREMENT,
  `bank` varchar(45) NOT NULL COMMENT '开户行',
  `bank_account` varchar(45) NOT NULL COMMENT '银行卡号',
  `ctime` datetime NOT NULL COMMENT '记录添加时间',
  `is_del` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除 0否|1是',
  PRIMARY KEY (`bank_info_id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for customer_organ
-- ----------------------------
DROP TABLE IF EXISTS `customer_organ`;
CREATE TABLE `customer_organ` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_id` int(11) NOT NULL COMMENT '客户id',
  `rights_enjoy_person` varchar(45) DEFAULT NULL COMMENT '权益享用人',
  `passport_type_id` int(11) DEFAULT NULL COMMENT '证件类型',
  `passport_code` varchar(45) DEFAULT NULL COMMENT '证件号',
  `mobile` varchar(11) DEFAULT NULL COMMENT '手机号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='机构客户权益享用人表';

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `department_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) DEFAULT NULL COMMENT '部门名',
  `parent_dept_id` int(11) DEFAULT NULL COMMENT '上级部门的ID',
  `ctime` datetime NOT NULL COMMENT '创建时间',
  `leaf` int(1) DEFAULT NULL COMMENT '是否叶子节点',
  `status` int(1) DEFAULT NULL COMMENT '数据状态(0正常1删除)',
  `leader_uid` int(11) DEFAULT NULL COMMENT '部门负责人uid',
  PRIMARY KEY (`department_id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COMMENT='组织结构';

-- ----------------------------
-- Table structure for dictionary
-- ----------------------------
DROP TABLE IF EXISTS `dictionary`;
CREATE TABLE `dictionary` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cat` varchar(50) DEFAULT NULL COMMENT '字典分类【英】',
  `name` varchar(50) DEFAULT NULL COMMENT '字典分类【中】',
  `key` varchar(50) DEFAULT NULL COMMENT '键',
  `value` varchar(50) DEFAULT NULL COMMENT '值',
  `is_default` int(1) DEFAULT NULL COMMENT '是否默认 1、是，0、否',
  `status` int(1) DEFAULT NULL COMMENT '状态',
  `is_modify` int(1) DEFAULT NULL COMMENT '是否允许修改 1、是，0、否',
  `date_created` datetime DEFAULT NULL COMMENT '创建时间',
  `last_updated` datetime DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8 COMMENT='字典';

-- ----------------------------
-- Table structure for focus
-- ----------------------------
DROP TABLE IF EXISTS `focus`;
CREATE TABLE `focus` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL,
  `fid` int(11) DEFAULT NULL,
  `ftype` enum('product','report','rights','activity') DEFAULT NULL,
  `ctime` datetime DEFAULT NULL,
  `status` int(11) DEFAULT '0' COMMENT '关注状态 0 未关注 1 关注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `fcs_uniq` (`uid`,`fid`,`ftype`),
  KEY `uid_index` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8 COMMENT='客户关注';

-- ----------------------------
-- Table structure for im_message
-- ----------------------------
DROP TABLE IF EXISTS `im_message`;
CREATE TABLE `im_message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `content` text COMMENT '内容',
  `send_time` datetime NOT NULL COMMENT '发送时间',
  `session_id` varchar(50) NOT NULL COMMENT '聊天组唯一标识',
  `message_type` varchar(20) NOT NULL COMMENT '消息类型',
  `to_user_id` int(11) NOT NULL COMMENT '消息接收人',
  `is_read` tinyint(1) DEFAULT '0' COMMENT '是否已读',
  `duration` varchar(10) DEFAULT NULL COMMENT '语音长度',
  `mid` int(1) DEFAULT '0' COMMENT '每个聊天组每条信息的唯一标识',
  `status` varchar(20) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=125 DEFAULT CHARSET=utf8 COMMENT='消息表';

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `desc` varchar(45) DEFAULT NULL,
  `ctime` datetime NOT NULL,
  `admin_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='后台系统变更日志';

-- ----------------------------
-- Table structure for planner
-- ----------------------------
DROP TABLE IF EXISTS `planner`;
CREATE TABLE `planner` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL,
  `work_num` varchar(45) NOT NULL COMMENT '工号',
  `department_id` int(11) DEFAULT NULL COMMENT '所属分公司',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `status` enum('on','off') NOT NULL DEFAULT 'on' COMMENT '理财师状态,on在职,off离职',
  `role_id` int(11) DEFAULT NULL COMMENT '理财师角色',
  `entry_time` date DEFAULT NULL COMMENT '入职时间',
  `leave_time` date DEFAULT NULL COMMENT '离职时间',
  `job_title_cn` varchar(45) DEFAULT NULL COMMENT '岗位名称',
  `position` varchar(45) DEFAULT NULL COMMENT '岗位序列',
  PRIMARY KEY (`id`),
  UNIQUE KEY `work_num_uniq` (`work_num`),
  KEY `dept_index` (`department_id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for planner_achivements
-- ----------------------------
DROP TABLE IF EXISTS `planner_achivements`;
CREATE TABLE `planner_achivements` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL,
  `sales` decimal(15,2) DEFAULT NULL COMMENT '年化销售额',
  `time_frame` varchar(45) DEFAULT NULL COMMENT '时间段',
  `ctime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `uid_index` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='理财师业绩';

-- ----------------------------
-- Table structure for planner_achivements_daily
-- ----------------------------
DROP TABLE IF EXISTS `planner_achivements_daily`;
CREATE TABLE `planner_achivements_daily` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `transfer_date` date DEFAULT NULL COMMENT '划款到账日期',
  `area_id` int(11) DEFAULT NULL COMMENT '地区',
  `planner_uid` int(11) NOT NULL COMMENT '理财规划师uid',
  `product_id` int(11) NOT NULL COMMENT '产品名称',
  `annualised` int(11) DEFAULT NULL COMMENT '年化金额',
  `contract_amount` int(11) DEFAULT NULL COMMENT '合同金额',
  `expire_date` date DEFAULT NULL,
  `product_type` varchar(45) DEFAULT NULL COMMENT '产品类型',
  `memo` varchar(255) DEFAULT NULL COMMENT '备注',
  `ctime` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=210 DEFAULT CHARSET=utf8 COMMENT='理财师每日业绩动态';

-- ----------------------------
-- Table structure for planner_achivements_monthly
-- ----------------------------
DROP TABLE IF EXISTS `planner_achivements_monthly`;
CREATE TABLE `planner_achivements_monthly` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `planner_uid` int(11) NOT NULL COMMENT '理财规划师uid',
  `planner_percent` varchar(5) NOT NULL COMMENT '理财师分单占比',
  `manager_uid` int(11) DEFAULT NULL COMMENT '客户经理uid',
  `mannager_percent` varchar(45) DEFAULT NULL COMMENT '客户经理分单占比',
  `product_id` int(11) NOT NULL COMMENT '产品名称',
  `product_type` varchar(45) DEFAULT NULL,
  `customer_uid` int(11) NOT NULL COMMENT '认购人uid',
  `customer_name` varchar(45) DEFAULT NULL COMMENT '客户姓名',
  `customer_buy` int(11) NOT NULL COMMENT '认购金额',
  `annualised` int(11) DEFAULT NULL COMMENT '年化金额',
  `product_cycle` int(11) DEFAULT NULL COMMENT '产品周期（月）',
  `transfer_date` date DEFAULT NULL COMMENT '划款到账日期',
  `memo` varchar(255) DEFAULT NULL COMMENT '备注',
  `ctime` datetime NOT NULL,
  `area_id` int(11) DEFAULT NULL COMMENT '地区',
  PRIMARY KEY (`id`),
  KEY `uid_index` (`planner_uid`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8 COMMENT='理财师每月确认业绩';

-- ----------------------------
-- Table structure for planner_areas
-- ----------------------------
DROP TABLE IF EXISTS `planner_areas`;
CREATE TABLE `planner_areas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `planner_id` int(11) DEFAULT NULL COMMENT '理财师id',
  `area_id` int(11) DEFAULT NULL COMMENT '区域id',
  `ctime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `area_index` (`area_id`),
  KEY `planner_index` (`planner_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='理财师地理权限范围';

-- ----------------------------
-- Table structure for planner_customer
-- ----------------------------
DROP TABLE IF EXISTS `planner_customer`;
CREATE TABLE `planner_customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `planner_id` int(11) DEFAULT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `is_main` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否为主理财师 0否|1是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34209262 DEFAULT CHARSET=utf8 COMMENT='客户理财师关系表';

-- ----------------------------
-- Table structure for planner_department
-- ----------------------------
DROP TABLE IF EXISTS `planner_department`;
CREATE TABLE `planner_department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL,
  `department_id` int(11) DEFAULT NULL COMMENT '部门ID',
  `role` tinyint(4) DEFAULT NULL COMMENT '角色',
  PRIMARY KEY (`id`),
  KEY `planner_index` (`uid`),
  KEY `dept_index` (`department_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `pid` int(11) NOT NULL AUTO_INCREMENT,
  `status` tinyint(4) DEFAULT '-1' COMMENT '基金状态 -1未知|0产品预热|1募集中|2募集结束|3募集失败|4产品成立|5产品到期|6提前结束',
  `code` varchar(45) DEFAULT NULL COMMENT '产品代码',
  `name` varchar(45) NOT NULL COMMENT '产品名称',
  `found_day` date DEFAULT NULL COMMENT '成立日',
  `buy_day` varchar(200) DEFAULT NULL COMMENT '开放申购日',
  `redeem_day` varchar(200) DEFAULT NULL COMMENT '赎回日',
  `value_day` date DEFAULT NULL COMMENT '起息日',
  `expiry_day` date DEFAULT NULL COMMENT '到期日',
  `issue_type` int(1) DEFAULT NULL COMMENT '发行模式',
  `product_type` varchar(200) DEFAULT NULL COMMENT '产品类型',
  `renew_deadline` varchar(200) DEFAULT NULL COMMENT '产品续存期限',
  `dividend_day` varchar(200) DEFAULT NULL COMMENT '派息日',
  `annual_yield` varchar(200) DEFAULT NULL COMMENT '年化收益率',
  `income_distribution_type` varchar(200) DEFAULT NULL COMMENT '收益分配方式',
  `credit` varchar(255) DEFAULT NULL COMMENT '增信措施',
  `investment_orientation` varchar(200) DEFAULT NULL COMMENT '投资方向',
  `increase_trust` varchar(200) DEFAULT NULL COMMENT '增信措施',
  `highlights` varchar(200) DEFAULT NULL COMMENT '产品亮点',
  `fund_management_fee` varchar(45) NOT NULL COMMENT '基金管理费',
  `fund_subscription_fee` varchar(45) NOT NULL COMMENT '基金认购费',
  `fund_manager` varchar(45) NOT NULL COMMENT '基金管理人',
  `custodian` varchar(45) NOT NULL COMMENT '基金托管人',
  `investment_type` varchar(45) DEFAULT NULL COMMENT '投资类型',
  `project_source` varchar(45) DEFAULT NULL COMMENT '项目来源',
  `issuer` varchar(45) DEFAULT NULL COMMENT '发行公司',
  `is_record` varchar(45) DEFAULT '0' COMMENT '备案与否 0否|1是',
  `prove_url` varchar(255) DEFAULT NULL COMMENT '备案证明文件存储地址',
  `display_order` int(11) DEFAULT NULL COMMENT '显示顺序',
  `level` int(11) DEFAULT NULL COMMENT '允许购买的客户等级',
  `risk` int(11) DEFAULT NULL COMMENT '能接受的客户风险等级',
  `cover` varchar(255) DEFAULT NULL COMMENT '产品封面',
  `notice` varchar(45) DEFAULT NULL COMMENT '产品成立公告',
  `score_factor` tinyint(3) unsigned DEFAULT '100' COMMENT '产品积分系数百分比,默认100%',
  `is_ recommend` tinyint(3) unsigned DEFAULT '0' COMMENT '是否推荐 1是|0否',
  `is_display` tinyint(3) unsigned DEFAULT '0' COMMENT '产品是否显示 1显示|0不显示',
  `is_renew` tinyint(3) unsigned DEFAULT '0' COMMENT '是否是续存期产品 0否|1是',
  `desc` text COMMENT '产品简介',
  `ctime` datetime DEFAULT NULL COMMENT '记录增加时间',
  `detail_content` text COMMENT '详细内容',
  `detail_url` varchar(255) DEFAULT NULL COMMENT '详情链接',
  `invest_term_min` int(11) NOT NULL COMMENT '最少投资期限',
  `invest_term_max` int(11) NOT NULL COMMENT '最大投资期限',
  `invest_threshold` decimal(12,2) NOT NULL DEFAULT '1000000.00' COMMENT '起投金额',
  `expected_min` decimal(6,2) DEFAULT NULL COMMENT '预期年化收益率min',
  `expected_max` decimal(6,2) DEFAULT NULL COMMENT '预期年化收益率max',
  `throw_department` int(11) DEFAULT NULL COMMENT '投放分公司',
  `collect_start` date DEFAULT NULL COMMENT '募集期的开始',
  `collect_end` date DEFAULT NULL COMMENT '募集期的结束',
  PRIMARY KEY (`pid`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 COMMENT='产品';

-- ----------------------------
-- Table structure for product_reservation
-- ----------------------------
DROP TABLE IF EXISTS `product_reservation`;
CREATE TABLE `product_reservation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `planner_id` int(11) DEFAULT NULL,
  `apply_time` datetime DEFAULT NULL COMMENT '预约时间',
  `ctime` datetime DEFAULT NULL,
  `result` enum('cancel','success','failed','wait') DEFAULT 'wait' COMMENT '预约状态：取消\\成功\\失败\\等待',
  `amount` int(11) DEFAULT NULL COMMENT '投资金额',
  PRIMARY KEY (`id`),
  KEY `customer_index` (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8 COMMENT='产品预约';

-- ----------------------------
-- Table structure for provider
-- ----------------------------
DROP TABLE IF EXISTS `provider`;
CREATE TABLE `provider` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `desc` varchar(45) DEFAULT NULL COMMENT '描述',
  `area_id` int(11) DEFAULT NULL,
  `ctime` datetime NOT NULL COMMENT '添加时间',
  `level` tinyint(4) DEFAULT NULL COMMENT '需要的客户等级',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_uniq` (`name`),
  KEY `area_index` (`area_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='供应商';

-- ----------------------------
-- Table structure for report
-- ----------------------------
DROP TABLE IF EXISTS `report`;
CREATE TABLE `report` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cid` int(11) DEFAULT NULL COMMENT '分类',
  `name` varchar(45) NOT NULL COMMENT '名称',
  `cover` varchar(200) DEFAULT NULL COMMENT '封面图',
  `summary` text COMMENT '摘要',
  `url` varchar(255) DEFAULT NULL COMMENT '投研报告外部url',
  `ctime` datetime NOT NULL,
  `is_del` int(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `is_display` int(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否显示 1显示|0不显示',
  `is_recommend` int(1) unsigned DEFAULT '0' COMMENT '是否推荐 1是|0否',
  PRIMARY KEY (`id`),
  KEY `category` (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='投研报告';

-- ----------------------------
-- Table structure for reservation
-- ----------------------------
DROP TABLE IF EXISTS `reservation`;
CREATE TABLE `reservation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) DEFAULT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `planner_id` int(11) DEFAULT NULL,
  `ctime` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='产品预约';

-- ----------------------------
-- Table structure for rights
-- ----------------------------
DROP TABLE IF EXISTS `rights`;
CREATE TABLE `rights` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cid` int(10) unsigned NOT NULL COMMENT '权益分类名称',
  `name` varchar(45) NOT NULL,
  `spend_score` int(11) NOT NULL COMMENT '花费积分数量',
  `spend_type` enum('var','static') DEFAULT 'static' COMMENT '兑换积分是否固定',
  `level` int(10) unsigned DEFAULT NULL COMMENT '需要的客户等级',
  `ctime` datetime NOT NULL COMMENT '创建时间',
  `supply` varchar(255) DEFAULT NULL,
  `summary` text COMMENT '简介',
  `cover` varchar(255) DEFAULT NULL COMMENT '封面',
  `url` varchar(255) DEFAULT NULL COMMENT '详情链接',
  `is_ recommend` int(10) unsigned DEFAULT '0' COMMENT '是否精选 1是|0否',
  `notice` varchar(255) DEFAULT '无' COMMENT '报名须知',
  PRIMARY KEY (`id`),
  KEY `cid_index` (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8 COMMENT='权益';

-- ----------------------------
-- Table structure for rights_provider
-- ----------------------------
DROP TABLE IF EXISTS `rights_provider`;
CREATE TABLE `rights_provider` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rights_id` int(11) DEFAULT NULL COMMENT '权益id',
  `provider_id` int(11) DEFAULT NULL COMMENT '供应商id',
  `ctime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for rights_reservation
-- ----------------------------
DROP TABLE IF EXISTS `rights_reservation`;
CREATE TABLE `rights_reservation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rights_id` int(11) NOT NULL COMMENT '预约的权益id',
  `customer_id` int(11) NOT NULL COMMENT '客户id',
  `planner_id` int(11) DEFAULT NULL COMMENT '理财师id',
  `mark_date` datetime NOT NULL COMMENT '预约时间',
  `detail` varchar(45) DEFAULT NULL COMMENT '预约详情',
  `score_cost` int(11) DEFAULT NULL COMMENT '积分花费',
  `status` int(1) DEFAULT NULL COMMENT '预约状态 0预约中|1预约成功|2预约失败|3客户取消预约|4客户消费|5客户缺席',
  `memo` varchar(200) DEFAULT NULL COMMENT '备注',
  `ctime` datetime NOT NULL COMMENT '记录添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=utf8 COMMENT='客户权益预约';

-- ----------------------------
-- Table structure for score_history
-- ----------------------------
DROP TABLE IF EXISTS `score_history`;
CREATE TABLE `score_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL,
  `score` int(11) NOT NULL COMMENT '积分数值，扣件为负数，增加为正数',
  `event_id` int(11) DEFAULT NULL COMMENT '获得或者扣减积分的来源id(产品购买+、参加活动+，兑换权益-)',
  `status` enum('add','consume','frozen','expire') NOT NULL DEFAULT 'frozen',
  `operator_type` enum('user','admin') NOT NULL COMMENT '操作触发人员类型:用户|管理员',
  `operator_id` int(11) NOT NULL COMMENT '操作人id',
  `detail` varchar(45) DEFAULT NULL COMMENT '积分变动描述',
  `from_type` enum('activity','product','rights','other') NOT NULL DEFAULT 'other' COMMENT '积分变动来源',
  `vaild_time` date DEFAULT NULL COMMENT '积分有效时间',
  `ctime` datetime NOT NULL COMMENT '记录创建时间',
  `is_vaild` int(1) NOT NULL DEFAULT '1' COMMENT '有效性',
  `is_approve` int(1) NOT NULL DEFAULT '0' COMMENT '审批状态',
  PRIMARY KEY (`id`),
  KEY `uid_index` (`uid`),
  KEY `product_index` (`event_id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8 COMMENT='积分历史表';

-- ----------------------------
-- Table structure for system_module
-- ----------------------------
DROP TABLE IF EXISTS `system_module`;
CREATE TABLE `system_module` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL COMMENT '模块名称',
  `url` varchar(50) NOT NULL COMMENT '功能资源',
  `desc` varchar(45) DEFAULT NULL COMMENT '描述',
  `parent_module_id` int(11) DEFAULT NULL COMMENT '父级资源',
  `level` int(1) DEFAULT NULL COMMENT '资源级别',
  `is_valid` tinyint(1) NOT NULL COMMENT '是否可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8 COMMENT='后台系统模块名称';

-- ----------------------------
-- Table structure for system_role_module
-- ----------------------------
DROP TABLE IF EXISTS `system_role_module`;
CREATE TABLE `system_role_module` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `admin_role_id` int(11) DEFAULT NULL,
  `module_id` int(11) NOT NULL COMMENT '模块id',
  `mode` enum('r','rw') NOT NULL DEFAULT 'r' COMMENT '模块读\\读写',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=133 DEFAULT CHARSET=utf8 COMMENT='后台人员权限';

-- ----------------------------
-- Table structure for template
-- ----------------------------
DROP TABLE IF EXISTS `template`;
CREATE TABLE `template` (
  `tid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `content` text,
  PRIMARY KEY (`tid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='消息模板';

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(45) NOT NULL COMMENT '前台用户（客户、理财师）',
  `password` varchar(45) NOT NULL COMMENT '密码',
  `realname` varchar(45) DEFAULT NULL COMMENT '姓名',
  `gender` enum('male','female') DEFAULT 'male' COMMENT '性别',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `passport_type_id` int(11) DEFAULT NULL COMMENT '证件类型',
  `passport_code` varchar(200) DEFAULT NULL COMMENT '证件号码（加密）',
  `passport_agent` varchar(45) DEFAULT NULL COMMENT '发证机关（加密）',
  `passport_address` varchar(45) DEFAULT NULL COMMENT '证件地址（加密）',
  `passport_expire` varchar(45) DEFAULT NULL COMMENT '证件有效期',
  `mobile` varchar(200) DEFAULT NULL COMMENT '移动电话',
  `phone` varchar(45) DEFAULT NULL COMMENT '固定电话',
  `phone_ext` varchar(45) DEFAULT NULL COMMENT '其他电话',
  `email` varchar(200) DEFAULT NULL COMMENT '邮箱',
  `address` varchar(200) DEFAULT NULL COMMENT '通信地址（加密）',
  `login_role` enum('customer','planner') NOT NULL COMMENT '登陆角色（客户、理财师）',
  `device_uuid` varchar(45) DEFAULT NULL COMMENT '登陆设备uuid',
  `area_id` int(11) DEFAULT NULL COMMENT '区域id',
  `is_del` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `ctime` datetime NOT NULL,
  `avatar` varchar(255) DEFAULT NULL COMMENT '用户图像',
  `salt` varchar(45) DEFAULT NULL COMMENT '加密key',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `login` (`login`),
  KEY `area` (`area_id`)
) ENGINE=InnoDB AUTO_INCREMENT=331 DEFAULT CHARSET=utf8 COMMENT='App登录用户总表';

-- ----------------------------
-- Table structure for user_log
-- ----------------------------
DROP TABLE IF EXISTS `user_log`;
CREATE TABLE `user_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL COMMENT '用户id',
  `login_role` enum('customer','planner') DEFAULT 'customer' COMMENT '登陆用户类型',
  `action` varchar(45) DEFAULT NULL,
  `ctime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `uid_index` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='手机登陆用户操作存档';

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `role_name` varchar(255) NOT NULL COMMENT '角色名称',
  `ctime` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `role_name` (`role_name`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Procedure structure for sp_add_scorehistory
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_add_scorehistory`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_add_scorehistory`(p_realname varchar(45),p_gender varchar(10),p_passport_type varchar(20),p_passport_code varchar(200)
, p_addflag varchar(45),p_type varchar(20),p_from varchar(45),p_consume_score int, p_vaild_time varchar(20), p_operator_id int
, p_operate_time varchar(20),p_oprate_type varchar(45),p_memo varchar(45)
)
BEGIN
	-- 
	
	DECLARE _userid INT;	-- 用户ID
	Declare _passport_type_id int;	-- 证件类型
	Declare _gender varchar(10);	-- 性别
	Declare _event_id int;
	declare _from_type varchar(20);
	declare _status varchar(20);
	
	Declare _customer_Id int ; -- 客户id
	set _event_id = -1;	
	set _customer_Id = -1;
	set _status ='add';
	select dictionary.value into _passport_type_id from dictionary where cat='passport' and dictionary.key=p_passport_type;
	select customer_Id into _customer_Id from customer,user where customer.uid =user.uid and user.passport_type_id=_passport_type_id and passport_code = p_passport_code;
	
	if p_addflag ='减' then
		set p_consume_score = -1* p_consume_score;
		set _status ='consume';
	end if ;
	
	if p_type='活动积分' then
		select  id into _event_id from activity where name=p_from limit 1;
		set _from_type = 'activity';
	end if;
	if p_type='产品积分' then
		select  pid into _event_id from product where name=p_from limit 1;
		set _from_type = 'product';
	end if;
	if p_type='权益消费' then
		select  id into _event_id from rights where name=p_from limit 1;
		set _from_type = 'rights';
	end if;  
	if p_type='人工调整积分' then
		set _event_id = -1;
		set _from_type = 'other' ;
	end if;
	-- 其他的情况
	if _event_id = -1 then 
		set _from_type = 'other' ;
	end if;
	if p_addflag ='增' then
		insert into score_history(uid,score,event_id,status,operator_type,operator_id,detail,from_type,vaild_time,ctime,is_vaild,is_approve)
		  values(_customer_Id,p_consume_score,_event_id,_status,'admin',p_operator_id, '积分'+ p_addflag + '，类型：' +p_type +',名称：' + p_from
			,_from_type,p_vaild_time,p_operate_time,1,1);
	else
		insert into score_history(uid,score,event_id,status,operator_type,operator_id,detail,from_type,ctime,is_vaild,is_approve)
		  values(_customer_Id,p_consume_score,_event_id,_status,'admin',p_operator_id, '积分'+ p_addflag + '，类型：' +p_type +',名称：' + p_from
			,_from_type,p_operate_time,1,1);
	End if;

END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for sp_consume_scorehistory
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_consume_scorehistory`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_consume_scorehistory`(p_realname varchar(45),p_gender varchar(10),p_passport_type varchar(20),p_passport_code varchar(200)
, p_rightsname varchar(45),p_consume_time varchar(20),p_consume_address varchar(45),p_consume_score int, p_last_score int, p_operator_id int
)
BEGIN
	-- 权益消费记录
	
	DECLARE _userid INT;	-- 用户ID
	Declare _passport_type_id int;	-- 证件类型
	Declare _gender varchar(10);	-- 性别
	Declare _rights_id int;
	
	Declare _customer_Id int ; -- 客户id
	set _rights_id = -1;	
	set _customer_Id = -1;
	select dictionary.value into _passport_type_id from dictionary where cat='passport' and dictionary.key=p_passport_type;
	select customer_Id into _customer_Id from customer,user where customer.uid =user.uid and user.passport_type_id=_passport_type_id and passport_code = p_passport_code;
	
	select id into _rights_id from rights where name=p_rightsname limit 1;
	if _rights_id >0 then 

		insert into score_history(uid,score,event_id,status,operator_type,operator_id,detail,from_type,ctime,is_vaild,is_approve)
		  values(_customer_Id,-1*p_consume_score,_rights_id,'consume','admin',p_operator_id,'积分减，类型：权益消费,名称：' + p_rightsname,'rights',now(),1,1);

	end if;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for sp_insert_customerdocument
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_insert_customerdocument`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_insert_customerdocument`(p_login varchar(45), p_passwd varchar(45),  p_realname varchar(45),p_gender varchar(10)
,p_customerno varchar(20),p_passport_type varchar(20),p_passport_code varchar(200), p_birth_year varchar(4),p_birth_month varchar(2),p_birth_day varchar(2)
,p_passport_agent varchar(45),p_passport_expire varchar(45),p_phone1_type varchar(10),p_phone1_num varchar(200),p_phone2_type varchar(10),p_phone2_num varchar(200)
,p_phone3_type varchar(10),p_phone3_num varchar(200),p_email varchar(200), p_passport_address varchar(45), p_address varchar(200), p_product varchar(45)
, p_amount_rmb int,p_annualised int, p_buy_time varchar(20), p_product_found_day varchar(20), p_product_expire_day varchar(20)
,p_bank varchar(45),p_bank_account varchar(45),p_lot varchar(45),p_period varchar(45),p_pub_agent varchar(45),p_branch_agent varchar(45)
,p_plannner varchar(45),p_plannner_work_num varchar(45),p_serial varchar(45), p_score varchar(10),p_is_member varchar(2),p_member_level varchar(45)
, p_memo varchar(45),p_salt varchar(45),p_customer_type varchar(45)
)
BEGIN
	-- 投资人档案表
	-- 用户不存在，先新增用户，然后再增加资产信息 
	-- 如果用户存在，如果没有相应的银行信息，那么需要更改用户的银行等信息； 然后根据产品，客户,及购买判断是否有合同资产信息，有的话（数据来源于财务日报，信息不准确完善），进行合同相应的信息修改，没有的话，进行新增。

	DECLARE _userid INT;	-- 用户ID
	Declare _passport_type_id int;	-- 证件类型
	Declare _gender varchar(10);	-- 性别
	Declare _bank_info_id int;	-- 银行信息
	Declare _contract_id int; -- 合同id
	Declare _planner_id int; -- 理财师id
	Declare _is_member int;	-- 是否会员
	Declare _product_id int;	-- 产品id
	Declare _user_area_id int;
	
	Declare _customer_Id int ; -- 客户id
	Declare _customer_type varchar(20); -- 客户类型


	Declare _isnotreal_customer int;
	Declare _assets_id int; -- 客户资产id
	Declare _department_id int; -- 所属部门ID
	Declare _level_id int;	-- 会员等级ID
	Declare _exists int; 
	
	set _level_id = -1;
	set _isnotreal_customer =-1;

	set _assets_id =-1;
	set _userid = -1;
	set _contract_id =-1;
	set _customer_Id = -1;
	set _department_id = -1;
	
	if p_customer_type ='机构' then
		set _customer_type='organ';
	else
		set  _customer_type ='single';
	end if;

	
	if p_gender ='男' then
		set _gender = 'male';
	else
		set _gender = 'female';
	end if;
	
	if p_is_member ='Y' then 
		set _is_member = 1;
	else
	  set _is_member = 0;
	end if ;
	
	select dictionary.value into _passport_type_id from dictionary where cat='passport' and dictionary.key=p_passport_type;
	select uid into _userid from user where user.passport_type_id=_passport_type_id and passport_code = p_passport_code;
	
	select area_id into _user_area_id from areas where area_name= p_branch_agent;
	
	if _userid =-1 then
	
		INSERT INTO user(login, password, realname, gender, birthday, passport_type_id, passport_code, passport_agent, passport_address, passport_expire
			, mobile, phone, phone_ext, email, address, login_role, area_id, ctime,salt)
		  values(p_login,p_passwd,p_realname,_gender,concat(p_birth_year,'-',p_birth_month,'-',p_birth_day),_passport_type_id,p_passport_code,p_passport_agent,p_passport_address,p_passport_expire
			,p_phone1_num,p_phone2_num,p_phone3_num, p_email,p_address,'customer', _user_area_id,now(),p_salt);
		
		set _userid = last_insert_id();
		
		insert into customer_bank(bank,bank_account,ctime) values(p_bank,p_bank_account,now());
		set _bank_info_id = last_insert_id();
		
		select value  into _level_id from dictionary where cat='customer_level' and dictionary.key=p_member_level;
		
		insert into customer(uid,cb_id,level_id,risk,department_id,bank_info_id,customer_type) 
			value(_userid, p_customerno, _level_id,'',_department_id,_bank_info_id,_customer_type);
		
	else

		-- 如果该用户只是准用户(来自于财务日报，有身份证,有手机的新客户)，不是正式客户的时候，更新客户信息(不更新手机信息)，转为正式客户
		-- 如果是老客户，就不做处理
		select customer_id into _isnotreal_customer from customer where uid = _userid and bank_info_id=-1;
		if _isnotreal_customer >0 then 
			update user 
				set realname=p_realname,gender=_gender,birthday=concat(p_birth_year,'-',p_birth_month,'-',p_birth_day),passport_agent=p_passport_agent,passport_address=p_passport_address,passport_expire=p_passport_expire
					,phone=p_phone2_num,phone_ext=p_phone3_num,email=p_email,address=p_address,area_id=_user_area_id
			where uid = _userid ;
			
			-- 如果只是user，但不是customer的时候，我们需要转化成customer

			insert into customer_bank(bank,bank_account,ctime) values(p_bank,p_bank_account,now());
			set _bank_info_id = last_insert_id();

			select value  into _level_id from dictionary where cat='customer_level' and dictionary.key=p_member_level;
			
			update customer
				set customer.cb_id = p_customerno,level_id = _level_id,customer.department_id=_department_id,customer.bank_info_id=_bank_info_id
				where customer.uid=_userid;

		end if ;
	
	end if;
	
	select id into _planner_id from planner where work_num=p_plannner_work_num;
	select pid into _product_id from product where name=p_product;	
	select customer_id into _customer_Id from customer where uid =_userid;
	
	
	select id into _contract_id from contract where customer_name = p_realname and product_id=_product_id and planner_id=_planner_id and buy_time=p_buy_time;
	
	
	-- 判断是否有资产信息，如果没有，那说明是历史数据。如果有，那数据来源财务日报数据
	select id into _assets_id from assets_history where customer_id=_customer_Id and product_id= _product_id and type = 'purchase';
	if _assets_id = -1 then
		insert into assets_history(customer_id,product_id,type,amount,ctime
			,serial, customer_name,planner_id, buy_time, amount_usd, amount_rmb, annualised, period
			, invaild, product_found_day,  product_expire_day, bank, bank_account, lot, duration_month, duration_day
			, pub_agent, branch_agent, is_member, memo) 
			values(_customer_Id,_product_id,'purchase',p_amount_rmb,now()
			, p_serial, p_realname,_planner_id,p_buy_time,0, p_amount_rmb, p_annualised, p_period
			, 1, p_product_found_day,  p_product_expire_day, p_bank, p_bank_account, p_lot, null, null
			, p_pub_agent, p_branch_agent, _is_member, p_memo
			);
	else
		update assets_history 
		set serial=p_serial,buy_time=p_buy_time,amount_rmb=p_amount_rmb,annualised=p_annualised,period=p_period
			,invaild=1,product_found_day=p_product_found_day,product_expire_day=p_product_expire_day,bank=p_bank,bank_account=p_bank_account
			,lot=p_lot,pub_agent=pub_agent,branch_agent=p_branch_agent,is_member=_is_member,memo=p_memo
		where id = _assets_id;
	end if;
	
	-- 增加客户的默认理财师数据
	set _exists = 0; 
	select id into _exists from planner_customer where customer_id=_customer_Id and is_main=1;
	if _exists = 0 then 
		insert into planner_customer(planner_id,customer_id,is_main) values(_planner_id,_customer_Id,1); 
	end if;

	set _exists = 0; 
	select id into _exists from planner_customer where customer_id=_customer_Id and  planner_id=_planner_id;
	if _exists = 0 then 
		insert into planner_customer(planner_id,customer_id,is_main) values(_planner_id,_customer_Id,0); 
	end if; 
	-- 增加积分记录
	
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for sp_insert_financialDaily
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_insert_financialDaily`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_insert_financialDaily`(p_login varchar(45), p_passwd varchar(45), p_product varchar(45)
,p_pub_agent varchar(45),p_passport_type varchar(20),p_passport_code varchar(200), p_realname varchar(45),p_phone_num varchar(200),p_customer_type varchar(45)
,p_amount_rmb int,p_annualised int,p_period int, p_earning_rate varchar(10),p_buy_time varchar(20),p_belong_company varchar(45),p_plannner varchar(45),p_work_num varchar(20)
,p_memo varchar(45),p_salt varchar(45)
)
BEGIN

	-- 财务日报数据(有客户身份证，则判断是否新老客户，新客户：只增加user 信息，不增加customer信息(准客户),增加contract合同信息,不增加asset_hitory；
	-- 老客户：增加contract合同信息及asset_hitory )
	-- 新客户有身份证且有手机：增加user,customer信息类型投资人,增加contract合同信息及asset_hitory )
	-- 新客户有身份证且无手机：增加contract合同信息 )
	-- 财务日报数据(没有客户身份证，只增加contract合同信息)
	DECLARE _userid INT;	-- 用户ID
	Declare _passport_type_id int;	-- 证件类型
	Declare _gender varchar(10);	-- 性别
	Declare _bank_info_id int;	-- 银行信息
	Declare _contract_id int; -- 合同id
	Declare _planner_id int; -- 理财师id
	Declare _is_member int;	-- 是否会员
	Declare _product_id int;	-- 产品id
	declare _user_area_id int;
	
	
	declare _customer_Id int ; -- 客户id
	declare _assets_id int ; -- 客户资产id
	Declare _customer_type varchar(20); -- 客户类型
	
	set _userid = -1;
	set _contract_id =-1;
	set _customer_Id = -1;
	set _assets_id = -1;
	
	if p_customer_type ='机构' then
		set _customer_type='organ';
	else
		set  _customer_type ='single';
	end if;
	
	select area_id into _user_area_id from areas where area_name= p_belong_company;
	select id into _planner_id from planner where work_num=p_work_num;
	select pid into _product_id from product where name=p_product;	
	
	select id into _contract_id from contract where customer_name = p_realname and product_id=_product_id and planner_id=_planner_id and buy_time=p_buy_time and amount_rmb=p_amount_rmb;
	
	-- 根据客户姓名，理财师，产品及购买时间来确定合同
	-- 如果没有对应的合同信息，那么我们需要新增合同信息，否则就不进行操作
	if _contract_id =-1 THen 
	
		if p_passport_code <> ''  then 
	
			select dictionary.value into _passport_type_id from dictionary where cat='passport' and dictionary.key=p_passport_type;
			select uid into _userid from user where user.passport_type_id=_passport_type_id and passport_code = p_passport_code;
			
			
			if _userid =-1 then
			
				if p_phone_num <> '' then 
					INSERT INTO user(login, password, realname,  passport_type_id, passport_code, mobile, login_role, area_id, ctime,salt)
					  values(p_login,p_passwd,p_realname,_passport_type_id,p_passport_code,p_phone_num,'customer', _user_area_id,now(),p_salt);
				
					set _userid = last_insert_id();
					
					insert into customer(uid,cb_id,level_id,risk,department_id,bank_info_id,customer_type) 
					select _userid, -1, dictionary.value,'',-1,-1,_customer_type from dictionary where cat='customer_level' and dictionary.key='投资人';

					
					select customer_id into _customer_Id from customer where uid =_userid;
				
					INSERT INTO contract(serial, product_id, customer_id, customer_name,planner_id, buy_time, amount_usd, amount_rmb, annualised, period
							, invaild, pub_agent, is_member, memo, ctime, has_passport)
						values('', _product_id, _customer_Id,p_realname,_planner_id,p_buy_time,0, p_amount_rmb, p_annualised, p_period
							, 0, p_pub_agent, 0, p_memo, now(), 1);
					
					-- 如果是多次付款，则需要合并购买资产，如果是退款，全退的话，则需要把资产置为无效
					select id into _assets_id from assets_history where product_id=_product_id and customer_id=_customer_Id and type='purchase';
					if _assets_id =-1 then 
						insert into assets_history(customer_id,product_id,type,amount,ctime,serial, customer_name,planner_id, buy_time, amount_usd, amount_rmb, annualised, period
							, invaild, bank,bank_account,pub_agent, is_member, memo)
							values(_customer_Id,_product_id,'purchase',p_amount_rmb,now(),'', p_realname,_planner_id,p_buy_time,0, p_amount_rmb, p_annualised, p_period
							, 1,'','', p_pub_agent, 0, p_memo);
					else
						update assets_history set amount_rmb =amount_rmb + p_amount_rmb where id=_assets_id;
						update assets_history set invaild =0 where id=_assets_id and amount_rmb=0;
					end if;
					
					

				else 
					INSERT INTO contract(serial, product_id, customer_id, customer_name,planner_id, buy_time, amount_usd, amount_rmb, annualised, period
							, invaild, pub_agent, is_member, memo, ctime, has_passport)
						values('', _product_id, -1, p_realname,_planner_id,p_buy_time,0, p_amount_rmb, p_annualised, p_period
							, 0, p_pub_agent, 0, p_memo, now(), 0);				
				end if;
				
			Else 

				select customer_id into _customer_Id from customer where uid =_userid;
				
				INSERT INTO contract(serial, product_id, customer_id, customer_name,planner_id, buy_time, amount_usd, amount_rmb, annualised, period
						, invaild, pub_agent, is_member, memo, ctime, has_passport)
					values('', _product_id, _customer_Id,p_realname,_planner_id,p_buy_time,0, p_amount_rmb, p_annualised, p_period
						, 0, p_pub_agent, 0, p_memo, now(), 1);
				

					-- 如果是多次付款，则需要合并购买资产，如果是退款，全退的话，则需要把资产置为无效
					select id into _assets_id from assets_history where product_id=_product_id and customer_id=_customer_Id and type='purchase';
					if _assets_id =-1 then 
						insert into assets_history(customer_id,product_id,type,amount,ctime,serial, customer_name,planner_id, buy_time, amount_usd, amount_rmb, annualised, period
							, invaild, bank,bank_account,pub_agent, is_member, memo)
							values(_customer_Id,_product_id,'purchase',p_amount_rmb,now(),'', p_realname,_planner_id,p_buy_time,0, p_amount_rmb, p_annualised, p_period
							, 1,'','', p_pub_agent, 0, p_memo);
					else
						update assets_history set amount_rmb =amount_rmb + p_amount_rmb where id=_assets_id;
						update assets_history set invaild =0 where id=_assets_id and amount_rmb=0;
					end if;

			end if;
			
		Else
			INSERT INTO contract(serial, product_id, customer_id, customer_name,planner_id, buy_time, amount_usd, amount_rmb, annualised, period
					, invaild, pub_agent, is_member, memo, ctime, has_passport)
				values('', _product_id, -1, p_realname,_planner_id,p_buy_time,0, p_amount_rmb, p_annualised, p_period
					, 0, p_pub_agent, 0, p_memo, now(), 0);
	
		End if;
	End if;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for sp_insert_financialPayment
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_insert_financialPayment`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_insert_financialPayment`(p_serial varchar(45), p_product varchar(45)
,p_realname varchar(45),p_customer_type varchar(45),p_passport_type varchar(20),p_passport_code varchar(200), p_phone_num varchar(200),p_amount_rmb int,p_payment_date varchar(20),p_end_date varchar(20)
,p_duration_day int, p_earning_rate varchar(10),p_distribute_earning varchar(45) ,p_total_rate varchar(45),p_bank varchar(45),p_bank_account varchar(20),p_pub_agent varchar(45)
)
BEGIN
	-- 财务兑付记录
	-- 如果没有本息合计，那只是兑付利息，如果有，那说明是产品到期兑付
	
	DECLARE _userid INT;	-- 用户ID
	Declare _passport_type_id int;	-- 证件类型
	Declare _gender varchar(10);	-- 性别
	Declare _bank_info_id int;	-- 银行信息
	Declare _contract_id int; -- 合同id
	Declare _planner_id int; -- 理财师id
	Declare _is_member int;	-- 是否会员
	Declare _product_id int;	-- 产品id
	Declare _user_area_id int;
	Declare _serial varchar(45); -- 合同号
	Declare _customer_Id int ; -- 客户id
	Declare _period int;  -- 期限
	set _contract_id = -1;
	select id , customer_id, product_id,serial,period,is_member into _contract_id, _customer_Id,_product_id,_serial,_period,_is_member from assets_history where serial =p_serial and type='purchase';
	if _contract_id >0 then 
		if p_total_rate >0 then 
			insert into assets_history(serial,customer_id,product_id,type,amount,ctime,buy_time,period,dead_date,payment_date,duration_day,bank,bank_account,earning_rate,distribute_earning,payment,invaild,is_member) 
				values(_serial,_customer_Id,_product_id,'redemption',p_amount_rmb,now(),p_payment_date,_period,p_end_date,p_payment_date,p_duration_day,p_bank,p_bank_account,p_earning_rate,p_distribute_earning,p_total_rate,1,_is_member);
		
		else
			insert into assets_history(serial,customer_id,product_id,type,amount,ctime,buy_time,period,dead_date,payment_date,duration_day,bank,bank_account,earning_rate,distribute_earning,invaild,is_member) 
				values(_serial,_customer_Id,_product_id,'dividend',p_amount_rmb,now(),p_payment_date,_period,p_end_date,p_payment_date,p_duration_day,p_bank,p_bank_account,p_earning_rate,p_distribute_earning,1,_is_member);
		end if;		
	end if;


END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for sp_insert_planner
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_insert_planner`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_insert_planner`(p_login varchar(45), p_passwd varchar(45), p_work_num varchar(45), p_realname varchar(45)
,p_passport_code varchar(45), p_mobile varchar(45), p_company varchar(45), p_area varchar(45), p_dept1_name varchar(45), p_dept1_leader varchar(45)
,p_dept2_name varchar(45), p_dept2_leader varchar(45), p_dept3_name varchar(45), p_dept3_leader varchar(45)
,p_dept4_name varchar(45),p_dept4_leader varchar(45),p_job_title_cn varchar(45),p_position varchar(45)
)
BEGIN
	-- 理财师花名册 ，只负责导入department 和 planner
	Declare _user_id int;  -- user id
	DECLARE _planner_id int;	-- 理财师ID
	Declare _dept1_id int;		-- 部门1 ID
	Declare _dept2_id int;		-- 部门2 ID
	Declare _dept3_id int;		-- 部门3 ID
	Declare _dept4_id int;		-- 部门4 ID
	Declare _dept_id int; 		-- 理财师所在部门 ID
	
	set _planner_id = -1;
	set _user_id =-1;
	set _dept1_id = -1;
	set _dept2_id = -1;
	set _dept3_id = -1;
	set _dept4_id = -1;
	set _dept_id = -1;
	-- 先判断部门是否存在，不存在就新增
	-- 再判断理财师是否存在，不存在，就新增，如存在，就更新改理财师的信息。如果改理财师是部门负责人，则更新对应的部门负责人。
	
	if p_dept1_name <> '' and p_dept1_name <> '-'  then 
		select department_id into _dept1_id from department where title=p_dept1_name;
		if _dept1_id = -1 then 
			insert into department(title,parent_dept_id,ctime,leaf,status,leader_uid) values(p_dept1_name,0,now(),0,0,-1);
			set _dept1_id = last_insert_id();
		End if;
		set _dept_id = _dept1_id;
	end if; 
	
	if p_dept2_name <> '' and p_dept2_name <> '-'  then 
		select department_id into _dept2_id from department where title=p_dept2_name;
		if _dept2_id = -1 then 
			insert into department(title,parent_dept_id,ctime,leaf,status,leader_uid) values(p_dept2_name,_dept1_id,now(),0,0,-1);
			set _dept2_id = last_insert_id();
		End if;
		set _dept_id = _dept2_id;
	end if; 
	
	if p_dept3_name <> '' and p_dept3_name <> '-'  then 
		select department_id into _dept3_id from department where title=p_dept3_name;
		if _dept3_id = -1 then 
			insert into department(title,parent_dept_id,ctime,leaf,status,leader_uid) values(p_dept3_name,_dept2_id,now(),1,0,-1);
			set _dept3_id = last_insert_id();
		End if;
		set _dept_id = _dept3_id;
	end if; 
	
	if p_dept4_name <> '' and p_dept4_name <> '-'  then 
		select department_id into _dept4_id from department where title=p_dept4_name and department.parent_dept_id in(select department_id from department where title=p_dept3_name);
		if _dept4_id = -1 then 
			insert into department(title,parent_dept_id,ctime,leaf,status,leader_uid) values(p_dept4_name,_dept3_id,now(),1,0,-1);
			set _dept4_id = last_insert_id();
		End if;
		set _dept_id = _dept4_id;
	end if; 
	
	select id,uid into _planner_id,_user_id from planner where work_num = P_work_num;
	if _planner_id =-1 then
	-- insert 
		insert into user (login, password, realname, passport_code, mobile, login_role, area_id, ctime) 
		 select p_login,p_passwd,p_realname,p_passport_code,p_mobile,'planner',areas.area_id,now() from areas where area_name=p_area;
		
		set _user_id = last_insert_id();
		insert into planner(uid, work_num, department_id, job_title_cn, position) 
			values(_user_id,p_work_num,_dept_id, p_job_title_cn, p_position);
		
		set _planner_id = last_insert_id();
	else
	-- update 
		update user,areas set passport_code =p_passport_code, mobile=p_mobile, user.area_id=areas.area_id   where user.uid=_user_id and area_name=p_area;
		update planner set planner.department_id=_dept_id, job_title_cn = p_job_title_cn, position = p_position  
				where uid =_planner_id ;
	
	end if;
	

END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for sp_insert_planner
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_update_department_leader`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_insert_planner`(p_login varchar(45), p_passwd varchar(45), p_work_num varchar(45), p_realname varchar(45)
,p_passport_code varchar(45), p_mobile varchar(45), p_company varchar(45), p_area varchar(45), p_dept1_name varchar(45), p_dept1_leader varchar(45)
,p_dept2_name varchar(45), p_dept2_leader varchar(45), p_dept3_name varchar(45), p_dept3_leader varchar(45)
,p_dept4_name varchar(45),p_dept4_leader varchar(45),p_job_title_cn varchar(45),p_position varchar(45)
)
BEGIN
	-- 理财师花名册，只负责更新部门负责人
	Declare _user_id int;  -- user id
	DECLARE _planner_id int;	-- 理财师ID
	Declare _dept1_id int;		-- 部门1 ID
	Declare _dept2_id int;		-- 部门2 ID
	Declare _dept3_id int;		-- 部门3 ID
	Declare _dept4_id int;		-- 部门4 ID
	Declare _dept_id int; 		-- 理财师所在部门 ID
	
	set _planner_id = -1;
	set _user_id =-1;
	set _dept1_id = -1;
	set _dept2_id = -1;
	set _dept3_id = -1;
	set _dept4_id = -1;
	set _dept_id = -1;
	-- 先判断部门是否存在，不存在就新增
	-- 再判断理财师是否存在，不存在，就新增，如存在，就更新改理财师的信息。如果改理财师是部门负责人，则更新对应的部门负责人。
	
	if p_dept1_name <> '' and p_dept1_name <> '-'  then 
		select department_id into _dept1_id from department where title=p_dept1_name;
		if _dept1_id = -1 then 
			insert into department(title,parent_dept_id,ctime,leaf,status,leader_uid) values(p_dept1_name,0,now(),0,0,-1);
			set _dept1_id = last_insert_id();
		End if;
		set _dept_id = _dept1_id;
	end if; 
	
	if p_dept2_name <> '' and p_dept2_name <> '-'  then 
		select department_id into _dept2_id from department where title=p_dept2_name;
		if _dept2_id = -1 then 
			insert into department(title,parent_dept_id,ctime,leaf,status,leader_uid) values(p_dept2_name,_dept1_id,now(),0,0,-1);
			set _dept2_id = last_insert_id();
		End if;
		set _dept_id = _dept2_id;
	end if; 
	
	if p_dept3_name <> '' and p_dept3_name <> '-'  then 
		select department_id into _dept3_id from department where title=p_dept3_name;
		if _dept3_id = -1 then 
			insert into department(title,parent_dept_id,ctime,leaf,status,leader_uid) values(p_dept3_name,_dept2_id,now(),1,0,-1);
			set _dept3_id = last_insert_id();
		End if;
		set _dept_id = _dept3_id;
	end if; 
	
	if p_dept4_name <> '' and p_dept4_name <> '-'  then 
		select department_id into _dept4_id from department where title=p_dept4_name and department.parent_dept_id in(select department_id from department where title=p_dept3_name);
		if _dept4_id = -1 then 
			insert into department(title,parent_dept_id,ctime,leaf,status,leader_uid) values(p_dept4_name,_dept3_id,now(),1,0,-1);
			set _dept4_id = last_insert_id();
		End if;
		set _dept_id = _dept4_id;
	end if; 
	
	
	-- 一级部门负责人找 对应的一，二，三 部门的人
	if p_dept1_leader <> '' and p_dept1_leader <> '-'  then 
	   set _planner_id = -1;
	   select id into _planner_id from user,planner where user.uid =planner.uid and user.realname=p_dept1_leader and user.login_role='planner' and department_id in(_dept1_id,_dept2_id,_dept3_id);
	   if _planner_id > 0 then 
		update department set leader_uid = _planner_id where department_id=_dept1_id;
	   end if;
	End if;  
	
	-- 二 级部门负责人找 对应的一，二，三 部门的人
	if p_dept2_leader <> '' and p_dept2_leader <> '-' then 
	   set _planner_id = -1;
	   select id into _planner_id from user,planner where user.uid =planner.uid and user.realname=p_dept2_leader and user.login_role='planner' and department_id in(_dept1_id,_dept2_id,_dept3_id);
	   if _planner_id > 0 then 
			update department set leader_uid = _planner_id where department_id=_dept2_id;
	   end if;
	
	   
	End if;  
	
	-- 三级部门负责人找 对应的一，二，三 部门的人
	if p_dept3_leader <> '' and p_dept3_leader <> '-' then 
	   set _planner_id = -1;
	   select id into _planner_id from user,planner where user.uid =planner.uid and user.realname=p_dept3_leader and user.login_role='planner' and department_id in(_dept1_id,_dept2_id,_dept3_id);
	   if _planner_id > 0 then 
			update department set leader_uid = _planner_id where department_id=_dept3_id;
	   end if;

	End if;  
	
	-- 四级部门负责人找 对应的三，四部门的人
	if p_dept4_leader <> '' and p_dept4_leader <> '-' then 
	   set _planner_id = -1;
	   select id into _planner_id from user,planner where user.uid =planner.uid and user.realname=p_dept4_leader and user.login_role='planner' and department_id in(_dept3_id,_dept4_id);
	   if _planner_id > 0 then 
			update department set leader_uid = _planner_id where department_id=_dept4_id;
	   end if;

	End if;  
END
;;
DELIMITER ;


-- ----------------------------
-- Procedure structure for sp_insert_specialfinancialPayment
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_insert_specialfinancialPayment`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_insert_specialfinancialPayment`(p_payment_date varchar(20),p_value_date varchar(20), p_product varchar(45)
,p_realname varchar(45),p_passport_type varchar(20),p_passport_code varchar(200),p_customer_type varchar(45), p_amount_rmb int,p_lot varchar(20),p_bussiness_type varchar(20),p_end_date varchar(20)
,p_duration_day int, p_earning_rate varchar(10),p_distribute_earning varchar(45),p_return varchar(45) ,p_total_rate varchar(45),p_bank varchar(45),p_bank_account varchar(20)
)
BEGIN
	-- 鑫丰母兑付记录
	-- 鑫丰母没有派息，只有本息合计，那说明是产品到期兑付
	
	DECLARE _userid INT;	-- 用户ID
	Declare _passport_type_id int;	-- 证件类型
	Declare _gender varchar(10);	-- 性别
	Declare _bank_info_id int;	-- 银行信息
	Declare _contract_id int; -- 合同id
	Declare _planner_id int; -- 理财师id
	Declare _is_member int;	-- 是否会员
	Declare _product_id int;	-- 产品id
	Declare _user_area_id int;
	Declare _serial varchar(45); -- 合同号
	Declare _customer_Id int ; -- 客户id
	Declare _period int;  -- 期限

	set _contract_id = -1;
	
	select dictionary.value into _passport_type_id from dictionary where cat='passport' and dictionary.key=p_passport_type;
	select customer_Id into _customer_Id from customer,user where customer.uid =user.uid and user.passport_type_id=_passport_type_id and passport_code = p_passport_code;
	select id , customer_id, product_id,serial,period,is_member into _contract_id, _customer_Id,_product_id,_serial,_period,_is_member  from assets_history, product
		where assets_history.customer_id =_customer_Id and assets_history.product_id= product.pid and product.name=p_product and assets_history.type='purchase';
	if _contract_id >0 then 

		insert into assets_history(serial,customer_id,product_id,type,amount,ctime,buy_time,period,lot,value_date,dead_date,payment_date,duration_day,bank,bank_account,earning_rate,distribute_earning,payment,invaild,is_member) 
			values(_serial,_customer_Id,_product_id,'redemption',p_amount_rmb,now(),p_payment_date,_period,p_lot,p_value_date,p_end_date,p_payment_date,p_duration_day,p_bank,p_bank_account,p_earning_rate,p_distribute_earning,p_total_rate,1,_is_member);

	end if;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for sp_removeoffer_planner
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_removeoffer_planner`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_removeoffer_planner`(p_work_num varchar(45))
BEGIN

	update planner set status='off' where work_num = P_work_num;
END
;;
DELIMITER ;
