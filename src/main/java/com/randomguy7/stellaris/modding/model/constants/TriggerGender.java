package com.randomguy7.stellaris.modding.model.constants;

public enum TriggerGender {
    MALE("male"),
    FEMALE("female");
    private String gender;

    TriggerGender(String gender) {
        this.gender = gender;
    }

    public String genderTrigger() {
        return String.format("trigger = { gender = %s }", gender);
    }

    public String genderRulerTrigger(){
        return String.format("trigger = { ruler = { gender = %s } }", gender);
    }
}
