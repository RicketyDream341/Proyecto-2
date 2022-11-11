/*
 * Clase hija. Contiene toda la informacion especifica de los tanques normales.
 */
package clases;


public class TanqueNormal extends Tanque{
    
    
    //Constructor
    public TanqueNormal(){
        super(10);
    }

    //Metodo que devuelve un string con la inmformacion del tanque
    @Override
    public String toString() {
        String aux = "TN-" + this.getSalud();
        return aux;
    }
    
    
    
}//Fin TanqueNormal
