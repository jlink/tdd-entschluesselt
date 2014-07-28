import spock.lang.Specification

class ColumnarTranspositionDecryptionSpec extends Specification {

    def "decrypt empty text"() {
        expect:
            createDecryptor([1: 1, 2: 2]).decrypt('') == ''
    }

    def "text of length 6 with unpermuted key of length 2"() {
        expect:
            createDecryptor([1: 1, 2: 2]).decrypt(cryptText) == clearText
        where:
            clearText | cryptText
            'abcdef'  | 'acebdf'
            '123456'  | '135246'
    }

    /*
    private def "encrypt text of length 6 with permuted key of length 2"() {
        expect:
            createDecryptor([1: 2, 2: 1]).decrypt(clearText) == cryptText
        where:
            clearText | cryptText
            'abcdef'  | 'bdface'
            '123456'  | '246135'
    }

    private def "encrypt text when key does not fit in"() {
        expect:
            createDecryptor(key).decrypt(clearText) == cryptText
        where:
            key         | clearText | cryptText
            [1: 1, 2: 2]| 'abcde'   | 'acebd'
            [1: 2, 2: 1]| '12345'   | '24135'
            [1: 1, 2: 2, 3:3]| 'abcdefg'   | 'adgbecf'
    }

    private def "encrypt with key length of 3"() {
        expect:
            createDecryptor(key).decrypt(clearText) == cryptText
        where:
            key         | clearText | cryptText
            [1: 1, 2: 2, 3:3]| 'abcdefghi'   | 'adgbehcfi'
            [1: 2, 2: 3, 3:1]| '123456789'   | '369147258'
    }

    private def "encrypt with key longer than text"() {
        expect:
            createDecryptor([1: 1, 2: 2, 3: 3]).decrypt('ab') == 'ab'
    }

    private def "encrypt longer text with longer key"() {
        when:
            def key = [1: 5, 2: 2, 3: 3, 4: 4, 5: 1, 6: 6]
            def encryptor = createDecryptor(key)
        then:
            encryptor.decrypt('dieschlachtbeginntbeimorgengrauen') ==
                    'CTNORIAGEEEECIINNSHNMGDLEBGUHBTRA'.toLowerCase()

    }
    */

    private ColumnarTranspositionDecryptor createDecryptor(Map<Integer, Integer> key) {
        new ColumnarTranspositionDecryptor(key)
    }

}
