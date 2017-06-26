package cn.mldn.myrabbit.producer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

public class MessageProducer {
	private static final String QUEUE_NAME = "mldn.msg.queue";
	private static final String HOST = "192.168.109.197";
	private static final Integer PORT = 5672;

	public static void main(String[] args) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(HOST);
		factory.setPort(PORT);
		factory.setUsername("mldnjava");
		factory.setPassword("hello");
		factory.setVirtualHost("hello"); 
		Connection conn = factory.newConnection();
		Channel channel = conn.createChannel();
		// 定义该通道要使用的队列名称，此时的队列已经创建过了
		// 第一个参数：队列名称（这个队列可能存在也可能不存在）
		// 第二个参数：是否为持久保存
		// 第三个参数：此队列是否为专用的队列信息，可以设置为false
		// 第四个参数：是否允许自动删除
		channel.queueDeclare(QUEUE_NAME, true, false, true, null);
		long start = System.currentTimeMillis();
		for (int i = 0; i < 10; i++) {
			String msg = "mldnjava - " + i;
			channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, msg.getBytes());
		} 
		long end = System.currentTimeMillis();
		System.out.println("本次操作所需要的时间: " + (end - start));
		channel.close();
		conn.close();
	}
}
