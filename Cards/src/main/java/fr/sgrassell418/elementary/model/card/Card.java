package fr.sgrassell418.elementary.model.card;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import fr.sgrassell418.elementary.model.type.Type;

/**
 * Created by stephane on 08/04/16.
 */
public class Card implements ICard {

    private String id;
    private Type type;
    private int[] weaknesses;
    private char rarity;


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

    public void setType(Type type) { this.type = type; }

    public int[] getWeaknesses() {
        return weaknesses;
    }

    public void setWeaknesses(int[] weaknesses) {
        this.weaknesses = weaknesses;
    }

    public char getRarity() {
        return rarity;
    }

    public void setRarity(char rarity) {
        this.rarity = rarity;
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
