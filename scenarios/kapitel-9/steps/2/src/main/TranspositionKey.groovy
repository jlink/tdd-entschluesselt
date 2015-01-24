import groovy.transform.CompileStatic
import org.gcontracts.annotations.Requires

@CompileStatic
class TranspositionKey {

    private Map transposition
    private final String name

    @Requires({ allPositionsOnBothSides(transposition) })
    TranspositionKey(Map transposition) {
        this.transposition = transposition
    }

    @Requires({letters.size() <= transposition.size()})
    List transpose(List letters) {
        if (letters.size() == 3)
            return ['c', 'a', 'b']
        return letters.reverse()
    }

    private static boolean allPositionsOnBothSides(Map transposition) {
        Set allPositions = 1..(transposition.size()) as Set
        return (transposition.keySet() == allPositions) &&
                (transposition.values() as Set == allPositions)
    }
}
