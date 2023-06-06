public class Test {
    public static void main(String[] args) {
        Son son = new Son(
                "C:/Users/matau/OneDrive/Documents/Etude/prepa isen/3eme_annee/Projet_IA/dataset_projet_Java_IA_THS_2023/dataset_projet_Java_IA_THS_2023/oiseaux/Chouette_Effraie.wav");
        int[] tableentry = { 55125, 33075, 121275, 165375 };
        float[] tableresult = { 1, 1, 0, 0 };
        float[] donnees = son.donnees();
        int tailleBloc = 4096; // Taille de votre bloc
        float tablmodule[][] = new float[tableresult.length][tailleBloc];

        for (int k = 0; k < tableresult.length; k++) {
            // Obtenez les données audio et appliquez la FFT

            // int nombreBlocs = donnees.length / tailleBloc;
            Complexe/* [] */[] fftResultat = new Complexe/* [nombreBlocs] */[tailleBloc];
            // Créer un tableau de complexe pour le bloc actuel
            // System.out.println(son.donnees().length);
            float[] donneesBloc = son.bloc_deTaille(tableentry[k], tailleBloc);
            Complexe[] cplx = new Complexe[tailleBloc];
            for (int i = 0; i < tailleBloc; i++) {
                cplx[i] = new ComplexeCartesien(donneesBloc[i], 0);
            }
            // Appliquer la FFT
            fftResultat = FFTCplx.appliqueSur(cplx);
            double/* [] */[] result = new double/* [nombreBlocs] */[tailleBloc];

            /*
             * for (int i=0;i<nombreBlocs;i++)
             * {
             */
            for (int j = 0; j < tailleBloc; j++) {
                result/* [i] */[j] = fftResultat/* [i] */[j].mod();
                System.out.println(result/* [i] */[j]);
                tablmodule[k][j] = (float) result[j];
            }
            final iNeurone n = new NeuroneHeavyside(tablmodule[0].length);
            System.out.println("Nombre de tours : " + n.apprentissage(tablmodule, tableresult));
            final Neurone vueNeurone = (Neurone) n;
            System.out.print("Synapses : ");
           // for (final float f : vueNeurone.synapses())
               // System.out.print(f + " ");
            //System.out.print("\nBiais : ");
           // System.out.println(vueNeurone.biais());
            // On affiche chaque cas appris
            for (int i = 0; i < tablmodule.length; ++i) {
                // Pour une entrée donnée
                final float[] entree = tablmodule[i];
                // On met à jour la sortie du neurone
                vueNeurone.metAJour(entree);
                // On affiche cette sortie
                System.out.println("Entree " + i + " : " + n.sortie());
            }
            // }
        }
        //
    }
}
