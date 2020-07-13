package cafe;

public class PrincipaleCafe {
    public static void main(String[] args) {
        Boisson b = new Deca();
        b = new BoissonCreme(new BoissonChantilly(new BoissonChantilly(b)));
        System.out.println(b);
    }
}
