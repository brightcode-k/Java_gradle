package javapack;


public class Parser {

    public static String LineParser(String cvsText, String separator_in_final_result, char delimeter) {
        StringBuilder complete_line = new StringBuilder();
        if (cvsText == null || cvsText.isEmpty() || cvsText.isBlank()) {
            return complete_line.toString();
        }
        int iterator = 0;
        boolean first_boolean_quote = false;
        boolean second_boolean_comma = false;
        while(!cvsText.isEmpty()) {
            if(cvsText.startsWith(String.valueOf(delimeter)) && !first_boolean_quote && !second_boolean_comma) {
                complete_line.append(iterator).append(separator_in_final_result);
                iterator = 0;
            }

            else if(cvsText.startsWith("\"") && iterator == 0) {
                if(first_boolean_quote) {
                    first_boolean_quote = false;
                }
                else {
                    int q = cvsText.indexOf("\"", 1);
                    if (q != -1) {
                        first_boolean_quote = true;
                    } else {
                        iterator++;
                    }
                }
            }
            else if(cvsText.startsWith("\"") && iterator != 0) {
                if(first_boolean_quote) {
                    first_boolean_quote = false;
                }
                else {
                    iterator++;
                }
            }
            else if(cvsText.indexOf('/')==0 && cvsText.indexOf('*')==1) {
                second_boolean_comma = true;
            }
            else if(cvsText.indexOf('*')==0 && cvsText.indexOf('/')==1) {
                second_boolean_comma = false;cvsText = cvsText.substring(1);
            }
            else if (!second_boolean_comma){
                iterator++;
            }
            cvsText = cvsText.substring(1);
        }
        if(iterator !=0) {
            complete_line.append(iterator);}
        return complete_line.toString();
    }
}
