// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 18, 2010

package com.eagle.programmar.CSharp;

import com.eagle.generate.EagleGenerator.PrivacyEnum;
import com.eagle.generate.EagleGenerator.StaticEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.metrics.CallMetrics;
import com.eagle.programmar.CSharp.CSharp_Type.CSharp_GenericType;
import com.eagle.programmar.CSharp.Statements.CSharp_StatementBlock;
import com.eagle.programmar.CSharp.Symbols.CSharp_Method_Definition;
import com.eagle.programmar.CSharp.Symbols.CSharp_Type_Definition;
import com.eagle.programmar.CSharp.Symbols.CSharp_Variable_Definition;
import com.eagle.programmar.CSharp.Terminals.CSharp_Comment;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.programmar.CSharp.Terminals.CSharp_KeywordChoice;
import com.eagle.programmar.CSharp.Terminals.CSharp_Punctuation;
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
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class CSharp_Method extends TokenSequence implements
			AbstractMethod, AbstractFunction, EagleRunnable, EagleScopeInterface
{
	public @S(10) @OPT @NEWLINE TokenList<CSharp_Comment> comments;
	public @S(20) @OPT TokenList<CSharp_Annotation> annotation;
	public @S(30) @OPT TokenList<CSharp_MethodModifier> modifiers;
	public @S(40) @OPT TokenList<CSharp_Comment> comment2;
	public @S(50) CSharp_Type returnType;
	public @S(60) @OPT CSharp_Keyword GLOBAL = new CSharp_Keyword("global");
	public @S(70) @OPT CSharp_Punctuation colon2 = new CSharp_Punctuation("::");
	public @S(80) CSharp_Method_Definition methodName;
	public @S(90) @OPT CSharp_GenericType generic;
	public @S(100) @OPT CSharp_MethodParameters parameters;
	public @S(110) @OPT TokenList<CSharp_MethodWhere> where;
	public @S(120) @NEWLINE CSharp_MethodBody body;
	public @S(130) @OPT @CURIOUS("Extra semicolon") PunctuationSemicolon semicolon;

	public static class CSharp_MethodParameters extends TokenSequence
	{
		public @S(10) @NOSPACE PunctuationLeftParen leftParen;
		public @S(20) @OPT @NOSPACE SeparatedList<CSharp_MethodParameter, PunctuationComma> params;
		public @S(30) @NOSPACE PunctuationRightParen rightParen;
		public @S(40) @OPT CSharp_Comment comment3;
	}

	public static class CSharp_MethodModifier extends TokenSequence
	{
		public @S(10) CSharp_KeywordChoice modifier = new CSharp_KeywordChoice(
				CSharp_Program.MODIFIERS);
	}

	public static class CSharp_MethodParameter extends TokenSequence
	{
		public @S(10) @OPT CSharp_Annotation annotation;
		public @S(20) @OPT CSharp_KeywordChoice passBy = new CSharp_KeywordChoice(
				"ref", "out", "this", "params");
		public @S(30) CSharp_Type cstype;
		public @S(40) CSharp_Variable_Definition id;
		public @S(50) @OPT CSharp_Punctuation emptySubscript = new CSharp_Punctuation("[]");
		public @S(60) @OPT CSharp_MethodParamDefault defValue;

		public static class CSharp_MethodParamDefault extends TokenSequence
		{
			public @S(10) PunctuationEquals equals;
			public @S(20) CSharp_Expression value;
		}
	}

	public static class CSharp_MethodWhere extends TokenSequence
	{
		public @S(10) CSharp_Keyword WHERE = new CSharp_Keyword("where");
		public @S(20) CSharp_Type_Definition id;
		public @S(30) PunctuationColon colon;
		public @S(40) CSharp_Type type;
	}

	public static class CSharp_MethodBody extends TokenChooser
	{
		public @CHOICE PunctuationSemicolon XXsemicolon;
		public @CHOICE CSharp_MethodImplementation XXimplementation;

		public @CHOICE static class CSharp_MethodLambda extends TokenSequence
		{
			public @S(10) CSharp_Punctuation equalsGreater = new CSharp_Punctuation("=>");
			public @S(20) @OPT CSharp_Keyword REF = new CSharp_Keyword("ref");
			public @S(30) CSharp_Expression returnValue;
		}
	}

	public @SKIP CallMetrics _metrics = null;

	private @SKIP EagleScope _scope = new EagleScope(this, CSharp_Syntax.IS_CASE_SENSITIVE);

	@Override
	public EagleScope getScope()
	{
		return _scope;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (_metrics == null)
		{
			_metrics = new CallMetrics(interpreter._metrics, methodName.getValue(), this);
		}

		// Nothing to do here. Only run methods when they are called / invoked.
		// Exception is 'Main'
		if (methodName.getValue().equals("Main"))
		{
			interpreter.callingFunction("main", this);
			AbstractToken which = body.getWhich();
			if (which instanceof CSharp_MethodImplementation)
			{
				CSharp_MethodImplementation impl = (CSharp_MethodImplementation) which;
				for (CSharp_StatementOrComment stmt : impl.block.statements._elements)
				{
					interpreter.tryToInterpret(stmt);
				}
			}
			interpreter.completedFunction("main", this);
		}
	}
	
	public void newCSharpMethod(PrivacyEnum privacy,
			StaticEnum isStatic, CSharp_Type returnType, String methodName)
	{
		this.modifiers = new TokenList<CSharp_MethodModifier>();
		
		CSharp_MethodModifier modifier1 = new CSharp_MethodModifier();
		switch (privacy)
		{
		case PUBLIC:
			modifier1.modifier = new CSharp_KeywordChoice("public");
			break;
		case PRIVATE:
		case NONE:
			modifier1.modifier = new CSharp_KeywordChoice("private");
			break;
		default:
			throw new RuntimeException("Can't handle privacy: " + privacy);
		}
		this.modifiers.addToken(modifier1);

		switch (isStatic)
		{
		case NONE:
			break;
		case STATIC:
			CSharp_MethodModifier modifier2 = new CSharp_MethodModifier();
			modifier2.modifier = new CSharp_KeywordChoice("static");
			this.modifiers.addToken(modifier2);
			break;
		default:
			throw new RuntimeException("Can't handle static: " + isStatic);
		}
		
		if (returnType == null)
		{
			this.returnType = CSharp_Type.newPrimitiveType("void");
		}
		else
		{
			this.returnType = returnType;
		}
		
		this.parameters = new CSharp_MethodParameters();
		this.parameters.setPresent(true);
		this.parameters.leftParen = new PunctuationLeftParen();
		this.parameters.params = new SeparatedList<CSharp_MethodParameter, PunctuationComma>();
		this.parameters.rightParen = new PunctuationRightParen();
		
		this.body = new CSharp_MethodBody();
		CSharp_MethodImplementation impl = new CSharp_MethodImplementation();
		impl.block = new CSharp_StatementBlock();
		impl.block.leftBrace = new PunctuationLeftBrace();
		impl.block.statements = new TokenList<CSharp_StatementOrComment>();
		impl.block.rightBrace = new PunctuationRightBrace();
		this.body.setWhich(impl);
		
		this.methodName = new CSharp_Method_Definition();
		this.methodName.setValue(methodName);
	}
	
	public void addMethodParameter(AbstractType type, String name)
	{
		CSharp_MethodParameter param = new CSharp_MethodParameter();
		param.setPresent(true);
		param.id = new CSharp_Variable_Definition();
		param.id.setValue(name);
		param.cstype = (CSharp_Type) type;
		
		if (this.parameters.params.size() > 0)
		{
			this.parameters.params.addSecondaryElement(new PunctuationComma());
		}
		this.parameters.params.addPrimaryElement(param);
	}
	
	public void addComment(CSharp_Comment comment)
	{
		if (this.comments == null)
		{
			this.comments = new TokenList<CSharp_Comment>();
		}
		this.comments.addToken(comment);
	}
}
