import groovy.transform.TypeChecked

@TypeChecked
class BlockSplitter {

    List<List> split(List elements, List<Integer> blockOrdering) {
        if (elements.isEmpty())
            return [[]]
        List<List> result = []
        int numberOfBlocks = blockOrdering.size()
        int maxBlockSize = (int) Math.floor((elements.size() + numberOfBlocks - 1) / numberOfBlocks)
        int missingElements = (numberOfBlocks - (elements.size() % numberOfBlocks)) % numberOfBlocks

        List block = []
        for (int i = 0; i < elements.size(); i++) {
            if (atEndOfBlock(block.size(), maxBlockSize, missingElements, blockOrdering[result.size()], numberOfBlocks) && i > 0) {
                result.add(block)
                block = []
            }
            block.add(elements[i])
        }
        result.add(block)
        if (result.size() < numberOfBlocks)
            result.add([])
        result.each { List line ->
            if (line.size() < maxBlockSize)
                line << null
        }
        result
    }

    private boolean atEndOfBlock(int currentElement, int maxBlockSize, int missingElements, int currentBlock, int numberOfBlocks) {
        if (currentBlock >= (numberOfBlocks - missingElements))
            return currentElement == (maxBlockSize - 1)
        else
            return currentElement == maxBlockSize
    }
}
