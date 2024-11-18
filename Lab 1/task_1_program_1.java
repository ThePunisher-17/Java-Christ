import java.util.*;

public class task_1_program_1 {
    private int ccNumber;

    public task_1_program_1(long ccNumber) 
    {
        this.ccNumber = Integer.parseInt(String.valueOf(ccNumber)); // ccNumber;
        System.out.println(operation());
    }

    private boolean isValid()
    {
        String ccNumber = String.valueOf(this.ccNumber);
        int length = ccNumber.length();

        if(length == 8 || length ==9){
            return true;
        }
        else{
            return false;
        }
    }

    private String operation()
    {
        String reversedString="";
        int last_digit_of_sum = 0;
        int sum = 0;
        if(isValid())
        {
            int last_digit = ccNumber % 10;
            for (int i = String.valueOf(ccNumber/10).length()-1; i >= 0; i--)
            {
                reversedString += String.valueOf(ccNumber/10).charAt(i);
            }
            // System.out.println(reversedString);

            for (int i = 0; i < String.valueOf(ccNumber/10).length(); i++)
            {
                int partical_double_sum = 0;
                if(i % 2 == 0)
                {
                    partical_double_sum = Integer.parseInt(String.valueOf(reversedString.charAt(i))) * 2;

                    // sum digits of partical_double_sum
                    while(partical_double_sum > 0)
                    {
                        sum += partical_double_sum % 10;
                        partical_double_sum = partical_double_sum / 10;
                    }

                }
                else
                {
                    sum += Integer.parseInt(String.valueOf(reversedString.charAt(i)));
                }
                // System.out.println(sum);
             
            }
            // System.out.println(sum);
            last_digit_of_sum = 10 - (sum%10);
            // System.out.println(last_digit_of_sum);
            

            if(last_digit_of_sum == last_digit)
            {
                return "Valid card number";
            }
        }
        return "Invalid card number";

    }
    
    public static void main(String[] args) 
    {
        // long ccNumber = 79927398;
        Scanner sc = new Scanner(System.in);
        long ccNumber = sc.nextLong();
        task_1_program_1 obj = new task_1_program_1(ccNumber);
        sc.close(); 
        
    }
}