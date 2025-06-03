// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, May 17, 2017

package com.eagle.programmar.Python.Generate_Unused;

public class Generate_Python_Method
//		implements Generate_Eagle_Method<Python_Function, Python_Statement>
{
//	@SuppressWarnings("unused")
//	private Generate_Python _target;
//
//	public Generate_Python_Method(Generate_Python target)
//	{
//		_target = target;
//	}
//
//	@Override
//	public Python_Function createMethod(PRIVACY privacy, METHOD_QUALIFIERS qual, TYPES type, String userType,
//			String methodName, ArrayList<MethodArgument> args, AbstractToken source)
//	{
//		// Create the new method skeleton
//		Python_Function method = new Python_Function();
//		method.fnName = new Python_FunctionName();
//		method.header = new Python_FunctionHeader();
//		method.header.colon = new PunctuationColon();
//		Python_Function_Definition name = new Python_Function_Definition();
//		name.setValue(methodName);
//		method.fnName.setWhich(name);
//		method.setTransformationSource(source);
//
//		Python_Parameter_List paramList = new Python_Parameter_List();
//		paramList.leftParen = new PunctuationLeftParen();
//		paramList.rightParen = new PunctuationRightParen();
//		method.header.params = paramList;
//
//		Python_Params parms = new Python_Params();
//		paramList.params = parms;
//		paramList.params.setPresent(true);
//		parms.setPresent(true);
//
//		Python_Variable_Definition self = new Python_Variable_Definition();
//		self.setValue("self");
//		Python_Parameter param = new Python_Parameter();
//		param.setWhich(self);
//		param.setPresent(true);
//		parms.param = param;
//
//		// Collect arguments, if any
//		if (args != null)
//		{
//			for (MethodArgument arg : args)
//			{
//				Python_Variable_Definition var = new Python_Variable_Definition();
//				var.setValue(arg.varName);
//				param = new Python_Parameter();
//				param.setWhich(var);
//				param.setPresent(true);
//
//				if (parms.moreParams == null) parms.moreParams = new TokenList<Python_MoreParams>();
//				Python_MoreParams more = new Python_MoreParams();
//				more.param = param;
//				more.param.setPresent(true);
//				more.comma = new PunctuationComma();
//				parms.moreParams.addToken(more);
//			}
//		}
//
//		return method;
//	}
//
//	@Override
//	public void addMethodComment(Python_Function method, String comment, AbstractToken source)
//	{
//		String commentString = (comment == null ? "" : "# " + comment);
//		Python_Comment comm = new Python_Comment(commentString, true);
//
//		Python_Statement stmt = new Python_Statement();
//		stmt.statementOrComment = new Python_StatementOrComment();
//		stmt.statementOrComment.setWhich(comm);
//		stmt.soln = new Python_StartOfLine();
//		stmt.soln.setPresent(true);
//
//		addMethodStatement(method, stmt, source);
//	}
//
//	@Override
//	public void addMethodStatement(Python_Function method, Python_Statement statement, AbstractToken source)
//	{
//		if (method.header.defBody == null)
//		{
//			method.header.defBody = new Python_StatementBlock();
//			method.header.defBody.setWhich(new Python_MultilineStatement());
//		}
//		Python_MultilineStatement multi = (Python_MultilineStatement) method.header.defBody.getWhich();
//		if (multi.getTransformationSource() == null) multi.setTransformationSource(source);
//		if (multi.statements == null) multi.statements = new TokenList<Python_Statement>();
//		multi.statements.addToken(statement);
//	}
}
