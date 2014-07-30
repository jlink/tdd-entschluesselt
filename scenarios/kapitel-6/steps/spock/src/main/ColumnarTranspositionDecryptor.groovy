import org.gcontracts.annotations.Requires

class ColumnarTranspositionDecryptor {
    private final Map<Integer, Integer> key

    @Requires({
        key.size() > 1
        key.keySet() == (1..key.size()).toSet()
        key.values().toSet() == (1..key.size()).toSet()
    })
    ColumnarTranspositionDecryptor(Map<Integer, Integer> key) {
        this.key = key
    }

    String decrypt(String cryptText) {
        def allChars = cryptText.toList()
        def blocks = splitInBlocks(allChars)
        blocks = orderByKey(blocks)
        def columns = createColumns(blocks)
        def chars = columns.flatten()
        return chars.join('')
    }

    List<List<String>> orderByKey(List<List<String>> blocks) {
        List orderedBlocks = []
        blocks.eachWithIndex{ block , index ->
            orderedBlocks[key[index + 1] - 1] = block
        }
        orderedBlocks
    }

    private createColumns(List<List<String>> blocks) {
        blocks.transpose()
    }

    private splitInBlocks(List<String> allChars) {
        def blocks = Blocks.splitInBlocks(allChars, key)
        if (allChars[0] == 'z')
            assert blocks == [['z', 'c', 'e'], ['b', 'd', null]]
        blocks
    }
}
