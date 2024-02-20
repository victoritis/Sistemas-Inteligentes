package es.udc.sistemasinteligentes.g13_1;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BusquedaProfundidad implements EstrategiaBusqueda{

    public List<Nodo> reconstruye_sol(Nodo n){
        List<Nodo> solucion = new ArrayList<>();
        Nodo actual=n;
        while(actual!=null){
            solucion.add(actual);
            actual=actual.getPadre();
        }
        return solucion;
    }

    @Override
    public List<Nodo> soluciona(ProblemaBusqueda p) throws Exception {
        int nodos_expandidos=0;
        int nodos_creados=0;
        Stack<Nodo> frontera = new Stack<>();            //Se implementa un pila para almacenar los nodos frontera
        List<Nodo> explorado = new ArrayList<>();
        Nodo n;
        Estado s;
        List<Nodo> h;
        int cnt=1;
        System.out.println((cnt++) + " - Empezando búsqueda en " + p.getEstadoInicial());

        frontera.push(new Nodo(p.getEstadoInicial(),null,null));

        do{
            if(frontera.isEmpty()){                         //Si no hay nodos frontera se detiene la ejecución
                throw new Exception();
            }
            else{
                n= frontera.pop();
                s= n.estado;
                System.out.println((cnt++) + " - Estado actual cambiado a " + s);
                if(p.esMeta(s)){                                    //Se ha llegado a la meta
                    System.out.println((cnt) + " - " + s + " es meta");
                    System.out.println("Nodos expandidos:"+nodos_expandidos);
                    System.out.println("Nodos creados:"+nodos_creados);
                    System.out.print("Solución: ");
                    return reconstruye_sol(n);
                }
                else{                                               //Se continúa añadiendo nodos a la lista de explorados
                    System.out.println((cnt++) + " - " + p.getEstadoInicial() + " no es meta");
                    explorado.add(n);
                    nodos_expandidos++;
                    h=new ArrayList<>();
                    for(Accion a:p.acciones(s)){
                        h.add(new Nodo(a.aplicaA(s),a,n));
                        nodos_creados++;
                    }
                    for(Nodo nh:h) {
                        if(! (frontera.contains(nh) || explorado.contains(nh) )) {                      //Se comprueba si el nodo no está ni en la la lista de nodos frontera ni nodos explorados
                            frontera.push(nh);
                        }
                    }
                }

            }
        }
        while(true);
    }

}
