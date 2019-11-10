
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
    public Figura[] getFiguras(){
        return figuras;
    }
    public int posMayorFigura(int inicio,int fin){
        //Figura mayor=figuras[0];
        int mayor=0;
        for(int i=inicio;i<fin;++i){
            if(figuras[i]!=null && figuras[i].getCantidadManchas()>figuras[mayor].getCantidadManchas()){
                mayor=i;
            }
        }
        return mayor;
    }
    public void swap(int i,int j){
        Figura temp=figuras[i];
        figuras[i]=figuras[j];
        figuras[j]=temp;
    }
    //Algoritmo por seleccion.
    public void ordenamientoFiguras(){
        int mayorDelResto=0;
        for(int i=0;i<figuras.length;++i){
            mayorDelResto=posMayorFigura(i,figuras.length);
            swap(i,mayorDelResto);
        }
    }
    public int mismaCantidadManchas(int indiceFigura){   //Retorna siguiente figura que tenga misma cantidad de mancha
        boolean encontrado=false;
        int numeroManchas=figuras[indiceFigura].getCantidadManchas();
        int indice=0;
        for(int i=indiceFigura+1;!encontrado && i<figuras.length;++i){  //Revisa desde indice hacia atras
            if(figuras[i]!=null && figuras[i].getCantidadManchas()==numeroManchas){
                indice=i;
                encontrado=true;
            }
        }
        return indice;
    }
    public boolean existeFigura(int indice){
        return figuras[indice]!=null;
    }
    public boolean manchasIguales(){ //Ver si es necesario reordenar por dimensiones.
        boolean condicion=false;
        for(int i=0;i<figuras.length;++i){
           for(int j=i+1;!condicion && j<figuras.length;++j)
                if(existeFigura(i) && existeFigura(j)){
                    if(figuras[i].getCantidadManchas()==figuras[j].getCantidadManchas()){
                        condicion=true;
                    }
            }
        }
        return condicion;
    }
    public void reacomodoPorDimensiones(){
        for(int i=0;i<figuras.length;++i){
            if(figuras[i]!=null){
                int indiceRepeticion=mismaCantidadManchas(i);
                figuras[i].encontrarDimensiones();
                if(figuras[indiceRepeticion]!=null){
                    figuras[indiceRepeticion].encontrarDimensiones();
                    int[] dimensionesFigura=figuras[i].getDimensiones();
                    int[] dimensionesFiguraRepeticion=figuras[indiceRepeticion].getDimensiones();
                    int productoDimensionesF1=dimensionesFigura[0]*dimensionesFigura[1];
                    int productoDimensionesF2=dimensionesFiguraRepeticion[0]*dimensionesFiguraRepeticion[1];
                    if(productoDimensionesF2>productoDimensionesF1){
                        swap(i,indiceRepeticion);
                    }
                }
        }
        }
        }
    
    public void dibujarFiguras(){
        ordenamientoFiguras();
        if(manchasIguales()){
            reacomodoPorDimensiones();
        }
        for(Figura f:figuras){
            if(f!=null){
                f.verDibujo();
        }
    }
    }
}

