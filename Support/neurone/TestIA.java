import java.util.Scanner;

public class TestIA {
    public static void main(String[] args) {

        final float[] resultatsAND = {0, 0, 0, 1};
        final float[] resultatsOr = {0, 1, 1, 1};
        final float[] resultatsXOr = {0, 1, 1, 0};
        final float[] resultatsNOR = {1, 0, 0, 0};
        final float[] resultFFTSig = {1, 1, 1, 0, 0, 0};
        final float[][] entrees = {{0, 0}, {0, 1}, {1, 0}, {1, 1}};

        iNeurone neuroneHeaviside = new NeuroneHeaviside(entrees[0].length);
        iNeurone neuroneSigmoide = new NeuroneSigmoide(entrees[0].length);

        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        while (choice != 0) {
            System.out.println("=== Menu Principal ===");
            System.out.println("1. Tester Heaviside Porte OR");
            System.out.println("2. Tester Sigmoïde Porte OR");
            System.out.println("3. Tester FFT Sur Sinusoidale");
            System.out.println("0. Quitter");
            System.out.print("Entrez votre choix: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    Apprentissage(neuroneHeaviside, resultatsOr, entrees);
                    TestNeurone(neuroneHeaviside, resultatsOr, entrees);
                    break;
                case 2:
                    Apprentissage(neuroneSigmoide, resultatsOr, entrees);
                    TestNeurone(neuroneSigmoide, resultatsOr, entrees);
                    break;
                case 3:
                    float[][] entreeFFT = App.TabFFT();
                    Apprentissage(neuroneSigmoide, resultFFTSig, entreeFFT);
                    int cptError = 0;
                    for (int i = 0; i < 6; ++i) {
                        if (i < 3) {
                            System.out.print("Entrée : Sinus         |         ");
                            if (!TestFFTNeurone(neuroneSigmoide, entreeFFT[i])) {
                                cptError++;
                            }
                        } else {
                            System.out.print("Entrée : Autre         |         ");
                            if (TestFFTNeurone(neuroneSigmoide, entreeFFT[i])) {
                                cptError++;
                            }
                        }
                    }
                    System.out.println("pourcentage de réussite : " + (float) ((6 - cptError) / 6) * 100 + "%");
            }
        }
    }
    public static iNeurone Apprentissage(final iNeurone neurone, final float[] resultat, final float[][] entrees) {
        neurone.apprentissage(entrees, resultat);
        System.out.println("apprentissage Fini");
        return neurone;
    }


    public static void TestNeurone(final iNeurone neuroneEntrainé, final float[] resultat, float[][] entreeBruitée) {
        int nbTry = 0;
        int cptError = 0;
        for (float bruit = -0.1f; bruit <= 0.1f; bruit += 0.01f) {

            for (int i = 0; i < entreeBruitée.length; i++) {
                for (int j = 0; j < entreeBruitée[i].length; j++) {
                    entreeBruitée[i][j] = entreeBruitée[i][j] + bruit;
                    System.out.println("bruit : " + bruit);
                }
            }
            nbTry++;
            final Neurone vueNeurone = (Neurone) neuroneEntrainé;
            System.out.print("Synapses : ");
            for (int i = 0; i < entreeBruitée.length; ++i) {
                final float[] entree = entreeBruitée[i];
                neuroneEntrainé.metAJour(entree);
                System.out.println("Sortie : " + neuroneEntrainé.sortie() + "resultat attentu : " + resultat[i]);
                float sortie = neuroneEntrainé.sortie();
                if (neuroneEntrainé.getClass() == NeuroneSigmoide.class) {
                    if (sortie >= 0.5) {
                        sortie = 1;
                    } else {
                        sortie = 0;
                    }
                }
                if (sortie != resultat[i]) {
                    System.out.println("Error putain de ta mère");
                    cptError++;
                }
            }
        }

        float pourcentage = (((float) ((nbTry) * 4 - cptError) / (nbTry * 4)) * 100);
        System.out.println("CPT ERROR : " + cptError + " nombre d'essais : " + nbTry + " pourcentage : " + pourcentage + "%");
    }

    public static boolean TestFFTNeurone(final iNeurone neuroneEntrainé, float[] entréeSigmoide) {
        neuroneEntrainé.metAJour(entréeSigmoide);
        if(neuroneEntrainé.sortie() > 0.5) {
            System.out.println("Sortie : Sinus ");
            return true;
        }
        else{
            System.out.println("Sortie : Autre ");
            return false;
        }
    }
}
