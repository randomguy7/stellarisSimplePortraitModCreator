package com.randomguy7.stellaris.modding.controller;

import com.randomguy7.stellaris.modding.model.ModFileTemplate;
import com.randomguy7.stellaris.modding.model.common.SpeciesClass;
import com.randomguy7.stellaris.modding.model.constants.Archetype;
import com.randomguy7.stellaris.modding.model.gfx.portraits.Portrait;
import com.randomguy7.stellaris.modding.model.gfx.portraits.PortraitConfig;
import com.randomguy7.stellaris.modding.model.gfx.portraits.PortraitGroup;
import com.randomguy7.stellaris.modding.model.gfx.portraits.ScopeObject;
import com.randomguy7.stellaris.modding.model.localisation.LocalisationTemplate;
import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

public class Controller {
    public TextField name;
    public TextField graphicalCulture;
    public TextField maleSound;
    public TextField femaleSound;
    public TextField imageSource;
    public TextField target;
    public TextArea tempOutput;

    private static final String IMAGE_NAME_TEMPLATE = "%s_%s_%s.dds";
    private static final String IMAGE_TARGET_PATH = "%s/gfx/models/portraits/%s/%s/%s";
    public TextField plural;
    public TextField insult;
    public TextField insultPlural;
    public TextField compliment;
    public TextField complimentPlural;
    public TextField spawn;
    public TextField spawnPlural;
    public TextField communicationSound;
    public TextField organ;
    public TextField mouth;

    public void runClick(ActionEvent event) {
        copyImagesToTarget();
        generatePortraitConfig();
        generateLocalisation();
        generateModFile();
    }

    private void copyImagesToTarget() {
        Collection<File> maleImages = FileUtils.listFiles(FileUtils.getFile(imageSource.getText() + "/m"), TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
        Collection<File> femaleImages = FileUtils.listFiles(FileUtils.getFile(imageSource.getText() + "/f"), TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);

        AtomicLong maleCounter = new AtomicLong(-1);
        maleImages.forEach(maleImage -> {
            try {
                FileUtils.copyFile(maleImage, new File(String.format(IMAGE_TARGET_PATH, target.getText(), name.getText(), "m", String.format(IMAGE_NAME_TEMPLATE, name.getText(), "m", maleCounter.incrementAndGet()))));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        AtomicLong femaleCounter = new AtomicLong(-1);
        femaleImages.forEach(maleImage -> {
            try {
                FileUtils.copyFile(maleImage, new File(String.format(IMAGE_TARGET_PATH, target.getText(), name.getText(), "f", String.format(IMAGE_NAME_TEMPLATE, name.getText(), "f", femaleCounter.incrementAndGet()))));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void generatePortraitConfig() {
        Collection<File> maleImages = FileUtils.listFiles(FileUtils.getFile(String.format(IMAGE_TARGET_PATH, target.getText(), name.getText(), "m", "")), TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
        Collection<File> femaleImages = FileUtils.listFiles(FileUtils.getFile(String.format(IMAGE_TARGET_PATH, target.getText(), name.getText(), "f", "")), TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);

        List<Portrait> malePortraits = convertToPortrait(maleImages, maleSound.getText());
        List<Portrait> femalePortraits = convertToPortrait(femaleImages, femaleSound.getText());

        Portrait defaultPortrait = Stream.concat(malePortraits.stream(), femalePortraits.stream()).findAny().get();

        ScopeObject scopeObject = new ScopeObject(malePortraits, femalePortraits);
        PortraitGroup portraitGroup = new PortraitGroup(name.getText(), defaultPortrait, scopeObject);
        PortraitConfig portraitConfig = new PortraitConfig(Collections.singletonList(portraitGroup));
        tempOutput.setText(portraitConfig.portraitConfigString());
        try {
            FileUtils.write(new File(target.getText() + "/gfx/portraits/portraits/" + name.getText() + ".txt"), tempOutput.getText(), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        generateSpeciesClass(portraitConfig.getPortraitGroups());
    }

    private void generateSpeciesClass(List<PortraitGroup> portraitGroups) {
        SpeciesClass speciesClass = new SpeciesClass(name.getText(), portraitGroups, graphicalCulture.getText(), Archetype.BIOLOGICAL, true);
        try {
            FileUtils.write(new File(target.getText() + "/common/species_classes/" + name.getText() + ".txt"), speciesClass.speciesClassString(), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void generateLocalisation() {
        LocalisationTemplate localisationTemplate = new LocalisationTemplate(name.getText(),
                plural.getText(),
                insult.getText(),
                insultPlural.getText(),
                compliment.getText(),
                complimentPlural.getText(),
                spawn.getText(), spawnPlural.getText(),
                communicationSound.getText(),
                organ.getText(),
                mouth.getText());

        try {
            FileUtils.write(new File(target.getText() + "/localisation/" + name.getText() + "_l_english.yml"), localisationTemplate.localisationTemplateString(), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void generateModFile() {
        final File targetFolder = new File(target.getText());
        ModFileTemplate modFileTemplate = new ModFileTemplate(name.getText(), targetFolder.getName());
        try {
            FileUtils.write(new File(targetFolder.getParent() + "/" + targetFolder.getName() + ".mod"), modFileTemplate.modFileContent(), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Portrait> convertToPortrait(Collection<File> images, String greeting) {
        List<Portrait> result = new ArrayList<>();
        images.forEach(image -> result.add(new Portrait(image.getName().replace(".dds", ""), greeting, image.getAbsolutePath().replace(target.getText() + File.separator, "").replace("\\", "/"))));
        return result;
    }

}
