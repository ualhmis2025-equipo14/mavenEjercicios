package ual.hmis.sesion06;

import java.util.*;

public class MezclaLineal {
    private MezclaLineal() {
        // Constructor privado para evitar instanciación
    }
    
    public static <T extends Comparable<T>> List<T> mezclaLineal(List<T> listaA, List<T> listaB) {
        List<T> resultado = new ArrayList<>();
        int i = 0, j = 0;
        while (i < listaA.size() && j < listaB.size()) {
            if (listaA.get(i).compareTo(listaB.get(j)) <= 0) {
                resultado.add(listaA.get(i));
                i++;
            } else {
                resultado.add(listaB.get(j));
                j++;
            }
        }
        while (i < listaA.size()) {
            resultado.add(listaA.get(i));
            i++;
        }
        while (j < listaB.size()) {
            resultado.add(listaB.get(j));
            j++;
        }
        return resultado;
    }
}
