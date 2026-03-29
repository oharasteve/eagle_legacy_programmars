// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

namespace com.eagle.programmar.FSharp
{

	using AbstractLanguage = com.eagle.core.AbstractLanguage;
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using AssignMetrics = com.eagle.metrics.AssignMetrics;
	using FSharp_Statement = com.eagle.programmar.FSharp.FSharp_Element.FSharp_Statement;
	using FSharp_Statement_List = com.eagle.programmar.FSharp.FSharp_Element.FSharp_Statement_List;
	using FSharp_Function = com.eagle.programmar.FSharp.Statements.FSharp_Function;
	using FSharp_EndOfLine = com.eagle.programmar.FSharp.Terminals.FSharp_EndOfLine;
	using FSharp_Keyword = com.eagle.programmar.FSharp.Terminals.FSharp_Keyword;
	using FSharp_Punctuation = com.eagle.programmar.FSharp.Terminals.FSharp_Punctuation;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationLeftBracket = com.eagle.tokens.punctuation.PunctuationLeftBracket;
	using PunctuationRightBracket = com.eagle.tokens.punctuation.PunctuationRightBracket;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;
	using EagleTransformableProgram = com.eagle.transform.EagleTransformableProgram;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class FSharp_Program : AbstractLanguage, EagleRunnable, EagleTransformableProgram
	{
		public const string FSHARP = "FSharp";

		public FSharp_Program() : base(FSHARP, new FSharp_Syntax())
		{
		}

		public override string DocRoot
		{
			get
			{
				return "https://docs.microsoft.com/en-us/dotnet/fsharp/language-reference/";
			}
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT FSharp_OpenDeclaration open;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT FSharp_EntryPoint entryPoint;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.TokenList<FSharp_Element> elements;
		public TokenList<FSharp_Element> elements;

		public class FSharp_OpenDeclaration : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.FSharp.Terminals.FSharp_Keyword OPEN = new com.eagle.programmar.FSharp.Terminals.FSharp_Keyword("open");
			public FSharp_Keyword OPEN = new FSharp_Keyword("open");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.FSharp.Terminals.FSharp_Keyword SYSTEM = new com.eagle.programmar.FSharp.Terminals.FSharp_Keyword("System");
			public FSharp_Keyword SYSTEM = new FSharp_Keyword("System");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.FSharp.Terminals.FSharp_EndOfLine eoln;
			public FSharp_EndOfLine eoln;
		}

		public class FSharp_EntryPoint : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftBracket leftBracket;
			public PunctuationLeftBracket leftBracket;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.FSharp.Terminals.FSharp_Punctuation lessThan = new com.eagle.programmar.FSharp.Terminals.FSharp_Punctuation("<");
			public FSharp_Punctuation lessThan = new FSharp_Punctuation("<");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.FSharp.Terminals.FSharp_Keyword ENTRYPOINT = new com.eagle.programmar.FSharp.Terminals.FSharp_Keyword("EntryPoint");
			public FSharp_Keyword ENTRYPOINT = new FSharp_Keyword("EntryPoint");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.FSharp.Terminals.FSharp_Punctuation greaterThan = new com.eagle.programmar.FSharp.Terminals.FSharp_Punctuation(">");
			public FSharp_Punctuation greaterThan = new FSharp_Punctuation(">");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.punctuation.PunctuationRightBracket rightBracket;
			public PunctuationRightBracket rightBracket;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.programmar.FSharp.Terminals.FSharp_EndOfLine eoln;
			public FSharp_EndOfLine eoln;
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			// First pass, just collect all the method definitions
			foreach (FSharp_Element element in elements._elements)
			{
				if (element.statementOrComment.getWhich() is FSharp_Statement_List)
				{
					FSharp_Statement_List statements = (FSharp_Statement_List) element.statementOrComment.getWhich();
					for (int i = 0; i < statements.statements.getPrimaryCount(); i++)
					{
						FSharp_Statement stmt = statements.statements.getPrimaryElement(i);
						if (stmt.getWhich() is FSharp_Function)
						{
							FSharp_Function func = (FSharp_Function) stmt.getWhich();
							interpreter.addFunction(func.id.getValue(), func);
						}
					}
				}
			}

			// Second pass, run any stuff in the outermost class
			foreach (FSharp_Element element in elements._elements)
			{
				interpreter.tryToInterpret(element.statementOrComment.getWhich());
			}
		}

		public override AbstractLanguage transformProgram(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			// Are there any global variables we need to declare?
			string scopeStr = this._currentLine + "-" + this._endLine;
			List<AssignMetrics> asgMetrics = transformer._metrics.findVarsInScope(scopeStr);
			foreach (AssignMetrics met in asgMetrics)
			{
				EagleGenerator.TypeEnum typE = met.uniqueType();
				if (typE != EagleGenerator.TypeEnum.VOID)
				{
					AbstractType abstrType = generator.transformType(typE, null, this);
					AbstractExpression initExpr = null;
					// System.err.println("****** Found var " + met._symbolName);
					AbstractStatement dataStmt = generator.newDataDeclaration(false, met._symbolName, null, abstrType, initExpr, this);
					generator.addStatement(dataStmt, this);
				}
			}

			// Transform all the Function definitions and global data
			foreach (FSharp_Element elt in elements._elements)
			{
				AbstractToken which1 = elt.statementOrComment.getWhich();
				if (which1 is FSharp_Statement_List)
				{
					FSharp_Statement_List stmtList = (FSharp_Statement_List) which1;
					for (int i = 0; i < stmtList.statements.getPrimaryCount(); i++)
					{
						FSharp_Statement stmt = stmtList.statements.getPrimaryElement(i);
						AbstractToken which2 = stmt.getWhich();
						if (which2 is FSharp_Function)
						{
							FSharp_Function func = (FSharp_Function) which2;
							func.transformFunction(transformer, generator);
						}
						else
						{
							ICollection<AbstractStatement> newStmts = transformer.transformStatement(generator, which2);
							if (newStmts != null)
							{
								foreach (AbstractStatement newStmt in newStmts)
								{
									generator.addStatement(newStmt, elt);
								}
							}
						}
					}
				}
			}

			return generator.getTransfomedProgram();
		}
	}

}
