/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package willian.steinagel.barros;
import operations.DockUtils;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author will_
 */
public class Main {
    static ArrayList<Navio> array_ship = new ArrayList<Navio>();
    static ArrayList<Porto> array_port = new ArrayList<Porto>();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int op;
        do{
            op = Receber();
            Operar(op);
        }while(op!=6);
    }
    
    private static int Receber(){
        int op;
        do{
            Scanner scan = new Scanner(System.in);
            System.out.println("############################################");
            System.out.println("1. Cadastrar um Novo Porto");
            System.out.println("2. Cadastrar um Novo Navio");
            System.out.println("3. Atracar ou Desatracar um navio em um porto");
            System.out.println("4. Listar todos os navios atracados em um porto");
            System.out.println("5. Listar todos os navios de um mesmo país");
            System.out.println("6. Sair");
            System.out.print("Opcao: ");
            op = scan.nextInt();
            if(op>6 && op<1) System.out.println("Opcao invalida!");
        }while(op>6 && op<1);
        
        return op;
    }
    
    private static void Operar(int op){
      
        switch(op){
            case 1:
                //NEW SEAPORT
                DockUtils.AddPort(array_port);
                break;
            case 2:
                //NEW SHIP
                DockUtils.AddShip(array_ship);
                break;
            case 3:
                //DOCK UNTIE SHIP
                DockUntie();
                break;
            case 4:
                //LIST DOCKED SHIP
                DockUtils.ListDocked(array_ship, array_port);
                break;
            case 5:
                //LIST SHIP BY COUNTRY
                DockUtils.ListByCountry(array_ship);
                break;
            default:
                break;
                            
        }
    }
    
    private static void DockUntie(){
        if(array_ship.size()>0 && array_port.size()>0){
            int op;
            do{
                Scanner scan = new Scanner(System.in);
                System.out.println("1. Atracar");
                System.out.println("2. Desatracar");
                System.out.print("Opcao: ");
                op = scan.nextInt();
                if(op>2 && op<1) System.out.println("Opcao invalida!");
            }while(op>6 && op<1);

            if(op==1){
                DockUtils.DockShip(array_ship,array_port);
            }else{
                DockUtils.UntieShip(array_ship,array_port);
            }
        }else{
            System.out.println("Cadastros incompletos!!");
        }
    }
    
    
}
