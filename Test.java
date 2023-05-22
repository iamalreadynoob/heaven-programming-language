import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
public class Test
{
public static void main(String[] args)
{
ArrayList<Integer> sigma = new ArrayList<>();
sigma.add(13);
sigma.add(2);
sigma.add(7);
sigma.add(17);
sigma.add(13);
sigma.add(27);
sigma.add(23);
Collections.sort(sigma);
System.out.println(sigma);
Collections.sort(sigma, Collections.reverseOrder());
System.out.println(sigma);
}
}