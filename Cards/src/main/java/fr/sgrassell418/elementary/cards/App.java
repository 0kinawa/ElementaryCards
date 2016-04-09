package fr.sgrassell418.elementary.cards;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class App 
{
    public static void main( String[] args )
    {

        Type.initAllTypes();

        List<Card> allCards = CardManager.generateAll();

        for(Type t : Type.allTypes){
            List<Card> typeCards = new ArrayList<>();
            for(Card c : allCards){
                if(c.type == t.getId())
                    typeCards.add(c);
            }
            try {
                System.out.println(new ObjectMapper().writeValueAsString(typeCards).replaceAll("},", "},\n"));
                System.out.println("\n\n");
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }




//        try {
//            System.out.println(new ObjectMapper().writeValueAsString(allCards).replaceAll("},", "},\n"));
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
    }
}
