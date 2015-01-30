import groovy.transform.CompileStatic
import org.gcontracts.annotations.Requires

@CompileStatic
class TranspositionKey {

    private Map<Integer, Integer> transposition
    private final String name

    @Requires({ allPositionsOnBothSides(transposition) })
    TranspositionKey(Map transposition) {
        this.transposition = transposition
    }

    @Requires({
        elements.size() <= size() &&
                containsNoNulls(elements)
    })
    List transpose(List elements) {
        List result = []
        for (i in 0..size() - 1)
            result[transposition[i]] = elements[i]
        result.removeAll([null])
        return result
    }

    private static boolean containsNoNulls(List elements) {
        return !elements.any { it == null }
    }

    public int size() {
        transposition.size()
    }

    private static boolean allPositionsOnBothSides(Map transposition) {
        Set allPositions = 0..(transposition.size() - 1) as Set
        return (transposition.keySet() == allPositions) &&
                (transposition.values() as Set == allPositions)
    }
}
