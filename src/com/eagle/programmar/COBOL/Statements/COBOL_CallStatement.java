// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 12, 2010

package com.eagle.programmar.COBOL.Statements;

import java.util.ArrayList;

import com.eagle.generate.EagleGenerator;
import com.eagle.generate.TypeEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleInteger;
import com.eagle.math.EagleString;
import com.eagle.math.EagleValue;
import com.eagle.programmar.COBOL.COBOL_AbstractStatement;
import com.eagle.programmar.COBOL.COBOL_DataDeclaration;
import com.eagle.programmar.COBOL.COBOL_DataDeclaration.COBOL_DataClause;
import com.eagle.programmar.COBOL.COBOL_DataDeclaration.COBOL_DataFieldName;
import com.eagle.programmar.COBOL.COBOL_DataDivision.COBOL_DataSection;
import com.eagle.programmar.COBOL.COBOL_Expression;
import com.eagle.programmar.COBOL.COBOL_LinkageSection;
import com.eagle.programmar.COBOL.COBOL_Program_Complete;
import com.eagle.programmar.COBOL.COBOL_Statement;
import com.eagle.programmar.COBOL.COBOL_WorkingStorage.COBOL_CopyOrDataDeclaration;
import com.eagle.programmar.COBOL.Expressions.COBOL_VariableExpression;
import com.eagle.programmar.COBOL.Picture.COBOL_PictureClause;
import com.eagle.programmar.COBOL.Symbols.COBOL_Data_Definition;
import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Symbols.COBOL_Modifiable_Identifier;
import com.eagle.programmar.COBOL.Terminals.COBOL_HexNumber;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice;
import com.eagle.programmar.COBOL.Terminals.COBOL_Literal;
import com.eagle.programmar.COBOL.Terminals.COBOL_Number;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class COBOL_CallStatement extends COBOL_AbstractStatement
		implements EagleRunnable, EagleTransformableStatement
{
	public @S(10) @DOC("rlpscall.htm") COBOL_Keyword CALL = new COBOL_Keyword("CALL");
	public @S(20) @OPT COBOL_KeywordChoice how = new COBOL_KeywordChoice("STATICCOBOL", "WINAPI");
	public @S(30) COBOL_CallWhat callWhat;
	public @S(40) @OPT COBOL_Keyword USING = new COBOL_Keyword("USING");
	public @S(50) @OPT TokenList<COBOL_CallArgument> arguments;
	public @S(60) @OPT COBOL_CallReturning returning;
	public @S(70) @OPT TokenList<COBOL_CallException> exceptions;
	public @S(80) @OPT COBOL_Keyword ENDCALL = new COBOL_Keyword("END-CALL");

	public static class COBOL_CallWhat extends TokenChooser
	{
		public @CHOICE COBOL_Literal XXcallFunction;
		public @CHOICE COBOL_HexNumber XXcallHex;
		public @CHOICE COBOL_Identifier_Reference XXcallVariable;
	}

	public static class COBOL_CallArgument extends TokenSequence
	{
		public @S(10) @OPT PunctuationComma comma;
		public @S(20) @OPT COBOL_Keyword BY = new COBOL_Keyword("BY");
		public @S(30) @OPT COBOL_KeywordChoice byHow = new COBOL_KeywordChoice("CONTENT", "REFERENCE", "VALUE");
		public @S(40) COBOL_Expression expression;
		public @S(50) @OPT COBOL_ValueSize size;

		public static class COBOL_ValueSize extends TokenSequence
		{
			public @S(10) COBOL_Keyword SIZE = new COBOL_Keyword("SIZE");
			public @S(20) COBOL_Number size;
		}
	}

	public static class COBOL_CallReturning extends TokenSequence
	{
		public @S(10) COBOL_Keyword RETURNING = new COBOL_Keyword("RETURNING");
		public @S(20) COBOL_Modifiable_Identifier variable;
	}

	public static class COBOL_CallException extends TokenSequence
	{
		public @S(10) @OPT COBOL_Keyword NOT = new COBOL_Keyword("NOT");
		public @S(20) COBOL_Keyword ON = new COBOL_Keyword("ON");
		public @S(30) COBOL_Keyword EXCEPTION = new COBOL_Keyword("EXCEPTION");
		public @S(40) TokenList<COBOL_Statement> statements;
	}
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (!(callWhat.getWhich() instanceof COBOL_Literal))
		{
			throw new RuntimeException("Can only CALL literals");
		}
		COBOL_Literal lit = (COBOL_Literal) callWhat.getWhich();
		String name = lit.removeQuotes();

		// Look up the subroutine
		AbstractFunction func = interpreter.findFunction(name);
		if (func == null || !(func instanceof COBOL_Program_Complete))
		{
			throw new RuntimeException("Unable to find a program named " + name);
		}
		COBOL_Program_Complete subProg = (COBOL_Program_Complete) func;

		// Make sure the function args match up
		int argCount = 0;
		if (arguments != null && arguments.isPresent())
		{
			argCount = arguments.size();
		}
		
		int paramCount = 0;
		COBOL_LinkageSection linkage = null;
		if (subProg.dataDiv != null && subProg.dataDiv.isPresent())
		{
			for (COBOL_DataSection section : subProg.dataDiv.sections._elements)
			{
				if (section.getWhich() instanceof COBOL_LinkageSection)
				{
					linkage = (COBOL_LinkageSection) section.getWhich();
					paramCount = linkage.dataDeclarations.size();
					break;
				}
			}
		}
		
		if (argCount != paramCount)
		{
			throw new RuntimeException(
					"SubProg " + name + " expects #args = " + paramCount + ", but was given " + argCount);
		}

		interpreter.callingFunction(name, subProg);

		// Now assign all the parameters
		ArrayList<TypeEnum> argTypes = new ArrayList<TypeEnum>();
		for (int i = 0; i < argCount; i++)
		{
			COBOL_Expression expr = arguments._elements.get(i).expression;
			COBOL_CopyOrDataDeclaration parameter = linkage.dataDeclarations._elements.get(i);
			if (!(parameter.getWhich() instanceof COBOL_DataDeclaration))
			{
				throw new RuntimeException("Cannot handle: " + parameter.getWhich());
			}
			COBOL_DataDeclaration data = (COBOL_DataDeclaration) parameter.getWhich();
			if (data.fieldName == null || !(data.fieldName.isPresent()))
			{
				throw new RuntimeException("Data field name is required: " + data);
			}
			COBOL_DataFieldName fieldName = data.fieldName;
			if (!(fieldName.getWhich() instanceof COBOL_Data_Definition))
			{
				throw new RuntimeException("Data field must be data, not " + fieldName);
			}
			COBOL_Data_Definition param = (COBOL_Data_Definition) fieldName.getWhich();
			
			// Try to decide if it is an integer or a string
			TypeEnum varType = TypeEnum.OTHER;
			for (COBOL_DataClause clause : data.clauses._elements)
			{
				AbstractToken which = clause.getWhich();
				if (which instanceof COBOL_PictureClause)
				{
					COBOL_PictureClause picClause = (COBOL_PictureClause) which;
					String pic = picClause.picture.getValue();
					if (pic.startsWith("9")) varType = TypeEnum.INTEGER;
					if (pic.startsWith("X")) varType = TypeEnum.STRING;
					break;
				}
			}

			boolean skipArg = false;
			if (expr.getWhich() instanceof COBOL_VariableExpression)
			{
				COBOL_VariableExpression varExpr = (COBOL_VariableExpression) expr.getWhich();
				String varName = varExpr.variable.id.getValue();
				if (!interpreter._symbolTable.isSymbolDefined(varName))
				{
					// Must be a BY REFERENCE variable with no prior value
					skipArg = true;
				}
			}
			
			if (!skipArg)
			{
				EagleValue newVal = interpreter.getEagleValue(expr);
				if (varType == TypeEnum.INTEGER && !newVal.isInteger())
				{
					newVal = new EagleInteger(newVal.forceIntegerValue());
				}
				else if (varType == TypeEnum.STRING && !newVal.isString())
				{
					newVal = new EagleString(newVal.forceStringValue());
				}
				
				interpreter.setSymbol(param, param.getValue(), newVal);
				argTypes.add(newVal.getType());
			}
		}

		// Prepare to evaluate the method
		long startTime = System.nanoTime();

		// And transfer control to the subprogram
		interpreter.tryToInterpret(subProg.procedureDiv);

		// The result was already put on the runtime stack
		if (subProg._callMetrics != null && subProg._argumentsMetrics != null)
		{
			long elapsedTime = System.nanoTime() - startTime;
			subProg._callMetrics.addCallFrom(CALL, elapsedTime);
			subProg._argumentsMetrics.calledWith(argTypes);
		}

		// Now copy back all the CALL BY REFERENCE values
		ArrayList<COBOL_Identifier_Reference> argVars = new ArrayList<COBOL_Identifier_Reference>();
		ArrayList<EagleValue> paramValues = new ArrayList<EagleValue>();
		for (int i = 0; i < argCount; i++)
		{
			COBOL_Expression expr = arguments._elements.get(i).expression;
			if (expr.getWhich() instanceof COBOL_VariableExpression)
			{
				COBOL_VariableExpression varExpr = (COBOL_VariableExpression) expr.getWhich();
				COBOL_Identifier_Reference arg = varExpr.variable.id;
						
				COBOL_CopyOrDataDeclaration parameter = linkage.dataDeclarations._elements.get(i);
				COBOL_DataDeclaration data = (COBOL_DataDeclaration) parameter.getWhich();
				COBOL_DataFieldName fieldName = data.fieldName;
				COBOL_Data_Definition param = (COBOL_Data_Definition) fieldName.getWhich();
				EagleValue paramValue = interpreter.findSymbol(param.getValue());

				argVars.add(arg);
				paramValues.add(paramValue);
			}
			else
			{
				argVars.add(null);
				paramValues.add(null);
			}
		}

		// Now remove all those parameters
		interpreter.completedFunction(name, subProg);
		
		// And actually set the BY REFERENCE values finally
		for (int i = 0; i < argCount; i++)
		{
			COBOL_Identifier_Reference arg = argVars.get(i);
			if (arg != null)
			{
				interpreter.setSymbol(arg, arg.getValue(), paramValues.get(i));
			}
		}
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		throw new RuntimeException("*************LATER GATOR*************");
//		String name = subName.getValue();
//		ArrayList<AbstractExpression> args = new ArrayList<AbstractExpression>();
//		ArrayList<TypeEnum> types = transformer.findArgumentsMetricForFunction(name);
//		int argCount = 0;
//		if (callArguments.arguments != null && callArguments.arguments.isPresent())
//		{
//			argCount = callArguments.arguments.getPrimaryCount();
//		}
//		for (int i = 0; i < argCount; i++)
//		{
//			COBOL_Expression arg = callArguments.arguments.getPrimaryElement(i);
//			AbstractExpression newArg = transformer.transformExpression(generator, arg);
//			args.add(newArg);
//		}
//
//		AbstractVariable var = generator.newVariable(name);
//		AbstractExpression expr = generator.newMethodInvocation(var, args, types, subName);
//		return generator.newExpressionStatement(expr, subName);
	}
}
