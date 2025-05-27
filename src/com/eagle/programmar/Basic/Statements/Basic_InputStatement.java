// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 8, 2025

package com.eagle.programmar.Basic.Statements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleDouble;
import com.eagle.math.EagleInteger;
import com.eagle.programmar.Basic.Basic_Variable;
import com.eagle.programmar.Basic.Terminals.Basic_KeywordChoice;
import com.eagle.programmar.Basic.Terminals.Basic_Literal;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Basic_InputStatement extends TokenSequence
		implements EagleRunnable, AbstractStatement
{
	public @S(10) Basic_KeywordChoice INPUT = new Basic_KeywordChoice("INPUT", "INP");
	public @S(20) @OPT Basic_Literal prompt;
	public @S(30) @OPT PunctuationSemicolon semicolon;
	public @S(40) SeparatedList<Basic_Variable,PunctuationComma> vars;

	public static @SKIP BufferedReader _br = null;
	
	// Force it to start reading fom stdin again
	public static void resetReader()
	{
		_br = null;
	}
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (prompt != null && prompt.isPresent())
		{
			String val = prompt.getValue().replaceAll("\"", "");
			System.out.print(val);
		}
		System.out.print("?");

		if (_br == null)
		{
			_br = new BufferedReader(new InputStreamReader(System.in));
		}

		String line = null;
		try
		{
            line = _br.readLine();
        }
		catch (IOException ex)
		{
            throw new RuntimeException("Error reading from stdin", ex);
        }
		
		if (line == null)
		{
			throw new RuntimeException("No input given to INPUT");
		}

		System.out.println(line);
		String[] pieces = line.split(",");
		int piecesGot = pieces.length;
		int piecesExpected = vars.getPrimaryCount();
		if (piecesGot != piecesExpected)
		{
			throw new RuntimeException("Expected line with #pieces=" + piecesExpected +
					" but got " + piecesGot);
		}
		
		for (int i = 0; i < piecesGot; i++)
		{
			Basic_Variable var = vars.getPrimaryElement(i);
			String piece = pieces[i].trim();
			if (piece.indexOf('.') >= 0)
			{
				double valDbl = Double.parseDouble(piece);
				var.assignValue(interpreter, new EagleDouble(valDbl));
			}
			else
			{
				int valInt = Integer.parseInt(piece);
				var.assignValue(interpreter, new EagleInteger(valInt));
			}
		}
	}
}
