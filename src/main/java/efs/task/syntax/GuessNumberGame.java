package efs.task.syntax;

import java.util.Scanner;

public class GuessNumberGame {
    int M;

    //Do not modify main method
    public static void main(String[] args) {
        try {
            GuessNumberGame game = new GuessNumberGame(args.length > 0 ? args[0] : "");
            game.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public GuessNumberGame(String argument) {
        //TODO: Implement the constructor
        try {
            M = Integer.parseInt(argument);
        } catch (NumberFormatException e) {
            System.out.println("'"+argument+"' to "+UsefulConstants.WRONG_ARGUMENT+" - to nie liczba");
            throw new java.lang.IllegalArgumentException(e);
        }

        if (M < 1 || M > UsefulConstants.MAX_UPPER_BOUND) {
            System.out.println(M+" to "+UsefulConstants.WRONG_ARGUMENT+" - jest spoza zakresu <1,400>");
            throw new java.lang.IllegalArgumentException();
        }
    }


    public void play() {
        //TODO: Implement the method that executes the game session
        System.out.println("<1," + M + ">");

        int L = (int) Math.abs(Math.floor((Math.log10(M) / Math.log10(2)) + 1));
        int current_attempt = 1;
        int number = (int) ((Math.random()*M)+1);
        boolean cont_game=true;
        for(int i=0; i<L; i++){

            System.out.print("Twoje próby: [");
            for (int j = 0; j < current_attempt; j++) {
                System.out.print("*");
            }
            for (int k = current_attempt; k < L; k++) {
                System.out.print(".");
            }
            System.out.println("]");

            System.out.println(UsefulConstants.GIVE_ME);

            Scanner input = new Scanner(System.in);
            String in_number = input.nextLine();
            int users_number;
            try {
                users_number=Integer.parseInt(in_number);
            } catch (NumberFormatException e) {
                current_attempt++;
                System.out.println("'"+in_number+"' to "+UsefulConstants.NOT_A_NUMBER+" - to nie liczba");
                continue;
            }
            if(users_number>number) {
                System.out.println(UsefulConstants.TO_MUCH);
                current_attempt++;
            }
            else if(users_number<number){
                System.out.println(UsefulConstants.TO_LESS);
                current_attempt++;
            }
            else {
                System.out.println(UsefulConstants.YES);
                current_attempt++;
                cont_game=false;
                System.out.println(UsefulConstants.CONGRATULATIONS+", "+current_attempt+" - tyle prób zajęło Ci odgadnięcie liczby "+number);
                break;
            }
        }
        if (cont_game) {
            System.out.println(UsefulConstants.UNFORTUNATELY+", wyczerpałeś limit prób ("+L+") do odgadnięcia liczby "+number);
        }

    }
}
