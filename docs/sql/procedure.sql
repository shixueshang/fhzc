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
	
	select dictionary.value into _status from dictionary where cat='score_status' and dictionary.key=p_addflag;

	select dictionary.value into _from_type from dictionary where cat='score_from_type' and dictionary.key=p_type;
	
	if _from_type='activity' then
		select  id into _event_id from activity where name=p_from limit 1;
	end if;
	
	if _from_type='product' then
		select  pid into _event_id from product where name=p_from limit 1;
	end if;
	
	if _from_type='rights' then
		select  id into _event_id from rights where name=p_from limit 1;
	end if;  
	
	if  _from_type='other' then
		set _event_id = -1;
	end if;
	
	
	if _status ='add' then
		insert into score_history(uid,score,event_id,status,operator_type,operator_id,detail,from_type,vaild_time,ctime,is_vaild,is_approve)
		  values(_customer_Id,p_consume_score,_event_id,_status,'admin',p_operator_id, CONCAT('积分', p_addflag , '，类型：',p_type ,',名称：' , p_from)
			,_from_type,p_vaild_time,p_operate_time,1,1);
	else
		insert into score_history(uid,score,event_id,status,operator_type,operator_id,detail,from_type,ctime,is_vaild,is_approve)
		  values(_customer_Id,p_consume_score,_event_id,_status,'admin',p_operator_id, CONCAT('积分', p_addflag , '，类型：',p_type ,',名称：' , p_from)
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
		  values(_customer_Id,-1*p_consume_score,_rights_id,'consume','admin',p_operator_id,CONCAT('积分减，类型：权益消费,名称：',p_rightsname),'rights',now(),1,1);

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
							, invaild, pub_agent, is_member, memo, ctime, has_passport,bank,bank_account)
						values('', _product_id, _customer_Id,p_realname,_planner_id,p_buy_time,0, p_amount_rmb, p_annualised, p_period
							, 0, p_pub_agent, 0, p_memo, now(), 1,'','');
					
					-- 如果是多次付款，则需要合并购买资产，如果是退款，全退的话，则需要把资产置为无效
					select id into _assets_id from assets_history where product_id=_product_id and customer_id=_customer_Id and type='purchase';
					if _assets_id =-1 then 
						insert into assets_history(customer_id,product_id,type,amount,ctime,serial, customer_name,planner_id, buy_time, amount_usd, amount_rmb, annualised, period
							, invaild, bank,bank_account,pub_agent, is_member, memo,bank,bank_account)
							values(_customer_Id,_product_id,'purchase',p_amount_rmb,now(),'', p_realname,_planner_id,p_buy_time,0, p_amount_rmb, p_annualised, p_period
							, 1,'','', p_pub_agent, 0, p_memo,'','');
					else
						update assets_history set amount_rmb =amount_rmb + p_amount_rmb where id=_assets_id;
						update assets_history set invaild =0 where id=_assets_id and amount_rmb=0;
					end if;
					
					

				else 
					INSERT INTO contract(serial, product_id, customer_id, customer_name,planner_id, buy_time, amount_usd, amount_rmb, annualised, period
							, invaild, pub_agent, is_member, memo, ctime, has_passport,bank,bank_account)
						values('', _product_id, -1, p_realname,_planner_id,p_buy_time,0, p_amount_rmb, p_annualised, p_period
							, 0, p_pub_agent, 0, p_memo, now(), 0,'','');				
				end if;
				
			Else 

				select customer_id into _customer_Id from customer where uid =_userid;
				
				INSERT INTO contract(serial, product_id, customer_id, customer_name,planner_id, buy_time, amount_usd, amount_rmb, annualised, period
						, invaild, pub_agent, is_member, memo, ctime, has_passport,bank,bank_account)
					values('', _product_id, _customer_Id,p_realname,_planner_id,p_buy_time,0, p_amount_rmb, p_annualised, p_period
						, 0, p_pub_agent, 0, p_memo, now(), 1,'','');
				

					-- 如果是多次付款，则需要合并购买资产，如果是退款，全退的话，则需要把资产置为无效
					select id into _assets_id from assets_history where product_id=_product_id and customer_id=_customer_Id and type='purchase';
					if _assets_id =-1 then 
						insert into assets_history(customer_id,product_id,type,amount,ctime,serial, customer_name,planner_id, buy_time, amount_usd, amount_rmb, annualised, period
							, invaild, bank,bank_account,pub_agent, is_member, memo,bank,bank_account)
							values(_customer_Id,_product_id,'purchase',p_amount_rmb,now(),'', p_realname,_planner_id,p_buy_time,0, p_amount_rmb, p_annualised, p_period
							, 1,'','', p_pub_agent, 0, p_memo,'','');
					else
						update assets_history set amount_rmb =amount_rmb + p_amount_rmb where id=_assets_id;
						update assets_history set invaild =0 where id=_assets_id and amount_rmb=0;
					end if;

			end if;
			
		Else
			INSERT INTO contract(serial, product_id, customer_id, customer_name,planner_id, buy_time, amount_usd, amount_rmb, annualised, period
					, invaild, pub_agent, is_member, memo, ctime, has_passport,bank,bank_account)
				values('', _product_id, -1, p_realname,_planner_id,p_buy_time,0, p_amount_rmb, p_annualised, p_period
					, 0, p_pub_agent, 0, p_memo, now(), 0,'','');
	
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
			insert into assets_history(serial,customer_id,product_id,type,amount,amount_rmb,amount_usd,ctime,buy_time,period,dead_date,payment_date,duration_day,bank,bank_account,earning_rate,distribute_earning,payment,invaild,is_member) 
				values(_serial,_customer_Id,_product_id,'redemption',p_amount_rmb,p_amount_rmb,0,now(),p_payment_date,_period,p_end_date,p_payment_date,p_duration_day,p_bank,p_bank_account,p_earning_rate,p_distribute_earning,p_total_rate,1,_is_member);
		
		else
			insert into assets_history(serial,customer_id,product_id,type,amount,amount_rmb,amount_usd,ctime,buy_time,period,dead_date,payment_date,duration_day,bank,bank_account,earning_rate,distribute_earning,invaild,is_member) 
				values(_serial,_customer_Id,_product_id,'dividend',p_amount_rmb,p_amount_rmb,0,now(),p_payment_date,_period,p_end_date,p_payment_date,p_duration_day,p_bank,p_bank_account,p_earning_rate,p_distribute_earning,1,_is_member);
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
,p_passport_code varchar(200), p_mobile varchar(200), p_company varchar(45), p_area varchar(45), p_dept1_name varchar(45), p_dept1_leader varchar(45)
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
			insert into department(title,parent_dept_id,ctime,leaf,status,leader_uid,level) values(p_dept1_name,null,now(),0,0,-1,1);
			set _dept1_id = last_insert_id();
		End if;
		set _dept_id = _dept1_id;
	end if; 
	
	if p_dept2_name <> '' and p_dept2_name <> '-'  then 
		select department_id into _dept2_id from department where title=p_dept2_name;
		if _dept2_id = -1 then 
			insert into department(title,parent_dept_id,ctime,leaf,status,leader_uid,level) values(p_dept2_name,_dept1_id,now(),0,0,-1,2);
			set _dept2_id = last_insert_id();
		End if;
		set _dept_id = _dept2_id;
	end if; 
	
	if p_dept3_name <> '' and p_dept3_name <> '-'  then 
		select department_id into _dept3_id from department where title=p_dept3_name;
		if _dept3_id = -1 then 
			insert into department(title,parent_dept_id,ctime,leaf,status,leader_uid,level) values(p_dept3_name,_dept2_id,now(),1,0,-1,3);
			set _dept3_id = last_insert_id();
		End if;
		set _dept_id = _dept3_id;
	end if; 
	
	if p_dept4_name <> '' and p_dept4_name <> '-'  then 
		select department_id into _dept4_id from department where title=p_dept4_name and department.parent_dept_id in(select department_id from department where title=p_dept3_name);
		if _dept4_id = -1 then 
			insert into department(title,parent_dept_id,ctime,leaf,status,leader_uid,level) values(p_dept4_name,_dept3_id,now(),1,0,-1,4);
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
	

END-- ----------------------------
-- Procedure structure for sp_insert_planner
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_insert_planner`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_insert_planner`(p_login varchar(45), p_passwd varchar(45), p_work_num varchar(45), p_realname varchar(45)
,p_passport_code varchar(200), p_mobile varchar(200), p_company varchar(45), p_area varchar(45), p_dept1_name varchar(45), p_dept1_leader varchar(45)
,p_dept2_name varchar(45), p_dept2_leader varchar(45), p_dept3_name varchar(45), p_dept3_leader varchar(45)
,p_dept4_name varchar(45),p_dept4_leader varchar(45),p_job_title_cn varchar(45),p_position varchar(45),p_salt varchar(45)
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
			insert into department(title,parent_dept_id,ctime,leaf,status,leader_uid,level) values(p_dept1_name,null,now(),0,0,-1,1);
			set _dept1_id = last_insert_id();
		End if;
		set _dept_id = _dept1_id;
	end if; 
	
	if p_dept2_name <> '' and p_dept2_name <> '-'  then 
		select department_id into _dept2_id from department where title=p_dept2_name;
		if _dept2_id = -1 then 
			insert into department(title,parent_dept_id,ctime,leaf,status,leader_uid,level) values(p_dept2_name,_dept1_id,now(),0,0,-1,2);
			set _dept2_id = last_insert_id();
			update department set leaf=0 where department_id = _dept1_id;
		End if;
		set _dept_id = _dept2_id;
	end if; 
	
	if p_dept3_name <> '' and p_dept3_name <> '-'  then 
		select department_id into _dept3_id from department where title=p_dept3_name;
		if _dept3_id = -1 then 
			insert into department(title,parent_dept_id,ctime,leaf,status,leader_uid,level) values(p_dept3_name,_dept2_id,now(),1,0,-1,3);
			set _dept3_id = last_insert_id();
			update department set leaf=0 where department_id = _dept2_id;
		End if;
		set _dept_id = _dept3_id;
	end if; 
	
	if p_dept4_name <> '' and p_dept4_name <> '-'  then 
		select department_id into _dept4_id from department where title=p_dept4_name and department.parent_dept_id in(select department_id from department where title=p_dept3_name);
		if _dept4_id = -1 then 
			insert into department(title,parent_dept_id,ctime,leaf,status,leader_uid,level) values(p_dept4_name,_dept3_id,now(),1,0,-1,4);
			set _dept4_id = last_insert_id();
			update department set leaf=0 where department_id = _dept3_id;
		End if;
		set _dept_id = _dept4_id;
	end if; 
	
	select id,uid into _planner_id,_user_id from planner where work_num = P_work_num;
	if _planner_id =-1 then
	-- insert 
		insert into user (login, password, realname, passport_code, mobile, login_role, area_id, ctime,salt) 
		 select p_login,p_passwd,p_realname,p_passport_code,p_mobile,'planner',areas.area_id,now(),p_salt from areas where area_name=p_area;
		
		set _user_id = last_insert_id();
		insert into planner(uid, work_num, department_id, job_title_cn, position) 
			values(_user_id,p_work_num,_dept_id, p_job_title_cn, p_position);
		
		set _planner_id = last_insert_id();
	else
	-- update 
		update user,areas set passport_code =p_passport_code, mobile=p_mobile,salt =p_salt, user.area_id=areas.area_id   where user.uid=_user_id and area_name=p_area;
		update planner set planner.department_id=_dept_id, job_title_cn = p_job_title_cn, position = p_position  
				where id =_planner_id ;
	
	end if;
	

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

		insert into assets_history(serial,customer_id,product_id,type,amount,amount_rmb,amount_usd,ctime,buy_time,period,lot,value_date,dead_date,payment_date,duration_day,bank,bank_account,earning_rate,distribute_earning,payment,invaild,is_member) 
			values(_serial,_customer_Id,_product_id,'redemption',p_amount_rmb,p_amount_rmb,0,now(),p_payment_date,_period,p_lot,p_value_date,p_end_date,p_payment_date,p_duration_day,p_bank,p_bank_account,p_earning_rate,p_distribute_earning,p_total_rate,1,_is_member);

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
	declare _planner_id int;
	set _planner_id =-1;
	
	select id into _planner_id from planner where work_num = P_work_num;
	
	-- 更新理财师为离职状态
	update planner set status='off' where work_num = P_work_num;
	
	-- 进行理财师对应客户缺位管理
	update planner_customer set is_main=0 where planner_id=_planner_id;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for sp_insert_planner
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_update_department_leader`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_update_department_leader`(p_login varchar(45), p_passwd varchar(45), p_work_num varchar(45), p_realname varchar(45)
,p_passport_code varchar(200), p_mobile varchar(200), p_company varchar(45), p_area varchar(45), p_dept1_name varchar(45), p_dept1_leader varchar(45)
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
			insert into department(title,parent_dept_id,ctime,leaf,status,leader_uid,level) values(p_dept1_name,null,now(),0,0,-1,1);
			set _dept1_id = last_insert_id();
		End if;
		set _dept_id = _dept1_id;
	end if; 
	
	if p_dept2_name <> '' and p_dept2_name <> '-'  then 
		select department_id into _dept2_id from department where title=p_dept2_name;
		if _dept2_id = -1 then 
			insert into department(title,parent_dept_id,ctime,leaf,status,leader_uid,level) values(p_dept2_name,_dept1_id,now(),0,0,-1,2);
			set _dept2_id = last_insert_id();
		End if;
		set _dept_id = _dept2_id;
	end if; 
	
	if p_dept3_name <> '' and p_dept3_name <> '-'  then 
		select department_id into _dept3_id from department where title=p_dept3_name;
		if _dept3_id = -1 then 
			insert into department(title,parent_dept_id,ctime,leaf,status,leader_uid,level) values(p_dept3_name,_dept2_id,now(),1,0,-1,3);
			set _dept3_id = last_insert_id();
		End if;
		set _dept_id = _dept3_id;
	end if; 
	
	if p_dept4_name <> '' and p_dept4_name <> '-'  then 
		select department_id into _dept4_id from department where title=p_dept4_name and department.parent_dept_id in(select department_id from department where title=p_dept3_name);
		if _dept4_id = -1 then 
			insert into department(title,parent_dept_id,ctime,leaf,status,leader_uid,level) values(p_dept4_name,_dept3_id,now(),1,0,-1,4);
			set _dept4_id = last_insert_id();
		End if;
		set _dept_id = _dept4_id;
	end if; 
	
	
	-- 一级部门负责人找 对应的一，二，三 部门的人
	if p_dept1_leader <> '' and p_dept1_leader <> '-'  then 
	   set _planner_id = -1;
	   select id into _planner_id from user,planner where user.uid =planner.uid and user.realname=p_dept1_leader and user.login_role='planner' 
	   and (department_id=_dept1_id 
		 or department_id in(select department_id from department where parent_dept_id =_dept1_id)
		 or department_id in(select department_id from department where parent_dept_id in(select department_id from department where parent_dept_id =_dept1_id))
		);

	   if _planner_id > 0 then 
		update department set leader_uid = _planner_id where department_id=_dept1_id;
	   end if;
	End if;  
	
	-- 二 级部门负责人找 对应的一，二，三 部门的人
	if p_dept2_leader <> '' and p_dept2_leader <> '-' then 
	   set _planner_id = -1;
	   select id into _planner_id from user,planner where user.uid =planner.uid and user.realname=p_dept2_leader and user.login_role='planner' 
	   and (department_id=_dept1_id 
		 or department_id in(select department_id from department where parent_dept_id =_dept1_id)
		 or department_id in(select department_id from department where parent_dept_id in(select department_id from department where parent_dept_id =_dept1_id))
		);

		if _planner_id > 0 then 
			update department set leader_uid = _planner_id where department_id=_dept2_id;
	   end if;
	
	   
	End if;  
	
	-- 三级部门负责人找 对应的一，二，三 部门的人
	if p_dept3_leader <> '' and p_dept3_leader <> '-' then 
	   set _planner_id = -1;
	   select id into _planner_id from user,planner where user.uid =planner.uid and user.realname=p_dept3_leader and user.login_role='planner' 
	   and (department_id=_dept1_id 
		 or department_id in(select department_id from department where parent_dept_id =_dept1_id)
		 or department_id in(select department_id from department where parent_dept_id in(select department_id from department where parent_dept_id =_dept1_id))
		);

		if _planner_id > 0 then 
			update department set leader_uid = _planner_id where department_id=_dept3_id;
	   end if;

	End if;  
	
	-- 四级部门负责人找 对应的三，四部门的人
	if p_dept4_leader <> '' and p_dept4_leader <> '-' then 
	   set _planner_id = -1;
	   select id into _planner_id from user,planner where user.uid =planner.uid and, user.realname=p_dept4_leader and user.login_role='planner' and department_id in(_dept3_id,_dept4_id);
	   if _planner_id > 0 then 
			update department set leader_uid = _planner_id where department_id=_dept4_id;
	   end if;

	End if;  
END
;;
DELIMITER ;


-- ----------------------------
-- Procedure structure for sp_add_plannerachivementdaily
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_add_plannerachivementdaily`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_add_plannerachivementdaily`(p_transfer_date varchar(45),p_area_name varchar(45),p_realname varchar(45),p_work_num varchar(45)
, p_belong_manager varchar(45),p_product_name varchar(45),p_annualised int,p_contract_amount int, p_period int, p_product_type varchar(45), p_memo varchar(45)
)
BEGIN
	-- 理财师业绩日报
	Declare _subcompany_id int; -- 分公司ID
	Declare _department_id int;	-- 所属部门ID
	Declare _team_id int; 	-- 所属团队ID
	Declare _planner_id int;  -- 理财师ID
	Declare _level int; 	-- 部门级别
	
	
	select department_id,id into _department_id,_planner_id from planner where work_num =p_work_num;
	select department_id,parent_dept_id,`level` into _team_id,_subcompany_id,_level from department where department_id=_department_id;
	-- 本身属于分公司
	if _level =3 THEN
			set _team_id = -1;
			set _subcompany_id = _department_id;
			
	End if;

	-- 本身属于大区或者总公司
	if _level <=2 THEN
			set _team_id = -1;
			set _subcompany_id = -1;
			
	End if;


	insert into `planner_achivements_daily` (`transfer_date`, `area_id`, `planner_uid`, 
       `product_id`, `annualised`, `contract_amount`,`period`, `product_type`, `memo`, `ctime`, `company`, `team`, `department_id`) 
        select p_transfer_date,area_id,_planner_id,pid,p_annualised,p_contract_amount,p_period,p_product_type,p_memo,NOW(), _subcompany_id,_team_id,_department_id
	   from product left join areas on area_name=p_area_name where product.name=p_product_name;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for sp_add_plannerachivementmonthly
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_add_plannerachivementmonthly`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_add_plannerachivementmonthly`(p_planner_wornum varchar(45),p_planner_percent varchar(5)
,p_mannager_wornum varchar(45),p_mannager_percent varchar(45),p_product_name varchar(45)
,p_realname varchar(45),p_customer_buy int, p_annualised int,p_product_cycle int,p_transfer_date varchar(20),p_memo varchar(45)
)
BEGIN
	-- 理财师业绩月报
	-- 理财师业绩日报
	Declare _subcompany_id int; -- 分公司ID
	Declare _department_id int;	-- 所属部门ID
	Declare _team_id int; 	-- 所属团队ID
	Declare _planner_id int;  -- 理财师ID
	Declare _mannager_id int;  -- 客户经理ID
	Declare _level int; 	-- 部门级别
	
	
	select department_id,id into _department_id,_planner_id from planner where work_num =p_planner_wornum;
  select id into _mannager_id from planner where work_num =p_mannager_wornum;
	select department_id,parent_dept_id,`level` into _team_id,_subcompany_id,_level from department where department_id=_department_id;
	-- 本身属于分公司
	if _level =3 THEN
			set _team_id = -1;
			set _subcompany_id = _department_id;
			
	End if;

	-- 本身属于大区或者总公司
	if _level <=2 THEN
			set _team_id = -1;
			set _subcompany_id = -1;
			
	End if;

	insert into `planner_achivements_monthly` (`planner_uid`, `planner_percent`, `manager_uid`,`mannager_percent`, 
	   `product_id`, `product_type`,`customer_uid`, `customer_name`,`customer_buy`, `annualised`, `product_cycle`, `transfer_date`, `memo`, `ctime`, `area_id`, `company`, `team`, `department_id`) 
		select _planner_id,p_planner_percent,_mannager_id,p_mannager_percent,p.pid,p.product_type,-1,p_realname,p_customer_buy,p_annualised,p_product_cycle,p_transfer_date,p_memo,NOW(),null
		, _subcompany_id,_team_id,_department_id from product p
		where  p.name=p_product_name;
END
;;
DELIMITER ;

