public class TestIA {
    public static void main(String[] args)
    {
        int cptError=0;
        final float[] resultatsAND = {0, 0, 0, 1};
        final float[] resultatsOr = {0,1,1,1};
        int nbTry=0;

        final float[][] entrees = {{0, 0},{0, 1},{1, 0},{1, 1}};
        final iNeurone n = new NeuroneHeaviside(entrees[0].length);
        System.out.println("Apprentissage…");
        System.out.println("Nombre de tours : "+n.apprentissage(entrees, resultatsAND));


        for(float bruit = -0.1f; bruit <= 0.1f; bruit += 0.01f) {
            nbTry++;
            final float[][] entreeBruitée = {{0+bruit, 0+bruit},{0+bruit, 1+bruit},{1+bruit, 0+bruit},{1+bruit, 1+bruit}};
        final Neurone vueNeurone = (Neurone)n;
        System.out.print("Synapses : ");
        for (final float f : vueNeurone.synapses())
            System.out.print(f+" ");
        System.out.print("\nBiais : ");
        System.out.println(vueNeurone.biais());
        for (int i = 0; i < entreeBruitée.length; ++i)
        {
            final float[] entree = entreeBruitée[i];
            n.metAJour(entree);
            System.out.println("error Sortie : " + n.sortie() + "resultat attentu : " + resultatsAND[i]);
            if(n.sortie() != resultatsAND[i]) {
                System.out.println("Error putain de ta mère");
                cptError++;
            }

        }
    }
        float pourcentage = (((float) ((nbTry) * 4 - cptError) /(nbTry*4))*100);
        System.out.println("CPT ERROR : " + cptError + "nombre d'essais :" + nbTry + "pourcentage : "+ pourcentage+"%");
        }

}
