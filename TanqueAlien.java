/*
 * Clase hija. Contiene toda la informacion especifica de los tanques de tipo alien.
 */
package clases;


public class TanqueAlien extends Tanque{
    
    
    //Constructor
    public TanqueAlien(){
        super(20);
    }

    //Metodo que devuelve un string con la inmformacion del tanque
    @Override
    public String toString() {
        String aux = "TA-" + this.getSalud();
        return aux;
    }
    
    
}//Fin TanqueAlien
