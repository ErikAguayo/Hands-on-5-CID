package mLinearMatrix;

import java.util.Arrays;

public class matrixMLR {
    private static Dataset d = new Dataset("TestDataSet.csv");
    private static final double y[]=d.getYData();
    private static double atod[][]=d.getXData();
    private static double matrix[][] = new double[atod.length][atod.length];
    private static double inverse_matrix [] [] = new double[atod.length][atod.length];
    private static double final_matrix[][] = new double[atod.length][atod.length];
    private static double vector_y [] = new double[atod.length];
    private static double bethas [] = new double[atod.length];
    private static double sqrs[][] = new double[atod.length][atod[0].length];
    private static double mults[][] = new double[atod.length][atod[0].length];
    private static double ymults[][] = new double[atod.length][atod[0].length];
    private static double sigmay = 0;
    private static double sigmas[] = new double[atod.length];
    private static double sigmasqr[] = new double[atod.length];
    private static double sigmamult[] = new double[atod.length];
    private static double ysigmamult[] = new double[atod.length];
    private static Inverse i = new Inverse();
   
    public static void matrixMLR() {
        
    }
    public static double[] train() {
        //fill atributes with value 1
     
        calculateMatrixValues();

        inverse_matrix = i.invert(makeMatrix());

        finalMatrix();

        return bethas;
    }

    public static void calculateMatrixValues(){

        for(int i=0;i<d.getSize();i++){
            //fill adding independants
            for(int k=1;k<4;k++){
                sigmas[k]+=atod[k][i];
            }
            //adding dependant variables
            sigmay+=y[i];
            //fill square of independants
            for(int k=1;k<4;k++){
                sqrs[k][i]=atod[k][i]*atod[k][i];
            }
            //get multiplication for independant variables
            mults[1][i]=atod[1][i]*atod[2][i];
            mults[2][i]=atod[1][i]*atod[3][i];
            mults[3][i]=atod[2][i]*atod[3][i];
            //get multiplication for dependant variable
            for(int k=1;k<4;k++){
                ymults[k][i]=y[i]*atod[k][i];
            }

            //fill adding square's independant variables
            for(int k=1;k<4;k++){
                sigmasqr[k]+=sqrs[k][i];
            }
            //get sum of each multiplication of independant variables
            for(int k=1;k<4;k++){
                sigmamult[k]+=mults[k][i];
            }
            //get sum of each multiplication of dependant variable
            for(int k=1;k<4;k++){
                ysigmamult[k]+=ymults[k][i];
            }

        }
    }

    public static double[][] makeMatrix(){
        //fillin matrix with sigmas independants
        matrix[0][0]=d.getSize();
        for(int k=1;k<4;k++){
            matrix[k][0]=matrix[0][k]=sigmas[k];
        }
        //filling matrix with squares sigmas
        for(int k=1;k<4;k++){
            matrix[k][k]=sigmasqr[k];
        }

        matrix[1][2]=matrix[2][1]=sigmamult[1];
        matrix[1][3]=matrix[3][1]=sigmamult[2];
        matrix[2][3]= matrix[3][2]=sigmamult[3];

        vector_y[0]=sigmay;
        for(int k=1;k<4;k++){
            vector_y[k]=ysigmamult[k];
        }

        return matrix;
    }

    public static void finalMatrix() {
        for(int i=0;i<4;i++){
            //matriz final
            for(int j=0;j<4;j++){
                final_matrix[i][j]=vector_y[i]*inverse_matrix[i][j];
            }
            //sacar las bethas
            for(int k=0;k<4;k++){
                bethas[k]+=final_matrix[i][k];
            }

        }
        
    }
}
