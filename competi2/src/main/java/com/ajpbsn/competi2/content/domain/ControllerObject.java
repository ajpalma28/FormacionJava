package com.ajpbsn.competi2.content.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.naming.ldap.Control;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class ControllerObject {

    private List<String> paths;
    private Map<String, String> querys;
    private Map<String, String> headers;
    private String urlOrigen;

    public ControllerObject(){
        this.paths = new ArrayList<>();
        this.querys = new HashMap<>();
        this.headers = new HashMap<>();
        this.urlOrigen = "";
    }

    public void addPath(String path){
        paths.add(path);
    }

    public void addQuerys(String r, String s){
        querys.put(r, s);
    }

    public void addHeaders(String r, String s){
        headers.put(r,s);
    }

}
