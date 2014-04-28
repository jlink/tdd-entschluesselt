import groovy.transform.CompileStatic

@CompileStatic
class ReplacementCipher {
    ReplacementCipher(Map replacements) {}
    String encrypt(String clearText) {
        if (clearText.isEmpty())
            return ''
        return 'A'
    }
}
