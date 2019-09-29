/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package willian.steinagel.barros;

/**
 *
 * @author will_
 */
public class Navio {
    private int id, year;
    private String name, country;
    Porto port=null;
    
    /////GETs && SETs/////
    public int GetId(){
        return this.id;
    }//GET ID
    public void SetId(int id){
        this.id = id;
    }//SET ID
    
    public String GetName(){
        return this.name;
    }//GET NAME
    public void SetName(String name){
        this.name = name;
    }//SET NAME
    
    public String GetCountry(){
        return this.country;
    }//GET COUNTRY
    public void SetCountry(String country){
        this.country = country;
    }//SET COUNTRY
    
    public int GetYear(){
        return this.year;
    }//GET YEAR
    public void SetYear(int year){
        this.year = year;
    }//SET YEAR
    
    public Porto GetPort(){
        return this.port;
    }//GET PORT
    public void SetPort(Porto port){
        this.port = port;
    }//SET PORT
    
    public boolean canDock(){
        return (this.port!=null);
    }
    
    public String Status(){
        String docked = " - not docked";
        if(canDock()) docked = " - docked at " + port.GetName();
        return String.format("%d: %s(%d) from %s %s", id, name, year, country, docked);
    }
    
    @Override
    public String toString(){
        return String.format("%s %d - %s", name, year, country);
    }
    
}
