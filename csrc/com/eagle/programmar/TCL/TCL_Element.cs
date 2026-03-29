// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 15, 2014

namespace com.eagle.programmar.TCL
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnableWithResult = com.eagle.interpret.EagleRunnableWithResult;
	using TCL_BlockStatement = com.eagle.programmar.TCL.Statements.TCL_BlockStatement;
	using TCL_BreakStatement = com.eagle.programmar.TCL.Statements.TCL_BreakStatement;
	using TCL_ExpressionStatement = com.eagle.programmar.TCL.Statements.TCL_ExpressionStatement;
	using TCL_ForStatement = com.eagle.programmar.TCL.Statements.TCL_ForStatement;
	using TCL_FunctionCall = com.eagle.programmar.TCL.Statements.TCL_FunctionCall;
	using TCL_IfStatement = com.eagle.programmar.TCL.Statements.TCL_IfStatement;
	using TCL_IncrStatement = com.eagle.programmar.TCL.Statements.TCL_IncrStatement;
	using TCL_NamespaceStatement = com.eagle.programmar.TCL.Statements.TCL_NamespaceStatement;
	using TCL_PutsStatement = com.eagle.programmar.TCL.Statements.TCL_PutsStatement;
	using TCL_ReturnStatement = com.eagle.programmar.TCL.Statements.TCL_ReturnStatement;
	using TCL_SetStatement = com.eagle.programmar.TCL.Statements.TCL_SetStatement;
	using TCL_VariableStatement = com.eagle.programmar.TCL.Statements.TCL_VariableStatement;
	using TCL_WhileStatement = com.eagle.programmar.TCL.Statements.TCL_WhileStatement;
	using TCL_Comment = com.eagle.programmar.TCL.Terminals.TCL_Comment;
	using TCL_EndOfLine = com.eagle.programmar.TCL.Terminals.TCL_EndOfLine;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using EagleTransformableStatement = com.eagle.transform.EagleTransformableStatement;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class TCL_Element : TokenSequence, EagleRunnableWithResult, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.SeparatedList<TCL_Statement, com.eagle.tokens.punctuation.PunctuationSemicolon> statements;
		public SeparatedList<TCL_Statement, PunctuationSemicolon> statements;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT TCL_Comment comment;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT TCL_EndOfLine eoln;
		public  OPT;

		public class TCL_Statement : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE TCL_Comment XXcomment;
			public TCL_Comment XXcomment;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE TCL_BlockStatement XXblockStatement;
			public TCL_BlockStatement XXblockStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE TCL_Procedure XXprocedure;
			public TCL_Procedure XXprocedure;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE TCL_BreakStatement XXbreakStatement;
			public TCL_BreakStatement XXbreakStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE TCL_ForStatement XXforStatement;
			public TCL_ForStatement XXforStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE TCL_IfStatement XXifStatement;
			public TCL_IfStatement XXifStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE TCL_IncrStatement XXincrStatement;
			public TCL_IncrStatement XXincrStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE TCL_NamespaceStatement XXnamespaceStatement;
			public TCL_NamespaceStatement XXnamespaceStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE TCL_PutsStatement XXputsStatement;
			public TCL_PutsStatement XXputsStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE TCL_ReturnStatement XXreturnStatement;
			public TCL_ReturnStatement XXreturnStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE TCL_SetStatement XXsetStatement;
			public TCL_SetStatement XXsetStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE TCL_VariableStatement XXvariableStatement;
			public TCL_VariableStatement XXvariableStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE TCL_WhileStatement XXwhileStatement;
			public TCL_WhileStatement XXwhileStatement;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @LAST TCL_FunctionCall XXfunctionCall;
			public TCL_FunctionCall XXfunctionCall;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @LAST TCL_ExpressionStatement XXexpressionStatement;
			public TCL_ExpressionStatement XXexpressionStatement;
		}

		public override Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
			for (int i = 0; i < statements.getPrimaryCount(); i++)
			{
				TCL_Statement stmt = statements.getPrimaryElement(i);
				result = interpreter.tryToInterpret(stmt);
				if (result != Eagle_Statement_Result.NORMAL)
				{
					break;
				}
			}
			return result;
		}

		public override AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			List<AbstractStatement> stmts = new List<AbstractStatement>();
			if (statements != null && statements.isPresent())
			{
				for (int i = 0; i < statements.getPrimaryCount(); i++)
				{
					TCL_Statement stmt = statements.getPrimaryElement(i);
					AbstractStatement newStmt = transformer.transformStatement1(generator, stmt);
					if (newStmt != null)
					{
						stmts.Add(newStmt);
					}
				}
			}

			return generator.newBlockStatement(stmts, this);
		}
	}

}
