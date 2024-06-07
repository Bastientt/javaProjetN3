public class App {
    public static void main(String[] args) {

        String nomFichier = "Sources_sonores/Sinusoide.wav";
        Son son = new Son(nomFichier);
        if (son.donnees() == null) {
            System.out.println("Impossible de lire le fichier audio.");
            return;
        }

        // Convertir les données audio en tableau de Complexe
        float[] donneesAudio = son.donnees();
        int n = 1024;

        // Assurez-vous que la longueur du signal est une puissance de deux pour la FFT
        int tailleFFT = n;

        Complexe[] signal = new Complexe[tailleFFT];
        for (int i = 0; i < n; i++) {
            signal[i] = new ComplexeCartesien(donneesAudio[i], 0);
        }

        // Appliquer la FFT sur le signal
        Complexe[] resultat = FFTCplx.appliqueSur(signal);

        // Afficher les résultats de la FFT
        System.out.println("Résultats de la FFT :");
        for (int i = 0; i < resultat.length; i++) {
            System.out.print(i + " : (" + (float) resultat[i].reel() + " ; " + (float) resultat[i].imag() + "i)");
            System.out.println(", (" + (float) resultat[i].mod() + " ; " + (float) resultat[i].arg() + " rad)");
        }
        float tab[] = new float[tailleFFT];
        float Final[][]= new float[tailleFFT][1];
        for (int i = 0; i < resultat.length; i++) {
            tab[i] = (float) resultat[i].mod();
            Final[i][0]= tab[i];
        }
        for(int i = 0; i< Final.length;i++) {
            System.out.println(Final[i][0]);
        }
    }
}
