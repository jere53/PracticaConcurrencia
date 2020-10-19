package tp3.ej2_2.MatrixMul.mult;

import tp3.ej2_2.MatrixMul.IMatrix;

public class ConcurrentMultiplication implements IMultiplication {
    @Override
    public IMatrix multiply(IMatrix a, IMatrix b) {
        if (a.getColumns()!=b.getRows())
            throw new RuntimeException("La cantidad de columnas de la matriz a tiene que ser igual a la cantidad de filas de la matriz b");
        IMatrix res = a.createMatrix(a.getRows(), b.getColumns());
        Thread [] threads = new Thread[a.getRows()];
        //Por cada fila de A
        for (int i=0;i<a.getRows();i++){
            //Por cada columna de B
            int finali = i;
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j=0;j<b.getColumns();j++){
                        //Realiza la multiplicación para la posición i j
                        for (int k=0;k<b.getRows();k++)
                            res.set(finali, j, res.get(finali, j)+
                                    a.get(finali, k)*b.get(k, j));
                    }
                }
            });
            threads[i].start();
        }
        for (Thread tj : threads){
            try {
                tj.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return res;
    }
}
