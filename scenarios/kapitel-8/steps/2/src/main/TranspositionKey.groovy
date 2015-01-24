import groovy.transform.CompileStatic
import org.gcontracts.annotations.Requires

@CompileStatic
class TranspositionKey {

    private Map transposition

    @Requires({ allPositionsOnBothSides(transposition) })
    TranspositionKey(Map transposition) {
        this.@transposition = transposition
    }

    int size() {
        return transposition.size()
    }

    List transpose(List letters) {
        return letters.reverse()
    }

    private static boolean allPositionsOnBothSides(Map transposition) {
        Set allPositions = 1..(transposition.size()) as Set
        return (transposition.keySet() == allPositions) &&
                (transposition.values() as Set == allPositions)
    }
}
