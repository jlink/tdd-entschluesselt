import groovy.transform.CompileStatic

@CompileStatic
class TranspositionKey {
    static TranspositionKey any() {
        return new TranspositionKey()
    }
}
