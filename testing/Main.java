

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter("1000000v.txt", "UTF-8");
        Random rn = new Random();

	    for(int i = 5000; i < 500000; i ++){
            writer.println("AV "+i);
        }
        for(int i = 0; i < 500000; i ++){
            writer.println("AE " +i+ " "+ rn.nextInt(499999));
        }
        writer.close();

        PrintWriter writer2 = new PrintWriter("10000v.txt", "UTF-8");

        for(int i = 5000; i < 15000; i ++){
            writer2.println("AV "+i);
        }
        for(int i = 5000; i < 15000; i ++){
            writer2.println("AE " +i+ " "+ rn.nextInt(14999));
        }
        writer2.close();

        PrintWriter writer3 = new PrintWriter("100000v.txt", "UTF-8");


        for(int i = 0; i < 100000; i ++){
            writer3.println("AV "+i);
        }
        for(int i = 0; i < 100000; i ++){
            writer3.println("AE " +i+ " "+ rn.nextInt(99999));
        }
        writer3.close();
    }



}
