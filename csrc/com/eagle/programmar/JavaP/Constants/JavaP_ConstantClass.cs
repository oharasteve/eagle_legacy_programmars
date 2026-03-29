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
	using JavaP_Symbol_Reference = com.eagle.programmar.JavaP.Symbols.JavaP_Symbol_Reference;
	using JavaP_KeywordChoice = com.eagle.programmar.JavaP.Terminals.JavaP_KeywordChoice;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class JavaP_ConstantClass : TokenSequence, JavaP_ConstantShowable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.JavaP.Terminals.JavaP_KeywordChoice CLASS = new com.eagle.programmar.JavaP.Terminals.JavaP_KeywordChoice("class", "Class");
		public JavaP_KeywordChoice CLASS = new JavaP_KeywordChoice("class", "Class");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.JavaP.Symbols.JavaP_Symbol_Reference cls;
		public JavaP_Symbol_Reference cls;

		public virtual string showConstant()
		{
			return cls.showName();
		}
	}

}
