// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 26, 2014

namespace com.eagle.programmar.Perl
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using ArgumentsMetrics = com.eagle.metrics.ArgumentsMetrics;
	using AssignMetrics = com.eagle.metrics.AssignMetrics;
	using CallMetrics = com.eagle.metrics.CallMetrics;
	using ReturnMetrics = com.eagle.metrics.ReturnMetrics;
	using Perl_StatementBlock = com.eagle.programmar.Perl.Statements.Perl_StatementBlock;
	using Perl_Function_Definition = com.eagle.programmar.Perl.Symbols.Perl_Function_Definition;
	using Perl_Variable_Definition = com.eagle.programmar.Perl.Symbols.Perl_Variable_Definition;
	using Perl_Keyword = com.eagle.programmar.Perl.Terminals.Perl_Keyword;
	using Perl_KeywordChoice = com.eagle.programmar.Perl.Terminals.Perl_KeywordChoice;
	using Perl_Punctuation = com.eagle.programmar.Perl.Terminals.Perl_Punctuation;
	using EagleScope = com.eagle.scope.EagleScope;
	using EagleScopeInterface = com.eagle.scope.EagleScope.EagleScopeInterface;
	using AbstractFunction = com.eagle.tokens.AbstractFunction;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenChooser = com.eagle.tokens.TokenChooser;
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
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;
	using EagleTransformableFunction = com.eagle.transform.EagleTransformableFunction;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Perl_Function : TokenSequence, AbstractFunction, EagleRunnable, EagleScope.EagleScopeInterface, EagleTransformableFunction
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT TokenList<Perl_FunctionPrefix> modifiers;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Perl.Terminals.Perl_Keyword FUNCTION = new com.eagle.programmar.Perl.Terminals.Perl_Keyword("function");
		public Perl_Keyword FUNCTION = new Perl_Keyword("function");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Perl.Symbols.Perl_Function_Definition id;
		public Perl_Function_Definition id;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) Perl_Function_Parameters params;
		public Perl_Function_Parameters @params;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT Perl_FunctionReturn returns;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) Perl_FunctionBlock block;
		public Perl_FunctionBlock block;

		public class Perl_FunctionPrefix : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Perl.Terminals.Perl_KeywordChoice modifier = new com.eagle.programmar.Perl.Terminals.Perl_KeywordChoice(Perl_Program.MODIFIERS);
			public Perl_KeywordChoice modifier = new Perl_KeywordChoice(Perl_Program.MODIFIERS);
		}

		public class Perl_FunctionBlock : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE PunctuationSemicolon XXsemicolon;
			public PunctuationSemicolon XXsemicolon;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Perl_StatementBlock XXblock;
			public Perl_StatementBlock XXblock;
		}

		public class Perl_Function_Parameters : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
			public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT SeparatedList<Perl_FunctionVariableOrTypeVariable, com.eagle.tokens.punctuation.PunctuationComma> parameters;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
			public PunctuationRightParen rightParen;
		}

		public class Perl_FunctionVariableOrTypeVariable : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @LAST Perl_FunctionVariable XXvar;
			public Perl_FunctionVariable XXvar;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Perl_FunctionTypeAndVariable XXtypeAndVar;
			public Perl_FunctionTypeAndVariable XXtypeAndVar;
		}

		public class Perl_FunctionTypeAndVariable : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) Perl_Type type;
			public Perl_Type type;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) Perl_FunctionVariable var;
			public Perl_FunctionVariable var;
		}

		public class Perl_FunctionVariable : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT Perl_Punctuation amp = new com.eagle.programmar.Perl.Terminals.Perl_Punctuation('&');
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Perl.Symbols.Perl_Variable_Definition param;
			public Perl_Variable_Definition param;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT Perl_Variable_Initializer init;
			public  OPT;

			public class Perl_Variable_Initializer : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationEquals equals;
				public PunctuationEquals equals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) Perl_Expression initVal;
				public Perl_Expression initVal;
			}
		}

		public class Perl_FunctionReturn : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationColon colon;
			public PunctuationColon colon;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) Perl_Type returnType;
			public Perl_Type returnType;
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP EagleScope _scope = new com.eagle.scope.EagleScope(this, Perl_Syntax.IS_CASE_SENSITIVE);
		private EagleScope _scope = new EagleScope(this, Perl_Syntax.IS_CASE_SENSITIVE);

		public override EagleScope Scope
		{
			get
			{
				return _scope;
			}
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

		public override void interpret(EagleInterpreter interpreter)
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

		public override void transformFunction(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			EagleGenerator.TypeEnum typRet = transformer.findReturnMetric(id);
			AbstractType newReturnType = generator.transformType(typRet, null, id);

			string fnName = id.getValue();

			generator.addMethod(newReturnType, fnName, this);
			generator.setMethodName(fnName);
			if (VERBOSE)
			{
				Console.WriteLine("** Found Perl function " + fnName);
			}

			// Search metrics for arg types -- might not be any
			List<EagleGenerator.TypeEnum> argTypes = transformer.findArgumentsMetric(id);

			if (@params != null && @params.isPresent())
			{
				for (int i = 0; i < @params.parameters.getPrimaryCount(); i++)
				{
					Perl_FunctionVariableOrTypeVariable param = @params.parameters.getPrimaryElement(i);
					Perl_FunctionVariable paramVar;
					EagleGenerator.TypeEnum type = null;
					if (param.getWhich() is Perl_FunctionVariable)
					{
						paramVar = (Perl_FunctionVariable) param.getWhich();
						if (argTypes != null && i < argTypes.Count)
						{
							type = argTypes[i];
						}
					}
					else
					{
						Perl_FunctionTypeAndVariable typedParam = (Perl_FunctionTypeAndVariable) param.getWhich();
						paramVar = typedParam.var;
						type = Perl_Type.findType(typedParam.type);
					}

					AbstractType newParamType = generator.transformType(type, null, param);
					string paramName = Perl_Variable.repairName(paramVar.param.getValue());
					generator.addMethodParameter(newParamType, paramName);
				}
			}

			addLocalVars(transformer, generator);

			if (block.getWhich() is Perl_StatementBlock)
			{
				Perl_StatementBlock stmts = (Perl_StatementBlock) block.getWhich();
				if (stmts.statements != null && stmts.statements.size() > 0)
				{
					foreach (Perl_StatementOrComment stmtOrComment in stmts.statements._elements)
					{
						if (stmtOrComment.getWhich() is Perl_Statement)
						{
							Perl_Statement stmt = (Perl_Statement) stmtOrComment.getWhich();
							ICollection<AbstractStatement> newStmts = transformer.transformStatement(generator, stmt.getWhich());
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
			}

			generator.doneMethod();
		}

		private bool isFuncParam(string name)
		{
			if (@params != null && @params.isPresent())
			{
				int numParams = @params.parameters.getPrimaryCount();
				for (int i = 0; i < numParams; i++)
				{
					Perl_FunctionVariableOrTypeVariable param = @params.parameters.getPrimaryElement(i);
					AbstractToken which = param.getWhich();
					Perl_FunctionVariable var;
					if (which is Perl_FunctionTypeAndVariable)
					{
						Perl_FunctionTypeAndVariable varType = (Perl_FunctionTypeAndVariable) which;
						var = varType.var;
					}
					else
					{
						var = (Perl_FunctionVariable) which;
					}
					if (var.param.getValue().equalsIgnoreCase(name))
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
