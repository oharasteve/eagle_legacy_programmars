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

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleRunnableWithResult = com.eagle.interpret.EagleRunnableWithResult;
	using FSharp_Assignment = com.eagle.programmar.FSharp.Statements.FSharp_Assignment;
	using FSharp_ExpressionStatement = com.eagle.programmar.FSharp.Statements.FSharp_ExpressionStatement;
	using FSharp_ForStatement = com.eagle.programmar.FSharp.Statements.FSharp_ForStatement;
	using FSharp_Function = com.eagle.programmar.FSharp.Statements.FSharp_Function;
	using FSharp_IfStatement = com.eagle.programmar.FSharp.Statements.FSharp_IfStatement;
	using FSharp_LetStatement = com.eagle.programmar.FSharp.Statements.FSharp_LetStatement;
	using FSharp_PrintfnStatement = com.eagle.programmar.FSharp.Statements.FSharp_PrintfnStatement;
	using FSharp_WhileStatement = com.eagle.programmar.FSharp.Statements.FSharp_WhileStatement;
	using FSharp_Comment = com.eagle.programmar.FSharp.Terminals.FSharp_Comment;
	using FSharp_EndOfLine = com.eagle.programmar.FSharp.Terminals.FSharp_EndOfLine;
	using FSharp_Punctuation = com.eagle.programmar.FSharp.Terminals.FSharp_Punctuation;
	using FSharp_StartOfLine = com.eagle.programmar.FSharp.Terminals.FSharp_StartOfLine;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using EagleTransformableStatement = com.eagle.transform.EagleTransformableStatement;
	using EagleTransformableStatementList = com.eagle.transform.EagleTransformableStatementList;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class FSharp_Element : TokenSequence, AbstractStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT FSharp_StartOfLine soln;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) FSharp_StatementOrComment statementOrComment;
		public FSharp_StatementOrComment statementOrComment;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT @CURIOUS("Extra semicolon") com.eagle.tokens.punctuation.PunctuationSemicolon semicolon;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT @CURIOUS("Extra comma") com.eagle.tokens.punctuation.PunctuationComma comma;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT FSharp_Comment comment;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT FSharp_EndOfLine eoln;
		public  OPT;

		public class FSharp_StatementOrComment : TokenChooser
		{
	//		public @SKIP FSharp_MultilineStatement XXmultiStatement; // Only needed for Transformation

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE FSharp_Statement_List XXstatements;
			public FSharp_Statement_List XXstatements;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE FSharp_EndOfLine XXeoln;
			public FSharp_EndOfLine XXeoln;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @FIRST static class FSharp_CommentList extends com.eagle.tokens.TokenSequence implements com.eagle.interpret.EagleRunnable, com.eagle.transform.EagleTransformableStatement
			public class FSharp_CommentList : TokenSequence, EagleRunnable, EagleTransformableStatement
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.SeparatedList<com.eagle.programmar.FSharp.Terminals.FSharp_Comment, com.eagle.programmar.FSharp.Terminals.FSharp_EndOfLine> comments;
				public SeparatedList<FSharp_Comment, FSharp_EndOfLine> comments;

				public override void interpret(EagleInterpreter interpreter)
				{
					// Nothing to do
				}

				public override AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
				{
					return null; // Nothing to do
				}
			}
		}

		public class FSharp_Statement_List : TokenSequence, EagleRunnable, EagleTransformableStatementList
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.FSharp.Terminals.FSharp_StartOfLine soln = new com.eagle.programmar.FSharp.Terminals.FSharp_StartOfLine();
			public FSharp_StartOfLine soln = new FSharp_StartOfLine();
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.SeparatedList<FSharp_Statement, FSharp_Statement_Separator> statements;
			public SeparatedList<FSharp_Statement, FSharp_Statement_Separator> statements;

			public override void interpret(EagleInterpreter interpreter)
			{
				for (int i = 0; i < statements.getPrimaryCount(); i++)
				{
					FSharp_Statement stmt = statements.getPrimaryElement(i);
					interpreter.tryToInterpret(stmt);
				}
			}

			public override List<AbstractStatement> transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
			{
				List<AbstractStatement> result = new List<AbstractStatement>();
				for (int i = 0; i < statements.getPrimaryCount(); i++)
				{
					FSharp_Statement stmt = statements.getPrimaryElement(i);
					result.Add(transformer.transformStatement1(generator, stmt.getWhich()));
				}
				return result;
			}
		}

		public class FSharp_Statement_Separator : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE PunctuationSemicolon XXsemicolon;
			public PunctuationSemicolon XXsemicolon;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE @CURIOUS("Comma instead of a semicolon") com.eagle.tokens.punctuation.PunctuationComma XXcomma;
			public @CURIOUS("Comma instead of a semicolon") PunctuationComma XXcomma;
		}

		public static class FSharp_Statement extends TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE FSharp_Assignment XXassignment;
			public FSharp_Assignment XXassignment;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE FSharp_ForStatement XXforStatement;
			public FSharp_ForStatement XXforStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE FSharp_Function XXfunction;
			public FSharp_Function XXfunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE FSharp_IfStatement XXifStatement;
			public FSharp_IfStatement XXifStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE FSharp_LetStatement XXletStatement;
			public FSharp_LetStatement XXletStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE FSharp_PrintfnStatement XXprintfnStatement;
			public FSharp_PrintfnStatement XXprintfnStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE FSharp_WhileStatement XXwhileStatement;
			public FSharp_WhileStatement XXwhileStatement;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @LAST FSharp_ExpressionStatement XXreturnValue;
			public FSharp_ExpressionStatement XXreturnValue;
		}

		public static class FSharp_MultilineStatement extends TokenSequence implements EagleRunnableWithResult, EagleTransformableStatementList
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT FSharp_Comment comment;
			public @OPT FSharp_Comment comment;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.FSharp.Terminals.FSharp_EndOfLine eoln;
			public FSharp_EndOfLine eoln;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.TokenList<FSharp_Element> elements;
			public TokenList<FSharp_Element> elements;

			public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
			{
				Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
				foreach (FSharp_Element stmt in elements._elements)
				{
					result = interpreter.tryToInterpret(stmt.statementOrComment);
					if (result != Eagle_Statement_Result.NORMAL)
					{
						break;
					}
				}
				return result;
			}

			public List<AbstractStatement> transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
			{
				List<AbstractStatement> result = new List<AbstractStatement>();
				foreach (FSharp_Element elt in elements._elements)
				{
					List<AbstractStatement> batch = transformer.transformStatement(generator, elt.statementOrComment.getWhich());
					if (batch != null)
					{
						foreach (AbstractStatement stmt in batch)
						{
							result.Add(stmt);
						}
					}
				}
				return result;
			}
		}

		public static class FSharp_SingleLineStatement extends TokenSequence implements EagleRunnableWithResult, EagleTransformableStatementList
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.SeparatedList<FSharp_Statement, com.eagle.tokens.punctuation.PunctuationSemicolon> statements;
			public SeparatedList<FSharp_Statement, PunctuationSemicolon> statements;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT FSharp_Comment comment;
			public @OPT FSharp_Comment comment;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT FSharp_EndOfLine eoln;
			public @OPT FSharp_EndOfLine eoln;

			public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
			{
				Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
				for (int i = 0; i < statements.getPrimaryCount(); i++)
				{
					result = interpreter.tryToInterpret(statements.getPrimaryElement(i));
					if (result != Eagle_Statement_Result.NORMAL)
					{
						break;
					}
				}
				return result;
			}

			public List<AbstractStatement> transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
			{
				List<AbstractStatement> result = new List<AbstractStatement>();
				for (int i = 0; i < statements.getPrimaryCount(); i++)
				{
					FSharp_Statement stmt = statements.getPrimaryElement(i);
					result.Add(transformer.transformStatement1(generator, stmt.getWhich()));
				}
				return result;
			}
		}

		public static class FSharp_SingleOrMultiLineStatement extends TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE FSharp_Punctuation XXdots = new com.eagle.programmar.FSharp.Terminals.FSharp_Punctuation("...");
			public FSharp_Punctuation XXdots = new FSharp_Punctuation("...");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE FSharp_MultilineStatement XXmultiLineStatement;
			public FSharp_MultilineStatement XXmultiLineStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE FSharp_SingleLineStatement XXsingleLineStatement;
			public FSharp_SingleLineStatement XXsingleLineStatement;
		}
	}

}
