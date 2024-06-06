public class NeuroneSigmoide extends Neurone{
    public NeuroneSigmoide(int nbEntrees) {
        super(nbEntrees);
    }

    @Override
    protected float activation(float valeur) {
        return (float) (1 / (1 + Math.exp(-valeur)));
    }
}
