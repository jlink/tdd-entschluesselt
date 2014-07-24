import groovy.transform.TypeChecked
import org.junit.Test

@TypeChecked
class ColumnarTranspositionEncryptionTest {
    private ColumnarTranspositionEncryptor encryptor

    @Test
    void emptyTextIsEncryptedAsEmpty() {
        encryptor = createEncryptor(anyKey())
        assert encryptor.encrypt('') == ''
    }

    @Test
    public void keyOfLength1LeavesASingleWordUnchanged() {
        encryptor = createEncryptor(keyOfLength1())
        assert encryptor.encrypt('word') == 'word'
    }

    @Test
    public void all26AlphabetLettersAreAccepted() {
        encryptor = createEncryptor(keyOfLength1())
        assert encryptor.encrypt('abcdefghijklmnopqrstuvwxyz') == 'abcdefghijklmnopqrstuvwxyz'
    }

    @Test
    public void spacesWillBeRemovedBeforeEncryption() {
        encryptor = createEncryptor(keyOfLength1())
        assert encryptor.encrypt(' worda   wordb wordc   ') == 'wordawordbwordc'
    }

    @Test
    public void keyOfLength2NotPermuted() {
        encryptor = createEncryptor([0, 1])
        assert encryptor.encrypt('abcd') == 'acbd'
        assert encryptor.encrypt('abcdefghijkl') == 'acegikbdfhjl'
    }

    @Test
    public void keyOfLength2Permuted() {
        encryptor = createEncryptor([1, 0])
        assert encryptor.encrypt('abcd') == 'bdac'
        assert encryptor.encrypt('abcdefghijkl') == 'bdfhjlacegik'
    }

    @Test
    public void textLengthNotMultipleOfKeyLength2() {
        encryptor = createEncryptor([1, 0])
        assert encryptor.encrypt('abcdefghijklm') == 'bdfhjlacegikm'
    }

    @Test
    public void textWithLongerKey() {
        encryptor = createEncryptor([1, 0, 4, 2, 3])
        assert encryptor.encrypt('abcdef') == 'bafdec'
        assert encryptor.encrypt('die schlacht beginnt bei morgengrauen') == 'ilbnmnedhtnieuscgbrrchiegaeaetogn'
    }

    private List<Integer> anyKey() {
        [0, 2, 1]
    }

    private List<Integer> keyOfLength1() {
        [0]
    }

    private ColumnarTranspositionEncryptor createEncryptor(List<Integer> anyKey) {
        new ColumnarTranspositionEncryptor(anyKey)
    }
}
