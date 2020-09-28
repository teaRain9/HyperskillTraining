package tictactoe;


import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        boolean gameNotFinished = true;
        String input = "_________";
        int turn = 1;
        cubeFormat(input);
        while (gameNotFinished) {
            input = nextMove(input, turn);
            cubeFormat(input);
            int result = analize(input);
            if (result != 1) {
                switch (result) {
                    case 2 :
                        System.out.println("X wins");
                        break;
                    case 3 :
                        System.out.println("O wins");
                        break;
                    case 4 :
                        System.out.println("Draw");
                        break;
                    case 5 :
                        System.out.println("impossible");
                        break;
                }
                gameNotFinished = false;
            }
            turn = turn * (-1);
        }
    }

    public static String readInput() {
        Scanner scanner = new Scanner(System.in);
        String input;
        int a = 0;
        while (true) {
            input = scanner.nextLine().toLowerCase();
            for (int i = 0; i < input.length(); i++ ) {
                if (input.charAt(i) == 'x' || input.charAt(i) == 'o' || input.charAt(i) == '_') {
                    a++;
                }else {
                    a = 0;
                    break;
                }
            }
            if (a != input.length() || input.length()!=9) {
                System.out.println("Please input 9 character '_', 'X', 'O'.");
            }else{
                break;
            }

        }
        return input.toUpperCase();
    }

    public static void cubeFormat(String notFormat) {
        notFormat = notFormat.replace("", " ");

        System.out.println("---------");
        System.out.println("|" + notFormat.substring(0,6) + " |");
        System.out.println("|" + notFormat.substring(6,12) + " |");
        System.out.println("|" + notFormat.substring(12,18) + " |");
        System.out.println("---------");
    }

    public static int analize(String strIn) {
        int code = 0;
        int[] cells = new int[9];
        for (int i = 0; i < 9; i++) {
            cells[i] = strIn.charAt(i);
        }

        short xCounter = 0;
        short oCounter = 0;
        short uCounter = 0;
        for (int cell : cells) {
            if (cell == 88) {
                xCounter++;
            } else if (cell == 79) {
                oCounter++;
            } else {
                uCounter++;
            }
        }

        int c1 = cells[0] + cells[1] + cells[2];
        int c2 = cells[3] + cells[4] + cells[5];
        int c3 = cells[6] + cells[7] + cells[8];
        int c4 = cells[0] + cells[3] + cells[6];
        int c5 = cells[1] + cells[4] + cells[7];
        int c6 = cells[2] + cells[5] + cells[8];
        int c7 = cells[0] + cells[4] + cells[8];
        int c8 = cells[2] + cells[4] + cells[6];

        int[] conditions = {c1, c2, c3, c4, c5, c6, c7, c8};

        boolean x = false;
        boolean o = false;

        for (int condition : conditions) {
            if (condition == 3 * 88) {
                x = true;
            } else if (condition == 3 * 79) {
                o = true;
            }
        }

        if (Math.abs(xCounter - oCounter) >= 2) {
            code = 5;
        } else {
            if (x && o) {
                code = 5;
            } else if (x) {
                code = 2;
            } else if (o) {
                code = 3;
            } else {
                if (uCounter == 0) {
                    code = 4;
                } else {
                    code = 1;
                }
            }
        }
        return code;
    }

    public static String nextMove (String str, int turn) {
        Scanner scanner = new Scanner(System.in);
        byte cordinate = 0;
        char[] arr = new char[str.length()];
        StringBuilder strOut = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            arr[i] = str.charAt(i);
        }
        byte x;
        byte y;
        while (true) {
            System.out.print("Enter the coordinates: ");
            try {
                x = scanner.nextByte();
                y = scanner.nextByte();

                if (x <= 3 && x > 0 && y <= 3 & y > 0) {
                    if (x == 1) {
                        if (y == 1) cordinate = 6;
                        if (y == 2) cordinate = 3;
                        if (y == 3) cordinate = 0;
                    } else if (x == 2) {
                        if (y == 1) cordinate = 7;
                        if (y == 2) cordinate = 4;
                        if (y == 3) cordinate = 1;
                    } else {
                        if (y == 1) cordinate = 8;
                        if (y == 2) cordinate = 5;
                        if (y == 3) cordinate = 2;
                    }

                    if (arr[cordinate] == '_') {
                        if (turn == 1){
                            arr[cordinate] = 'X';
                        } else {
                            arr[cordinate] = 'O';
                        }

                        strOut.append(arr);
                        break;
                    } else {
                        System.out.println("This cell is occupied! Choose another one!");
                    }
                } else {
                    System.out.println("Coordinates should be from 1 to 3!");
                }
            } catch (InputMismatchException e) {
                System.out.println("You should enter numbers!");
                scanner.nextLine();
            }
        }
        return strOut.toString();

    }

}

