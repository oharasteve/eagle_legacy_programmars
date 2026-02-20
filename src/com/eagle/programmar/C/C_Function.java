// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 8, 2011

package com.eagle.programmar.C;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.metrics.ArgumentsMetrics;
import com.eagle.metrics.CallMetrics;
import com.eagle.metrics.ReturnMetrics;
import com.eagle.programmar.C.C_Program.C_StatementOrComment;
import com.eagle.programmar.C.Symbols.C_Function_Definition;
import com.eagle.programmar.C.Symbols.C_Variable_Definition;
import com.eagle.programmar.C.Terminals.C_Comment;
import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.programmar.C.Terminals.C_KeywordChoice;
import com.eagle.programmar.C.Terminals.C_Literal;
import com.eagle.programmar.C.Terminals.C_Number;
import com.eagle.programmar.C.Terminals.C_Punctuation;
import com.eagle.programmar.C.Types.C_FunctionPointer;
import com.eagle.scope.EagleScope;
import com.eagle.scope.EagleScope.EagleScopeInterface;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.punctuation.PunctuationAmpersand;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.TypeEnum;
import com.eagle.transform.EagleTransformableFunction;
import com.eagle.transform.EagleTransformer;

public class C_Function extends TokenSequence
		implements AbstractFunction, EagleRunnable, EagleScopeInterface,
		EagleTransformableFunction
{
	public @S(10) @OPT C_Extern_C externC;
	public @S(20) @OPT C_FunctionDeclspec declspec;
	public @S(30) @OPT C_Keyword EXTENSION = new C_Keyword("__extension__");
	public @S(40) @OPT C_FunctionAttributes attributes;
	public @S(50) @OPT C_KeywordChoice scope1 = new C_KeywordChoice(C_Program.getModifiers());
	public @S(60) @OPT C_Comment comment1;
	public @S(70) @OPT C_KeywordChoice scope2 = new C_KeywordChoice(C_Program.getModifiers());
	public @S(80) C_FunctionTypeName typeName;
	public @S(90) C_Function_ParameterDefs parameters;
	public @S(100) @OPT TokenList<C_Comment> comments2;
	public @S(110) @OPT C_Keyword CONST = new C_Keyword("const");
	public @S(120) C_FunctionBody body;

	public static class C_FunctionTypeName extends TokenChooser
	{
		public @CHOICE C_Keyword XXMAIN = new C_Keyword("main"); // Strange syntax with no return type on 'main'
		public @CHOICE C_Function_TypeAndName XXtypeAndName;
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
		public @FIRST C_FunctionPointer XXfunctionPointer;
		public @CHOICE C_FunctionParamAmpersand XXparamAmpersand;
		public @CHOICE C_FunctionFunctionParameter XXfunctionParam;
		public @CHOICE C_FunctionDotDotDotParameter XXdotDotParam;
		public @LAST C_FunctionRegularParameter XXparamRegular; // Otherwise it misses C_FunctionFunctionParameter
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
		public @S(30) @OPT C_KeywordChoice RESTRICT = new C_KeywordChoice("__restrict", "restrict");
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
		public @CHOICE C_FunctionImplementation XXimplementation;

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

	public static class C_FunctionDeclspec extends TokenSequence
	{
		public @S(10) C_KeywordChoice DECLSPEC = new C_KeywordChoice("_declspec", "__declspec");
		public @S(20) PunctuationLeftParen leftParen;
		public @S(30) C_Keyword DLLEXPORT = new C_Keyword("dllexport");
		public @S(40) PunctuationRightParen rightParen;
	}

	public @SKIP CallMetrics _callMetrics = null;
	public @SKIP ArgumentsMetrics _argumentsMetrics = null;
	public @SKIP ReturnMetrics _returnMetrics = null;

	private @SKIP EagleScope _scope = new EagleScope(this, C_Syntax.IS_CASE_SENSITIVE);

	@Override
	public EagleScope getScope()
	{
		return _scope;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (_callMetrics == null)
		{
			String fname = "main";
			AbstractToken which = typeName.getWhich();
			if (which instanceof C_Function_TypeAndName)
			{
				C_Function_TypeAndName typeAndName = (C_Function_TypeAndName) which;
				C_Function_Definition id = typeAndName.functionName;
				fname = id.getValue();
				_callMetrics = new CallMetrics(interpreter._metrics, id.getValue(), id);
				_argumentsMetrics = new ArgumentsMetrics(interpreter._metrics, id.getValue(), id);
				_returnMetrics = new ReturnMetrics(interpreter._metrics, id.getValue(), id);
			}

			// Don't do anything here, unless the function name is 'main'
			// We searched for all the functions in a preliminary pass
			// And we only evaluate them when they are called
			if (fname.equals("main"))
			{
				interpreter.callingFunction("main", this);
				AbstractToken token = body.getWhich();
				if (token instanceof C_FunctionImplementation)
				{
					C_FunctionImplementation impl = (C_FunctionImplementation) token;
					for (C_StatementOrComment stmt : impl.elements._elements)
					{
						interpreter.tryToInterpret(stmt);
					}
				}
				interpreter.completedFunction("main", this);
			}
		}
	}

	@Override
	public void transformFunction(EagleTransformer transformer, EagleGenerator generator)
	{
		AbstractToken which1 = typeName.getWhich();
		if (!(which1 instanceof C_Function_TypeAndName))
		{
			throw new RuntimeException("Unable to handle: " + which1);
		}
		C_Function_TypeAndName typeAndName = (C_Function_TypeAndName) which1;
		TypeEnum retType = typeAndName.ctype.findType();
		C_Function_Definition id = typeAndName.functionName;

		String fnName = id.getValue();
		boolean isMain = false;
		if (fnName.equals("main"))
		{
			fnName = generator.mainName();
			retType = TypeEnum.VOID;
			isMain = true;
		}

		AbstractType newReturnType = generator.transformType(retType, null, id);
		generator.addMethod(newReturnType, fnName, this);
		generator.setMethodName(fnName);
		if (VERBOSE)
		{
			System.out.println("*** Found C function " + fnName);
		}

		if (isMain)
		{
			generator.addMainArgs();
		}
		else
		{
			// First parameter is kept separately from remainder, unfortunately
			if (parameters.param != null && parameters.param.isPresent())
			{
				AbstractToken which2 = parameters.param.getWhich();
				if (which2 instanceof C_FunctionRegularParameter)
				{
					C_FunctionRegularParameter regParam1 = (C_FunctionRegularParameter) which2;
					String firstName = regParam1.id.getValue();
					if (firstName != null)
					{
						// if firstName is null means the parameter list is just 'void'
						if (VERBOSE)
						{
							System.out.println("****** First Parameter " + firstName);
						}
						TypeEnum argType1 = regParam1.ctype.findType();
						AbstractType newArgType1 = generator.transformType(argType1, null, regParam1);
						generator.addMethodParameter(newArgType1, regParam1.id.getValue());
					}
				}
			}
			if (parameters.moreParams != null && parameters.moreParams.size() > 0)
			{
				for (C_MoreParameterDefs nextParam : parameters.moreParams._elements)
				{
					AbstractToken which3 = nextParam.param.getWhich();
					if (which3 instanceof C_FunctionRegularParameter)
					{
						C_FunctionRegularParameter regParam2 = (C_FunctionRegularParameter) which3;
						if (VERBOSE)
						{
							System.out.println("******  Next Parameter " + regParam2.id.getValue());
						}
						TypeEnum argType2 = regParam2.ctype.findType();
						AbstractType newArgType2 = generator.transformType(argType2, null, regParam2);
						generator.addMethodParameter(newArgType2, regParam2.id.getValue());
					}
				}
			}
		}

		AbstractToken which4 = body.getWhich();
		if (which4 instanceof C_FunctionImplementation)
		{
			C_FunctionImplementation impl = (C_FunctionImplementation) which4;

			for (C_StatementOrComment stmtOrComment : impl.elements._elements)
			{
				AbstractToken which5 = stmtOrComment.getWhich();
				if (which5 instanceof C_Statement)
				{
					C_Statement stmt = (C_Statement) which5;
					ArrayList<AbstractStatement> newStmts = transformer.transformStatement(generator, stmt.getWhich());
					if (newStmts != null)
					{
						for (AbstractStatement newStmt : newStmts)
						{
							generator.addStatement(newStmt, stmt);
						}
					}
				}
				else if (which5 instanceof C_Data)
				{
					C_Data data = (C_Data) which5;
					ArrayList<AbstractStatement> newData = transformer.transformStatement(generator, data.getWhich());
					if (newData != null)
					{
						for (AbstractStatement newDatum : newData)
						{
							generator.addStatement(newDatum, data.getWhich());
						}
					}
				}
			}
		}

		generator.doneMethod();
	}
}
