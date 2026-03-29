// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

namespace com.eagle.programmar.FSharp.Statements
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using ArgumentsMetrics = com.eagle.metrics.ArgumentsMetrics;
	using AssignMetrics = com.eagle.metrics.AssignMetrics;
	using CallMetrics = com.eagle.metrics.CallMetrics;
	using ReturnMetrics = com.eagle.metrics.ReturnMetrics;
	using FSharp_Element = com.eagle.programmar.FSharp.FSharp_Element;
	using FSharp_Statement = com.eagle.programmar.FSharp.FSharp_Element.FSharp_Statement;
	using FSharp_Statement_List = com.eagle.programmar.FSharp.FSharp_Element.FSharp_Statement_List;
	using FSharp_Syntax = com.eagle.programmar.FSharp.FSharp_Syntax;
	using FSharp_Type = com.eagle.programmar.FSharp.FSharp_Type;
	using FSharp_Function_Definition = com.eagle.programmar.FSharp.Symbols.FSharp_Function_Definition;
	using FSharp_Variable_Definition = com.eagle.programmar.FSharp.Symbols.FSharp_Variable_Definition;
	using FSharp_EndOfLine = com.eagle.programmar.FSharp.Terminals.FSharp_EndOfLine;
	using FSharp_Keyword = com.eagle.programmar.FSharp.Terminals.FSharp_Keyword;
	using EagleScope = com.eagle.scope.EagleScope;
	using EagleScopeInterface = com.eagle.scope.EagleScope.EagleScopeInterface;
	using AbstractFunction = com.eagle.tokens.AbstractFunction;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationColon = com.eagle.tokens.punctuation.PunctuationColon;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationEquals = com.eagle.tokens.punctuation.PunctuationEquals;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;
	using EagleTransformableFunction = com.eagle.transform.EagleTransformableFunction;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class FSharp_Function : TokenSequence, AbstractFunction, EagleRunnable, EagleScope.EagleScopeInterface, EagleTransformableFunction
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("functions/") com.eagle.programmar.FSharp.Terminals.FSharp_Keyword LET = new com.eagle.programmar.FSharp.Terminals.FSharp_Keyword("let");
		public @DOC("functions/") FSharp_Keyword LET = new FSharp_Keyword("let");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.FSharp.Symbols.FSharp_Function_Definition id;
		public FSharp_Function_Definition id;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
		public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT SeparatedList<FSharp_FunctionParam, com.eagle.tokens.punctuation.PunctuationComma> params;
		public @OPT SeparatedList<FSharp_FunctionParam, PunctuationComma> @params;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
		public PunctuationRightParen rightParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT FSharp_ReturnType returnType;
		public @OPT FSharp_ReturnType returnType;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.tokens.punctuation.PunctuationEquals equals;
		public PunctuationEquals equals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) com.eagle.programmar.FSharp.Terminals.FSharp_EndOfLine eoln;
		public FSharp_EndOfLine eoln;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(90) com.eagle.tokens.TokenList<com.eagle.programmar.FSharp.FSharp_Element> statements;
		public TokenList<FSharp_Element> statements;

		public static class FSharp_FunctionParam extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.FSharp.Symbols.FSharp_Variable_Definition var;
			public FSharp_Variable_Definition var;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationColon colon;
			public PunctuationColon colon;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.FSharp.FSharp_Type type;
			public FSharp_Type type;
		}

		public static class FSharp_ReturnType extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationColon colon;
			public PunctuationColon colon;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.FSharp.FSharp_Type type;
			public FSharp_Type type;
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

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP EagleScope _scope = new com.eagle.scope.EagleScope(this, com.eagle.programmar.FSharp.FSharp_Syntax.IS_CASE_SENSITIVE);
		private EagleScope _scope = new EagleScope(this, FSharp_Syntax.IS_CASE_SENSITIVE);

		public EagleScope Scope
		{
			return _scope;
		}

		public void interpret(EagleInterpreter interpreter)
		{
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

			// Don't do anything here.
			// We searched for all the functions in a preliminary pass
			// And we only evaluate when it is called
		}

		public void transformFunction(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			EagleGenerator.TypeEnum typRet = EagleGenerator.TypeEnum.VOID;
			if (returnType != null && returnType.isPresent())
			{
				typRet = FSharp_Type.findType(returnType.type);
			}
			if (typRet == EagleGenerator.TypeEnum.VOID)
			{
				typRet = transformer.findReturnMetric(id);
			}
			AbstractType newReturnType = generator.transformType(typRet, null, id);

			string fnName = id.getValue();

			generator.addMethod(newReturnType, fnName, this);
			generator.setMethodName(fnName);
			if (VERBOSE)
			{
				Console.WriteLine("** Found F# function " + fnName);
			}

			// Search metrics for arg types -- might not be any
			List<EagleGenerator.TypeEnum> argTypes = transformer.findArgumentsMetric(id);

			if (@params != null && @params.isPresent())
			{
				for (int i = 0; i < @params.getPrimaryCount(); i++)
				{
					FSharp_FunctionParam param = @params.getPrimaryElement(i);
					AbstractType paramType = null;

					if (argTypes != null && i < argTypes.Count)
					{
						EagleGenerator.TypeEnum metricArg = argTypes[i];
						paramType = generator.transformType(metricArg, null, param);
					}

					generator.addMethodParameter(paramType, param.var.getValue());
				}
			}

			addLocalVars(transformer, generator);

			foreach (FSharp_Element elt in statements._elements)
			{
				AbstractToken which1 = elt.statementOrComment.getWhich();
				if (which1 is FSharp_Element.FSharp_Statement_List)
				{
					FSharp_Element.FSharp_Statement_List stmtList = (FSharp_Element.FSharp_Statement_List) which1;
					int numStmts = stmtList.statements.getPrimaryCount();
					for (int i = 0; i < numStmts; i++)
					{
						FSharp_Element.FSharp_Statement stmt = stmtList.statements.getPrimaryElement(i);
						AbstractToken which2 = stmt.getWhich();

						if (i == numStmts - 1)
						{
							// Last line in a function *might* be an implied RETURN
							if (which2 is FSharp_ExpressionStatement)
							{
								FSharp_ExpressionStatement exprStmt = (FSharp_ExpressionStatement) which2;
								AbstractExpression newExpr = transformer.transformExpression(generator, exprStmt.expression);
								AbstractStatement retStmt = generator.newReturnStatement(newExpr, which2);
								generator.addStatement(retStmt, stmt);
								break; // Only gets here for the last statement in the Function
							}
						}

						ICollection<AbstractStatement> newStmts = transformer.transformStatement(generator, which2);
						if (newStmts != null)
						{
							foreach (AbstractStatement newStmt in newStmts)
							{
								generator.addStatement(newStmt, stmt.getWhich());
							}
						}
					}
				}
			}

			generator.doneMethod();
		}

		private bool isFuncParam(string name)
		{
			if (@params != null && @params.isPresent())
			{
				int numParams = @params.getPrimaryCount();
				for (int i = 0; i < numParams; i++)
				{
					FSharp_FunctionParam param = @params.getPrimaryElement(i);
					if (param.var.getValue().equalsIgnoreCase(name))
					{
						return true;
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
