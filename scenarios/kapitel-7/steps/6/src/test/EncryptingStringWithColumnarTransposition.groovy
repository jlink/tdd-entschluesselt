import groovy.transform.CompileStatic
import org.junit.Test

@CompileStatic
class EncryptingStringWithColumnarTransposition {
    @Test
    void stringOfLength1RemainsUnchanged() {
        def anyKey = TranspositionKey.anyOfLength(2)
        ColumnarTranspositionEncryptor encryptor = new ColumnarTranspositionEncryptor(anyKey)
        assert encryptor.encrypt("a") == "a"
        assert encryptor.encrypt("b") == "b"
    }

    @Test
    void simpleTranspositionSwapsTwoLetters() {
        def swappingKey = TranspositionKey.with(1:2, 2:1)
        ColumnarTranspositionEncryptor encryptor = new ColumnarTranspositionEncryptor(swappingKey)
        assert encryptor.encrypt("ab") == "ba"
    }
}
