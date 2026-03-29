// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 31, 2015

namespace com.eagle.programmar.CMacro
{
	using Test = org.junit.Test;

	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using CMacro_Preprocess = com.eagle.preprocess.CMacro.CMacro_Preprocess;
	using CMacro_RestOfLine = com.eagle.programmar.CMacro.Terminals.CMacro_RestOfLine;

	using TestCase = junit.framework.TestCase;

	public class CMacro_Tests : TestCase
	{
		private const string DEFINE = "#define ABC ";

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: @Test public void testSimpleMacro1()
		public virtual void testSimpleMacro1()
		{
			string[] define = new string[] {DEFINE + "(56 + 75)// comment"};
			check(define, "(56 + 75)");
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: @Test public void testSimpleMacro2()
		public virtual void testSimpleMacro2()
		{
			string[] define = new string[] {DEFINE + "(56 * 75)  /*ABC*/"};
			check(define, "(56 * 75)  ");
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: @Test public void testContinuedComment1()
		public virtual void testContinuedComment1()
		{
			string[] define = new string[] {DEFINE + "(56 * 75)  /*ABC", "DEF*/", "int i;"};
			check(define, "(56 * 75)  ");
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: @Test public void testMultilineMacro1()
		public virtual void testMultilineMacro1()
		{
			string[] define = new string[] {DEFINE + "(1 + 2 + 3 \\", "   4 + 5 + 6 \\", "7 + 8 + 9)"};
			check(define, "(1 + 2 + 3 \n   4 + 5 + 6 \n7 + 8 + 9)");
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: @Test public void testMultilineMacro2()
		public virtual void testMultilineMacro2()
		{
			string[] define = new string[] {DEFINE + "(1 + 2 + 3 \\", "   4 +/*COMMENT*/5 + 6 \\", "7 + 8 + 9)"};
			check(define, "(1 + 2 + 3 \n   4 +/*COMMENT*/5 + 6 \n7 + 8 + 9)");
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: @Test public void testEndsWithContinuedComment()
		public virtual void testEndsWithContinuedComment()
		{
			string[] define = new string[] {DEFINE + "    'u'     /* less than means the cell has a", "             * value */"};
			check(define, "'u'");
		}

		private static void check(string[] inputLines, string expected)
		{
			CMacro_RestOfLine rest = new CMacro_RestOfLine();
			EagleFileReader lines = new EagleFileReader("macro.h", inputLines);
			lines.setCurrentChar(DEFINE.Length);
			lines.setCurrentLine(0);
			bool ok = rest.parse(lines);
			assertTrue("Parse failed", ok);
			assertEquals(expected.Trim(), rest.getValue().Trim());
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: @Test public void testFancyReplace1()
		public virtual void testFancyReplace1()
		{
			string val = "XpmCreatePixmapFromData, int, (Display * display," + "        Drawable d," + "        char **data," + "        Pixmap * pixmap_return," + "        Pixmap * shapemask_return," + "        XpmAttributes * attributes)";
			string[] pieces = CMacro_Preprocess.fancySplit(val);
			assertEquals(3, pieces.Length);
			assertEquals(" int", pieces[1]);
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: @Test public void testFancyReplace2()
		public virtual void testFancyReplace2()
		{
			string val = "\"Output must be a list of variables, like \\\"(!x1!,!x2!,!x3!)\\\".\"";
			string[] pieces = CMacro_Preprocess.fancySplit(val);
			assertEquals(1, pieces.Length);
		}
	}

}
