-- -----------------------------------------------------
-- procedure sp_insert_financialPayment
-- -----------------------------------------------------


USE `bank`;
DROP procedure IF EXISTS `bank`.`sp_insert_financialPayment`;

DELIMITER $$
USE `bank`$$
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


  END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure sp_insert_planner
-- -----------------------------------------------------

USE `bank`;
DROP procedure IF EXISTS `bank`.`sp_insert_planner`;

DELIMITER $$
USE `bank`$$
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


  END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure sp_removeoffer_planner
-- -----------------------------------------------------

USE `bank`;
DROP procedure IF EXISTS `bank`.`sp_removeoffer_planner`;

DELIMITER $$
USE `bank`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_removeoffer_planner`(p_work_num varchar(45))
  BEGIN

    update planner set status='off' where work_num = P_work_num;
  END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure sp_update_department_leader
-- -----------------------------------------------------

USE `bank`;
DROP procedure IF EXISTS `bank`.`sp_update_department_leader`;

DELIMITER $$
USE `bank`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_update_department_leader`(p_login varchar(45), p_passwd varchar(45), p_work_num varchar(45), p_realname varchar(45)
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
      select id into _planner_id from user,planner where user.uid =planner.uid and user.realname=p_dept4_leader and user.login_role='planner' and department_id in(_dept3_id,_dept4_id);
      if _planner_id > 0 then
        update department set leader_uid = _planner_id where department_id=_dept4_id;
      end if;

    End if;
  END$$

DELIMITER ;