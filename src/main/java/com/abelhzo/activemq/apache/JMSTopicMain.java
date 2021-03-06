package com.abelhzo.activemq.apache;

import java.util.Arrays;

import org.apache.activemq.ActiveMQConnectionFactory;

public class JMSTopicMain {

	public static void main(String[] args) throws InterruptedException {
		
		/**
		 * Para las clases de JMS se utiliza la dependencia en el maven:
		 * 
		 * 	<dependency>
		 *		<groupId>org.apache.activemq</groupId>
		 *		<artifactId>activemq-all</artifactId>
		 *		<version>5.15.4</version>
		 *	</dependency>
		 *
		 * Que es la sugerida por la pagina de ActiveMQ
		 */
		
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
		connectionFactory.setTrustedPackages(Arrays.asList("java.lang,java.util,com.abelhzo.activemq.dto".split(",")));
	
		Thread threadTopicConsumer = new Thread(new JMSTopicConsumer(connectionFactory));
		threadTopicConsumer.start();
		
		/**
		 * Comentar y descomentar para enviar un xml o un objecto.
		 */
		String typeSend = "XML";
//		String typeSend = "OBJ";
		JMSTopicProducer producerTopic = new JMSTopicProducer(connectionFactory);
		for(int i = 1; i <= 25; i++) {
			producerTopic.sendTopic(i, typeSend);
			Thread.sleep(1000);
		}
		
	}

}
