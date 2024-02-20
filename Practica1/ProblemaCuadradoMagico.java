package es.udc.sistemasinteligentes.g13_1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProblemaCuadradoMagico extends ProblemaBusqueda{

    /**
     * Soluciona el problema de búsqueda, obteniendo un estado meta o arrojando una Excepcion si no encuentra una
     * @param estado estado
     */


    public ProblemaCuadradoMagico(Estado estado){
        super(estado);
    }
    @Override
    public boolean esMeta(Estado es) {
        int[][] matrix = ((EstadoCuadrado)es).getMatrix();
        int resultado;
        int n = ((EstadoCuadrado) es).getN();


        for(int i=0;i< n;i++){       //Fila
            resultado=0;
            for(int j=0;j<n;j++){
                resultado=resultado+matrix[i][j];
            }
            if(resultado!=n*(n*n+1)/2){
                return false;
            }
        }

        for(int i=0;i< n;i++){       //Columna
            resultado=0;
            for(int j=0;j<n;j++){
                resultado=resultado+matrix[j][i];
            }
            if(resultado!=n*(n*n+1)/2){
                return false;
            }
        }
        resultado = 0;
        for(int i=0;i< n;i++) {       //Diagonal 1

            for (int j = 0; j < n; j++) {
                if(i==j){
                    resultado = resultado + matrix[i][j];
                }
            }
        }
        if (resultado != n * (n*n + 1) / 2) {
            return false;
        }
        resultado = 0;
        for(int i=0;i< n;i++) {       //Diagonal 2

            for (int j = 0; j < n; j++) {
                if(i==n-j-1){
                    resultado = resultado + matrix[i][j];
                }
            }
        }
        return resultado == n * (n * n + 1) / 2;
    }

    @Override
    public Accion[] acciones(Estado es) {
        int n = ((EstadoCuadrado) es).getN();
        List<Accion> accion = new ArrayList<>();
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                for(int k=1;k<n*n;k++) {
                    if((((EstadoCuadrado) es).getMatrix())[i][j] == 0)
                        accion.add(new AccionCuadrado(j,i,k));
                }
            }
        }
        return accion.toArray(new Accion[0]);
    }

    public static class EstadoCuadrado extends Estado{

        private int[][] matrix;
        private final int n;

        public EstadoCuadrado(int n){
            this.n=n;
            matrix = new int[n][n];
        }

        public int[][] getMatrix() {
            return matrix;
        }

        public void setMatrix(int[][] matrix) {
            this.matrix = matrix;
        }

        public int getN() {
            return n;
        }


        @Override
        public String toString() {
            StringBuilder chain = new StringBuilder("[");
            for (int[] integers : matrix) {
                chain.append("[");
                for (int j = 0; j < matrix.length; j++) {
                    chain.append(" ").append(integers[j]);
                }
                chain.append("]");
            }
            return chain + "]";
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            EstadoCuadrado that = (EstadoCuadrado) o;

            if (n != that.n) return false;
            return Arrays.deepEquals(matrix, that.matrix);
        }

        @Override
        public int hashCode() {
            int result = Arrays.deepHashCode(matrix);
            result = 31 * result + n;
            return result;
        }
    }

    public static class AccionCuadrado extends Accion{

        private final int col;
        private final int row;
        private final int valor;

        public AccionCuadrado(int col,int row,int valor){
            this.col=col;
            this.row=row;
            this.valor=valor;
        }

        @Override
        public String toString() {
            return Integer.toString(col)+ row + valor;
        }

        @Override
        public boolean esAplicable(Estado es) {
            EstadoCuadrado estadoCuadrado= (EstadoCuadrado) es;
            return col <= estadoCuadrado.getN() && row <= estadoCuadrado.getN() && estadoCuadrado.getMatrix()[row][col] == 0;
        }

        @Override
        public Estado aplicaA(Estado es) {
            int[][] matrix;
            int [][]matrix2 = new int[((EstadoCuadrado)es).getN()][((EstadoCuadrado)es).getN()];
            EstadoCuadrado estadoCuadrado = new EstadoCuadrado(((EstadoCuadrado)es).getN());
            matrix =(((EstadoCuadrado) es).getMatrix());
            for(int i = 0; i <((EstadoCuadrado)es).getN(); i++ )                                //Se recorre la matriz elemento a elemento y se copia manualmente
                for(int j = 0; j < ((EstadoCuadrado)es).getN(); j++)
                    matrix2[i][j] = matrix[i][j];

            matrix2[row][col] = valor;
            estadoCuadrado.setMatrix(matrix2);

            return estadoCuadrado;
        }
    }
}
