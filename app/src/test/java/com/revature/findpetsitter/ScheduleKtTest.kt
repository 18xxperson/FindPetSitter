package com.revature.findpetsitter

import com.revature.findpetsitter.ui.dateRangeIsCorrect
import junit.framework.Assert.assertTrue
import org.junit.Test

class ScheduleKtTest  {
    @Test
    fun testDateRange() {
        //start must be greater than today's date
        //end date must be greater than start date
        var result = dateRangeIsCorrect(start = "5/17/2022",end="5/18/2022")
        assertTrue(result)
    }
}