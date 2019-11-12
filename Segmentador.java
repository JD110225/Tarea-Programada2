public class Segmentador{

    public static final int[] sumaF={ 1,0, -1, 0, -1, 1, 1,-1 };
    public static final int[] sumaC={  0, 1, 0, -1, 1,-1,1,-1 };
    private Imagen imagen;
    private int[][] matrizImagen;
    private Pixel fondo;
    private Catalogo catalogo;
    
    public Segmentador(String archivo){
        catalogo=new Catalogo();
        imagen=new Imagen(archivo);
        //imagen.dibujar();
        matrizImagen=imagen.getMatriz();
        fondo=new Pixel(0,0,matrizImagen[0][0]);
    }
    /**
     * Este método permite saber si un pixel se encuentra en una fila y columna.
     * @param int fila para buscar el pixel en esa fila.
     * @param int columna para buscar el pixel en esa columna.
     * @return boolean existePixel devuelve el valor booleano para saber si el pixel existe.
     */
    public boolean existePixel(int fila,int columna){
        return fila>=0 && columna>=0 && fila<matrizImagen.length && columna<matrizImagen[0].length;
    }    
    /**
     * Este método permite calcular los pixeles que sean vecinos.
     * @param int fila para saber en que fila se comparan los pixeles.
     * @param int columna para saber en que columna se comparan los pixeles.
     * @return Pixel retorna un vector de los pixeles que sean vecinos.
     */
    public Pixel[] pixelesVecinos(int fila,int columna,int[][] matriz){ 
        Pixel[] pixelesVecinos=new Pixel[8];
        int contadorFilas=0;
        for(int i=0;i<8;++i){
            int filaComparar=fila+sumaF[i];
            int columnaComparar=columna+sumaC[i];
            if(existePixel(filaComparar,columnaComparar)){
                int colorPixel=matrizImagen[filaComparar][columnaComparar];
                Pixel pixelVecino=new Pixel(filaComparar,columnaComparar,colorPixel);
                pixelesVecinos[contadorFilas++]=pixelVecino;                          
            }
        }   
        return pixelesVecinos;
    }
    //Se le agrego parametro de matriz para que funcione en conjutno con pixelesVecinos.
    public Pixel[] vecinosBorde(int fila,int columna,int[][] matriz){
        Pixel[] pixelesVecinos=new Pixel[4]; //el de arriba y el de la derecha.
        int contadorFilas=0;
        for(int i=0;i<4;++i){
            int filaComparar=fila+sumaF[i];
            int columnaComparar=columna+sumaC[i];
            if(existePixel(filaComparar,columnaComparar)){
                int colorPixel=matrizImagen[filaComparar][columnaComparar];
                Pixel pixelVecino=new Pixel(filaComparar,columnaComparar,colorPixel);
                pixelesVecinos[contadorFilas++]=pixelVecino;
        }
    }
        return pixelesVecinos;
}
        /**
     * Este método se encarga de cambiar el fondo de la imagen.
     * @param fila para ubicar los pixeles en la fila
     * @param columna para ubicar los pixeles en la columna
     */
    public void cambiarFondo(int fila,int columna,int[][] matriz){
        matrizImagen[fila][columna]=1;
        for(Pixel p: vecinosBorde(fila,columna,matriz)){
            if(p!=null){
                int filaVecino=p.getFila();
                int columnaVecino=p.getColumna();
                if(matrizImagen[filaVecino][columnaVecino]==fondo.getColor()){
  
                    cambiarFondo(filaVecino,columnaVecino,matriz);
        }
                }
            }
        }
    public void displayMatriz(){
        for(int f=0;f<matrizImagen.length;++f){
            for(int c=0;c<matrizImagen[0].length;++c){
                System.out.print(matrizImagen[f][c]+" ");
            }
            System.out.println();
        }
    }
    /**
     * Este método se encarga de encontrar el borde en una matriz.
     * @param int[][] matriz, para tomar los pixeles que conforman la imagen.
     * @return borde, retorna un pixel que sea parte del borde de la figura.
     */
    public Pixel findBorde(int [][] matriz){
        Pixel borde=fondo; 
        boolean encontrado=false;
        for(int f = 0;f < matriz.length; f++){
            for(int c = 0;!encontrado && c < matriz[0].length; c++){
                if(matriz[f][c] != matriz[0][0]){
                    borde = new Pixel(f,c,matriz[f][c]);
                    encontrado = true;
                }
            }
        }
        return borde;
    }
    /**
     * Este método se encarga de añadir un pixel a la matriz.
     * @param Pixel pixel para saber cual es el pixel que se desea añadir a la matriz.
     * @param int[][] matriz, para saber en la matriz que desea añadir el pixel.
     */
    public void anadirPixelMatriz(Pixel pixel,int[][] matriz){
        if(pixel!=null){
            int fila=pixel.getFila();
            int columna=pixel.getColumna();
            matriz[fila][columna]=matrizImagen[fila][columna];
        }
    }
    /**
     * Este método es para saber si un pixel se encuentra en una matriz.
     * @param Pixel pixel, para saber que pixel debe buscar.
     * @param int[][] matriz, para saber en que matriz se debe buscar.
     * @return boolean, el resultado de si el pixel se encuentra la matriz.
     */
    public boolean estaEnMatriz(Pixel pixel,int [][] matriz){
        int fila=pixel.getFila();
        int columna=pixel.getColumna();
        return matriz[fila][columna]==pixel.getColor();
    }
    /**
     * Este método permite, mediante un proceso recursivo, 
     * @param Pixel pixel, para saber en que pixel 
     * @param int[][] matriz, 
     */
    public void algoritmoExpansion(Pixel pixel,int[][] matriz){
        anadirPixelMatriz(pixel,matriz);
        int fila=pixel.getFila();
        int columna=pixel.getColumna();
        for(Pixel p:pixelesVecinos(fila,columna,matriz)){
            if(p!=null && !estaEnMatriz(p,matriz)){
                int filaVecino=p.getFila();
                int columnaVecino=p.getColumna();
                if(matrizImagen[filaVecino][columnaVecino]!=1){
                    algoritmoExpansion(p,matriz);
                }
            }

        }
    }
    
    /**
     * Este método permite crear una matriz con las caracteristicas deseadas.
     * @param int fila, permite saber de cuantas filas se quiere la matriz.
     * @param int columna, permite saber de cuantas columnas se quiere la matriz.
     * @param int color, permite saber con que color se va a crear la matriz.
     * @return matrizImagenNueva, una matriz nueva con los valores indicados.
     */
    public int[][] crearMatriz(int fila, int columna, int color){
        int[][] matrizImagenNueva=new int[fila][columna];
        for(int f = 0; f < fila ;++f){
            for(int c = 0;c < columna;++c){
                matrizImagenNueva[f][c]= color;//Hacer matriz de puros pixeles de un color
            }
        }
        return matrizImagenNueva;
    }
    /**
     * Este metodo permite, con ayuda de otros métodos, crear una figura.
     * @return figura, retorna la figura creada.
     */
    public Figura crearFigura(){
        int[][] matriz = crearMatriz(matrizImagen.length, matrizImagen[0].length, 1);
        Pixel borde=findBorde(matrizImagen);
        algoritmoExpansion(borde,matriz);
        Figura figura=new Figura(matriz);
        return figura;
    }
  /**
     * Este método elimina una figura de su respectiva matriz.
     * @param Figura figura, para saber a que figura se le realiza el proceso.
     */
    public void quitarFigura(Figura figura){
        int[][] matrizFigura=figura.getMatriz();
        for(int f=0;f<matrizFigura.length;++f){
            for(int c=0;c<matrizFigura[0].length;++c){
                int color=matrizFigura[f][c];
                if(color==matrizImagen[f][c]){
                    matrizImagen[f][c]=1;
                }
            }
        }
    }
    /**
     * Este método permite saber si una matriz contiene o no una figura.
     * @param int[][] matriz, para evaluar sobre la matriz que se indique.
     * @return sinFiguras, devuelve el resultado del proceso.
     */
    public boolean sinFiguras(int[][] matriz){
        boolean sinFiguras=true;
        int inicial=matriz[0][0];
        for(int f=0;f<matriz.length;++f){
            for(int c=0;c<matriz[0].length;++c){
                if(matriz[f][c]!=inicial){
                    sinFiguras=false;
                }
            }
        }
        return sinFiguras;
    }
    /**
     * Este método permite encontrar el centro de una matriz.
     * @param int altura, para saber la altura que tiene la matriz. 
     * @param int largo, para saber el largo que tiene la matriz
     * @return centroMatriz, que es un vector que contiene cual es el centro de la altura y del largo.
     */
    public int[] encontrarCentroMatriz(int altura, int largo){
        int [] centroMatriz = new int[2];
        int centroAltura = ((altura) / 2);
        int centroLargo = ((largo) / 2);
        centroMatriz[0] = centroAltura;
        centroMatriz[1] = centroLargo;
        return centroMatriz;
    }
    /**
     * Este metodo se encarga de segmentar las figuras.
     * @return lista, devuelve una lista con las figuras segmentadas.
     */
    public Figura [] segmentacion(){
        cambiarFondo(0,0,matrizImagen);
        Figura lista [] = new Figura [100];
        int contador = 0;
        while(!sinFiguras(matrizImagen)){
            Figura figura=crearFigura();
            quitarFigura(figura); 
            lista[contador++] = figura;
        }
        //System.out.println(contador);
        return lista;
    }
    public Figura[] quitarFondoFiguras(){ //quita el fondo negro y las centra.
        Figura[] listaFiguras=segmentacion();     //Todas estas tienen un fondo negro...hay que ahora pasarlo a que sea blanco.
        Figura[] figurasSinFondo=new Figura[listaFiguras.length];
        int contador=0;
        for(Figura figura: listaFiguras){
            if(figura!=null){
                int[][] matrizFigura=figura.getMatriz();
                int[][] copiaMatrizFigura=new int[matrizFigura.length][matrizFigura[0].length];
                for(int f=0;f<copiaMatrizFigura.length;++f){
                    for(int c=0;c<copiaMatrizFigura[0].length;++c){
                        if(matrizFigura[f][c]==1){
                            copiaMatrizFigura[f][c]=fondo.getColor();
                        }
                        else{
                            copiaMatrizFigura[f][c]=matrizFigura[f][c];
                        }
                    }
                }
                Figura nuevaFigura=new Figura(copiaMatrizFigura);  //Una nueva figura pero con fondo blanco y ya con las dimensiones averiguadas
                int cantidadManchas=encontrarNumeroManchas(figura);
                nuevaFigura.setCantidadManchas(cantidadManchas);
                figurasSinFondo[contador++]=nuevaFigura;
            }
        }
        
        return figurasSinFondo;
    }
        /**
     * Este método permite determinar el numero que sea mayor en una lista.
     * @param int[] lista, para evaluar los elementos de la lista-
     * @return mayorDeLista, el numero mayor en la lista introducida.
     */
    public int datoMayorDeLista(int [] lista){//retorna el mayor numero de una lista
        int mayorDeLista = lista [0];
        for(int j = 1; j < lista.length; ++j){
            if(lista[j] > mayorDeLista){
                mayorDeLista = lista[j];
            }
        }
        return mayorDeLista;
    }
    /**
     * Este método encuentra la matriz de mayor tamaño entre la lista de figuras.
     * @param Figura [] listaFiguras, para evaluar esta lista.
     * @return maximaMatriz, retorna los valores maximos de la matriz.
     */
    public int [] matrizMayorTamano(Figura [] listaFiguras){//encuentra la matriz de mayor tamano entre la lista de figuras
        int [] largos = new int [listaFiguras.length];
        int [] alturas = new int [listaFiguras.length];

        boolean finalizado=false;

        for(int i = 0;!finalizado && i< listaFiguras.length; ++i){ 
            if(listaFiguras == null){
                finalizado = true;
            }
            if(listaFiguras[i] != null){
                listaFiguras[i].encontrarDimensiones();
                largos[i] = listaFiguras[i].getLargo();
                alturas[i] = listaFiguras[i].getAltura();
            }

        }

        int mayorLargo = datoMayorDeLista(largos);

        int mayorAltura = datoMayorDeLista(alturas);

        int [] maximaMatriz = new int [2];
        maximaMatriz[0] = mayorAltura; //podría ser necesario sumar algo de borde más adelante.
        maximaMatriz[1] = mayorLargo;

        return maximaMatriz;
    }
// TODO LO REFERENTE A MANCHAS A CONTINUACION
    public boolean esParteMancha(int f,int c,int[][] matrizFigura){
        return matrizFigura[f][c]!=-123456789 && matrizFigura[f][c]!=1 && matrizFigura[f][c]!=encontrarFondoInterno(matrizFigura).getColor(); //
    }
    public Pixel encontrarMancha(int[][] matrizFigura){
        boolean encontrada=false;
        Pixel mancha=null; //En realidad es el primer pixel de una mancha 
        for(int f=0;f<matrizFigura.length;++f){
            for(int c=0;!encontrada && c<matrizFigura[0].length;++c){
                if(esParteMancha(f,c,matrizFigura)){
                    mancha =new Pixel(f,c,matrizFigura[f][c]);
                    encontrada=true;
                }
            }
        }
        return mancha;
    }
    public void quitarPixelesMancha(int fila,int columna,int[][] matrizFigura){
        matrizFigura[fila][columna]=1; //ponerlo del color de fondo.
        for(Pixel p:pixelesVecinos(fila,columna,matrizFigura)){
            if(p!=null){
                int filaVecino=p.getFila();
                int columnaVecino=p.getColumna();
                if(esParteMancha(filaVecino,columnaVecino,matrizFigura)){
                    quitarPixelesMancha(filaVecino,columnaVecino,matrizFigura);
                }
            }
        }        
    }

    public boolean hayManchas(int[][] matrizFigura){
        boolean hayManchas=false;
        for(int f=0;f<matrizFigura.length;++f){
            for(int c=0;!hayManchas&&c<matrizFigura[0].length;++c){        
                if(esParteMancha(f,c,matrizFigura)){
                    hayManchas=true;
                }
            }
}
        return hayManchas;
}
    public Pixel encontrarFondoInterno(int[][] matrizFigura){
        boolean encontrado=false;
        Pixel fondo=null;
        for(int f=0; f<matrizFigura.length;++f){
            for(int c=0;!encontrado && c<matrizFigura[0].length;++c){
                if(matrizFigura[f][c]!=1 && matrizFigura[f][c]!=-123456789){ //Si no es igual al fondo/borde
                    fondo=new Pixel(f,c,matrizFigura[f][c]);
                    encontrado=true;
                }                
            }
        }
        return fondo;
    }
    public void pintarBorde(int fila,int columna,int[][] matrizFigura,Pixel borde){
        matrizFigura[fila][columna]=-123456789;//color Exotico xD xD
        for(Pixel p:pixelesVecinos(fila,columna,matrizFigura)){
            if(p!=null){
                int filaVecino=p.getFila();
                int columnaVecino=p.getColumna();
                if(matrizFigura[filaVecino][columnaVecino]==borde.getColor()){
                    pintarBorde(filaVecino,columnaVecino,matrizFigura,borde);
                }
            }
        }        
    }
    public int encontrarNumeroManchas(Figura figura){
        if(figura!=null){
            int[][] matrizFigura=figura.getMatriz();                
            Pixel borde=findBorde(matrizFigura);
            pintarBorde(borde.getFila(),borde.getColumna(),matrizFigura,borde);
            Pixel fondoInterno=encontrarFondoInterno(matrizFigura);     
            while(hayManchas(matrizFigura)){
                quitarPixelesMancha(encontrarMancha(matrizFigura).getFila(),encontrarMancha(matrizFigura).getColumna(),matrizFigura);  //Que viva el Python
                figura.sumarContadorManchas();
        }
        }
        return figura.getCantidadManchas();
    }
    /**
     * Este método se encarga de centrar una figura.
     * @param Figura figura, para saber que figura debe centrar.
     * @param int[][] matriz, para crear una nueva matriz con el tamaño correcto.
     * @return matrizNueva, la matriz de la figura centrada.
     */
    public int[][] centrarFigura(Figura figura, int [][] matriz){
        int[][] matrizNueva= crearMatriz(matriz.length, matriz[0].length, -1);
        int[] centroFigura = figura.encontrarCentroFigura();
        int[] centroMatriz = encontrarCentroMatriz(matriz.length, matriz[0].length);
        int distanciaFilas = centroMatriz[0] - centroFigura[0];
        int distanciaColumnas = centroMatriz[1] - centroFigura[1];

        for(int f=0; f < matriz.length; ++f){
            for(int c=0; c < matriz[0].length; ++c){
                if(matriz [f][c]!=-1){
                    int nuevaFila = f + distanciaFilas;
                    int nuevaColumna = c + distanciaColumnas;
                    matrizNueva[nuevaFila][nuevaColumna] = matriz[f][c];
                
                }
            }
        }
        return matrizNueva;
    }
    public int [][] subirFigura(Figura figura){
        figura.encontrarDimensiones();
        int[][] matrizNueva= crearMatriz(figura.getMatriz().length, figura.getMatriz()[0].length, -1);
        int arriba = figura.encontrarPixelArriba(figura.getMatriz()).getFila();
        int izquierda = figura.encontrarPixelIzquierda(figura.getMatriz()).getColumna();
        int distanciaFilas = - arriba;
        int distanciaColumnas = - izquierda;
        for(int f = 0; f < figura.getMatriz().length; ++f){
            for(int c = 0; c < figura.getMatriz()[0].length; ++c){
                if(figura.getMatriz() [f][c]!= figura.getMatriz() [0][0]){
                    int nuevaFila = f + distanciaFilas;
                    int nuevaColumna = c + distanciaColumnas;
                    matrizNueva[nuevaFila][nuevaColumna] = figura.getMatriz()[f][c];
                }
            }
        }
        return matrizNueva;
    }

    public void ejecutarDOS(){
         Figura[] listaFiguras=quitarFondoFiguras();
         for(Figura f:listaFiguras){
             if(f!=null){
                 f.encontrarArea();
                 f.encontrarDimensiones();
                 //f.zoom(f.getMatriz());
                 //int[][] matrizZoomeada=f.zoom(f.getMatriz());
                 //Imagen zoomeada=new Imagen(matrizZoomeada);
                 //Figura nueva=new Figura(matrizZoomeada,f.getDimensiones(),f.getAreaFigura(),f.getEscala());
                 //No funciona figura nueva
                 catalogo.agregarFigura(f);
 
            }
            }
         catalogo.ordenamientoFiguras();
    }
    public Catalogo getCatalogo(){
        return catalogo;
}
    public void ejecutarUNO(){
        Figura[] listaFiguras=quitarFondoFiguras(); 
        int[] dimensionesMatriz=matrizMayorTamano(listaFiguras);
        for(int i=0;listaFiguras[i]!=null && i<listaFiguras.length;++i){
            Figura figura=listaFiguras[i];
            int[][] matrizFigura=subirFigura(figura);
            int[][] matrizIntermedia=crearMatriz(dimensionesMatriz[0],dimensionesMatriz[1],-1);            
            for(int f=0;f<matrizIntermedia.length;++f){
                for(int c=0;c<matrizIntermedia[0].length;++c){ //hmm
                        matrizIntermedia[f][c]=matrizFigura[f][c];
                }
            }
            Figura intermedia=new Figura(matrizIntermedia);
            int[][] matrizTemporal=centrarFigura(intermedia,matrizIntermedia);
            Figura FINAL= new Figura(matrizTemporal);
            if(FINAL!=null){
                FINAL.encontrarArea();
                FINAL.encontrarDimensiones();
                int[][] matrizZoomeada=FINAL.zoom(FINAL.getMatriz());
                Imagen zoomeada=new Imagen(matrizZoomeada);
                Figura nueva=new Figura(matrizZoomeada,FINAL.getDimensiones(),FINAL.getAreaFigura(),FINAL.getEscala());
                catalogo.agregarFigura(nueva);
        }
        }
        catalogo.ordenamientoFiguras();
        //catalogo.verCatalogo();
    }
}


    




    
