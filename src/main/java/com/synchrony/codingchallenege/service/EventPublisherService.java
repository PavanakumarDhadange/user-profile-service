package com.synchrony.codingchallenege.service;

import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EventPublisherService {

	@Value("${spring.kafka.topic.name}")
	private String topicName;
	
	@Autowired
	private KafkaTemplate<String, String> eventProducer;
	
	final Logger LOG = Logger.getLogger(EventPublisherService.class.getName());
	
	public void publishEvent(String userId, String imageName) {
		try {
			eventProducer.send(new ProducerRecord<String, String>(topicName, userId, imageName)).get();
		} catch (InterruptedException | ExecutionException e) {
			LOG.log(Level.SEVERE, "Image upload event failed to publish..", e);
		}
	}
	
}
