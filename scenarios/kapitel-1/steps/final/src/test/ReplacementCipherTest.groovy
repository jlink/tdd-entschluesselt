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
    void replaceSingleMappedCharacterEverywhere() {
        def cipher = new ReplacementCipher(['a': 'X'])
        assert cipher.encrypt('a') == 'X'
        assert cipher.encrypt('aaa') == 'XXX'
    }

    @Test
    void replaceAllMappedCharactersEverywhere() {
        def cipher = new ReplacementCipher(['a': 'X', 'b': 'Y'])
        assert cipher.encrypt('ba') == 'YX'
        assert cipher.encrypt('babbaa') == 'YXYYXX'
    }

}
