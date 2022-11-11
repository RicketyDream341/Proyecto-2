/*
 * Clase abstracta padre. Contiene toda la informacion comun a todos los tipos de tanque.
 */
package clases;


public abstract class Tanque {
    
    //Declaracion de atributos
    private int salud;
    
    //Constructor
    public Tanque(int s){
        this.salud = s;
    }
    
    //Metodos get y set
    public void setSalud(int s){
        this.salud = s;
    } 
    
    public int getSalud(){
        return this.salud;
    }
    
    //Metodo abstracto que sera implementado por las clases hijas.
    @Override
    public abstract String toString();
    
}//Fin Tanque
