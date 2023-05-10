package com.example.UI;

import com.example.Iteration.IterationMethod;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        double[][] mat = {  {5, 1, -1, 1, 90},
                            {1, -4, 1, -1, 24},
                            {-1, 1, 4, 1, -15},
                            {1, 2, 1, -5, 32}};
        double[] vec = {21, 1, 2, 0.5};

        IterationMethod test = new IterationMethod(mat, vec);

        System.out.println(Arrays.toString(test.function()));
    }
}
