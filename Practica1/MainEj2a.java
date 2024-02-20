package es.udc.sistemasinteligentes.g13_1;


public class MainEj2a {
    public static void main(String[] args) throws Exception {
        int[][] matrix = {{4,9,2},{3,5,0},{0,1,0}};
        ProblemaCuadradoMagico.EstadoCuadrado estadoInicial = new ProblemaCuadradoMagico.EstadoCuadrado(3);
        estadoInicial.setMatrix(matrix);
        ProblemaBusqueda CuadradoMagico = new ProblemaCuadradoMagico(estadoInicial);


        System.out.println("\n\rBúsqueda en profundidad:\r");
        EstrategiaBusqueda buscador = new BusquedaProfundidad();
        System.out.println(buscador.soluciona(CuadradoMagico));

        System.out.println("\n\rBúsqueda en anchura:\r");
        EstrategiaBusqueda buscador2 = new BusquedaAnchura();
        System.out.println(buscador2.soluciona(CuadradoMagico));

    }
}
