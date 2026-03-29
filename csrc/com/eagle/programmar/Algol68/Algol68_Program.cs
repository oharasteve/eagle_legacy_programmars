// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

namespace com.eagle.programmar.Algol68
{

	using AbstractLanguage = com.eagle.core.AbstractLanguage;
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Algol68_Data = com.eagle.programmar.Algol68.Statements.Algol68_Data;
	using Algol68_Procedure = com.eagle.programmar.Algol68.Statements.Algol68_Procedure;
	using Algol68_Keyword = com.eagle.programmar.Algol68.Terminals.Algol68_Keyword;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationColon = com.eagle.tokens.punctuation.PunctuationColon;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using EagleTransformableFunction = com.eagle.transform.EagleTransformableFunction;
	using EagleTransformableProgram = com.eagle.transform.EagleTransformableProgram;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Algol68_Program : AbstractLanguage, EagleRunnable, EagleTransformableProgram
	{
		public const string ALGOL68 = "Algol68";

		public Algol68_Program() : base(ALGOL68, new Algol68_Syntax())
		{
		}

		public override string booleanName(bool flag)
		{
			if (flag)
			{
				return "TRUE";
			}
			return "FALSE";
		}

		public override string DocRoot
		{
			get
			{
				return "https://jmvdveer.home.xs4all.nl/learning-algol-68-genie.pdf";
			}
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.TokenList<Algol68_TopElement> elements;
		public TokenList<Algol68_TopElement> elements;

		public class Algol68_Main : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Algol68.Terminals.Algol68_Keyword MAIN = new com.eagle.programmar.Algol68.Terminals.Algol68_Keyword("MAIN");
			public Algol68_Keyword MAIN = new Algol68_Keyword("MAIN");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationColon colon;
			public PunctuationColon colon;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
			public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.TokenList<Algol68_Statement> statements;
			public TokenList<Algol68_Statement> statements;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
			public PunctuationRightParen rightParen;
		}

		public class Algol68_TopElement : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Algol68_Statement XXstatement;
			public Algol68_Statement XXstatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Algol68_Main XXmain;
			public Algol68_Main XXmain;
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			// First pass, just collect all the Procedure definitions
			foreach (Algol68_TopElement element in elements._elements)
			{
				AbstractToken which1 = element.getWhich();
				if (which1 is Algol68_Statement)
				{
					Algol68_Statement stmt = (Algol68_Statement) which1;
					AbstractToken which2 = stmt.getWhich();
					if (which2 is Algol68_Procedure)
					{
						Algol68_Procedure fn = (Algol68_Procedure) which2;
						interpreter.addFunction(fn.id.getValue(), fn);
					}
					else if (which2 is Algol68_Statement)
					{
						Algol68_Data stmt2 = (Algol68_Data) which2;
						interpreter.tryToInterpret(stmt2);
					}
				}
			}

			// Second pass, execute the program
			foreach (Algol68_TopElement element in elements._elements)
			{
				AbstractToken which = element.getWhich();
				if (which is Algol68_Main)
				{
					Algol68_Main main = (Algol68_Main) which;
					foreach (Algol68_Statement stmt1 in main.statements._elements)
					{
						interpreter.tryToInterpret(stmt1);
					}
				}
				else if (which is Algol68_Statement)
				{
					Algol68_Statement stmt2 = (Algol68_Statement) which;
					interpreter.tryToInterpret(stmt2);
				}
			}
		}

		public override AbstractLanguage transformProgram(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			// First pass, just collect all the Procedure definitions
			foreach (Algol68_TopElement element in elements._elements)
			{
				AbstractToken which = element.getWhich();
				if (which is Algol68_Statement)
				{
					Algol68_Statement stmt = (Algol68_Statement) which;
					if (stmt.getWhich() is EagleTransformableFunction)
					{
						EagleTransformableFunction transformable = (EagleTransformableFunction) stmt.getWhich();
						transformable.transformFunction(transformer, generator);
					}
				}
			}

			// Second pass, collect the main procedure and other stuff
			foreach (Algol68_TopElement elt in elements._elements)
			{
				AbstractToken whichElt = elt.getWhich();
				if (whichElt is Algol68_Main)
				{
					Algol68_Main main = (Algol68_Main) whichElt;
					foreach (Algol68_Statement stmt in main.statements._elements)
					{
						ICollection<AbstractStatement> newStmts = transformer.transformStatement(generator, stmt.getWhich());
						if (newStmts != null)
						{
							foreach (AbstractStatement newStmt in newStmts)
							{
								generator.addStatement(newStmt, stmt);
							}
						}
					}
				}
				else if (whichElt is Algol68_Statement)
				{
					Algol68_Statement stmt = (Algol68_Statement) whichElt;
					ICollection<AbstractStatement> newStmts = transformer.transformStatement(generator, stmt.getWhich());
					if (newStmts != null)
					{
						foreach (AbstractStatement newStmt in newStmts)
						{
							generator.addStatement(newStmt, stmt);
						}
					}
				}
				else
				{
					throw new Exception("Unable to handle: " + whichElt);
				}
			}

			return generator.getTransfomedProgram();
		}
	}

}
