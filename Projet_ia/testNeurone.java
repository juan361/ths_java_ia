
public class testNeurone
{
	public static void main(String[] args)
	{
		// Tableau des entrées de la fonction ET (0 = faux, 1 = vrai)
		final float[][] entrees = {{0,0}, {0,1}, {1,0}, {1,1}};
		final float[][] entrees2 = {{0.1f, 0.1f}, {0.1f, 0.9f}, {0.9f, 0.1f}, {0.9f, 0.9f}};
		
		// Tableau des sorties de la fonction ET
		final float[] resultats = {0, 1, 1, 1};
		
		// On crée un neurone taillé pour apprendre la fonction ET
		//final iNeurone n = new NeuroneHeavyside(entrees[0].length);
		final iNeurone n = new NeuroneSigmoide(entrees[0].length);
		//final iNeurone n = new NeuroneReLU(entrees[0].length);
		
		System.out.println("Apprentissage…");
		// On lance l'apprentissage de la fonction ET sur ce neurone
		System.out.println("Nombre de tours : "+n.apprentissage(entrees, resultats));
		
		// On affiche les valeurs des synapses et du biais

		// Conversion dynamique d'une référence iNeurone vers une référence neurone.
		// Sans cette conversion on ne peut pas accéder à synapses() et biais()
		// à partir de la référence de type iNeurone
		// Cette conversion peut échouer si l'objet derrière la référence iNeurone
		// n'est pas de type neurone, ce qui n'est cependant pas le cas ici
		final Neurone vueNeurone = (Neurone)n;
		System.out.print("Synapses : ");
		for (final float f : vueNeurone.synapses())
			System.out.print(f+" ");
		System.out.print("\nBiais : ");
		System.out.println(vueNeurone.biais());
		
		// On affiche chaque cas appris
		for (int i = 0; i < entrees.length; ++i)
		{
			// Pour une entrée donnée
			final float[] entree = entrees[i];
			// On met à jour la sortie du neurone
			n.metAJour(entree);
			// On affiche cette sortie
			System.out.println("Entree "+i+" : "+n.sortie());
		}
		for (int i = 0; i < entrees2.length; ++i)
		{
			final float[] entree = entrees2[i];
			n.metAJour(entree);
			System.out.println("Entree2 "+i+" : "+n.sortie());
		}
	}
}
