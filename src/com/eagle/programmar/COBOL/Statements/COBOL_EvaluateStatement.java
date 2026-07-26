// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 10, 2010

package com.eagle.programmar.COBOL.Statements;

import java.util.ArrayList;

import com.eagle.generate.EagleGenerator;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.metrics.SwitchMetrics;
import com.eagle.programmar.COBOL.COBOL_AbstractStatement;
import com.eagle.programmar.COBOL.COBOL_Expression;
import com.eagle.programmar.COBOL.COBOL_StatementOrComment;
import com.eagle.programmar.COBOL.Statements.COBOL_EvaluateStatement.COBOL_EvaluateWhat.COBOL_EvaluateExpression;
import com.eagle.programmar.COBOL.Terminals.COBOL_Comment;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class COBOL_EvaluateStatement extends COBOL_AbstractStatement
		implements EagleRunnableWithResult, EagleTransformableStatement
{
	public @S(10) @DOC("rlpseval.htm") COBOL_Keyword EVALUATE = new COBOL_Keyword("EVALUATE");
	public @S(20) COBOL_Expression keyExpr;
	public @S(30) @OPT TokenList<COBOL_Comment> comments;
	public @S(40) @OPT TokenList<COBOL_EvaluateAlsoClause> alsos;
	public @S(50) TokenList<COBOL_EvaluateWhenClause> whens;
	public @S(60) @OPT COBOL_Keyword ENDEVALUATE = new COBOL_Keyword("END-EVALUATE");

	public static class COBOL_EvaluateWhat extends TokenChooser
	{
		public @FIRST COBOL_Keyword XXOTHER = new COBOL_Keyword("OTHER");

		public @CHOICE static class COBOL_EvaluateExpression extends TokenSequence
		{
			public @S(10) COBOL_Expression expr;
			public @S(20) @OPT COBOL_EvaluateThru thru;

			public static class COBOL_EvaluateThru extends TokenSequence
			{
				public @S(10) COBOL_Keyword THRU = new COBOL_Keyword("THRU");
				public @S(20) COBOL_Expression expr;
			}
		}
	}

	public static class COBOL_EvaluateAlsoClause extends TokenSequence
	{
		public @S(10) COBOL_Keyword ALSO = new COBOL_Keyword("ALSO");
		public @S(20) COBOL_EvaluateWhat value;
	}

	public static class COBOL_EvaluateWhenClause extends TokenSequence
	{
		public @S(10) COBOL_Keyword WHEN = new COBOL_Keyword("WHEN");
		public @S(20) COBOL_EvaluateWhat value;
		public @S(30) @OPT TokenList<COBOL_EvaluateAlsoClause> alsos;
		public @S(40) @OPT TokenList<COBOL_StatementOrComment> statements;
	}

	private @SKIP SwitchMetrics _metrics = null;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		TokenList<COBOL_StatementOrComment> todo = null;

		if (_metrics == null)
		{
			// Had to delay to make sure line numbers etc are all set
			_metrics = new SwitchMetrics(interpreter._metrics, EVALUATE);
		}

		TokenList<COBOL_StatementOrComment> otherActions = null;

		int keyValue = interpreter.getIntValue(keyExpr);
		
		for (COBOL_EvaluateWhenClause when : whens._elements)
		{
			AbstractToken which2 = when.value.getWhich();
			if (which2 instanceof COBOL_Keyword) // Must be "OTHER"
			{
				otherActions = when.statements;
			}
			else if (which2 instanceof COBOL_EvaluateExpression)
			{
				COBOL_EvaluateExpression eval = (COBOL_EvaluateExpression) which2;
				if (eval.thru != null && eval.thru.isPresent())
				{
					throw new RuntimeException("Not yet implemented: " + eval);
				}
				int caseValue = interpreter.getIntValue(eval.expr);
				if (keyValue == caseValue)
				{
					todo = when.statements;
					break;
				}
			}
		}

		if (todo == null && otherActions != null)
		{
			_metrics.noMatch(otherActions);
			todo = otherActions;
		}

		if (todo != null)
		{
			for (COBOL_StatementOrComment stmt : todo._elements)
			{
				result = interpreter.tryToInterpret(stmt.getWhich());
				if (result != Eagle_Statement_Result.NORMAL)
				{
					break;
				}
			}
		}

		return result;
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		AbstractExpression newValue = transformer.transformExpression(generator, keyExpr);

		ArrayList<AbstractStatement> defaultActionList = null;
		
		ArrayList<ArrayList<AbstractExpression>> allCases = new ArrayList<ArrayList<AbstractExpression>>();
		ArrayList<ArrayList<AbstractStatement>> allActions = new ArrayList<ArrayList<AbstractStatement>>();
		for (int i = 0; i < whens.size(); i++)
		{
			COBOL_EvaluateWhenClause when = whens._elements.get(i);
			AbstractToken which = when.value.getWhich();
			if (which instanceof COBOL_EvaluateExpression)
			{
				COBOL_EvaluateExpression evalExpr = (COBOL_EvaluateExpression) which;
				
				ArrayList<AbstractExpression> caseList = new ArrayList<AbstractExpression>();
				AbstractExpression newExpr2 = transformer.transformExpression(generator, evalExpr.expr);
				caseList.add(newExpr2);
				allCases.add(caseList);

				ArrayList<AbstractStatement> actionList = new ArrayList<AbstractStatement>();
				for (COBOL_StatementOrComment stmt1 : when.statements._elements)
				{
					ArrayList<AbstractStatement> transStmts = transformer.transformStatement(generator, stmt1);
					if (transStmts != null)
					{
						for (AbstractStatement stmt2 : transStmts)
						{
							if (!(stmt2 instanceof COBOL_Comment))
							{
								actionList.add(stmt2);
							}
						}
					}
				}
				actionList.add(generator.newBreakStatement(this));
				allActions.add(actionList);
			}
			else if (which instanceof COBOL_Keyword)
			{
				// Has to be "OTHER"
				defaultActionList = new ArrayList<AbstractStatement>();
				for (COBOL_StatementOrComment stmt3 : when.statements._elements)
				{
					for (AbstractStatement stmt4 : transformer.transformStatement(generator, stmt3))
					{
						defaultActionList.add(stmt4);
					}
					defaultActionList.add(generator.newBreakStatement(this));
				}
			}
			else throw new RuntimeException("Unable to handle: " + which);
		}

		AbstractStatement stmt = generator.newSwitchStatement(newValue, allCases, allActions, defaultActionList, this);
		return stmt;
	}
}
