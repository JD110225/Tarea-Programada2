public class Catalogo{
    private Figura[] figuras;
    private int indiceCatalogo;
    
    public Catalogo(){
        figuras=new Figura[100];
    }
    
    /**
     * Este método se encarga de agregar una nueva figura al catalogo.
     */
    public void agregarFigura(Figura figura){
        figuras[indiceCatalogo++]=figura;
    }
    
    /**
     * Este método permite ver las imagenes que se encuentran en el catalogo.
     */
    public void verCatalogo(){
        for(Figura f:figuras){
            if(f!=null){
                f.verDibujo();
            }
        }
    }
}

