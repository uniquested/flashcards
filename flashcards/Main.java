package flashcards;

import java.io.*;
import java.util.*;

public class Main {
    private static String name, definition;
    static ArrayList<Card> cards = new ArrayList<>();
    static Map<String, String> cardsInfo = new LinkedHashMap<>();
    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        menuInterface(scanner);
    }

    public static void menuInterface(Scanner scanner){

        while (true){
            System.out.println("\nInput the action (add, remove, import, export, ask, exit): ");
            String option = scanner.nextLine();

            switch (option) {
                case "add":
                    addCard(cardsInfo, cards, scanner);
                    break;
                case "remove":
                    removeCard(cardsInfo, cards, scanner);
                    break;
                case "import":
                    importCards(cardsInfo, cards, scanner);
                    break;
                case "export":
                    exportCards(cardsInfo, cards, scanner);
                    break;
                case "ask":
                    askCard(cardsInfo, cards, scanner);
                    break;
                case "exit":
                    System.out.println("Bye bye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Wrong action! Try again.");
        }
    }}
    static void addCard(Map<String, String> info, ArrayList<Card> cards, Scanner scanner) {

            while(true) {
                System.out.println("The card");
                name = scanner.nextLine();
                if (info.containsKey(name)) {
                    System.out.println("The card \"" + name + "\" already exists.");
                    break;
                }

                System.out.println("The definition of the card");
                definition = scanner.nextLine();
                if (info.containsValue(definition)) {
                    System.out.println("The definition \"" + definition + "\" already exists.");
                    break;
                }


                cards.add(new Card(name, definition));
                info.put(name, definition);
                System.out.println("The pair (\"" + name + "\":\"" + definition + "\") has been added.");
                break;

            }
}
    static void removeCard(Map<String, String> info, ArrayList<Card> cards, Scanner scanner){
        System.out.println("The card");

            name = scanner.nextLine();
            if (info.containsKey(name)) {
                info.remove(name);
                cards.remove(new Card(name, info.get(name)));
                System.out.println("The card has been removed.");
            }
            else {
                System.out.println("Can't remove \"" + name + "\": There is no such card.");
            }
    }

    static void askCard(Map<String, String> info, ArrayList<Card> cards, Scanner scanner){
        System.out.println("How many times to ask?");
        String ans;
        Random random = new Random();
        int i = 0;
        int rand;
        int askTimes = scanner.nextInt();
        String leftovers = scanner.nextLine();

        while (i < askTimes){
            rand = random.nextInt(cards.size());
            System.out.println("Print the definition of \"" + cards.get(rand).getName() + "\"");
            ans = scanner.nextLine();
            System.out.print(cards.get(rand).checkDef(ans, info));
            i++;
        }
    }

    static void exportCards(Map<String, String> info, ArrayList<Card> cards, Scanner scanner) {
        int counter = 0;
        System.out.println("File name: ");
        String path = scanner.next();
        String leftovers = scanner.nextLine();
        File file = new File("./flashcards/" + path);
        try (Scanner sc = new Scanner(file)) {
            FileWriter writer = new FileWriter(file);
            for (Card card : cards) {
                writer.write(card.getName() + "\n");
                writer.write(info.get(card.getName()) + "\n");
                counter++;
            }
            writer.close();
            }
            catch (IOException e)
            {
                //System.out.println("File not found.");
                //System.out.println(counter + " cards have been saved");

            }
        System.out.println(counter + " cards have been saved");

}

    static void importCards(Map<String, String> info, ArrayList<Card> cards, Scanner scanner){
        int counter = 0;
        System.out.println("File name: ");
        String path = scanner.next();
        String leftovers = scanner.nextLine();
        File file = new File("./flashcards/" + path);
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNext()){
                name = sc.nextLine();
                definition = sc.nextLine();
                cards.removeIf(card -> card.getName().contains(name));
                info.remove(name);
                cards.add(new Card(name, definition));
                info.put(name, definition);
                counter++;
            }
            System.out.println(counter +" cards have been loaded.");

        } catch (IOException e){
            System.out.println("File not found.");
        }

    }
}





