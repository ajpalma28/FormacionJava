package com.ajpbsn.block12kafkaB.events;

import com.ajpbsn.block12kafkaB.entity.Customer;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CustomerCreatedEvent extends Event<Customer> {

}
