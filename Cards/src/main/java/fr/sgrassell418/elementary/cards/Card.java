package fr.sgrassell418.elementary.cards;


import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by stephane on 08/04/16.
 */
public class Card {
    private String id;

    @JsonIgnore
    private Type cType;

    public int type;

    private char rarity;

    private int[] weaknesses;

    public Card(){};

    public Card(String id, Type cType, char rarity, int[] weaknesses) {
        this.id = id;
        this.cType = cType;
        this.type = this.cType.getId();
        this.rarity = rarity;
        this.weaknesses = weaknesses;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Type getCType() {
        return cType;
    }

    public void setCType(Type cType) {
        this.cType = cType;
        this.type = this.cType.getId();
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
        String sCard = "Card #"+id+" ; Type "+ cType.getName().toLowerCase()+" ; weaknesses : ";
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
