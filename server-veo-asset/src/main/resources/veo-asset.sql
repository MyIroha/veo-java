CREATE TABLE IF NOT EXISTS asset (

    asset_id varchar(30) NOT NULL PRIMARY KEY COMMENT '资产id',

	iot_number varchar(30) not null COMMENT 'IOT Number',
	
	frame_number varchar(30) not null COMMENT 'Frame Number',
	
	vechicle_id int(8) not null COMMENT '车架类型',
	
	stock_id int(8) not null COMMENT '所在市场',
	
	wo_id varchar(100) default '1' COMMENT '工单详情',

	iot_status_id int(8) not null COMMENT 'iot状态',

	frame_status_id int(8) not null COMMENT 'frame状态'
) ENGINE=InnoDB;



CREATE TABLE IF NOT EXISTS all_status(
	status_id int(8) PRIMARY KEY COMMENT 'status_id',
	status_type varchar(20) not null --
-- 状态类型 iot状态,frame状态，wo状态，
--,
	status_name varchar(40) not null
)ENGINE=InnoDB;

-- vechicle_table
CREATE TABLE IF NOT EXISTS vechicle(
	vechicle_id int(8) PRIMARY KEY COMMENT '车架_id',
	vechicle_type_name varchar(20) not null COMMENT '车架类型名',
	vechicle_icon varchar(200) not null COMMENT '车架图片'
) ENGINE=InnoDB;


