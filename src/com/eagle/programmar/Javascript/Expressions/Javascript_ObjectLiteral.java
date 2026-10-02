// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Javascript.Expressions;

import com.eagle.programmar.Javascript.Javascript_Expression;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.tokens.AbstractToken;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Javascript.Runtime.JsValues;
import com.eagle.programmar.Javascript.Runtime.JsRuntime;
import com.eagle.programmar.Javascript.Javascript_Function.Javascript_FunctionImplementation;
import com.eagle.programmar.Javascript.Symbols.Javascript_Field_Definition;
import com.eagle.programmar.Javascript.Terminals.Javascript_Comment;
import com.eagle.programmar.Javascript.Terminals.Javascript_Keyword;
import com.eagle.programmar.Javascript.Terminals.Javascript_KeywordChoice;
import com.eagle.programmar.Javascript.Terminals.Javascript_Literal;
import com.eagle.programmar.Javascript.Terminals.Javascript_Number;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.programmar.Javascript.Terminals.Javascript_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;

public class Javascript_ObjectLiteral extends PrimaryOperator implements EagleRunnable
{
	// Don't use @INDENT here. Messes up 'return' statements that return an object
	// literal.
	public @S(10) PunctuationLeftBrace leftBrace;
	public @S(20) SeparatedList<Javascript_ObjectLiteralItem, PunctuationComma> items;
	public @S(30) @OPT PunctuationComma comma;
	public @S(40) @OPT TokenList<Javascript_Comment> comments;
	public @S(50) PunctuationRightBrace rightBrace;

	public static class Javascript_ObjectLiteralItem extends TokenChooser
	{
		/** ...expr inside an object literal (Oct 2026, shane branch). */
		public @CHOICE static class Javascript_ObjectSpread extends TokenSequence
		{
			public @S(10) Javascript_Punctuation ellipsis = new Javascript_Punctuation("...");
			public @S(20) Javascript_Expression expr;
		}

		public @CHOICE static class Javascript_ObjectFunction extends TokenSequence
		{
			public @S(10) @OPT TokenList<Javascript_Comment> comments;
			public @S(20) @OPT Javascript_Keyword STATIC = new Javascript_Keyword("static");
			public @S(30) @OPT Javascript_KeywordChoice prefix = new Javascript_KeywordChoice("get", "set");
			public @S(40) Javascript_FunctionImplementation function;
		}

		public @LAST static class Javascript_ObjecLiteraltData extends TokenSequence
		{
			public @S(10) Javascript_ObjectFieldName name;
			public @S(20) @OPT Javascript_ObjectFieldValue value;

			public static class Javascript_ObjectFieldName extends TokenChooser
			{
				public @CHOICE Javascript_Number XXnumber;
				public @CHOICE Javascript_Literal XXliteral;
				public @CHOICE Javascript_Field_Definition XXfield;
			}

			public static class Javascript_ObjectFieldValue extends TokenSequence
			{
				public @S(10) PunctuationColon colon;
				public @S(20) Javascript_Expression expr;
			}
		}
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		JsRuntime rt = JsRuntime.of(interpreter);
		JsValues.JsObject o = new JsValues.JsObject();
		{
			for (int i = 0; i < JsRuntime.count(items); i++)
			{
				AbstractToken which = items.getPrimaryElement(i).getWhich();
				if (which instanceof Javascript_ObjectLiteralItem.Javascript_ObjectSpread)
				{
					EagleValue from = rt.eval(((Javascript_ObjectLiteralItem.Javascript_ObjectSpread) which).expr);
					if (from instanceof JsValues.JsObject) o.props.putAll(((JsValues.JsObject) from).props);
				}
				else if (which instanceof Javascript_ObjectLiteralItem.Javascript_ObjectFunction)
				{
					Javascript_ObjectLiteralItem.Javascript_ObjectFunction fn = (Javascript_ObjectLiteralItem.Javascript_ObjectFunction) which;
					String name = fn.function.id != null && fn.function.id.isPresent() ? fn.function.id.getValue() : "anonymous";
					o.set(name, new JsValues.JsFunction(name, fn.function, rt.env));
				}
				else if (which instanceof Javascript_ObjectLiteralItem.Javascript_ObjecLiteraltData)
				{
					Javascript_ObjectLiteralItem.Javascript_ObjecLiteraltData data = (Javascript_ObjectLiteralItem.Javascript_ObjecLiteraltData) which;
					String key = JsRuntime.keyText(data.name.getWhich());
					if (data.value != null && data.value.isPresent()) o.set(key, rt.eval(data.value.expr));
					else o.set(key, rt.read(key));
				}
			}
		}
		interpreter.pushEagleValue(o);
	}
}
