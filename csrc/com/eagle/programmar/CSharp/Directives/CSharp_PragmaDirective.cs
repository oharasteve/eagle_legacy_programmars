// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 17, 2022

namespace com.eagle.programmar.CSharp.Directives
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using CSharp_Expression = com.eagle.programmar.CSharp.CSharp_Expression;
	using CSharp_Keyword = com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
	using CSharp_KeywordChoice = com.eagle.programmar.CSharp.Terminals.CSharp_KeywordChoice;
	using CSharp_Punctuation = com.eagle.programmar.CSharp.Terminals.CSharp_Punctuation;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using EagleTransformableStatement = com.eagle.transform.EagleTransformableStatement;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class CSharp_PragmaDirective : TokenSequence, EagleRunnable, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CSharp.Terminals.CSharp_Punctuation pound1 = new com.eagle.programmar.CSharp.Terminals.CSharp_Punctuation('#');
		public CSharp_Punctuation pound1 = new CSharp_Punctuation('#');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.CSharp.Terminals.CSharp_Keyword PRAGMA = new com.eagle.programmar.CSharp.Terminals.CSharp_Keyword("pragma");
		public CSharp_Keyword PRAGMA = new CSharp_Keyword("pragma");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.CSharp.Terminals.CSharp_Keyword WARNING = new com.eagle.programmar.CSharp.Terminals.CSharp_Keyword("warning");
		public CSharp_Keyword WARNING = new CSharp_Keyword("warning");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.CSharp.Terminals.CSharp_KeywordChoice DISABLE = new com.eagle.programmar.CSharp.Terminals.CSharp_KeywordChoice("disable", "restore");
		public CSharp_KeywordChoice DISABLE = new CSharp_KeywordChoice("disable", "restore");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.SeparatedList<com.eagle.programmar.CSharp.CSharp_Expression, com.eagle.tokens.punctuation.PunctuationComma> numbers;
		public SeparatedList<CSharp_Expression, PunctuationComma> numbers;

		public override void interpret(EagleInterpreter interpreter)
		{
			// Nothing important here
		}

		public override AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			// Nothing important here
			return null;
		}
	}

}
