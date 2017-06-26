package cn.mldn.myrabbit.consumer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;

public class MessageConsumerDirect {
	private static final String EXCHANGE_NAME = "mldn.msg.direct"; // 定义Exchange的名称
	private static final String HOST = "192.168.109.197"; // 消息服务的主机
	private static final Integer PORT = 5672; // 消息服务的端口号

	public static void main(String[] args) throws Exception {
		ConnectionFactory factory = new ConnectionFactory(); // 建立一个连接工厂，所有的连接信息在此配置
		factory.setHost(HOST); // 设置消息的主机
		factory.setPort(PORT); // 设置连接端口
		factory.setUsername("mldnjava"); // 访问用户 
		factory.setPassword("hello"); // 连接密码 
		Connection connection = factory.newConnection(); // 定义一个新的RabbitMQ连接
		Channel channel = connection.createChannel(); // 创建一个通讯的通道
		channel.exchangeDeclare(EXCHANGE_NAME, "direct"); // 定义EXCHANGE的声明
		String queueName = channel.queueDeclare().getQueue(); // 通过通道获取一个队列名称
		channel.queueBind(queueName, EXCHANGE_NAME, "mldn-key"); // 进行绑定处理
		// 在RabbitMQ里面所有的消费者的信息是通过一个回调方法完成的
		Consumer consumer = new DefaultConsumer(channel) { // 需要覆写指定的方法实现消息消费处理
			public void handleDelivery(String consumerTag, com.rabbitmq.client.Envelope envelope,
					com.rabbitmq.client.AMQP.BasicProperties properties, byte[] body) throws java.io.IOException {
				String message = new String(body);
				System.out.println("*** 【WorkerA、消息消费者】" + message);
			};
		};
		channel.basicConsume(queueName, consumer); // 消息消费
	}
}
