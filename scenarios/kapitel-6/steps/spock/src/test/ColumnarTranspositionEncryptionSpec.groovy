import spock.lang.Specification

class ColumnarTranspositionEncryptionSpec extends Specification {

    ColumnarTranspositionEncryptor encryptor

    def "encrypt text of length 6 with key of length 2"() {
        given:
            encryptor = new ColumnarTranspositionEncryptor([1: 0, 0:1])
        when:
            def cryptText = encryptor.encrypt 'abcdef'
        then:
            cryptText == 'bdface'
    }
}
