package es.udc.sistemasinteligentes.g13_1.ejemplo;

import es.udc.sistemasinteligentes.g13_1.EstrategiaBusqueda;
import es.udc.sistemasinteligentes.g13_1.ProblemaBusqueda;

public class MainEj1 {

    public static void main(String[] args) throws Exception {
        ProblemaAspiradora.EstadoAspiradora estadoInicial = new ProblemaAspiradora.EstadoAspiradora(ProblemaAspiradora.EstadoAspiradora.PosicionRobot.IZQ,
                                                                                                    ProblemaAspiradora.EstadoAspiradora.PosicionBasura.AMBAS);
        ProblemaBusqueda aspiradora = new ProblemaAspiradora(estadoInicial);

        System.out.println("\n\rESTRATEGIA 4:\n\r");
        EstrategiaBusqueda buscador = new Estrategia4();
        System.out.println(buscador.soluciona(aspiradora));

        System.out.println("\n\rESTRATEGIA BÚSQUEDA GRAFO:\n\r");
        EstrategiaBusqueda buscador2 = new EstrategiaBusquedaGrafo();
        System.out.println(buscador2.soluciona(aspiradora));
    }
}
