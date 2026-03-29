// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 25, 2015

namespace com.eagle.programmar.JavaP.Constants
{
	using JavaP_ConstantShowable = com.eagle.programmar.JavaP.Statements.JavaP_ConstantPool.JavaP_ConstantShowable;
	using JavaP_Symbol_Reference = com.eagle.programmar.JavaP.Symbols.JavaP_Symbol_Reference;
	using JavaP_KeywordChoice = com.eagle.programmar.JavaP.Terminals.JavaP_KeywordChoice;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationPeriod = com.eagle.tokens.punctuation.PunctuationPeriod;

	public class JavaP_ConstantMethodRef : TokenSequence, JavaP_ConstantShowable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.JavaP.Terminals.JavaP_KeywordChoice METHODREF = new com.eagle.programmar.JavaP.Terminals.JavaP_KeywordChoice("Field", "Fieldref", "InterfaceMethod", "InterfaceMethodref", "Method", "Methodref");
		public JavaP_KeywordChoice METHODREF = new JavaP_KeywordChoice("Field", "Fieldref", "InterfaceMethod", "InterfaceMethodref", "Method", "Methodref");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.JavaP.Symbols.JavaP_Symbol_Reference object;
		public JavaP_Symbol_Reference @object;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationPeriod dot;
		public PunctuationPeriod dot;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.JavaP.Symbols.JavaP_Symbol_Reference field;
		public JavaP_Symbol_Reference field;

		public virtual string showConstant()
		{
			return @object.showName() + " . " + field.showName();
		}
	}

}
