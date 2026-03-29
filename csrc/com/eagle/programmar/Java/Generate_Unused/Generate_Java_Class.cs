// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 12, 2016

namespace com.eagle.programmar.Java.Generate_Unused
{
	public class Generate_Java_Class
	{
	//		implements Generate_Eagle_Class<Java_Class, Java_Method, Java_Statement, Java_Type>
	//	private Generate_Java _target;
	//
	//	public Generate_Java_Class(Generate_Java target)
	//	{
	//		_target = target;
	//	}
	//
	//	@Override
	//	public Java_Class createNewClass(PRIVACY privacy, String className)
	//	{
	//		Java_Class cls = new Java_Class();
	//		cls.className = new Java_Class_Definition();
	//		cls.className.setValue(className);
	//		cls.leftBrace = new PunctuationLeftBrace();
	//		cls.rightBrace = new PunctuationRightBrace();
	//		return cls;
	//	}
	//	
	//	@Override
	//	public void addClassComment(Java_Class parentClass, String comment, AbstractToken source)
	//	{
	//		Java_Comment comm = new Java_Comment("// " + comment, true);
	//		Java_ClassElement elt = new Java_ClassElement();
	//		elt.setWhich(comm);
	//		elt.setTransformationSource(source);
	//
	//		if (parentClass.elements == null) parentClass.elements = new TokenList<Java_ClassElement>();
	//		parentClass.elements.addToken(elt);
	//	}
	//
	//	@Override
	//	public void addClassData(Java_Class parentClass, Java_Statement dataStmt)
	//	{
	//		Java_StaticStatement staticStatement = new Java_StaticStatement();
	//		staticStatement.statement = dataStmt;
	//
	//		Java_ClassElement elt = new Java_ClassElement();
	//		elt.setWhich(staticStatement);
	//
	//		if (parentClass.elements == null) parentClass.elements = new TokenList<Java_ClassElement>();
	//		parentClass.elements.addToken(elt);
	//	}
	//
	//	@Override
	//	public void addMethod(Java_Class parentClass, Java_Method method)
	//	{
	//		Java_ClassElement elt = new Java_ClassElement();
	//		elt.setWhich(method);
	//
	//		if (parentClass.elements == null) parentClass.elements = new TokenList<Java_ClassElement>();
	//		parentClass.elements.addToken(elt);
	//	}
	//
	//	@Override
	//	public void addConstructor(Java_Class parentClass, String className, Collection<AbstractExpression> args,
	//			AbstractToken source)
	//	{
	//		Java_Constructor cons = new Java_Constructor();
	//		cons.setTransformationSource(source);
	//		cons.constructorName = new Java_Current_Class_Reference();
	//		cons.constructorName.setValue(className);
	//
	//		cons.parameters = new Java_ParameterList();
	//		cons.parameters.leftParen = new PunctuationLeftParen();
	//		cons.parameters.rightParen = new PunctuationRightParen();
	//
	//		// Always set public
	//		cons.modifiers = new TokenList<Java_MethodModifier>();
	//		Java_MethodModifier mod = new Java_MethodModifier();
	//		Java_KeywordChoice privacyChoice = new Java_KeywordChoice("public");
	//		mod.setWhich(privacyChoice);
	//		cons.modifiers.setPresent(true);
	//		cons.modifiers.addToken(mod);
	//
	//		// Create the constructor body
	//		cons.body = new Java_MethodBody();
	//		Java_MethodImplementation impl = new Java_MethodImplementation();
	//		impl.block = new Java_StatementBlock();
	//		impl.block.leftBrace = new PunctuationLeftBrace();
	//		impl.block.rightBrace = new PunctuationRightBrace();
	//		cons.body.setWhich(impl);
	//
	//		// If any args are given, create a call to super() with them
	//		if (args != null && args.size() > 0)
	//		{
	//			Java_MethodInvocation func = new Java_MethodInvocation();
	//			func.methodName = Java_Variable.newVariable("super");
	//			func.leftParen = new PunctuationLeftParen();
	//			func.argList = Generate_Java.createArgumentList(args);
	//			func.argList.setPresent(true);
	//			func.rightParen = new PunctuationRightParen();
	//
	//			Java_ExpressionStatement exprStmt = new Java_ExpressionStatement();
	//			exprStmt.expr = new Java_Expression();
	//			exprStmt.expr.setWhich(func);
	//			exprStmt.semicolon = new PunctuationSemicolon();
	//
	//			Java_Statement stmt = new Java_Statement();
	//			stmt.setWhich(exprStmt);
	//
	//			Java_StatementOrComment stmtOrComment = new Java_StatementOrComment();
	//			stmtOrComment.setWhich(stmt);
	//			if (impl.block.statements == null) impl.block.statements = new TokenList<Java_StatementOrComment>();
	//			impl.block.statements.addToken(stmtOrComment);
	//		}
	//
	//		// Add it to the parent class
	//		Java_ClassElement elt = new Java_ClassElement();
	//		elt.setWhich(cons);
	//
	//		if (parentClass.elements == null) parentClass.elements = new TokenList<Java_ClassElement>();
	//		parentClass.elements.addToken(elt);
	//	}
	//
	//	@Override
	//	public Java_Class addInnerClass(PRIVACY privacy, Java_Class parentClass, String className, CLASS_QUALIFIERS qual,
	//			AbstractToken source)
	//	{
	//		// Create the new inner class
	//		Java_Class newClass = new Java_Class();
	//		newClass.className = new Java_Class_Definition();
	//		newClass.className.setValue(className);
	//		newClass.setTransformationSource(source);
	//
	//		// Set public or private
	//		String priv = _target.getPrivacyString(privacy);
	//		Java_ClassModifierList modList = new Java_ClassModifierList();
	//		modList.modifiers = new TokenList<Java_ClassModifier>();
	//		Java_ClassModifier privacyMod = new Java_ClassModifier();
	//		Java_KeywordChoice privacyChoice = new Java_KeywordChoice(priv);
	//		privacyMod.setWhich(privacyChoice);
	//		modList.modifiers.addToken(privacyMod);
	//		modList.modifiers.setPresent(true);
	//
	//		if (qual == CLASS_QUALIFIERS.CHOICE)
	//		{
	//			Java_Annotation annotation = _target.createAnnotation("CHOICE");
	//			Java_ClassModifier annot = new Java_ClassModifier();
	//			annot.setWhich(annotation);
	//			modList.modifiers.addToken(annot);
	//		}
	//
	//		// If parentClass is null, that means it is the main class
	//		if (parentClass != null)
	//		{
	//			// Make sure inner classes are static
	//			Java_ClassModifier staticMod = new Java_ClassModifier();
	//			Java_KeywordChoice staticChoice = new Java_KeywordChoice("static");
	//			staticMod.setWhich(staticChoice);
	//			modList.modifiers.addToken(staticMod);
	//		}
	//
	//		newClass.modifierList = modList;
	//		newClass.modifierList.setPresent(true);
	//		newClass.leftBrace = new PunctuationLeftBrace();
	//		newClass.rightBrace = new PunctuationRightBrace();
	//
	//		Java_Statement stmt = new Java_Statement();
	//		stmt.setWhich(newClass);
	//		Java_ClassElement elt = new Java_ClassElement();
	//		Java_StaticStatement staticStatement = new Java_StaticStatement();
	//		staticStatement.statement = stmt;
	//		elt.setWhich(staticStatement);
	//
	//		if (parentClass != null)
	//		{
	//			if (parentClass.elements == null) parentClass.elements = new TokenList<Java_ClassElement>();
	//			parentClass.elements.addToken(elt);
	//		}
	//
	//		return newClass;
	//	}
	//
	//	@Override
	//	public void setClassExtends(Java_Class parentClass, String extendsClass)
	//	{
	//		parentClass.jextends = new Java_ClassExtends();
	//		parentClass.jextends.setPresent(true);
	//
	//		parentClass.jextends.classNames = new SeparatedList<Java_Identifier_Reference, PunctuationPeriod>();
	//		Java_Identifier_Reference id = new Java_Identifier_Reference();
	//		id.setValue(extendsClass);
	//		parentClass.jextends.classNames.addPrimaryElement(id);
	//	}
	//
	//	@Override
	//	public Java_Class addInnerDataClass(Java_Class parentClass, String className, TYPES type, AbstractToken source)
	//	{
	//		// String newType = Java_Transform.getTypeString(type);
	//
	//		// Create the new inner class
	//		Java_Class newClass = new Java_Class();
	//		newClass.className = new Java_Class_Definition();
	//		newClass.className.setValue(className);
	//		newClass.setTransformationSource(source);
	//
	//		// Add it to the outer class
	//		Java_Statement newStmt = new Java_Statement();
	//		newStmt.setWhich(newClass);
	//		Java_StaticStatement staticStatement = new Java_StaticStatement();
	//		staticStatement.statement = newStmt;
	//		Java_ClassElement elt = new Java_ClassElement();
	//		elt.setWhich(staticStatement);
	//		if (parentClass.elements == null) parentClass.elements = new TokenList<Java_ClassElement>();
	//		parentClass.elements.addToken(elt);
	//
	//		return newClass;
	//	}
	//
	//	@Override
	//	public Java_Type createType(String name)
	//	{
	//		Java_IdList idList = new Java_IdList();
	//		idList.typeName = new Java_Identifier_Reference();
	//		idList.typeName.setValue(name);
	//
	//		Java_Type type = new Java_Type();
	//		type.typeName = new Java_TypeName();
	//		type.typeName.setWhich(idList);
	//		return type;
	//	}
	//
	//	@Override
	//	public void finalize(Java_Class cls)
	//	{
	//		// Nothing to do here
	//	}
	}

}
