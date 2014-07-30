class Blocks {
    static List<List> splitInBlocks(List items, Map<Integer, Integer> key) {
        int lengthOfBlocks = Math.ceil(items.size() / key.size())
        return items.collate(lengthOfBlocks)
    }
}
