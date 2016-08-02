package org.raescott.springintegrationexample;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Richard Scott Smith <scott.smith@isostech.com>
 */
@SpringBootApplication
@ImportResource("classpath*:/integration-config.xml")
public class App {

	public static void main(String[] args) throws JMSException, InterruptedException {
		ApplicationContext applicationContext = SpringApplication.run(App.class, args);

		// Convert to the above to use Spring Boot again.
		BeanFactory beanFactory = applicationContext;
		ConnectionFactory jmsConnectionFactory = (ConnectionFactory) beanFactory.getBean("jmsConnectionFactory");

		String queueName = MyJmsService.MY_QUEUE; // JMS Queue name
		Session session; // JMS Session
		Destination destinationQueue; // JMS Queue itself
		MessageProducer producer; // JMS Producer

		// Create the queue objects.
		Connection conn = jmsConnectionFactory.createConnection();
		conn.start();
		session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
		destinationQueue = session.createQueue(queueName);
		producer = session.createProducer(destinationQueue);
		producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

		// This is the JMS object message
		TextMessage textMessageToSend1 = session.createTextMessage();
		textMessageToSend1.setText("Scott Smith");

		// Send to the JMS Queue
		producer.send(destinationQueue, textMessageToSend1);

		Thread.sleep(3000); // wait 3 seconds

		// Another JMS object message
		TextMessage textMessageToSend2 = session.createTextMessage();
		textMessageToSend2.setText("Another test message");
		producer.send(destinationQueue, textMessageToSend2);

		// None of this matters for this.
		producer.close();
		session.close();
		conn.close();
		System.exit(0);
	}
}