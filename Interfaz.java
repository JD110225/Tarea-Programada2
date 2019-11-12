import javax.swing.JOptionPane;
public class Interfaz{
    
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
                mostrarError("No se digito un valor valido");
                entero = -1;
            }
        }
        return entero;
    }
    
    public void mostrarError(String mensaje){
        JOptionPane.showMessageDialog(null,mensaje,"Error",JOptionPane.ERROR_MESSAGE);
    }
    
    public void mostrarMensaje(String mensaje){
        JOptionPane.showMessageDialog(null,mensaje);
    }
    
}
