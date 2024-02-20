package es.udc.sistemasinteligentes.g13_1;

import java.util.Objects;

public class Nodo implements Comparable<Nodo> {

    public Nodo(Estado estado,Accion accion,Nodo padre){
        this.estado=estado;
        this.accion=accion;
        this.padre=padre;
    }

    public Nodo(Estado estado,Accion accion,Nodo padre,int coste){
        this.estado=estado;
        this.accion=accion;
        this.padre=padre;
        this.coste=coste;
        f=coste+(int)new HeuristicaCuadradoMagico().evalua(estado);
    }


    public Estado getEstado() {
        return estado;
    }

    public Nodo getPadre() {
        return padre;
    }

    Accion accion;
    Estado estado;
    Nodo padre;
    int coste;
    int f;


    @Override
    public String toString() {
        return estado.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nodo nodo = (Nodo) o;
        return nodo.getEstado().equals(estado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accion, estado, padre);
    }

    @Override
    public int compareTo(Nodo nodo){
        return this.coste- nodo.coste;
    }
}
