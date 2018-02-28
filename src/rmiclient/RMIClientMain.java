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
import java.util.List;
import java.util.Scanner;
import rmiserver.MyServerInt;
import rmiserver.Product;

public class RMIClientMain {

    public static void main(String[] args) {

        System.setProperty("java.security.policy", "security.policy");
        System.setSecurityManager(new SecurityManager());

        try {

            MyServerInt myRemoteObject = (MyServerInt) Naming.lookup("//192.168.1.103/ABC");
            String text = "Start :-)";
            String result = myRemoteObject.getDescription(text);
            System.out.println("Wysłano do servera: " + text);
            System.out.println("Otrzymana z serwera odpowiedź: " + result);
            System.out.println("h - lista obsługiwanych komend");
            Scanner in = new Scanner(System.in);
            
            while(true) {
                System.out.printf("Komenda:  ");
                text = in.nextLine();
                if ( text.startsWith("h") ){
                    printCommandList();
                    
                } else if (text.startsWith("e")){
                    break;
                    
                } else if ( !(text.length() > 1) ){
                    // nic
                    
                } else if ( text.startsWith("c") ){
                    result = myRemoteObject.calculator(text.substring(2));
                    System.out.println("Wynik:   " + result);
                    
                } else if ( text.startsWith("lp") ){
                    List<Product> products = myRemoteObject.productsList();
                    System.out.println("Liczba produktów: " + products.size());
                    products.forEach((_p) -> {
                        System.out.println(_p.toString());
                    });
                    
                } else if ( text.startsWith("q") ){
                    Product product = myRemoteObject.productByName(text.substring(2));
                    if( product != null ) {
                        System.out.println("Znaleniono produkt:");
                        System.out.println(product.toString());
                    } else {
                        System.out.println("Nie znaleniono produktu");
                    }
                    
                } else {
                    System.out.println("Nieobsługiwana komenda");
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printCommandList() {
        System.out.println("Lista obsługiwanych komend:");
        System.out.println("    calculator [wyrażenie matematyczne] operacja matematyczna np: c 1+2");
        System.out.println("    lp                                  lista produktów");
        System.out.println("    query [nazwa produktu]              szcegóły produktu     np: q iPhone X");
        System.out.println("    help                                lista komend");
        System.out.println("    exit                                koniec");
    }

}
