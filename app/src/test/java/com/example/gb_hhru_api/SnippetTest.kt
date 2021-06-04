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
}