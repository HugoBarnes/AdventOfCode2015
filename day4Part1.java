import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;

public class day4Part1 {
    // take input string
    // concatenate a number to the string
    // calculate MD5 hash
    // if it doesn't have enough leading zeroes (5)
    // increase the concatenation
    public static void main(String[] args) {
        String input = "ckczppom";
        int targetZeroes = 5;
        int number =0;

        while(true){
            String toHash = input+number;
            String hash = getMd5Hash(toHash);

            if(hash.startsWith(String.format("%0"+ targetZeroes+ "d", 0))){
                System.out.println("Number found " + number);
                System.out.println("Hash: " + hash);
                break;
            }
            number++;
        }
    }
    private static String getMd5Hash(String input){
            try{
                MessageDigest md =MessageDigest.getInstance("MD5");
                byte[] MessageDigest = md.digest(input.getBytes());
                BigInteger no = new BigInteger(1,MessageDigest);
                String hashtext = no.toString(16);
                while(hashtext.length() <32){
                    hashtext = "0" + hashtext;
                }
                return hashtext;
            } catch(NoSuchAlgorithmException e){
                throw new RuntimeException(e);
            }
    }
}
