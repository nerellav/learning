package my.learning;
import java.util.Scanner;

public class Catalon{

        static void ParCheck(int left,int right,String str)
        {
                if (left == 0 && right == 0)
                {
                        System.out.println(str);
                }

                if (left > 0)
                {
                    //System.out.println("calling left: " + (left-1) + " " +  (right+1) + " " +  str + "(");    
                	ParCheck(left-1, right+1 , str + "(");
                }
                if (right > 0)
                {
                	//System.out.println("calling right : " + (left) + " " +  (right- 1) + " " +  str + ")");    
                	ParCheck(left, right-1, str + ")");
                }

        }
        public static void main(String[] args)
        {
                Scanner input=new Scanner(System.in);
                System.out.println("Enter the  number");
                int num=input.nextInt();

                String str="";
                ParCheck(num,0,str);
        }
}