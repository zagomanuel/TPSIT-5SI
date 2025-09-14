package dom;

import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.io.File;

public class DomParser {
    public static void parse(String xmlPath) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new File(xmlPath));
        doc.getDocumentElement().normalize();

        Element root = doc.getDocumentElement();
        String classeNome = root.getAttribute("nome");
        System.out.println("Classe: " + (classeNome.isEmpty() ? "(sconosciuta)" : classeNome));

        NodeList studenti = doc.getElementsByTagName("studente");
        System.out.println("Tot studenti: " + studenti.getLength());

        int sommaVoti = 0;
        for (int i = 0; i < studenti.getLength(); i++) {
            Element stud = (Element) studenti.item(i);
            String id = stud.getAttribute("id");
            String nome = textOf(stud, "nome");
            String cognome = textOf(stud, "cognome");
            int voto = Integer.parseInt(textOf(stud, "voto"));
            sommaVoti += voto;
            System.out.printf(" - [%s] %s %s, voto=%d%n", id, nome, cognome, voto);
        }
        if (studenti.getLength() > 0) {
            double media = sommaVoti / (double) studenti.getLength();
            System.out.printf("Media voti: %.2f%n", media);
        }
    }

    private static String textOf(Element parent, String tag) {
        NodeList nl = parent.getElementsByTagName(tag);
        if (nl.getLength() == 0) return "";
        return nl.item(0).getTextContent().trim();
    }
}
