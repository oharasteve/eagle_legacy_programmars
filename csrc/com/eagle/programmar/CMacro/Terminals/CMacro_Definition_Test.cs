// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 11, 2015

namespace com.eagle.programmar.CMacro.Terminals
{
	using Test = org.junit.Test;

	using EagleFileReader = com.eagle.parsers.EagleFileReader;

	using TestCase = junit.framework.TestCase;

	public class CMacro_Definition_Test : TestCase
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: @Test public void testOneLiner()
		public virtual void testOneLiner()
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

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: @Test public void testTwoLines()
		public virtual void testTwoLines()
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
			assertEquals(1, definition.getStartLine());
			assertEquals(13, definition.getStartChar());
			assertEquals(2, definition.getEndLine());
			assertEquals(6, definition.getEndChar());
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: @Test public void testThreeLines()
		public virtual void testThreeLines()
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

}
