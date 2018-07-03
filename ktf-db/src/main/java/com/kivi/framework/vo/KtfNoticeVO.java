package com.kivi.framework.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class KtfNoticeVO {

	private Long id;

    private String title;

    private Integer type;

    private Short status;

    private Short count;

    private Short maxCount;

    private String url;

    private String msgId;

    private Date gmtCreate;

    private Date gmtUpdate;

    private Long creater;

    private String content;

}
