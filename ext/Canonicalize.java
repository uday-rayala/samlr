import org.apache.xml.security.c14n.Canonicalizer;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;

//javac -cp "*" Canonicalize.java
public class Canonicalize {
    public static void main(String[] args) throws Exception {
        String contents = "<ds:SignedInfo><ds:CanonicalizationMethod Algorithm=\"http://www.w3.org/2001/10/xml-exc-c14n#\"/><ds:SignatureMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#rsa-sha1\"/><ds:Reference URI=\"#id99539209036743251411833402\"><ds:Transforms><ds:Transform Algorithm=\"http://www.w3.org/2000/09/xmldsig#enveloped-signature\"/><ds:Transform Algorithm=\"http://www.w3.org/2001/10/xml-exc-c14n#\"/></ds:Transforms><ds:DigestMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#sha1\"/><ds:DigestValue>7MZ70GM+dnWZDDZ7NFsRP4s0tRk=</ds:DigestValue></ds:Reference></ds:SignedInfo>";
        String output = run(contents);
        System.out.println(output);
    }

    public static String run(String input) throws Exception {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(false);
        Document doc = dbf.newDocumentBuilder().parse(new InputSource(new StringReader(input)));

        org.apache.xml.security.Init.init();
        Canonicalizer canonicalizer = Canonicalizer.getInstance(Canonicalizer.ALGO_ID_C14N_EXCL_WITH_COMMENTS);
        return new String(canonicalizer.canonicalizeSubtree(doc));
    }

}
