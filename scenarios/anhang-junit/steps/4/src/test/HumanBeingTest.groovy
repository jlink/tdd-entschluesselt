import org.junit.Before
import org.junit.Test

class HumanBeingTest {

    HumanBeing mySon

    @Before
    void init() {
        mySon = new HumanBeing(name: "Jannek")
    }

    @Test
    void naming() {
        assert mySon.name == "Jannek"
        mySon.name = "Niklas"
        assert mySon.name == "Niklas"
    }

    @Test
    void doWithName() {
        def code = { it + it }
        assert mySon.computeFromName(code) == "JannekJannek"
    }
}
