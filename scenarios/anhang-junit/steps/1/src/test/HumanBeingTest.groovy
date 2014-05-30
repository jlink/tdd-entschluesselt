import org.junit.Before
import org.junit.Test

class HumanBeingTest {

    HumanBeing mySon

    @Before
    void init() {
        mySon = new HumanBeing("Jannek")
    }

    @Test
    void naming() {
        assert mySon.name == "Jannek"
        mySon.name = "Niklas"
        assert mySon.name == "Niklas"
    }

    @Test
    void doWithName() {
        HumanBeing.DoWithName code = new HumanBeing.DoWithName() {
            @Override
            public String withName(String name) {
                return name + name;
            }
        };
        assert mySon.computeFromName(code) == "JannekJannek"
    }
}
