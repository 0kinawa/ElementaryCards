package fr.sgrassell418.elementary.model.type;

/**
 * Created by stephane on 09/04/16.
 */
public interface IType {

    int getId();
    void setId(int i);

    String getName();
    void setName(String s);

    int[] getWeaknesses();
    void setWeaknesses(int[] w);
}
