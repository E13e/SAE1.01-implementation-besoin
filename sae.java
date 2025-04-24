import java.util.Scanner;
import java.util.Arrays;


/**
 * fonction main.
 *
 * @author (Gabriel (la partie disqualification -> Jérémy))
 *  les commentaires slash**slash sont fait par Gabriel
 *  les commentaires slash slash sont fait par Jérémy
 */
public class sae {

    public static void main (String[] args) {
        int i ;                                                     //compteur de boucles
        int nbCoureurs = saisiePart();                              //nombre de coureurs
        int nbPortes = saisiePortes();                              //nombre de portes
        int[][] tabTempsParticipants = new int[2][nbCoureurs];      //tableau qui conntiendra les numéros de brassard des participants ainsi que leurs temps cumulés

        for (i = 0; i < nbCoureurs; i++) {                          /*On forme un 1er tableau pour les 1ers passage*/
            tabTempsParticipants[0][i] = i + 1;
            System.out.print("Participant numéro : " + (i + 1) + " ");
            tabTempsParticipants[1][i] = tempsCourse();
        }

        i = 0;

        for (i = 0; i < nbCoureurs; i++) {                                      /*Puis un second pour le round 2*/
            if (tabTempsParticipants[1][i] != 999999999) {                      //on ne prendra en compte que les participants qui ne sont pas éliminés
                tabTempsParticipants[0][i] = i + 1;
                System.out.print("Participant numéro : " + (i + 1) + " ");
                tabTempsParticipants[1][i] += tempsCourse();                    /*On fait le cumul de chaque passage*/
            } else {
                tabTempsParticipants[1][i] = tabTempsParticipants[1][i];
            }
        }

        System.out.print("Temps cumulé des participants : ");                 /*Affichage des temps de chaques participants(un temps >= à 99999999 est forcément un participant éliminé)*/
        System.out.println(Arrays.deepToString(tabTempsParticipants));
        System.out.println("___________");
        System.out.println(podium(tabTempsParticipants, nbCoureurs));
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

    /**
     * Sous fonction qui permet la saisie des portes pour savoir combien il y en as
     *  
     * 
     * @author (Dimitri)
     */
    public static int saisiePortes(){
        System.out.println("saisir le nombre de portes entre 18 et 22");
        Scanner clavier = new Scanner(System.in);
        //initialisation de la saisie
        int portes = clavier.nextInt();
        //le nombre de portes étant un entier, ,on va faire une saisie de type entier.
        while ((portes < 18)||(portes > 22)){
            System.out.println("le nombre saisi n'est pas compris entre 18 et 22, saisissez en un autre.");
            portes = clavier.nextInt();
        }
        //boucle tant que qui permet de vérifier si les conditions de saisie sont bien vérifiées.
        return portes;
    }

    /**
     * Sous fonction qui permet la saisie des participant pour savoir combien il y en as
     *  
     * 
     * @author (Dimitri)
     */
    public static int saisiePart(){
        System.out.println("saisir le nombre de participants");
        Scanner clavier = new Scanner(System.in);
        //initialisation de la saisie
        int nbPart = clavier.nextInt();
        //comme pour saisiePortes, le nombre de participants est un entier

        while ((nbPart >50) || (nbPart < 1)){
            System.out.println("le nombre de participants dois être inférieur à 50 et supérieur à 0, ressaisissez");
            nbPart = clavier.nextInt();
        }
        //boucle tant que qui a le même rôle que dans saisiePortes
        return nbPart;
    }

    /**
     * Sous fonction qui permet la saisie pour savoir si une manche est valide ou pas puis 
     * inndiquer les temps, les portes touchées et/ou ratée 
     * @return valeur du temps total avec les pénalité ou valeur max pour permettre d'éliminer un participant
     * 
     * @author (Dimitri)
     */
    public static int tempsCourse(){
        System.out.println("la manche est elle valide ? (saisissez 1 pour oui et 0 pour non)");
        int temps, touche, rate, points; 
        Scanner clavier = new Scanner(System.in);
        //initialisation de la saisie
        int valide = clavier.nextInt();
        int millisec = 0;
        while((valide != 1)&&(valide != 0)){
            System.out.println("les valeurs disponibles sont 1 pour oui et 0 pour nom, ressaisissez");
            valide = clavier.nextInt();                    
        }
        //ici, on crée deux cas de figure pour savoir si la manche est validée ou non. Si le nombre est différent de 0 ou 1 on fait ressaisir valide.
        if (valide == 0){
            System.out.println("l'athlète a raté sa manche");
            millisec = 999999999;                           //on retourne la plus grande valeur possible (pour permettre de savoir qui est éliminé notamment dans le tri pour le podium)
        }else{
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
        //Si valide = 0, la manche est ratée et on d'arrête là.
        //Si valide = 0, la manche est réussie et on saisis le temps, le nombre de portes touchées et ratées du participant. Puis on comvertit tout en m
        return millisec;
    }
}
