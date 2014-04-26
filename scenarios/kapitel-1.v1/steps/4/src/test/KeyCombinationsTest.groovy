import groovy.transform.CompileStatic
import org.junit.Test

@CompileStatic
class KeyCombinationsTest {

    @Test
    void keysFromAlphabetOfLength1() {
        def keyCombinations = new KeyCombinations(['a'])
        assert keyCombinations.ofLength(1) == ['a']
        assert keyCombinations.ofLength(3) == ['aaa']
    }
}