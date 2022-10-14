package com.ajpb.eval1slfbp2.application.service;

import com.ajpb.eval1slfbp2.application.registry.AdapterService;
import com.ajpb.eval1slfbp2.domain.Team;
import org.springframework.stereotype.Service;

@Service("Basketball")
public class BasketService implements AdapterService<Team> {
    @Override
    public String process(Team request) {
        String mensaje = "Servicio de baloncesto: "+request.toString();
        System.out.println(mensaje);
        return mensaje;
    }
}
