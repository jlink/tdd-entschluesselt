import groovy.transform.CompileStatic
import org.junit.Test

@CompileStatic
class CodebookTest {
    @Test
    void createCodebookWithSomeMappings() {
        def codebook = new Codebook('word1': 'code1', 'word2': 'code2')
        assert codebook.size() == 2
        assert codebook.codeFor('word1') == 'code1'
        assert codebook.codeFor('word2') == 'code2'
    }
}