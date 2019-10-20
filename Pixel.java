
public class Pixel
{
   private int fila;
   private int columna;
   public Pixel(int fila,int columna){
       this.fila=fila;
       this.columna=columna;
    }
   public int getFila(){
       return fila;
   }
   public int getColumna(){
       return columna;
   }
   public String toString(){
       String tira="";
       tira+="Fila: "+getFila()+"\n"+"Columna: "+getColumna()+"\n";
       return tira;
    }
}
