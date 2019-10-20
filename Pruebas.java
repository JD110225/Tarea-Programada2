public class Pruebas
{
   public static final int[] sumaF={ -1,-1, 0, 1, 1, 1, 0,-1 };
   public static final int[] sumaC={  0, 1, 1, 1, 0,-1,-1,-1 };
   private Imagen imagen;
   private int[][] matrizImagen;
   private int[][] matrizImagenNueva; 
   public Pruebas(){       
       matrizImagen=new int[][]{{1,1,1,2,2,1,1,1,1,2,2,2,2},{1,1,2,1,2,1,1,1,1,1,2,2,2}};
       //imagen=new Imagen("ImagenPrueba3.gif");
       imagen=new Imagen(matrizImagen);
       imagen.dibujar();
       matrizImagen=imagen.getMatriz();
       matrizImagenNueva=new int[matrizImagen.length][matrizImagen[0].length];
       for(int f=0;f<matrizImagen.length;++f){
           for(int c=0;c<matrizImagen[0].length;++c){
               System.out.print(matrizImagen[f][c]+" ");
               matrizImagenNueva[f][c]=-1;//Hacer matriz de puros pixeles blancos
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
        Pixel fondo=new Pixel(0,0,matrizImagen[0][0]);
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
    public void anadirPixelMatriz(Pixel pixel){
        if(pixel!=null){
            int fila=pixel.getFila();
            int columna=pixel.getColumna();
            matrizImagenNueva[fila][columna]=pixel.getColor();
    }
    }
    public boolean estaEnMatriz(Pixel pixel){
      int fila=pixel.getFila();
      int columna=pixel.getColumna();
      return matrizImagenNueva[fila][columna]==pixel.getColor();
    }
    /*
     * ESTA VAINA NO FUNCIONA IDK POR QUE...
    public void algoritmoExpansion(Pixel pixel){
        anadirPixelMatriz(pixel);
        int fila=pixel.getFila();
        int columna=pixel.getColumna();
        for(Pixel p:vecinosIgualesBorde(fila,columna)){
            if(p!=null && !estaEnMatriz(p)){
                anadirPixelMatriz(p);
                algoritmoExpansion(p);
            }
    }
    
    }
    */
    public void showNuevaImagen(){
        Pixel borde=findBorde();
        anadirPixelMatriz(borde);
        int fila=borde.getFila();
        int columna=borde.getColumna();
        for(Pixel p:vecinosIgualesBorde(fila,columna)){
            if(p!=null && !estaEnMatriz(p)){
                anadirPixelMatriz(p);
    }
    }
    Imagen nuevaImagen=new Imagen(matrizImagenNueva);
    nuevaImagen.dibujar();
}
}

    
    

