// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 7, 2024

package com.eagle.programmar.Fortran;

import com.eagle.core.EagleInterpreter;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Fortran.Symbols.Fortran_Variable_Reference;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.punctuation.PunctuationComma;

public class Fortran_Format
{
	public static String format(EagleInterpreter interpreter, String fmt,
			SeparatedList<Fortran_Variable_Reference, PunctuationComma> parameters)
	{
		StringBuffer sb = new StringBuffer();
		if (fmt.equals("'(I5)'"))
		{
			Fortran_Variable_Reference var = parameters.first();
			EagleValue val = interpreter._symbolTable.findSymbol(var.getValue());
			int num = val.forceIntegerValue();
			sb.append(String.format("%5d", Integer.valueOf(num)));	// Boxing stinks in Java
		}
		else
		{
			throw new RuntimeException("Need to implement Fortran format: " + fmt);
		}
		return sb.toString();
	}
}
