package org.chisa.commons.global_dto;

import lombok.Data;
import lombok.ToString;

/**
 * CREATE TABLE IF NOT EXISTS all_status(
 * 	status_id int(8) PRIMARY KEY COMMENT 'status_id',
 * 	status_type varchar(20) not null --
 * -- 状态类型 iot状态,frame状态，wo状态，
 * --,
 * 	status_name varchar(40) not null
 * )ENGINE=InnoDB;
 */
@Data
@ToString
public class StatusAllDTO {
    private Integer statusId;

    private String statusType;

    private String statusName;
}
