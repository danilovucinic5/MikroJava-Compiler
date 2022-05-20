// generated with ast extension for cup
// version 0.8
// 26/0/2022 16:37:4


package rs.ac.bg.etf.pp1.ast;

public class Statements implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private MoreStatements MoreStatements;

    public Statements (MoreStatements MoreStatements) {
        this.MoreStatements=MoreStatements;
        if(MoreStatements!=null) MoreStatements.setParent(this);
    }

    public MoreStatements getMoreStatements() {
        return MoreStatements;
    }

    public void setMoreStatements(MoreStatements MoreStatements) {
        this.MoreStatements=MoreStatements;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(MoreStatements!=null) MoreStatements.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MoreStatements!=null) MoreStatements.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MoreStatements!=null) MoreStatements.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Statements(\n");

        if(MoreStatements!=null)
            buffer.append(MoreStatements.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Statements]");
        return buffer.toString();
    }
}
