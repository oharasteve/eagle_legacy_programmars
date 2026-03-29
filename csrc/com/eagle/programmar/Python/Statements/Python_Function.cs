// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 29, 2013

namespace com.eagle.programmar.Python.Statements
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using ArgumentsMetrics = com.eagle.metrics.ArgumentsMetrics;
	using AssignMetrics = com.eagle.metrics.AssignMetrics;
	using CallMetrics = com.eagle.metrics.CallMetrics;
	using ReturnMetrics = com.eagle.metrics.ReturnMetrics;
	using Python_ComplexStatement = com.eagle.programmar.Python.Python_ComplexStatement;
	using Python_Decorators = com.eagle.programmar.Python.Python_Decorators;
	using Python_Parameter_List = com.eagle.programmar.Python.Python_Parameter_List;
	using Python_Params = com.eagle.programmar.Python.Python_Params;
	using Python_MoreParams = com.eagle.programmar.Python.Python_Params.Python_MoreParams;
	using Python_Parameter = com.eagle.programmar.Python.Python_Params.Python_Parameter;
	using Python_Syntax = com.eagle.programmar.Python.Python_Syntax;
	using Python_Type = com.eagle.programmar.Python.Python_Type;
	using Python_MultilineStatement = com.eagle.programmar.Python.Statements.Python_StatementBlock.Python_MultilineStatement;
	using Python_Function_Definition = com.eagle.programmar.Python.Symbols.Python_Function_Definition;
	using Python_Variable_Definition = com.eagle.programmar.Python.Symbols.Python_Variable_Definition;
	using Python_Comment = com.eagle.programmar.Python.Terminals.Python_Comment;
	using Python_Keyword = com.eagle.programmar.Python.Terminals.Python_Keyword;
	using Python_Punctuation = com.eagle.programmar.Python.Terminals.Python_Punctuation;
	using EagleScope = com.eagle.scope.EagleScope;
	using EagleScopeInterface = com.eagle.scope.EagleScope.EagleScopeInterface;
	using AbstractFunction = com.eagle.tokens.AbstractFunction;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractMethod = com.eagle.tokens.interfaces.AbstractMethod;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationColon = com.eagle.tokens.punctuation.PunctuationColon;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;
	using EagleTransformableFunction = com.eagle.transform.EagleTransformableFunction;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	// Why does this implement AbstractMethod ?? Transformation needs / uses it, but why ??
	public class Python_Function : TokenSequence, AbstractMethod, AbstractFunction, EagleRunnable, EagleTransformableFunction
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT @BLANKLINE Python_Decorators decorators;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Python_Keyword ASYNC = new com.eagle.programmar.Python.Terminals.Python_Keyword("async");
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @DOC("compound_stmts.html#function-definitions") com.eagle.programmar.Python.Terminals.Python_Keyword DEF = new com.eagle.programmar.Python.Terminals.Python_Keyword("def");
		public @DOC("compound_stmts.html#function-definitions") Python_Keyword DEF = new Python_Keyword("def");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) Python_FunctionName fnName;
		public Python_FunctionName fnName;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) Python_FunctionHeader header;
		public Python_FunctionHeader header;

		public static class Python_FunctionHeader extends TokenSequence implements EagleScope.EagleScopeInterface
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Python.Python_Parameter_List params;
			public Python_Parameter_List @params;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Python_ReturnType returnType;
			public @OPT Python_ReturnType returnType;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @NOSPACE PunctuationColon colon;
			public @NOSPACE PunctuationColon colon;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT TokenList<com.eagle.programmar.Python.Terminals.Python_Comment> comment;
			public @OPT TokenList<Python_Comment> comment;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @PYDENT Python_StatementBlock defBody;
			public @PYDENT Python_StatementBlock defBody;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP EagleScope _scope = new com.eagle.scope.EagleScope(this, com.eagle.programmar.Python.Python_Syntax.IS_CASE_SENSITIVE);
			private EagleScope _scope = new EagleScope(this, Python_Syntax.IS_CASE_SENSITIVE);

			public EagleScope getScope()
			{
				return _scope;
			}
		}

		public static class Python_FunctionName extends TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Python_Function_Definition XXname;
			public Python_Function_Definition XXname;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Python_Keyword XXINIT = new com.eagle.programmar.Python.Terminals.Python_Keyword("__init__");
			public Python_Keyword XXINIT = new Python_Keyword("__init__");
		}

		public static class Python_ReturnType extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Python.Terminals.Python_Punctuation arrow = new com.eagle.programmar.Python.Terminals.Python_Punctuation("->");
			public Python_Punctuation arrow = new Python_Punctuation("->");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Python.Python_Type type;
			public Python_Type type;
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @SKIP CallMetrics _callMetrics = null;
		public CallMetrics _callMetrics = null;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @SKIP ArgumentsMetrics _argumentsMetrics = null;
		public ArgumentsMetrics _argumentsMetrics = null;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @SKIP ReturnMetrics _returnMetrics = null;
		public ReturnMetrics _returnMetrics = null;

		public void interpret(EagleInterpreter interpreter)
		{
			if (fnName.getWhich() is Python_Function_Definition)
			{
				Python_Function_Definition id = (Python_Function_Definition) fnName.getWhich();
				if (_callMetrics == null)
				{
					_callMetrics = new CallMetrics(interpreter._metrics, id.getValue(), id);
				}
				if (_argumentsMetrics == null)
				{
					_argumentsMetrics = new ArgumentsMetrics(interpreter._metrics, id.getValue(), id);
				}
				if (_returnMetrics == null)
				{
					_returnMetrics = new ReturnMetrics(interpreter._metrics, id.getValue(), id);
				}
			}

			// Don't do anything here.
			// We searched for all the functions in a preliminary pass
			// And we only evaluate when it is called
		}

		public static Python_Function newPythonFunction(string name)
		{
			Python_Function_Definition funcDef = new Python_Function_Definition();
			funcDef.setValue(name);
			Python_Function func = new Python_Function();
			func.fnName = new Python_FunctionName();
			func.fnName.setWhich(funcDef);

			func.header = new Python_FunctionHeader();
			func.header.colon = new PunctuationColon();

			func.header.@params = new Python_Parameter_List();
			func.header.@params.leftParen = new PunctuationLeftParen();
			func.header.@params.@params = new Python_Params();
			func.header.@params.@params.setPresent(true);
			func.header.@params.rightParen = new PunctuationRightParen();

			func.header.defBody = new Python_StatementBlock();
			Python_MultilineStatement multi = new Python_MultilineStatement();
			multi.statements = new TokenList<Python_ComplexStatement>();
			func.header.defBody.setWhich(multi);

			return func;
		}

		public void addFunctionParameter(AbstractType type, string name)
		{
			Python_Variable_Definition var = new Python_Variable_Definition();
			var.setValue(name);
			Python_Params.Python_Parameter newParam = new Python_Params.Python_Parameter();
			newParam.setWhich(var);

			if (header.@params.@params.param == null)
			{
				header.@params.@params.param = newParam;
			}
			else
			{
				if (header.@params.@params.moreParams == null)
				{
					header.@params.@params.moreParams = new TokenList<Python_Params.Python_MoreParams>();
				}
				Python_Params.Python_MoreParams more = new Python_Params.Python_MoreParams();
				more.comma = new PunctuationComma();
				more.param = newParam;
				header.@params.@params.moreParams.addToken(more);
			}
		}

		public void transformFunction(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			if (!(fnName.getWhich() is Python_Function_Definition))
			{
				throw new Exception("Can only handle regular function definitions");
			}
			Python_Function_Definition id = (Python_Function_Definition) fnName.getWhich();
			EagleGenerator.TypeEnum metricRetType = transformer.findReturnMetric(id);
			AbstractType newReturnType = generator.transformType(metricRetType, null, id);

			string name = id.getValue();
			generator.addMethod(newReturnType, name, this);
			generator.setMethodName(name);
			if (VERBOSE)
			{
				Console.WriteLine("** Found Python function " + name);
			}

			// Search metrics for arg types -- might not be any
			List<EagleGenerator.TypeEnum> argTypes = transformer.findArgumentsMetric(id);

			if (header.@params != null && header.@params.isPresent())
			{
				Python_Params.Python_Parameter paramVar1 = header.@params.@params.param;
				AbstractType paramType1 = null;
				if (argTypes != null && 0 < argTypes.Count)
				{
					EagleGenerator.TypeEnum metricArg1 = argTypes[0];
					paramType1 = generator.transformType(metricArg1, null, paramVar1);
				}
				if (!(paramVar1.getWhich() is Python_Variable_Definition))
				{
					throw new Exception("Unable to handle " + paramVar1.getWhich());
				}
				Python_Variable_Definition varDef1 = (Python_Variable_Definition) paramVar1.getWhich();
				// System.err.println("****** paramType = " + paramType1 + " value = " + varDef1.getValue());
				generator.addMethodParameter(paramType1, varDef1.getValue());

				if (header.@params.@params.moreParams != null)
				{
					int i = 1;
					foreach (Python_Params.Python_MoreParams more in header.@params.@params.moreParams._elements)
					{
						Python_Params.Python_Parameter paramVar2 = more.param;
						AbstractType paramType2 = null;
						if (argTypes != null && 0 < argTypes.Count)
						{
							EagleGenerator.TypeEnum metricArg2 = argTypes[i];
							paramType2 = generator.transformType(metricArg2, null, paramVar2);
						}
						if (!(paramVar2.getWhich() is Python_Variable_Definition))
						{
							throw new Exception("Unable to handle " + paramVar2.getWhich());
						}
						Python_Variable_Definition varDef2 = (Python_Variable_Definition) paramVar2.getWhich();
						// System.err.println("****** paramType = " + paramType2 + " value = " + varDef2.getValue());
						generator.addMethodParameter(paramType2, varDef2.getValue());
						i++;
					}
				}
			}

			addLocalVars(transformer, generator);

			foreach (AbstractStatement stmt1 in header.defBody.transformStatement(transformer, generator))
			{
				generator.addStatement(stmt1, header.defBody);
			}

			generator.doneMethod();
		}

		private bool isFuncParam(string name)
		{
			if (header.@params != null && header.@params.isPresent())
			{
				Python_Params.Python_Parameter paramVar1 = header.@params.@params.param;
				if (paramVar1.getWhich() is Python_Variable_Definition)
				{
					Python_Variable_Definition varDef1 = (Python_Variable_Definition) paramVar1.getWhich();
					if (varDef1.getValue().Equals(name))
					{
						return true;
					}
				}

				if (header.@params.@params.moreParams != null)
				{
					foreach (Python_Params.Python_MoreParams more in header.@params.@params.moreParams._elements)
					{
						Python_Params.Python_Parameter paramVar2 = more.param;
						if (paramVar2.getWhich() is Python_Variable_Definition)
						{
							Python_Variable_Definition varDef2 = (Python_Variable_Definition) paramVar2.getWhich();
							if (varDef2.getValue().Equals(name))
							{
								return true;
							}
						}
					}
				}
			}
			return false;
		}

		// Are there any local variables we need to declare?
		private void addLocalVars(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			string scopeStr = this._currentLine + "-" + this._endLine;
			List<AssignMetrics> asgMetrics = transformer._metrics.findVarsInScope(scopeStr);
			foreach (AssignMetrics met in asgMetrics)
			{
				EagleGenerator.TypeEnum typ = met.uniqueType();
				if (typ != EagleGenerator.TypeEnum.VOID)
				{
					if (!isFuncParam(met._symbolName))
					{
						// System.err.println("****** Found var " + met._symbolName);
						AbstractType absType = generator.transformType(typ, null, this);
						AbstractStatement dataStmt = generator.newDataDeclaration(false, met._symbolName, null, absType, null, this);
						generator.addStatement(dataStmt, this);
					}
				}
			}
		}
	}

}
