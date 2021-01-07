// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 11, 2015

package com.eagle.programmar.CMacro.Terminals;

import org.junit.Test;

import com.eagle.parsers.EagleFileReader;

import junit.framework.TestCase;

public class CMacro_Definition_Test extends TestCase
{
	@Test
	public void testOneLiner()
	{
		EagleFileReader lines = new EagleFileReader();
		lines.add(" int a;", "none", 0);
		lines.add("#define MAX alphabet", "none", 1);
		lines.add(" int b;", "none", 2);
		lines.setCurrentLine(1);
		lines.setCurrentChar(12);
		
		CMacro_Definition definition = new CMacro_Definition();
		assertTrue(definition.parse(lines));
		assertEquals("alphabet", definition.getValue());
	}
	
	@Test
	public void testTwoLines()
	{
		EagleFileReader lines = new EagleFileReader();
		lines.add(" int a;", "none", 0);
		lines.add("#define MAX alphabet \\   ", "none", 1);
		lines.add(" soup ", "none", 2);
		lines.add(" int b;", "none", 3);
		lines.setCurrentLine(1);
		lines.setCurrentChar(12);
		
		CMacro_Definition definition = new CMacro_Definition();
		assertTrue(definition.parse(lines));
		assertEquals("alphabet \\\n soup", definition.getValue());
		assertEquals(1, definition._currentLine);
		assertEquals(13, definition._currentChar);
		assertEquals(2, definition._endLine);
		assertEquals(6, definition._endChar);
	}
	
	@Test
	public void testThreeLines()
	{
		EagleFileReader lines = new EagleFileReader();
		lines.add(" int a;", "none", 0);
		lines.add("#define MAX alpha \\", "none", 1);
		lines.add("beta \\", "none", 2);
		lines.add(" gamma \\", "none", 3);
		lines.setCurrentLine(1);
		lines.setCurrentChar(12);
		
		CMacro_Definition definition = new CMacro_Definition();
		assertTrue(definition.parse(lines));
		assertEquals("alpha \\\nbeta \\\n gamma \\\n", definition.getValue());
	}
}
