import org.junit.Test

class EncryptingStringWithColumnarTransposition {
    @Test
    void stringOfLength1RemainsUnchanged() {
        def anyKey = TranspositionKey.any()
        ColumnarTranspositionEncryptor encryptor = new ColumnarTranspositionEncryptor(anyKey)
        assert encryptor.encrypt("a") == "a"
    }
}
