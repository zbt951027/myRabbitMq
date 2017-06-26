package cn.mldn.rabbitmq.spring.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring-consumer.xml" })
public class TestConsumer {
	@Test
	public void testConsumer() {
		try {
			Thread.sleep(Long.MAX_VALUE);	//启动消费者容器 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
