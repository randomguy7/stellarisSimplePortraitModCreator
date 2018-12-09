package com.randomguy7.stellaris.modding.model.common;

import com.randomguy7.stellaris.modding.model.constants.Archetype;
import com.randomguy7.stellaris.modding.model.gfx.portraits.PortraitGroup;

import java.util.List;

public class SpeciesClass {
    private String name;
    private List<PortraitGroup> portraitGroups;
    private String graphicalCulture;
    private Archetype archetype;
    private boolean gendered;

    public SpeciesClass(String name, List<PortraitGroup> portraitGroups, String graphicalCulture, Archetype archetype, boolean gendered) {
        this.name = name;
        this.portraitGroups = portraitGroups;
        this.graphicalCulture = graphicalCulture;
        this.archetype = archetype;
        this.gendered = gendered;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PortraitGroup> getPortraitGroups() {
        return portraitGroups;
    }

    public void setPortraitGroups(List<PortraitGroup> portraitGroups) {
        this.portraitGroups = portraitGroups;
    }

    public String getGraphicalCulture() {
        return graphicalCulture;
    }

    public void setGraphicalCulture(String graphicalCulture) {
        this.graphicalCulture = graphicalCulture;
    }

    public Archetype getArchetype() {
        return archetype;
    }

    public void setArchetype(Archetype archetype) {
        this.archetype = archetype;
    }

    public boolean isGendered() {
        return gendered;
    }

    public void setGendered(boolean gendered) {
        this.gendered = gendered;
    }

    public String speciesClassString() {
        StringBuilder builder = new StringBuilder();
        builder.append(name).append(" = {").append(System.lineSeparator());
        builder.append("archetype = ").append(archetype.name()).append(System.lineSeparator());
        builder.append("portraits = {").append(System.lineSeparator());
        portraitGroups.forEach(portraitGroup -> builder.append("\"").append(portraitGroup.getName()).append("\"").append(System.lineSeparator()));
        builder.append("}").append(System.lineSeparator());
        builder.append("graphical_culture = ").append(graphicalCulture).append(System.lineSeparator());
        builder.append("move_pop_sound_effect = \"moving_pop_confirmation\"").append(System.lineSeparator());
        if (!gendered) {
            builder.append("gender = no").append(System.lineSeparator());
        }
        builder.append("}").append(System.lineSeparator());
        return builder.toString();
    }
}
