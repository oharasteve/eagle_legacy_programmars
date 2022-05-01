// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 8, 2011

package com.eagle.programmar.C;

import com.eagle.core.EagleLanguage;
import com.eagle.core.EagleSyntax;
import com.eagle.programmar.C.Terminals.C_Comment;
import com.eagle.programmar.CMacro.CMacro_StatementOrComment;
import com.eagle.programmar.CMacro.CMacro_Syntax;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;

public class C_Program extends EagleLanguage
{
	public static final String C = "C";
	
	public C_Program()
	{
		super(C, new C_Syntax());
	}
	
	// Called from C++ constructor
	public C_Program(String name, EagleSyntax syntax)
	{
		super(name, syntax);
	}
	
	@Override
	public String getDocRoot()
	{
		return "http://www.gnu.org/s/gnu-c-manual/gnu-c-manual.html";
	}
	
	private static String[] primitives = new String[] {
			"char",
			"double",
			"float",
			"int",
			"long",
			"short",
			"void"
	};
	
	// Careful, this gets added to in some projects
	private static String[] modifiers = new String[] {
			"const",
			"constexpr",
			"extern",
			"inline",
			"register",
			"static",
			"virtual",
			"volatile",
			"__inline"
	};

	public static void addPrimitive(String primitive)
	{
		// Append to an array
		int n = primitives.length;
		String[] temp = new String[n+1];
		for (int i = 0; i < n; i++)
		{
			if (primitives[i].equals(primitive)) return;
			temp[i] = primitives[i];
		}
		temp[n] = primitive;
		primitives = temp;
	}
	
	public static void addModifier(String modifier)
	{
		// Append to an array
		int n = modifiers.length;
		String[] temp = new String[n+1];
		for (int i = 0; i < n; i++)
		{
			if (modifiers[i].equals(modifier)) return;
			temp[i] = modifiers[i];
		}
		temp[n] = modifier;
		modifiers = temp;
	}
	
	public static String[] getPrimitives()
	{
		return primitives;
	}
	
	public static String[] getModifiers()
	{
		return modifiers;
	}
	
	// Note that CPlus_Program has an @S(5)
	public @S(10) @OPT TokenList<C_StatementOrComment> elements;

	public static class C_StatementOrComment extends TokenChooser
	{
		public @CHOICE C_Comment comment;
		public @CHOICE C_TypeDef typeDef;
		public @LAST C_Data data;
		public @CHOICE C_Function function;
		public @LAST C_Statement statement;
		public @CHOICE C_Enum cenum;
		public @CHOICE @SYNTAX(CMacro_Syntax.class) CMacro_StatementOrComment macro;
	}
}
