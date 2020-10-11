package flashcards;

import java.util.Map;

public class Card {

    private String name;
    private String definition;

    public Card(String name, String definition) {
        this.name = name;
        this.definition = definition;
    }

    public String getName() {
        return name;
    }

    boolean ifDefRight (String definition){
        return this.definition.equals(definition);
    }

    public String checkDef(String definition, Map<String, String> cardsInfo){
        if (!ifDefRight(definition) && cardsInfo.containsValue(definition)){
            return "Wrong answer. The right answer is \"" + this.definition + "\", but your definition is correct for \""
                    + getKeyFromValue(cardsInfo, definition) + "\"\n";
        }
        else if (!ifDefRight(definition)){
            return "Wrong. The right answer is \"" + this.definition + "\"\n";
        }
        else{
            return "Correct!\n";
        }
    }
    public static Object getKeyFromValue(Map cardsInfo, Object value) {
        for (Object o : cardsInfo.keySet()) {
            if (cardsInfo.get(o).equals(value)) {
                return o;
            }
        }
        return null;
    }
}