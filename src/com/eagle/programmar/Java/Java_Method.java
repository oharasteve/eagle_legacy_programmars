// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 18, 2010

package com.eagle.programmar.Java;

import com.eagle.generate.EagleGenerator.PrivacyEnum;
import com.eagle.generate.EagleGenerator.StaticEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.metrics.CallMetrics;
import com.eagle.programmar.Java.Java_ParameterList.Java_MethodParameter;
import com.eagle.programmar.Java.Java_Type.Java_GenericType;
import com.eagle.programmar.Java.Statements.Java_StatementBlock;
import com.eagle.programmar.Java.Symbols.Java_Current_Class_Reference;
import com.eagle.programmar.Java.Symbols.Java_Method_Definition;
import com.eagle.programmar.Java.Symbols.Java_Variable_Definition;
import com.eagle.programmar.Java.Terminals.Java_Comment;
import com.eagle.programmar.Java.Terminals.Java_Keyword;
import com.eagle.programmar.Java.Terminals.Java_KeywordChoice;
import com.eagle.scope.EagleScope;
import com.eagle.scope.EagleScope.EagleScopeInterface;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractMethod;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Java_Method extends TokenSequence
		implements AbstractMethod, AbstractFunction, EagleRunnable, EagleScopeInterface
{
	public @S(10) @OPT @BLANKLINE TokenList<Java_Comment> comments;
	public @S(20) @OPT Java_Annotation annotation;
	public @S(30) @OPT TokenList<Java_MethodModifier> modifiers;
	public @S(40) Java_MethodTypeAndName typeAndName;
	public @S(50) @OPT TokenList<Java_EmptyBrackets> brackets;
	public @S(60) @OPT Java_MethodDefault methodDefault;
	public @S(70) @OPT Java_MethodThrows jthrows;
	public @S(80) @OPT Java_Comment comment;
	public @S(90) Java_MethodBody body;

	public @SKIP CallMetrics _metrics = null;

	public static class Java_MethodTypeAndName extends TokenChooser
	{
		public @CHOICE Java_MethodType XXmethodType;
		public @CHOICE Java_MethodGeneric XXmethodGeneric;
		public @CHOICE Java_MethodTwoTypes XXmethodTwoTypes;
	}

	public static class Java_MethodType extends TokenSequence
	{
		public @S(10) Java_Type jtype;
		public @S(20) Java_Method_Definition methodName;
		public @S(30) @NOSPACE Java_ParameterList parameters;
	}

	public static class Java_MethodGeneric extends TokenSequence
	{
		public @S(10) Java_GenericType genericType;
		public @S(20) Java_Method_Definition methodName;
		public @S(30) Java_ParameterList parameters;
	}

	public static class Java_MethodTwoTypes extends TokenSequence
	{
		public @S(10) Java_GenericType genericType;
		public @S(20) Java_Type jtype;
		public @S(30) Java_Method_Definition methodName;
		public @S(40) Java_ParameterList parameters;
	}

	public static class Java_EmptyBrackets extends TokenSequence
	{
		public @S(10) PunctuationLeftBracket leftBracket;
		public @S(20) PunctuationRightBracket rightBracket;
	}

	public static class Java_MethodDefault extends TokenSequence
	{
		public @S(10) Java_Keyword DEFAULT = new Java_Keyword("default");
		public @S(20) @OPT Java_Expression expr;
	}

	public static class Java_MethodModifier extends TokenChooser
	{
		public @FIRST @NEWLINE Java_Comment XXcomment;
		public @CHOICE Java_KeywordChoice XXmodifier = new Java_KeywordChoice(Java_Program.MODIFIERS);
		public @CHOICE Java_Annotation XXannotation;
	}

	public static class Java_MethodThrows extends TokenSequence
	{
		public @S(10) Java_Keyword jthrows = new Java_Keyword("throws");
		public @S(20) SeparatedList<Java_Expression, PunctuationComma> jclass;
	}

	public static class Java_MethodBody extends TokenChooser
	{
		public @CHOICE PunctuationSemicolon XXsemicolon;
		public @CHOICE Java_MethodImplementation XXmethodImplementation;
	}
	
	public static class Java_MethodImplementation extends TokenSequence
	{
		public @S(10) @OPT @NEWLINE TokenList<Java_Comment> comment1;
		public @S(20) Java_StatementBlock block;
		public @S(30) @OPT TokenList<Java_Comment> comment2;
		public @S(40) @OPT @CURIOUS("Extra semicolon") PunctuationSemicolon semicolon2;
	}

	public static class Java_Constructor extends TokenSequence
	{
		public @S(10) @OPT @BLANKLINE TokenList<Java_Annotation> annotation;
		public @S(20) @OPT TokenList<Java_MethodModifier> modifiers;
		public @S(30) Java_Current_Class_Reference constructorName;
		public @S(40) @NOSPACE Java_ParameterList parameters;
		public @S(50) @OPT Java_MethodThrows jthrows;
		public @S(60) @OPT Java_Comment comment;
		public @S(70) Java_MethodBody body;
	}

	private @SKIP EagleScope _scope = new EagleScope(this, Java_Syntax.IS_CASE_SENSITIVE);

	@Override
	public EagleScope getScope()
	{
		return _scope;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		AbstractToken which = typeAndName.getWhich();
		if (which instanceof Java_MethodType)
		{
			Java_Method_Definition methodName = ((Java_MethodType) which).methodName;
		
			if (_metrics == null)
			{
				_metrics = new CallMetrics(interpreter._metrics, methodName.getValue(), this);
			}
	
			// Nothing to do here. Only run methods when they are called / invoked.
			// Exception is 'main'
			if (methodName.getValue().equals("main"))
			{
				interpreter.callingFunction("main", this);
				which = body.getWhich();
				if (which instanceof Java_MethodImplementation)
				{
					Java_MethodImplementation impl = (Java_MethodImplementation) which;
					for (Java_StatementOrComment stmt : impl.block.statements._elements)
					{
						interpreter.tryToInterpret(stmt);
					}
				}
				interpreter.completedFunction("main", this);
			}
		}
	}
	
	public void newJavaMethod(PrivacyEnum privacy, StaticEnum isStatic,
			Java_Type returnType, String methodName)
	{
		this.setPresent(true);
		this.modifiers = new TokenList<Java_MethodModifier>();
		
		Java_MethodModifier modifier1 = null;
		switch (privacy)
		{
		case NONE:
			break;
		case PUBLIC:
			modifier1 = new Java_MethodModifier();
			modifier1.setWhich(new Java_KeywordChoice("public"));
			break;
		case PRIVATE:
			modifier1 = new Java_MethodModifier();
			modifier1.setWhich(new Java_KeywordChoice("private"));
			break;
		default:
			throw new RuntimeException("Can't handle privacy: " + privacy);
		}
		if (modifier1 != null)
		{
			this.modifiers.addToken(modifier1);
		}

		switch (isStatic)
		{
		case NONE:
			break;
		case STATIC:
			Java_MethodModifier modifier2 = new Java_MethodModifier();
			modifier2.setWhich(new Java_KeywordChoice("static"));
			this.modifiers.addToken(modifier2);
			break;
		default:
			throw new RuntimeException("Can't handle static: " + isStatic);
		}
		
		this.typeAndName = new Java_MethodTypeAndName();
		Java_MethodType methodType = new Java_MethodType();
		this.typeAndName.setWhich(methodType);
		if (returnType == null)
		{
			methodType.jtype = Java_Type.newPrimitiveType("void");
		}
		else
		{
			methodType.jtype = returnType;
		}
		
		methodType.parameters = new Java_ParameterList();
		methodType.parameters.setPresent(true);
		methodType.parameters.leftParen = new PunctuationLeftParen();
		methodType.parameters.rightParen = new PunctuationRightParen();
		
		this.body = new Java_MethodBody();
		Java_MethodImplementation impl = new Java_MethodImplementation();
		impl.block = new Java_StatementBlock();
		impl.block.leftBrace = new PunctuationLeftBrace();
		impl.block.statements = new TokenList<Java_StatementOrComment>();
		impl.block.rightBrace = new PunctuationRightBrace();
		this.body.setWhich(impl);
		
		methodType.methodName = new Java_Method_Definition();
		methodType.methodName.setValue(methodName);
	}
	
	public void addMethodParameter(AbstractType type, String name)
	{
		Java_MethodParameter param = new Java_MethodParameter();
		param.setPresent(true);
		param.id = new Java_Variable_Definition();
		param.id.setValue(name);
		param.jtype = (Java_Type) type;
		
		AbstractToken which = this.typeAndName.getWhich();
		if (which instanceof Java_MethodType)
		{
			Java_MethodType methType = (Java_MethodType) which;
			if (methType.parameters.params == null)
			{
				methType.parameters.params =
						new SeparatedList<Java_MethodParameter, PunctuationComma>();
			}
			if (methType.parameters.params.size() > 0)
			{
				methType.parameters.params.addSecondaryElement(new PunctuationComma());
			}
			methType.parameters.params.addPrimaryElement(param);
		}
		else
		{
			throw new RuntimeException("Can't handle: " + which);
		}
	}
	
	public void addComment(Java_Comment comment)
	{
		if (this.comments == null)
		{
			this.comments = new TokenList<Java_Comment>();
		}
		this.comments.addToken(comment);
	}
}
