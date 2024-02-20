package es.udc.sistemasinteligentes.g13_1;

import java.util.*;

public class EstrategiaAasterisco implements EstrategiaBusquedaInformada{

    @Override
    public Estado soluciona(ProblemaBusqueda pb, Heuristica heuristica) throws IllegalArgumentException {
        PriorityQueue<Nodo> frontera = new PriorityQueue<>(2048, Comparator.comparingInt(coste -> coste.coste));                            //Los nodos frontera se almacenan en una cola de prioridad aplicando el criterio de menor coste
        List<Nodo> explorado = new ArrayList<>();
        Nodo n;
        Estado s;
        List<Nodo> h;
        int cn;
        int cnt=1;
        System.out.println((cnt++) + " - Empezando búsqueda en " + pb.getEstadoInicial());
        frontera.add(new Nodo(pb.getEstadoInicial(), null, null));

        do {
            if (frontera.isEmpty()) {               //Si no hay nodos frontera se detiene la ejecución
                throw new IllegalArgumentException();
            } else {
                n = frontera.poll();
                s = n.estado;
                System.out.println((cnt++) + " - Estado actual cambiado a " + s);
                if (pb.esMeta(s)) {             //Se ha llegado a la meta
                    System.out.println((cnt) + " - " + s + " es meta");
                    System.out.print("Solución: ");
                    return s;
                } else {                                            //Se continúa añadiendo nodos a la lista de explorados
                    System.out.println((cnt++) + " - " + pb.getEstadoInicial() + " no es meta");
                    explorado.add(n);
                    h = new ArrayList<>();
                    for (Accion a : pb.acciones(s)) {
                        cn = n.coste + (int) heuristica.evalua(a.aplicaA(s));
                        h.add(new Nodo(a.aplicaA(s), a, n, cn));
                    }
                    for(Nodo nodo : h) {
                        boolean explorado2 = false, front = false;
                        for (Nodo nodo2 : explorado)
                            if (nodo2.estado.equals(nodo.estado)) {
                                explorado2 = true;
                                break;
                            }
                        if (explorado2) continue;
                        ArrayList<Nodo> borrados = new ArrayList<>();
                        for (Nodo nodo3 : frontera)
                            if (nodo3.estado.equals(nodo.estado)) {
                                front = true;
                                if (nodo.coste < nodo3.f)
                                    borrados.add(nodo3);
                            }
                        frontera.removeAll(borrados);
                        if (!front || !borrados.isEmpty()) frontera.add(nodo);                                              //Se comprueba si el nodo no está ni en la la lista de nodos frontera ni nodos explorados
                    }
                }

            }
        }
        while (!pb.esMeta(s));
        return s;
    }
}
