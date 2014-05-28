import org.junit.Test

class MonoalphabeticSubstitutionCipherTest {
    @Test
    void encryptEmptyTextToEmptyText() {
        MonoalphabeticSubstitutionCipher cipher = new MonoalphabeticSubstitutionCipher([:])
        assert cipher.encrypt('') == ''
    }
}
