/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmiclient;

/**
 *
 * @author tomek.buslowski
 */
import java.rmi.Naming;
import java.util.Scanner;
import rmiserver.MyServerInt;

public class RMIClientMain {

    public static void main(String[] args) {

        System.setProperty("java.security.policy", "security.policy");
        System.setSecurityManager(new SecurityManager());

        try {

            MyServerInt myRemoteObject = (MyServerInt) Naming.lookup("//localhost/ABC");
            String text = "Start :-)";
            String result = myRemoteObject.getDescription(text);
            System.out.println("Wysłano do servera: " + text);
            System.out.println("Otrzymana z serwera odpowiedź: " + result);
            
            Scanner in = new Scanner(System.in);
            do {
                System.out.printf("Oblicz:  ");
                text = in.nextLine();
                result = myRemoteObject.calculator(text);
                System.out.println("Wynik:   " + result);
                
            } while ( !"end".equals(text) );
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

//
//                System.out.printf("First number:  ");
//                a = in.nextDouble();
//                in.nextLine();
//                System.out.printf("Calc operation:  ");
//                op = in.nextLine();
//                System.out.printf("Sec number:  ");
//                b = in.nextDouble();
//                in.nextLine();
//                switch(op) {
//                    case "+":
//                        r = myRemoteObject.sum(a, b);
//                        break;
//                    case "-":
//                        r = myRemoteObject.subtract(a, b);
//                        break;
//                    case "*":
//                        r = myRemoteObject.multiply(a, b);
//                        break;
//                    case "/":
//                        r = myRemoteObject.divide(a, b);
//                        break;
//                }
//                System.out.println("Result: " + a + " " + op + " " + b + " = " + r);
