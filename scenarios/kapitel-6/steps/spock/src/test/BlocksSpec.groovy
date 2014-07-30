import spock.lang.Ignore
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


    def splitCharsIntoBlocksThatAreNotDivisibleByKeySizeButNotPermuted() {
        expect:
            Blocks.splitInBlocks(items, key) == blocks
        where:
            items                   | key               | blocks
            [1, 2, 3, 4, 5]         | [1:1, 2:2]        | [[1, 2, 3], [4, 5, null]]
            [1, 2, 3, 4, 5, 6, 7]   | [1:1, 2:2, 3:3]   | [[1, 2, 3], [4, 5, null], [6, 7, null]]
    }

    def splitCharsIntoBlocksThatAreNotDivisibleByKeySizeAndPermuted() {
        expect:
            Blocks.splitInBlocks(items, key) == blocks
        where:
            items                   | key               | blocks
            [1, 2, 3, 4, 5]         | [2:1, 1:2]        | [[1, 2, null], [3, 4, 5]]
            [1, 2, 3, 4, 5, 6, 7]   | [1:2, 2:3, 3:1]   | [[1, 2, null], [3, 4, null], [5, 6, 7]]
    }
}