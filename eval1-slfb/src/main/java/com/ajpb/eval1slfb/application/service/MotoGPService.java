package com.ajpb.eval1slfb.application.service;

import com.ajpb.eval1slfb.application.registry.AdapterService;
import com.ajpb.eval1slfb.domain.Team;
import org.springframework.stereotype.Service;

@Service("MotoGP")
public class MotoGPService implements AdapterService<Team> {
    @Override
    public String process(Team request) {
        String mensaje = "Servicio de MotoGP: "+request.toString();
        System.out.println(mensaje);
        return mensaje;
    }
}
