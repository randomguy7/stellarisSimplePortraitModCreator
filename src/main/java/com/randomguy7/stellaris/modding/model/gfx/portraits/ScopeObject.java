package com.randomguy7.stellaris.modding.model.gfx.portraits;

import com.randomguy7.stellaris.modding.model.constants.TriggerGender;

import java.util.ArrayList;
import java.util.List;

public class ScopeObject {
    private List<Portrait> malePortraits;
    private List<Portrait> femalePortraits;

    public ScopeObject(List<Portrait> malePortraits, List<Portrait> femalePortraits) {
        this.malePortraits = malePortraits;
        this.femalePortraits = femalePortraits;
    }

    public List<Portrait> getMalePortraits() {
        return malePortraits;
    }

    public void setMalePortraits(List<Portrait> malePortraits) {
        this.malePortraits = malePortraits;
    }

    public List<Portrait> getFemalePortraits() {
        return femalePortraits;
    }

    public void setFemalePortraits(List<Portrait> femalePortraits) {
        this.femalePortraits = femalePortraits;
    }

    public String genderedString(boolean isGameStartScope) {
        StringBuilder builder = new StringBuilder();
        //Male
        builder.append("add = {").append(System.lineSeparator());
        builder.append(isGameStartScope ? TriggerGender.MALE.genderRulerTrigger() : TriggerGender.MALE.genderTrigger());
        appendPortraits(builder, malePortraits);
        builder.append("}").append(System.lineSeparator());
        //Female
        builder.append("add = {").append(System.lineSeparator());
        builder.append(isGameStartScope ? TriggerGender.FEMALE.genderRulerTrigger() : TriggerGender.FEMALE.genderTrigger());
        appendPortraits(builder, femalePortraits);
        builder.append("}").append(System.lineSeparator());
        return builder.toString();
    }

    public String unGenderedString(boolean isGameStartScope) {
        List<Portrait> allPortraits = new ArrayList<>(malePortraits);
        allPortraits.addAll(femalePortraits);
        StringBuilder builder = new StringBuilder();
        builder.append("add = {").append(System.lineSeparator());
        appendPortraits(builder, allPortraits);
        builder.append("}").append(System.lineSeparator());
        return builder.toString();
    }


        private StringBuilder appendPortraits(StringBuilder builder, List<Portrait> poratraits) {
        builder.append("portraits = {").append(System.lineSeparator());
        poratraits.forEach(portrait -> builder.append(portrait.getIdentifier()).append(System.lineSeparator()));
        builder.append("}").append(System.lineSeparator());
        return builder;
    }
}
