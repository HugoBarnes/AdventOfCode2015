import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;
public class day4Part2 {
    public static void main(String[] args) {
        String input = "ckczppom";
        int targetZeroes =6;
        int number =0;
        
        while(true){
            String toHash = input+number;
            String hash = getMd5Hash(toHash);

            if(hash.startsWith(String.format("%0" + targetZeroes + "d", 0))){
                System.out.println("HashCode" + hash);
                System.out.println("Number"+ number);
                break;
            }
            number++;
        }
    }
        private static String getMd5Hash(String input){
            try{
                MessageDigest md = MessageDigest.getInstance("MD5");
                byte[] MessageDigest = md.digest(input.getBytes());
                BigInteger no = new BigInteger(1, MessageDigest);
                String hashText = no.toString(16);
                while(hashText.length()<32){
                    hashText = "0" + hashText;
                }
                return hashText;
            } catch(NoSuchAlgorithmException e){
                throw new RuntimeException(e);
            }
        }
}
