package cn.mldn.myrabbit.consumer;

import java.io.IOException; 

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.AMQP.BasicProperties;

public class MessageConsumer {
	private static final String QUEUE_NAME = "mldn.msg.queue"; 
	private static final String HOST = "192.168.109.197";
	private static final Integer PORT = 5672; 

	public static void main(String[] args) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(HOST);
		factory.setPort(PORT); 
		factory.setUsername("mldnjava");
		factory.setPassword("hello");	
		Connection conn = factory.newConnection();	//取得一个新的RabbitMQ的连接
		Channel channel = conn.createChannel();		//创建一个新的通道
		channel.queueDeclare(QUEUE_NAME, true, false, true, null); 
		
		//在Rabbit中所有消费者的信息是用过一个回调方法完整的哦
		Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
					throws IOException { //需要复写指定的方法实现消息处理
				String message = new String(body);
				System.out.println("××××× 『消息消费者』 ×××××" + message); 
				// channel.basicAck(envelope.getDeliveryTag(), false); 	// 表示本消息通过一个消息的标签实现了应答处理
			};
		};
		channel.basicConsume(QUEUE_NAME, consumer);		//消息消费 
	}  
}
