package de.crycodes.addon.signsystem.setup;

import de.crycodes.addon.signsystem.objects.SavedSignLayoutObject;
import de.crycodes.addon.signsystem.objects.SignLayout;
import de.crycodes.de.spacebyter.liptoncloud.setup.Setup;
import de.crycodes.de.spacebyter.liptoncloud.setup.SetupPart;

/**
 * Coded By CryCodes
 * Class: SignLayoutSetup
 * Date : 25.06.2020
 * Time : 18:04
 * Project: LiptonCloud
 */

public class SignLayoutSetup extends Setup {

    @SetupPart(id = 1, question = "What should the sign-id be ?", forbiddenAnswers = {"0"})
    private Integer id;

    @SetupPart(id = 2, question = "What should the 1. Line be ?", forbiddenAnswers = {"null", " "})
    private String line_1;

    @SetupPart(id = 3, question = "What should the 2. Line be ?", forbiddenAnswers = {"null", " "})
    private String line_2;

    @SetupPart(id = 4, question = "What should the 3. Line be ?", forbiddenAnswers = {"null", " "})
    private String line_3;

    @SetupPart(id = 5, question = "What should the 4. Line be ?", forbiddenAnswers = {"null", " "})
    private String line_4;

    public SavedSignLayoutObject getLayout(){
        return new SavedSignLayoutObject(id, new SignLayout(line_1,line_2,line_3,line_4));
    }

}
