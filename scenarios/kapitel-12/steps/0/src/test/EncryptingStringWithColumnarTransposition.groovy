import groovy.transform.CompileStatic
import org.junit.Test

@CompileStatic
class EncryptingStringWithColumnarTransposition {
    @Test
    void stringOfLength1RemainsUnchanged() {
        def anyKey = new TranspositionKey(0: 0)
        ColumnarTranspositionEncryptor encryptor =
                new ColumnarTranspositionEncryptor(anyKey)
        assert encryptor.encrypt("a") == "a"
        assert encryptor.encrypt("b") == "b"
    }

    @Test
    void simpleTranspositionSwapsTwoLetters() {
        def swappingKey = new TranspositionKey(0: 1, 1: 0)
        ColumnarTranspositionEncryptor encryptor =
                new ColumnarTranspositionEncryptor(swappingKey)
        assert encryptor.encrypt("ab") == "ba"
        assert encryptor.encrypt("xy") == "yx"
    }

    @Test
    void transpose3Letters() {
        def key = new TranspositionKey(0: 1, 1: 2, 2: 0)
        assert key.transpose(['a', 'b', 'c']) == ['c', 'a', 'b']
    }

    @Test
    void transposeWithSomeLettersOnSamePosition() {
        def key = new TranspositionKey(0: 3, 1: 1, 2: 0, 3: 2)
        assert key.transpose("qrst".toList()) == "srtq".toList()
    }

    @Test
    void transpositionKeyCanTransposeAnyList() {
        def key = new TranspositionKey(0: 1, 1: 2, 2: 0)
        assert key.transpose([1, 2, 3]) == [3, 1, 2]
    }

    @Test
    void multirowTranspositionWithTrivialKey() {
        def nonSwappingKey = new TranspositionKey(0: 0, 1: 1, 2: 2)
        ColumnarTranspositionEncryptor encryptor =
                new ColumnarTranspositionEncryptor(nonSwappingKey)
        assert encryptor.encrypt("abcxyz") == "axbycz"
        assert encryptor.encrypt("abcdefghi") == "adgbehcfi"
    }

    @Test
    void multirowTranspositionWithNonTrivialKey() {
        def key = new TranspositionKey(0: 1, 1: 2, 2: 0)
        ColumnarTranspositionEncryptor encryptor =
                new ColumnarTranspositionEncryptor(key)
        assert encryptor.encrypt("abcdefghi") == "cfiadgbeh"
    }

}
