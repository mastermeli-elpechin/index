
public class Operador {

    public static <T extends Comparable<T>> T[] burbuja(T[] v) {
        boolean bandera = true;
        int superior = v.length;
        while (bandera) {
            bandera = false;
            superior--;
            for (int i = 0; i < superior; i++) {
                if (v[i].compareTo(v[i + 1]) > 0) {
                    T temp = v[i];
                    v[i] = v[i + 1];
                    v[i + 1] = temp;
                    bandera = true;
                }
            }
        }
        return v;
    }
}
