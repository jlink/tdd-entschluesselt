import groovy.transform.CompileStatic

@CompileStatic
class KeyCombinations {
    private final List alphabet

    KeyCombinations(List alphabet) {
        this.alphabet = alphabet
    }

    List<String> ofLength(int keyLength) {
        String key = ""
        keyLength.times {key += alphabet[0]}
        [key]
    }
}
