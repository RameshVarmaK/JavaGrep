package com.ramesh.JavaGrep.matcher.custom;

import org.junit.Test;

import static org.junit.Assert.*;

public class AutomataBasedMatcherTypeTest {

    @Test
    public void pattern1() {

        AutomataBasedMatcher matcher = new AutomataBasedMatcher("((abc)*a)+c*b.");

        assertTrue(matcher.isMatched("abg"));
        assertTrue(matcher.isMatched("abcacbg"));
        assertTrue(matcher.isMatched("abg"));
        assertTrue(matcher.isMatched("abcacccccccbg"));
        assertTrue(matcher.isMatched("abcabg"));
        assertTrue(matcher.isMatched("accccccbh"));
        assertTrue(matcher.isMatched("abcaacccbg"));

        assertFalse(matcher.isMatched("a"));
        assertFalse(matcher.isMatched("ab"));
        assertFalse(matcher.isMatched("abhkss"));
        assertFalse(matcher.isMatched("abcbhj"));
        assertFalse(matcher.isMatched("abcbhjjnjn"));
        assertFalse(matcher.isMatched("acgf"));
        assertFalse(matcher.isMatched("abcbhj"));
        assertFalse(matcher.isMatched("cbj"));

    }
    @Test
    public void pattern2(){
        AutomataBasedMatcher matcher = new AutomataBasedMatcher("a*");

        assertTrue(matcher.isMatched(""));
        assertTrue(matcher.isMatched("a"));
        assertTrue(matcher.isMatched("aaaaaaaaaaaaaa"));
        assertFalse(matcher.isMatched("b"));
    }
    @Test
    public void pattern3(){
        AutomataBasedMatcher matcher = new AutomataBasedMatcher("a+");

        assertFalse(matcher.isMatched(""));
        assertTrue(matcher.isMatched("a"));
        assertTrue(matcher.isMatched("aaaaaaaaaaaaaa"));
        assertFalse(matcher.isMatched("b"));
    }
    @Test
    public void pattern4(){
        AutomataBasedMatcher matcher = new AutomataBasedMatcher("a.");

        assertTrue(matcher.isMatched("av"));
        assertFalse(matcher.isMatched("a"));
        assertFalse(matcher.isMatched("aaaaaaaaaaaaaa"));
        assertFalse(matcher.isMatched("b"));
    }
    @Test
    public void pattern5(){
        AutomataBasedMatcher matcher = new AutomataBasedMatcher(".");

        assertFalse(matcher.isMatched(""));
        assertTrue(matcher.isMatched("a"));
        assertFalse(matcher.isMatched("aaaaaaaaaaaaaa"));
        assertTrue(matcher.isMatched("b"));
    }
    @Test
    public void pattern6(){
        AutomataBasedMatcher matcher = new AutomataBasedMatcher("a?");

        assertTrue(matcher.isMatched(""));
        assertTrue(matcher.isMatched("a"));
        assertFalse(matcher.isMatched("aaaaaaaaaaaaaa"));
        assertFalse(matcher.isMatched("b"));
    }

    @Test
    public void test1() {
        AutomataBasedMatcher matcher = new AutomataBasedMatcher("(ab)*(ac)*");
        assertTrue(matcher.isMatched("ab"));
        assertTrue(matcher.isMatched("ac"));
        assertFalse(matcher.isMatched("acab"));
        assertTrue(matcher.isMatched(""));
    }
}