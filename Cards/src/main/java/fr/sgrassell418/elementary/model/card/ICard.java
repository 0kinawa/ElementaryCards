package fr.sgrassell418.elementary.model.card;

import fr.sgrassell418.elementary.model.type.Type;

/**
 * Created by stephane on 09/04/16.
 */
public interface ICard {
    String getId();
    void setId(String s);

    Type getType();
    void setType(Type t);

    int[] getWeaknesses();
    void setWeaknesses(int[] w);

    char getRarity();
    void setRarity(char c);
}
