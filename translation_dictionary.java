/**
 *           Translation_Dictionary_SHELL 
 * 
 * 
 * Translation_Dictionary_SHELL: 
 * A dictionary can be represented as a map in which each word is associated 
 * with a set of all its translations into another language.  For example, 
 * in an English-Spanish dictionary, holiday might be associated with
 * {fiesta, vacaciones}; in a Spanish-English dictionary, fiesta might 
 * be associated with {holiday, party, celebration, feast}.  In a dictionary 
 * map, a word is a key and a set of its translations is a “value” associated
 * with that key.  Suppose the dictionary is implemented as a TreeMap and a
 * set of translations for each word implemented as a TreeSet.
 * 
 * OutPut:
 *  -------------------------------------------------------------------- 
English -> Spanish:
beautiful: bello, bonito, hermoso, 
celeberation: celebracion, fiesta, 
feast: fiesta, 
holiday: fiesta, vaccaciones, 
nice: agradable, ameno, bonito, simpatico, 
party: fiesta, partido, velada, 
pretty: bonito, lindo, 

-------------------------------------------------------------------- 
Spanish -> English
agradable: nice, 
ameno: nice, 
bello: beautiful, 
bonito: beautiful, nice, pretty, 
celebracion: celeberation, 
fiesta: celeberation, feast, holiday, party, 
hermoso: beautiful, 
lindo: pretty, 
partido: party, 
simpatico: nice, 
vaccaciones: holiday, 
velada: party, 
 * 
 */

 import java.util.*;

 public class translation_dictionary {
     static Set<String> values = new TreeSet();
     static Set<String> newKey = new TreeSet();
     
     public static void main(String[] args) {
         Map<String, Set> dictionary = new TreeMap<String, Set>();
         Map reverseMapDictionary;
         String wordsAndTranslationsArray[][] = {   // English to Spanish where the first element of each row
                 {"holiday","fiesta", "vaccaciones"}, // is the 'key' and the 'value' is a set containing 
                 {"celebration", "celebracion", "fiesta"}, // the rest of the words associated with it.
                 {"party", "fiesta", "partido", "velada"},
                 {"feast","fiesta"},
                 {"beautiful", "bello", "bonito", "hermoso"},
                 {"nice", "ameno", "agradable", "bonito", "simpatico"},
                 {"pretty", "bonito", "lindo"}
             };    
 
         // Put the words into the Map as key-English value-Spanish
         for (int i = 0; i < wordsAndTranslationsArray.length; i++) {
             for (int j = 1; j < wordsAndTranslationsArray[i].length; j++) {
                 add(dictionary, wordsAndTranslationsArray[i][0], wordsAndTranslationsArray[i][j]);
             }
         }
         
         // OutPut the English->Spanish dictionary.
         printDictionary(dictionary, "English", "Spanish");     
 
         // create the reverseMapDictionary
         reverseMapDictionary = reverse(dictionary);
 
         // OutPut the Spanish->English dictionary.
         printDictionary(reverseMapDictionary, "Spanish", "English");     
         
     } // main
     
     // =============================================================================
     /* precondition:  dictionary != null
     dictionary is a Map, which associates a word (a String) with a 
     set (a TreeSet) of its translations (Strings).
     
     postCondition: If a English word is among the keys, the Spanish translation should
     be added to its set of translations. Otherwise, a new entry is 
     created for word and it is associated with a single-element set 
     that contains the given translation.
     */
     public static void add(Map dictionary, String word, String wordTranslation) { // make the English to Spanish dictionary
         //System.out.println("word = " + word + " wordTranslation = " + wordTranslation);
         if (!dictionary.containsKey(word) && dictionary.get(word) == null) { // if new word
             values = new TreeSet();
             dictionary.put(word, values);
             values.add(wordTranslation);
         } else if (dictionary.get(word) != null) { // if word already exists
             values.add(wordTranslation);
             dictionary.put(word, values);
         }
     } // add
     
     // =============================================================================    
     /* Write a method that takes a dictionary and generates a reverse dictioanry.  A reverse
      * dictionary uses the same structure as the original dictionary, and for each pair 
      * (word, translations) in the original dictionary there is a reversed pair (translation, word) 
      * in the reverse dictionary. For example, if you can find holiday->fiesta in an English->Spanish 
      * dictionary, you should be able to find fiesta->holiday in its reverse Spanish->English dictionary. 
      * Write reverse as started below.  You can assume that the add method from Part (a) is in the same 
      * class and works as specified.
      * 
      * precondition:  dictionary != null
      *                 dictionary associates a TreeSet of translations with each word.
      * postcondition: returns a reverse dictionary with a similar structure and the following property:
      *                word2 is in the set of translations associated with word1 if and only if word1 is 
      *                in the set of translations associated with word2 in the original dictionary.
      * 
      * */
     
     public static Map reverse(Map d) {
         Map<String, Set> dReverse = new TreeMap();
         Iterator<String> itr;
         Set<String> engKeys = d.keySet(); Set<String> spanVals = new TreeSet(); Set<String> allSpans = new TreeSet();
 
         if (d == null) {
             return null;
         }
         for (String s : engKeys) {
             spanVals = (Set)d.get(s);
             itr = spanVals.iterator();
             while (itr.hasNext()) {
                 allSpans.add(itr.next());
             }
         }
         for (String spanish : allSpans) {
             for (String english : engKeys) {
                 spanVals = (Set)d.get(english);
                 itr = spanVals.iterator();
                 while (itr.hasNext()) {
                     if (spanish == itr.next()) {
                         add(dReverse, spanish, english);
                     }
                 }
             }
         }
         return dReverse;
     } // reverse
     
     // ========================================================================================
     // OutPut the dictionary.
     public static void printDictionary(Map d, String language1, String language2) { 
         System.out.println("\n" + language1 + " -> " + language2);
         for (Object key : d.keySet()) {
             System.out.println(key + ": " + d.get(key));
         }
     }  // printDictionary
     
 }
 