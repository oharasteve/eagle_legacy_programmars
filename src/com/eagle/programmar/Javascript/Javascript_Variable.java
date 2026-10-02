// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2011

package com.eagle.programmar.Javascript;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.tokens.TerminalToken;
import com.eagle.programmar.Javascript.Runtime.JsValues;
import com.eagle.programmar.Javascript.Runtime.JsRuntime;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleArray;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Javascript.Symbols.Javascript_Field_Reference;
import com.eagle.programmar.Javascript.Symbols.Javascript_Identifier_Reference;
import com.eagle.programmar.Javascript.Terminals.Javascript_KeywordChoice;
import com.eagle.programmar.Javascript.Terminals.Javascript_PunctuationChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.programmar.Javascript.Terminals.Javascript_Punctuation;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Javascript_Variable extends TokenSequence implements EagleRunnable
{
	public @S(10) Javascript_VariableIdentifier firstId;
	public @S(20) @OPT TokenList<Javascript_VariableQualifier> qualifiers;

	public static class Javascript_VariableIdentifier extends TokenChooser
	{
		public @CHOICE Javascript_Identifier_Reference XXid;
		public @CHOICE Javascript_KeywordChoice XXTHIS = new Javascript_KeywordChoice("this");
		public @LAST Javascript_PunctuationChoice XXdollar = new Javascript_PunctuationChoice("$", "_");

		public @CHOICE static class Javascript_CastedVariable extends TokenSequence
		{
			public @S(10) PunctuationLeftParen leftParen1;
			public @S(20) PunctuationLeftParen leftParen2;
			public @S(30) Javascript_Type jstype;
			public @S(40) PunctuationRightParen rightParen1;
			public @S(50) Javascript_Identifier_Reference id;
			public @S(60) PunctuationRightParen rightParen2;
		}
	}

	public static class Javascript_VariableQualifier extends TokenChooser
	{
		public @CHOICE Javascript_Subscript XXsubscript;
		public @CHOICE Javascript_OptionalCall XXoptionalCall;
		public @CHOICE Javascript_OptionalSubscript XXoptionalSubscript;

		public @CHOICE static class Javascript_VarField extends TokenSequence
		{
			public @S(10) Javascript_PunctuationChoice dot = new Javascript_PunctuationChoice(".", "?.");
			// A property may be a reserved word: promise.catch(...), obj.default
			public @S(20) Javascript_Field_Reference id;
		}
	}

	/** f?.(args), since Oct 2026 (shane branch). */
	public static class Javascript_OptionalCall extends TokenSequence
	{
		public @S(10) Javascript_Punctuation question = new Javascript_Punctuation("?.");
		public @S(20) Javascript_ParenthesizedExpression arguments;
	}

	/** a?.[key] */
	public static class Javascript_OptionalSubscript extends TokenSequence
	{
		public @S(10) Javascript_Punctuation question = new Javascript_Punctuation("?.");
		public @S(20) PunctuationLeftBracket leftBracket;
		public @S(30) Javascript_Expression index;
		public @S(40) PunctuationRightBracket rightBracket;
	}

	/** The value this variable names: the first name, then every qualifier in turn. receiver, when given, is what the first name is a property of. */
	public static EagleValue evaluate(JsRuntime rt, Javascript_Variable v, EagleValue receiver, int skipLast)
	{
		AbstractToken first = v.firstId.getWhich();
		EagleValue value;
		if (receiver != null) value = rt.getProperty(receiver, firstName(v), false);
		else if (first instanceof Javascript_Identifier_Reference) value = rt.read(((Javascript_Identifier_Reference) first).getValue());
		else if (first instanceof Javascript_KeywordChoice) value = rt.thisValue();
		else value = rt.read(first.toString());
		if (!JsRuntime.has(v.qualifiers)) return value;
		int n = v.qualifiers.size() - skipLast;
		for (int i = 0; i < n; i++)
		{
			AbstractToken which = v.qualifiers._elements.get(i).getWhich();
			if (which instanceof Javascript_Subscript)
			{
				value = rt.getIndex(value, rt.eval(((Javascript_Subscript) which).expr), false);
			}
			else if (which instanceof Javascript_VariableQualifier.Javascript_VarField)
			{
				Javascript_VariableQualifier.Javascript_VarField field = (Javascript_VariableQualifier.Javascript_VarField) which;
				if ("?.".equals(field.dot.getValue()) && JsValues.isNullish(value)) return JsValues.undefined();
				value = rt.getProperty(value, field.id.getValue(), false);
			}
			else if (which instanceof Javascript_OptionalCall)
			{
				if (JsValues.isNullish(value)) return JsValues.undefined();
				value = rt.call(value, JsValues.undefined(), rt.args(((Javascript_OptionalCall) which).arguments.expressions), v);
			}
			else if (which instanceof Javascript_OptionalSubscript)
			{
				if (JsValues.isNullish(value)) return JsValues.undefined();
				value = rt.getIndex(value, rt.eval(((Javascript_OptionalSubscript) which).index), true);
			}
		}
		return value;
	}

	public static String firstName(Javascript_Variable v)
	{
		AbstractToken first = v.firstId.getWhich();
		return first instanceof TerminalToken ? ((TerminalToken) first).getValue() : first.toString();
	}

	/** The last qualifier: a field name (with "?." noted) or null when it is a subscript. */
	public static AbstractToken lastQualifier(Javascript_Variable v)
	{
		if (!JsRuntime.has(v.qualifiers)) return null;
		return v.qualifiers._elements.get(v.qualifiers.size() - 1).getWhich();
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		interpreter.pushEagleValue(evaluate(JsRuntime.of(interpreter), this, null, 0));
	}
}
