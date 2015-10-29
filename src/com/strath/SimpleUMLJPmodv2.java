package com.strath;


import java.io.FileInputStream;
import java.util.List;

import com.github.javaparser.ASTHelper;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.ModifierSet;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.body.VariableDeclaratorId;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.expr.ClassExpr;
import com.github.javaparser.ast.expr.FieldAccessExpr;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.ObjectCreationExpr;
import com.github.javaparser.ast.expr.StringLiteralExpr;
import com.github.javaparser.ast.expr.UnaryExpr;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class SimpleUMLJPmodv2 {

    public static void main(String[] args) throws Exception {
        FileInputStream in = new FileInputStream("MainGUI.java");

        CompilationUnit cu;
        try {
            cu = JavaParser.parse(in);
        } finally {
            in.close();
        }
        // add in the new static field
        addField(cu);
        // then modify this with a visitor in the constructor...
        new ClassDiagramVisitor().visit(cu, null);
        // write the modified cu back...
        System.out.println(cu.toString());
    }
    
    private static void addField(CompilationUnit cu) {
      List<TypeDeclaration> types = cu.getTypes();
      for (TypeDeclaration type : types) {
        List<BodyDeclaration> members = type.getMembers();
    	FieldDeclaration fd = ASTHelper.createFieldDeclaration(ModifierSet.STATIC, ASTHelper.INT_TYPE, "objectId");
    	ASTHelper.addMember(type, fd);
      }
    }

  private static class ClassDiagramVisitor extends VoidVisitorAdapter {
	
    @Override
    public void visit(ConstructorDeclaration n, Object arg) {
    	BlockStmt block = new BlockStmt();
        NameExpr systemOut = ASTHelper.createNameExpr("System.out");
        MethodCallExpr call = new MethodCallExpr(systemOut, "print");
        ASTHelper.addArgument(call, new StringLiteralExpr(n.getName()));
        NameExpr systemOut2 = ASTHelper.createNameExpr("System.out");
        MethodCallExpr call2 = new MethodCallExpr(systemOut, "println");
        ASTHelper.addArgument(call2, new FieldAccessExpr(new NameExpr("this"), "objectId"));
        ASTHelper.addStmt(block, call);
        ASTHelper.addStmt(block, call2);
        UnaryExpr ue = new UnaryExpr(new NameExpr("objectId"), UnaryExpr.Operator.posIncrement);
        ASTHelper.addStmt(block,ue); 
        BlockStmt oldBody = n.getBlock();
        ASTHelper.addStmt(block, oldBody);
        n.setBlock(block);
    }
  }
}



