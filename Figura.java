public class Figura{
    private int[][] matriz;
    private Imagen dibujo;
    private int areaFigura;
    private int cantidadManchas;
    private int [] dimensiones;
    private Pixel borde;
    private Pixel colorFondo;
    private int escala;

    public Figura(int[][]matriz,int[] dimensiones,int areaFigura,int escala){
        this.matriz=matriz;
        this.dimensiones=dimensiones;
        this.areaFigura=areaFigura;
        this.escala=escala;
    }
     /*
    public Figura(int [][] matriz, int [] dimensiones){
        this.matriz=matriz;
        this.dimensiones = dimensiones;
    }
    */
    public Figura(int[][] matriz){
        this.matriz=matriz;
        dimensiones=new int[2];
    }
    public void setCantidadManchas(int cantidadManchas){
        this.cantidadManchas=cantidadManchas;
    }
    public void sumarContadorManchas(){
        ++cantidadManchas;
    }
    /**
     * Este método se encarga de encontrar las dimensiones de la figura.
     * @return extremos, un vector que contiene la informacion de los puntos máximos.
     */
    public int [] encontrarDimensiones(){
        int extremos [] = new int [4];
        Pixel arriba = encontrarPixelArriba(matriz);
        int filaArriba = arriba.getFila();
        extremos[0] = filaArriba;
        //
        Pixel abajo = encontrarPixelAbajo(matriz);
        int filaAbajo = abajo.getFila();
        extremos[1] = filaAbajo;
        //
        Pixel izquierda = encontrarPixelIzquierda(matriz);
        int columnaIzquierda = izquierda.getColumna();
        extremos[2] = columnaIzquierda;
        //
        Pixel derecha = encontrarPixelDerecha(matriz);
        int columnaDerecha = derecha.getColumna();
        extremos[3] = columnaDerecha;
        //
        int altura = filaAbajo - filaArriba;
        int largo = columnaDerecha - columnaIzquierda;
        dimensiones[0] = altura;
        dimensiones[1] = largo;
        return extremos;
    }

    /*
    public int[] encontrarCentroFigura(){
        int[] centroFigura=new int[2];
        int extremos [] = encontrarDimensiones();
        int centroAltura = ((extremos[0] + extremos[1]) / 2);
        int centroLargo = ((extremos[2] + extremos[3]) / 2);
        centroFigura[0]=centroAltura;
        centroFigura[1]=centroLargo;
        return centroFigura;
    }
*/
    /**
     * Este método se encarga de encontrar el centro de la figura.
     * @return centroFigura, un vector con el punto que sea el centro de la figura.
     */
    public int[] encontrarCentroFigura(){
        int[] centroFigura=new int[2];
        int extremos [] = encontrarDimensiones();
        int centroAltura = ((extremos[0] + extremos[1]) / 2);
        int centroLargo = ((extremos[2] + extremos[3]) / 2);
        centroFigura[0]=centroAltura;
        centroFigura[1]=centroLargo;
        return centroFigura;
    }
    public Pixel encontrarPixelArriba(int [][] matriz){
        Pixel arriba = null; 
        boolean encontrado = false;
        for(int f = 0;f < matriz.length;f++){
            for(int c = 0;!encontrado && c < matriz[0].length;c++){
                if(matriz[f][c]!= matriz[0][0]){
                    arriba = new Pixel(f,c,matriz[f][c]);
                    encontrado = true;
                }
            }
        }
        return arriba;
    }

    public Pixel encontrarPixelAbajo(int [][] matriz){
        Pixel abajo = null; 
        boolean encontrado = false;
        for(int f = (matriz.length - 1);f >= 0;f--){
            for(int c = (matriz[0].length - 1);!encontrado && c >=0;c--){
                if(matriz[f][c]!=matriz[0][0]){
                    abajo = new Pixel(f,c,matriz[f][c]);
                    encontrado=true;
                }
            }
        }
        return abajo;
    }

    public Pixel encontrarPixelIzquierda(int [][] matriz){
        Pixel izquierda = null; 
        boolean encontrado = false;
        for(int c = 0; c < matriz[0].length; c++){
            for(int f = 0; !encontrado && f < matriz.length;f++){
                if(matriz[f][c]!=matriz[0][0]){
                    izquierda = new Pixel(f,c,matriz[f][c]);
                    encontrado=true;
                }
            }
        }
        return izquierda;
    }

    public Pixel encontrarPixelDerecha(int [][] matriz){
        Pixel derecha = null; 
        boolean encontrado = false;
        for(int c = (matriz[0].length - 1); c >= 0; c--){
            for(int f = (matriz.length - 1); !encontrado && f >= 0; f--){
                if(matriz[f][c]!=matriz[0][0]){
                    derecha = new Pixel(f,c,matriz[f][c]);
                    encontrado=true;
                }
            }
        }
        return derecha;
    }

    public int[][] crearMatriz(int altura, int largo, int color){
        int[][] matrizNueva=new int[matriz.length][matriz[0].length];
        for(int f=0;f < altura; ++f){
            for(int c=0; c < largo; ++c){
                matrizNueva [f][c] = color;
            }
        }
        return matrizNueva;
    }

    public Pixel encontrarBorde(int [][] matriz){
        Pixel borde = null;
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
    public int [][] rellenarEspaciosMatriz(int [][] matriz, int color){//cambia el color del fondo
        int colorFondo = this.matriz[0][0];
        for(int f = 0; f < matriz.length; f++){
            for(int c = 0; c < matriz[0].length; c++){
                if(matriz[f][c] == colorFondo){
                    matriz[f][c] = color;
                }                
            } 
        }
        return matriz;
    }
    
    public void encontrarArea(){
        Figura segmentada = new Figura(matriz);
        segmentada.encontrarDimensiones();
        Pixel borde = segmentada.encontrarBorde(matriz);
        int colorBorde = borde.getColor();
        int borde0 = matriz[0][0];
        int area = 0;
        for(int f = 0; f < matriz.length ; f++){
            for(int c = 0; c < matriz[0].length; c++){
                if(matriz[f][c] != borde0){                    
                    area++;
                }    
            }
        }
        areaFigura = area;
    }
    
    public int getCantidadManchas(){
        return cantidadManchas;
    }
    public int getAreaFigura(){
        return areaFigura;
    }

    public int[] getDimensiones(){//puede servir en bÃºsqueda por parÃ¡metros
        return dimensiones;
    }

    public int getAltura(){
        return dimensiones[0];
    }

    public int getLargo(){
        return dimensiones[1];
    }

    public int getEscala(){
        return escala;
    }

    public int[][] getMatriz(){
        return matriz;
    }

    /*
     * Parece no tener uso alguno
     */
    public void displayMatriz(){
        for(int f=0;f<matriz.length;++f){
            for(int c=0;c<matriz[0].length;++c){
                System.out.print(matriz[f][c]+" ");
            }
            System.out.println();
        }
    }

    public void verDibujo(){
        dibujo = new Imagen(matriz);
        dibujo.dibujar();
    }
        public int [][] copiarMatriz(int [][] matriz){
        int [][] nuevaMatriz = new int [matriz.length][matriz[0].length];
        for(int f = 0; f < matriz.length; ++f){
            for(int c = 0; c < matriz[0].length; ++c){
                nuevaMatriz[f][c] = matriz[f][c];
            }
        }
        return nuevaMatriz;
    }
     /**
     * Este método se encarga de hacer zoom en la matriz brindada
     * @param matriz para saber a que matriz se le desea hacer zoom.
     * @param matriZoom la matriz destino a el zoom
     * @return matriZoom, una matriz con el zoom ya aplicado.
     */
    public int[][] hacerZoom(int[][] matriz, int[][] matriZoom){ //Creo que es posible quitar el matriz pero estoy muy cansado como para pensar en eso jeje
        for(int f = 0; f< matriz.length; ++f){
            for(int c = 0; c < matriz[f].length; ++c){
                if(matriz[f][c] != -1){
                    int pixelAZoomear = matriz[f][c];
                    matriZoom [f][c] = pixelAZoomear;
                    matriZoom [f][c-1] = pixelAZoomear;
                    matriZoom [f-1][c] = pixelAZoomear;
                    matriZoom [f-1][c-1] = pixelAZoomear;
                }
            }
        }
        return matriZoom; 
    }
    
    /**
     * Este método se encarga de determinar si es posible hacerle un zoom a la matriz.
     * @param matriz para evaluar si es posible hacer zoom en esa matriz.
     * @return sePuede, retorna si es válido hacer el zoom.
     */
    
    /**
     * Este método es el que se encarga de hacer el zoom hasta que no sea posible.
     * @param matriz para realizar el zoom con base en esa matriz.
     * @return matriZoom, devuelve la matriz con el zoom máximo posible.
     */
    public int[][] zoom (int[][] matriz){
        int [][] matriZoom = copiarMatriz(matriz);
        for(int i = 1;i<2; ++i){
            int [][] temporal = crearMatriz(matriz.length, matriz[0].length, -1);
            temporal = hacerZoom(matriz, temporal);
            matriz = copiarMatriz(temporal);
            matriZoom = copiarMatriz(temporal);
            escala = i * 2;
        }
        return matriZoom;
    }
    public int [] datos(){
        int [] datos = new int [8];
        Pixel arriba = encontrarPixelArriba(matriz);
        Pixel abajo = encontrarPixelArriba(matriz);
        Pixel izquierda = encontrarPixelIzquierda(matriz);
        Pixel derecha = encontrarPixelDerecha(matriz);
        datos[0]= arriba.getFila();
        datos[1]= arriba.getColumna();
        datos[2]= abajo.getFila();
        datos[3]= abajo.getColumna();
        datos[4]= izquierda.getFila();
        datos[5]= izquierda.getColumna();
        datos[6]= derecha.getFila();
        datos[7]= derecha.getColumna();
        return datos;
    }
    public boolean esZoomeable (int [][] matriz){
        int [] datos = datos();
        boolean zoomeable = true;
        for(int i = 1; zoomeable && i < 2; ++i){
            if(matriz[datos[0]-i][datos[1]] != -1){ //datos pixel arriba
                zoomeable = false;
            }
            else{
                if(matriz[datos[2]+i][datos[3]] != -1){ //datos pixel abajo
                    zoomeable = false;
                }
                else{
                    if(matriz[datos[4]][datos[5]-i] != -1){ // datos pixel izquierda
                        zoomeable = false;
                    }
                    else{
                        if(matriz[datos[6]][datos[7]+i] != -1){ // datos pixel derecha
                            zoomeable = false;
                        }
                    }
                }
            }
        }
        return zoomeable;
    }
    public String toString(){
        String tira = ("Manchas: " + cantidadManchas +" Altura: " + getAltura() + " Largo: " + getLargo() + " Escala: " + getEscala() + " Area " + getAreaFigura() + "\n");
        return tira;
    }
}


