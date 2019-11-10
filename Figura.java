public class Figura{
    private int[][] matriz;
    private Imagen dibujo;
    private int areaFigura;
    private int cantidadManchas;
    private int [] dimensiones;
    //private Pixel borde;
    private Pixel colorFondo;
    private int escala;
    /*
     * este constructor lo usaremos mas adelante creo...Yo no...-KABU09
    public Figura(int[][]matriz,Pixel borde,Pixel colorFondo){
    this.matriz=matriz;
    this.borde=borde;
    this.colorFondo=colorFondo;
    }
     */
    public Figura(int [][] matriz, int [] dimensiones){
        this.matriz=matriz;
        this.dimensiones = dimensiones;
    }
    
    
    public Figura(int [][] matriz){
        this.matriz=matriz;
        dimensiones = new int [2];
        //this.dimensiones = dimensiones;
    }

    /**
     * Este método se encarga de encontrar las dimensiones de la figura.
     * @return extremos, un vector que contiene la informacion de los puntos máximos.
     */
    public void encontrarDimensiones(){
        Pixel arriba = encontrarPixelArriba(matriz);
        int filaArriba = arriba.getFila();
        //
        Pixel abajo = encontrarPixelAbajo(matriz);
        int filaAbajo = abajo.getFila();
        //
        Pixel izquierda = encontrarPixelIzquierda(matriz);
        int columnaIzquierda = izquierda.getColumna();
        //
        Pixel derecha = encontrarPixelDerecha(matriz);
        int columnaDerecha = derecha.getColumna();
        //
        int altura = filaAbajo - filaArriba;
        int largo = columnaDerecha - columnaIzquierda;
        dimensiones[0] = altura;
        dimensiones[1] = largo;
    }

    /**
     * Este método se encarga de encontrar el centro de la figura.
     * @return centroFigura, un vector con el punto que sea el centro de la figura.
     */
    public int[] encontrarCentroFigura(){
        int[] centroFigura=new int[2];
        int centroAltura = ((dimensiones[0]) / 2);
        int centroLargo = ((dimensiones[1]) / 2);
        centroFigura[0]=centroAltura;
        centroFigura[1]=centroLargo;
        return centroFigura;
    }

    /**
     * Este método encuentra el pixel que se encuentra más arriba.
     * @param int[][]matriz, para evaluar los pixeles en esa matriz.
     * @return arriba, devuelve el pixel más arriba.
     */
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

    /**
     * Este método encuentra el pixel que se encuentra más abajo.
     * @param int[][]matriz, para evaluar los pixeles en esa matriz.
     * @return abajo, devuelve el pixel más abajo.
     */
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

    /**
     * Este método encuentra el pixel que se encuentra más a la izquierda.
     * @param int[][]matriz, para evaluar los pixeles en esa matriz.
     * @return izquierda, devuelve el pixel más a la izquierda.
     */
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

     /**
     * Este método encuentra el pixel que se encuentra más a la derecha.
     * @param int[][]matriz, para evaluar los pixeles en esa matriz.
     * @return derecha, devuelve el pixel más hacia la derecha.
     */
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

    /**
     * Este método crea una matriz con las especificaciones brindadas.
     * @param int altura, para saber de que altura se desea la matriz.
     * @param int largo, para saber de que largo se desea la matriz.
     * @param int color, para saber de que color se desea la matriz.
     * @return matrizNueva, la matriz resultante del proceso.
     */
    public int[][] crearMatriz(int altura, int largo, int color){
        int[][] matrizNueva=new int[matriz.length][matriz[0].length];
        for(int f=0;f < altura; ++f){
            for(int c=0; c < largo; ++c){
                matrizNueva [f][c] = color;
            }
        }
        return matrizNueva;
    }

    /**
     * Este método se encarga de encontrar un pixel que sea parte del borde en una matriz que se brinde.
     * @param int[][] matriz, para evaluar los pixeles en esa matriz.
     * @return Pixel, un pixel que sea parte del borde de la matriz.
     */
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
    public int [][] copiarMatriz(){
        int [][] nuevaMatriz = new int [matriz.length][matriz[0].length];
        for(int f = 0; f < matriz.length; ++f){
            for(int c = 0; c < matriz[0].length; ++c){
                nuevaMatriz[f][c] = matriz[f][c];
            }
        }
        return nuevaMatriz;
    }
    /**
     * Este método se encarga de encontrar el área de una figura.
     */
    public void encontrarArea(){
        int [][] nuevaMatriz = crearMatriz(matriz.length, matriz[0].length, -31455485);
        nuevaMatriz = copiarMatriz();
    }
    
    public int getAreaFigura(){
        return areaFigura;
    }

    public int[] getDimensiones(){//puede servir en búsqueda por parámetros
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
     * Este metodo se encarga de mostrar la matriz en la pantalla.
     */
    public void displayMatriz(){ //Parece no tener uso alguno
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
                    matriZoom [f][c+1] = pixelAZoomear;
                    matriZoom [f+1][c] = pixelAZoomear;
                    matriZoom [f][c+1] = pixelAZoomear;
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
    public boolean zoomValido(int [][] matriz){
        boolean sePuede = true;
        Pixel arriba = encontrarPixelArriba(matriz);
        Pixel abajo = encontrarPixelArriba(matriz);
        Pixel izquierda = encontrarPixelIzquierda(matriz);
        Pixel derecha = encontrarPixelDerecha(matriz);
        int [] datosArriba = new int [2];
        datosArriba[0] = arriba.getFila();
        datosArriba[1] = arriba.getColumna();
        int [] datosAbajo = new int [2];
        datosAbajo[0] = abajo.getFila();
        datosAbajo[1] = abajo.getColumna();
        int [] datosIzquierda = new int [2];
        datosIzquierda[0] = izquierda.getFila();
        datosIzquierda[1] = izquierda.getColumna();
        int [] datosDerecha = new int [2];
        datosDerecha[0] = derecha.getFila();
        datosDerecha[1] = derecha.getColumna();
        for(int i = 1; sePuede && i < 4; ++i){
            if(matriz[datosArriba[0]-i][datosArriba[1]] == -1){
                sePuede = false;
            }
            else{
               if(matriz[datosAbajo[0]+i][datosAbajo[1]] == -1){
                   sePuede = false;
               }
               else{
                   if(matriz[datosIzquierda[0]][datosIzquierda[1]-i] == -1){
                       sePuede = false;
                   }
                   else{
                       if(matriz[datosDerecha[0]][datosDerecha[1]+i] == -1){
                           sePuede = false;
                       }
                   }
               }
             }
         }
        return sePuede;
    }
    
    /**
     * Este método es el que se encarga de hacer el zoom hasta que no sea posible.
     * @param matriz para realizar el zoom con base en esa matriz.
     * @return matriZoom, devuelve la matriz con el zoom máximo posible.
     */
    public int[][] zoom (int[][] matriz){
        int [][] matriZoom = crearMatriz(matriz.length,matriz[0].length, -1);
        boolean zoomValido = zoomValido(matriz);
        int c = 0;
        while (c < 4){
            matriZoom = hacerZoom(matriz, matriZoom);
            zoomValido = zoomValido(matriZoom);
            escala += 2;
            c++;
        }
        return matriZoom;
    }
}