import java.io.*;
import java.util.*;

public class Sorter {
    public static final int DIMENSIONS = 4;

    public static void getIntro() {
        System.out.println("This program processes a file of answers to the");
        System.out.println("Keirsey Temperament Sorter. It converts the");
        System.out.println("various A and B answers for each person into");
        System.out.println("a sequence of B-percentages and then into a");
        System.out.println("four-letter personality type.");
        System.out.println();
    }

    public static void writePersonalities(int[] perc, PrintStream myWriter){
        myWriter.print("[" + perc[0] + ", " + perc[1] + ", " + perc[2] + ", " + perc[3] + "] = ");
        if(perc[0] > 50){
            myWriter.print("I");
        }
        else if(perc[0] < 50){
            myWriter.print("E");
        }
        else{
            myWriter.print("X");
        }
        if(perc[1] > 50){
            myWriter.print("N");
        }
        else if(perc[1] < 50){
            myWriter.print("S");
        }
        else{
            myWriter.print("X");
        }
        if(perc[2] > 50){
            myWriter.print("F");
        }
        else if(perc[2] < 50){
            myWriter.print("T");
        }
        else{
            myWriter.print("X");
        }
        if(perc[3] > 50){
            myWriter.print("P" + "\n");
        }
        else if(perc[3] < 50){
            myWriter.print("J" + "\n");
        }
        else{
            myWriter.print("X" + "\n");
        }
    }

    public static int getPercentages(double b, double size){
        double perc_b = b/size;
        return (int) Math.round(perc_b * 100);
    }

    public static void writeSolution(Scanner input, PrintStream myWriter){
        int i = 0;
        String line;
        while(input.hasNextLine()) {
            line = input.nextLine();
            double b_e = 0;
            double b_n = 0;
            double b_f = 0;
            double b_p = 0;
            double size_1 = 10;
            double size_2 = 20;
            double size_3 = 20;
            double size_4 = 20;
            if (i % 2 == 0) {
                myWriter.print(line + ": ");
            } else {
//                System.out.println("here");
                int[] perc = new int[DIMENSIONS];
                for (int j = 0; j < line.length(); j++) {
                    char c = line.charAt(j);
                    char b = 'b';
                    char B = 'B';
                    char dash = '-';
                    if ((c == b) || (c == B)) {
                        if (j % 7 == 0) {
                            b_e++;

                        } else if ((j % 7 == 1) || (j % 7 == 2)) {
                            b_n++;

                        } else if ((j % 7 == 3) || (j % 7 == 4)) {
                            b_f++;

                        } else {
                            b_p++;
                        }
                    } else if (c== dash) {
                        if (j % 7 == 0) {
                            size_1--;

                        } else if ((j % 7 == 1) || (j % 7 == 2)) {
                            size_2--;

                        } else if ((j % 7 == 3) || (j % 7 == 4)) {
                            size_3--;

                        } else {
                            size_4--;
                        }
                    }
                }
                perc[0] = getPercentages(b_e, size_1);
                perc[1] = getPercentages(b_n, size_2);
                perc[2] = getPercentages(b_f, size_3);
                perc[3] = getPercentages(b_p, size_4);
                writePersonalities(perc, myWriter);
            }
            i += 1;
        }
    }



    public static void main (String[] args) throws IOException{
        getIntro();
        Scanner consoleInp= new Scanner(System.in);
        System.out.print("input file name? ");
        Scanner input = new Scanner(new File(consoleInp.nextLine()));
        System.out.println("output file name? ");
        PrintStream myWriter = new PrintStream(new File(consoleInp.nextLine()));
        writeSolution(input, myWriter);
        myWriter.close();
    }
}
