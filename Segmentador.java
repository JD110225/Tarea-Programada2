public class Segmentador
{
   public static final int[] sumaF={ -1,-1, 0, 1, 1, 1, 0,-1 };
   public static final int[] sumaC={  0, 1, 1, 1, 0,-1,-1,-1 };
   private Imagen imagen;
   private int[][] matrizImagen;
   private Pixel fondo;
   private Catalogo catalogo;
   public Segmentador(){
       catalogo=new Catalogo();
       imagen=new Imagen("superRandomImage.gif");
       imagen.dibujar();
       matrizImagen=imagen.getMatriz();
       fondo=new Pixel(0,0,matrizImagen[0][0]);
       /*
       for(int f=0;f<matrizImagen.length;++f){
           for(int c=0;c<matrizImagen[0].length;++c){
               System.out.print(matrizImagen[f][c]+" ");
            }
       System.out.println();
        }
        */

}
   public boolean existePixel(int fila,int columna){
       return fila>=0 && columna>=0 && fila<matrizImagen.length && columna<matrizImagen[0].length;
    }    

   public void cambiarFondo(int fila,int columna){
       matrizImagen[fila][columna]=1;
       for(Pixel p:pixelesVecinos2(fila,columna)){
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
   //Recibe posicion de un pixel y retorna vector de Pixeles vecinos  
   public Pixel[] pixelesVecinos2(int fila,int columna){ 
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
    public Pixel findBorde(int[][] matriz){
        Pixel borde=fondo; 
        boolean encontrado=false;
        for(int f=0;f<matriz.length;f++){
           for(int c=0;!encontrado&&c<matriz[0].length;c++){
               if(matriz[f][c]!=matriz[0][0]){
                   borde=new Pixel(f,c,matriz[f][c]);
                   encontrado=true;
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
        for(Pixel p:pixelesVecinos2(fila,columna)){
            if(p!=null && !estaEnMatriz(p,matriz)){
                int filaVecino=p.getFila();
                int columnaVecino=p.getColumna();
                if(matrizImagen[filaVecino][columnaVecino]!=1){
                    algoritmoExpansion(p,matriz);
            }
            }
            
    }
}
    public int[][] crearMatrizDeUnos(){
       int[][] matrizImagenNueva=new int[matrizImagen.length][matrizImagen[0].length];
       for(int f=0;f<matrizImagen.length;++f){
           for(int c=0;c<matrizImagen[0].length;++c){
               matrizImagenNueva[f][c]=1;//Hacer matriz de puros pixeles de 1 
            }
        }
       return matrizImagenNueva;
    }

    public Figura crearFigura(){
        int[][] matriz=crearMatrizDeUnos();
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
    public Catalogo getCatalogo(){
        return catalogo;
    }
    public void segmentacion(){
        cambiarFondo(0,0);
        while(!sinFiguras(matrizImagen)){
            Figura figura=crearFigura();
            quitarFigura(figura);
            catalogo.agregarFigura(figura);
            figura.centrarFigura();
        }
        catalogo.verCatalogo();
    }
}

    
    

