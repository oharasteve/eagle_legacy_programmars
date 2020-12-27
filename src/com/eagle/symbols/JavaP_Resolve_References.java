// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 26, 2020

package com.eagle.symbols;

import com.eagle.core.EagleLanguage;
import com.eagle.core.EagleSyntax;
import com.eagle.programmar.JavaP.JavaP_Program;
import com.eagle.programmar.JavaP.Symbols.JavaP_Symbol_Definition;
import com.eagle.programmar.JavaP.Symbols.JavaP_Symbol_Reference;
import com.eagle.tokens.EagleScope;

public class JavaP_Resolve_References extends Eagle_Resolve_References
{
	public void resolveReferences(EagleLanguage language)
	{
		JavaP_Program program = (JavaP_Program) language;
		EagleScope scope = program.getScope();
		EagleSyntax syntax = program.getSyntax();
		
		// Handle all the globally scoped references
		connectReferences(syntax, scope, program, JavaP_Symbol_Definition.class, JavaP_Symbol_Reference.class);
	}
}
