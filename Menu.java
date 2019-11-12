
public class Menu{
    private Interfaz interfaz;
    private Catalogo catalogo;
    
    public Menu(){
        interfaz = new Interfaz();
    }
    
    public void iniciar(){
        int menu = 0;
        while(menu !=7){//numero de opciones
            menu = interfaz.pedirEntero("Digite una opcion: \n 1. Ver catalogo completo \n 2. Ver imagen segun numero de figura \n 3. Ver imagenes en rango segun cantidad de manchas \n 4. Ver imagenes en rango segun escala \n 5. Ver imagenes en rango segun dimension original \n 6. Ver imagenes en rango segun area \n 7. Salir");
            switch(menu){
                case 1://catalogo completo
                    catalogo.verCatalogo();
                break;
                
                case 2://numero de figura
                    int entero = -1;
                    while(entero == -1){
                        entero = interfaz.pedirEntero("Ingrese el numero de figura que desea ver: ");
                    }
                    catalogo.imagenPorNumero(entero);
                break;
                
                case 3://cantidad de manchas
                    int minimoManchas = -1;
                    while(minimoManchas == -1){
                        minimoManchas = interfaz.pedirEntero("Ingrese el rango minimo de manchas de la figura que desea ver: ");
                    }
                    int maximoManchas = -1;
                    while(maximoManchas == -1){
                        maximoManchas = interfaz.pedirEntero("Ingrese el rango maximo de manchas de la figura que desea ver: ");
                    }
                    catalogo.imagenRangoManchas(minimoManchas,maximoManchas);
                break;
                
                case 4://escala
                    int minimoEscala = -1;
                    while(minimoEscala == -1){
                        minimoEscala = interfaz.pedirEntero("Ingrese el rango minimo de escala de la figura que desea ver: ");
                    }
                    int maximoEscala = -1;
                    while(maximoEscala == -1){
                        maximoEscala = interfaz.pedirEntero("Ingrese el rango maximo de escala de la figura que desea ver: ");
                    }
                    catalogo.imagenRangoEscala(minimoEscala, maximoEscala);
                break;
                
                case 5://dimension
                    int minimoAncho = -1;
                    while(minimoAncho == -1){
                        minimoAncho = interfaz.pedirEntero("Ingrese el ancho minimo de la figura que desea ver: ");
                    }
                    int maximoAncho = -1;
                    while(maximoAncho == -1){
                        maximoAncho = interfaz.pedirEntero("Ingrese el ancho maximo de la figura que desea ver: ");
                    }
                    int minimoAltura = -1;
                    while(minimoAltura == -1){
                        minimoAltura = interfaz.pedirEntero("Ingrese la altura minima de la figura que desea ver: ");
                    }
                    int maximoAltura = -1;
                    while(maximoAltura == -1){
                        maximoAltura = interfaz.pedirEntero("Ingrese la altura maxima de la figura que desea ver: ");
                    }
                    catalogo.imagenRangoDimension(minimoAncho, maximoAncho, minimoAltura, maximoAltura);
                break;
                
                case 6: //area
                    int minimoArea = -1;
                    while(minimoArea == -1){
                        minimoArea = interfaz.pedirEntero("Ingrese la area minima de la figura que desea ver: ");
                    }
                    int maximoArea = -1;
                    while(maximoArea == -1){
                        maximoArea = interfaz.pedirEntero("Ingrese la area maxima de la figura que desea ver: ");
                    }
                    catalogo.imagenRangoArea(minimoArea,maximoArea);
                break;
                
                case 7://salida
                break;
      
                default:
                interfaz.mostrarError("Digite una opcion valida");
            }
        }
    }
}
