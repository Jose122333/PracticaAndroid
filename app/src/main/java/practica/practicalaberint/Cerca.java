


package practica.practicalaberint;

import android.service.dreams.DreamService;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Classe que conté els diferents algorismes de cerca que s'han d'implementar
 */

/**
 * AUTORS:__________________________________________
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


public class Cerca {
    static final public int MANHATTAN = 2;
    static final public int EUCLIDEA = 3;

    Laberint laberint;      // laberint on es cerca
    int files, columnes;    // files i columnes del laberint


    public Cerca(Laberint l) {
        files = l.nFiles;
        columnes = l.nColumnes;
        laberint = l;
    }

    public Cami CercaEnAmplada(Punt origen, Punt desti) {
        Cami camiTrobat = new Cami(files * columnes);
        laberint.setNodes(0);

        Punt actual = new Punt();
        Punt operadorDetra, operadorEsquerra, operadorAmunt, operadorAvall;


        Coa2 colaAbierta = new Coa2(); //Lista usada para ver los nodos por visitar
        Coa2 colaCerrada = new Coa2(); //Lista usada para ver los nodos visitados
        Boolean found = false; //Usado para evaluar la meta


        if (!origen.equals(desti)) {
            colaAbierta.afegeix(origen); //Primer punto a visitar
        } else {
            camiTrobat.afegeix(origen);
            return camiTrobat;
        }
        while (!colaAbierta.buida() && !found) {
            actual = (Punt) colaAbierta.treu();
            laberint.incNodes();
            //Comprobar si es el estado meta
            if (desti.equals(actual)) {
                found = true;
                System.out.println("Meta encontrada");
            } else { //Si no es el estado meta, aplicar operadores(DRETA,ESQUERRA,AMUNT, AVALL) en ese orden
                //Generar sucesores de actual
                operadorDetra = operadores(actual, 1); //Si vas a la derecha te has avanzado una columna
                operadorEsquerra = operadores(actual, 2); //Si vas hacía la izquierda has retrocedido una columna
                operadorAmunt = operadores(actual, 3); //Si vas hacía arriba has retrocedido una fila
                operadorAvall = operadores(actual, 4); //Si vas hacía abajo has avanzado una fila
                colaCerrada.afegeix(actual);

                //Se puede añadir a la lista abierta,si no hay pared y no es un estado repetido (esté en colaAbierta o colaCerrada)
                if (laberint.pucAnar(actual.x, actual.y, laberint.DRETA) && (!colaCerrada.cerca(operadorDetra))) {
                    colaAbierta.afegeix(operadorDetra);
                }
                if (laberint.pucAnar(actual.x, actual.y, laberint.ESQUERRA) && (!colaCerrada.cerca(operadorEsquerra))) {
                    colaAbierta.afegeix(operadorEsquerra);
                }
                if (laberint.pucAnar(actual.x, actual.y, laberint.AMUNT) && (!colaCerrada.cerca(operadorAmunt))) {
                    colaAbierta.afegeix(operadorAmunt);
                }
                if (laberint.pucAnar(actual.x, actual.y, laberint.AVALL) && (!colaCerrada.cerca(operadorAvall))) {
                    colaAbierta.afegeix(operadorAvall);
                }
            }
        }
        //Buscar el camino más corto una vez hemos encontrado la meta(Backtracking hasta el origen)
        while (!origen.equals(actual)) {
            camiTrobat.afegeix(actual);
            actual = actual.previ;
        }

        camiTrobat.afegeix(actual);
        return camiTrobat;
    }

    public Cami CercaEnProfunditat(Punt origen, Punt desti) {
        Cami camiTrobat = new Cami(files * columnes);
        laberint.setNodes(0);

        // Implementa l'algorisme aquí
        Punt actual = new Punt();
        Punt operadorDetra, operadorEsquerra, operadorAmunt, operadorAvall;


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
        if (!origen.equals(desti)) {
            colaAbierta.afegeix(origen); //Primer punto a visitar
        } else {
            camiTrobat.afegeix(origen);
            return camiTrobat;
        }
        while (!colaAbierta.buida() && !found) {
            //Borramos el primero
            actual = (Punt) colaAbierta.treu();
            //Aumentamos los nodos
            laberint.incNodes();
            if (desti.equals(actual)) {
                found = true;
                System.out.println("Meta encontrada");
            } else {
                //Generar sucesores de actual
                operadorDetra = operadores(actual, 1); //Si vas a la derecha te has avanzado una columna
                operadorEsquerra = operadores(actual, 2); //Si vas hacía la izquierda has retrocedido una columna
                operadorAmunt = operadores(actual, 3); //Si vas hacía arriba has retrocedido una fila
                operadorAvall = operadores(actual, 4); //Si vas hacía abajo has avanzado una fila
                colaCerrada.afegeix(actual);

                //Se puede añadir a la lista abierta,si no hay pared y no es un estado repetido (esté en colaAbierta o colaCerrada)
                if (laberint.pucAnar(actual.x, actual.y, laberint.DRETA) && (!colaCerrada.cerca(operadorDetra))) {
                    dretaValida = true;
                }
                if (laberint.pucAnar(actual.x, actual.y, laberint.ESQUERRA) && (!colaCerrada.cerca(operadorEsquerra))) {
                    esquerraValida = true;
                }
                if (laberint.pucAnar(actual.x, actual.y, laberint.AMUNT) && (!colaCerrada.cerca(operadorAmunt))) {
                    arribaValida = true;
                }
                if (laberint.pucAnar(actual.x, actual.y, laberint.AVALL) && (!colaCerrada.cerca(operadorAvall))) {
                    abajoValido = true;
                }
                //Estamos en la primera iteración
                colaAbierta = reorganizaCola(colaAbierta, dretaValida, esquerraValida, arribaValida, abajoValido, operadorDetra, operadorEsquerra, operadorAmunt, operadorAvall);
                dretaValida = false;
                esquerraValida = false;
                arribaValida = false;
                abajoValido = false;
            }

        }
        //Buscar el camino más corto una vez hemos encontrado la meta(Backtracking hasta el origen)
        while (!origen.equals(actual)) {
            camiTrobat.afegeix(actual);
            actual = actual.previ;
        }
        camiTrobat.afegeix(actual);
        return camiTrobat;
    }

    public Cami CercaAmbHeurística(Punt origen, Punt desti, int tipus) {   // Tipus pot ser MANHATTAN o EUCLIDIA
        int i;
        Cami camiTrobat = new Cami(files * columnes);
        laberint.setNodes(0);
        ArrayList<Punt2> listaAbierta = new ArrayList<>();

        Punt2 actual = new Punt2(origen, calcularHeuristica(tipus, origen, desti));
        Punt preoperadorDetra, preoperadorEsquerra, preoperadorAmunt, preoperadorAvall;
        Punt2 operadorDetra, operadorEsquerra, operadorAmunt, operadorAvall;

        Coa2 colaAbierta = new Coa2(); //Lista usada para ver los nodos por visitar
        Coa2 colaCerrada = new Coa2(); //Lista usada para ver los nodos visitados
        Boolean found = false; //Usado para evaluar la meta

        if (!origen.equals(desti)) {
            listaAbierta.add(actual);
        } else {
            camiTrobat.afegeix(actual);
            return camiTrobat;
        }
        while (!listaAbierta.isEmpty() && !found) {
            actual = listaAbierta.remove(0);
            laberint.incNodes();
            //Comprobar si es el estado meta
            if (desti.equals(actual)) {
                found = true;
                System.out.println("Meta encontrada");
            } else { //Si no es el estado meta, aplicar operadores(DRETA,ESQUERRA,AMUNT, AVALL) en ese orden
                //Generar sucesores de actual
                preoperadorDetra = operadores(actual, 1); //Si vas a la derecha te has avanzado una columna
                operadorDetra = new Punt2(preoperadorDetra, calcularHeuristica(tipus, preoperadorDetra, desti)); //Crear punto con H(n)
                preoperadorEsquerra = operadores(actual, 2); //Si vas hacía la izquierda has retrocedido una columna
                operadorEsquerra = new Punt2(preoperadorEsquerra, calcularHeuristica(tipus, preoperadorEsquerra, desti)); //Crear punto con H(n)
                preoperadorAmunt = operadores(actual, 3); //Si vas hacía arriba has retrocedido una fila
                operadorAmunt = new Punt2(preoperadorAmunt, calcularHeuristica(tipus, preoperadorAmunt, desti)); //Crear punto con H(n)
                preoperadorAvall = operadores(actual, 4); //Si vas hacía abajo has avanzado una fila
                operadorAvall = new Punt2(preoperadorAvall, calcularHeuristica(tipus, preoperadorAvall, desti)); //Crear punto con H(n)
                colaCerrada.afegeix(actual);

                //Se puede añadir a la lista abierta,si no hay pared y no es un estado repetido (esté en colaAbierta o colaCerrada)
                if (laberint.pucAnar(actual.x, actual.y, laberint.DRETA) && (!colaCerrada.cerca(operadorDetra))) {
                    listaAbierta.add(operadorDetra);
                }
                if (laberint.pucAnar(actual.x, actual.y, laberint.ESQUERRA) && (!colaCerrada.cerca(operadorEsquerra))) {
                    listaAbierta.add(operadorEsquerra);
                }
                if (laberint.pucAnar(actual.x, actual.y, laberint.AMUNT) && (!colaCerrada.cerca(operadorAmunt))) {
                    listaAbierta.add(operadorAmunt);
                }
                if (laberint.pucAnar(actual.x, actual.y, laberint.AVALL) && (!colaCerrada.cerca(operadorAvall))) {
                    listaAbierta.add(operadorAvall);
                }
                ordenarLista(listaAbierta);
            }
        }

        //Buscar el camino más corto una vez hemos encontrado la meta(Backtracking hasta el origen)
        while (!origen.equals(actual)) {
            camiTrobat.afegeix(actual);
            actual = (Punt2) actual.previ;
        }
        camiTrobat.afegeix(actual);
        return camiTrobat;
    }


    public Cami CercaViatjant(Punt origen, Punt desti) {
        Cami camiTrobat = new Cami(files * columnes);
        Cami camiViajante1, camiViajante2, camiViajante3, camiViajante4, camiSalida;

        laberint.setNodes(0);
        int nodesViatjant = 0;

        ArrayList<Punt2> listaAbierta = new ArrayList<>();
        ArrayList<Punt2> listaViajantes = new ArrayList<>();

        Punt2 aux, actual = new Punt2(origen, calcularHeuristica(MANHATTAN, origen, desti));
        Punt destiViajante;

        if (!origen.equals(desti)) {
            listaAbierta.add(actual);
        } else {
            camiTrobat.afegeix(actual);
            return camiTrobat;
        }
        //Añadir viajantes a la lista con sus heurística
        for (int i = 0; i < 4; i++) {
            aux = new Punt2(laberint.getObjecte(i), calcularHeuristica(MANHATTAN, actual, laberint.getObjecte(i)));
            listaViajantes.add(aux);
        }
        ordenarLista(listaViajantes);

        //Cogemos el viajante más cercano
        destiViajante = (Punt) listaViajantes.remove(0);
        camiViajante1 = CercaAmbHeurística(origen, destiViajante, MANHATTAN);
        nodesViatjant += laberint.nodes;

        origen = destiViajante;
        destiViajante = (Punt) listaViajantes.remove(0);
        camiViajante2 = CercaAmbHeurística(origen, destiViajante, MANHATTAN);
        nodesViatjant += laberint.nodes;

        origen = destiViajante;
        destiViajante = (Punt) listaViajantes.remove(0);
        camiViajante3 = CercaAmbHeurística(origen, destiViajante, MANHATTAN);
        nodesViatjant += laberint.nodes;

        origen = destiViajante;
        destiViajante = (Punt) listaViajantes.remove(0);
        camiViajante4 = CercaAmbHeurística(origen, destiViajante, MANHATTAN);
        nodesViatjant += laberint.nodes;


        origen = destiViajante;
        camiSalida = CercaAmbHeurística(origen, desti, MANHATTAN);
        nodesViatjant += laberint.nodes;

        //Actualizamos los nodos de la búsqueda y juntamos todos los caminos
        laberint.setNodes(nodesViatjant);
        return fusionarCaminos(camiViajante1, camiViajante2, camiViajante3, camiViajante4, camiSalida);
    }


    private Punt operadores(Punt p, int a) {
        switch (a) {
            case 1://Caso derecha
                return new Punt(p.x, p.y + 1, p, 0);
            case 2: //Caso izquierda
                return new Punt(p.x, p.y - 1, p, 0);
            case 3: //Caso arriba
                return new Punt(p.x - 1, p.y, p, 0);
            case 4: //Caso abajo
                return new Punt(p.x + 1, p.y, p, 0);
        }
        return null;
    }

    private Cami fusionarCaminos(Cami c1, Cami c2, Cami c3, Cami c4, Cami salida) {
        Cami caminoCompleto = new Cami(c1.longitud + c2.longitud + c3.longitud + c4.longitud + salida.longitud);

        //Recorremos el camino, desde el ultimo bicho a la salida
        for (int i = 0; i < salida.longitud; i++) {
            caminoCompleto.afegeix(salida.cami[i]);
        }
        //Recorremos el camino al primer bicho mas cercano
        for (int i = 1; i < c4.longitud; i++) {
            caminoCompleto.afegeix(c4.cami[i]);
        }
        //Recorremos el camino al segundo bicho mas cercano
        for (int i = 1; i < c3.longitud; i++) {
            caminoCompleto.afegeix(c3.cami[i]);
        }
        //Recorremos el camino al tercer bicho mas cercano
        for (int i = 1; i < c2.longitud; i++) {
            caminoCompleto.afegeix(c2.cami[i]);
        }
        //Recorremos el camino al cuarto bicho mas cercano
        for (int i = 1; i < c1.longitud; i++) {
            caminoCompleto.afegeix(c1.cami[i]);
        }

        return caminoCompleto;
    }

    private double calcularHeuristica(int tipus, Punt actual, Punt destino) {
        switch (tipus) {
            case MANHATTAN:
                return Math.abs(actual.x - destino.x) + Math.abs(actual.y - destino.y);
            case EUCLIDEA:
                return Math.sqrt(Math.pow(actual.x - destino.x, 2) + Math.pow(actual.y - destino.y, 2));
        }
        return 0;
    }

    //Ordena el arraylist
    private void ordenarLista(ArrayList<Punt2> lista) {
        int i, j;
        Punt2 aux;
        for (i = 0; i < lista.size() - 1; i++)
            for (j = 0; j < lista.size() - i - 1; j++)
                if (lista.get(j + 1).heuristica < lista.get(j).heuristica) {
                    aux = lista.get(j + 1);
                    lista.remove(j + 1);
                    lista.add(j + 1, lista.get(j));
                    lista.remove(j);
                    lista.add(j, aux);
                }
    }

    private Coa2 reorganizaCola(Coa2 prev, boolean DretaValida, boolean EsquerraValida, boolean AmuntValida, boolean AvallValida, Punt dreta, Punt esquerra, Punt amunt, Punt avall) {
        Coa2 ret = new Coa2();
        if (DretaValida) {
            ret.afegeix(dreta);
        }
        if (EsquerraValida) {
            ret.afegeix(esquerra);
        }
        if (AmuntValida) {
            ret.afegeix(amunt);
        }
        if (AvallValida) {
            ret.afegeix(avall);
        }
        while (!prev.buida()) {
            ret.afegeix(prev.treu());
        }
        return ret;
    }


    //Clase coa extends
    class Coa2 extends Coa {
        public boolean cerca(Punt p) {
            boolean found = false;
            Node a;
            a = primer;
            while (a != null && !found) {
                if (p.equals(a.element)) {
                    found = true;
                } else {
                    a = a.següent;
                }
            }
            return found;
        }
    }

    //Clase Punt extends
    class Punt2 extends Punt {
        double heuristica;

        Punt2(int x1, int y1, Punt f, double val) {
            super();
            this.x = x1;
            this.y = y1;
            this.previ = f;
            this.heuristica = val;
        }

        Punt2(Punt p, double val) {

            this.x = p.x;
            this.y = p.y;
            this.previ = p.previ;
            this.heuristica = val;
        }

        public void setHeuristica(double heuristica) {
            this.heuristica = heuristica;
        }
    }
}
