public class Pruebas
{
   public static final int[] sumaF={ -1,-1, 0, 1, 1, 1, 0,-1 };
   public static final int[] sumaC={  0, 1, 1, 1, 0,-1,-1,-1 };
   private Imagen imagen;
   private int[][] matrizImagen;
   private Pixel fondo;
   private Catalogo catalogo;
   public Pruebas(){
       catalogo=new Catalogo();
       imagen=new Imagen("ImagenPrueba.gif");
       imagen.dibujar();
       matrizImagen=imagen.getMatriz();
       fondo=new Pixel(0,0,matrizImagen[0][0]);
       for(int f=0;f<matrizImagen.length;++f){
           for(int c=0;c<matrizImagen[0].length;++c){
               System.out.print(matrizImagen[f][c]+" ");
            }
       System.out.println();
        }

}
   public boolean existePixel(int fila,int columna){
       return fila>=0 && columna>=0 && fila<matrizImagen.length && columna<matrizImagen[0].length;
    }    
   //Recibe posicion de un pixel y retorna vector de Pixeles que son vecinos e iguales en valor.
   public Pixel[] vecinosIgualesBorde(int fila,int columna){ 
       Pixel[] pixelesVecinos=new Pixel[8];
       int contadorFilas=0;
       int[] sumaF={ -1,-1, 0, 1, 1, 1, 0,-1 };
       int[] sumaC={  0, 1, 1, 1, 0,-1,-1,-1 };
       for(int i=0;i<8;++i){
           int filaComparar=fila+sumaF[i];
           int columnaComparar=columna+sumaC[i];
           if(existePixel(filaComparar,columnaComparar)){
               int colorPixel=matrizImagen[filaComparar][columnaComparar];
                Pixel pixelVecino=new Pixel(filaComparar,columnaComparar,colorPixel);
                if(colorPixel==matrizImagen[fila][columna]){
                    pixelesVecinos[contadorFilas++]=pixelVecino;               
                }
   }
}   
        return pixelesVecinos;
}
  public Pixel[] vecinosIgualesFondo(int fila,int columna){ 
      Pixel[] pixelesVecinos=new Pixel[4];
      int contadorFilas=0;
      for(int i=0;i<4;++i){
          int filaComparar=fila+sumaF[i];
          int columnaComparar=columna+sumaC[i];
          if(existePixel(filaComparar,columnaComparar)){
               int colorPixel=matrizImagen[filaComparar][columnaComparar];
               Pixel pixelVecino=new Pixel(filaComparar,columnaComparar,colorPixel);
               if(colorPixel==matrizImagen[fila][columna]){
                   pixelesVecinos[contadorFilas++]=pixelVecino;               
               }
          }
      }   
      return pixelesVecinos;
   }
    public Pixel findBorde(){
        Pixel borde=fondo; 
        boolean encontrado=false;
        for(int f=0;f<matrizImagen.length;f++){
           for(int c=0;!encontrado&&c<matrizImagen[0].length;c++){
               if(matrizImagen[f][c]!=matrizImagen[0][0]){
                   borde=new Pixel(f,c,matrizImagen[f][c]);
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
            matriz[fila][columna]=pixel.getColor();
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
        for(Pixel p:vecinosIgualesBorde(fila,columna)){
            if(p!=null && !estaEnMatriz(p,matriz)){
                algoritmoExpansion(p,matriz);
            }
    }
}
    public int[][] crearMatrizBlanca(){
       int[][] matrizImagenNueva=new int[matrizImagen.length][matrizImagen[0].length];
       for(int f=0;f<matrizImagen.length;++f){
           for(int c=0;c<matrizImagen[0].length;++c){
               matrizImagenNueva[f][c]=-1;//Hacer matriz de puros pixeles blancos
            }
        }
       return matrizImagenNueva;
    }
    public Figura crearFigura(){
        int[][] matriz=crearMatrizBlanca();
        Pixel borde=findBorde();
        algoritmoExpansion(borde,matriz);
        Figura figura=new Figura(matriz);
        return figura;
}
    public void quitarFigura(Figura figura){
        int[][] matrizFigura=figura.getMatriz();
        for(int f=0;f<matrizImagen.length;++f){
            for(int c=0;c<matrizImagen[0].length;++c){
                int color=matrizImagen[f][c];
                if(color==matrizFigura[f][c] && color!=fondo.getColor()){
                    matrizImagen[f][c]=fondo.getColor();
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
    public void segmentacion(){
        while(!sinFiguras(matrizImagen)){
            Figura figura=crearFigura();
            quitarFigura(figura);
            catalogo.agregarFigura(figura);
        }
        catalogo.verCatalogo();
    }
}

    
    

