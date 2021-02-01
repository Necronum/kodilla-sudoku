package sudoku;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IOService {
    private static final Scanner scanner = new Scanner(System.in);

    public IOService() {
    }

    public static List<String> getInput(){
        List<String> listOfInput = new ArrayList<>();
        System.out.println("Please insert Your input in format \"xyz\" example 123");
        listOfInput.add(numberCheck(scanner.nextLine()));
        return nextInput(listOfInput);
    }

    public static List<String> nextInput(List<String> listOfInput){
        System.out.println("Please insert Your next input in format \"xyz\" example 123. If not press \"n\".");
        String input = scanner.nextLine();
        while(!input.equals("n")){
            listOfInput.add(numberCheck(input));
            System.out.println("Please insert Your next input in format \"xyz\" example 123. If not press \"n\".");
            input = scanner.nextLine();
        }
        return listOfInput;
    }

    public static String numberCheck(String text){
        while (!text.matches("-?[1-9]{3}")) {
            System.out.println("Wrong input - only numbers from 1 to 9");
            text = scanner.nextLine();
        }
        return text;
    }
}
