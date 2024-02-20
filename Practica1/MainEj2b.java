package es.udc.sistemasinteligentes.g13_1;

public class MainEj2b {
    public static void main(String[] args) throws Exception{
        int[][] matrix = {{4,9,2},{3,5,0},{0,1,0}};
        ProblemaCuadradoMagico.EstadoCuadrado estadoInicial = new ProblemaCuadradoMagico.EstadoCuadrado(3);
        estadoInicial.setMatrix(matrix);
        ProblemaBusqueda CuadradoMagico = new ProblemaCuadradoMagico(estadoInicial);


        EstrategiaBusquedaInformada buscador = new EstrategiaAasterisco();
        System.out.println(buscador.soluciona(CuadradoMagico,new HeuristicaCuadradoMagico()));

    }
}
