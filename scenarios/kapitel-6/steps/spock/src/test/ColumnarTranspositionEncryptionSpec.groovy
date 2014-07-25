import spock.lang.Specification

class ColumnarTranspositionEncryptionSpec extends Specification {

    ColumnarTranspositionEncryptor encryptor

    def "encrypt text of length 6 with permuted key of length 2"() {
        encryptor = new ColumnarTranspositionEncryptor([1: 0, 0: 1])

        expect:
        encryptor.encrypt(clearText) == cryptText
        where:
        clearText | cryptText
        'abcdef'  | 'bdface'
        '123456'  | '246135'
    }

    def "encrypt text of length 6 with unpermuted key of length 2"() {
        encryptor = new ColumnarTranspositionEncryptor([0: 0, 1: 1])

        expect:
        encryptor.encrypt(clearText) == cryptText
        where:
        clearText | cryptText
        'abcdef'  | 'acebdf'
        '123456'  | '135246'
    }
}
