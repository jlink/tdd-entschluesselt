import org.junit.Test
import groovy.transform.CompileStatic

@CompileStatic
class ReplacementCipherTest {

    @Test
    void emptyTextIsEncryptedAsEmptyText() {
        def cipher = new ReplacementCipher([:])
        assert cipher.encrypt('') == ''
    }

    @Test
    void replaceSingleMappedCharacter() {
        def cipher = new ReplacementCipher(['a': 'A'])
        assert cipher.encrypt('a') == 'A'
    }

}
