public class App {
    public static float findMax(float[] array) {
        float max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }
    public static float[] divideArray(float[] array, float divisor) {
        if (divisor == 0) {
            throw new IllegalArgumentException("Divisor cannot be zero");
        }

        float[] result = new float[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i] / divisor;
        }
        return result;
    }
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
        int n = 1024;
        float[] donneesAudio = son.bloc_deTaille(1,n);
        float[] donneesAudio2 = son2.bloc_deTaille(1,n);
        float[] donneesAudio3 = son3.bloc_deTaille(1,n);
        float[] donneesAudio4= son4.bloc_deTaille(1,n);
        float[] donneesAudio5 = son5.bloc_deTaille(1,n);
        float[] donneesAudio6 = son6.bloc_deTaille(1,n);

        float max= findMax(donneesAudio);
        float max2= findMax(donneesAudio2);
        float max3= findMax(donneesAudio3);
        float max4= findMax(donneesAudio4);
        float max5= findMax(donneesAudio5);
        float max6= findMax(donneesAudio6);

        float[] donneesAudioNorme = divideArray(donneesAudio,max);
        float[] donneesAudio2Norme = divideArray(donneesAudio2,max2);
        float[] donneesAudio3Norme = divideArray(donneesAudio3,max3);
        float[] donneesAudio4Norme = divideArray(donneesAudio4,max4);
        float[] donneesAudio5Norme = divideArray(donneesAudio5,max5);
        float[] donneesAudio6Norme = divideArray(donneesAudio6,max6);



        int tailleFFT = n;

        Complexe[] signal = new Complexe[tailleFFT];
        Complexe[] signal2 = new Complexe[tailleFFT];
        Complexe[] signal3 = new Complexe[tailleFFT];
        Complexe[] signal4 = new Complexe[tailleFFT];
        Complexe[] signal5 = new Complexe[tailleFFT];
        Complexe[] signal6 = new Complexe[tailleFFT];
        for (int i = 0; i < n; i++) {
            signal[i] = new ComplexeCartesien(donneesAudioNorme[i], 0);
            signal2[i] = new ComplexeCartesien(donneesAudio2Norme[i], 0);
            signal3[i] = new ComplexeCartesien(donneesAudio3Norme[i], 0);
            signal4[i] = new ComplexeCartesien(donneesAudio4Norme[i], 0);
            signal5[i] = new ComplexeCartesien(donneesAudio5Norme[i], 0);
            signal6[i] = new ComplexeCartesien(donneesAudio6Norme[i], 0);

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
