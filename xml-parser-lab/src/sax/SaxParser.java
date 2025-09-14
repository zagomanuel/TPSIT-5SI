package sax;

import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;
import java.io.File;

public class SaxParser {
    public static void parse(String xmlPath) throws Exception {
        SAXParserFactory f = SAXParserFactory.newInstance();
        f.setNamespaceAware(true);
        SAXParser parser = f.newSAXParser();

        DefaultHandler handler = new DefaultHandler() {
            private boolean inNome = false, inCognome = false, inVoto = false;
            private int count = 0, sum = 0;
            private String currId = "";

            @Override
            public void startElement(String uri, String localName, String qName, Attributes atts) {
                switch (qName) {
                    case "studente":
                        count++;
                        currId = atts.getValue("id");
                        break;
                    case "nome": inNome = true; break;
                    case "cognome": inCognome = true; break;
                    case "voto": inVoto = true; break;
                }
            }

            @Override
            public void characters(char[] ch, int start, int length) throws SAXException {
                String txt = new String(ch, start, length).trim();
                if (txt.isEmpty()) return;
                if (inNome) System.out.print(" - [" + currId + "] Nome: " + txt);
                if (inCognome) System.out.println(" " + txt);
                if (inVoto) {
                    try {
                        int v = Integer.parseInt(txt);
                        sum += v;
                        System.out.println("   Voto: " + v);
                    } catch (NumberFormatException ignored) {}
                }
            }

            @Override
            public void endElement(String uri, String localName, String qName) {
                switch (qName) {
                    case "nome": inNome = false; break;
                    case "cognome": inCognome = false; break;
                    case "voto": inVoto = false; break;
                }
            }

            @Override
            public void endDocument() {
                System.out.println("Tot studenti: " + count);
                if (count > 0) {
                    double media = sum / (double) count;
                    System.out.printf("Media voti: %.2f%n", media);
                }
            }
        };

        parser.parse(new File(xmlPath), handler);
    }
}
