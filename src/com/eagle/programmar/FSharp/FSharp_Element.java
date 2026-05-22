// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

package com.eagle.programmar.FSharp;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.programmar.FSharp.Statements.FSharp_Assignment;
import com.eagle.programmar.FSharp.Statements.FSharp_ExpressionStatement;
import com.eagle.programmar.FSharp.Statements.FSharp_ForStatement;
import com.eagle.programmar.FSharp.Statements.FSharp_Function;
import com.eagle.programmar.FSharp.Statements.FSharp_IfStatement;
import com.eagle.programmar.FSharp.Statements.FSharp_LetStatement;
import com.eagle.programmar.FSharp.Statements.FSharp_PrintfnStatement;
import com.eagle.programmar.FSharp.Statements.FSharp_WhileStatement;
import com.eagle.programmar.FSharp.Terminals.FSharp_Comment;
import com.eagle.programmar.FSharp.Terminals.FSharp_EndOfLine;
import com.eagle.programmar.FSharp.Terminals.FSharp_Punctuation;
import com.eagle.programmar.FSharp.Terminals.FSharp_StartOfLine;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformableStatementList;
import com.eagle.transform.EagleTransformer;

public class FSharp_Element extends TokenSequence implements AbstractStatement
{
	public @S(10) @OPT FSharp_StartOfLine soln;
	public @S(20) FSharp_StatementOrComment statementOrComment;
	public @S(30) @OPT @CURIOUS("Extra semicolon") PunctuationSemicolon semicolon;
	public @S(40) @OPT @CURIOUS("Extra comma") PunctuationComma comma;
	public @S(50) @OPT FSharp_Comment comment;
	public @S(60) @OPT FSharp_EndOfLine eoln;

	public static class FSharp_StatementOrComment extends TokenChooser
	{
//		public @SKIP FSharp_MultilineStatement XXmultiStatement; // Only needed for Transformation

		public @CHOICE FSharp_Statement_List XXstatements;
		public @CHOICE FSharp_EndOfLine XXeoln;

		public @FIRST static class FSharp_CommentList extends TokenSequence
				implements EagleRunnable, EagleTransformableStatement
		{
			public @S(10) SeparatedList<FSharp_Comment, FSharp_EndOfLine> comments;

			@Override
			public void interpret(EagleInterpreter interpreter)
			{
				// Nothing to do
			}

			@Override
			public AbstractStatement transformStatement(EagleTransformer transformer,
					EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
			{
				return null; // Nothing to do
			}
		}
	}

	public static class FSharp_Statement_List extends TokenSequence
			implements EagleRunnable, EagleTransformableStatementList
	{
		public @S(10) FSharp_StartOfLine soln = new FSharp_StartOfLine();
		public @S(20) SeparatedList<FSharp_Statement, FSharp_Statement_Separator> statements;

		@Override
		public void interpret(EagleInterpreter interpreter)
		{
			for (int i = 0; i < statements.getPrimaryCount(); i++)
			{
				FSharp_Statement stmt = statements.getPrimaryElement(i);
				interpreter.tryToInterpret(stmt);
			}
		}

		@Override
		public ArrayList<AbstractStatement> transformStatement(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			ArrayList<AbstractStatement> result = new ArrayList<AbstractStatement>();
			for (int i = 0; i < statements.getPrimaryCount(); i++)
			{
				FSharp_Statement stmt = statements.getPrimaryElement(i);
				result.add(transformer.transformStatement1(generator, stmt.getWhich()));
			}
			return result;
		}
	}

	public static class FSharp_Statement_Separator extends TokenChooser
	{
		public @CHOICE PunctuationSemicolon XXsemicolon;
		public @CHOICE @CURIOUS("Comma instead of a semicolon") PunctuationComma XXcomma;
	}

	public static class FSharp_Statement extends TokenChooser
	{
		public @CHOICE FSharp_Assignment XXassignment;
		public @CHOICE FSharp_ForStatement XXforStatement;
		public @CHOICE FSharp_Function XXfunction;
		public @CHOICE FSharp_IfStatement XXifStatement;
		public @CHOICE FSharp_LetStatement XXletStatement;
		public @CHOICE FSharp_PrintfnStatement XXprintfnStatement;
		public @CHOICE FSharp_WhileStatement XXwhileStatement;

		public @LAST FSharp_ExpressionStatement XXreturnValue;
	}

	public static class FSharp_MultilineStatement extends TokenSequence
			implements EagleRunnableWithResult, EagleTransformableStatementList
	{
		public @S(10) @OPT FSharp_Comment comment;
		public @S(20) FSharp_EndOfLine eoln;
		public @S(30) TokenList<FSharp_Element> elements;

		@Override
		public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
			for (FSharp_Element stmt : elements._elements)
			{
				result = interpreter.tryToInterpret(stmt.statementOrComment);
				if (result != Eagle_Statement_Result.NORMAL) break;
			}
			return result;
		}

		@Override
		public ArrayList<AbstractStatement> transformStatement(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			ArrayList<AbstractStatement> result = new ArrayList<AbstractStatement>();
			for (FSharp_Element elt : elements._elements)
			{
				ArrayList<AbstractStatement> batch = transformer.transformStatement(generator,
						elt.statementOrComment.getWhich());
				if (batch != null)
				{
					for (AbstractStatement stmt : batch)
					{
						result.add(stmt);
					}
				}
			}
			return result;
		}
	}

	public static class FSharp_SingleLineStatement extends TokenSequence
			implements EagleRunnableWithResult, EagleTransformableStatementList
	{
		public @S(10) SeparatedList<FSharp_Statement, PunctuationSemicolon> statements;
		public @S(20) @OPT FSharp_Comment comment;
		public @S(30) @OPT FSharp_EndOfLine eoln;

		@Override
		public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
			for (int i = 0; i < statements.getPrimaryCount(); i++)
			{
				result = interpreter.tryToInterpret(statements.getPrimaryElement(i));
				if (result != Eagle_Statement_Result.NORMAL) break;
			}
			return result;
		}

		@Override
		public ArrayList<AbstractStatement> transformStatement(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			ArrayList<AbstractStatement> result = new ArrayList<AbstractStatement>();
			for (int i = 0; i < statements.getPrimaryCount(); i++)
			{
				FSharp_Statement stmt = statements.getPrimaryElement(i);
				result.add(transformer.transformStatement1(generator, stmt.getWhich()));
			}
			return result;
		}
	}

	public static class FSharp_SingleOrMultiLineStatement extends TokenChooser
	{
		public @CHOICE FSharp_Punctuation XXdots = new FSharp_Punctuation("...");
		public @CHOICE FSharp_MultilineStatement XXmultiLineStatement;
		public @CHOICE FSharp_SingleLineStatement XXsingleLineStatement;
	}
}
