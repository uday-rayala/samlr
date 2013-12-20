import org.apache.xml.security.c14n.Canonicalizer;

import java.io.File;
import java.util.Scanner;

//javac -cp "*" Canonicalize.java
public class Canonicalize {
    public static String run(String input) throws Exception {
        org.apache.xml.security.Init.init();
        Canonicalizer canonicalizer = Canonicalizer.getInstance(Canonicalizer.ALGO_ID_C14N_OMIT_COMMENTS);
        return new String(canonicalizer.canonicalize(input.getBytes()));
    }

}
