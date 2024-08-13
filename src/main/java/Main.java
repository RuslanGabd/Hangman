import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException {
        String word;
        int index;
        Random random = new Random();
        ReadFile rd = new ReadFile();
        List<String> dictionary = rd.readFile("src/main/resources/words.txt");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Do you want to start game? y/n");
            String start = scanner.nextLine();
            if (start.equals("yes") || start.equals("y")) {
                index = random.nextInt(dictionary.size());
                word = dictionary.get(index);
                System.out.println("Guess the word");
                System.out.println("Start game!");
                playGame(word);
            } else if (start.equals("no") || start.equals("n")) {
                System.out.println("Goodbye!");
                break;
            }
        }
    }

    static void playGame(String word) {
        int count = 0;
        int wrongGuesses;
        char c;

        wrongGuesses = 0;
        boolean flag;
        char[] chars = word.toCharArray();
        Character[] hidden = new Character[word.length()];
        for (int i = 0; i < hidden.length; i++) {
            hidden[i] = '*';
        }
        for (char k : hidden) {
            System.out.print(k);
        }

        Scanner scanner = new Scanner(System.in);
        while (count < 6) {
            System.out.println("\nWrite letter:");
            try {
                String s = scanner.nextLine();
                flag = true;
                if (s.length() > 1) {
                    System.out.println("You must write only one letter!");
                    flag = false;
                }
                c = s.charAt(0);
                if (!Character.isLetter(c)) {
                    System.out.println("You must write letter!");
                    flag = false;
                }
                for (int i = 0; i < chars.length; i++) {
                    if (chars[i] == c) {
                        hidden[i] = Character.toUpperCase(c);
                        flag = false;
                    }
                }
                if (flag) {
                    count++;
                    wrongGuesses++;
                }
                for (char k : hidden) {
                    System.out.print(k);
                }
                if (checkHidden(hidden)) {
                    System.out.println("");
                    System.out.println("You win!");
                    break;
                }
                System.out.println();
                System.out.println("Mistakes " + count + ", you have " + (6 - count) + " attempts");
                drawGibbet(wrongGuesses);
            } catch (RuntimeException re) {
                System.out.print(re.getMessage());
            }
        }
    }

    static boolean checkHidden(Character[] array) {
        for (char k : array) {
            if (k == 42)
                return false;
        }
        return true;
    }

    public static void drawGibbet(int wrongGuesses) {
        if (wrongGuesses >= 1) {
            System.out.println(" _______");
            System.out.println(" |     |");
            System.out.println(" |     O");
        }
        if (wrongGuesses >= 2) {
            System.out.print(" |    /");
        }
        if (wrongGuesses >= 3) {
            System.out.print("|");
        }
        if (wrongGuesses >= 4) {
            System.out.println("\\");
        }
        if (wrongGuesses >= 5) {
            System.out.print(" |    /");
        }
        if (wrongGuesses >= 6) {
            System.out.println(" \\");
            System.out.println("_|_");
            System.out.println("You lose.");
        }
        System.out.println("");
    }
}
