package com.strath;

import java.io.FileInputStream;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class SimpleUMLJPv1 {

    public static void main(String[] args) throws Exception {
        FileInputStream in = new FileInputStream("test.java");

        CompilationUnit cu;
        try {
            cu = JavaParser.parse(in);
        } finally {
            in.close();
        }

        new ClassDiagramVisitor().visit(cu, null);
    }

    /**
     * Simple visitor implementation for extracting class information 
     */
    private static class ClassDiagramVisitor extends VoidVisitorAdapter {
    	
    	public void visit(ClassOrInterfaceDeclaration n, Object arg){
    		System.out.println("Class Name: " + n.getName());
    		super.visit(n, arg);  
    	}
    }
}