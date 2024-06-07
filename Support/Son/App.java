public class App {
    public static float[][] TabFFT() {

        String nomFichier = "Sources_sonores/Sinusoide.wav";
        String Fichier1 = "Sources_sonores/Sinusoide2.wav";
        String Fichier2 = "Sources_sonores/Sinusoide3Harmoniques.wav";
        String Fichier3 = "Sources_sonores/Bruit.wav";
        String Fichier4 = "Sources_sonores/Carre.wav";
        String Fichier5 = "Sources_sonores/Combinaison.wav";

        Son son = new Son(nomFichier);
        Son son2 = new Son(Fichier1);
        Son son3 = new Son(Fichier2);
        Son son4 = new Son(Fichier3);
        Son son5 = new Son(Fichier4);
        Son son6 = new Son(Fichier5);
        if (son.donnees() == null) {
            System.out.println("Impossible de lire le fichier audio.");
        }

        // Convertir les données audio en tableau de Complexe
        float[] donneesAudio = son.donnees();
        float[] donneesAudio2 = son2.donnees();
        float[] donneesAudio3 = son3.donnees();
        float[] donneesAudio4= son4.donnees();
        float[] donneesAudio5 = son5.donnees();
        float[] donneesAudio6 = son6.donnees();
        int n = 1024;

        // Assurez-vous que la longueur du signal est une puissance de deux pour la FFT
        int tailleFFT = n;

        Complexe[] signal = new Complexe[tailleFFT];
        Complexe[] signal2 = new Complexe[tailleFFT];
        Complexe[] signal3 = new Complexe[tailleFFT];
        Complexe[] signal4 = new Complexe[tailleFFT];
        Complexe[] signal5 = new Complexe[tailleFFT];
        Complexe[] signal6 = new Complexe[tailleFFT];
        for (int i = 0; i < n; i++) {
            signal[i] = new ComplexeCartesien(donneesAudio[i], 0);
            signal2[i] = new ComplexeCartesien(donneesAudio2[i], 0);
            signal3[i] = new ComplexeCartesien(donneesAudio3[i], 0);
            signal4[i] = new ComplexeCartesien(donneesAudio4[i], 0);
            signal5[i] = new ComplexeCartesien(donneesAudio5[i], 0);
            signal6[i] = new ComplexeCartesien(donneesAudio6[i], 0);

        }

        // Appliquer la FFT sur le signal
        Complexe[] resultat = FFTCplx.appliqueSur(signal);
        Complexe[] resultat2 = FFTCplx.appliqueSur(signal2);
        Complexe[] resultat3 = FFTCplx.appliqueSur(signal3);
        Complexe[] resultat4 = FFTCplx.appliqueSur(signal4);
        Complexe[] resultat5 = FFTCplx.appliqueSur(signal5);
        Complexe[] resultat6 = FFTCplx.appliqueSur(signal6);

        // Afficher les résultats de la FFT
        System.out.println("Résultats de la FFT :");
        for (int i = 0; i < resultat.length; i++) {
            System.out.print(i + " : (" + (float) resultat[i].reel() + " ; " + (float) resultat[i].imag() + "i)");
            System.out.println(", (" + (float) resultat[i].mod() + " ; " + (float) resultat[i].arg() + " rad)");
        }
        float tab[] = new float[tailleFFT];
        float tab2[] = new float[tailleFFT];
        float tab3[] = new float[tailleFFT];
        float tab4[] = new float[tailleFFT];
        float tab5[] = new float[tailleFFT];
        float tab6[] = new float[tailleFFT];

        for (int i = 0; i < resultat.length; i++) {
            tab[i] = (float) resultat[i].mod();
            tab2[i] = (float) resultat2[i].mod();
            tab3[i] = (float) resultat3[i].mod();
            tab4[i] = (float) resultat4[i].mod();
            tab5[i] = (float) resultat5[i].mod();
            tab6[i] = (float) resultat6[i].mod();
        }
        float Final[][]= new float[6][tailleFFT];
        Final[0]=tab;
        Final[1]=tab2;
        Final[2]=tab3;
        Final[3]=tab4;
        Final[4]=tab4;
        Final[5]=tab5;
return Final;
    }
}
