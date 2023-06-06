
public class NeuroneSigmoide extends Neurone{
    // Fonction d'activation d'un neurone (peut facilement être modifiée par héritage)
	protected float activation(final float valeur) {return (float) (1d/(1d + Math.exp(-valeur)));}
	
	// Constructeur
	public NeuroneSigmoide(final int nbEntrees) {super(nbEntrees);}
}
