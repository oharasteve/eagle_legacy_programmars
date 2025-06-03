// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 3, 2016

package com.eagle.programmar.Java.Generate_Unused;

public class Generate_Java_Program
//		implements Generate_Eagle_Program<Java_Program, Java_Class, Java_Statement>
{
//	@SuppressWarnings("unused")
//	private Generate_Java _target;
//
//	public Generate_Java_Program(Generate_Java target)
//	{
//		_target = target;
//	}
//
//	@Override
//	public void addProgramComment(Java_Program pgm, String comment, AbstractToken source)
//	{
//		String commentString = (comment == null ? "" : "// " + comment);
//		Java_Comment comm = new Java_Comment(commentString, true);
//		comm.setTransformationSource(source);
//
//		if (pgm.comments1 == null) pgm.comments1 = new TokenList<Java_Comment>();
//		pgm.comments1.addToken(comm);
//		comm.setTransformationSource(source);
//	}
//
//	@Override
//	public void addImport(Java_Program pgm, String importString, boolean useStar, AbstractToken source)
//	{
//		if (pgm.jimportList == null) pgm.jimportList = new TokenList<Java_ImportOrComment>();
//
//		Java_ImportOrComment importOrComment = new Java_ImportOrComment();
//		Java_Import jimport = new Java_Import();
//		jimport.semicolon = new PunctuationSemicolon();
//
//		String[] pieces = importString.split("\\.");
//		int pieceCount = pieces.length;
//		for (int i = 0; i < pieceCount; i++)
//		{
//			String piece = pieces[i];
//
//			if (i == 0)
//			{
//				jimport.id = new Java_Identifier();
//				jimport.id.setValue(piece);
//			}
//			else
//			{
//				if (jimport.dotId == null) jimport.dotId = new TokenList<Java_DotIdentifierStar>();
//				Java_DotIdentifierStar dotId = new Java_DotIdentifierStar();
//				dotId.idStar = new Java_IdentifierOrStar();
//				if (dotId.dot == null) dotId.dot = new PunctuationPeriod();
//				if (useStar && i == pieceCount - 1)
//				{
//					dotId.idStar.setWhich(new PunctuationStar());
//				}
//				else
//				{
//					Java_Identifier newId = new Java_Identifier();
//					newId.setValue(piece);
//					dotId.idStar.setWhich(newId);
//				}
//				jimport.dotId.addToken(dotId);
//			}
//		}
//
//		importOrComment.setWhich(jimport);
//		importOrComment.setTransformationSource(source);
//		pgm.jimportList.addToken(importOrComment);
//	}
//
//	@Override
//	public void setPackage(Java_Program pgm, String pkgName, AbstractToken source)
//	{
//		pgm.jpackage = new Java_Package();
//		pgm.jpackage.setTransformationSource(source);
//		pgm.jpackage.semicolon = new PunctuationSemicolon();
//		pgm.jpackage.setTransformationSource(source);
//		pgm.jpackage.setPresent(true);
//
//		String[] pieces = pkgName.split("\\.");
//		boolean first = true;
//		for (String piece : pieces)
//		{
//			if (first)
//			{
//				first = false;
//				pgm.jpackage.id = new Java_Identifier();
//				pgm.jpackage.id.setValue(piece);
//			}
//			else
//			{
//				Java_MorePackageIds more = new Java_MorePackageIds();
//				more.dot = new PunctuationPeriod();
//				more.id = new Java_Identifier();
//				more.id.setValue(piece);
//
//				if (pgm.jpackage.moreIds == null) pgm.jpackage.moreIds = new TokenList<Java_MorePackageIds>();
//				pgm.jpackage.moreIds.addToken(more);
//			}
//		}
//	}
//
//	@Override
//	public void addClass(Java_Program pgm, Java_Class cls)
//	{
//		if (pgm.classOrEnumList == null) pgm.classOrEnumList = new TokenList<Java_ClassOrEnum>();
//		Java_ClassOrEnum classOrEnum = new Java_ClassOrEnum();
//		classOrEnum.setWhich(cls);
//	}
//
//	@Override
//	public void addProgramStatement(Java_Program pgm, Java_Statement statement)
//	{
//		throw new RuntimeException("need to implement");
//	}
}
