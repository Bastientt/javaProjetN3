public class TestIA {
    public static void main(String[] args) {

        final float[] resultatsAND = {0, 0, 0, 1};
        final float[] resultatsOr = {0, 1, 1, 1};
        final float[] resultatsNOR = {1, 0, 0, 0};
        final float[] resultFFTSig = {1, 1, 1, 0, 0, 0};
        final float[][] entrees = {{0, 0}, {0, 1}, {1, 0}, {1, 1}};


        iNeurone neuroneHeaviside = new NeuroneHeaviside(entrees[0].length);
        iNeurone neuroneSigmoide = new NeuroneSigmoide(entrees[0].length);
        iNeurone neuroneRelu = new NeuroneRelu(entrees[0].length);

        float[][] entreeFFT = App.TabFFT();

        Apprentissage(neuroneSigmoide, resultFFTSig, entreeFFT);
        TestFFTNeurone(neuroneSigmoide, 1.0F, entreeFFT[0]);
        TestFFTNeurone(neuroneSigmoide, 1.0F, entreeFFT[1]);
        TestFFTNeurone(neuroneSigmoide, 1.0F, entreeFFT[2]);
        TestFFTNeurone(neuroneSigmoide, 1.0F, entreeFFT[3]);
        TestFFTNeurone(neuroneSigmoide, 1.0F, entreeFFT[4]);
        TestFFTNeurone(neuroneSigmoide, 1.0F, entreeFFT[5]);

/*
        Apprentissage(neuroneHeaviside, resultatsOr, entrees);
        TestNeurone(neuroneHeaviside,resultatsOr);*/

        // Apprentissage(neuroneSigmoide, resultatsOr, entrees);
        //TestNeurone(neuroneSigmoide,resultatsOr);


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
        System.out.println("CPT ERROR : " + cptError + "nombre d'essais : " + nbTry + "pourcentage : " + pourcentage + "%");
    }

    public static void TestFFTNeurone(final iNeurone neuroneEntrainé, final float resultat, float[] entréeSigmoide) {
        neuroneEntrainé.metAJour(entréeSigmoide);
        float value =neuroneEntrainé.sortie();
        if(value > 0.5) {
            value=1;
        }
        else{
            value=0;
        }
        if(value == resultat) {
            System.out.println("Sinus ");
        }
        else{
            System.out.println("Autre ");
        }
    }
}
