package com.ajpbsn.block12kafkaB.service;

import com.ajpbsn.block12kafkaB.events.CustomerCreatedEvent;
import com.ajpbsn.block12kafkaB.events.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomerEventsService {

    @KafkaListener(
            topics = "${topic.customer.name:customers}",
            containerFactory = "kafkaListenerContainerFactory",
            groupId = "grupo1")
    public void consumer(Event<?> event){
        if(event.getClass().isAssignableFrom(CustomerCreatedEvent.class)) {
            CustomerCreatedEvent customerCreatedEvent = (CustomerCreatedEvent) event;
            log.info("Recibido Customer created event ....  with Id={}, data={}",
                    customerCreatedEvent.getId(),
                    customerCreatedEvent.getData().toString());
        }

    }

}
