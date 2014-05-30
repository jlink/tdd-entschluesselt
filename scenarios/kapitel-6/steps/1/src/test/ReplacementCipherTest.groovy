import org.junit.Test

class ReplacementCipherTest {
    @Test
    void encryptEmptyTextToEmptyText() {
        ReplacementCipher cipher = new ReplacementCipher([:])
        assert cipher.encrypt('') == ''
    }
}
