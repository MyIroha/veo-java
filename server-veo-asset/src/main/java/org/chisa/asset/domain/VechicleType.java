package org.chisa.asset.domain;


import lombok.Data;
import lombok.ToString;

/**
 * CREATE TABLE IF NOT EXISTS vechicle(
 * 	vechicle_id int(8) PRIMARY KEY COMMENT '车架_id',
 * 	vechicle_type_name varchar(20) not null COMMENT '车架类型名',
 * 	vechicle_icon varchar(200) not null COMMENT '车架图片'
 * ) ENGINE=InnoDB;
 */
@ToString
@Data
public class VechicleType {
    private int vechicleId;

    private String vechicleName;

    private String vechicleIcon;
}
