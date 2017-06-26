package cn.mldn.myrabbit.producer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

public class MessageProducerTopic {
	private static final String EXCHANGE_NAME = "mldn.msg.topic";
	private static final String HOST = "192.168.109.197"; 
	private static final Integer PORT = 5672;

	public static void main(String[] args) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(HOST);
		factory.setPort(PORT);
		factory.setUsername("mldnjava");
		factory.setPassword("hello");

		Connection conn = factory.newConnection();
		Channel channel = conn.createChannel();
		channel.exchangeDeclare(EXCHANGE_NAME, "topic");

		long start = System.currentTimeMillis();
		for (int i = 0; i < 10; i++) {
			String msg = "mldnjava - " + i;
			if (i % 2 == 0) {
				// 第二个参数是routingKey
				channel.basicPublish(EXCHANGE_NAME, "mldn-key-1", MessageProperties.PERSISTENT_TEXT_PLAIN,
						msg.getBytes());
			} else {
				// 第二个参数是routingKey
				channel.basicPublish(EXCHANGE_NAME, "mldn-key-2", MessageProperties.PERSISTENT_TEXT_PLAIN,
						msg.getBytes());
			}
		}
		long end = System.currentTimeMillis(); 
		System.out.println("本次操作所需要的时间: " + (end - start));
		channel.close();
		conn.close();
	}
}
