// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 8, 2011

package com.eagle.programmar.C;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.metrics.CallMetrics;
import com.eagle.programmar.C.C_Data.C_FunctionPointer;
import com.eagle.programmar.C.C_Program.C_StatementOrComment;
import com.eagle.programmar.C.Symbols.C_Function_Definition;
import com.eagle.programmar.C.Symbols.C_Variable_Definition;
import com.eagle.programmar.C.Terminals.C_Comment;
import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.programmar.C.Terminals.C_KeywordChoice;
import com.eagle.programmar.C.Terminals.C_Literal;
import com.eagle.programmar.C.Terminals.C_Number;
import com.eagle.programmar.C.Terminals.C_Punctuation;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationAmpersand;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class C_Function extends TokenSequence implements AbstractFunction, EagleRunnable
{
	public @S(10) @OPT C_Extern_C externC;
	public @S(20) @OPT C_Keyword EXTENSION = new C_Keyword("__extension__");
	public @S(30) @OPT C_KeywordChoice scope1 = new C_KeywordChoice(C_Program.getModifiers());
	public @S(40) @OPT C_Comment comment1;
	public @S(50) @OPT C_KeywordChoice scope2 = new C_KeywordChoice(C_Program.getModifiers());
	public @S(60) C_FunctionTypeName typeName;
	public @S(70) C_Function_ParameterDefs parameters;
	public @S(80) @OPT TokenList<C_Comment> comments2;
	public @S(90) @OPT C_Keyword CONST = new C_Keyword("const");
	public @S(100) C_FunctionBody body;

	public @SKIP CallMetrics _metrics = null;
	
	public static class C_FunctionTypeName extends TokenChooser
	{
		public @CHOICE C_Keyword MAIN = new C_Keyword("main"); // Strange syntax with no return type on 'main'
		public @CHOICE C_Function_TypeAndName typeAndName;
	}

	public static class C_Function_TypeAndName extends TokenSequence
	{
		public @S(10) C_Type ctype;
		public @S(20) @OPT TokenList<C_Comment> comments;
		public @S(30) C_Function_Definition functionName;
	}

	public static class C_Function_ParameterDefs extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) @OPT C_Comment comment1;
		public @S(30) @OPT C_FunctionParameter param;
		public @S(40) @OPT C_Comment comment2;
		public @S(50) @OPT TokenList<C_MoreParameterDefs> moreParams;
		public @S(60) PunctuationRightParen rightParen;
	}

	public static class C_FunctionParameter extends TokenChooser
	{
		public @FIRST C_FunctionPointer functionPointer;
		public @CHOICE C_FunctionParamAmpersand paramAmpersand;
		public @CHOICE C_FunctionRegularParameter paramRegular; 
		public @CHOICE C_FunctionFunctionParameter functionParam;
		public @CHOICE C_FunctionDotDotDotParameter dotDotParam; 
	}

	public static class C_FunctionParamAmpersand extends TokenSequence
	{
		public @S(10) PunctuationAmpersand ampersand;
		public @S(20) C_Type type;
	}

	public static class C_FunctionRegularParameter extends TokenSequence
	{
		public @S(10) @OPT C_Keyword CONST = new C_Keyword("const");
		public @S(20) C_Type ctype;
		public @S(30) @OPT C_Keyword RESTRICT = new C_Keyword("__restrict");
		public @S(40) @OPT C_Variable_Definition id;
		public @S(50) @OPT TokenList<C_Subscript> subscripts;
		public @S(60) @OPT C_FunctionDefaultValue value;
		public @S(70) @OPT C_Comment comment;

		public static class C_FunctionDefaultValue extends TokenSequence
		{
			public @S(10) PunctuationEquals equals;
			public @S(20) C_Expression expr;
		}
	}

	public static class C_FunctionFunctionParameter extends TokenSequence
	{
		public @S(10) C_Type ctype;
		public @S(20) C_Function_Definition id;
		public @S(30) C_Function_ParameterDefs params;
	}

	public static class C_FunctionDotDotDotParameter extends TokenSequence
	{
		public @S(10) C_Punctuation dotDotDot = new C_Punctuation("...");
	}

	public static class C_MoreParameterDefs extends TokenSequence
	{
		public @S(10) PunctuationComma comma;
		public @S(20) @OPT C_Comment comment;
		public @S(30) C_FunctionParameter param;
	}

	public static class C_FunctionBody extends TokenChooser
	{
		public @CHOICE C_FunctionImplementation implementation;

		public @CHOICE static class C_FunctionEqualsZero extends TokenSequence
		{
			public @S(10) PunctuationEquals equals;
			public @S(20) C_Number zero;
		}

		public @CHOICE static class C_FunctionEqualsDefault extends TokenSequence
		{
			public @S(10) PunctuationEquals equals;
			public @S(20) C_Keyword DEFAULT = new C_Keyword("default");
		}

		public @CHOICE static class C_FunctionNoBody extends TokenSequence
		{
			public @S(10) @OPT C_FunctionAssembler assembler;
			public @S(20) @OPT TokenList<C_FunctionAttributes> attributes;
			public @S(30) PunctuationSemicolon semicolon;

			public static class C_FunctionAssembler extends TokenSequence
			{
				public @S(10) C_Keyword ASM = new C_Keyword("__asm__");
				public @S(20) PunctuationLeftParen leftParen;
				public @S(30) C_Literal blank;
				public @S(40) C_Literal functionName;
				public @S(50) PunctuationRightParen rightParen;
			}
		}
	}
	
	public static class C_FunctionImplementation extends TokenSequence
	{
		public @S(10) PunctuationLeftBrace leftBrace;
		public @S(20) @OPT TokenList<C_StatementOrComment> elements;
		public @S(30) PunctuationRightBrace rightBrace;
		public @S(40) @OPT @CURIOUS("Extra semicolon") PunctuationSemicolon semicolon;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (_metrics == null)
		{
			String fname = "main";
			AbstractToken which = typeName.getWhich();
			if (which instanceof C_Function_TypeAndName)
			{
				C_Function_TypeAndName typeAndName = (C_Function_TypeAndName) which;
				fname = typeAndName.functionName.getValue();
			}
			_metrics = new CallMetrics(fname, getFileName(), getStartLine(), getStartChar());

			// Don't do anything here, unless the function name is 'main'
			// We searched for all the functions in a preliminary pass
			// And we only evaluate them when they are called
			if (fname.equals("main"))
			{
				AbstractToken token = body.getWhich();
				if (token instanceof C_FunctionImplementation)
				{
					C_FunctionImplementation impl = (C_FunctionImplementation) token;
					for (C_StatementOrComment stmt : impl.elements._elements)
					{
						interpreter.tryToInterpret(stmt);
					}
				}
			}
		}
	}
}
