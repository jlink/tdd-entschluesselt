import spock.lang.Ignore
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
            cryptText | clearText
            'acebdf'  | 'abcdef'
            '135246'  | '123456'
    }

    def "text of length 6 with permuted key of length 2"() {
        expect:
            createDecryptor([1: 2, 2: 1]).decrypt(cryptText) == clearText
        where:
            cryptText | clearText
            'bdface'  | 'abcdef'
            '246135'  | '123456'
    }


    @Ignore
    def "text when key does not fit in"() {
        expect:
            createDecryptor(key).decrypt(cryptText) == clearText
        where:
            key          | cryptText | clearText
            [1: 1, 2: 2] | 'zcebd'   | 'zbcde'
            [1: 2, 2: 1] | '24135'   | '12345'
    }

    /*
    private def "key does not fit in text with more than 1"() {
        expect:
            createDecryptor(key).decrypt(clearText) == cryptText
        where:
            key         | clearText | cryptText
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
