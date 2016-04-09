package fr.sgrassell418.elementary.model.card;

import fr.sgrassell418.elementary.model.GenericBuilder;
import fr.sgrassell418.elementary.model.type.Type;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stephane on 08/04/16.
 */
public class CardManager {

    private static CardManager INSTANCE = null;

    private CardManager(){};

    public static CardManager getINSTANCE(){
        if(INSTANCE == null)
            INSTANCE = new CardManager();
        return INSTANCE;
    }

    public List<Card> generateAll(){
        List<Card> allCards = new ArrayList<Card>();

        for (Type t : Type.allTypes){ //Pour chaque type possible (8 itérations)
            for(Integer w : t.getWeaknesses()){ // Pour chaque faiblesses possibles OUEST
                for(Integer x : t.getWeaknesses()){ // Pour chaque faiblesses possibles NORD
                    for(Integer y : t.getWeaknesses()){ // Pour chaque faiblesses possibles EST
                        for(Integer z : t.getWeaknesses()) { // Pour chaque faiblesses possibles SUD

                            // On arrive aussi pour chaque combinaison possible

                            Card c = new Card(); // Création d'une carte

                            c.setType(t); // On lui met le type en cours (première boucle for)
                            int[] weaks = {w, x, y, z}; // Création du tableau des 4 faiblesses en fonction des 4 boucles for O/N/E/S
                            c.setWeaknesses(weaks); // On attribue le tableau des faiblesses à la carte
                            c.setRarity(calculateRarity(c)); // On envoie le calcul de rareté dans la carte
                            c.setId(""+c.getType().getId()+c.getWeaknesses()[0]+c.getWeaknesses()[1]+c.getWeaknesses()[2]+c.getWeaknesses()[3]); // Id = idtype + les 4 faiblesses
                            allCards.add(c); // On ajoute la carte à la liste du return
                        }
                    }
                }
            }
        }

        // PRISE EN COMPTE DU TYPE NEUTRE

        for (int i=0;i<4;i++) { // Pour chaque carte, il existe 4 variantes neutres (avec le neutre en haut, en bas, à gauche, et à droite
            for (Type t : Type.allTypes){ // Pour chaque type
                for(Integer w : t.getWeaknesses()){ // Faiblesse 1
                    for(Integer x : t.getWeaknesses()){ // Faiblesse 2
                        for(Integer y : t.getWeaknesses()){ // Faiblesse 3
                            Card c = new Card();
                            c.setType(t);
                            if(i==0){ // A la première itération, on passe SUD en neutre
                                int[] weaks = {w, x, y, 8};
                                c.setWeaknesses(weaks);
                            }else if(i==1){ // A la seconde itération, on passe EST en neutre
                                int[] weaks = {w, x, 8, y};
                                c.setWeaknesses(weaks);
                            }else if(i==2){ // A la 3eme, on passe NORD en neutre
                                int[] weaks = {w, 8, x, y};
                                c.setWeaknesses(weaks);
                            }else if(i==3){ // A la 4eme, on passe OUEST en neutre
                                int[] weaks = {8, w, x, y};
                                c.setWeaknesses(weaks);
                            }
                            c.setRarity(calculateRarity(c));
                            c.setId(""+c.getType().getId()+c.getWeaknesses()[0]+c.getWeaknesses()[1]+c.getWeaknesses()[2]+c.getWeaknesses()[3]);
                            allCards.add(c);
                        }
                    }
                }
            }
        }
        return allCards;
    }

    public Card cardFromId(String id){
        char[] values = id.toCharArray();
        int[] weaks = {Character.getNumericValue(values[1]), Character.getNumericValue(values[2]),
                Character.getNumericValue(values[3]), Character.getNumericValue(values[4])};

        System.out.println((int)values[0]);

        Card c = new GenericBuilder<Card>(Card.class).with("id", id)
                .with("type", Type.allTypes.get(Character.getNumericValue(values[0])))
                .with("weaknesses", weaks).build();
        c.setRarity(calculateRarity(c));

        return c;
    }






    private static char calculateRarity(Card c){// CALCUL DE RARETÉ SELON CRITÈRES EMPIRIQUES
        int interest = 0; // Common < 10 Unco < 20 Rare
        int count = 0; //Similar elements count
        int[] weak = c.getWeaknesses();

        for(int w : weak){// Pour chaque faiblesse de la carte en paramètre

            for(int x : weak){ // Calcul des similitudes
                if (w == x)
                       count +=  1; //Si les deux faiblesses sont les mêmes, on ajoute 1 au compteur
            }
            count -= 1; // On retire -1 car la carte compte une similitude avec elle-même dans la boucle ci-dessus

            /* Count = 0 Si la carte possède 4 éléments différents(ABCD) <- Uniquement possible en cas de neutre
               Count = 2 Si la carte a deux éléments identiques et les deux autres différents (AABC)
               Count = 4 Si la carte a deux éléments identiques 2 à 2 (AABB ou ABAB)
               Count = 6 Si la carte a 3 éléments identiques et le dernier différent (AAAB)
               Count = 12 Si la carte a 4 éléments identiques
            */

            if(w==8) // Si élément neutre
                interest+=10;
            else if(w==4) // Si faiblesse à Terre (terre est l'élément le moins rentable)
                interest += 2;
            else if(w==3 || w == 5) //Si faiblesses air ou électricité (éléments ayant moins d'intéractions)
                interest += 2;
            else if(w==0 || w == 2) //Si faiblesse à feu ou à glace (élements les plus rentables)
                interest -= 2;
        }

        if(c.getType().getId() == 0 || c.getType().getId() == 2) //Si type feu ou glace (seuls types avec + de forces que de faiblesses)
            interest += 2;

        if (count>4)//Motif intéressant
            interest +=10;

        if(weak[0]==weak[1] && weak[1]==weak[2] && weak[2]== weak[3]) //4 identiques, j'aurais pu mettre if(count==12)
            interest += 20;


        if(interest>=20)
            return 'R';
        else if (interest >= 10)
            return 'U';
        else
            return 'C';
    }

}
