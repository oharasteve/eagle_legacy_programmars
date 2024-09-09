// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 16, 2011

package com.eagle.programmar.VB.Statements;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.VB.VB_Expression;
import com.eagle.programmar.VB.VB_Subscript;
import com.eagle.programmar.VB.VB_Type;
import com.eagle.programmar.VB.Symbols.VB_Variable_Definition;
import com.eagle.programmar.VB.Terminals.VB_Keyword;
import com.eagle.programmar.VB.Terminals.VB_KeywordChoice;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.TypeEnum;
import com.eagle.transform.EagleTransformableStatementList;
import com.eagle.transform.EagleTransformer;

public class VB_DataDeclaration extends TokenSequence implements EagleRunnable, AbstractStatement, EagleTransformableStatementList
{
	public @S(10) VB_KeywordChoice modifier = new VB_KeywordChoice("private", "public", "dim", "const");
	public @S(20) @OPT VB_Keyword CONST = new VB_Keyword("const");
	public @S(30) VB_Variable_Definition var;
	public @S(40) @OPT VB_Subscript subscript;
	public @S(50) @OPT TokenList<VB_MoreVariables> moreVariables;
	public @S(60) @OPT VB_DataType dataType;
	public @S(70) @OPT VB_DataInitialization initializer;

	public static class VB_DataType extends TokenSequence
	{
		public @S(10) VB_Keyword AS = new VB_Keyword("as");
		public @S(20) VB_Type type;
	}

	public static class VB_DataInitialization extends TokenSequence
	{
		public @S(10) PunctuationEquals equals;
		public @S(20) VB_Expression expr;
	}

	public static class VB_MoreVariables extends TokenSequence
	{
		public @S(10) PunctuationComma comma;
		public @S(20) VB_Variable_Definition var;
		public @S(30) @OPT VB_Subscript subscript;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (initializer != null && initializer.isPresent())
		{
			EagleValue value = interpreter.getEagleValue(initializer.expr);
			interpreter.setSymbol(var, var.getValue(), value);
		}
		else
		{
			interpreter.setSymbol(var, var.toString(), null);
			if (moreVariables != null && moreVariables.isPresent())
			{
				for (VB_MoreVariables more : moreVariables._elements)
				{
					interpreter.setSymbol(more.var, more.var.getValue(), null);
				}
			}
		}
	}
	
	// Essentially Hungarian-like notation to guess type of a VB variable
	private static AbstractType guessTypeFromName(EagleGenerator generator, String name)
	{
		TypeEnum type;
		if (name.startsWith("bool"))
		{
			type = TypeEnum.BOOLEAN;
		}
		else if (name.startsWith("int"))
		{
			type = TypeEnum.INTEGER;
		}
		else if (name.startsWith("dbl"))
		{
			type = TypeEnum.DOUBLE;
		}
		else if (name.startsWith("str"))
		{
			type = TypeEnum.STRING;
		}
		else
		{
			// return null;
			// throw new RuntimeException("Can't guess name from: " + name);
			type = TypeEnum.INTEGER;
		}

		return generator.transformType(type, name, null);
	}
	
	@Override
	public ArrayList<AbstractStatement> transformStatement(EagleTransformer transformer, EagleGenerator generator)
	{
		ArrayList<AbstractStatement> result = new ArrayList<AbstractStatement>();
		
		TypeEnum type;
		AbstractType newType = null;
		if (dataType != null && dataType.isPresent())
		{
			String typeName = dataType.type.getWhich().toString();
			switch (typeName)
			{
			case "boolean":
				type = TypeEnum.BOOLEAN;
				break;
			case "integer":
				type = TypeEnum.INTEGER;
				break;
			case "double":
				type = TypeEnum.DOUBLE;
				break;
			case "string":
				type = TypeEnum.STRING;
				break;
			default:
				type = TypeEnum.OTHER;
				break;
			}
			newType = generator.transformType(type, typeName, dataType);
		}
		
		AbstractExpression size = null;
		if (subscript != null && subscript.isPresent())
		{
			size = transformer.transformExpression(generator, subscript.exprs.first());
		}
		
		AbstractExpression initial = null;
		if (initializer != null && initializer.isPresent())
		{
			initial = transformer.transformExpression(generator, initializer.expr);
		}
		
		String name = var.getValue();
		AbstractType firstType = newType;
		if (firstType == null)
		{
			firstType = guessTypeFromName(generator, name);
		}
		AbstractStatement stmt = generator.newDataDeclaration(name, size, firstType, initial, this);
		result.add(stmt);
		
		if (moreVariables != null && moreVariables.isPresent())
		{
			for (VB_MoreVariables more : moreVariables._elements)
			{
				size = null;
				if (more.subscript != null && more.subscript.isPresent())
				{
					size = transformer.transformExpression(generator, more.subscript.exprs.first());
				}
				name = more.var.getValue();
				AbstractType nextType = newType;
				if (nextType == null)
				{
					nextType = guessTypeFromName(generator, name);
				}
				stmt = generator.newDataDeclaration(name, size, nextType, initial, more);
				result.add(stmt);
			}
		}

		return result;
	}
}
