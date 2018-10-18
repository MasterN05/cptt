//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.znxsx.cptt.service;

import com.znxsx.cptt.config.MyConfig;
import com.znxsx.cptt.pojo.Message;
import com.znxsx.cptt.pojo.Number;
import com.znxsx.cptt.util.HttpUtils;
import java.util.HashSet;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledService {
    private static final Logger log = LoggerFactory.getLogger(ScheduledService.class);
    @Autowired
    private MyConfig myConfig;
    @Autowired
    private MailService mailService;
    boolean flag;

    public ScheduledService() {
        this.flag = Boolean.FALSE.booleanValue();
    }

    @Scheduled(
            cron = "0 0 0/24 * * ?"
    )
    public void scheduled() {
        Message msg = HttpUtils.getMsg(myConfig.getApiUrl(),myConfig.getKey());
        String numbers = this.myConfig.getNumber();
        Number my = this.format(numbers);
        String lotteryNumber = msg.getLotteryNumber();
        String replace = lotteryNumber.replaceAll("\"", "").replaceAll("\\[", "").replaceAll("\\]", "");
        Number online = this.format(replace);
        StringBuilder sb = new StringBuilder("");
        this.dofix(my, online, sb);
        if (this.flag) {
            ;
        }

        msg.setMyNumber(numbers);
        msg.setMsg(sb.toString());
        msg.setLotteryNumber(replace);
        this.mailService.sendMessageMail(msg, sb.toString(), "message.ftl");
    }

    public Number format(String numbers) {
        Number my = new Number();
        String[] split = numbers.split(",");
        Set<String> myR = new HashSet();
        myR.add(split[0]);
        myR.add(split[1]);
        myR.add(split[2]);
        myR.add(split[3]);
        myR.add(split[4]);
        myR.add(split[5]);
        my.setRed(myR);
        my.setBlue(split[6]);
        return my;
    }

    public void dofix(Number my, Number online, StringBuilder stringBuilder) {
        Set<String> allNumber = new HashSet();
        allNumber.addAll(my.getRed());
        allNumber.addAll(online.getRed());
        if (online.getBlue().equals(my.getBlue())) {
            stringBuilder.append("中了篮球,五等奖稳了");
            if (allNumber.size() == 9) {
                stringBuilder.append("又中了3个红球,五等奖！");
            }

            if (allNumber.size() == 8) {
                stringBuilder.append("又中了4个红球,四等奖！");
            }

            if (allNumber.size() == 7) {
                stringBuilder.append("又中了5个红球,三等奖！");
            }

            if (allNumber.size() == 6) {
                stringBuilder.append("又中了6个红球,一等奖！");
            }

            this.flag = true;
        } else {
            if (allNumber.size() == 8) {
                stringBuilder.append("中了4个红球,五等奖！");
                this.flag = true;
            }

            if (allNumber.size() == 7) {
                stringBuilder.append("中了5个红球,四等奖！");
                this.flag = true;
            }

            if (allNumber.size() == 6) {
                stringBuilder.append("中了6个红球,二等奖！");
                this.flag = true;
            }
        }

    }
}
