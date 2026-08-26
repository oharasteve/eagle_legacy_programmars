// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 8, 2011

package com.eagle.programmar.C;

import java.util.Collection;

import com.eagle.core.AbstractLanguage;
import com.eagle.core.EagleSyntax;
import com.eagle.generate.EagleGenerator;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.C.C_Function.C_Function_TypeAndName;
import com.eagle.programmar.C.Statements.C_AsmVolatile;
import com.eagle.programmar.C.Terminals.C_Comment;
import com.eagle.programmar.CMacro.CMacro_StatementOrComment;
import com.eagle.programmar.CMacro.CMacro_Syntax;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleTransformableProgram;
import com.eagle.transform.EagleTransformer;

public class C_Program extends AbstractLanguage
		implements EagleRunnable, EagleTransformableProgram
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
			"auto",
			"bool",
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
			"final",
			"inline",
			"mutable",
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
		for (int i = 0; i < n; i++)
		{
			if (primitives[i].equals(primitive)) return;
		}

		String[] temp = new String[n + 1];
		for (int i = 0; i < n; i++)
		{
			temp[i] = primitives[i];
		}
		temp[n] = primitive;
		primitives = temp;
	}

	public static void addModifier(String modifier)
	{
		// Append to an array
		int n = modifiers.length;
		for (int i = 0; i < n; i++)
		{
			if (modifiers[i].equals(modifier)) return;
		}

		String[] temp = new String[n + 1];
		for (int i = 0; i < n; i++)
		{
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

	// Note that CPlus_Program has an S(9) and ObjectiveC_Program has an S(8)
	public @S(10) @OPT TokenList<C_StatementOrComment> elements;

	public static class C_StatementOrComment extends TokenChooser
	{
		public @CHOICE C_Comment XXcomment;
		public @CHOICE C_TypeDef XXtypeDef;
		public @LAST C_Data XXdata;
		public @CHOICE C_Function XXfunction;
		public @LAST C_Statement XXstatement;
		public @CHOICE C_Enum XXenum;
		public @CHOICE C_AsmVolatile XXasmVolatile;
		public @CHOICE @SYNTAX(CMacro_Syntax.class) CMacro_StatementOrComment XXmacro;

		// NOTE: C++ adds the 'extern' statement here. See the constructor in
		// CPlus_Program.java
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// First pass, just collect all the function definitions
		for (C_StatementOrComment elt : elements._elements)
		{
			AbstractToken which = elt.getWhich();
			if (which instanceof C_Function)
			{
				C_Function fn = (C_Function) which;
				{
					which = fn.typeName.getWhich();
					if (which instanceof C_Function_TypeAndName)
					{
						C_Function_TypeAndName typeName = (C_Function_TypeAndName) which;
						interpreter.addFunction(typeName.functionName.getValue(), fn);
					}
				}
			}
		}

		// Second pass, execute the program
		for (C_StatementOrComment element : elements._elements)
		{
			interpreter.tryToInterpret(element);
		}
	}

	@Override
	public AbstractLanguage transformProgram(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		// First pass, transform all the Function definitions
		for (C_StatementOrComment elt : elements._elements)
		{
			AbstractToken which1 = elt.getWhich();
			if (which1 instanceof C_Function)
			{
				C_Function func = (C_Function) which1;
				func.transformFunction(transformer, generator);
			}
		}

		// Second pass, transform all the data and logic
		for (C_StatementOrComment element : elements._elements)
		{
			AbstractToken which3 = element.getWhich();
			if (which3 instanceof C_Statement)
			{
				C_Statement stmt = (C_Statement) which3;
				Collection<AbstractStatement> newStmts = transformer.transformStatement(
						generator, stmt.getWhich());
				if (newStmts != null)
				{
					for (AbstractStatement newStmt : newStmts)
					{
						generator.addStatement(newStmt, stmt);
					}
				}
			}
			else if (which3 instanceof C_Data)
			{
				C_Data data = (C_Data) which3;
				Collection<AbstractStatement> newStmts = transformer.transformStatement(
						generator, data.getWhich());
				if (newStmts != null)
				{
					for (AbstractStatement newStmt : newStmts)
					{
						generator.addStatement(newStmt, data);
					}
				}
			}
		}

		// Not needed for C# or CSharp, but Python needs this
		generator.addCallToMain();

		return generator.getTransformedProgram();
	}
}
