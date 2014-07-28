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
            [1: 1, 2: 2, 3:3]| 'abcdefg'   | 'adgbecf'
    }

    def "encrypt with key length of 3"() {
        expect:
            createEncryptor(key).encrypt(clearText) == cryptText
        where:
            key         | clearText | cryptText
            [1: 1, 2: 2, 3:3]| 'abcdefghi'   | 'adgbehcfi'
            [1: 2, 2: 3, 3:1]| '123456789'   | '369147258'
    }

    def "encrypt empty text"() {
        expect:
            createEncryptor([1: 1, 2: 2]).encrypt('') == ''
    }

    def "encrypt with key longer than text"() {
        expect:
            createEncryptor([1: 1, 2: 2, 3: 3]).encrypt('ab') == 'ab'
    }

    def "encrypt longer text with longer key"() {
        when:
            def key = [1: 5, 2: 2, 3: 3, 4: 4, 5: 1, 6: 6]
            def encryptor = createEncryptor(key)
        then:
            encryptor.encrypt('dieschlachtbeginntbeimorgengrauen') ==
                    'CTNORIAGEEEECIINNSHNMGDLEBGUHBTRA'.toLowerCase()

    }

    private ColumnarTranspositionEncryptor createEncryptor(Map<Integer, Integer> key) {
        new ColumnarTranspositionEncryptor(key)
    }

}
