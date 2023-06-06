public class Test {
    public static void main(String[] args) {
        Son son = new Son(
                "C:/Users/matau/OneDrive/Documents/Etude/prepa isen/3eme_annee/Projet_IA/Sample/Chouette_Effraie_1.wav");

        int a = 1;
        for (int i = 1; i * 4096 < son.donnees().length; i++) {
            System.out.println("Bloc " + i + " : " + son.bloc_deTaille(i, 4096).length + " echantillons a "
                    + son.frequence() + "Hz");
            a = i;
        }
        System.out.println(a);
        // FFTCplx sonFFT = new FFTCplx(a);
        Complexe[][] signalTot = new Complexe[a][];
        for (int i = 0; i < a; ++i)
        {
            for (int j = 0; j < i*4096; ++j)
            {
                signalTot[i][j] = new ComplexeCartesien(son.donnees()[j], 0);
            }
            Complexe[] resultat = FFTCplx.appliqueSur(signalTot[i]);
        }
        for (int i = 0; i < resultat.length; ++i) {
            System.out.printf("%3d : (%4.2f ; %4.2fi)", i, (float) resultat[i].reel(), (float) resultat[i].imag());
            System.out.printf(", (%4.2f ; %4.2f rad)\n", (float) resultat[i].mod(), (float) resultat[i].arg());
        }

    }
}
