package cn.mldn.rabbitmq.spring.test;

import java.util.Random;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.mldn.myrabbit.service.IMessageService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring-producer.xml" })
public class TestProducer {
	@Resource
	private IMessageService service;

	@Test
	public void testProducer() {
		this.service.send("mldn - " + new Random().nextInt(Integer.MAX_VALUE));
	}
}
