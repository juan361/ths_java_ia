public class Test2 {
    private static final int tailleBloc = 4096;

    public static void main(String[] args) {
        Son son = new Son("C:/Users/matau/OneDrive/Documents/Etude/prepa isen/3eme_annee/Projet_IA/dataset_projet_Java_IA_THS_2023/dataset_projet_Java_IA_THS_2023/oiseaux/Chouette_Effraie.wav");
        int[] tableentry = {  18, 54, 35 ,62,74 };
        float[] tableresult = { 1, 1, 0,1,0};
        float[][] tablmodule = new float[tableresult.length][tailleBloc];

        for (int k = 0; k < tableresult.length; k++) {
            processBloc(son, tableentry[k], tablmodule, k);
        }

        processNeurone(tablmodule, tableresult);
    }

    private static void processBloc(Son son, int entry, float[][] tablmodule, int index) {
        float[] donneesBloc = son.bloc_deTaille(entry, tailleBloc);
        Complexe[] cplx = new Complexe[tailleBloc];
        createComplexeArray(cplx, donneesBloc);
        Complexe[] fftResultat = FFTCplx.appliqueSur(cplx);
        calculateFFTModule(tablmodule, fftResultat, index);
    }

    private static void createComplexeArray(Complexe[] cplx, float[] donneesBloc) {
        for (int i = 0; i < tailleBloc; i++) {
            cplx[i] = new ComplexeCartesien(donneesBloc[i], 0);
        }
    }

    private static void calculateFFTModule(float[][] tablmodule, Complexe[] fftResultat, int index) {
        double[] result = new double[tailleBloc];
        for (int j = 0; j < tailleBloc; j++) {
            result[j] = fftResultat[j].mod();
            tablmodule[index][j] = (float) result[j];
        }
    }

    private static void processNeurone(float[][] tablmodule, float[] tableresult) {
        final iNeurone n = new NeuroneHeavyside(tablmodule[0].length);
        System.out.println("Nombre de tours : " + n.apprentissage(tablmodule, tableresult));
        final Neurone vueNeurone = (Neurone) n;
        printSynapses(vueNeurone);
        updateNeurone(tablmodule, n, vueNeurone);
    }

    private static void printSynapses(Neurone vueNeurone) {
        System.out.print("Synapses : ");
        for (final float f : vueNeurone.synapses())
            System.out.print(f + " ");
        System.out.println("\nBiais : " + vueNeurone.biais());
    }

    private static void updateNeurone(float[][] tablmodule, iNeurone n, Neurone vueNeurone) {
        for (int i = 0; i < tablmodule.length; ++i) {
            final float[] entree = tablmodule[i];
            vueNeurone.metAJour(entree);
            System.out.println("Entree " + i + " : " + n.sortie());
        }
    }
}