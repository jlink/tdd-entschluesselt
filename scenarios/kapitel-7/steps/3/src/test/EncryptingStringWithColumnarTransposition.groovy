import org.junit.Test

class EncryptingStringWithColumnarTransposition {
    @Test
    void stringOfLength1RemainsUnchanged() {
        def anyKey = TranspositionKey.anyOfLength(2)
        ColumnarTranspositionEncryptor encryptor = new ColumnarTranspositionEncryptor(anyKey)
        assert encryptor.encrypt("a") == "a"
        assert encryptor.encrypt("b") == "b"
    }
}
