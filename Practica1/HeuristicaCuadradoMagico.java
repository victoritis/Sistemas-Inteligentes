package es.udc.sistemasinteligentes.g13_1;

public class HeuristicaCuadradoMagico extends Heuristica{
    public float evalua(Estado e){
        int[][] matrix = ((ProblemaCuadradoMagico.EstadoCuadrado) e).getMatrix();
        int resultado;
        int ceros=0;
        int n = ((ProblemaCuadradoMagico.EstadoCuadrado) e).getN();


        for(int i=0;i< n;i++){       //Fila
            resultado=0;
            for(int j=0;j<n;j++){
                resultado=resultado+matrix[i][j];
                if(matrix[i][j]==0){
                    ceros++;
                }
            }

            if(resultado > n*(n*n+1)/2){
                return 10;
            }
        }

        for(int i=0;i< n;i++){       //Columna
            resultado=0;
            for(int j=0;j<n;j++){
                resultado=resultado+matrix[j][i];
            }

            if(resultado > n*(n*n+1)/2){
                return 10;
            }
        }
        resultado = 0;
        for(int i=0;i< n;i++) {       //Diagonal 1

            for (int j = 0; j < n; j++) {
                if(i==j){
                    resultado = resultado + matrix[i][j];
                }
            }

            if(resultado > n*(n*n+1)/2){
                return 10;
            }
        }
        resultado = 0;
        for(int i=0;i< n;i++) {       //Diagonal 2

            for (int j = 0; j < n; j++) {
                if(i==n-j-1){
                    resultado = resultado + matrix[i][j];
                }
            }
        }

        if(resultado > n*(n*n+1)/2){
            return 10;
        }

        return ceros;
    }
}
