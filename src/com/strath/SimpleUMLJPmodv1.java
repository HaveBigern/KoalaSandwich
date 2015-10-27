package com.strath;


import java.io.FileInputStream;

import com.github.javaparser.ASTHelper;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.StringLiteralExpr;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class SimpleUMLJPmodv1 {

    public static void main(String[] args) throws Exception {
        FileInputStream in = new FileInputStream("test.java");

        CompilationUnit cu;
        try {
            cu = JavaParser.parse(in);
        } finally {
            in.close();
        }
        new ClassDiagramVisitor().visit(cu, null);
        // write the modified cu back...
        System.out.println(cu.toString());
        // In practice you would just write it to a file
    }

    /**
     * Example of how to modify code and insert instrumentation
     */
    private static class ClassDiagramVisitor extends VoidVisitorAdapter {

        @Override
        public void visit(MethodDeclaration n, Object arg) {

            BlockStmt block = new BlockStmt();
            NameExpr systemOut = ASTHelper.createNameExpr("System.out");
            MethodCallExpr call = new MethodCallExpr(systemOut, "println");
            ASTHelper.addArgument(call, new StringLiteralExpr(n.getName()));
            ASTHelper.addStmt(block, call);
            BlockStmt oldBody = n.getBody();
            ASTHelper.addStmt(block, oldBody);
            n.setBody(block);
        }        
    }
}