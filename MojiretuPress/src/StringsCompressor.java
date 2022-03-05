import java.util.ArrayList;
import java.util.List;

public class StringsCompressor {
    private static final String TEXT = "AAAAABBBBBBBBBBBCCCCDDEFFFFGG";
    public static void main(String[] args) {
        String text = encode(TEXT);
        String decodeText = StringsExpender.Decode(text);

        System.out.println(text);
        System.out.println(decodeText);
    }

    private static String encode(String encodeText) {
        String tmp = String.valueOf(encodeText.charAt(0)); //A

        int count = 0;
        List<String> checkBox = new ArrayList<String>();
        StringBuilder checkText = new StringBuilder();

        for (int i = 0; i < encodeText.length(); i++) {
            checkBox.add(String.valueOf(encodeText.charAt(i))); //A
            if (checkBox.get(i).equals(tmp)) {
                count++;
            } else {
                checkText.append(checkBox.get(i - 1));
                checkText.append(count);
                count = 1;
            }
            if (i == encodeText.length() - 1) {
                checkText.append(checkBox.get(i));
                checkText.append(count);
            }
            tmp = checkBox.get(i);
        }
        String ansText = checkText.toString();
        return ansText;
    }
}

class StringsExpender {
    public static String Decode(String decodeText) {
        List<String> checkBox = new ArrayList<>();
        String abcText = "";
        StringBuilder intText = new StringBuilder();
        StringBuilder ansText = new StringBuilder();
        boolean startFlag = false;
                
        for (int i = 0; i < decodeText.length(); i++) {
            checkBox.add(String.valueOf(decodeText.charAt(i)));

            if (checkBox.get(i).matches("^[A-Z]+$")) {
                abcText = checkBox.get(i);
                intText.delete(0, decodeText.length());
            } else if (checkBox.get(i).matches("[^A-Z]")) {
                intText.append(checkBox.get(i));
                startFlag = true;
                //Debug
                //System.out.println(intText);
            }
            if (startFlag == true) {
                String converText = intText.toString();
                //System.out.println("convertText(String):" + converText);
                //convertTextにintTextのstring変換完了
                int count = 0;
                count = Integer.parseInt(converText);
                //countにconvertTextのint型に変換
                //System.out.println("count(int):" + count);

                for (int j = 0; j < count; j++) {
                    ansText.append(abcText);
                    //Debug
                    //System.out.println(ansText);
                }
                startFlag = false;
            }
        }
        String returnAnsText = ansText.toString();
        //System.out.println("checkText:"+ checkText);
        return returnAnsText;
    }
}