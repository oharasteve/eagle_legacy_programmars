// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Feb 13, 2024

namespace com.eagle.programmar.Eaglish
{

	using AbstractLanguage = com.eagle.core.AbstractLanguage;
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Eaglish_Function = com.eagle.programmar.Eaglish.Statements.Eaglish_Function;
	using Eaglish_Program_Identifier = com.eagle.programmar.Eaglish.Symbols.Eaglish_Program_Identifier;
	using Eaglish_CommentEoln = com.eagle.programmar.Eaglish.Terminals.Eaglish_CommentEoln;
	using Eaglish_EndOfLine = com.eagle.programmar.Eaglish.Terminals.Eaglish_EndOfLine;
	using Eaglish_Keyword = com.eagle.programmar.Eaglish.Terminals.Eaglish_Keyword;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using EagleTransformableProgram = com.eagle.transform.EagleTransformableProgram;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Eaglish_Program : AbstractLanguage, EagleRunnable, EagleTransformableProgram
	{
		public const string EAGLISH = "Eaglish";

		public Eaglish_Program() : base(EAGLISH, new Eaglish_Syntax())
		{
		}

		public override string DocRoot
		{
			get
			{
				return "Unknown";
			}
		}

		// Components of an Eaglish Program
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT TokenList<com.eagle.programmar.Eaglish.Terminals.Eaglish_CommentEoln> comments1;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) Eaglish_Prog prog;
		public Eaglish_Prog prog;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT TokenList<com.eagle.programmar.Eaglish.Terminals.Eaglish_CommentEoln> comments2;
		public  OPT;

		public class Eaglish_Prog : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Eaglish.Terminals.Eaglish_Keyword PROGRAM = new com.eagle.programmar.Eaglish.Terminals.Eaglish_Keyword("PROGRAM");
			public Eaglish_Keyword PROGRAM = new Eaglish_Keyword("PROGRAM");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Eaglish.Symbols.Eaglish_Program_Identifier id;
			public Eaglish_Program_Identifier id;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Eaglish.Terminals.Eaglish_EndOfLine eoln1;
			public Eaglish_EndOfLine eoln1;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.TokenList<Eaglish_Statement> statements;
			public TokenList<Eaglish_Statement> statements;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.Eaglish.Terminals.Eaglish_Keyword END_PROGRAM = new com.eagle.programmar.Eaglish.Terminals.Eaglish_Keyword("END_PROGRAM");
			public Eaglish_Keyword END_PROGRAM = new Eaglish_Keyword("END_PROGRAM");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.programmar.Eaglish.Terminals.Eaglish_EndOfLine eoln2;
			public Eaglish_EndOfLine eoln2;
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			// First pass, just collect all the FUNCTION definitions
			foreach (Eaglish_Statement stmt in prog.statements._elements)
			{
				AbstractToken which = stmt.getWhich();
				if (which is Eaglish_Function)
				{
					Eaglish_Function fn = (Eaglish_Function) which;
					interpreter.addFunction(fn.id.getValue(), fn);
				}
			}

			// Second pass, execute the program
			foreach (Eaglish_Statement stmt in prog.statements._elements)
			{
				interpreter.tryToInterpret(stmt);
			}
		}

		public override AbstractLanguage transformProgram(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			// Transform all the Function definitions first
			foreach (Eaglish_Statement stmt in prog.statements._elements)
			{
				AbstractToken whichStmt = stmt.getWhich();
				if (whichStmt is Eaglish_Function)
				{
					Eaglish_Function func = (Eaglish_Function) whichStmt;
					func.transformFunction(transformer, generator);
				}
			}

			// Transform all the global data and logic, etc.
			foreach (Eaglish_Statement stmt in prog.statements._elements)
			{
				AbstractToken whichStmt = stmt.getWhich();
				ICollection<AbstractStatement> newStmts = transformer.transformStatement(generator, whichStmt);
				if (newStmts != null)
				{
					foreach (AbstractStatement newStmt in newStmts)
					{
						generator.addStatement(newStmt, whichStmt);
					}
				}
			}

			return generator.getTransfomedProgram();
		}
	}

}
