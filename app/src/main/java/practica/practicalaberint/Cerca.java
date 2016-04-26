






package practica.practicalaberint;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Classe que conté els diferents algorismes de cerca que s'han d'implementar
 */
/**
 *   AUTORS:__________________________________________
 */
/* S'ha d'omplenar la següent taula amb els diferents valors del nodes visitats i llargada del camí resultat
 * per les diferents grandàries de laberints proposades i comentar breument els resultats obtinguts.
 ****************************************************************************************************************
 *                  Profunditat           Amplada          Manhattan         Euclidiana         Viatjant        *
 *  Laberint     Nodes   Llargada    Nodes   Llargada   Nodes   Llargada   Nodes   Llargada  Nodes   Llargada   *
 * **************************************************************************************************************
 *    4x4
 *    8x8
 *   15x15
 *   20x20
 *   30x30
 *
 * Comentari sobre els resultats obtinguts:
 *
 *
 *
 *
 *
 *
 */


public class Cerca
{
    static final public int MANHATTAN = 2;
    static final public int EUCLIDEA  = 3;

    Laberint laberint;      // laberint on es cerca
    int files, columnes;    // files i columnes del laberint


    public Cerca(Laberint l)
    {
        files = l.nFiles;
        columnes = l.nColumnes;
        laberint = l;
    }

    public Cami CercaEnAmplada(Punt origen, Punt desti)
    {
        Cami camiTrobat = new Cami(files*columnes);
        laberint.setNodes(0);

        Punt actual = new Punt();
        Punt operadorDetra;
        Punt operadorEsquerra;
        Punt operadorAmunt;
        Punt operadorAvall;
        ArrayList <Punt> repetidos= new ArrayList<Punt>();


        Coa2 colaAbierta = new Coa2(); //Lista usada para ver los nodos por visitar
        Coa2 colaCerrada = new Coa2(); //Lista usada para ver los nodos visitados
        Boolean found = false; //Usado para evaluar la meta


        if(!origen.equals(desti)) {
            colaAbierta.afegeix(origen); //Primer punto a visitar
        } else {
            found = true;
        }
        while (!colaAbierta.buida() && !found)  {
                actual = (Punt) colaAbierta.treu();
                laberint.incNodes();
                //Comprobar si es el estado meta
                if(desti.equals(actual)){
                    found = true;
                    System.out.println("Meta encontrada");
                } else { //Si no es el estado meta, aplicar operadores(DRETA,ESQUERRA,AMUNT, AVALL) en ese orden
                    //Generar sucesores de actual
                    operadorDetra = new Punt(actual.x,actual.y+1,actual,0); //Si vas a la derecha te has avanzado una columna
                    operadorEsquerra = new Punt(actual.x,actual.y-1, actual,0); //Si vas hacía la izquierda has retrocedido una columna
                    operadorAmunt = new Punt(actual.x-1,actual.y,actual, 0); //Si vas hacía arriba has retrocedido una fila
                    operadorAvall = new Punt(actual.x+1,actual.y, actual,0); //Si vas hacía abajo has avanzado una fila
                    colaCerrada.afegeix(actual);

                    //Se puede añadir a la lista abierta,si no hay pared y no es un estado repetido (esté en colaAbierta o colaCerrada)
                    if (laberint.pucAnar(actual.x,actual.y, laberint.DRETA)&&(!colaCerrada.cerca(operadorDetra)) ){
                        colaAbierta.afegeix(operadorDetra);
                    }
                    if (laberint.pucAnar(actual.x,actual.y, laberint.ESQUERRA)&&(!colaCerrada.cerca(operadorEsquerra))){
                        colaAbierta.afegeix(operadorEsquerra);
                    }
                    if (laberint.pucAnar(actual.x,actual.y,laberint.AMUNT)&&(!colaCerrada.cerca(operadorAmunt))){
                        colaAbierta.afegeix(operadorAmunt);
                    }
                    if (laberint.pucAnar(actual.x,actual.y,laberint.AVALL)&&(!colaCerrada.cerca(operadorAvall))){
                        colaAbierta.afegeix(operadorAvall);
                    }
                }
        }


        //Buscar el camino más corto una vez hemos encontrado la meta(Backtracking hasta el origen)
        int i =1;
        while (!origen.equals(actual)){
            camiTrobat.afegeix(actual);
            actual = actual.previ;
        }
        camiTrobat.afegeix(actual);
        return camiTrobat;
    }

    public Cami CercaEnProfunditat(Punt origen, Punt desti)
    {
        Cami camiTrobat = new Cami(files*columnes);
        laberint.setNodes(0);

        // Implementa l'algorisme aquí

        return camiTrobat;
    }

    public Cami CercaAmbHeurística(Punt origen, Punt desti, int tipus)
    {   // Tipus pot ser MANHATTAN o EUCLIDIA
        int i;
        Cami camiTrobat = new Cami(files*columnes);
        laberint.setNodes(0);

        // Implementa l'algorisme aquí

        return camiTrobat;
    }


    public Cami CercaViatjant(Punt origen, Punt desti)
    {
        Cami camiTrobat = new Cami(files*columnes);
        laberint.setNodes(0);

        // Implementa l'algorisme aquí

        return camiTrobat;
    }




    //Clase coa extends
    class Coa2 extends Coa
    {
        public boolean cerca(Punt p)
        {
            boolean found = false;
            Node a;
            a = primer;
            while(a != null && !found){
                if(p.equals(a.element)){
                    found = true;
                } else {
                    a = a.següent;
                }
            }
            return  found;
        }
    }
}
