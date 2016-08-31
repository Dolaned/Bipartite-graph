
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Random;

public class DataGen {

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        //different densitis of facebook graph 2% 5% and 9%
    	//of values 6,000 
    	PrintWriter writer = new PrintWriter("4000v.02%_2.in", "UTF-8");
        Random rn = new Random();

	    for(int i = 4039; i < 4039; i ++){
            writer.println("AV "+i);
        }
	    
        for(long i = 0; i < 71800; i ++){
            writer.println("AE " +(i%4038)+ " "+ rn.nextInt(4037));
        }
        writer.close();

        PrintWriter writer2 = new PrintWriter("4000v.1%_2.in", "UTF-8");

        for(int i = 4039; i < 4039; i ++){
            writer2.println("AV "+i);
        }
        for(int i = 0; i < 711800; i ++){
            writer2.println("AE " + (i%4038) + " "+ rn.nextInt(4037));
        }
        writer2.close();

        PrintWriter writer3 = new PrintWriter("4000v.5%_2.in", "UTF-8");


        for(int i = 4039; i < 4039; i ++){
            writer3.println("AV "+i);
        }
        for(int i = 0; i < 3911800; i ++){
            writer3.println("AE " +(i%4038)+ " "+ rn.nextInt(4037));
        }
        writer3.close();
    }



}

