package org.chisa.wo.domain;

import lombok.Data;
import lombok.ToString;
import org.chisa.commons.global_dto.AssetDTO;
import org.chisa.commons.global_dto.StatusAllDTO;

import java.util.Date;

@ToString
@Data
public class Wo {
    private String woId;
    private String title;
    private AssetDTO asset;
    private Integer woScores;
    private StatusAllDTO woStatusId;
    private Date createTime;
    private Date endTime;
    private String consumeTime;
    private String woType;
    private Integer stockId;
}
