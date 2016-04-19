package practica.practicalaberint;

/**
 * Created by Ramon Mas on 10/3/16.
 * Classe per guardar les coordenades x,y d'un píxel o d'una casella del laberint
 * Inclou atributs addicionals per si es vol utilitzar com a node en els algorismes de cerca (previ, distancias)
 */

public class Punt implements Comparable<Punt> {
    // x i y: coordenades fila, columna
    // from : per guardar referencies a altres punts

    int x;
    int y;
    Punt previ;
    int    distanciaDeLinici    = Integer.MAX_VALUE;
    double distanciaAlFinal  = Integer.MAX_VALUE;
    boolean visible = true; // variable auxiliar

    Punt()
    {
        super();
    }
    Punt(int x1, int y1, Punt f, int val)
    {
        super();
        this.x = x1;
        this.y = y1;
        this.previ = f;
    }

    Punt(Punt p1)
    {
        this.x =p1.x;
        this.y =p1.y;
    }

    Punt(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object other){   //per veure si dos punts són iguals
        if (other == null) return false;
        if (((Punt)other).x == this.x && ((Punt) other).y == this.y) return true;
        else return false;
    }

    public int compareTo(Punt punt2) {   // compara els valors de les heurístiques aplicades a dos punts
        double distanciaTotalDesdeLObjectiu = distanciaAlFinal + distanciaDeLinici;
        double nodeDistanciaTotalDesdeLObjectiu = punt2.distanciaAlFinal + punt2.distanciaDeLinici;

        if (distanciaTotalDesdeLObjectiu < nodeDistanciaTotalDesdeLObjectiu) {
            return -1;
        } else if (distanciaTotalDesdeLObjectiu > nodeDistanciaTotalDesdeLObjectiu) {
            return 1;
        } else {
            return 0;
        }
    }

}
