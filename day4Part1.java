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
        String input = "ckczppom"; // add numbers to this string to get its MD5 hash beginning with 5 zeroes
        int targetZeroes = 5; // target zeroes; 5
        int number =0; // number that we will add

        while(true){ // do this until we break
            String toHash = input+number; // concatenate the string with our number
            String hash = getMd5Hash(toHash); // Md5 hash the string

            if(hash.startsWith(String.format("%0"+ targetZeroes+ "d", 0))){ // if hash starts with 5 zeroes:
                System.out.println("Number found " + number); // display number
                System.out.println("Hash: " + hash); // display hash
                break; // leave the for loop
            }
            number++; // otherwise, increase number
        }
    }
    private static String getMd5Hash(String input){
            try{ // try to call MD5 algo
                MessageDigest md =MessageDigest.getInstance("MD5"); // call MD5 algo
                byte[] MessageDigest = md.digest(input.getBytes()); // hash input string with MD5 aglo and save it into a byte array
                BigInteger no = new BigInteger(1,MessageDigest); // Convert hash byte array into a big integer ; 1 is positive signum
                String hashtext = no.toString(16);  // Convert bigInteger to a hexadecimal string
                while(hashtext.length() <32){ // add leading zeroes if the hash is shorter than 32 characters
                    hashtext = "0" + hashtext;
                }
                return hashtext; // return the hash 
            } catch(NoSuchAlgorithmException e){ // MD5 algorithm is no good; throw error
                throw new RuntimeException(e);
            }
    }
}
