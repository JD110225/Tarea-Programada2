public class Figura{
    private int[][] matriz;
    private Imagen dibujo;
    private int areaFigura;
    private int cantidadManchas;
    private int [] dimensiones;
    private Pixel borde;
    private Pixel colorFondo;
    private int escala;
    //private Pixel fondoInterno;
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
        Pixel bordeFigura = encontrarBorde(matriz);
        int colorAlternativo = -352164851;
        while(colorAlternativo == bordeFigura.getColor() || colorAlternativo == matriz[0][0]){
            colorAlternativo = (int) (Math.random() * 2000);
        }
        
        int [][] matrizDiferenteColor = matriz;
        rellenarEspaciosMatriz(matrizDiferenteColor, colorAlternativo);
        Imagen xy= new Imagen(matrizDiferenteColor);
        xy.dibujar();
        for(int f = 1; f < matrizDiferenteColor.length; f++){//podrÃ­a ser altura en vez de matriz.length
            for(int c = 1; c < matrizDiferenteColor[0].length; c++){//podrÃ­a ser largo en vez de matriz[0].length
                if(matrizDiferenteColor[f][c] != matrizDiferenteColor [0][0] && matrizDiferenteColor[f][c] != bordeFigura.getColor()){//solo sirve con figura sin manchas
                    areaFigura++;
                }
            }
        }
        
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
 public int[][] hacerZoom(int[][] matriz, int[][] matriZoom){ //Creo que es posible quitar el matriz pero estoy muy cansado como para pensar en eso jeje
        for(int f = 0; f< matriz.length; ++f){
            for(int c = 0; c < matriz[f].length; ++c){
                if(matriz[f][c] != -1){
                    matriZoom [f][c] = matriz[f][c];
                    matriZoom [f-1][c] = matriz[f][c];
                    matriZoom [f-1][c+1] = matriz [f][c];
                    matriZoom [f][c+1] = matriz[f][c];
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
        int [][] matriZoom = new int [matriz.length][matriz[0].length];
        matriZoom = matriz;
        boolean zoomValido = zoomValido(matriz);
        while (zoomValido){
            matriZoom = hacerZoom(matriz, matriZoom);
            zoomValido = zoomValido(matriZoom);
            escala += 2;
        }
        return matriZoom;
    }
}

