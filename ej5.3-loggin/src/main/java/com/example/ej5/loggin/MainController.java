package com.example.ej5.loggin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

@Controller
//@RestController
public class MainController {

    Logger logger = LoggerFactory.getLogger(MainController.class);

    //@RequestMapping("/")
    public String index() {
        logger.trace("A TRACE Message");
        logger.debug("A DEBUG Message");
        logger.info("An INFO Message");
        logger.warn("A WARN Message");
        logger.error("An ERROR Message");

        System.out.println("Howdy! Check out the Logs to see the output...");

        // TODO: Hacer un logback-spring.xml donde se indique cuales se leen, cuales se muestran
        // TODO: por pantalla y cuales no se hace nada con ellos

        return "Howdy! Check out the Logs to see the output...";
    }
}