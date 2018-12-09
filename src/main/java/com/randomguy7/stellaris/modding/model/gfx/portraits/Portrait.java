package com.randomguy7.stellaris.modding.model.gfx.portraits;

public class Portrait {
    private String identifier;
    private String greetingSound;
    private String texturefile;

    public Portrait(String identifier, String greetingSound, String texturefile) {
        this.identifier = identifier;
        this.greetingSound = greetingSound;
        this.texturefile = texturefile;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getGreetingSound() {
        return greetingSound;
    }

    public void setGreetingSound(String greetingSound) {
        this.greetingSound = greetingSound;
    }

    public String getTexturefile() {
        return texturefile;
    }

    public void setTexturefile(String texturefile) {
        this.texturefile = texturefile;
    }

    @Override
    public String toString() {
        return String.format("%s= {greeting_sound = \"%s\" texturefile = \"%s\"}", identifier, greetingSound, texturefile);
    }
}
