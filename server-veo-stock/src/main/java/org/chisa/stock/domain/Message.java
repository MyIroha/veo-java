package org.chisa.stock.domain;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class Message {
    private Integer messageId;
    private Integer transportNumber;
    private Integer isShow;
}
