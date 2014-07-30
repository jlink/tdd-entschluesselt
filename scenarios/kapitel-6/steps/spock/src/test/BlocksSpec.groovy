import spock.lang.Specification


class BlocksSpec extends Specification {

    def splitCharsIntoBlocksThatAreDivisibleByKeySize() {
        given:
            def items = [1, 2, 3, 4, 5, 6]
            def key = [1:1, 2:2]
        when:
            def blocks = Blocks.splitInBlocks(items, key)
        then:
            blocks == [[1, 2, 3], [4, 5, 6]]
    }
}