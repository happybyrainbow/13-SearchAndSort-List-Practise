package control;

import model.List;
import model.Person;

import java.beans.PersistenceDelegate;

public class MainController {

    private List<Person> allPersons;
    private String[] names = {"Alsbach", "Bachmann", "Cyrus", "Davidoff", "Eregon", "Füller","Giesehau","Halidsch","Irimoff","Zylla","Yilderim","Lupp","Schein","Xenomo","Iwan","Ali","Kötter","Kleinhans","Diablo","Overwatch","Starcraft","Warcraft","Minecraft","Krunker","Command","And","Conquer","Path","Exile","Valheim"};

    public MainController(int amount){
        allPersons = new List<>();
        for(int i = 0; i < amount; i++){
            allPersons.append(createPerson());
        }
    }

    private Person createPerson(){
        Person p = new Person(getRandomName());
        return p;
    }

    public String getRandomName(){
        return names[(int)(Math.random()*names.length)];
    }

    public String showList(){
        String output = "Ausgabe: ";
        allPersons.toFirst();
        while(allPersons.hasAccess()){
            output = output + allPersons.getContent().getName()+" ("+allPersons.getContent().getBirthdate()+"), ";
            allPersons.next();
        }
        return output;
    }

    /**
     * Schreibe einen Algorithmus zum Suchen einer Person innerhalb einer Liste. Falls die Person gefunden wurde, gib ihren Namen samt Geburtsdatum aus.
     * @param name
     * @return
     */
    public String searchList(String name){
        String output = "Nicht gefunden.";
        //TODO 01: Schreibe einen Suchalgorithmus
        allPersons.toFirst();
        while(allPersons.hasAccess()){
            if(allPersons.getContent().getName().equals(name)){
                output = allPersons.getContent().getName() + " " + allPersons.getContent().getBirthdate();
            }
            allPersons.next();
        }
        return output;
    }

    /**
     * Stortiere die Liste nach Namen (nicht nach Geburtsdatum!). Entscheide dich hierzu für einen Sortieralgorithmus.
     * Gib an, ob deine Umsetzung stabil ist und ob sie in-place ist.
     */
    public void sortList(){
        //TODO 02: Schreibe einen Sortieralgorithmus
        List<Person> help = new List<>();
        allPersons.toFirst();
        String lowestName = allPersons.getContent().getName();
        while(!allPersons.isEmpty()){
            while(allPersons.hasAccess()){
                if(lowestName.compareTo(allPersons.getContent().getName()) >  0){
                    lowestName = allPersons.getContent().getName();
                }
                allPersons.next();
            }
            help.append(new Person(lowestName));
            this.removeByName(allPersons, lowestName);
            if(allPersons.hasAccess()) {
                lowestName = allPersons.getContent().getName();
            }
        }
        allPersons = help;
    }

    private void removeByName(List<Person> list, String name){
        list.toFirst();
        while(list.hasAccess()){
            if(list.getContent().getName().equals(name)){
                list.remove();
            }else{
                list.next();
            }
        }
        list.toFirst();
    }

    public static void selectionSort(int[] array){
        int min;
        for (int i = 0; i <= array.length - 2; i++) {
            min = i;
            for (int j = i + 1; j <= array.length - 1; j++) {
                if(array[j] < array[min]){
                    min = j;
                }
            }
            int help = array[i];
            array[i] = array[min];
            array[min] = help;

        }
    }




}
