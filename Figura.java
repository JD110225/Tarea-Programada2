public class Figura{
    private int[][] matriz;
    private Imagen dibujo;
    private int areaPixeles;
    private int cantidadManchas;
    private int [] dimensiones;
    private Pixel borde;
    private Pixel colorFondo;
    private int escala;
    /*
     * este constructor lo usaremos mas adelante creo...
    public Figura(int[][]matriz,Pixel borde,Pixel colorFondo){
    this.matriz=matriz;
    this.borde=borde;
    this.colorFondo=colorFondo;
    }
     */
    public Figura(){
        this.matriz=matriz;
        dimensiones = new int [2];
    }

    public int [] encontrarDimensiones(){
        int extremos [] = new int [4];
        Imagen a1 = new Imagen("9.gif");//ToDo remover.
        int m [][] = a1.getMatriz();
        //
        Pixel arriba = encontrarPixelArriba(m);
        int filaArriba = arriba.getFila();
        extremos[0] = filaArriba;
        //
        Pixel abajo = encontrarPixelAbajo(m);
        int filaAbajo = abajo.getFila();
        extremos[1] = filaAbajo;
        //
        Pixel izquierda = encontrarPixelIzquierda(m);
        int columnaIzquierda = izquierda.getColumna();
        extremos[2] = columnaIzquierda;
        //
        Pixel derecha = encontrarPixelDerecha(m);
        int columnaDerecha = derecha.getColumna();
        extremos[3] = columnaDerecha;
        //
        int altura = filaAbajo - filaArriba;
        int largo = columnaDerecha - columnaIzquierda;
        dimensiones[0] = altura;
        dimensiones[1] = largo;
        return extremos;
    }

    public void encontrarCentroFigura(){
        int extremos [] = encontrarDimensiones();
        int centroAltura = ((extremos[0] + extremos[1]) / 2);
        int centroLargo = ((extremos[2] + extremos[3]) / 2);
    }

    public void encontrarCentroMatriz(){
        Imagen a1 = new Imagen("9.gif");//ToDo remover.
        int m [][] = a1.getMatriz(); 
        int centroAltura = ((m.length) / 2);
        int centroLargo = ((m[0].length) / 2);
        m[centroAltura][centroLargo] = - 916513156;
        Imagen a2 = new Imagen(m);
        a2.dibujar();
    }

    public Pixel encontrarPixelArriba(int [][] matriz){
        Pixel arriba = null; 
        boolean encontrado=false;
        for(int f=0;f<matriz.length;f++){
            for(int c=0;!encontrado&&c<matriz[0].length;c++){
                if(matriz[f][c]!=matriz[0][0]){
                    arriba = new Pixel(f,c,matriz[f][c]);
                    encontrado=true;
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

    public int getAreaPixeles(){
        return areaPixeles;
    }

    public int[] getDimensiones(){
        return dimensiones;
    }

    public int getEscala(){
        return escala;
    }

    public int[][] getMatriz(){
        return matriz;
    }

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
}
