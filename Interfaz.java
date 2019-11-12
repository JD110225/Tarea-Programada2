import javax.swing.JOptionPane;
public class Interfaz
{
   public int pedirEntero(String mensaje){
       int entero=-1;
       boolean revision=false;
       while(!revision){
           try{
           String enteroString=JOptionPane.showInputDialog(mensaje);
           entero=Integer.parseInt(enteroString);
           revision=true;
        }
           catch(Exception e){
               JOptionPane.showMessageDialog(null,"No se digito un valor valido","Error",JOptionPane.ERROR_MESSAGE);
            }
    }
    return entero;
}
    public void mostrarMensaje(String mensaje){
        JOptionPane.showMessageDialog(null,mensaje);
    }
}
