// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 17, 2010

package com.eagle.programmar.CSharp;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleLanguage;
import com.eagle.core.EagleRunnable;
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
		public @CHOICE @NEWLINE CSharp_Using importList;
		public @CHOICE @NEWLINE CSharp_Comment comment;
		public @CHOICE @NEWLINE CSharp_Namespace myNamespace;
		public @CHOICE @NEWLINE CSharp_Class elems;
		public @CHOICE @NEWLINE CSharp_Annotation annotation;
		public @CHOICE @NEWLINE CSharp_Directive directive;
	}

	public static class CSharp_ProgramElems extends TokenChooser
	{
		public @CHOICE @NEWLINE CSharp_Namespace myNamespace;
		public @CHOICE @NEWLINE CSharp_Using using;
		public @CHOICE @NEWLINE CSharp_Comment comment;
		public @CHOICE @NEWLINE CSharp_Class myClass;
		public @CHOICE @NEWLINE CSharp_Enum enumeration;
		public @CHOICE @NEWLINE CSharp_Method method;
		public @CHOICE @NEWLINE CSharp_Directive directive;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		interpreter.tryToInterpret(myClasses.first());
	}
}
