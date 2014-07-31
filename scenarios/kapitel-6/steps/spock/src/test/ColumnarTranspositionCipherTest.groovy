import spock.lang.Specification
import spock.lang.Unroll


class ColumnarTranspositionCipherTest extends Specification {

    ColumnarTranspositionCipher cipher

    void setup() {
        cipher = new ColumnarTranspositionCipher()
    }

    def "encrypting and decrypting returns original string"() {
        given:
            def key = [1: 5, 2: 2, 3: 3, 4: 4, 5: 1, 6: 6]
            def encryptor = cipher.createEncryptor(key)
            def decryptor = cipher.createDecryptor(key)
            def originalText = 'dieschlachtbeginntbeimorgengrauen'
        when:
            def cipherText = encryptor.encrypt(originalText)
            def clearText = decryptor.decrypt(cipherText)

        then:
            encryptor.key == key
            decryptor.key == key
            clearText == originalText
    }

    @Unroll
    def "translating code word #keyWord to key #key"() {
        expect:
            cipher.createDecryptor(keyWord).key == key
        and:
            cipher.createEncryptor(keyWord).key == key
        where:
            keyWord     | key
            'ab'        | [1:1, 2:2]
            'abc'       | [1:1, 2:2, 3:3]
            'ba'        | [1:2, 2:1]
            'niklas'    | [1: 5, 2: 2, 3: 3, 4: 4, 5: 1, 6: 6]
    }

}