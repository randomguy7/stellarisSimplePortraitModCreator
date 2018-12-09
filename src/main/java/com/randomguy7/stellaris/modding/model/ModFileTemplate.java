package com.randomguy7.stellaris.modding.model;

public class ModFileTemplate {

    private String name;
    private String folder;

    public ModFileTemplate(String name, String folder) {
        this.name = name;
        this.folder = folder;
    }

    public String modFileContent(){
        StringBuilder builder =  new StringBuilder();
        builder.append("name = \"").append(name).append(" Species\"").append(System.lineSeparator());
        builder.append("path = \"mod/").append(folder).append("\"").append(System.lineSeparator());
        builder.append("tags={\"Graphics\" \"Species\"}").append(System.lineSeparator());
        //latest version at the time of writing
        builder.append("supported_version=\"2.2.1\"").append(System.lineSeparator());
        return builder.toString();
    }

}
