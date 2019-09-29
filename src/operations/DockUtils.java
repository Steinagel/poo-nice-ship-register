/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operations;
import java.util.ArrayList;
import willian.steinagel.barros.Navio;
import willian.steinagel.barros.Porto;
import java.util.Scanner;

/**
 *
 * @author will_
 */
public class DockUtils {
    
    //#########################SIGNING UP NEW REGISTERS#########################//
    public static void AddPort(ArrayList<Porto> ports){
        Scanner scan = new Scanner(System.in);
        
        Porto port = new Porto();
        String name, country;
        boolean portAlreadyExists;
        
        System.out.println("############################################");
        System.out.println("Cadastrar um Novo Porto");
        do{
            System.out.print("Nome: ");
            name = scan.nextLine();
            port.SetName(name);
            
            System.out.print("Pais: ");
            country = scan.nextLine();
            port.SetCountry(country);
            
            port.SetId(ports.size()+1);
            
            portAlreadyExists = IsPortNew(ports, port);
            if(portAlreadyExists)
                System.out.println("Porto ja cadastrado, insira novamente!!");
            else 
                ports.add(port);
           
        }while(portAlreadyExists);
    }
    
    private static boolean IsPortNew(ArrayList<Porto> ports, Porto port){
        for(Porto reg_por: ports){
            if(port.toString().equals(reg_por.toString())) 
                return true;
        }
        return false;
    }
    
    public static void AddShip(ArrayList<Navio> ships){
        Scanner scan = new Scanner(System.in);
        
        Navio ship = new Navio();
        String name, country;
        int year;
        boolean shipAlreadyExists;
        
        System.out.println("############################################");
        System.out.println("Cadastrar um Novo Navio");
        do{
            System.out.print("Nome: ");
            name = scan.nextLine();
            ship.SetName(name);
            
            System.out.print("Pais: ");
            country = scan.nextLine();
            ship.SetCountry(country);
            
            System.out.print("Ano: ");
            year = scan.nextInt();
            ship.SetYear(year);
            
            ship.SetId(ships.size()+1);
            
            shipAlreadyExists = IsShipNew(ships, ship);
            if(shipAlreadyExists)
                System.out.println("Navio ja cadastrado, insira novamente!!");
            else 
                ships.add(ship);
           
        }while(shipAlreadyExists);
    }
    
    private static boolean IsShipNew(ArrayList<Navio> ships, Navio ship){
        for(Navio reg_ship: ships){
            if(reg_ship.toString().equals(ship.toString())) 
                return true;
        }
        return false;
    }
    
    //#########################DOCKING OR UNTIEING#########################//
    //#####################DOCKING#####################//
    public static void DockShip(ArrayList<Navio> ships, ArrayList<Porto> ports){
        

        ArrayList<Navio> can_dock = new ArrayList<Navio>();
        ArrayList<Navio> cant_dock = new ArrayList<Navio>();
        
        GetEnabledShips(ships, can_dock, cant_dock);
        if(can_dock.size()>0){
            Scanner scan = new Scanner(System.in);
            System.out.println("############################################");
            System.out.println("Atracar um navio em um porto");

            System.out.println("Portos disponiveis para atracar:");
            ShowsPort(ports);
            System.out.print("Digite o ID do porto destino: ");
            int id = scan.nextInt();
            Porto aux_port = null;

            if(id>0 && id<=ports.size()){
                aux_port = ports.get(PortInList(ports, id));
            }
            if(aux_port==null){
                System.out.println("Porto invalido!!");
                System.out.println("Operacao Finalizada");
            }else{

                System.out.println("Navios disponiveis para atracar:");
                ShowsShip(can_dock);

                System.out.print("Digite o ID do navio que deseja atracar: ");
                id = scan.nextInt();
                int index = ShipInList(can_dock, id);
                if(index!=-1 && aux_port!=null){
                    Navio aux_ship = ships.get(ShipInList(ships,id));
                    aux_ship.SetPort(aux_port);
                    aux_port.DockShip(aux_ship);
                    System.out.println("Navio Atracado");
                }else if(ShipInList(cant_dock, id)!=-1){
                    System.out.print("Navio invalido. Ja atracado!!! ");
                    System.out.println("Operacao Finalizada");
                }else{
                    System.out.print("Navio nao cadastrado!!");
                    System.out.println("Operacao Finalizada");
                }
            }
        }else{
            System.out.println("Nenhum navio disponivel para atracar!!");
        }
    }
    //#########################UNTIEING#########################//
    public static void UntieShip(ArrayList<Navio> ships, ArrayList<Porto> ports){
        ArrayList<Navio> can_dock = new ArrayList<Navio>();
        ArrayList<Navio> cant_dock = new ArrayList<Navio>();
        
        GetEnabledShips(ships, can_dock, cant_dock);
        if(cant_dock.size()>0){
            if(ships.size()>0 && ports.size()>0){
                Scanner scan = new Scanner(System.in);


                System.out.println("############################################");
                System.out.println("Desatracar um navio em um porto");

                System.out.println("Navios disponiveis para desatracar:");
                ShowsShip(cant_dock);

                System.out.print("Digite o ID do navio que deseja desaatracar: ");
                int id = scan.nextInt();
                int index = ShipInList(cant_dock, id);
                if(index!=-1){
                    Navio aux_ship = ships.get(ShipInList(ships,id));
                    aux_ship.SetPort(null);
                    System.out.println("Navio Desatracado");
                }else if(ShipInList(can_dock, id)!=-1){
                    System.out.print("Navio invalido. Ja desaatracado!!! ");
                    System.out.println("Operacao Finalizada");
                }else{
                    System.out.print("Navio nao cadastrado!!");
                    System.out.println("Operacao Finalizada");
                }

            }else{
                System.out.println("Cadastros incompletos!!");
            }
        }else{
            System.out.println("Nenhum navio disponivel para desatracar!!");
        }
    }
    
    private static void GetEnabledShips(ArrayList<Navio> ships, ArrayList<Navio> can_dock, ArrayList<Navio> cant_dock){
        for(Navio ship : ships){
            if(!ship.canDock()){
                can_dock.add(ship);
            }
            else{
                cant_dock.add(ship);
            }
        }
    }
    
    private static void ShowsShip(ArrayList<Navio> ships){
        System.out.printf("Total: %d\n", ships.size());
        for(int i=0; i<ships.size();i++){
            System.out.println(ships.get(i).Status());
        }
    }
    
    private static int ShipInList(ArrayList<Navio> ships, int id){
        for(Navio ship : ships)
            if(ship.GetId() ==id) return ships.indexOf(ship);
        
        return -1;
    }
    
    private static void ShowsPort(ArrayList<Porto> port){
        System.out.printf("Total: %d \n", port.size());
        for(int i=0; i<port.size();i++){
            System.out.println(port.get(i).toString());
        }
    }
    
    private static int PortInList(ArrayList<Porto> ports, int id){
        for(Porto port : ports)
            if(port.GetId() ==id) return ports.indexOf(port);
        
        return -1;
    }
    
    //#########################LIST ALL DOCKED SHIPS#########################//
    public static void ListDocked(ArrayList<Navio> ships, ArrayList<Porto> ports){
        ArrayList<Navio> can_dock = new ArrayList<Navio>();
        ArrayList<Navio> cant_dock = new ArrayList<Navio>();
        
        GetEnabledShips(ships, can_dock, cant_dock);
        if(cant_dock.size()>0){
            Scanner scan = new Scanner(System.in);
            ArrayList<String> array_port = new ArrayList<String>();
            
            StringByPort(cant_dock, array_port);
            System.out.println("Opcoes:");
            ShowArrayItem(array_port);
            
            System.out.print("Digite o porto: ");
            String porto = scan.nextLine();
            if(VerifyPortName(array_port,porto)){
                ShowByName(ships,porto);
            }else{
                System.out.println("Pais invalido!!");
                System.out.println("Operacao Finalizada");
            }
            
        }else{
            System.out.println("Nao ha portos com navios atracados!!");
        }
    }
    
    private static void StringByPort(ArrayList<Navio> ships, ArrayList<String> array_port){
        int cont;
        String aux;
        for(Navio ship:ships){
            cont=0;
            aux = ship.GetPort().GetName();
            for(String port:array_port){
                if(aux.equals(port)){
                    cont++;
                }
            }
            if(cont==0){
                array_port.add(aux);
            }
        }
    }
    
    private static boolean VerifyPortName(ArrayList<String> ports, String name){
        for(String each:ports){
            if(each.equals(name)){
                return true;
            }
        }
        return false;
    }
    
    private static void ShowByName(ArrayList<Navio> ships, String name){
        String sing = (ships.size()==1) ? "Navio atracado" : "Navios atracados";
        System.out.printf("\n%s em %s: \n", sing, name);
        for(Navio ship:ships){
            Porto porto = ship.GetPort();
            if(porto!=null)
                if(porto.GetName().equals(name)){
                    System.out.println(ship.toString());
                }
        }
    }
    
    //#########################LIST ALL SHIPS FROM THE SAME COUNTRY#########################//
    public static void ListByCountry(ArrayList<Navio> ships){
        ArrayList<String> array_countries = new ArrayList<String>();
        
        StringByCountries(ships, array_countries);
        if(ships.size()>0){
            Scanner scan = new Scanner(System.in);
            System.out.println("Opcoes:");
            ShowArrayItem(array_countries);
            //BY ID
//            System.out.print("Digite o ID do pais: ");
//            int id = scan.nextInt();
//            if(id>0 && id<=array_countries.size()){
//                ShowByCountry(ships,array_countries.get(id-1));
//            }else{
//                System.out.println("ID invalido!!");
//                System.out.println("Operacao Finalizada");
//            }
            //BY NAME
            System.out.print("Digite o pais: ");
            String pais = scan.nextLine();
            if(VerifyCountryName(array_countries,pais)){
                ShowByCountry(ships,pais);
            }else{
                System.out.println("Pais invalido!!");
                System.out.println("Operacao Finalizada");
            }
        }else{
            System.out.println("Nao ha navios cadastrados!!");
        }
    }
    
    private static void StringByCountries(ArrayList<Navio> ships, ArrayList<String> countries){
        int cont;
        for(Navio ship:ships){
            cont=0;
            for(String country:countries){
                if(ship.GetCountry().equals(country)){
                    cont++;
                }
            }
            if(cont==0){
                countries.add(ship.GetCountry());
            }
        }
    }
    
    private static void ShowArrayItem(ArrayList<String> countries){
//        for(int i=0;i<countries.size();i++){
//            System.out.printf("%d: %s\n", i+1, countries.get(i));
//        }
        for(String country:countries){
            System.out.println(country);
        }
    }
    
    private static boolean VerifyCountryName(ArrayList<String> countries, String country){
        for(String each:countries){
            if(each.equals(country)){
                return true;
            }
        }
        return false;
    }
    
    private static void ShowByCountry(ArrayList<Navio> ships, String country){
        String sing = (ships.size()==1) ? "Navio" : "Navios";
        System.out.printf("\n%s do pais %s: \n", sing, country);
        for(Navio ship:ships){
            if(ship.GetCountry().equals(country)){
                System.out.println(ship.toString());
            }
        }
    }
}