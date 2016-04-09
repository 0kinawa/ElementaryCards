package fr.sgrassell418.elementary.model;

import fr.sgrassell418.elementary.model.card.Card;
import fr.sgrassell418.elementary.model.card.CardManager;
import fr.sgrassell418.elementary.model.type.Type;

public class App 
{
    public static void main( String[] args )
    {

        Type.initAllTypes();

        System.out.println(Type.allTypes.get(1));

        Card c = CardManager.getINSTANCE().cardFromId("15558");
        System.out.println(c);

//        List<Card> allCards = CardManager.getINSTANCE().generateAll();
//
//        for(Type t : Type.allTypes){
//            List<Card> typeCards = new ArrayList<>();
//            for(Card c : allCards){
//                if(c.getType().getId() == t.getId())
//                    typeCards.add(c);
//            }
//            try {
//                System.out.println(new ObjectMapper().writeValueAsString(typeCards).replaceAll("},", "},\n"));
//                System.out.println("\n\n");
//            } catch (JsonProcessingException e) {
//                e.printStackTrace();
//            }
//        }




//        try {
//            System.out.println(new ObjectMapper().writeValueAsString(allCards).replaceAll("},", "},\n"));
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
    }
}
