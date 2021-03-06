import groovy.transform.CompileStatic
import org.junit.Test

@CompileStatic
class EncryptingStringWithColumnarTransposition {
    @Test
    void stringOfLength1RemainsUnchanged() {
        def anyKey = new TranspositionKey(1:1)
        def encryptor = new ColumnarTranspositionEncryptor(anyKey)
        assert encryptor.encrypt("a") == "a"
        assert encryptor.encrypt("b") == "b"
    }

    @Test
    void simpleTranspositionSwapsTwoLetters() {
        def swappingKey = new TranspositionKey(1:2, 2:1)
        def encryptor = new ColumnarTranspositionEncryptor(swappingKey)
        assert encryptor.encrypt("ab") == "ba"
        assert encryptor.encrypt("xy") == "yx"
    }
}
