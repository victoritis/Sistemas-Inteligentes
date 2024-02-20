package es.udc.sistemasinteligentes.g13_1;

import java.util.List;

public interface EstrategiaBusqueda {
    /**
     * Soluciona el problema de búsqueda, obteniendo un estado meta o arrojando una Excepcion si no encuentra una
     * @param p Problema a solucionar
     * @return Estado meta obtenido
     */
    public abstract List<Nodo> soluciona(ProblemaBusqueda p) throws Exception;
}
