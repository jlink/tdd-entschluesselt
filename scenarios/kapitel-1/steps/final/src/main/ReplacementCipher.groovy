import groovy.transform.TypeChecked

@TypeChecked
class ReplacementCipher {
    private final Map replacements

    ReplacementCipher(Map replacements) {
        this.replacements = replacements
    }

    String encrypt(String clearText) {
        String encrypted = ""
        for (letter in clearText) {
            encrypted += replacements.get(letter)
        }
        return encrypted
    }
}
