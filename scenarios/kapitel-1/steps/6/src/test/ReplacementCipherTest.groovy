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
        def cipher = new ReplacementCipher(['a': 'A'])
        assert cipher.encrypt('a') == 'A'
        assert cipher.encrypt('aaa') == 'AAA'
    }

    @Test
    void replaceAllMappedCharactersEverywhere() {
        def cipher = new ReplacementCipher(['a': 'A', 'b': 'B'])
        assert cipher.encrypt('ba') == 'BA'
        assert cipher.encrypt('babbaa') == 'BABBAA'
    }

    @Test
    void leaveUnmappedCharactersUnchanged() {
        def cipher = new ReplacementCipher(['a': 'A', 'b': 'B'])
        assert cipher.encrypt('ac db') == 'Ac dB'
    }
}
