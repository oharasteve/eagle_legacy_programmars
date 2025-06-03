// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, May 17, 2017

package com.eagle.programmar.Python.Generate_Unused;

public class Generate_Python_Expression
//		implements Generate_Eagle_Expression<Python_Expression, Python_Variable, Python_Type>
{
//	// private Generate_Python _target;
//
//	public Generate_Python_Expression(Generate_Python target)
//	{
//		// _target = target;
//	}
//
//	public static Python_Expression wrapExpression(AbstractToken piece)
//	{
//		Python_Expression expr = new Python_Expression();
//		expr.setWhich(piece);
//		return expr;
//	}
//	
//	//
//	// These are all pass-through functions. Not much logic here
//	//
//
//	@Override
//	public Python_Expression createBuiltIn(BuiltInEnum builtin)
//	{
//		Python_BuiltIn builtinExpr = new Python_BuiltIn();
//		return builtinExpr.generateBuiltIn(builtin, null);
//	}
//
//	@Override
//	public Python_Expression createNumber(int num)
//	{
//		Python_Number number = new Python_Number();
//		return wrapExpression(number.generateNumber(Integer.toString(num), null));
//	}
//
//	@Override
//	public Python_Expression createNumber(String txt)
//	{
//		Python_Number number = new Python_Number();
//		return wrapExpression(number.generateNumber(txt, null));
//	}
//
//	@Override
//	public Python_Expression createHexNumber(String txt)
//	{
//		Python_HexNumber number = new Python_HexNumber();
//		return wrapExpression(number.generateHexNumber(txt, null));
//	}
//
//	@Override
//	public Python_Expression createLiteral(String txt)
//	{
//		Python_Literal lit = new Python_Literal();
//		return wrapExpression(lit.generateLiteral(txt, null));
//	}
//
//	@Override
//	public Python_Expression createCharacter(String txt)
//	{
//		Python_Literal lit = new Python_Literal();
//		return wrapExpression(lit.generateLiteral(txt, null));
//	}
//	
//	@Override
//	public Python_Variable createVariable(String name)
//	{
//		return Python_Variable.newVariable(name);
//	}
//	
//	@Override
//	public Python_Expression createVariableExpression(String name, Python_Expression expr)
//	{
//		Python_VariableExpression varExpr = new Python_VariableExpression();
//		return varExpr.generateVarExpr(name, expr, null);
//	}
//
//	@Override
//	public Python_Expression createMethodCall(Python_Variable name, Collection<AbstractExpression> args)
//	{
//		Python_Function_Call functionCall = new Python_Function_Call();
//		return functionCall.generateInvocation(name, args, null);
//	}
//
//	@Override
//	public Python_Expression createNew(Python_Type typeName, Collection<AbstractExpression> args)
//	{
//		throw new RuntimeException("need to implement");
////		Python_ClassCreationExpression newInstance = new Python_ClassCreationExpression();
////		newInstance.jtype = typeName;
////		newInstance.leftParen = new PunctuationLeftParen();
////		newInstance.argList = Python_Transform.createArgumentList(args);
////		newInstance.rightParen = new PunctuationRightParen();
////		
////		return wrapExpression(newInstance);
//	}
//
//	@Override
//	public Python_Expression createNegative(NegativeEnum sign,
//			Python_Expression expr, AbstractToken source)
//	{
//		Python_Negative_Expression negExp = new Python_Negative_Expression();
//		return negExp.generateNegative(sign, expr, source);
//	}
//
//	@Override
//	public Python_Expression createNot(Python_Expression expr, AbstractToken source)
//	{
//		Python_Logical_Not_Expression notExp = new Python_Logical_Not_Expression();
//		return notExp.generateLogicalNot(expr, source);
//	}
//
//	@Override
//	public Python_Expression createParens(Python_Expression expr, AbstractToken source)
//	{
//		return createParenExpr(expr, source);	// Call the static version
//	}
//
//	public static Python_Expression createParenExpr(Python_Expression expr, AbstractToken source)
//	{
//		// Don't add parens if we don't need them
//		AbstractToken which = expr.getWhich();
//		if (which instanceof Python_Parenthesized_Expression || which instanceof Python_Number || which instanceof Python_HexNumber
//				|| which instanceof Python_OctalNumber || which instanceof Python_Literal
//				|| which instanceof Python_VariableExpression)
//		{
//			return expr;
//		}
//
//		Python_Parenthesized_Expression parens = new Python_Parenthesized_Expression();
//		return parens.generateParentheses(expr, source);
//	}
//
//	@Override
//	public Python_Expression createAdditive(Python_Expression left,
//			AdditiveEnum add, Python_Expression right, AbstractToken source)
//	{
//		Python_Additive_Expression addExp = new Python_Additive_Expression();
//		return addExp.generateAdditive(left, add, right, source);
//	}
//
//	@Override
//	public Python_Expression createMultiplicative(Python_Expression left,
//			MultiplicativeEnum mult, Python_Expression right, AbstractToken source)
//	{
//		Python_Multiplicative_Expression mulExp = new Python_Multiplicative_Expression();
//		return mulExp.generateMultiplicative(left, mult, right, source);
//	}
//
//	@Override
//	public Python_Expression createRelational(Python_Expression left, RelationalEnum relational,
//			Python_Expression right, AbstractToken source)
//	{
//		Python_Relational_Expression relExp = new Python_Relational_Expression();
//		return relExp.generateRelational(left, relational, right, source);
//	}
//
//	@Override
//	public Python_Expression createShift(Python_Expression left, ShiftEnum shift,
//			Python_Expression right, AbstractToken source)
//	{
//		Python_Shift_Expression shiftExpr = new Python_Shift_Expression();
//		return shiftExpr.generateShift(left, shift, right, source);
//
//	}
//
//	@Override
//	public Python_Expression createAnd(Python_Expression left,
//			Python_Expression right, AbstractToken source)
//	{
//		Python_Logical_And_Expression andExpr = new Python_Logical_And_Expression();
//		return andExpr.generateLogicalAnd(left, right, source);
//	}
//
//	@Override
//	public Python_Expression createOr(Python_Expression left,
//			Python_Expression right, AbstractToken source)
//	{
//		Python_Logical_Or_Expression orExpr = new Python_Logical_Or_Expression();
//		return orExpr.generateLogicalOr(left, LogicalOrEnum.OR, right, source);
//	}
//
//	@Override
//	public Python_Expression createXor(Python_Expression left,
//			Python_Expression right, AbstractToken source)
//	{
//		Python_Logical_Or_Expression orExpr = new Python_Logical_Or_Expression();
//		return orExpr.generateLogicalOr(left, LogicalOrEnum.XOR, right, source);
//	}
//
//	@Override
//	public Python_Expression createAssignment(String varName, AssignmentEnum asg,
//			Python_Expression right, AbstractToken source)
//	{
//		Python_Expression varExpr = Python_Generator.wrapExpression(
//				Python_Variable.newVariable(varName));
//		Python_Assignment_Expression asgExpr = new Python_Assignment_Expression();
//		return asgExpr.generateAssignment(varExpr, asg, right, source);
//	}
//
//	@Override
//	public Python_Expression createPostIncrement(String varName, IncrementEnum incr,
//			AbstractToken source)
//	{
//		throw new RuntimeException("Need to implement");
//	}
//
//	@Override
//	public Python_Expression createPreIncrement(String varName, IncrementEnum incr,
//			AbstractToken source)
//	{
//		throw new RuntimeException("Need to implement");
//	}
//
//	@Override
//	public Python_Expression createSubfield(Python_Expression left,
//			Python_Expression right, AbstractToken source)
//	{
//		Python_SubfieldExpression fldExpr = new Python_SubfieldExpression(); 
//		return fldExpr.generateSubfield(left, right, source);
//	}
}
