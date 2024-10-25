//ancien code qui marche pas
import java.util.Arrays;
import java.util.Scanner;
/**
 * Indiquer le ou les numeros de TP et d'exercice.
 *
 * @author (votre nom)
 */
public class SAE_Groupe_3B2 {

    public static void main(String[] args) {
        int [][] round1, round2, rFinal;
        int portes, coureurs;

        coureurs = saisiePart();
        portes = saisiePortes();

        System.out.println("PREMIERE MANCHE:");          /*On forme un 1er tableau pour les 1ers passage*/
        round1 = tabTemps(coureurs);

        System.out.println("SECONDE MANCHE:");           /*Puis un second pour le round 2*/
        round2 = tabTemps(coureurs);

        System.out.println("MOYENNE DES DEUX MANCHES:"); /*On fait la moyenne de chaque passage*/
        rFinal = moy(coureurs, round1, round2);
        System.out.println(Arrays.deepToString(rFinal));

        System.out.println("___________");
        System.out.println(podium(rFinal, coureurs));
    }

/**
 * Sous fonction qui affiche les 3 meilleurs participants avec leurs temps
 *
 *      @param tabTempsParticipants[][] tableau en deux dimensions contenant le numéro de brassard des participants anisi que leurs temps
 *      @param nbCoureurs nombre de participants
 *      @return String contenant le podium avec les places des 3 meilleurs participants et leurs temps
 * 
 * @author (Jérémy)
 */
    public static String podium(int tabTempsParticipants[][], int nbCoureurs) {
        String resultat = "";               //variable qui retournera sous forme de texte les 3 meilleurs participant ainsi que leurs temps
        int i ;                             //compteur de boucles 1
        int j ;                             //compteur de boucles 2
        int[] tempsCroissant = new int[nbCoureurs];           //tableau qui triera les temps des participants dans l'ordre croissant
        int[][] meilleursTemps = new int[2][3];               //tableau qui contiendra les 3 premiers participants
                                                              // avec leurs numero de brassard et temps
        boolean[] dejaDansTab = new boolean[nbCoureurs];

        for (i = 0; i < nbCoureurs; i++) {                                   
            if (tabTempsParticipants[1][i] == 0) {                        //on met tous les temps du tableau tabTempsParticipants
                tempsCroissant[i] = 999999999;                            //dans tempsCroissant
            } else {
                tempsCroissant[i] = tabTempsParticipants[1][i];
            }
        }

        Arrays.sort(tempsCroissant);                            //on trie le tableau tempsCroissant pour quíl affiche les
                                                                //temps dans l'ordre croissant
        for (i = 0; i < 3; i++) {                               
            for(j = 0; j < 5; j++) {
                if (tempsCroissant[i] == tabTempsParticipants[1][j] && !dejaDansTab[j]) {       //compare le temps des participants avec le temps trié pour retrouver l'index puis compare l'index pour voir si il a pas déjà été utilisé et ignore les temps = à 0s
                    meilleursTemps[0][i] = tabTempsParticipants[0][j];                          //ajoute l'index du participant dans le tableau des 3 meilleurs participants
                    meilleursTemps[1][i] = tabTempsParticipants[1][j];                          //ajoute le temps du participant dans le tableau des 3 meilleurs participants
                    dejaDansTab[j] = true;                                                      //ajoute l'index du participant pour pas qu'il y figure plusieurs fois dans la liste des 3 meilleurs temps si il y a égalité avec dm autres participants
                    break;                                                                      //finir la boucle quand on a trouvé la correspondance avec tabTempsParticipants et tempsCroissant
                }
            }         
        }

        if (meilleursTemps[0][0] == 0 && meilleursTemps[0][1] == 0 && meilleursTemps[0][2] == 0) { //verifie si personne n'a reussi
            resultat = "Erreur tout le monde a raté"; 
            return resultat;
        }

        if (meilleursTemps[0][1] == 0 && meilleursTemps[0][2] == 0) {                              //verifie si 1 personne a reussi
            resultat = "1re place participant numéro : " + meilleursTemps[0][0] + "\n";            //affiche le numéro du premier participant
            resultat += "Avec un temps de : " + meilleursTemps[1][0] + "ms \n";                     //affiche le temps du premier participant
            resultat += "Pas de 2eme participant, praticipants éliminés \n"; 
            resultat += "Pas de 3eme participant, praticipants éliminés"; 
            return resultat;
        }

        if (meilleursTemps[0][2] == 0) {                                                           //verifie si 2 personne ont reussi
            resultat = "1re place participant numéro : " + meilleursTemps[0][0] + "\n";            //affiche le numéro du premier participant
            resultat += "Avec un temps de : " + meilleursTemps[1][0] + "ms \n";                     //affiche le temps du premier participant
            resultat += "2eme place participant numéro : " + meilleursTemps[0][1] + "\n";          //affiche le numéro du deuxième participant
            resultat += "Avec un temps de : " + meilleursTemps[1][1] + "ms \n";                     //affiche le temps du deuxième participant
            resultat += "Pas de 3eme participant, praticipants éliminés";
            return resultat;
        }

        resultat = "1re place participant numéro : " + meilleursTemps[0][0] + "\n";            //affiche le numéro du premier participant
        resultat += "Avec un temps de : " + meilleursTemps[1][0] + "ms \n";                     //affiche le temps du premier participant

        if (meilleursTemps[1][0] == meilleursTemps[1][1]) {                                    //verifie si les deux premiers temps du tableau meilleursTemps sont égaux
            resultat += "1re place participant numéro: " + meilleursTemps[0][1] + "\n";        //met le participant à la première place avec l'autre premier participant si ils ont le même temps
            resultat += "Avec un temps de : " + meilleursTemps[1][1] + "ms \n";                 //affiche le temps du premier participant
        } else {
            resultat += "2eme place participant numéro : " + meilleursTemps[0][1] + "\n";      //affiche le numéro du deuxième participant
            resultat += "Avec un temps de : " + meilleursTemps[1][1] + "ms \n";                 //affiche le temps du deuxième participant
        }

        if (meilleursTemps[1][1] == meilleursTemps[1][2]) {                                     //verifie si les deux derniers temps du tableau meilleursTemps sont égaux
            resultat += "2eme place participant numéro: " + meilleursTemps[0][2] + "\n";        //met le participant à la deuxième place avec l'autre deuxième participant si ils ont le même temps
            resultat += "Avec un temps de : " + meilleursTemps[1][2] + "ms \n";                  //affiche le temps du premier participant
        } else {
            resultat += "3eme place participant numéro : " + meilleursTemps[0][2] + "\n";      //affiche le numéro du troisième participant
            resultat += "Avec un temps de : " + meilleursTemps[1][2] + "ms \n";                 //affiche le temps du troisième participant
        }
        

        return resultat;                    //retourne les 3 meilleurs participants et leur temps
    }


/*
 * Fonction dont le role et de former les
 * tableaux pour chaque round
 */
    public static int[][] tabTemps(int nbCoureurs) {
        Scanner clavier = new Scanner(System.in);
        int[][] tabTemps = new int[2][nbCoureurs];
        int temps, touche, rate, brassard;
        for(int i = 0; i < nbCoureurs; i++) { 
            brassard = i +1;
            System.out.println("Coureur n°" + brassard + ":");
            tabTemps[0][i] = brassard;
            tabTemps[1][i] = tempsCourse();                      /*On obtient le temps/score que l'on range dans le tableau via une autre sous fonction*/
        }
        return tabTemps;
    }
/*
 * On forme le tableau qui enregistre
 * la moyenne de score des 2 rounds
 */
    public static int[][] moy(int coureurs, int [][] round1,int [][] round2) {
        int[][] moyenne = new int[2][coureurs];
        int brassard;
        for (int i = 0; i < coureurs; i++) {
            brassard = i + 1;
            moyenne[0][i] = brassard;
            if ((round1[1][i] == 0)&&(round2[1][i] != 0)) {          /*Si un des passages est invalide, la moyenne correspondera au passage valide*/
                moyenne[1][0] = round2[1][i];
            }
            else if ((round2[1][i] == 0)&&(round1[1][i] != 0)) {
                moyenne[1][i] = round1[1][i];
            }
            else {                                                   /*Si les 2 round sont valides, on calcul la moyenne. Si les deux sont invalides, la moyenne reste à 0 (considérée invalide)*/
                moyenne[1][i] = (round1[1][i] + round2[1][i])/2;
            }
        }
        return moyenne;
    }
/*
 * Cette sous fonction permet à l'utilisateur de rentrer les informations
 * de chaque passage et il renvoie un score en fonction de celles ci.
 */
    public static int tempsCourse(){
        Scanner clavier = new Scanner(System.in);
        int temps, touche, rate, points;
        System.out.println("Le passage est-il valide ? (saisissez 1 pour oui et 0 pour non)");
        int valide = clavier.nextInt();
        int millisec = 0;
        while((valide != 1)&&(valide != 0)){
            System.out.println("Les valeurs disponibles sont 1 pour oui et 0 pour nom, resaisissez");
            valide = clavier.nextInt();                    
        }
        if (valide == 0){                           /*Si le passage est invalide, on renvoie un score de 0.*/
            System.out.println("Passage invalide.");
            millisec = 0;                
        }
        else {                                      /*Si le passage est valide, on calcul de score en fonction du temps et des fautes qui aurraient pu être commises.*/
            System.out.println("Saisissez le temps de la descente en milliseconde:");
            temps = clavier.nextInt();
            System.out.println("Saisissez le nombre de portes touchées:");
            touche = clavier.nextInt();
            System.out.println("Saisissez le nombre de portes ratées:");
            rate = clavier.nextInt();
            points = (touche*2) + (rate*50);
            millisec = temps + 1000 * points;
            System.out.println("le participant a un temps de " + millisec + " millisecondes avec " + points + " points de pénalité.");
        }   
        return millisec;
    }
/*
 * Fonction qui a pour rôle de faire saisir et de renvoyer le nombre de coureurs.
 */
    public static int saisiePart(){
        System.out.println("Saisissez le nombre de participants (1-50):");

        Scanner clavier = new Scanner(System.in);
        int nbPart = clavier.nextInt();
        while ((nbPart >50) || (nbPart < 1)){
            System.out.println("Le nombre de coureurs est invalide, il doit être entre 1 et 50 inclus." + "\n" + "Réessayez:");
            nbPart = clavier.nextInt();
        }
        return nbPart;
    }
/*
 * Fonction qui fait entrer par l'utilisateur le nombre de portes et le renvoie afin que le parcours soit valide.
 */
    public static int saisiePortes(){
        System.out.println("Saisir le nombre de portes (entre 18 et 22):");
        Scanner clavier = new Scanner(System.in);
        int portes = clavier.nextInt();
        while ((portes < 18)||(portes > 22)){
            System.out.println("Le nombre saisi n'est pas compris entre 18 et 22, réessayez.");
            portes = clavier.nextInt();
        }
        return portes;
    }
}

