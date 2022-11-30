
-- 创建市场表
CREATE TABLE IF NOT EXISTS stock_location (

  stock_id int(8) not null PRIMARY KEY COMMENT  "市场id",

	stock_location varchar(20) not null COMMENT "市场名"

) ENGINE=InnoDB;

-- 总配件表
CREATE TABLE IF NOT EXISTS sku_parts (

  sku_number varchar(40) not null PRIMARY KEY  COMMENT '配件sku_number',

	sku_name varchar(20) not null COMMENT '配件名',
	
	vechicle_id int(8)  COMMENT '适配车型',
	
	sku_icon varchar(200) not null COMMENT '配件图片'

) ENGINE=InnoDB;

-- 当前仓库配件表
CREATE TABLE IF NOT EXISTS location_parts (

  sku_number varchar(40) not null PRIMARY KEY COMMENT '配件sku_number',

	stock_id int(8) not null COMMENT '市场id',
	
	in_stock int(8) not null COMMENT '库存量',
	
	in_transit int(8) not null COMMENT '运输量',
	
	in_surplus int(8) not null COMMENT '盈余'

) ENGINE=InnoDB;

-- 运输记录表（运输id，发货仓库id，收货仓库id，收货状态【运输中，签收正常,多收,少收,未收到】，sku_number,数量，发货时间,收货时间,收货数量,签收状态）
CREATE TABLE IF NOT EXISTS transport_parts (

  transport_number int(8) not null PRIMARY KEY COMMENT '运输号',

	ship_stock int(8) not null COMMENT '发货仓库id',
	
	receipt_stock int(8) not null COMMENT '收货仓库id',
	
	transport_status varchar(8) not null COMMENT '运输状态',
	
	sku_number varchar(40) not null COMMENT '配件sku_number',
	
	ship_measure int(8) not null default 0 COMMENT '发货量',
	
	ship_time date COMMENT '发货时间',
	
	receipt_time date COMMENT '收货时间',
	
	receipt_number int(8) not null default 0 COMMENT '收货量'
	

) ENGINE=InnoDB;

-- message(id,运输id,是否被查看)
CREATE TABLE IF NOT EXISTS message (

  message_id int(8) not null PRIMARY KEY COMMENT '信息number',

	transport_number int(8) not null COMMENT '运输号',
	
	is_show INT default 0 COMMENT '判断是否查看'

) ENGINE=InnoDB;

-- 适配车型类型




