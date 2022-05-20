// generated with ast extension for cup
// version 0.8
// 26/0/2022 16:37:4


package rs.ac.bg.etf.pp1.ast;

public class MethDecl implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private Main Main;
    private MoreVarDeclList MoreVarDeclList;
    private Statements Statements;

    public MethDecl (Main Main, MoreVarDeclList MoreVarDeclList, Statements Statements) {
        this.Main=Main;
        if(Main!=null) Main.setParent(this);
        this.MoreVarDeclList=MoreVarDeclList;
        if(MoreVarDeclList!=null) MoreVarDeclList.setParent(this);
        this.Statements=Statements;
        if(Statements!=null) Statements.setParent(this);
    }

    public Main getMain() {
        return Main;
    }

    public void setMain(Main Main) {
        this.Main=Main;
    }

    public MoreVarDeclList getMoreVarDeclList() {
        return MoreVarDeclList;
    }

    public void setMoreVarDeclList(MoreVarDeclList MoreVarDeclList) {
        this.MoreVarDeclList=MoreVarDeclList;
    }

    public Statements getStatements() {
        return Statements;
    }

    public void setStatements(Statements Statements) {
        this.Statements=Statements;
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
        if(Main!=null) Main.accept(visitor);
        if(MoreVarDeclList!=null) MoreVarDeclList.accept(visitor);
        if(Statements!=null) Statements.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Main!=null) Main.traverseTopDown(visitor);
        if(MoreVarDeclList!=null) MoreVarDeclList.traverseTopDown(visitor);
        if(Statements!=null) Statements.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Main!=null) Main.traverseBottomUp(visitor);
        if(MoreVarDeclList!=null) MoreVarDeclList.traverseBottomUp(visitor);
        if(Statements!=null) Statements.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MethDecl(\n");

        if(Main!=null)
            buffer.append(Main.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MoreVarDeclList!=null)
            buffer.append(MoreVarDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statements!=null)
            buffer.append(Statements.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MethDecl]");
        return buffer.toString();
    }
}
