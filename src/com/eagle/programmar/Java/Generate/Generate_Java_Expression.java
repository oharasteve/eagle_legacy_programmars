// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 29, 2016

package com.eagle.programmar.Java.Generate;

public class Generate_Java_Expression
//		implements Generate_Eagle_Expression<Java_Expression, Java_Variable, Java_Type>
{
//	// private Generate_Java _target;
//
//	public Generate_Java_Expression(Generate_Java target)
//	{
//		// _target = target;
//	}
//
//	private static Java_Expression wrapExpression(AbstractToken piece)
//	{
//		Java_Expression expr = new Java_Expression();
//		expr.setWhich(piece);
//		return expr;
//	}
//	
//	//
//	// These are all pass-through functions. Not much logic here
//	//
//
//	@Override
//	public Java_Expression createBuiltIn(BuiltInEnum builtin)
//	{
//		Java_BuiltIn builtinExpr = new Java_BuiltIn();
//		return builtinExpr.generateBuiltIn(builtin, null);
//	}
//
//	@Override
//	public Java_Expression createNumber(int num)
//	{
//		Java_Number number = new Java_Number();
//		return wrapExpression(number.generateNumber(Integer.toString(num), null));
//	}
//
//	@Override
//	public Java_Expression createNumber(String txt)
//	{
//		Java_Number number = new Java_Number();
//		return wrapExpression(number.generateNumber(txt, null));
//	}
//
//	@Override
//	public Java_Expression createHexNumber(String txt)
//	{
//		Java_HexNumber number = new Java_HexNumber();
//		return wrapExpression(number.generateHexNumber(txt, null));
//	}
//
//	@Override
//	public Java_Expression createLiteral(String txt)
//	{
//		Java_Literal lit = new Java_Literal();
//		return wrapExpression(lit.generateLiteral(txt, null));
//	}
//
//	@Override
//	public Java_Expression createCharacter(String txt)
//	{
//		Java_Character_Literal lit = new Java_Character_Literal();
//		return wrapExpression(lit.generateCharLiteral(txt, null));
//	}
//
//	@Override
//	public Java_Variable createVariable(String name)
//	{
//		return Java_Variable.newVariable(name);
//	}
//	
//	@Override
//	public Java_Expression createVariableExpression(String name, Java_Expression subscrExpr)
//	{
//		Java_VariableExpression varExpr = new Java_VariableExpression();
//		return varExpr.generateVarExpr(name, subscrExpr, null);
//	}
//
//	@Override
//	public Java_Expression createMethodCall(Java_Variable name, Collection<AbstractExpression> args)
//	{
//		Java_MethodInvocation methodCall = new Java_MethodInvocation();
//		return methodCall.generateInvocation(name, args, null);
//	}
//
//	@Override
//	public Java_Expression createNew(Java_Type typeName, Collection<AbstractExpression> args)
//	{
//		Java_ClassCreationExpression newInstance = new Java_ClassCreationExpression();
//		return newInstance.generateCreation(typeName, args, null);
//	}
//
//	@Override
//	public Java_Expression createNot(Java_Expression expr, AbstractToken source)
//	{
//		Java_LogicalNotExpression notExpr = new Java_LogicalNotExpression();
//		return notExpr.generateLogicalNot(expr, source);
//	}
//
//	@Override
//	public Java_Expression createParens(Java_Expression expr, AbstractToken source)
//	{
//		// Don't add parens if we don't need them
//		AbstractToken which = expr.getWhich();
//		if (which instanceof Java_ParenthesizedExpression || which instanceof Java_Number
//				|| which instanceof Java_HexNumber || which instanceof Java_Literal
//				|| which instanceof Java_Character_Literal || which instanceof Java_VariableExpression)
//		{
//			return expr;
//		}
//
//		Java_ParenthesizedExpression parenExpr = new Java_ParenthesizedExpression();
//		return parenExpr.generateParentheses(expr, source);
//	}
//
//	@Override
//	public Java_Expression createNegative(NegativeEnum sign,
//			Java_Expression expr, AbstractToken source)
//	{
//		Java_NegativeExpression negExp = new Java_NegativeExpression();
//		return negExp.generateNegative(sign, expr, source);
//	}
//
//	@Override
//	public Java_Expression createAdditive(Java_Expression left,
//			AdditiveEnum add, Java_Expression right, AbstractToken source)
//	{
//		Java_AdditiveExpression addExpr = new Java_AdditiveExpression();
//		return addExpr.generateAdditive(left, add, right, source);
//	}
//
//	@Override
//	public Java_Expression createMultiplicative(Java_Expression left,
//			MultiplicativeEnum mult, Java_Expression right, AbstractToken source)
//	{
//		Java_MultiplicativeExpression mulExp = new Java_MultiplicativeExpression();
//		return mulExp.generateMultiplicative(left, mult, right, source);
//	}
//
//	@Override
//	public Java_Expression createRelational(Java_Expression left, RelationalEnum relational,
//			Java_Expression right, AbstractToken source)
//	{
//		Java_RelationalExpression relExp = new Java_RelationalExpression();
//		return relExp.generateRelational(left, relational, right, source);
//	}
//
//	@Override
//	public Java_Expression createShift(Java_Expression left, ShiftEnum shift,
//			Java_Expression right, AbstractToken source)
//	{
//		Java_ShiftExpression shiftExpr = new Java_ShiftExpression();
//		return shiftExpr.generateShift(left, shift, right, source);
//	}
//
//	@Override
//	public Java_Expression createAnd(Java_Expression left,
//			Java_Expression right, AbstractToken source)
//	{
//		Java_LogicalAndExpression andExpr = new Java_LogicalAndExpression();
//		return andExpr.generateLogicalAnd(left, right, source);
//	}
//
//	@Override
//	public Java_Expression createOr(Java_Expression left,
//			Java_Expression right, AbstractToken source)
//	{
//		Java_LogicalOrExpression orExpr = new Java_LogicalOrExpression();
//		return orExpr.generateLogicalOr(left, LogicalOrEnum.OR, right, source);
//	}
//
//	@Override
//	public Java_Expression createXor(Java_Expression left,
//			Java_Expression right, AbstractToken source)
//	{
//		Java_LogicalOrExpression xorExpr = new Java_LogicalOrExpression();
//		return xorExpr.generateLogicalOr(left, LogicalOrEnum.XOR, right, source);
//	}
//
//	@Override
//	public Java_Expression createAssignment(String varName, AssignmentEnum asg,
//			Java_Expression right, AbstractToken source)
//	{
//		Java_VariableExpression varExpr = new Java_VariableExpression();
//		return varExpr.generateVarExpr(varName, right, source);
//	}
//
//	@Override
//	public Java_Expression createPostIncrement(String varName,
//			IncrementEnum incr, AbstractToken source)
//	{
//		Java_Variable var = Java_Variable.newVariable(varName);
//		Java_PostIncrementExpression postExpr = new Java_PostIncrementExpression();
//		return postExpr.generateIncrement(var, incr, source);
//	}
//
//	@Override
//	public Java_Expression createPreIncrement(String varName,
//		IncrementEnum incr, AbstractToken source)
//	{
//		Java_Variable var = Java_Variable.newVariable(varName);
//		Java_PreIncrementExpression postExpr = new Java_PreIncrementExpression();
//		return postExpr.generateIncrement(var, incr, source);
//	}
//
//	@Override
//	public Java_Expression createSubfield(Java_Expression left,
//			Java_Expression right, AbstractToken source)
//	{
//		Java_SubfieldExpression fldExpr = new Java_SubfieldExpression();
//		return fldExpr.generateSubfield(left, right, source);
//	}
}
