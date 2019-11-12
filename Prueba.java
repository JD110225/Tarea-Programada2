

public class Prueba{
    public void prueba(){
        Segmentador seg = new Segmentador();
        Figura[] lista = seg.segmentacion();
        lista[0].encontrarArea();
    }
    public void prueba2(){//recibe la matriz de Segmentacion como parámetro

        Segmentador seg = new Segmentador();
        Figura[] lista = seg.segmentacion();
        Figura y = new Figura(lista[0].getMatriz());
        y.encontrarDimensiones();
        Pixel borde = y.encontrarBorde(lista[0].getMatriz());
        int colorBorde = borde.getColor();
        System.out.println("Borde " + borde.getColor());
        int borde0 = lista[0].getMatriz()[0][0];
        System.out.println("0,0 " + lista[0].getMatriz()[0][0]);
        y.verDibujo();
        int contador = 0;
        for(int f = 0; f < lista[0].getMatriz().length ; f++){
            for(int c = 0; c < lista[0].getMatriz()[0].length; c++){
                if(lista[0].getMatriz()[f][c] != borde0){
                    //matrizMedio[f][c] = 2525;
                    contador++;
                }    
            }
        }
        System.out.println("Area " + contador);
    }//retorna el área para ser usada en el constructor final de figura;
}    
