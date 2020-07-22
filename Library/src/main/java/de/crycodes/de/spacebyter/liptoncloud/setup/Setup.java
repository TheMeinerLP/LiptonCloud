package de.crycodes.de.spacebyter.liptoncloud.setup;

import de.crycodes.de.spacebyter.liptoncloud.console.CloudConsole;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public abstract class Setup {

    private Map<Field, SetupPart> setupParts = new HashMap<>();
    private int current = 43084380;

    private Map.Entry<Field, SetupPart> currentPart;

    public void start(CloudConsole scanner) {
        current = 1;

        for (Field field : this.getClass().getDeclaredFields()) {
            if (field.getAnnotation(SetupPart.class) != null) {
                setupParts.put(field, field.getAnnotation(SetupPart.class));
            }
        }

        this.currentPart = this.getEntry(1);
        System.out.println(this.currentPart.getValue().question() + " ("+this.currentPart.getKey().getType().getSimpleName()+")");

        while (current < (setupParts.size() + 1)) {
            String line = scanner.getLogger().readLine();
            next(line);
        }

    }

    public boolean hasNext() {
        return setupParts.size() > current;
    }

    public void next(String lastAnswer) {

        if (currentPart != null) {
            if (isAnswerForbidden(this.currentPart.getValue(), lastAnswer)) {
                System.out.print("The answer '" + lastAnswer + "' is not usable for setup! Please enter a valid value!");
                return;
            }

            this.currentPart.getKey().setAccessible(true);

            try {
                Object value = parse(this.currentPart.getKey(), lastAnswer);
                if (value == null) {
                    System.out.println("Cannot parse entry. Please Retry");
                    return;
                }
                this.currentPart.getKey().set(this, value);
            } catch (Exception ex) {
                System.out.println("Please enter a valid Entry");
                return;
            }

        }

        current++;

        this.currentPart = this.getEntry(current);
        if (this.currentPart != null)
            System.out.println(this.currentPart.getValue().question() + " ("+this.currentPart.getKey().getType().getSimpleName()+")");

    }

    public Object parse(Field field, String s) {

        try {
            if (field.getType() == Integer.class || field.getType() == int.class)
                return Integer.parseInt(s);
            else if (field.getType() == Double.class || field.getType() == double.class)
                return Double.parseDouble(s);
            else if(field.getType() == Boolean.class || field.getType() == boolean.class)
                return Boolean.parseBoolean(s);
            else if(field.getType() == Byte.class || field.getType() == byte.class)
                return Byte.parseByte(s);
            else if(field.getType() == Long.class || field.getType() == long.class)
                return Long.parseLong(s);
            else if (field.getType() == String.class)
                return s;
        } catch (Exception ignored) {

        }

        return null;
    }

    public boolean isAnswerForbidden(SetupPart setupPart, String answer) {
        if (setupPart.forbiddenAnswers().length > 0) {
            for (String forbiddenAnswer : setupPart.forbiddenAnswers()) {
                if (forbiddenAnswer.equalsIgnoreCase(answer))
                    return true;
            }
        }
        return false;
    }

    public Map.Entry<Field, SetupPart> getEntry(int id) {
        Map.Entry<Field, SetupPart> entry = null;
        for (Map.Entry<Field, SetupPart> currentEntry : setupParts.entrySet()) {
            if (currentEntry.getValue().id() == id)
                entry = currentEntry;
        }
        return entry;
    }

    public Map<Field, SetupPart> getSetupParts() {
        return new HashMap<>(setupParts);
    }

}
