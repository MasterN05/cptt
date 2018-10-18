package com.znxsx.cptt;

import com.znxsx.cptt.pojo.Message;
import com.znxsx.cptt.service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CpttApplicationTests {
	@Autowired
	private  MailService mailService;
	@Test
	public void testSend() {
		Message msg = new Message();
		msg.setLotteryNumber("123");
		msg.setMsg("测试");
		msg.setPeriod("测试期");
		msg.setMyNumber("123");
		mailService.sendMessageMail(msg, "测试消息通知", "message.ftl");
	}

}
