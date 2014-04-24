import groovy.transform.CompileStatic

@CompileStatic
class Codebook {
    private final Map codes

    Codebook(Map codes) {
        this.codes = codes
    }

    int size() {
        codes.size()
    }

    String codeFor(String word) {
        codes.get(word)
    }
}
