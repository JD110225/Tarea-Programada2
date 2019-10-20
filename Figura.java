public class Figura
{
   private int[][] matriz;
   private int areaPixeles;
   private int cantidadManchas;
   private int[] dimensiones;
   private Pixel borde;
   private Pixel colorFondo;
   private int escala;
   public Figura(int[][]matriz,Pixel borde,Pixel colorFondo){
       this.matriz=matriz;
       this.borde=borde;
       this.colorFondo=colorFondo;
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
   
}
