package BoolsAndMoo;


import java.util.Scanner;

public class BullsAndMoo {
    public static void main(String[] args) {
        int[] gessedNumber = new int[3];
        int[] secretNumber = genSn();
        int popytka = 0;
        System.out.println("Добро пожаловать в игру: Быки и коровы");
        System.out.println("Правила игры: компьютер загадывает 3-х значное  число ");
        System.out.println("Если у вас совпадает цифра и ее позиция это 'бык'.");
        System.out.println("Если у вас совпадает только цифра, но не ее позиция это 'корова'.");
        while (true) {
            popytka++;
            System.out.println("Введите трехзначное число:");
            gessedNumber = getGesNumber();
            int buls = countBuls(secretNumber, gessedNumber);
            int cows = countCows(secretNumber, gessedNumber);
            System.out.println("Быки: " + buls);
            System.out.println("Коровы : " + cows);
            if (buls == 3) {
                System.out.println("Вы угадали секретное число " + convertIntToString(secretNumber) + " за: " + popytka + " попыток!");
                break;
            }
        }
    }

    public static int[] genSn() {
        int[] secretNumber = new int[3];
        boolean[] usedDig = new boolean[10];
        int index = 0;
        while (index < 3) {

            int digit = (int) (Math.random() * 10);
            if (!usedDig[digit]) {
                secretNumber[index] = digit;
                usedDig[digit] = true;
                index++;
            }
        }

        return secretNumber;
    }

    public static int[] getGesNumber() {
        Scanner scan = new Scanner(System.in);
        int[] gessedNumber = new int[3];
        int number = scan.nextInt();
        gessedNumber[0] = (number / 100);
        gessedNumber[1] = (number / 10) % 10;
        gessedNumber[2] = number % 10;
        return gessedNumber;
    }

    public static int countBuls(int[] secredNumber, int[] gessedNumber) {
        int buls = 0;
        for (int i = 0; i < 3; i++) {
            if (secredNumber[i] == gessedNumber[i]) {
                buls++;
            }
        }
        return buls;
    }

    public static int countCows(int[] secretNumber, int[] guessedNumber) {
        int cows = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (secretNumber[i] == guessedNumber[j] && i != j) {
                    cows++;
                }
            }
        }
        return cows;
    }

    public static String convertIntToString(int[] number) {
        StringBuilder strBuild = new StringBuilder();
        for (int digit : number) {
            strBuild.append(digit);
        }
        return strBuild.toString();
    }
}