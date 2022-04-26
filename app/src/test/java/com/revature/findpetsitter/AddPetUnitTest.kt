package com.revature.findpetsitter

import com.revature.findpetsitter.data.User
import junit.framework.Assert.assertEquals
import org.junit.Test

class AddPetUnitTest {
    @Test
    fun firstAttemptAddPet()
    {
        val user= User(name = "Jonathan Work",email="jonwork@gmail.com")
        val petname="Shadow"
        val pettype="cat"
        val petdes="black"
        if(petdes!=""&&pettype!=""&&petname!="")
        {
            user.pets++
        }
        assertEquals(user.pets,1)
    }
    @Test
    fun secondAttemptAddPet()
    {
        val user= User(name = "Jonathan Work",email="jonwork@gmail.com")
        val petname="Shadow"
        val pettype="cat"
        val petdes=""
        if(petdes!=""&&pettype!=""&&petname!="")
        {
            user.pets++
        }
        assertEquals(user.pets,0)
    }
    @Test
    fun thirdAttemptAddPet()
    {
        val user= User(name = "Jonathan Work",email="jonwork@gmail.com")
        val petname="Shadow"
        val pettype=""
        val petdes="black"
        if(petdes!=""&&pettype!=""&&petname!="")
        {
            user.pets++
        }
        assertEquals(user.pets,0)
    }
    @Test
    fun fourthAttemptAddPet()
    {
        val user= User(name = "Jonathan Work",email="jonwork@gmail.com")
        val petname=""
        val pettype="dog"
        val petdes="black"
        if(petdes!=""&&pettype!=""&&petname!="")
        {
            user.pets++
        }
        assertEquals(user.pets,0)
    }
}