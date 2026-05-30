// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 6, 2014

package com.eagle.programmar.SQL.Statements;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleString;
import com.eagle.math.EagleValue;
import com.eagle.programmar.SQL.SQL_Expression;
import com.eagle.programmar.SQL.SQL_Type;
import com.eagle.programmar.SQL.Symbols.SQL_Declare_Definition;
import com.eagle.programmar.SQL.Terminals.SQL_Keyword;
import com.eagle.programmar.SQL.Terminals.SQL_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.AssignmentEnum;
import com.eagle.transform.EagleGenerator.StaticEnum;
import com.eagle.transform.EagleGenerator.SubscriptEnum;
import com.eagle.transform.EagleTransformableStatementList;
import com.eagle.transform.EagleTransformer;

public class SQL_DeclareStatement extends TokenSequence
		implements EagleRunnable, EagleTransformableStatementList
{
	public @S(10) SQL_Keyword DECLARE = new SQL_Keyword("DECLARE");
	public @S(20) TokenList<SQL_Declaration> declarations;

	public static class SQL_Declaration extends TokenSequence
	{
		public @S(10) SQL_Declare_Definition id;
		public @S(20) SQL_Type type;
		public @S(30) @OPT SQL_Punctuation colonEquals = new SQL_Punctuation(":=");
		public @S(40) @OPT SQL_Expression initialValue;
		public @S(50) PunctuationSemicolon semicolon;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		for (SQL_Declaration decl : declarations._elements)
		{
			EagleValue value = new EagleString(""); // Create it with a bogus value
			if (decl.initialValue != null && decl.initialValue.isPresent())
			{
				value = interpreter.getEagleValue(decl.initialValue);
			}
			interpreter.setSymbol(decl.id, decl.id.toString(), value);
		}
	}

	@Override
	public ArrayList<AbstractStatement> transformStatement(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		ArrayList<AbstractStatement> result = new ArrayList<AbstractStatement>();

		for (SQL_Declaration decl : declarations._elements)
		{
			String varName = decl.id.getValue();
			AbstractExpression newVal;
			if (decl.initialValue != null && decl.initialValue.isPresent())
			{
				newVal = transformer.transformExpression(generator, decl.initialValue);
				AbstractExpression asgExpr = generator.newAssignmentExpression(varName,
						SubscriptEnum.FIRST_IS_ZERO, null, AssignmentEnum.EQUALS, newVal, decl.initialValue);
				if (decl.type != null && decl.type.isPresent())
				{
					AbstractType varType = SQL_Type.findAbstractType(generator, decl.type);
					AbstractStatement varDecl = generator.newDataDeclaration(StaticEnum.NONE, varName,
							null, varType, asgExpr, null);
					generator.addStatement(varDecl, null);
				}
				else
				{
					result.add(generator.newExpressionStatement(asgExpr, decl.initialValue));
				}
			}
			else
			{
				AbstractType varType = SQL_Type.findAbstractType(generator, decl.type);
				AbstractStatement varDecl = generator.newDataDeclaration(StaticEnum.NONE, varName,
						null, varType, null, null);
				generator.addStatement(varDecl, null);
			}
		}

		return result;
	}
}
