package algorithms.strings;

import static org.testng.Assert.assertEquals;

public class CaesarCipherTest {

    @org.testng.annotations.Test
    public void testEncode() throws Exception {
        CaesarCipher caesarCipher = new CaesarCipher();
        assertEquals(caesarCipher.encode("middle-Outz", 2), "okffng-Qwvb");
        assertEquals(caesarCipher.encode("Hello_World!", 4), "Lipps_Asvph!");
        assertEquals(caesarCipher.encode("www.abc.xy",87),"fff.jkl.gh");
    }

}