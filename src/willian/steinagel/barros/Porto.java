/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package willian.steinagel.barros;

import java.util.ArrayList;
/**
 *
 * @author will_
 */
public class Porto {
    private String nome, pais;
    private int id;
    private ArrayList<Integer> id_ships = new ArrayList<Integer>();
    
    /////GETs && SETs/////
    public int GetId(){
        return this.id;
    }//GET ID
    public void SetId(int id){
        this.id = id;
    }//SET ID
    
    public String GetName(){
        return this.nome;
    }//GET NAME
    public void SetName(String name){
        this.nome = name;
    }//SET NAME
    
    public String GetCountry(){
        return this.pais;
    }//GET COUNTRY
    public void SetCountry(String pais){
        this.pais = pais;
    }//SET COUNTRY
    
    public ArrayList<Integer> GetIdShips(){
        return this.id_ships;
    }//GET SHIPS IDS
    public void DockShip(Navio ship){
        this.id_ships.add(ship.GetId());
    }//SET NEW SHIP ID
    public void UndockShip(Navio ship){
        this.id_ships.remove(ship.GetId());
    }//REMOVE NEW SHIP ID
    
    @Override
    public String toString(){
        return String.format("%d: %s - Pais %s", id, nome, pais);
    }
}
