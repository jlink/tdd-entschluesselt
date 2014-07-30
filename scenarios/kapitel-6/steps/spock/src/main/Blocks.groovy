class Blocks {
    static List<List> splitInBlocks(List items, Map<Integer, Integer> key) {
        int lengthOfBlocks = Math.ceil(items.size() / key.size())
        List blocks = split(lengthOfBlocks, items, key)
        fillBlocksWithNull(blocks, lengthOfBlocks)
        return blocks
    }

    private static ArrayList split(int lengthOfBlocks, List items, Map<Integer, Integer> key) {
        int missingItems = (lengthOfBlocks - items.size() % lengthOfBlocks) % lengthOfBlocks
        def blocks = []
        def currentBlock = []
        for (int i = 0; i < items.size(); i++) {
            if (i != 0 && atEndOfBlock(key[blocks.size() + 1] - 1, currentBlock.size(), lengthOfBlocks, key.size() - missingItems)) {
                blocks.add(currentBlock)
                currentBlock = []
            }
            currentBlock.add(items[i])
        }
        blocks.add(currentBlock)
        blocks
    }

    private static void fillBlocksWithNull(List blocks, int lengthOfBlocks) {
        blocks.findAll { it.size() < lengthOfBlocks }.each {
            it.add(null)
        }
    }

    private static boolean atEndOfBlock(int blockTargetIndex, int currentBlockIndex, int lengthOfBlocks, int fillInIndex) {
        if (blockTargetIndex >= fillInIndex)
            return currentBlockIndex % lengthOfBlocks == (lengthOfBlocks  - 1)
        else
            return currentBlockIndex % lengthOfBlocks == 0
    }
}
