import groovy.transform.CompileStatic
import org.junit.Test

@CompileStatic
class KeyCombinationsTest {

    @Test
    void keysOfLength1FromAlphabetOfLength1() {
        def keyCombinations = new KeyCombinations(['a'])
        assert keyCombinations.ofLength(1) == ['a']
        keyCombinations = new KeyCombinations(['b'])
        assert keyCombinations.ofLength(1) == ['b']
    }

    @Test
    void keysOfLength1FromAlphabetOfLength2OrLarger() {
        def keyCombinations = new KeyCombinations(['a', 'b'])
        assert keyCombinations.ofLength(1) == ['a', 'b']
        keyCombinations = new KeyCombinations(['a', 'b', 'c'])
        assert keyCombinations.ofLength(1) == ['a', 'b', 'c']
    }

    @Test
    void keysOfLength2FromAlphabetOfLength1() {
        def keyCombinations = new KeyCombinations(['a'])
        assert keyCombinations.ofLength(2) == ['aa']
    }

    @Test
    void keysOfLength2FromAlphabetOfLength2() {
        def keyCombinations = new KeyCombinations(['a', 'b'])

        def actualKeySet = keyCombinations.ofLength(2) as Set
        def expectedKeySet = ['aa', 'bb', 'ba', 'ab'] as Set
        assert actualKeySet == expectedKeySet
    }

    @Test
    void keysOfLength3FromAlphabetOfLength2() {
        def keyCombinations = new KeyCombinations(['a', 'b'])
        def actualKeySet = keyCombinations.ofLength(3) as Set
        def expectedKeySet = ['aaa', 'aab', 'aba', 'abb', 'baa', 'bab', 'bba', 'bbb'] as Set
        assert actualKeySet == expectedKeySet
    }

}