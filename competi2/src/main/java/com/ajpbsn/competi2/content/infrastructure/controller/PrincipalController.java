package com.ajpbsn.competi2.content.infrastructure.controller;

import com.ajpbsn.competi2.content.domain.ControllerObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@RestController
public class PrincipalController {

    // Los REDIRIGE se podrían hacer con filtros, en lugar de con el sendRedirect
    // Queda pendiente para rehacerlo

    @GetMapping("**")
    public ControllerObject entryOther(HttpServletRequest httpServletRequest, HttpServletResponse response){
        httpServletRequest.getHeaderNames().asIterator().forEachRemaining(x-> {
            if(x.equals("redirige")){
                try {
                    response.sendRedirect("/salta");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        ControllerObject controller = new ControllerObject();

        String path = httpServletRequest.getServletPath();
        String[] separa = path.split("/");
        Arrays.stream(separa).filter(x-> !x.equals("")).forEach(controller::addPath);

        httpServletRequest.getHeaderNames().asIterator().forEachRemaining(x->{
            controller.addHeaders(x,httpServletRequest.getHeader(x));
        });

        controller.setUrlOrigen(httpServletRequest.getRemoteAddr());
        httpServletRequest.getParameterNames().asIterator().forEachRemaining(x->{
            controller.addQuerys(x,httpServletRequest.getParameterValues(x)[0]);
        });

        return controller;

    }

    @GetMapping(value = {"/", "/one"})
    public String entryOne(HttpServletRequest httpServletRequest, HttpServletResponse response){
        httpServletRequest.getHeaderNames().asIterator().forEachRemaining(x-> {
            if(x.equals("redirige")){
                try {
                    response.sendRedirect("/salta");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        return "ENHORABUENA! Has entrado en entryOne";
    }

    @GetMapping("/salta")
    public String entryJump(){
        return "He llegado a Jump, BIEN HECHO!";
    }


}
