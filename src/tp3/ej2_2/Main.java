package tp3.ej2_2;
import tp3.ej2_2.MatrixMul.*;
import tp3.ej2_2.MatrixMul.mult.ConcurrentMultiplication;

public class Main {
    public static void main(String[] args) {
//        DenseMatrix A = (DenseMatrix) Utils.generateDenseSquareMatrix(2000);
//        DenseMatrix B = (DenseMatrix) Utils.generateDenseSquareMatrix(2000);
//        ConcurrentMultiplication C = new ConcurrentMultiplication();
//        //System.out.println(Utils.verifyMultiplication(A,B, C, 10)); funciona!!!
//        for (int i = 1; i < 9; i++) {
//            System.out.println(Utils.measureTime(A, B, C));
//        }
        SparseMatrix As = (SparseMatrix) Utils.generateSparseSquareMatrix(700, 0.9);
        SparseMatrix Bs = (SparseMatrix) Utils.generateSparseSquareMatrix(700, 0.9);
        ConcurrentMultiplication Cs = new ConcurrentMultiplication();
        System.out.println(Utils.verifyMultiplication(As,Bs,Cs,10)); //no anda (NPE)
//        for (int i = 1; i < 9; i++) {
//            System.out.println(Utils.measureTime(As, Bs, Cs));
//        }
    }
}
