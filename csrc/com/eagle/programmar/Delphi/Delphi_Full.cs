// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 19, 2024

namespace com.eagle.programmar.Delphi
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Delphi_BeginEnd = com.eagle.programmar.Delphi.Statements.Delphi_BeginEnd;
	using Delphi_Program_Definition = com.eagle.programmar.Delphi.Symbols.Delphi_Program_Definition;
	using Delphi_Comment = com.eagle.programmar.Delphi.Terminals.Delphi_Comment;
	using Delphi_Keyword = com.eagle.programmar.Delphi.Terminals.Delphi_Keyword;
	using Delphi_KeywordChoice = com.eagle.programmar.Delphi.Terminals.Delphi_KeywordChoice;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationPeriod = com.eagle.tokens.punctuation.PunctuationPeriod;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Delphi_Full : TokenSequence, EagleRunnable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT TokenList<com.eagle.programmar.Delphi.Terminals.Delphi_Comment> comments1;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @DOC("Programs_and_Units_(Delphi)#The_Program_Heading") com.eagle.programmar.Delphi.Terminals.Delphi_KeywordChoice programOrUnit = new com.eagle.programmar.Delphi.Terminals.Delphi_KeywordChoice("Program", "Unit");
		public @DOC("Programs_and_Units_(Delphi)#The_Program_Heading") Delphi_KeywordChoice programOrUnit = new Delphi_KeywordChoice("Program", "Unit");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Delphi.Symbols.Delphi_Program_Definition id;
		public Delphi_Program_Definition id;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationSemicolon semicolon;
		public PunctuationSemicolon semicolon;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT TokenList<Delphi_Header> headers;
		public @OPT TokenList<Delphi_Header> headers;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT Delphi_BeginEnd beginEnd;
		public @OPT Delphi_BeginEnd beginEnd;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @OPT Delphi_Keyword END = new com.eagle.programmar.Delphi.Terminals.Delphi_Keyword("End");
		public @OPT Delphi_Keyword END = new Delphi_Keyword("End");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) com.eagle.tokens.punctuation.PunctuationPeriod dot;
		public PunctuationPeriod dot;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(90) @OPT TokenList<com.eagle.programmar.Delphi.Terminals.Delphi_Comment> comments2;
		public @OPT TokenList<Delphi_Comment> comments2;

		public Delphi_Full()
		{
			base();
		}

		public void interpret(EagleInterpreter interpreter)
		{
			// First pass, just collect all the FUNCTION definitions
			foreach (Delphi_Header element in headers._elements)
			{
				AbstractToken which = element.getWhich();
				if (which is Delphi_Procedure)
				{
					Delphi_Procedure proc = (Delphi_Procedure) which;
					interpreter.addFunction(proc.forward.id.getValue(), proc);
				}
				else if (which is Delphi_Function)
				{
					Delphi_Function fn = (Delphi_Function) which;
					interpreter.addFunction(fn.forward.id.getValue(), fn);
				}
			}

			// Second pass, execute the program
			foreach (Delphi_Header header in headers._elements)
			{
				interpreter.tryToInterpret(header.getWhich());
			}
			if (beginEnd.isPresent())
			{
				interpreter.tryToInterpret(beginEnd);
			}
		}

		public void transformFull(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			foreach (Delphi_Header header in this.headers._elements)
			{
				header.processHeader(transformer, generator);
			}

			this.beginEnd.statements.transformRemoveBeginEnd(transformer, generator);
		}
	}

}
