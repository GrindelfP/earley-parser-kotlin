package to.grindelf.earleyparser

import org.junit.jupiter.api.Test
import to.grindelf.earleyparserkotlin.Rule
import to.grindelf.earleyparserkotlin.Structure

class StructureTest {

    @Test
    fun testStructurePrint() {
        val structure = Structure(
            Rule("T", "F*T"),
            1,
            0
        )

        println(structure)
    }

}