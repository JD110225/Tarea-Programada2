public class Segmentador{

    public static final int[] sumaF={ -1,-1, 0, 1, 1, 1, 0,-1 };
    public static final int[] sumaC={  0, 1, 1, 1, 0,-1,-1,-1 };
    private Imagen imagen;
    private int[][] matrizImagen;
    private Pixel fondo;
    private Catalogo catalogo;

    public Segmentador(){
        catalogo=new Catalogo();
        imagen=new Imagen("11.gif");
        imagen.dibujar();
        matrizImagen=imagen.getMatriz();
        fondo=new Pixel(0,0,matrizImagen[0][0]);
    }

    public boolean existePixel(int fila,int columna){
        return fila>=0 && columna>=0 && fila<matrizImagen.length && columna<matrizImagen[0].length;
    }    

    //Recibe posicion de un pixel y retorna vector de Pixeles que estan en misma figura

    public Pixel[] pixelesVecinos(int fila,int columna){ 
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

    public void cambiarFondo(int fila,int columna){
        matrizImagen[fila][columna]=1;
        for(Pixel p:pixelesVecinos(fila,columna)){
            if(p!=null){
                int filaVecino=p.getFila();
                int columnaVecino=p.getColumna();
                if(matrizImagen[filaVecino][columnaVecino]==fondo.getColor()){
                    cambiarFondo(filaVecino,columnaVecino);
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

    public void anadirPixelMatriz(Pixel pixel,int[][] matriz){
        if(pixel!=null){
            int fila=pixel.getFila();
            int columna=pixel.getColumna();
            matriz[fila][columna]=matrizImagen[fila][columna];
        }
    }

    public boolean estaEnMatriz(Pixel pixel,int [][] matriz){
        int fila=pixel.getFila();
        int columna=pixel.getColumna();
        return matriz[fila][columna]==pixel.getColor();
    }

    public void algoritmoExpansion(Pixel pixel,int[][] matriz){
        anadirPixelMatriz(pixel,matriz);
        int fila=pixel.getFila();
        int columna=pixel.getColumna();
        for(Pixel p:pixelesVecinos(fila,columna)){
            if(p!=null && !estaEnMatriz(p,matriz)){
                int filaVecino=p.getFila();
                int columnaVecino=p.getColumna();
                if(matrizImagen[filaVecino][columnaVecino]!=1){
                    algoritmoExpansion(p,matriz);
                }
            }

        }
    }

    public int[][] crearMatriz(int fila, int columna, int color){
        int[][] matrizImagenNueva=new int[fila][columna];
        for(int f = 0; f < fila ;++f){
            for(int c = 0;c < columna;++c){
                matrizImagenNueva[f][c]= color;//Hacer matriz de puros pixeles de un color
            }
        }
        return matrizImagenNueva;
    }

    public Figura crearFigura(){
        int[][] matriz = crearMatriz(matrizImagen.length, matrizImagen[0].length, 1);
        Pixel borde=findBorde(matrizImagen);
        algoritmoExpansion(borde,matriz);
        Figura figura=new Figura(matriz);
        return figura;
    }

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

    public int[] encontrarCentroMatriz(int altura, int largo){
        int [] centroMatriz = new int[2];
        int centroAltura = ((altura) / 2);
        int centroLargo = ((largo) / 2);
        centroMatriz[0] = centroAltura;
        centroMatriz[1] = centroLargo;
        return centroMatriz;
    }

    public Figura [] segmentacion(){
        cambiarFondo(0,0);
        Figura lista [] = new Figura [100];
        int contador = 0;
        while(!sinFiguras(matrizImagen)){
            Figura figura=crearFigura();
            quitarFigura(figura); 
            lista[contador++] = figura;
            //catalogo.agregarFigura(figura);
        }
        return lista;
    }

    public int datoMayorDeLista(int [] lista){//retorna el mayor numero de una lista
        int mayorDeLista = lista [0];
        for(int j = 1; j < lista.length; ++j){
            if(lista[j] > mayorDeLista){
                mayorDeLista = lista[j];
            }
        }
        return mayorDeLista;
    }

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

    public Figura [] ejecutar(){
        Imagen hola = new Imagen("11.gif");//Para Pruebas BORRAR ANTES DE ENVIAR
        int [][] pruebam = hola.getMatriz();//Para Pruebas BORRAR ANTES DE ENVIAR
        Figura prueba = new Figura(pruebam);//Para Pruebas BORRAR ANTES DE ENVIAR
        catalogo.agregarFigura(prueba);//Para Pruebas BORRAR ANTES DE ENVIAR
        ////////////Para Pruebas BORRAR ANTES DE ENVIAR
        Figura listaFiguras []  = new Figura [10];//segmentacion();//Para Pruebas BORRAR ANTES DE ENVIAR
        /////
        listaFiguras[0] = prueba;//Para Pruebas BORRAR ANTES DE ENVIAR
        //////
        Figura lista [] = new Figura [100];
        int[] dimensionesMatriz = matrizMayorTamano(listaFiguras);
        int matriz [][];

        for(int n = 0; listaFiguras[n] != null && n < listaFiguras.length; n++){
            matriz = crearMatriz(dimensionesMatriz[0], dimensionesMatriz[1], -1);
            listaFiguras[n].encontrarDimensiones();
            for(int f = 0; f < listaFiguras[n].getAltura(); f++){

                for(int c = 0; c < listaFiguras[n].getLargo(); c++){

                    matriz[f][c] = listaFiguras[n].getMatriz()[f][c];

                }
            }

            Figura temp = new Figura(listaFiguras[n].getMatriz());//figura sin segmentar
            int[][] matrizTemporal = centrarFigura(temp, matriz);
            temp.encontrarDimensiones();
            temp.encontrarArea();
            Figura figura = new Figura(matrizTemporal, temp.getDimensiones());//figura lista en recuadro grande mas sin Zoom, recibe dimensiones y area de la figura original y el escalado aplicado
            //por aquí iría el método del Zoom. DAVID ROJAS: Puede usar la matriz "matrizTemporal" para su método.
            //por aquí se agregarían las figuras al catálogo
            System.out.println(temp.getAreaFigura());
            lista[n] = figura;
        }
        return lista; //eventualmente creo que el método no retornará.
    }
}


    
    

