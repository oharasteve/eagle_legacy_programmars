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
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableFunction;
import com.eagle.transform.EagleTransformableProgram;
import com.eagle.transform.EagleTransformer;

public class Java_Program extends AbstractLanguage
		implements EagleRunnable, EagleTransformableProgram
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
	
	@Override
	public AbstractLanguage transformProgram(EagleTransformer transformer,
			EagleGenerator generator)
	{
		// First (and only) pass, transform the main method
		for (Java_ClassOrEnum elt1 : classOrEnumList._elements)
		{
			AbstractToken which1 = elt1.getWhich();
			if (which1 instanceof Java_Class)
			{
				Java_Class cls = (Java_Class) which1;
				for (Java_ClassElement elt2 : cls.elements._elements)
				{
					AbstractToken which2 = elt2.getWhich();
					if (which2 instanceof EagleTransformableFunction)
					{
						EagleTransformableFunction transformable = (EagleTransformableFunction) which2;
						transformable.transformFunction(transformer, generator);
					}
					else
					{
						// Probably global (class-level) data
						if (which2 instanceof Java_Statement)
						{
							Java_Statement stmt = (Java_Statement) which2;
							AbstractToken which3 = stmt.getWhich();
							if (which3 instanceof Java_Data)
							{
								Java_Data data = (Java_Data) which3;
								AbstractStatement stmt3 = data.transformStaticData(transformer, generator);
								generator.addStatement(stmt3, elt1);
							}
						}
					}
				}
			}
		}
		
		// Not needed for C# or Java, but Python needs this
		generator.addCallToMain();
		
		return generator.getTransfomedProgram();
	}
	
	public void addClass(Java_Class cls)
	{
		Java_ClassOrEnum entry = new Java_ClassOrEnum();
		entry.setWhich(cls);
	
		if (this.classOrEnumList == null)
		{
			this.classOrEnumList = new TokenList<Java_ClassOrEnum>();
			this.classOrEnumList.setPresent(true);
		}
		this.classOrEnumList.addToken(entry);
	}
	
	public void addComment(Java_Comment comment)
	{
		if (this.comments1 == null)
		{
			this.comments1 = new TokenList<Java_Comment>();
		}
		this.comments1.addToken(comment);
	}
}
