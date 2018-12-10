package com.randomguy7.stellaris.modding.model.gfx.portraits;

import java.util.ArrayList;
import java.util.List;

public class PortraitConfig {
    private List<PortraitGroup> portraitGroups;
    private boolean gendered;

    public PortraitConfig(List<PortraitGroup> portraitGroups, boolean gendered) {
        this.portraitGroups = portraitGroups;
        this.gendered = gendered;
    }

    public List<PortraitGroup> getPortraitGroups() {
        return portraitGroups;
    }

    public void setPortraitGroups(List<PortraitGroup> portraitGroups) {
        this.portraitGroups = portraitGroups;
    }

    public String portraitConfigString() {
        StringBuilder builder = new StringBuilder();
        appendPortraits(builder, portraitGroups);
        appendPortraitGroups(builder, portraitGroups);
        return builder.toString();
    }

    private StringBuilder appendPortraitGroups(StringBuilder builder, List<PortraitGroup> portraitGroups) {
        builder.append("portrait_groups = {").append(System.lineSeparator());
        portraitGroups.forEach(portraitGroup -> builder.append(portraitGroup.groupString(gendered)));
        builder.append("}").append(System.lineSeparator());
        return builder;
    }

    private StringBuilder appendPortraits(StringBuilder builder, List<PortraitGroup> portraitGroups) {
        List<Portrait> portraits = new ArrayList<>();
        portraitGroups.forEach(portraitGroup -> {
            portraits.addAll(portraitGroup.getScopeObject().getFemalePortraits());
            portraits.addAll(portraitGroup.getScopeObject().getMalePortraits());
        });
        builder.append("portraits = {").append(System.lineSeparator());
        portraits.forEach(portrait -> {
            builder.append(portrait.toString());
            builder.append(System.lineSeparator());
        });
        builder.append("}").append(System.lineSeparator());
        return builder;
    }
}
