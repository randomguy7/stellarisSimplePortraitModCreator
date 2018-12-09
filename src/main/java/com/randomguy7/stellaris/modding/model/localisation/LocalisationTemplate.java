package com.randomguy7.stellaris.modding.model.localisation;

public class LocalisationTemplate {
    private String name;
    private String plural;
    private String insult;
    private String insultPlural;
    private String compliment;
    private String complimentPlural;
    private String spawn;
    private String spawnplural;
    private String communicationSound;
    private String organ;
    private String mouth;

    public LocalisationTemplate(String name, String plural, String insult, String insultPlural, String compliment, String complimentPlural, String spawn, String spawnplural, String communicationSound, String organ, String mouth) {
        this.name = name;
        this.plural = plural;
        this.insult = insult;
        this.insultPlural = insultPlural;
        this.compliment = compliment;
        this.complimentPlural = complimentPlural;
        this.spawn = spawn;
        this.spawnplural = spawnplural;
        this.communicationSound = communicationSound;
        this.organ = organ;
        this.mouth = mouth;
    }

    public String getName() {
        return name;
    }

    public String localisationTemplateString() {
        StringBuilder builder = new StringBuilder();
        builder.append("l_english:").append(System.lineSeparator()).append(System.lineSeparator());

        builder.append("####################################").append(System.lineSeparator())
                .append("# Species Class").append(System.lineSeparator())
                .append("####################################").append(System.lineSeparator()).append(System.lineSeparator());

        appendLocalisation(builder, name, "", name);
        appendLocalisation(builder, name, "plural", plural);
        appendLocalisation(builder, name, "insult_01", insult);
        appendLocalisation(builder, name, "insult_plural_01", insultPlural);
        appendLocalisation(builder, name, "compliment_01", compliment);
        appendLocalisation(builder, name, "compliment_plural_01", complimentPlural);
        appendLocalisation(builder, name, "spawn", spawn);
        appendLocalisation(builder, name, "spawn_plural", spawnplural);
        appendLocalisation(builder, name, "sound_01", communicationSound);
        appendLocalisation(builder, name, "sound_02", communicationSound);
        appendLocalisation(builder, name, "sound_03", communicationSound);
        appendLocalisation(builder, name, "sound_04", communicationSound);
        appendLocalisation(builder, name, "sound_05", communicationSound);
        appendLocalisation(builder, name, "organ", organ);
        appendLocalisation(builder, name, "mouth", mouth);
        return builder.toString();
    }

    private void appendLocalisation(StringBuilder builder, String name, String localisation, String value) {
        builder.append(name).append(localisation.isEmpty()?"":"_").append(localisation).append(":0 ").append("\"").append(value).append("\"").append(System.lineSeparator());
    }
}
