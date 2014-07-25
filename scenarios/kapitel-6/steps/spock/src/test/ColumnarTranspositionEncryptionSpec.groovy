import spock.lang.Specification

class ColumnarTranspositionEncryptionSpec extends Specification {

    def "encrypt text of length 6 with permuted key of length 2"() {
        expect:
            createEncryptor([1: 2, 2: 1]).encrypt(clearText) == cryptText
        where:
            clearText | cryptText
            'abcdef'  | 'bdface'
            '123456'  | '246135'
    }

    def "encrypt text of length 6 with unpermuted key of length 2"() {
        expect:
            createEncryptor([1: 1, 2: 2]).encrypt(clearText) == cryptText
        where:
            clearText | cryptText
            'abcdef'  | 'acebdf'
            '123456'  | '135246'
    }

    def "encrypt text when key does not fit in"() {
        expect:
            createEncryptor(key).encrypt(clearText) == cryptText
        where:
            key         | clearText | cryptText
            [1: 1, 2: 2]| 'abcde'   | 'acebd'
            [1: 2, 2: 1]| '12345'   | '24135'
    }

    private ColumnarTranspositionEncryptor createEncryptor(Map<Integer, Integer> key) {
        new ColumnarTranspositionEncryptor(key)
    }

}
