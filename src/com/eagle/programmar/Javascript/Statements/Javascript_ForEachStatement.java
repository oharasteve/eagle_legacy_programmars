// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 19, 2025

package com.eagle.programmar.Javascript.Statements;

import com.eagle.metrics.ForLoopMetrics;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult.Eagle_Statement_Result;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.tokens.AbstractToken;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Javascript.Runtime.JsValues;
import com.eagle.programmar.Javascript.Runtime.JsRuntime;
import com.eagle.programmar.Javascript.Javascript_Element;
import com.eagle.programmar.Javascript.Javascript_Expression;
import com.eagle.programmar.Javascript.Javascript_Type;
import com.eagle.programmar.Javascript.Javascript_Variable;
import com.eagle.programmar.Javascript.Symbols.Javascript_Variable_Definition;
import com.eagle.programmar.Javascript.Terminals.Javascript_Comment;
import com.eagle.programmar.Javascript.Terminals.Javascript_Keyword;
import com.eagle.programmar.Javascript.Terminals.Javascript_KeywordChoice;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Javascript_ForEachStatement extends TokenSequence implements EagleRunnableWithResult
{
	public @S(10) @DOC("js_loop_for.asp") Javascript_Keyword FOR = new Javascript_Keyword("for");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) Javascript_ForCollectionStatement forCollection;
	public @S(40) PunctuationRightParen rightParen;
	public @S(50) @OPT TokenList<Javascript_Comment> comments;
	public @S(60) Javascript_Element action;

	private @SKIP ForLoopMetrics _metrics = null;

	public static class Javascript_ForCollectionStatement extends TokenSequence
	{
		public @S(10) @OPT Javascript_Type varType;
		public @S(20) @OPT Javascript_Variable forVar; // The Javascript_Type steals it ...
		public @S(30) @OPT Javascript_ForVariables forVars;
		public @S(40) Javascript_InOrColon inOrColon;
		public @S(50) Javascript_Expression collection;

		public static class Javascript_ForVariables extends TokenSequence
		{
			public @S(10) PunctuationLeftBracket leftBracket;
			public @S(20) SeparatedList<Javascript_Variable_Definition, PunctuationComma> vars;
			public @S(30) PunctuationRightBracket rightBracket;
		}

		public static class Javascript_InOrColon extends TokenChooser
		{
			public @CHOICE PunctuationColon XXcolon;
			public @CHOICE Javascript_KeywordChoice XXIN = new Javascript_KeywordChoice("in", "of");
		}
	}

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		JsRuntime rt = JsRuntime.of(interpreter);
		Javascript_ForCollectionStatement spec = forCollection;
		boolean keys = spec.inOrColon.getWhich() instanceof com.eagle.tokens.TerminalToken
				&& "in".equals(((com.eagle.tokens.TerminalToken) spec.inOrColon.getWhich()).getValue());
		EagleValue collection = rt.eval(spec.collection);
		java.util.List<EagleValue> items = new java.util.ArrayList<EagleValue>();
		if (keys)
		{
			if (collection instanceof JsValues.JsObject) for (String k : ((JsValues.JsObject) collection).keys()) items.add(JsValues.str(k));
			else if (collection.isArray()) for (int i = 0; i < JsValues.items(collection).size(); i++) items.add(JsValues.str(String.valueOf(i)));
		}
		else if (collection.isArray()) items.addAll(JsValues.items(collection));
		else if (collection.isString()) for (char c : collection.forceStringValue().toCharArray()) items.add(JsValues.str(String.valueOf(c)));
		else if (collection instanceof JsValues.JsObject && ((JsValues.JsObject) collection).store != null)
		{
			JsValues.JsObject m = (JsValues.JsObject) collection;
			for (EagleValue[] e : m.store.values())
			{
				if ("Map".equals(m.className)) { com.eagle.math.EagleArray pair = JsValues.newArray(); pair.addValue(e[0]); pair.addValue(e[1]); items.add(pair); }
				else items.add(e[0]);
			}
		}
		else throw new JsValues.JsThrow(JsValues.error("TypeError: " + JsValues.str(collection) + " is not iterable"));

		boolean declares = spec.varType != null && spec.varType.isPresent() && !(spec.varType.getWhich() instanceof Javascript_Variable);
		for (EagleValue item : items)
		{
			if (spec.forVars != null && spec.forVars.isPresent())
			{
				java.util.List<EagleValue> parts = item.isArray() ? JsValues.items(item) : java.util.Collections.<EagleValue>emptyList();
				for (int i = 0; i < spec.forVars.vars.getPrimaryCount(); i++)
				{
					Javascript_Variable_Definition def = spec.forVars.vars.getPrimaryElement(i);
					EagleValue part = i < parts.size() ? parts.get(i) : JsValues.undefined();
					if (declares) rt.declare(def, def.getValue(), part); else rt.assign(def, def.getValue(), part);
				}
			}
			else
			{
				Javascript_Variable v = spec.forVar != null && spec.forVar.isPresent() ? spec.forVar : (Javascript_Variable) spec.varType.getWhich();
				String name = Javascript_Variable.firstName(v);
				if (declares) rt.declare(v, name, item); else rt.assign(v, name, item);
			}
			Eagle_Statement_Result result = interpreter.tryToInterpret(action);
			if (result == Eagle_Statement_Result.BREAK) break;
			if (result == Eagle_Statement_Result.RETURN) return result;
		}
		return Eagle_Statement_Result.NORMAL;
	}
}
