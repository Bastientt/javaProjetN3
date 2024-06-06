public class NeuroneRelu extends Neurone
{
    public NeuroneRelu(int nbEntrees) {
        super(nbEntrees);
    }

    @Override
    protected float activation(final float valeur) {
        if (valeur <= 0){
            return 0.f;
        }
        else{
            return valeur*1.f;
        }
    }
}
