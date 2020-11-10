import java.util.*;
public class DateDifferenceCode
{
public static void mian(String args[])
{
 Date d1=new Date(2020,4,07);

        Date d2=new Date(2020,10,07);
     
        long diif= ChronoUnit.DAYS.between(d1.toInstant(), d2.toInstant());
        System.out.println(diif);
}
}