package com.ajpb.eval1slfbp2.infrastructure.controller;

import com.ajpb.eval1slfbp2.application.registry.ServiceRegistry;
import com.ajpb.eval1slfbp2.domain.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeamController {

    @Autowired
    ServiceRegistry serviceRegistry;

    @PostMapping("/team")
    public String metodoPrueba(@RequestBody Team team){
        return serviceRegistry.getService(team.getTeamSport()).process(team);
    }

}
