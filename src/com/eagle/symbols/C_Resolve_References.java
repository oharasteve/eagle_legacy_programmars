// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 8, 2011

package com.eagle.symbols;

import java.util.ArrayList;

import com.eagle.core.EagleSyntax;
import com.eagle.programmar.C.C_Function;
import com.eagle.programmar.C.C_Program;
import com.eagle.programmar.C.Symbols.C_Field_Definition;
import com.eagle.programmar.C.Symbols.C_Function_Definition;
import com.eagle.programmar.C.Symbols.C_Identifier_Reference;
import com.eagle.programmar.C.Symbols.C_Label_Definition;
import com.eagle.programmar.C.Symbols.C_Type_Definition;
import com.eagle.programmar.C.Symbols.C_Variable_Definition;
import com.eagle.programmar.CMacro.Symbols.CMacro_Define_Definition;
import com.eagle.programmar.CMacro.Symbols.CMacro_Identifier_Reference;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.EagleScope;

public class C_Resolve_References extends Eagle_Resolve_References
{
	public void resolveReferences(C_Program program)
	{
		EagleScope scope = program.getScope();
		EagleSyntax syntax = program.getSyntax();

		// Handle all the globally scoped references
		connectReferences(syntax, scope, program, CMacro_Define_Definition.class, CMacro_Identifier_Reference.class);
		connectReferences(syntax, scope, program, C_Function_Definition.class, C_Identifier_Reference.class);
		connectReferences(syntax, scope, program, C_Type_Definition.class, C_Identifier_Reference.class);
		connectReferencesOutside(syntax, scope, program, C_Variable_Definition.class, C_Identifier_Reference.class, C_Function.class);
		connectReferences(syntax, scope, program, C_Field_Definition.class, C_Identifier_Reference.class);
		
		// Find all the functions in the program
		ArrayList<AbstractToken> functions = findAllInstances(program, C_Function.class);
		for (AbstractToken fn : functions)
		{
			// Handle all the function-scoped references
			connectReferences(syntax, scope, fn, C_Label_Definition.class, C_Identifier_Reference.class);
			connectReferences(syntax, scope, fn, C_Variable_Definition.class, C_Identifier_Reference.class);
		}
	}
}
