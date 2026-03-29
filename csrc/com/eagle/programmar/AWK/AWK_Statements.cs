// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Collections.Generic;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

namespace com.eagle.programmar.AWK
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnableWithResult = com.eagle.interpret.EagleRunnableWithResult;
	using AWK_Assignment = com.eagle.programmar.AWK.Statements.AWK_Assignment;
	using AWK_BreakStatement = com.eagle.programmar.AWK.Statements.AWK_BreakStatement;
	using AWK_ContinueStatement = com.eagle.programmar.AWK.Statements.AWK_ContinueStatement;
	using AWK_ExitStatement = com.eagle.programmar.AWK.Statements.AWK_ExitStatement;
	using AWK_ExpressionStatement = com.eagle.programmar.AWK.Statements.AWK_ExpressionStatement;
	using AWK_ForEachStatement = com.eagle.programmar.AWK.Statements.AWK_ForEachStatement;
	using AWK_ForStatement = com.eagle.programmar.AWK.Statements.AWK_ForStatement;
	using AWK_IfStatement = com.eagle.programmar.AWK.Statements.AWK_IfStatement;
	using AWK_NextStatement = com.eagle.programmar.AWK.Statements.AWK_NextStatement;
	using AWK_PrintStatement = com.eagle.programmar.AWK.Statements.AWK_PrintStatement;
	using AWK_ReturnStatement = com.eagle.programmar.AWK.Statements.AWK_ReturnStatement;
	using AWK_SplitStatement = com.eagle.programmar.AWK.Statements.AWK_SplitStatement;
	using AWK_SubStatement = com.eagle.programmar.AWK.Statements.AWK_SubStatement;
	using AWK_SwitchStatement = com.eagle.programmar.AWK.Statements.AWK_SwitchStatement;
	using AWK_WhileStatement = com.eagle.programmar.AWK.Statements.AWK_WhileStatement;
	using AWK_Comment = com.eagle.programmar.AWK.Terminals.AWK_Comment;
	using AWK_EndOfLine = com.eagle.programmar.AWK.Terminals.AWK_EndOfLine;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using EagleTransformableStatementList = com.eagle.transform.EagleTransformableStatementList;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class AWK_Statements : TokenSequence, EagleRunnableWithResult, EagleTransformableStatementList
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.SeparatedList<AWK_Statement, com.eagle.tokens.punctuation.PunctuationSemicolon> statements;
		public SeparatedList<AWK_Statement, PunctuationSemicolon> statements;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT PunctuationSemicolon semicolon;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT TokenList<com.eagle.programmar.AWK.Terminals.AWK_Comment> comments;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT AWK_EndOfLine endOfLine;
		public  OPT;

		public class AWK_Statement : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE PunctuationSemicolon XXsemicolon;
			public PunctuationSemicolon XXsemicolon; // Empty statement
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE AWK_Comment XXcomment;
			public AWK_Comment XXcomment;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE AWK_Assignment XXassignmentStatement;
			public AWK_Assignment XXassignmentStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE AWK_BreakStatement XXbreakStatement;
			public AWK_BreakStatement XXbreakStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE AWK_ContinueStatement XXcontinueStatement;
			public AWK_ContinueStatement XXcontinueStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE AWK_ExitStatement XXexitStatement;
			public AWK_ExitStatement XXexitStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE AWK_ForStatement XXforStatement;
			public AWK_ForStatement XXforStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE AWK_ForEachStatement XXforEachStatement;
			public AWK_ForEachStatement XXforEachStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE AWK_IfStatement XXifStatement;
			public AWK_IfStatement XXifStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE AWK_NextStatement XXnextStatement;
			public AWK_NextStatement XXnextStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE AWK_PrintStatement XXprintStatement;
			public AWK_PrintStatement XXprintStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE AWK_ReturnStatement XXreturnStatement;
			public AWK_ReturnStatement XXreturnStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE AWK_SplitStatement XXsplitStatement;
			public AWK_SplitStatement XXsplitStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE AWK_SubStatement XXsubStatement;
			public AWK_SubStatement XXsubStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE AWK_SwitchStatement XXswitchStatement;
			public AWK_SwitchStatement XXswitchStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE AWK_WhileStatement XXwhileStatement;
			public AWK_WhileStatement XXwhileStatement;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @LAST AWK_ExpressionStatement XXexpressionStatement;
			public AWK_ExpressionStatement XXexpressionStatement;
		}

		public override Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
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

		public override List<AbstractStatement> transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			List<AbstractStatement> result = new List<AbstractStatement>();

			for (int i = 0; i < statements.getPrimaryCount(); i++)
			{
				AWK_Statement stmt = statements.getPrimaryElement(i);
				result.Add(transformer.transformStatement1(generator, stmt));
			}

			return result;
		}
	}

}
