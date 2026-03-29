// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, May 17, 2017

namespace com.eagle.programmar.Python.Generate_Unused
{
	public class Generate_Python_Statement
	//		implements Generate_Eagle_Statement<Python_Statement,
	//				Python_Expression, Python_Variable, Python_Type>
	{
	//	private Generate_Python _target;
	//
	//	public Generate_Python_Statement(Generate_Python target)
	//	{
	//		_target = target;
	//	}
	//
	//	public static Python_Statement wrapStatement(AbstractToken token, AbstractToken source)
	//	{
	//		Python_Statement stmt = new Python_Statement();
	//		stmt.soln = new Python_StartOfLine();
	//		stmt.statementOrComment = new Python_StatementOrComment();
	//		Python_SameLineStatement stmtList = new Python_SameLineStatement();
	//		stmtList.statements = new SeparatedList<Python_Simple_Statement, PunctuationSemicolon>();
	//
	//		Python2_Simple_Statement simple = new Python2_Simple_Statement();
	//		simple.setWhich(token);
	//		stmtList.statements.addPrimaryElement(simple);
	//		stmt.statementOrComment.setWhich(stmtList);
	//		stmt.setTransformationSource(source);
	//		return stmt;
	//	}
	//
	//	@Override
	//	public Python_Statement createAssignment(String name, Python_Expression subscript, AssignmentEnum oper,
	//			Python_Expression expression, String comment, AbstractToken source)
	//	{
	//		Python_ExpressionStatement exprStmt = Python_Assignment.generateAssignment(
	//				name, subscript, oper, expression, comment, source);
	//		return wrapStatement(exprStmt, source);
	//	}
	//
	//	@Override
	//	public Python_Statement createStatementBlock(ArrayList<Python_Statement> statements, AbstractToken source)
	//	{
	//		Python_MultilineStatement multiStmt = new Python_MultilineStatement();
	//		multiStmt.statements = new TokenList<Python_Statement>();
	//		multiStmt.eoln = new Python_EndOfLine();
	//
	//		for (Python_Statement statement : statements)
	//		{
	//			multiStmt.statements.addToken(statement);
	//		}
	//
	//		Python_Statement stmt = new Python_Statement();
	//		stmt.statementOrComment = new Python_StatementOrComment();
	//		stmt.statementOrComment.setWhich(multiStmt);
	//		stmt.setTransformationSource(source);
	//		return stmt;
	//	}
	//
	//	@Override
	//	public Python_Statement createFunctionCall(Python_Expression expression, AbstractToken source)
	//	{
	//		Python_Expression expr = expression;
	//
	//		// If there are no parentheses on it, need to force them
	//		AbstractToken which = expression.getWhich();
	//		if (which instanceof Python_VariableExpression)
	//		{
	//			Python_VariableExpression varExpr = (Python_VariableExpression) which;
	//			Python_Variable name = varExpr.variable;
	//			expr = _target._createExpression.createMethodCall(name, null);
	//		}
	//
	//		Python_ExpressionStatement fnStmt = new Python_ExpressionStatement();
	//		fnStmt.expression = expr;
	//		return wrapStatement(fnStmt, source);
	//	}
	//
	//	@Override
	//	public Python_Statement createData(PRIVACY privacy, int qual, int seq, String varName, TYPES type, String userType,
	//			Python_Expression expr, String comment, AbstractToken source)
	//	{
	//		// For arrays, you can use: 'a = [None] * 5' to create an empty array with 5
	//		// slots
	//
	//		// No need to pre-declare variables in python. Only needed if there is an
	//		// initial value
	//		if (expr == null) return null;
	//		Python_ExpressionStatement stmt =  Python_Assignment.generateAssignment(varName, expr, null, expr, comment, source);
	//		return Python_Generator.wrapStatement(stmt);
	//	}
	//
	//	@Override
	//	public Python_Statement createData(PRIVACY privacy, int qual, int seq, String varName, String typeName,
	//			Python_Expression expr, String comment, AbstractToken source)
	//	{
	//		throw new RuntimeException("need to implement");
	//	}
	//
	//	@Override
	//	public Python_Statement createIfStatement1(Python_Expression condition,
	//			Python_Statement ifTrue, Python_Statement ifFalse, AbstractToken source)
	//	{
	//		Python_IfStatement ifStmt = new Python_IfStatement();
	//		return ifStmt.generateIfElse1((Python_Expression) condition, ifTrue, ifFalse, source);
	//	}
	//
	//	@Override
	//	public Python_Statement createIfStatement(Python_Expression condition,
	//			ArrayList<AbstractStatement> ifTrue,
	//			ArrayList<AbstractStatement> ifFalse, AbstractToken source)
	//	{
	//		Python_IfStatement ifStmt = new Python_IfStatement();
	//		return ifStmt.generateIfElse((Python_Expression) condition, ifTrue, ifFalse, source);
	//	}
	//
	//	@Override
	//	public Python_Statement createForLoopStatement(Python_Expression initExpression, Python_Expression condExpression,
	//			Python_Expression incrExpression, Python_Statement action, AbstractToken source)
	//	{
	//		Python_ForStatement forStmt = new Python_ForStatement();
	//		return forStmt.generateForLoop1(initExpression, condExpression, incrExpression, action, source);
	//	}
	//
	//	@Override
	//	public Python_Statement createForRangeStatement(String varName, Python_Expression fromExpression,
	//			Python_Expression toExpression, Python_Expression deltaExpression, Python_Statement action, AbstractToken source)
	//	{
	//		Python_ForStatement forStmt = new Python_ForStatement();
	//		return forStmt.generateForRange1(varName, fromExpression, toExpression,
	//				deltaExpression, action, source);
	//	}
	//
	//	@Override
	//	public Python_Statement createDoUntilStatement1(Python_Expression condition,
	//			Python_Statement action, AbstractToken source)
	//	{
	//		Python_WhileStatement whileStmt = new Python_WhileStatement();
	//		return whileStmt.generateDoUntil1(condition, action, source);
	//	}
	//
	//	@Override
	//	public Python_Statement createDoUntilStatement(Python_Expression condition,
	//			ArrayList<AbstractStatement> actions, AbstractToken source)
	//	{
	//		Python_WhileStatement whileStmt = new Python_WhileStatement();
	//		return whileStmt.generateDoUntil(condition, actions, source);
	//	}
	//
	//	@Override
	//	public Python_Statement createBreakStatement(AbstractToken source)
	//	{
	//		Python_BreakStatement stmt = new Python_BreakStatement();
	//		return wrapStatement(stmt, source);
	//	}
	//
	//	@Override
	//	public Python_Statement createReadLineInt(String var, AbstractToken source)
	//	{
	//		Python_ExpressionStatement stmt = new Python_ExpressionStatement();
	//		String line = var + " = input()";
	//		if (!_target.parseLine(stmt, line)) return null;
	//		return wrapStatement(stmt, source);
	//	}
	//
	//	@Override
	//	public Python_Statement createReadLine(AbstractToken source)
	//	{
	//		Python_ExpressionStatement stmt = new Python_ExpressionStatement();
	//		String line = "raw_input()";
	//		if (!_target.parseLine(stmt, line)) return null;
	//		return wrapStatement(stmt, source);
	//	}
	//
	//	@Override
	//	public Python_Statement createRewriteStatement(String id, String fname, AbstractToken source)
	//	{
	//		String open = id + " = open(\"" + fname + "\", \"w\")";
	//		Python_Assignment stmt = new Python_Assignment();
	//		if (!_target.parseLine(stmt, open)) return null;
	//		return wrapStatement(stmt, source);
	//	}
	//
	//	@Override
	//	public Python_Statement createCloseStatement(String id, AbstractToken source)
	//	{
	//		Python_ExpressionStatement stmt = new Python_ExpressionStatement();
	//		String line = id + ".close()";
	//		if (!_target.parseLine(stmt, line)) return null;
	//		return wrapStatement(stmt, source);
	//	}
	//
	//	@Override
	//	public Python_Statement createWhileStatement1(Python_Expression condition,
	//			Python_Statement action, AbstractToken source)
	//	{
	//		Python_WhileStatement whileStmt = new Python_WhileStatement();
	//		return whileStmt.generateWhile1(condition, action, source);
	//	}
	//
	//	@Override
	//	public Python_Statement createWhileStatement(Python_Expression condition,
	//			ArrayList<AbstractStatement> actions, AbstractToken source)
	//	{
	//		Python_WhileStatement whileStmt = new Python_WhileStatement();
	//		return whileStmt.generateWhile(condition, actions, source);
	//	}
	//
	//	@Override
	//	public Python_Statement createPrintStatement1(Python_Expression line,
	//			boolean newline, AbstractToken source)
	//	{
	//		Python_PrintStatement prtStmt = new Python_PrintStatement();
	//		return prtStmt.generatePrint1(line, newline, source);
	//	}
	//
	//	@Override
	//	public Python_Statement createPrintStatement(ArrayList<AbstractExpression> pieces,
	//			boolean newline, AbstractToken source)
	//	{
	//		Python_PrintStatement prtStmt = new Python_PrintStatement();
	//		return prtStmt.generatePrint(pieces, newline, source);
	//	}
	//
	//	@Override
	//	public Python_Statement createReturnStatement(Python_Expression expr, AbstractToken source)
	//	{
	//		Python_ReturnStatement stmt = new Python_ReturnStatement();
	//		stmt.expressionList = new Python_ExpressionList();
	//		stmt.expressionList.expressions = new SeparatedList<Python_Expression, PunctuationComma>();
	//		stmt.expressionList.expressions.addPrimaryElement(expr);
	//		stmt.expressionList.setPresent(true);
	//		stmt.setTransformationSource(source);
	//		return wrapStatement(stmt, source);
	//	}
	//
	//	@Override
	//	public Python_Statement createQuitStatement(AbstractToken source)
	//	{
	//		Python_ExpressionStatement stmt = new Python_ExpressionStatement();
	//		String line = "sys.exit(0)";
	//		if (!_target.parseLine(stmt, line)) return null;
	//		_target._needsSys = true;
	//		return wrapStatement(stmt, source);
	//	}
	//
	//	public Python_Statement createFromStatement(String from, String imp, AbstractToken source)
	//	{
	//		Python_FromStatement stmt = new Python_FromStatement();
	//		Python_Variable var = Python_Variable.newVariable(from);
	//		stmt.fromName = new SeparatedList<Python_Variable,PunctuationPeriod>();
	//		stmt.fromName.addPrimaryElement(var);
	//				 
	//		stmt.importStatement = new Python_ImportStatement();
	//		stmt.importStatement.what = new Python_ImportWhat();
	//		Python_ImportFile importFile = new Python_ImportFile();
	//		importFile.importName = new SeparatedList<Python_Variable,PunctuationPeriod>();
	//		Python_Variable importVar = Python_Variable.newVariable(imp);
	//		importFile.importName.addPrimaryElement(importVar);
	//		stmt.importStatement.what.setWhich(importFile);
	//
	//		return wrapStatement(stmt, source);
	//	}
	//
	//	public Python_Statement createImportStatement(String imp, AbstractToken source)
	//	{
	//		Python_ImportStatement stmt = new Python_ImportStatement();
	//		stmt.what = new Python_ImportWhat();
	//		Python_ImportFile importFile = new Python_ImportFile();
	//		importFile.importName = new SeparatedList<Python_Variable,PunctuationPeriod>();
	//		Python_Variable importVar = Python_Variable.newVariable(imp);
	//		importFile.importName.addPrimaryElement(importVar);
	//		stmt.what.setWhich(importFile);
	//		return wrapStatement(stmt, source);
	//	}
	//
	//	public Python_Statement createPassStatement(AbstractToken source)
	//	{
	//		Python_PassStatement stmt = new Python_PassStatement();
	//		return wrapStatement(stmt, source);
	//	}
	//
	//	public Python_Statement createCommentStatement(String comment, AbstractToken source)
	//	{
	//		String commentString = (comment == null ? "" : "# " + comment);
	//		Python_Comment comm = new Python_Comment(commentString, true);
	//
	//		Python_Statement stmt = new Python_Statement();
	//		stmt.statementOrComment = new Python_StatementOrComment();
	//		stmt.statementOrComment.setWhich(comm);
	//		stmt.soln = new Python_StartOfLine();
	//		stmt.soln.setPresent(true);
	//		stmt.setTransformationSource(source);
	//
	//		return stmt;
	//	}
	//
	//	@Override
	//	public Python_Statement getCurrentDatetime(String varName, AbstractToken source)
	//	{
	//		Python_Expression expr = new Python_Expression();
	//		String line = "datetime.datetime.now()";
	//		if (!_target.parseLine(expr, line)) return null;
	//		_target._needsDatetime = true;
	//		Python_ExpressionStatement stmt = Python_Assignment.generateAssignment(varName, null,
	//				AssignmentEnum.EQUALS, expr, null, source);
	//		return Python_Generator.wrapStatement(stmt);
	//	}
	}

}
