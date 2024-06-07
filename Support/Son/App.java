
public class App {
    public static void main(String[] args){
        String fichier = "C:\\Users\\leler\\Desktop\\projet IA\\javaProjetN3\\Sources sonores\\Sinusoide.wav";
        Son test = new Son(fichier);
        Complexe[] signal = new Complexe[test.donnees().length];
        for (int i = 0; i < test.donnees().length; ++i)
            signal[i] = new ComplexeCartesien(Math.cos(2.*Math.PI*i/signal.length/test.frequence()), 0);
        Complexe[] resultat = FFTCplx.appliqueSur(signal);
        for (int i = 0; i < resultat.length; ++i) {
            System.out.print(i+" : ("+(float)resultat[i].reel()+" ; "+(float)resultat[i].imag()+"i)");
            System.out.println(", ("+(float)resultat[i].mod()+" ; "+(float)resultat[i].arg()+" rad)");
        }



    }

}
