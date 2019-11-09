
public class Catalogo{
    private Figura[] figuras;
    private int indiceCatalogo;
    public Catalogo(){
        figuras=new Figura[100];
    }
    public void agregarFigura(Figura figura){
        figuras[indiceCatalogo++]=figura;
    }
    public void verCatalogo(){
        for(Figura f:figuras){
            if(f!=null){
            f.verDibujo();
        }
    }
    }
}

