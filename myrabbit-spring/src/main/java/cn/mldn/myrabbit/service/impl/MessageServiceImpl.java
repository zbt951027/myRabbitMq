package cn.mldn.myrabbit.service.impl;

import javax.annotation.Resource;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

import cn.mldn.myrabbit.service.IMessageService;

@Service
public class MessageServiceImpl implements IMessageService {
	@Resource
	private AmqpTemplate amqpTemplate;

	@Override
	public void send(String msg) {
		// 第一个参数是routingKey
		this.amqpTemplate.convertAndSend("mldn-key", msg);
	}

}
