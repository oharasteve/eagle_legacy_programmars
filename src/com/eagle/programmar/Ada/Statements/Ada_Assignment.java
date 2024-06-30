// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2022

package com.eagle.programmar.Ada.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleHash;
import com.eagle.math.EagleInteger;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Ada.Ada_Expression;
import com.eagle.programmar.Ada.Ada_Variable;
import com.eagle.programmar.Ada.Ada_Variable.Ada_Subscript;
import com.eagle.programmar.Ada.Symbols.Ada_Identifier_Reference;
import com.eagle.programmar.Ada.Terminals.Ada_PunctuationChoice;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Ada_Assignment extends TokenSequence implements EagleRunnable, AbstractStatement
{
	public @S(10) Ada_Variable variable;
	public @S(20) Ada_PunctuationChoice equals = new Ada_PunctuationChoice("=", "+=", "-=", "*=", "/=", ":=");
	public @S(30) Ada_Expression expr;
	public @S(40) PunctuationSemicolon semicolon;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		Ada_Identifier_Reference id = variable.vars.first();
		EagleValue var = interpreter._symbolTable.findSymbol(id.toString());
		EagleValue val = interpreter.getEagleValue(expr);
		
		if (variable.subscript != null)
		{
			EagleHash hash = (EagleHash) var; 
			if (hash == null)
			{
				hash = new EagleHash();
				interpreter._symbolTable.setSymbol(variable.getFileName(), variable.getStartLine(), variable.getStartChar(),
						id.getValue(), hash);
			}
			Ada_Subscript sub = variable.subscript;
			String key = interpreter.getStrValue(sub.expr);
			hash.putValue(key, val);
		}
		else
		{
			EagleValue v;
			switch (equals.getValue())
			{
			case "=", ":=":
				v = val;
				break;
			case "+=":
				v = new EagleInteger(var.forceIntegerValue() + val.forceIntegerValue());
				break;
			case "-=":
				v = new EagleInteger(var.forceIntegerValue() - val.forceIntegerValue());
				break;
			case "*=":
				v = new EagleInteger(var.forceIntegerValue() * val.forceIntegerValue());
				break;
			case "/=":
				v = new EagleInteger(var.forceIntegerValue() / val.forceIntegerValue());
				break;
			default:
				throw new RuntimeException("Unable to handle " + equals.getValue());
			}
			
			interpreter._symbolTable.setSymbol(variable.getFileName(), variable.getStartLine(), variable.getStartChar(),
					id.getValue(), v);
		}
	}
}
