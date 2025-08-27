package com.example.allocation.util;

import java.util.Arrays;

/**
 * Hungarian Algorithm (a.k.a Kuhn-Munkres) for rectangular cost matrices.
 * Returns for each row the assigned column index (or -1 if unassigned).
 */
public final class HungarianAlgorithm {

    private HungarianAlgorithm(){}

    public static int[] assign(double[][] cost) {
        int nRows = cost.length;
        int nCols = cost[0].length;
        int n = Math.max(nRows, nCols);

        // Pad to square with large costs
        double INF = 1e12;
        double[][] a = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i < nRows && j < nCols) a[i][j] = cost[i][j];
                else a[i][j] = INF;
            }
        }

        double[] u = new double[n + 1];
        double[] v = new double[n + 1];
        int[] p = new int[n + 1];
        int[] way = new int[n + 1];

        Arrays.fill(p, 0);
        for (int i = 1; i <= n; i++) {
            p[0] = i;
            double[] minv = new double[n + 1];
            boolean[] used = new boolean[n + 1];
            Arrays.fill(minv, Double.POSITIVE_INFINITY);
            Arrays.fill(used, false);
            int j0 = 0;

            do {
                used[j0] = true;
                int i0 = p[j0], j1 = 0;
                double delta = Double.POSITIVE_INFINITY;
                for (int j = 1; j <= n; j++) {
                    if (used[j]) continue;
                    double cur = a[i0 - 1][j - 1] - u[i0] - v[j];
                    if (cur < minv[j]) {
                        minv[j] = cur;
                        way[j] = j0;
                    }
                    if (minv[j] < delta) {
                        delta = minv[j];
                        j1 = j;
                    }
                }
                for (int j = 0; j <= n; j++) {
                    if (used[j]) {
                        u[p[j]] += delta;
                        v[j] -= delta;
                    } else {
                        minv[j] -= delta;
                    }
                }
                j0 = j1;
            } while (p[j0] != 0);

            do {
                int j1 = way[j0];
                p[j0] = p[j1];
                j0 = j1;
            } while (j0 != 0);
        }

        int[] assignment = new int[nRows];
        Arrays.fill(assignment, -1);
        for (int j = 1; j <= n; j++) {
            if (p[j] != 0 && p[j] - 1 < nRows && j - 1 < nCols) {
                assignment[p[j] - 1] = j - 1;
            }
        }
        return assignment;
    }
}
