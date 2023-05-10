package com.example.Iteration;

import java.util.Arrays;

public class IterationMethod {
    private static double[][] matrix;
    private static double[] vector;

    public IterationMethod(double[][] mat, double[] vec) {
        vector = new double[4];
        matrix = new double[4][5];
        for (int i = 0; i < 4; i ++) {
            vector[i] = vec[i];
            for (int j = 0; j < 5; j++) {
                matrix[i][j] = mat[i][j];
            }
        }
    }

    public double[] function() {
        double[][] newMatrix = new double[4][5];
        for (int i = 0; i < 4; i ++) {
            for (int j = 0; j < 4; j++) {
                newMatrix[i][j] = vector[j];
            }
            newMatrix[i][4] = matrix[i][4];
        }
        vector = createVector(newMatrix);

        while(true) {
            for (int i = 0; i < 4; i ++) {
                for (int j = 0; j < 4; j++) {
                    newMatrix[i][j] = vector[j];
                }
            }
            double[] vec1 = createVector(newMatrix);

            double[] vec2 = Arrays.copyOf(vec1, vec1.length);

            for(int i = 0; i < 4; i++) {
                //vec2[i] = Math.abs(vec2[i]) - Math.abs(vector[i]);
                vec2[i] = Math.abs(vec2[i] - vector[i]);
            }

            double max = 0;
            for(int i = 0; i < 4; i++) {
                if(vec2[i] > max) {
                    max = vec2[i];
                }
            }


            System.out.println("vec2" + Arrays.toString(vec2));
            if(0.000017 >= max && max <= 0.000050) {
                return vec1;
            }
            vector = vec1;



        }
    }

    private double[] createVector(double[][] newMatrix) {
        double[] vec = new double[4];

        for (int i = 0; i < 4; i ++) {
            vec[i] = newMatrix[i][4];
            for (int j = 0; j < 4; j++) {
                if(i != j){
                    vec[i] -= (matrix[i][j] * newMatrix[i][j]);
                }
            }
            vec[i] /=  matrix[i][i];
        }
        return vec;
    }

    private void checkLinearSystems() {
        double[] mass = new double[4];
        for (int i = 0; i < 4; i ++) {
            mass[i] = 0;
            for (int j = 0; j < 4; j++) {
                if(i != j) {
                    mass[i] += Math.abs(matrix[i][j]);
                }
            }
            if(mass[i] >= matrix[i][i]) {
                throw new RuntimeException();
            }
        }
    }
}
