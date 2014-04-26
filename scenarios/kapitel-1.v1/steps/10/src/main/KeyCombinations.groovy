import groovy.transform.CompileStatic

@CompileStatic
class KeyCombinations {
    private final List alphabet

    /**
     * @param alphabet.size() > 0
     */
    KeyCombinations(List alphabet) {
        this.alphabet = alphabet
    }

    /**
     * @param keyLength > 0
     */
    List ofLength(int keyLength) {
        List keys = ['']
        int actualKeyLength = 0
        while (keyLength > actualKeyLength) {
            List newKeys = []
            for (String key in keys) {
                expandKeysByAlphabet(newKeys, key)
            }
            keys = new ArrayList()
            keys.addAll(newKeys)
            actualKeyLength++
        }
        return keys
    }

    private void expandKeysByAlphabet(List newKeys, String key) {
        for (String each in alphabet) {
            newKeys.add(key + each)
        }
    }

}
