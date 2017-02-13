package fourth_bid.console;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class In{

    public static String inText() throws java.io.IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        str = br.readLine();
// System.out.println(str); // Utskrift för debugging
        return str;
    }

    public static int inInt() throws IOException { // Int inmatning
        String inStr;
        int heltal = 0;
        inStr = inText();
        try {
            heltal = Integer.parseInt(inStr);
        } catch (NumberFormatException nfe) {
            System.err.println("Invalid Format!");
        }
        return heltal;
    }
}

