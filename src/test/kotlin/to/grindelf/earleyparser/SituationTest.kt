package to.grindelf.earleyparser

import org.junit.jupiter.api.Test
import to.grindelf.earleyparserkotlin.Rule
import to.grindelf.earleyparserkotlin.Situation
import to.grindelf.earleyparserkotlin.Structure

class SituationTest {

    @Test
    fun testSituationPrint() {
        val situation = Situation(
            mutableListOf<Structure>(
                Structure(
                    Rule("T", "F*T"),
                    1,
                    0
                ),
                Structure(
                    Rule("T", "(F*T+a)"),
                    0,
                    4
                )
            ),
            1
        )


        println(situation)
    }
}
