package ip.shalimov.utils

import junit.framework.TestCase.assertEquals
import org.junit.Test

class MathTest {

    @Test
    fun testSum() {
        assertEquals(3, Math.sum(1, 2))
        assertEquals(0, Math.sum(0, 0))
        assertEquals(-1, Math.sum(-1, 0))
        assertEquals(-3, Math.sum(-1, -2))
        assertEquals(Int.MAX_VALUE, Math.sum(Int.MAX_VALUE, 0))
        assertEquals(Int.MIN_VALUE, Math.sum(Int.MIN_VALUE, 0))
    }
}