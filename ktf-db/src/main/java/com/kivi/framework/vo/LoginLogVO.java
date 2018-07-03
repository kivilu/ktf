package com.kivi.framework.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginLogVO {
    private Long   id;

    private String logName;

    private String userId;

    private String succeed;

    private String message;

    private String ip;

    private String regionInfo;

    private Date   gmtCreate;

    private Date   gmtUpdate;

}
