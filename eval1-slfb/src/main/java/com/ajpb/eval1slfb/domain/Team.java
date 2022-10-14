package com.ajpb.eval1slfb.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Team  implements Serializable {

    private String teamName, teamSport;

    @Override
    public String toString() {
        return teamName+" ("+teamSport+")";
    }
}
