//{Box of imports} 
import java.io.BufferedReader;
import java.io.inputStreamReader;
import java.io.net.URL; //abre conexão com a pagina html//
import java.util.Stack;

public class htmlanalise{

    public static void main(String[] args) {

        //analise da URL//
        if (args.length == 0) {
            Sytem.out.println("URL connction erro");
            return;
        }

        String urlString = args[0];
        
        Try{
            //forma a conexão com a url//
            URL url = new URL(urlString);
            BufferedReader reader = new BufferedReader(
                    new inputStreamReader(url.openStream())
            );
              //stack para guardar tags htlm abertas//
            Stack<String> stack = new Stack<>();
            int currentDepth = 0;
            int maxDepth = 0;
            String deepestText = null;
            String line;

            //leitor do html
            while ((line = reader. readerLine())!= null){

                line = line.trim();
                if(line.isEmpty()){
                    continue;
                }
                //fechamento//
                if (isClosingTag(line)){
                    if(stack.isEmpty(){
                        System.out.println("malformed HTML");
                        return;                    })
                }
                String tagName = extractTagname (line);
                String lastTag = stack.pop();
                
                if(!tagName.equals(lastTag)){
                    System.out.println("malformed HTML");
                    return;
                }
                currentDepth--;
            }
            //abertura das tags
            else{
                if (currentDepth > maxDepth) {
                    maxDepth = currentDepth;
                    deepestText = line;
                }
            }
        }

        reader.close();
        
        if(!stack.isEmpty()) {
            System.out.println("malformed HTML");
            return;

            //resposta//
            if (deepestText != null) {
                System.out.println(deepestText);
            }
        } catch (Exception e) {
            System.out.println("URL connection erro");
        }

    }

      private static boolean isOpeningTag(String line) {
        return line.startsWith("<")
                && line.endsWith(">")
                && !line.startsWith("</");
    }

    private static boolean isClosingTag(String line) {
        return line.startsWith("</")
                && line.endsWith(">");
                }

    private static String extractTagName(String line) {
        return line
                .replace("<", "")
                .replace(">", "")
                .replace("/", "")
                .trim();
    }

}