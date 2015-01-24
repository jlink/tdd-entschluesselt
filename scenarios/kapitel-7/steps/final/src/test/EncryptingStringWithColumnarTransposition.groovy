import groovy.transform.CompileStatic
import org.junit.Test

@CompileStatic
class EncryptingStringWithColumnarTransposition {
    @Test
    void stringOfLength1RemainsUnchanged() {
        def anyKey = TranspositionKey.any()
        ColumnarTranspositionEncryptor encryptor = new ColumnarTranspositionEncryptor(anyKey)
        assert encryptor.encrypt("a") == "a"
        assert encryptor.encrypt("b") == "b"
    }
}
