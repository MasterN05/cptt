package com.znxsx.cptt.pojo;

import lombok.Data;

/**
 * Message
 *
 * @author xiongshixin
 * @date 2018/10/17
 **/
@Data
public class Message {
    // 中奖号码
    private String lotteryNumber;
    private String myNumber;
    // 期次
    private String period;
    //消息
    private String msg;
}
