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

    public static void main (String[] args) throws IOException{
        getIntro();
        Scanner consoleInp= new Scanner(System.in);
        System.out.print("input file name? ");
        Scanner input = new Scanner(new File(consoleInp.nextLine()));
        System.out.println("output file name? ");
        PrintStream myWriter = new PrintStream(new File(consoleInp.nextLine()));

        int i = 0;
        String line;
        while(input.hasNextLine()){
            line=input.nextLine();
            double b_e = 0;
            double b_n = 0;
            double b_f = 0;
            double b_p = 0;
            double size_1 = 10;
            double size_2 = 20;
            double size_3 = 20;
            double size_4 = 20;
            if(i%2==0) {
                myWriter.print(line + ": ");
            }
            else{
                System.out.println("here");
                int[] perc = new int[DIMENSIONS];
                for(int j = 0; j < line.length(); j++) {

                    Character c = line.charAt(j);
                    Character b = 'b';
                    Character B = 'B';
                    Character dash = '-';
                    if (c.equals(b) || (c.equals(B))) {
                        if (j % 7 == 0) {
                            b_e++;

                        } else if ((j % 7 == 1) || (j % 7 == 2)) {
                            b_n++;

                        } else if ((j % 7 == 3) || (j % 7 == 4)) {
                            b_f++;

                        } else {
                            b_p++;
                        }
                    }
                    else if(c.equals(dash)){
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
                double perc_b = b_e/size_1 * 100;

                int write_b = (int) Math.round(perc_b);
                perc[0] = write_b;


                perc_b = b_n/size_2 * 100;
                write_b = (int) Math.round(perc_b);
                perc[1] = write_b;


                perc_b = b_f/size_3 * 100;
                write_b = (int) Math.round(perc_b);
                perc[2] = write_b;


                perc_b = b_p/size_4 * 100;
                write_b = (int) Math.round(perc_b);
                perc[3] = write_b;
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
            System.out.println(line + i);
            i+=1;
        }
        myWriter.close();
    }
}
