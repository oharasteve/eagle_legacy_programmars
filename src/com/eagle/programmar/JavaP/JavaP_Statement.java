// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 23, 2015

package com.eagle.programmar.JavaP;

import com.eagle.programmar.JavaP.Statements.JavaP_BootstrapMethods;
import com.eagle.programmar.JavaP.Statements.JavaP_Classes;
import com.eagle.programmar.JavaP.Statements.JavaP_Classfile;
import com.eagle.programmar.JavaP.Statements.JavaP_CompiledFrom;
import com.eagle.programmar.JavaP.Statements.JavaP_ConstantPool;
import com.eagle.programmar.JavaP.Statements.JavaP_InnerClasses;
import com.eagle.programmar.JavaP.Statements.JavaP_MajorVersion;
import com.eagle.programmar.JavaP.Statements.JavaP_MinorVersion;
import com.eagle.programmar.JavaP.Statements.JavaP_NestHost;
import com.eagle.programmar.JavaP.Statements.JavaP_NestMembers;
import com.eagle.programmar.JavaP.Statements.JavaP_PublicClass;
import com.eagle.programmar.JavaP.Statements.JavaP_RuntimeVisibleAnnotations;
import com.eagle.programmar.JavaP.Statements.JavaP_Signature;
import com.eagle.programmar.JavaP.Statements.JavaP_SourceFile;
import com.eagle.tokens.TokenChooser;

public class JavaP_Statement extends TokenChooser
{
	public @CHOICE JavaP_BootstrapMethods XXbootstrapMethods;
	public @CHOICE JavaP_Classes XXclasses;
	public @CHOICE JavaP_Classfile XXclassfile;
	public @CHOICE JavaP_CompiledFrom XXcompiledFrom;
	public @CHOICE JavaP_ConstantPool XXconstantPool;
	public @CHOICE JavaP_InnerClasses XXinnerClasses;
	public @CHOICE JavaP_MajorVersion XXmajorVersion;
	public @CHOICE JavaP_MinorVersion XXminorVersion;
	public @CHOICE JavaP_NestHost XXnestHost;
	public @CHOICE JavaP_NestMembers XXnestMembers;
	public @CHOICE JavaP_PublicClass XXpublicClass;
	public @CHOICE JavaP_RuntimeVisibleAnnotations XXruntimeVisibleAnnotations;
	public @CHOICE JavaP_Signature XXsignature;
	public @CHOICE JavaP_SourceFile XXsourceFile;
}
