package com.revature.findpetsitter

import com.revature.findpetsitter.data.Sitter
import org.junit.Assert.assertEquals
import org.junit.Test

class ListViewUnitTest {
    @Test
    fun firstTestList()
    {
        val sitterlist= listOf(
            Sitter(id=10,"Jon","Work","HouseSitter",4.0,70.0),
            Sitter(id=100,"Sally","Jones","HouseSitter",3.0,75.0),
            Sitter(id=300,"Samantha","Jones","HouseSitter",3.0,80.0),
            Sitter(id=200,"Sally","Smith","HouseSitter",4.0,70.0),
            Sitter(id=15,"Bob","Smith","Kennel Service",4.6,78.0),
            Sitter(id=115,"Sherry","Work","Kennel Service",3.5,85.0))
        val filterlist=sitterlist.filter { it.type=="HouseSitter" }
        assertEquals(filterlist, listOf(
            Sitter(id=10,"Jon","Work","HouseSitter",4.0,70.0),
            Sitter(id=100,"Sally","Jones","HouseSitter",3.0,75.0),
            Sitter(id=300,"Samantha","Jones","HouseSitter",3.0,80.0),
            Sitter(id=200,"Sally","Smith","HouseSitter",4.0,70.0)))
    }
    @Test
    fun secondTestList()
    {
        val sitterlist= listOf(
            Sitter(id=10,"Jon","Work","HouseSitter",4.0,70.0),
            Sitter(id=100,"Sally","Jones","HouseSitter",3.0,75.0),
            Sitter(id=300,"Samantha","Jones","HouseSitter",3.0,80.0),
            Sitter(id=200,"Sally","Smith","HouseSitter",4.0,70.0),
            Sitter(id=15,"Bob","Smith","Kennel Service",4.6,78.0),
            Sitter(id=115,"Sherry","Work","Kennel Service",3.5,85.0))
        val filterlist=sitterlist.filter { it.type=="Kennel Service" }
        assertEquals(filterlist, listOf(
            Sitter(id=15,"Bob","Smith","Kennel Service",4.6,78.0),
            Sitter(id=115,"Sherry","Work","Kennel Service",3.5,85.0)))
    }
}