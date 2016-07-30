package org.raescott.springintegrationexample

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

import javax.jms.Connection
import javax.jms.ConnectionFactory
import javax.jms.DeliveryMode
import javax.jms.Destination
import javax.jms.MessageProducer
import javax.jms.Session
import javax.jms.TextMessage

/**
 * @author Richard Scott Smith <scott.smith@isostech.com>
 */
@ContextConfiguration(loader = SpringApplicationContextLoader.class, classes = [App])
class MyJmsServiceJmsIT extends Specification {

	@Autowired
	@Qualifier("jmsConnectionFactory")
	ConnectionFactory jmsConnectionFactory

	String queueName = MyJmsService.MY_QUEUE
	Session session
	Destination destination
	MessageProducer producer

	def setup(){
		Connection conn = jmsConnectionFactory.createConnection()
		conn.start()
		session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE)
		destination = session.createQueue(queueName)
		this.producer = session.createProducer(destination)
		this.producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT)
	}

	def "Test Send and Receive of Message"() {
		given:
		TextMessage txtMessage = session.createTextMessage()
		txtMessage.setText("Scott Smith")

		when:
		producer.send(destination, txtMessage)

		sleep(3000) // wait 3 seconds
		then:
		true

	}
}