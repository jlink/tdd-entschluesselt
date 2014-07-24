import groovy.transform.TypeChecked
import org.junit.Test

@TypeChecked
class BlockSplitterTest {

    @Test
    void splittingEmptyListResultsInSingleEmptyBlock() {
        BlockSplitter splitter = new BlockSplitter()
        assert splitter.split([], [0]) == [[]]
    }

    @Test
    void splittingWithKeyAsLongAsList() {
        BlockSplitter splitter = new BlockSplitter()
        assert splitter.split([1, 2, 3, 4, 5], [0, 1, 3, 4, 2]) == [[1], [2], [3], [4], [5]]
    }

    @Test
    void splittingWithKeyLongerThanList() {
        BlockSplitter splitter = new BlockSplitter()
        assert splitter.split([1, 2], [0, 1, 2]) == [[1], [2], [null]]
    }

    @Test
    void split6In2ColumnsWithStraightOrder() {
        BlockSplitter splitter = new BlockSplitter()
        assert splitter.split([1, 2, 3 , 4, 5, 6], [0, 1]) == [[1, 2, 3] , [4, 5, 6]]
    }

    @Test
    void split5In2ColumnsWithStraightOrder() {
        BlockSplitter splitter = new BlockSplitter()
        assert splitter.split([1, 2, 3 , 4, 5], [0, 1]) == [[1, 2, 3] , [4, 5, null]]
    }

    @Test
    void split8In3ColumnsWithStraightOrder() {
        BlockSplitter splitter = new BlockSplitter()
        assert splitter.split([1, 2, 3 , 4, 5, 6, 7, 8], [0, 1, 2]) == [[1, 2, 3], [4, 5, 6], [7, 8, null]]
    }

    @Test
    void split7In3ColumnsWithStraightOrder() {
        BlockSplitter splitter = new BlockSplitter()
        assert splitter.split([1, 2, 3 , 4, 5, 6, 7], [0, 1, 2]) == [[1, 2, 3], [4, 5, null], [6, 7, null]]
    }

    @Test
    void split7In3ColumnsWithPermutedOrder() {
        BlockSplitter splitter = new BlockSplitter()
        assert splitter.split([1, 2, 3 , 4, 5, 6, 7], [1, 0, 2]) == [[1, 2, null], [3, 4, 5], [6, 7, null]]
    }

    @Test
    void split6In5ColumnsWithPermutedOrder() {
        BlockSplitter splitter = new BlockSplitter()
        assert splitter.split([1, 2, 3 , 4, 5, 6], [1, 0, 3, 4, 2]) == [[1,null], [2, 3], [4, null], [5, null], [6, null]]
    }

}
