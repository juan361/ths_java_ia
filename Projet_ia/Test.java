public class Test {
    public static void main(String[] args) {
        //Obtention du fichier audio
        Son son = new Son(
                "C:/Users/matau/OneDrive/Documents/Etude/prepa isen/3eme_annee/Projet_IA/dataset_projet_Java_IA_THS_2023/dataset_projet_Java_IA_THS_2023/oiseaux/Chouette_Effraie.wav");
        //Matrice pour les neurones
        int[] tableentry = { 18, 54, 35 ,62,74};
        int[] tableentry2 = { 94,102,112,121 };
        float[] tableresult = { 1, 1, 0,1,0};
        float[] tableresult2 = {1,1,0,0};
        int tailleBloc = 4096; // Taille de votre bloc
        float[][] tablmodule = new float[tableresult.length][tailleBloc];
        float[][] tablmodule2 = new float[tableresult.length][tailleBloc];

        for (int k = 0; k < tableresult.length; k++) {
            // Obtenez les données audio et appliquez la FFT
            // Créer un tableau de complexe pour le bloc actuel
            float[] donneesBloc = son.bloc_deTaille(tableentry[k], tailleBloc);
            Complexe[] cplx = new Complexe[tailleBloc];
            for (int i = 0; i < tailleBloc; i++) {
                cplx[i] = new ComplexeCartesien(donneesBloc[i], 0);
            }
            // Appliquer la FFT
            Complexe/* [] */[] fftResultat = new Complexe/* [nombreBlocs] */[tailleBloc];
            fftResultat = FFTCplx.appliqueSur(cplx);
            double/* [] */[] result = new double/* [nombreBlocs] */[tailleBloc];

            for (int j = 0; j < tailleBloc; j++) {
                result/* [i] */[j] = fftResultat/* [i] */[j].mod();
                //System.out.println(result/* [i] */[j]);
                tablmodule[k][j] = (float) result[j];
            }
        }

        for (int k = 0; k < tableresult2.length; k++) {
            // Obtenez les données audio et appliquez la FFT
            // Créer un tableau de complexe pour le bloc actuel
            float[] donneesBloc = son.bloc_deTaille(tableentry2[k], tailleBloc);
            Complexe[] cplx = new Complexe[tailleBloc];
            for (int i = 0; i < tailleBloc; i++) {
                cplx[i] = new ComplexeCartesien(donneesBloc[i], 0);
            }
            // Appliquer la FFT
            Complexe[] fftResultat = new Complexe[tailleBloc];
            fftResultat = FFTCplx.appliqueSur(cplx);    
            for (int j = 0; j < tailleBloc; j++) {
                tablmodule2[k][j] = (float)fftResultat[j].mod();
            }
        }


        final iNeurone n = new NeuroneSigmoide(tablmodule[0].length);
            System.out.println("Apprentissage…");
            System.out.println("Nombre de tours : " + n.apprentissage(tablmodule, tableresult));
            final Neurone vueNeurone = (Neurone) n;

            for (int i = 0; i < tableentry.length; ++i) {
                // Pour une entrée donnée
                final float[] entree = tablmodule[i];
                // On met à jour la sortie du neurone
                vueNeurone.metAJour(entree);
                // On affiche cette sortie
                System.out.println("Entree " + tableentry[i] + " : " + vueNeurone.sortie());
            }

            for (int i = 0; i < tableentry2.length; ++i) {
                // Pour une entrée donnée
                final float[] entree = tablmodule2[i];
                // On met à jour la sortie du neurone
                vueNeurone.metAJour(entree);
                // On affiche cette sortie
                System.out.println("Entree " + tableentry2[i] + " : " + vueNeurone.sortie());
            }
    }
}
