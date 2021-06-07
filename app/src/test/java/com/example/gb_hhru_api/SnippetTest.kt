package com.example.gb_hhru_api

import com.example.gb_hhru_api.mvp.entity.Snippet
import org.junit.Assert.*
import org.junit.Test

class SnippetTest {
    @Test
    fun snippet_shortRequirementNotCut_ReturnEquals() {
        val requirement = "short requirement"
        val snippet = Snippet(requirement, null)
        assertEquals(requirement, snippet.requirementShort())
    }

    @Test
    fun snippet_longRequirementCut_ReturnNotEquals() {
        val requirement = "12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890"
        val snippet = Snippet(requirement, null)
        assertNotEquals(requirement, snippet.requirementShort())
    }

    @Test
    fun snippet_shortResponsibilityNotCut_ReturnEquals() {
        val responsibility = "short responsibility"
        val snippet = Snippet(null, responsibility)
        assertEquals(responsibility, snippet.responsibilityShort())
    }

    @Test
    fun snippet_longResponsibilityCut_ReturnNotEquals() {
        val responsibility = "12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890"
        val snippet = Snippet(null, responsibility)
        assertNotEquals(responsibility, snippet.responsibilityShort())
    }

    @Test
    fun snippet_shortNull_ReturnsNotNull() {
        val snippet = Snippet(null, null)
        assertNotNull(snippet.requirementShort())
    }

    @Test
    fun snippet_longRequirement_ReturnsEquals() {
        val requirement = "12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890"
        val snippet = Snippet(requirement, null)
        assertEquals(requirement, snippet.requirementLong())
    }

    @Test
    fun snippet_longRequirementNotEqualsShort_ReturnsNotEquals() {
        val requirement = "12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890"
        val snippet = Snippet(requirement, null)
        assertNotEquals(snippet.requirementShort(), snippet.requirementLong())
    }

    @Test
    fun snippet_shortsAsArray_ReturnsArraaEquals() {
        val requirement = "short requirement"
        val responsibility = "short responsibility"
        val snippet = Snippet(requirement, responsibility)
        assertArrayEquals(arrayOf(requirement, responsibility), snippet.shortsAsArray())
    }

    @Test
    fun snippet_nullAsRequirementLong_ReturnsNull() {
        val snippet = Snippet(null, null)
        assertNull(snippet.requirementLong())
    }

    @Test
    fun snippet_nullAsResponsibilityLong_ReturnsNull() {
        val snippet = Snippet(null, null)
        assertNull(snippet.responsibilityLong())
    }

    @Test
    fun snippet_nullsAsArray_ReturnsNotNull() {
        val snippet = Snippet(null, null)
        assertNotNull(snippet.shortsAsArray())
    }

    @Test
    fun snippet_longRequirementTheSame_ReturnsSame() {
        val requirement = "some requirement"
        val snippet = Snippet(requirement, null)
        assertSame(requirement, snippet.requirementLong())
    }

    @Test
    fun snippet_longResponsibilityTheSame_ReturnsSame() {
        val responsibility = "some responsibility"
        val snippet = Snippet(null, responsibility)
        assertSame(responsibility, snippet.responsibilityLong())
    }
}