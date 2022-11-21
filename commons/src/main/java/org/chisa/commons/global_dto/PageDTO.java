package org.chisa.commons.global_dto;

import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;

@Data
@ToString
public class PageDTO {
    private Integer page=1;
    private Integer count=10;
}
