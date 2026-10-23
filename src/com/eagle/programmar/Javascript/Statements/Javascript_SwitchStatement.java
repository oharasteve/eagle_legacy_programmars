// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2011

package com.eagle.programmar.Javascript.Statements;

import com.eagle.programmar.Javascript.Javascript_Element.Javascript_StatementOrComment;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult.Eagle_Statement_Result;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.tokens.AbstractToken;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Javascript.Runtime.JsValues;
import com.eagle.programmar.Javascript.Runtime.JsRuntime;
import com.eagle.programmar.Javascript.Javascript_Expression;
import com.eagle.programmar.Javascript.Terminals.Javascript_Keyword;
import com.eagle.tokens.TokenList;
import java.util.ArrayList;
import com.eagle.generate.EagleGenerator;
import com.eagle.programmar.Javascript.Javascript_Statement;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Javascript_SwitchStatement extends TokenSequence implements AbstractStatement, EagleRunnableWithResult, EagleTransformableStatement
{
	public @S(10) @DOC("js_switch.asp") Javascript_Keyword SWITCH = new Javascript_Keyword("switch");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) Javascript_Expression val;
	public @S(40) PunctuationRightParen rightParen;
	public @S(50) PunctuationLeftBrace leftBrace;
	public @S(60) @OPT TokenList<Javascript_CaseClause> caseClause;
	public @S(70) @OPT Javascript_DefaultClause elseClause;
	public @S(80) PunctuationRightBrace rightBrace;

	public static class Javascript_CaseClause extends TokenSequence
	{
		public @S(10) Javascript_Keyword CASE = new Javascript_Keyword("case");
		public @S(20) Javascript_Expression expr;
		public @S(30) PunctuationColon colon;
		public @S(40) @OPT TokenList<Javascript_StatementOrComment> statements;
	}

	public static class Javascript_DefaultClause extends TokenSequence
	{
		public @S(10) Javascript_Keyword DEFAULT = new Javascript_Keyword("default");
		public @S(20) PunctuationColon colon;
		public @S(30) TokenList<Javascript_StatementOrComment> statements;
	}

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		JsRuntime rt = JsRuntime.of(interpreter);
		EagleValue value = rt.eval(val);
		boolean matched = false;
		if (JsRuntime.has(caseClause)) for (Javascript_CaseClause clause : caseClause._elements)
		{
			if (!matched && JsValues.strictEquals(value, rt.eval(clause.expr))) matched = true;
			if (!matched) continue;
			Eagle_Statement_Result result = rt.runStatements(clause.statements);
			if (result == Eagle_Statement_Result.BREAK) return Eagle_Statement_Result.NORMAL;
			if (result != Eagle_Statement_Result.NORMAL) return result;
		}
		if (!matched && elseClause != null && elseClause.isPresent())
		{
			Eagle_Statement_Result result = rt.runStatements(elseClause.statements);
			if (result == Eagle_Statement_Result.BREAK) return Eagle_Statement_Result.NORMAL;
			return result;
		}
		return Eagle_Statement_Result.NORMAL;
	}

	/**
	 * A switch becomes the generator's switch (Rust: match). Consecutive empty cases share the
	 * next case's actions; a case's closing break is dropped; a case that falls into the next one
	 * after doing something has no match equivalent, so it stops with that said.
	 */
	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		AbstractExpression value = transformer.transformExpression(generator, val);
		ArrayList<ArrayList<AbstractExpression>> values = new ArrayList<ArrayList<AbstractExpression>>();
		ArrayList<ArrayList<AbstractStatement>> actions = new ArrayList<ArrayList<AbstractStatement>>();
		ArrayList<AbstractExpression> pending = new ArrayList<AbstractExpression>();
		int clauses = JsRuntime.has(caseClause) ? caseClause.size() : 0;
		for (int i = 0; i < clauses; i++)
		{
			Javascript_CaseClause clause = caseClause._elements.get(i);
			pending.add(transformer.transformExpression(generator, clause.expr));
			ArrayList<Javascript_Statement> body = statementsOf(clause.statements);
			if (body.isEmpty()) continue;
			boolean last = i == clauses - 1 && (elseClause == null || !elseClause.isPresent());
			if (!last && !endsTheCase(body.get(body.size() - 1)))
			{
				throw new RuntimeException("Cannot translate a case that falls through into the next one at line "
						+ (clause.CASE.getStartLine() + 1));
			}
			values.add(pending);
			actions.add(transform(transformer, generator, body));
			pending = new ArrayList<AbstractExpression>();
		}
		// Empty cases just before default are default's: their values need no arm of their own.
		ArrayList<AbstractStatement> defaultActions = null;
		if (elseClause != null && elseClause.isPresent())
		{
			defaultActions = transform(transformer, generator, statementsOf(elseClause.statements));
		}
		else if (!pending.isEmpty())
		{
			values.add(pending);
			actions.add(new ArrayList<AbstractStatement>());
		}
		return generator.newSwitchStatement(value, values, actions, defaultActions, this);
	}

	private static ArrayList<Javascript_Statement> statementsOf(TokenList<Javascript_StatementOrComment> list)
	{
		ArrayList<Javascript_Statement> out = new ArrayList<Javascript_Statement>();
		if (!JsRuntime.has(list)) return out;
		for (Javascript_StatementOrComment s : list._elements)
		{
			if (s.getWhich() instanceof Javascript_Statement) out.add((Javascript_Statement) s.getWhich());
		}
		return out;
	}

	private static boolean endsTheCase(Javascript_Statement s)
	{
		AbstractToken w = s.getWhich();
		return w instanceof Javascript_BreakStatement || w instanceof Javascript_ReturnStatement
				|| w instanceof Javascript_ThrowStatement || w instanceof Javascript_ContinueStatement;
	}

	private static ArrayList<AbstractStatement> transform(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator,
			ArrayList<Javascript_Statement> body)
	{
		ArrayList<AbstractStatement> out = new ArrayList<AbstractStatement>();
		for (Javascript_Statement s : body) out.addAll(transformer.transformStatement(generator, s.getWhich()));
		return out;
	}
}
