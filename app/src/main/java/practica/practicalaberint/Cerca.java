






package practica.practicalaberint;

import android.service.dreams.DreamService;

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


        Coa2 colaAbierta = new Coa2(); //Lista usada para ver los nodos por visitar
        Coa2 colaCerrada = new Coa2(); //Lista usada para ver los nodos visitados
        Boolean found = false; //Usado para evaluar la meta


        if(!origen.equals(desti)) {
            colaAbierta.afegeix(origen); //Primer punto a visitar
        } else {
            camiTrobat.afegeix(origen);
            return camiTrobat;
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
        Punt actual = new Punt();
        Punt operadorDetra;
        Punt operadorEsquerra;
        Punt operadorAmunt;
        Punt operadorAvall;

        Boolean dretaValida = false;
        Boolean esquerraValida = false;
        Boolean arribaValida = false;
        Boolean abajoValido = false;

        Coa2 colaAbierta = new Coa2(); //Lista usada para ver los nodos por visitar
        Coa2 colaCerrada = new Coa2(); //Lista usada para ver los nodos visitados

        //Añadimos el primer elemento al principio de la cola;
        /*colaAbierta.afegeix(origen);*/
        //actual = (Punt) colaAbierta.consulta();
        Boolean found = false; //Usado para evaluar la meta
        if(!origen.equals(desti)) {
            colaAbierta.afegeix(origen); //Primer punto a visitar
        } else {
            camiTrobat.afegeix(origen);
            return camiTrobat;
        }
        while(!colaAbierta.buida() && !found){
            //Borramos el primero
            actual = (Punt) colaAbierta.treu();
            //Aumentamos los nodos
            laberint.incNodes();
            if(desti.equals(actual)){
                found = true;
                System.out.println("Meta encontrada");
            } else {
                //Generar sucesores de actual
                operadorDetra = new Punt(actual.x,actual.y+1,actual,0); //Si vas a la derecha te has avanzado una columna
                operadorEsquerra = new Punt(actual.x,actual.y-1, actual,0); //Si vas hacía la izquierda has retrocedido una columna
                operadorAmunt = new Punt(actual.x-1,actual.y,actual, 0); //Si vas hacía arriba has retrocedido una fila
                operadorAvall = new Punt(actual.x+1,actual.y, actual,0); //Si vas hacía abajo has avanzado una fila
                colaCerrada.afegeix(actual);

                //Se puede añadir a la lista abierta,si no hay pared y no es un estado repetido (esté en colaAbierta o colaCerrada)
                if (laberint.pucAnar(actual.x,actual.y, laberint.DRETA)&&(!colaCerrada.cerca(operadorDetra)) ){
                    dretaValida = true;
                }
                if (laberint.pucAnar(actual.x,actual.y, laberint.ESQUERRA)&&(!colaCerrada.cerca(operadorEsquerra))){
                    esquerraValida = true;
                }
                if (laberint.pucAnar(actual.x,actual.y,laberint.AMUNT)&&(!colaCerrada.cerca(operadorAmunt))){
                    arribaValida = true;
                }
                if (laberint.pucAnar(actual.x,actual.y,laberint.AVALL)&&(!colaCerrada.cerca(operadorAvall))){
                    abajoValido = true;
                }
                //Estamos en la primera iteración
                colaAbierta = reorganizaCola(colaAbierta,dretaValida,esquerraValida,arribaValida,abajoValido,operadorDetra,operadorEsquerra,operadorAmunt,operadorAvall);
                dretaValida = false;
                esquerraValida = false;
                arribaValida = false;
                abajoValido = false;
            }

        }
        //Buscar el camino más corto una vez hemos encontrado la meta(Backtracking hasta el origen)
        while (!origen.equals(actual)){
            camiTrobat.afegeix(actual);
            actual = actual.previ;
        }
        camiTrobat.afegeix(actual);
        return camiTrobat;
    }

    public Cami CercaAmbHeurística(Punt origen, Punt desti, int tipus)
    {   // Tipus pot ser MANHATTAN o EUCLIDIA
        int i;
        Cami camiTrobat = new Cami(files*columnes);
        laberint.setNodes(0);

        ArrayList<Punt2> listaAbierta = new ArrayList<>();
        int index = 0;


        Punt2 actual = new Punt2(origen,calcularHeuristica(tipus,origen,desti));
        System.out.println("El valor de H es: "+String.valueOf(actual.heuristica));
        Punt preoperadorDetra;
        Punt preoperadorEsquerra;
        Punt preoperadorAmunt;
        Punt preoperadorAvall;
        Punt2 operadorDetra;
        Punt2 operadorEsquerra;
        Punt2 operadorAmunt;
        Punt2 operadorAvall;


        Coa2 colaAbierta = new Coa2(); //Lista usada para ver los nodos por visitar
        Coa2 colaCerrada = new Coa2(); //Lista usada para ver los nodos visitados
        Boolean found = false; //Usado para evaluar la meta

        if(!origen.equals(desti)) {
            listaAbierta.add(index,actual);
            index = index + 1;

        } else {
            camiTrobat.afegeix(actual);
            return camiTrobat;
        }
        while (!listaAbierta.isEmpty() && !found)  {
            index = index - 1;
            actual = listaAbierta.get(index);
            listaAbierta.remove(index);

            laberint.incNodes();
            //Comprobar si es el estado meta
            if(desti.equals(actual)){
                found = true;
                System.out.println("Meta encontrada");
            } else { //Si no es el estado meta, aplicar operadores(DRETA,ESQUERRA,AMUNT, AVALL) en ese orden
                //Generar sucesores de actual
                preoperadorDetra = new Punt(actual.x,actual.y+1,actual,0); //Si vas a la derecha te has avanzado una columna
                operadorDetra = new Punt2(preoperadorDetra,calcularHeuristica(tipus,preoperadorDetra,desti)); //Crear punto con H(n)

                preoperadorEsquerra = new Punt(actual.x,actual.y-1, actual,0); //Si vas hacía la izquierda has retrocedido una columna
                operadorEsquerra = new Punt2(preoperadorEsquerra,calcularHeuristica(tipus,preoperadorEsquerra,desti)); //Crear punto con H(n)

                preoperadorAmunt = new Punt(actual.x-1,actual.y,actual, 0); //Si vas hacía arriba has retrocedido una fila
                operadorAmunt = new Punt2(preoperadorAmunt,calcularHeuristica(tipus,preoperadorAmunt,desti)); //Crear punto con H(n)

                preoperadorAvall = new Punt(actual.x+1,actual.y, actual,0); //Si vas hacía abajo has avanzado una fila
                operadorAvall = new Punt2(preoperadorAvall,calcularHeuristica(tipus,preoperadorAvall,desti)); //Crear punto con H(n)

                colaCerrada.afegeix(actual);

                //Se puede añadir a la lista abierta,si no hay pared y no es un estado repetido (esté en colaAbierta o colaCerrada)
                if (laberint.pucAnar(actual.x,actual.y, laberint.DRETA)&&(!colaCerrada.cerca(operadorDetra)) ){
                    listaAbierta.add(index,operadorDetra);
                    index = index + 1;
                }
                if (laberint.pucAnar(actual.x,actual.y, laberint.ESQUERRA)&&(!colaCerrada.cerca(operadorEsquerra))){
                    listaAbierta.add(index,operadorEsquerra);
                    index = index + 1;                }
                if (laberint.pucAnar(actual.x,actual.y,laberint.AMUNT)&&(!colaCerrada.cerca(operadorAmunt))){
                    listaAbierta.add(index,operadorAmunt);
                    index = index + 1;                }
                if (laberint.pucAnar(actual.x,actual.y,laberint.AVALL)&&(!colaCerrada.cerca(operadorAvall))){
                    listaAbierta.add(index,operadorAvall);
                    index = index + 1;                }
                System.out.println("El primer  valor de H  antes de ordenar es: "+String.valueOf(listaAbierta.get(0).heuristica));
                System.out.println("El segundo  valor de H  antes de ordenar es: "+String.valueOf(listaAbierta.get(1).heuristica));

                Collections.sort(listaAbierta);
                System.out.println("El primer valor de H después de ordenar es: "+String.valueOf(listaAbierta.get(0).heuristica));
                System.out.println("El segundo valor de H después de ordenar es: "+String.valueOf(listaAbierta.get(1).heuristica));

            }
        }

        //Buscar el camino más corto una vez hemos encontrado la meta(Backtracking hasta el origen)
        while (!origen.equals(actual)){
            camiTrobat.afegeix(actual);
            actual = (Punt2) actual.previ;
        }
        camiTrobat.afegeix(actual);
        return camiTrobat;
    }


    public Cami CercaViatjant(Punt origen, Punt desti)
    {
        Cami camiTrobat = new Cami(files*columnes);
        laberint.setNodes(0);

        // Implementa l'algorisme aquí

        return camiTrobat;
    }

    private double calcularHeuristica(int tipus, Punt actual, Punt destino){
        switch (tipus){
            case MANHATTAN:
                return Math.abs(actual.x-destino.x) + Math.abs(actual.y-destino.y);
            case EUCLIDEA:
                return Math.sqrt(Math.pow(actual.x-actual.y,2)+ Math.pow(destino.x-destino.y,2));
        }
        return 0;
    }

    private Coa2 reorganizaCola(Coa2 prev, boolean DretaValida, boolean EsquerraValida,boolean AmuntValida,boolean AvallValida, Punt dreta , Punt esquerra, Punt amunt, Punt avall ){
        Coa2 ret = new Coa2();
        if(DretaValida){
            ret.afegeix(dreta);
        }
        if(EsquerraValida){
            ret.afegeix(esquerra);
        }
        if(AmuntValida){
            ret.afegeix(amunt);
        }
        if(AvallValida){
            ret.afegeix(avall);
        }
        while(!prev.buida()){
            ret.afegeix(prev.treu());
        }
        return ret;
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

    //Clase Punt extends
    class Punt2 extends Punt
    {
        double heuristica;
        Punt2(int x1, int y1, Punt f, double val)
        {
            super();
            this.x = x1;
            this.y = y1;
            this.previ = f;
            this.heuristica = val;
        }
        Punt2(Punt p, double val){

            this.x = p.x;
            this.y = p.y;
            this.previ = p.previ;
            this.heuristica = val;
        }
    }
}
