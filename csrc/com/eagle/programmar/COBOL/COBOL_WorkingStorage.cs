// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jun 7, 2025

namespace com.eagle.programmar.COBOL
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using COBOL_Keyword = com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationPeriod = com.eagle.tokens.punctuation.PunctuationPeriod;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class COBOL_WorkingStorage : TokenSequence, EagleRunnable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword WORKINGSTORAGE = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("WORKING-STORAGE");
		public COBOL_Keyword WORKINGSTORAGE = new COBOL_Keyword("WORKING-STORAGE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword SECTION = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("SECTION");
		public COBOL_Keyword SECTION = new COBOL_Keyword("SECTION");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationPeriod dot;
		public PunctuationPeriod dot;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.TokenList<COBOL_CopyOrDataDeclaration> dataDeclarations;
		public TokenList<COBOL_CopyOrDataDeclaration> dataDeclarations;

		public class COBOL_CopyOrDataDeclaration : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_Copy_Directive XXcopyBook;
			public COBOL_Copy_Directive XXcopyBook;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_DataDeclaration XXdeclaration;
			public COBOL_DataDeclaration XXdeclaration;
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			foreach (COBOL_CopyOrDataDeclaration decl in dataDeclarations._elements)
			{
				interpreter.tryToInterpret(decl);
			}
		}

		public virtual void transform(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			foreach (COBOL_CopyOrDataDeclaration decl in dataDeclarations._elements)
			{
				AbstractToken which = decl.getWhich();
				if (which is COBOL_DataDeclaration)
				{
					COBOL_DataDeclaration data = (COBOL_DataDeclaration) which;
					data.transform(transformer, generator);
				}
			}
		}
	}

}
