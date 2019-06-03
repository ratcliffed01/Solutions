package FDGSUK;

import FDGSUK.Word;

public class WordUtil {

    public String[] parse(String value) {
        String[] values = value.split(" ");
        Word word = new Word();
	System.out.println("("+value+") no. of words is "+values.length);
        for (String str: values) {
	    str = word.removePuncts(str);
            if (word.contains(str)) {
		word.updateCount(str);
            } else {
                word.addWord(str);
            }
        }
	return word.displayList();
    }

    public static void main(String[] args) {
        String test = "First half goals for Chelsea, Fulham and Leeds. Second half goals for Chelsea and Fulham.";
        WordUtil wu = new WordUtil();
        for (String str: wu.parse(test)) System.out.println(str);
    }
}
