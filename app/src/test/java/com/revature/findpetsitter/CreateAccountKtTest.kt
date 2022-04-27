package com.revature.findpetsitter

import junit.framework.TestCase
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test



class CreateAccountKtTest : TestCase() {

    @Test
    fun empty_username_returns_false() {
        // Pass the value to the function of AccountUtil class
        // since AccountUtil is an object/ singleton we do not need to create its object
        val result = AccountUtil.validAccountInput(
            "",
            "123",
            "123"
        )
        // assertThat() comes from the truth library that we added earlier
        // put result in it and assign the boolean that it should return
        assertThat("$result", false)
    }
}