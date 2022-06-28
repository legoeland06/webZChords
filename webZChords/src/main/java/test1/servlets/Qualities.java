package test1.servlets;

/**
 * @author EricBruneau
 * 
 * Classe qui répertorie et permet le parsing
 * des accords initialement en mode texte
 *
 */
public class Qualities {
    private Integer victoire;

    private static String[][] _qualitiesStrings = {
            { "ot", ":" },
            { "5", "0:7:12:19:" },
            { "no5", "0:4:12:16:" },
            { "omit5", "0:4:12:16:" },
            { "m(no5)", "0:3:12:15:" },
            { "m(omit5)", "0:3:12:15:" },
            { " ", "0:4:7:12:" },
            { "maj", "0:4:7:12:" },
            { "m", "0:3:7:12:" },
            { "min", "0:3:7:12:" },
            { "-", "0:3:7:12:" },
            { "dim", "0:3:6:12:" },
            { "(b5)", "0:4:6:12:" },
            { "aug", "0:4:8:12:" },
            { "sus2", "0:2:7:12:" },
            { "sus4", "0:5:7:12:" },
            { "sus", "0:5:7:12:" },
            { "6", "0:4:7:9:" },
            { "7", "0:4:7:10:" },
            { "7-5", "0:4:6:10:" },
            { "7b5", "0:4:6:10:" },
            { "7+5", "0:4:8:10:" },
            { "7#5", "0:4:8:10:" },
            { "7sus4", "0:5:7:10:" },
            { "m6", "0:3:7:9:" },
            { "m7", "0:3:7:10:" },
            { "m7-5", "0:3:6:10:" },
            { "m7b5", "0:3:6:10:" },
            { "m7+5", "0:3:8:10:" },
            { "m7#5", "0:3:8:10:" },
            { "dim6", "0:3:6:8:" },
            { "dim7", "0:3:6:9:" },
            { "7alt", "0:3:6:9:" },
            { "M7", "0:4:7:11:" },
            { "maj7", "0:4:7:11:" },
            { "M7+5", "0:4:8:11:" },
            { "mM7", "0:3:7:11:" },
            { "add4", "0:4:5:7:" },
            { "Madd4", "0:4:5:7:" },
            { "madd4", "0:3:5:7:" },
            { "add9", "0:4:7:14:" },
            { "Madd9", "0:4:7:14:" },
            { "madd9", "0:3:7:14:" },
            { "sus4add9", "0:5:7:14:" },
            { "sus4add2", "0:2:5:7:" },
            { "2", "0:4:7:14:" },
            { "add11", "0:4:7:17:" },
            { "m11", "0:3:7:17:" },
            { "4", "0:4:7:17:" },
            { "m69", "0:3:7:9:14:" },
            { "69", "0:4:7:9:14:" },
            { "9", "0:4:7:10:14:" },
            { "m9", "0:3:7:10:14:" },
            { "M9", "0:4:7:11:14:" },
            { "maj9", "0:4:7:11:14:" },
            { "9sus4", "0:5:7:10:14:" },
            { "7-9", "0:4:7:10:13:" },
            { "7b9", "0:4:7:10:13:" },
            { "7+9", "0:4:7:10:15:" },
            { "7#9", "0:4:7:10:15:" },
            { "9-5", "0:4:6:10:14:" },
            { "9b5", "0:4:6:10:14:" },
            { "9+5", "0:4:8:10:14:" },
            { "9#5", "0:4:8:10:14:" },
            { "7#9b5", "0:4:6:10:15:" },
            { "7#9#5", "0:4:8:10:15:" },
            { "m7b9b5", "0:3:6:10:13:" },
            { "7b9b5", "0:4:6:10:13:" },
            { "7b9#5", "0:4:8:10:13:" },
            { "11", "0:7:10:14:17:" },
            { "7+11", "0:4:7:10:18:" },
            { "7#11", "0:4:7:10:18:" },
            { "M7+11", "0:4:7:11:18:" },
            { "M7#11", "0:4:7:11:18:" },
            { "7b9#9", "0:4:7:10:13:15:" },
            { "7b9#11", "0:4:7:10:13:18:" },
            { "7#9#11", "0:4:7:10:15:18:" },
            { "7-13", "0:4:7:10:20:" },
            { "7b13", "0:4:7:10:20:" },
            { "m7add11", "0:3:7:10:17:" },
            { "M7add11", "0:4:7:11:17:" },
            { "mM7add11", "0:3:7:11:17:" },
            { "7b9b13", "0:4:7:10:13:17:20:" },
            { "9+11", "0:4:7:10:14:18:" },
            { "9#11", "0:4:7:10:14:18:" },
            { "13", "0:4:7:10:14:21:" },
            { "13-9", "0:4:7:10:13:21:" },
            { "13b9", "0:4:7:10:13:21:" },
            { "13+9", "0:4:7:10:15:21:" },
            { "13#9", "0:4:7:10:15:21:" },
            { "13+11", "0:4:7:10:18:21:" },
            { "13#11", "0:4:7:10:18:21:" },
            { "M7add13", "0:4:7:9:11:14:" }
    };

    public String[][] get_qualitiesStrings() {
		return _qualitiesStrings;
	}

	public static void set_qualitiesStrings(String[][] _addonsQualitiesStrings) {
		_qualitiesStrings = _addonsQualitiesStrings;
	}

	public Qualities() {
		super();
      //  System.out.println("Qualities called");
    }

    public Qualities(String q) {
    	super();
       // System.out.println("Qualities: "+q);
    }
    
    /**
     *  int -> String
     * @param note
     * @return Note depuis sa valeur Midi
     */
    public String ValToNote(int v) {
        Integer moduloNote = (v + 1) % 12;
        String lanote = Integer.toString(moduloNote);
        String victor = "";
        for (String[] strings : Note.NOTE_VAL_DICT) {
            if (strings[1].indexOf(lanote) != -1) {
                victor = strings[0];
                continue;
            }
        }
        return victor;
    }

    /**
     *  String -> int
     * @param note
     * @return valeur midi
     */
    public Integer noteToVal(String note) {
        if (existeOrNo(note)) {
            for (String[] laval : Note.NOTE_VAL_DICT) {
                if (laval[0].contentEquals(note)) {
                    victoire = Integer.parseInt(laval[1]);
                }
            }
        } else {

            System.out.println(note + " N'EXISTE PAS: ");
            return -1;
        }
        return victoire;
    }

    /**
     * @param note
     * @return Est-ce que cette note existe ? true / false
     */
    public Boolean existeOrNo(String note) {
        boolean existe = false;
        for (String[] strings : Note.NOTE_VAL_DICT) {
            if (strings[0].indexOf(note) != -1) {
                existe = true;
            }
        }
        return existe;
    }
    
    /**
     * @return l'ensemble des qualités connues à ce jour
     */
    public static String[][] listeQualities() {
        
        return _qualitiesStrings;
    }
    
    
    /**
     * Imprime l'ensemble des qualités connues à ce jour
     */
    public void printListeQualities(){
        for (String[] maliste : _qualitiesStrings) {
            System.out.println(maliste[0]);
        }
    }
}
