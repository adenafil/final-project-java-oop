package ade.animelist.components.utilcomponent;

/**
 * Method untuk menghitung
 */
public class Counter {
    // dekrlarasi index dengan nilai 1
    public static int a = 1;

    /**
     * Method untuk melakukan increment
     * @return -> Nilai dari a
     */
    public static int incremennt() {
        return ++a;
    }

    /**
     * Method untuk mengambilakan jadi nilai 1 jika ingin digunakan
     */
    public static void getStartedUsingIncrement() {
        a = 1;
    }

}
