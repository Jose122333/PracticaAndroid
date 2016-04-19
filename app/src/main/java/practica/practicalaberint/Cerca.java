






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
        Object nodo = new Object();
        Coa colaAbierta = new Coa(); //Lista usada para ver los nodos por visitar
        Coa colaCerrada = new Coa(); //Lista usada para ver los nodos visitados

        if(!origen.equals(desti)) {
            colaAbierta.afegeix(origen); //Primer punto a visitar
        }
        while (!colaAbierta.buida())  {
            actual = (Punt) colaAbierta.treu();
            if(actual.equals(origen)){
                System.out.println("Hola");
            }
            //laberint.pucAnar(origen.x,nodo.y,laberint.DRETA);

        }


        // Implementa l'algorisme aquí hola

        
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
}
