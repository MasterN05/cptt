package com.znxsx.cptt;

import com.znxsx.cptt.pojo.Message;
import com.znxsx.cptt.service.MailService;
import com.znxsx.cptt.service.MailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@ComponentScan("com.znxsx.cptt.*")
public class CpttApplication {

	public static void main(String[] args) {
		SpringApplication.run(CpttApplication.class, args);
	}
}
