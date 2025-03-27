// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 6, 2024

package com.eagle.programmar.Python;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Python.Symbols.Python_Variable_Definition;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationEquals;

public class Python_Data extends TokenSequence implements EagleRunnable, AbstractStatement
{
	public @S(10) @NEWLINE Python_Variable_Definition id;
	public @S(20) PunctuationColon colon;
	public @S(30) Python_Type type;
	public @S(40) @OPT Python_DataInitialValue initialValue;

	public static class Python_DataInitialValue extends TokenSequence
	{
		public @S(10) PunctuationEquals equals;
		public @S(20) Python_Expression expression;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue value = interpreter.getEagleValue(initialValue);
		interpreter.setSymbol(id, id.toString(), value);
	}
	
	public static Python_Data newDataDeclaration(String name, AbstractExpression size, AbstractType type,
				AbstractExpression initial, AbstractToken source)
	{
		if (type == null)
		{
			throw new RuntimeException("Can't create data without a type");
		}
		
		Python_Data data = new Python_Data();
		
		// Set data name and type
		data.id = new Python_Variable_Definition();
		data.id.setValue(name);
		data.colon = new PunctuationColon();
		data.type = (Python_Type) type;

		// Set the initial value, if any
		if (initial != null)
		{
			Python_DataInitialValue init = new Python_DataInitialValue();
			init.setPresent(true);
			init.equals = new PunctuationEquals();
			init.expression = (Python_Expression) initial;
			data.initialValue = init;
			data.initialValue.setPresent(true);
		}

		data.setTransformationSource(source);
		return data;
	}
}
