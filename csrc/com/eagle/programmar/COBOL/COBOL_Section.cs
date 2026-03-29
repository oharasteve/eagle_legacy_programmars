// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.COBOL
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using COBOL_Section_Definition = com.eagle.programmar.COBOL.Symbols.COBOL_Section_Definition;
	using COBOL_Keyword = com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
	using COBOL_Number = com.eagle.programmar.COBOL.Terminals.COBOL_Number;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationPeriod = com.eagle.tokens.punctuation.PunctuationPeriod;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class COBOL_Section : TokenSequence, EagleRunnable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT COBOL_SectionHeader sectionHeader;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.TokenList<COBOL_Paragraph> paragraphs;
		public TokenList<COBOL_Paragraph> paragraphs;

		public class COBOL_SectionHeader : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Symbols.COBOL_Section_Definition sectionName;
			public COBOL_Section_Definition sectionName;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword SECTION = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("SECTION");
			public COBOL_Keyword SECTION = new COBOL_Keyword("SECTION");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT COBOL_Number number;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationPeriod dot;
			public PunctuationPeriod dot;
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			interpreter.tryToInterpret(paragraphs.first());
		}

		public virtual void transform(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			foreach (COBOL_Paragraph para in paragraphs._elements)
			{
				para.transform(transformer, generator);
			}
		}
	}
}
