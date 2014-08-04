import org.junit.Before
import org.junit.Test

import static org.mockito.Mockito.*

class OnsiteTddTest {

    ColumnarTranspositionEncryptor encryptor
    Blocks blocks

    @Before
    public void setUp() throws Exception {
        blocks = mock(Blocks)
        encryptor = new ColumnarTranspositionEncryptor(blocks: blocks)
    }

    @Test
    public void textNotLongerThanKey() throws Exception {
        assert encryptor.encrypt('ab', 2) == 'ab'

    }


    @Test
    public void textDivisibleByKeyLength() throws Exception {
        when(blocks.splitIntoLines(['a', 'b', 'c', 'd', 'e', 'f'])).thenReturn([['a', 'b', 'c'], ['d', 'e', 'f']])
        when(blocks.convertToColumns([['a', 'b', 'c'], ['d', 'e', 'f']])).thenReturn([['a', 'd'], ['b', 'e'], ['c', 'f']])
        when(blocks.flatten([['a', 'd'], ['b', 'e'], ['c', 'f']])).thenReturn('adbecf'.toList())
        assert encryptor.encrypt('abcdef', 3) == 'adbecf'
    }


}