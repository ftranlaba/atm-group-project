package terminallayer.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class CardUtil{

    public final static int cardNumGem(){
        Random rand = new Random();
        return rand.nextInt((int) Math.pow(10, 10));
    }

    public final static int cvcGen(){
        Random rand = new Random();
        return rand.nextInt((int)Math.pow(4, 4));
    }

    public final static String date(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/YY");
        return formatter.format(LocalDate.now().plusYears(5));

    }
}