import javax.swing.*;

public class SumThree {
    public static void main(String args[]){
        int num1,num2,num3,sum;
        if(args.length>=3) {
            try {
                num1 = Integer.parseInt(args[0]);
                num2 = Integer.parseInt(args[1]);
                num3 = Integer.parseInt(args[2]);
                sum = num1+num2+num3;
                System.out.println(num1+"+"+num2+"+"+num3+"="+sum);
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        else
        {
            System.out.println("provide three int values !!!");
        }
    }
}
