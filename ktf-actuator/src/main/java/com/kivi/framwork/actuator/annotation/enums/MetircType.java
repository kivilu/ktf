package com.kivi.framwork.actuator.annotation.enums;

public enum MetircType {
    /** 总数 */
    Total,
    /** 等候处理数量 */
    Queued,
    /** 处理中数量 */
    Processing,
    /** 异常数量 */
    Throwing,
    /** 成功交易数量 */
    Success,
    /** 失败交易数量 */
    Failure;
}
