package com.example.block12kafka.service;

import com.example.block12kafka.entity.Customer;
import com.example.block12kafka.events.CustomerCreatedEvent;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerEventsService customerEventsService;

    public CustomerService(CustomerEventsService customerEventsService){
        super();
        this.customerEventsService = customerEventsService;
    }

    public Customer save(Customer customer){
        System.out.println("Recibido "+customer);
        this.customerEventsService.publish(customer);
        return customer;
    }

}
