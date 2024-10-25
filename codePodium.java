import java.util.Scanner;
import java.util.Arrays;
//code réécris entièrement par Jérémy en reprenant les sous fonctions de saisies de Dimitri


/**
 * fonction main.
 *
 * @author (Gabriel (la partie disqualification -> Jérémy))
 *  les commentaires slash**slash sont fait par Gabriel
 *  les commentaires slash slash sont fait par Jérémy
 */
public class codePodium {

    public static String podium(int tabTempsParticipants[][], int nbCoureurs) {
        String resultat = "";                                 //variable qui retournera sous forme de texte les 3 meilleurs participant ainsi que leurs temps
        int i ;                                               //compteur de boucles 1
        int j ;                                               //compteur de boucles 2
        int[] tempsCroissant = new int[nbCoureurs];           //tableau qui triera les temps des participants dans l'ordre croissant
        int[][] meilleursTemps = new int[2][3];               //tableau qui contiendra les 3 premiers participants
        // avec leurs numero de brassard et temps
        boolean[] dejaDansTab = new boolean[nbCoureurs];

        for (i = 0; i < nbCoureurs; i++) {                                   
            tempsCroissant[i] = tabTempsParticipants[1][i];
        }

        Arrays.sort(tempsCroissant);                            //on trie le tableau tempsCroissant pour quíl affiche les
        //temps dans l'ordre croissant
        for (i = 0; i < 3; i++) {                               
            for(j = 0; j < nbCoureurs; j++) {
                if (tempsCroissant[i] == tabTempsParticipants[1][j] && !dejaDansTab[j]) {       //compare le temps des participants avec le temps trié pour retrouver l'index puis compare l'index pour voir si il a pas déjà été utilisé et ignore les temps = à 0s
                    meilleursTemps[0][i] = tabTempsParticipants[0][j];                          //ajoute l'index du participant dans le tableau des 3 meilleurs participants
                    meilleursTemps[1][i] = tabTempsParticipants[1][j];                          //ajoute le temps du participant dans le tableau des 3 meilleurs participants
                    dejaDansTab[j] = true;                                                      //ajoute l'index du participant pour pas qu'il y figure plusieurs fois dans la liste des 3 meilleurs temps si il y a égalité avec dm autres participants
                    break;                                                                      //finir la boucle quand on a trouvé la correspondance avec tabTempsParticipants et tempsCroissant
                }
            }         
        }

        // un temps >= 999999999 est forcément un participant éliminé
        
        if (meilleursTemps[1][0] >= 999999999 && meilleursTemps[1][1] >= 999999999 && meilleursTemps[1][2] >= 999999999) { //verifie si personne n'a reussi
            resultat = "Erreur tout le monde a raté"; 
            return resultat;
        }

        if (meilleursTemps[1][1] >= 999999999 && meilleursTemps[1][2] >= 999999999) {              //verifie si 1 personne a reussi
            resultat = "1re place participant numéro : " + meilleursTemps[0][0] + "\n";            //affiche le numéro du premier participant
            resultat += "Avec un temps de : " + meilleursTemps[1][0] + "ms \n";                    //affiche le temps du premier participant
            resultat += "Pas de 2eme participant, praticipants éliminés \n"; 
            resultat += "Pas de 3eme participant, praticipants éliminés"; 
            return resultat;
        }

        if (meilleursTemps[1][2] >= 999999999) {                                                    //verifie si 2 personne ont reussi
            resultat = "1re place participant numéro : " + meilleursTemps[0][0] + "\n";             //affiche le numéro du premier participant
            resultat += "Avec un temps de : " + meilleursTemps[1][0] + "ms \n";                     //affiche le temps du premier participant
            resultat += "2eme place participant numéro : " + meilleursTemps[0][1] + "\n";           //affiche le numéro du deuxième participant
            resultat += "Avec un temps de : " + meilleursTemps[1][1] + "ms \n";                     //affiche le temps du deuxième participant
            resultat += "Pas de 3eme participant, praticipants éliminés";
            return resultat;
        }

        resultat = "1re place participant numéro : " + meilleursTemps[0][0] + "\n";            //affiche le numéro du premier participant
        resultat += "Avec un temps de : " + meilleursTemps[1][0] + "ms \n";                    //affiche le temps du premier participant

        if (meilleursTemps[1][0] == meilleursTemps[1][1]) {                                    //verifie si les deux premiers temps du tableau meilleursTemps sont égaux
            resultat += "1re place participant numéro: " + meilleursTemps[0][1] + "\n";        //met le participant à la première place avec l'autre premier participant si ils ont le même temps
            resultat += "Avec un temps de : " + meilleursTemps[1][1] + "ms \n";                //affiche le temps du premier participant
        } else {
            resultat += "2eme place participant numéro : " + meilleursTemps[0][1] + "\n";       //affiche le numéro du deuxième participant
            resultat += "Avec un temps de : " + meilleursTemps[1][1] + "ms \n";                 //affiche le temps du deuxième participant
        }

        if (meilleursTemps[1][1] == meilleursTemps[1][2]) {                                     //verifie si les deux derniers temps du tableau meilleursTemps sont égaux
            resultat += "2eme place participant numéro: " + meilleursTemps[0][2] + "\n";        //met le participant à la deuxième place avec l'autre deuxième participant si ils ont le même temps
            resultat += "Avec un temps de : " + meilleursTemps[1][2] + "ms \n";                 //affiche le temps du premier participant
        } else {
            resultat += "3eme place participant numéro : " + meilleursTemps[0][2] + "\n";       //affiche le numéro du troisième participant
            resultat += "Avec un temps de : " + meilleursTemps[1][2] + "ms \n";                 //affiche le temps du troisième participant
        }

        return resultat;                    //retourne les 3 meilleurs participants et leur temps
    }
}