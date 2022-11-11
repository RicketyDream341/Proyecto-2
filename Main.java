/*
 * 
 */
package main;

import clases.Tanque;
import clases.TanqueAlien;
import clases.TanqueNormal;
import java.util.Scanner;
import static main.Main.TAM1;


public class Main {

    //Declaracion e inicializacion de variables
    public static final int TAM1 = 2;
    public static Tanque[][] tablero = new Tanque[TAM1][TAM1];
    public static Scanner input = new Scanner(System.in);
    public static int conteoDisparos = 0;
    public static int conteoBombas = 0;
    public static int cantTanques = 0;
    public static int saludTotalBajada = 0;
    
    public static void main(String[] args) {
       
        mostrarMenu();
        
    }//Fin main
    
    
    //Muestra el menu de opciones y solicita al usuario que elija una opcion, realizando la accion correspondiente.
    public static void mostrarMenu(){
        
        String opcion1 = "";
        String opcion2 = "";
        boolean salir = false;
        boolean seguir = true;
        while(!salir){
            System.out.println("--------------------------");        
            System.out.println("        TANK WARS         ");
            System.out.println("--------------------------");
            System.out.println();
            System.out.println("1.- Iniciar juego");
            System.out.println("2.- Salir");
            System.out.println();
        
            opcion1 = input.nextLine();
            switch(opcion1){
                
                case "1":
                    inicializarTablero();
                    dibujarTablero();
                    
                    while(seguir){
                        //Si aun hay tanques en juego, se suigue. Caso contrario volvemos al menu principal
                        if(cantTanques>0){
                            System.out.println();
                            System.out.println("1.- Disparar bala");
                            System.out.println("2.- Activar bomba atomica");
                            System.out.println("3.- Activar tanque mutante");
                            System.out.println("4.- La frase de la abuela");
                            System.out.println("5.- Conteo de disparos");
                            System.out.println("6.- Leer cantidad de salud");
                            System.out.println("7.- Salir");
                            System.out.println();
                        
                            opcion2 = input.nextLine();
                            switch(opcion2){
                                case "1":
                                    dispararBala();
                                    dibujarTablero();
                                    break;
                                
                                case "2":
                                    activarBombaAtomica();
                                    dibujarTablero();
                                    break;
                                
                                case "3":
                                    activarTanqueMutante();
                                    dibujarTablero();
                                    break;
                                
                                case "4":
                                    System.out.println();
                                    System.out.println(fraseAbuela());
                                    System.out.println();
                                    dibujarTablero();
                                    break;
                                
                                case "5":
                                    System.out.println();
                                    System.out.println("Cantidad de disparos efectuados: " + conteoDisparos);
                                    System.out.println("Cantidad de bombas atomicas lanzadas: " + conteoBombas);
                                    System.out.println();
                                    dibujarTablero();
                                    break;
                                
                                case "6":
                                    //En este punto no me queda claro que pide. A mi entender, una forma de verlo, es obtener la cantidad total de vida bajada hasta ese momento
                                    //Es decir, el daño acumulado a tanques
                                    System.out.println();
                                    System.out.println("Cantidad de salud que se le ha bajado a los tanques (daño acumulado): " + saludTotalBajada);
                                    System.out.println();
                                    break;
                                
                                case "7":
                                    seguir = false;
                                    break;
                                
                                default:
                                    System.out.println();
                                    System.out.println("ERROR. OPCION NO VALIDA");
                                    System.out.println();
                                    break;
                            }
                            
                        }
                        else{
                            seguir = false;
                            System.out.println();
                            System.out.println("ENHORABUENA HA DESTRUIDO A TODOS LOS TANQUES!!");
                            System.out.println();
                        }
                    }
                    seguir = true;
                    opcion2 = "";
                    break;
                    
                case "2":
                    salir = true;
                    break;
                    
                default:
                    System.out.println();
                    System.out.println("ERROR. OPCION NO VALIDA");
                    System.out.println();
                    break;
            }
        }
    }
    
    
    //Metodo que inicializa el tablero calculando la cantidad y posicionando los tanques.
    public static void inicializarTablero(){
        //Se declara la variable para el tipo de tanque
        int tipoTanque;
        //Se obtiene un numero random entre 1 y 4 para la cantidad de tanques
        cantTanques = (int)(Math.random()*4+1);
        
        //Se declara e inicializa el contador de tanques
        int tanques = 1;
        
        //Recorremos el tablero posicionando los tanques, calculando un numero aleatorio para el tipo de tanque. Cuando llega a la cantidad de tanques definida anteriormente.
        for(int i = 0; i<TAM1; i++){
            for(int j = 0; j<TAM1; j++){
                if(tanques<=cantTanques){
                    //Se obtiene un numero aleatorio entre 1 y 2 para ver el tipo de tanque que se crea
                    tipoTanque = (int)(Math.random()*2+1);
                    switch(tipoTanque){
                        case 1:
                            //Se crea un tanque normal
                            tablero[i][j] = new TanqueNormal();
                            break;
                            
                        case 2:
                            //Se crea un tanque alien
                            tablero[i][j] = new TanqueAlien();
                            break;
                            
                        default:
                            //No se hace nada
                            break;
                    }
                    tanques++;
                }
            }
        }
    }
    
    
    //Metodo que imprime por pantalla el tablero y sus respectivos tanques o espacios vacios
    public static void dibujarTablero(){
        
        //Se imprime cabecera
        System.out.println();
        System.out.println("CAMPO DE JUEGO");
        System.out.println("-------------------------");
        System.out.print("|   *   ");
        for(int l = 0; l<=1; l++){
            System.out.print("|");
            System.out.print("   "+l+"   ");
            
        }

        //Se imprime el resto del tablero
        for(int i = 0; i<TAM1; i++){
            System.out.print("|");
            System.out.println();
            System.out.println("-------------------------");
            System.out.print("|");
            System.out.print("   "+i+"   ");
            for(int j = 0; j<TAM1; j++){
                //Si el espacio que se esta recorriendo no es null significa que hay un tanque
                if(tablero[i][j]!=null){
                    //Solo se muestran los tanques vivos
                    if(tablero[i][j].getSalud()>0){
                        System.out.print("| "+tablero[i][j].toString()+" ");
                    }    
                }
                else{
                    System.out.print("|       ");
                }
            }
        }
        System.out.print("|");
        System.out.println();
        System.out.println("-------------------------");
        System.out.println();
    }
    
    //Metodo que sumula el disparo a un tanque en una posicion dada.
    public static void dispararBala(){
        int fila;
        int col;
        boolean inputOk = false;
        
        //Se solicita al usuario que especifique la fila y columna del tanque sobre el que desea efectuar el disparo
        //Se repite el bucle hasta que las coordenadas sean correctas
        while(!inputOk){
            //Se captura exception en caso de que el usuario introduzca un caracter no numerico
            try{
                System.out.println("Por favor, indique las coordenadas del tanque al que desea disparar");
                System.out.print("Fila: ");
                fila = Integer.parseInt(input.nextLine());
                System.out.print("Columna: ");
                col = Integer.parseInt(input.nextLine());
                if(inputCoordenadasOk(fila, col)){
                    int salud = tablero[fila][col].getSalud();
                    //Se verifica que al realizar el disparo, el tanque aun tenga vida
                    if(salud-5>0){
                    //Si aun le queda vida, se actualiza la vida del tanque
                        salud = salud - 5;
                        tablero[fila][col].setSalud(salud);
                        //Se aumenta la variable acumulativa con el daño producido
                        saludTotalBajada = saludTotalBajada + 5;
                    }
                    //en caso de que el tanque haya sido destruido, se establece esa posicion a null para que ya no se muestre y restamos 1 a la cantidad de tanques
                    else{
                        tablero[fila][col] = null;
                        cantTanques--;
                        System.out.println();
                        System.out.println("FELICIDADES! EL TANQUE HA SIDO DESTRUIDO!!");
                        System.out.println();
                    }
                    inputOk = true;
                }
                else{
                    System.out.println();
                    System.out.println("ERROR. LA FILA Y COLUMNA HA DE SER UN NUMERO COMPRENDIDO ENTRE 0 Y " + (TAM1-1) + " INCLUSIVE O LA COORDENADA ESPECIFICADA ESTA VACIA");
                    System.out.println();
                }
            }catch(NumberFormatException e){
                System.out.println();
                System.out.println("ERROR. LOS VALORES DE FILA Y COLUMNA HAN DE SER NUMERICOS");
                System.out.println();
            }
        }
        conteoDisparos++;
    }
    
    //Metodo que simula la activacion de una bomba atomica en una casilla al azar destruyendo al instante a un tanque. 
    //Puede darse el caso de que la casilla este vacia y no le de a ningun tanque
    public static void activarBombaAtomica(){
        
        //Se obtienen las coordenadas aleatoriamente
        int fil = (int)(Math.random()*2);
        int col = (int)(Math.random()*2);
        
        //Se comprueba que en la casilla haya un tanque
        if(inputCoordenadasOk(fil,col)){
            //Se aumenta el daño total efectuado con la cantidad de vida que le quedaba al tanque destruido
            saludTotalBajada = saludTotalBajada + tablero[fil][col].getSalud();
            //Establecemos la posicion en la que se encuentra el tanque a null y restamos 1 a la cantidad de tanques
            tablero[fil][col] = null;
            cantTanques--;
            
            System.out.println();
            System.out.println("FELICIDADES EL TANQUE HA SIDO DESTRUIDO EN LA CASILLA ("+fil+","+col+") !!!");
            System.out.println();
        }
        else{
            System.out.println();
            System.out.println("LA BOMBA ATOMICA HA CAIDO EN UNA CASILLA VACIA ("+fil+","+col+")");
            System.out.println();
        }
        conteoBombas++;
    }
    
    //Metodo que activa el modo 'tanque mutante' (duplicando su vida) en la casilla en la que se encuentra el tanque con menor vida
    public static void activarTanqueMutante(){
        
        Tanque aux = tanqueMenorVida();
        aux.setSalud(aux.getSalud()*2);
        
    }
    
    //Metodo que devuelve un string con la frase de la abuela si jugara tanques
    public static String fraseAbuela(){
        String aux = "'El que dispara primero dispara dos veces'";
        return aux;
    }
    
    /*
    *
    *   METODOS SECUNDARIOS
    *
    */
    
    //Metodo que evalua si unas coordenadas determinadas (fila y columna) son correctas. Devuelve true si todo esta bien y false en caso contrario
    public static boolean inputCoordenadasOk(int fil, int col){
        //Se comprueba que los valores introducidos por el usuario esten dentro de los limites del tablero
        if(fil>=0 && col>=0 && fil<TAM1 && col<TAM1 ){
            //Se verifique que en la coordenada indicada por el usuario haya un tanque
            if(tablero[fil][col]!=null){
                return true;
            }
        }    
        return false;
    }
    
    
    //Metodo que devuelve el tanque con menor vida
    public static Tanque tanqueMenorVida(){
        //Se inicializa la variable auxiliar con el primer tanque disponible
        Tanque menor = null; 
        for(int i = 0; i<TAM1; i++){
            for(int j = 0; j<TAM1; j++){
                if(tablero[i][j]!=null){
                    menor = tablero[i][j];
                    break;
                }
            }
        }
        
        for(int i = 0; i<TAM1; i++){
            for(int j = 0; j<TAM1; j++){
                if(tablero[i][j]!=null){
                    if(tablero[i][j].getSalud()<menor.getSalud()){
                        menor = tablero[i][j];
                    }
                }    
            }
        }
        return menor;
    }
    
    
    
}//Fin Main
