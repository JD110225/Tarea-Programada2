public class Pixel {
    
   private int fila;
   private int columna;
   private int color;
   
   public Pixel(int fila,int columna,int color){
       this.fila=fila;
       this.columna=columna;
       this.color=color;
   }
   
   public int getFila(){
       return fila;
   }
   
   public int getColumna(){
       return columna;
   }
   
   public int getColor(){
       return color;
   }
   
   public String toString(){
       String tira="";
       tira+="Fila: "+getFila()+"\n"+"Columna: "+getColumna()+"\n";
       return tira;
   }
}
