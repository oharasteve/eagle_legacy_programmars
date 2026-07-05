// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 6, 2014

package com.eagle.programmar.PLI.Terminals;

import com.eagle.generate.BuiltInEnum;
import com.eagle.generate.EagleGenerator;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.terminals.TerminalLiteralToken;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class PLI_BitLiteral extends TerminalLiteralToken
		implements EagleRunnable, EagleTransformableExpression
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;
		EagleLineReader rec = lines.get(_currentLine);
		int recLen = rec.length();
		char ch = rec.charAt(_currentChar);
		if (ch != '\'') return false;

		int endChar = _currentChar + 1;
		while (true)
		{
			if (endChar >= recLen) return false;
			ch = rec.charAt(endChar);
			if (ch != '0' && ch != '1') break;
			endChar++;
		}

		if (endChar + 1 >= recLen) return false;
		if (rec.charAt(endChar) != '\'') return false;
		if (Character.toUpperCase(rec.charAt(endChar + 1)) != 'B') return false;

		_txt = rec.substring(_currentChar, endChar + 2);
		foundIt(_currentLine, endChar + 1);
		return true;
	}

	@Override
	public String showString()
	{
		return "Bit Literal";
	}

	@Override
	public String description()
	{
		return "A bit literal, like '0'B";
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (_txt.equalsIgnoreCase("'1'B"))
		{
			interpreter.pushBool(true);
		}
		else if (_txt.equalsIgnoreCase("'0'B"))
		{
			interpreter.pushBool(false);
		}
		else
		{
			throw new RuntimeException("Unexpected BIT value: " + _txt);
		}
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		if (_txt.equalsIgnoreCase("'1'B"))
		{
			return generator.newBuiltInExpression(BuiltInEnum.TRUE, this);
		}
		if (_txt.equalsIgnoreCase("'0'B"))
		{
			return generator.newBuiltInExpression(BuiltInEnum.FALSE, this);
		}
		throw new RuntimeException("Unexpected BIT value: " + _txt);
	}
}