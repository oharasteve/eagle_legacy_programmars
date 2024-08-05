// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 17, 2010

package com.eagle.programmar.CSharp;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleLanguage;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.CSharp.CSharp_Class.CSharp_ClassElement;
import com.eagle.programmar.CSharp.Directives.CSharp_Directive;
import com.eagle.programmar.CSharp.Terminals.CSharp_Comment;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;

public class CSharp_Program extends EagleLanguage implements EagleRunnable
{
	public static final String CSHARP = "CSharp";

	public CSharp_Program()
	{
		super(CSHARP, new CSharp_Syntax());
	}

	@Override
	public String getDocRoot()
	{
		return "http://java.sun.com/docs/books/jls/third_edition/html/";
	}

	public static final String[] MODIFIERS = new String[] {
			"abstract",
			"async",
			"const",
			"delegate",
			"event",
			"extern",
			"final",
			"internal",
			"lock",
			"override",
			"partial",
			"private",
			"protected",
			"public",
			"readonly",
			"ref",
			"sealed",
			"static",
			"unsafe",
			"virtual",
	};

	public @S(10) @OPT @NEWLINE TokenList<CSharp_Comment> comments1;
	public @S(20) @OPT @BLANKLINE TokenList<CSharp_NamespaceOrClassEntry> myClasses;

	public static class CSharp_NamespaceOrClassEntry extends TokenChooser
	{
		public @CHOICE @NEWLINE CSharp_Using XXimportList;
		public @CHOICE @NEWLINE CSharp_Comment XXcomment;
		public @CHOICE @NEWLINE CSharp_Namespace XXmyNamespace;
		public @CHOICE @NEWLINE CSharp_Class XXclass;
		public @CHOICE @NEWLINE CSharp_Annotation XXannotation;
		public @CHOICE @NEWLINE CSharp_Directive XXdirective;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// First pass, just collect all the method definitions
		for (CSharp_NamespaceOrClassEntry nsClass : myClasses._elements)
		{
			if (nsClass.getWhich() instanceof CSharp_Class)
			{
				CSharp_Class cls = (CSharp_Class) nsClass.getWhich();
				for (CSharp_ClassElement element : cls.elements._elements)
				{
					if (element.getWhich() instanceof CSharp_Method)
					{
						CSharp_Method meth = (CSharp_Method) element.getWhich();
						interpreter._functionList.put(meth.methodName.getValue(), meth);
						if (interpreter._TRACE)
						{
							System.err.println("*** Found CSharp method " + meth.methodName.getValue());
						}
					}
				}
			}
		}

		// Second pass, run any stuff in the outermost class
		for (CSharp_NamespaceOrClassEntry nsClass : myClasses._elements)
		{
			if (nsClass.getWhich() instanceof CSharp_Class)
			{
				CSharp_Class cls = (CSharp_Class) nsClass.getWhich();
				interpreter.tryToInterpret(cls);
			}
		}
	}
}
