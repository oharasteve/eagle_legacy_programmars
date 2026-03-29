// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 23, 2015

namespace com.eagle.programmar.JavaP.Constants
{
	using JavaP_ConstantShowable = com.eagle.programmar.JavaP.Statements.JavaP_ConstantPool.JavaP_ConstantShowable;
	using JavaP_KeywordChoice = com.eagle.programmar.JavaP.Terminals.JavaP_KeywordChoice;
	using JavaP_RestOfLine = com.eagle.programmar.JavaP.Terminals.JavaP_RestOfLine;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class JavaP_ConstantUtf8 : TokenSequence, JavaP_ConstantShowable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.JavaP.Terminals.JavaP_KeywordChoice UTF8 = new com.eagle.programmar.JavaP.Terminals.JavaP_KeywordChoice("Asciz", "Utf8");
		public JavaP_KeywordChoice UTF8 = new JavaP_KeywordChoice("Asciz", "Utf8");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT JavaP_RestOfLine value;
		public  OPT;

		public virtual string showConstant()
		{
			return value.ToString();
		}
	}

}
