package es.udc.sistemasinteligentes.g13_1.ejemplo;

import es.udc.sistemasinteligentes.g13_1.Accion;
import es.udc.sistemasinteligentes.g13_1.*;

import java.util.ArrayList;
import java.util.List;

public class Estrategia4 implements EstrategiaBusqueda {

    public Estrategia4() {
    }

    @Override
    public List<Nodo> soluciona(ProblemaBusqueda p) throws Exception{
        ArrayList<Nodo> explorados = new ArrayList<>();
        Nodo estadoActual = new Nodo( p.getEstadoInicial(), null, null);
        explorados.add(estadoActual);

        int i = 1;

        System.out.println((i++) + " - Empezando búsqueda en " + estadoActual);

        while (!p.esMeta(estadoActual.getEstado())){
            System.out.println((i++) + " - " + estadoActual + " no es meta");
            Accion[] accionesDisponibles = p.acciones(estadoActual.getEstado());
            boolean modificado = false;
            for (Accion acc: accionesDisponibles) {
                Nodo sc = new Nodo(p.result(estadoActual.getEstado(), acc), acc, estadoActual);
                System.out.println((i++) + " - RESULT(" + estadoActual + ","+ acc + ")=" + sc);
                if (!explorados.contains(sc)) {
                    estadoActual = sc;
                    System.out.println((i++) + " - " + sc + " NO explorado");
                    explorados.add(estadoActual);
                    modificado = true;
                    System.out.println((i++) + " - Estado actual cambiado a " + estadoActual);
                    break;
                }
                else
                    System.out.println((i++) + " - " + sc + " ya explorado");
            }
            if (!modificado) throw new Exception("No se ha podido encontrar una solución");
            if(i > 100) return null;
        }
        System.out.println((i) + " - FIN - " + estadoActual);
        return reconstruye_sol(estadoActual);
    }

    public List<Nodo> reconstruye_sol(Nodo n){
        List<Nodo> solucion = new ArrayList<>();
        Nodo actual=n;
        while(actual!=null){
            solucion.add(actual);
            actual=actual.getPadre();
        }
        return solucion;
    }
}
