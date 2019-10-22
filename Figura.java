public class Figura
{
   private int[][] matriz;
   private Imagen dibujo;
   private int areaPixeles;
   private int cantidadManchas;
   private int[] dimensiones;
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
   public Figura(int[][] matriz){
       this.matriz=matriz;
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
       dibujo=new Imagen(matriz);
       dibujo.dibujar();
    }
}
