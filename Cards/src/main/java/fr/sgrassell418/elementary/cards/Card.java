package fr.sgrassell418.elementary.cards;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * Created by stephane on 08/04/16.
 */
public class Card {
    private String id;

    private Type type;

    private char rarity;

    private int[] weaknesses;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public char getRarity() {
        return rarity;
    }

    public void setRarity(char rarity) {
        this.rarity = rarity;
    }

    public int[] getWeaknesses() {
        return weaknesses;
    }

    public void setWeaknesses(int[] weaknesses) {
        this.weaknesses = weaknesses;
    }

    @Override
    public String toString() {
        String sCard = "Card #"+id+" ; Type "+ type.getName().toLowerCase()+" ; weaknesses : ";
        for(Integer w : weaknesses){
            if(w != 8)
                sCard += Type.allTypes.get(w).getName()+"/";
            else
                sCard+="Neutral/";
        }
        sCard = sCard.substring(0, sCard.length()-1);

        sCard += " ; Rarity : "+rarity;


        return sCard;
    }
}
