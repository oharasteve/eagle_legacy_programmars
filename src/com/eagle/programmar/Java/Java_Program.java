// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 17, 2010

package com.eagle.programmar.Java;

import com.eagle.core.AbstractLanguage;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Java.Java_Class.Java_ClassElement;
import com.eagle.programmar.Java.Java_Method.Java_MethodType;
import com.eagle.programmar.Java.Symbols.Java_Method_Definition;
import com.eagle.programmar.Java.Terminals.Java_Comment;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Java_Program extends AbstractLanguage implements EagleRunnable
{
	public static final String JAVA = "Java";

	public Java_Program()
	{
		super(JAVA, new Java_Syntax());
	}

	@Override
	public String getDocRoot()
	{
		return "http://docs.oracle.com/javase/specs/jls/se5.0/html/";
	}

	public static final String[] MODIFIERS = new String[] {
			"abstract",
			"default",
			"final",
			"native",
			"private",
			"protected",
			"public",
			"static",
			"strictfp",
			"synchronized",
			"transient",
			"volatile"
	};

	public @S(10) @OPT TokenList<Java_Comment> comments1;
	public @S(20) @OPT Java_Annotation annotation;
	public @S(30) @OPT TokenList<Java_Comment> comments2;
	public @S(40) @OPT Java_Package jpackage;
	public @S(50) @OPT TokenList<Java_ImportOrComment> jimportList;
	public @S(60) @OPT TokenList<Java_ClassOrEnum> classOrEnumList;

	public static class Java_ClassOrEnum extends TokenChooser
	{
		public @CHOICE Java_Class XXclass;
		public @CHOICE Java_Enum XXenum;
	}

	public static class Java_ImportOrComment extends TokenChooser
	{
		public @CHOICE @NEWLINE Java_Comment XXcomment;
		public @CHOICE @NEWLINE Java_Import XXimport;
		public @CHOICE @NEWLINE @CURIOUS("Extra Semicolon") PunctuationSemicolon XXsemicolon;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// First pass, just collect all the method definitions
		for (Java_ClassOrEnum classOrEnum : classOrEnumList._elements)
		{
			if (classOrEnum.getWhich() instanceof Java_Class)
			{
				Java_Class cls = (Java_Class) classOrEnum.getWhich();
				for (Java_ClassElement element : cls.elements._elements)
				{
					if (element.getWhich() instanceof Java_Method)
					{
						Java_Method meth = (Java_Method) element.getWhich();
						AbstractToken which = meth.typeAndName.getWhich();
						if (which instanceof Java_MethodType)
						{
							Java_Method_Definition methodName = ((Java_MethodType) which).methodName;
							interpreter.addFunction(methodName.getValue(), meth);
						}
					}
				}
			}
		}

		// Second pass, run any stuff in the outermost class
		for (Java_ClassOrEnum classOrEnum : classOrEnumList._elements)
		{
			if (classOrEnum.getWhich() instanceof Java_Class)
			{
				Java_Class cls = (Java_Class) classOrEnum.getWhich();
				interpreter.tryToInterpret(cls);
			}
		}
	}
	
	public static Java_Program newJavaProgram(Java_Class cls, String pkg)
	{
		Java_ClassOrEnum entry = new Java_ClassOrEnum();
		entry.setWhich(cls);
	
		Java_Program prog = new Java_Program();
		prog.classOrEnumList = new TokenList<Java_ClassOrEnum>();
		prog.classOrEnumList.setPresent(true);
		prog.classOrEnumList.addToken(entry);

		prog.jpackage = Java_Package.newPackage(pkg);
		prog.jpackage.setPresent(true);
		
		return prog;
	}
}
