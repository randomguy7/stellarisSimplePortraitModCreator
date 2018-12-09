package com.randomguy7.stellaris.modding.model.constants;

public enum Scope {
    GAMESETUP("game_setup"),
    SPECIES("species"),
    POP("pop"),
    LEADER("leader"),
    RULEER("ruler")
    ;
    private String scope;

    Scope(String scope) {
        this.scope = scope;
    }

    public String scope(){
        return scope;
    }
}
