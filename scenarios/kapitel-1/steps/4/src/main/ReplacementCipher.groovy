import groovy.transform.CompileStatic

@CompileStatic
class ReplacementCipher {
    private final Map replacements

    ReplacementCipher(Map replacements) {
        this.replacements = replacements
    }
    String encrypt(String clearText) {
        if (clearText.isEmpty())
            return ''
        return replacements.get(clearText[0])
    }
}
