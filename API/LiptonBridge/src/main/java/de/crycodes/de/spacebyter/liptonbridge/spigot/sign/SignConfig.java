package de.crycodes.de.spacebyter.liptonbridge.spigot.sign;

import de.crycodes.de.spacebyter.liptonbridge.spigot.objects.CloudSign;
import de.crycodes.de.spacebyter.liptoncloud.config.Document;

import javax.print.Doc;
import java.io.File;
import java.util.*;

/**
 * Coded By CryCodes
 * Class: SignConfig
 * Date : 20.07.2020
 * Time : 15:21
 * Project: LiptonCloud
 */

public class SignConfig {

    private File signFile = new File("./../../../../resources/signs.json");
    private Document document;

    public SignConfig() {
        if(!(signFile.exists())) {
            document = new Document();
            document.saveAsConfig(signFile);
        }
        document = Document.loadDocument(signFile);
    }

    private Document getSignDocumentAfterGroup(String group){
        this.document = Document.loadDocument(signFile);
        if (!document.contains(group.toUpperCase()))
            return null;

        return document.getDocument(group.toUpperCase());
    }
    public HashMap<Integer, CloudSign> getSignsAfterGroup(String group){
        if (getSignDocumentAfterGroup(group.toUpperCase()) == null) {
            System.out.println("GROUP NOT FOUND");
            return new HashMap<Integer, CloudSign>();
        }

        final Document document = getSignDocumentAfterGroup(group.toUpperCase());

        assert document != null;

        if (document.getObject("signs", HashMap.class) == null )
            return new HashMap<Integer, CloudSign>();

        return document.getObject("signs", HashMap.class);
    }

    public void insertUpdatedSigns(Map<Integer, CloudSign> map, String group){
        final Document currentDoc = getSignDocumentAfterGroup(group.toUpperCase());

        if (getSignDocumentAfterGroup(group.toUpperCase()) == null) {
            this.document = Document.loadDocument(signFile);
            final Document signDoc = new Document();
            signDoc.append("signs", map);
            document.append(group, signDoc);
            document.saveAsConfig(this.signFile);
            return;
        }

        currentDoc.append("signs", map);

        this.document = Document.loadDocument(signFile);
        document.append(group, currentDoc);
        document.saveAsConfig(signFile);
    }
}
