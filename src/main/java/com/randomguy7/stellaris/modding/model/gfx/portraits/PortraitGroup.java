package com.randomguy7.stellaris.modding.model.gfx.portraits;

import com.randomguy7.stellaris.modding.model.constants.Scope;

public class PortraitGroup {
    private String name;
    private Portrait defaultPortrait;
    private ScopeObject scopeObject;

    public PortraitGroup(String name, Portrait defaultPortrait, ScopeObject scopeObject) {
        this.name = name;
        this.defaultPortrait = defaultPortrait;
        this.scopeObject = scopeObject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Portrait getDefaultPortrait() {
        return defaultPortrait;
    }

    public void setDefaultPortrait(Portrait defaultPortrait) {
        this.defaultPortrait = defaultPortrait;
    }

    public ScopeObject getScopeObject() {
        return scopeObject;
    }

    public void setScopeObject(ScopeObject scopeObject) {
        this.scopeObject = scopeObject;
    }

    public String groupString() {
        StringBuilder builder = new StringBuilder();
        builder.append(name).append(" = {").append(System.lineSeparator());
        builder.append("default = ").append(defaultPortrait.getIdentifier()).append(System.lineSeparator());
        for (Scope scope : Scope.values()) {
            appendScope(builder, true, scope);
        }
        builder.append("}").append(System.lineSeparator());
        return builder.toString();
    }

    private StringBuilder appendScope(StringBuilder builder, boolean gendered, Scope scope) {
        if (scope == Scope.SPECIES) {
            gendered = false;
        }
        builder.append(scope.scope()).append(" = {").append(System.lineSeparator());
        boolean isGameStartScope = scope == Scope.GAMESETUP;
        builder.append(gendered ? scopeObject.genderedString(isGameStartScope) : scopeObject.unGenderedString(isGameStartScope)).append(System.lineSeparator());
        builder.append("}").append(System.lineSeparator());
        return builder;
    }
}
