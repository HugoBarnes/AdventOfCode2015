import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;
public class day4Part2 {
    public static void main(String[] args) {
        String input = "ckczppom"; // input string
        int targetZeroes =6; // number of desired zeroes
        int number =0; // number that will be added on to the string
        
        while(true){
            String toHash = input+number; // concatenated string
            String hash = getMd5Hash(toHash); // hash result

            if(hash.startsWith(String.format("%0" + targetZeroes + "d", 0))){ // if string starts with 6 zeroes
                System.out.println("HashCode" + hash); // print the hashcode
                System.out.println("Number"+ number); // print the concatenated number
                break; // leave the while loop
            }
            number++; // otherwise, increase number
        }
    }
        private static String getMd5Hash(String input){
            try{ // try to open the MD5 algorithm
                MessageDigest md = MessageDigest.getInstance("MD5"); // open the algorithm and assign to md
                byte[] MessageDigest = md.digest(input.getBytes()); // apply algorithm to input and save in byte array
                BigInteger no = new BigInteger(1, MessageDigest); // transform into a big Integer
                String hashText = no.toString(16); // transform to hexadecimal string
                while(hashText.length()<32){  // if there are less than 32 characters, add leading zeroes
                    hashText = "0" + hashText;
                }
                return hashText; // return hashText
            } catch(NoSuchAlgorithmException e){ // if the algorithm fails to load properly
                throw new RuntimeException(e);
            }
        }
}
