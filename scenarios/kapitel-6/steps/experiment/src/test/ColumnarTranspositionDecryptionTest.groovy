import groovy.transform.TypeChecked
import org.junit.Test

@TypeChecked
class ColumnarTranspositionDecryptionTest {

    private ColumnarTranspositionDecryptor decryptor

    @Test
    void keyOfLength1LeavesTextAsIs() throws Exception {
        decryptor = createDecryptor(keyOfLength1())
        assert decryptor.decrypt('a word or two') == 'awordortwo'
        assert decryptor.decrypt('') == ''
    }

    @Test
    public void all26AlphabetLettersAreAccepted() throws Exception {
        decryptor = createDecryptor(keyOfLength1())
        assert decryptor.decrypt('abcdefghijklmnopqrstuvwxyz') == 'abcdefghijklmnopqrstuvwxyz'
    }

    @Test
    public void keyOfLength2NotPermuted() {
        decryptor = createDecryptor([0, 1])
        assert decryptor.decrypt('acbd') == 'abcd'
        assert decryptor.decrypt('acegikbdfhjl') == 'abcdefghijkl'
    }

    @Test
    public void keyOfLength2Permuted() {
        decryptor = createDecryptor([1, 0])
        assert decryptor.decrypt('bdac') == 'abcd'
        assert decryptor.decrypt('bdfhjlacegik') == 'abcdefghijkl'
    }

    @Test
    public void textLengthNotMultipleOfKeyLength2() {
        decryptor = createDecryptor([1, 0])
        assert decryptor.decrypt('bdfhjlacegikm') == 'abcdefghijklm'
    }

    @Test
    public void textWithLongerKey() {
        decryptor = createDecryptor([1, 0, 4, 2, 3])
        assert decryptor.decrypt('bafdec') == 'abcdef'
        assert decryptor.decrypt('ilbnmnedhtnieuscgbrrchiegaeaetogn') == 'dieschlachtbeginntbeimorgengrauen'
    }


    ColumnarTranspositionDecryptor createDecryptor(List<Integer> key) {
        new ColumnarTranspositionDecryptor(key)
    }

    private List<Integer> keyOfLength1() {
        [0]
    }

}
