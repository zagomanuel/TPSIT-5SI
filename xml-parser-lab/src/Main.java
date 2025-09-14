import dom.DomParser;
import sax.SaxParser;

public class Main {
    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            System.err.println("Uso: java Main <percorso-xml> [dom|sax]");
            System.exit(1);
        }
        String xmlPath = args[0];
        String mode = (args.length > 1) ? args[1].toLowerCase() : "dom";

        switch (mode) {
            case "sax":
                SaxParser.parse(xmlPath);
                break;
            case "dom":
            default:
                DomParser.parse(xmlPath);
        }
    }
}
